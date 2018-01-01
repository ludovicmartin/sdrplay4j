package fr.ludovicmartin.sdrplay4j.device;

import com.mirics.sdrplay.MirSdrApiRspLibrary;
import com.mirics.sdrplay.MirSdrApiRspLibrary.mir_sdr_SetGrModeT;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import fr.ludovicmartin.sdrplay4j.SdrPlay;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Abstract device.
 *
 * @author Ludovic MARTIN - contact _A_T_ ludovicmartin.fr
 */
public abstract class AbstractDevice implements Device {

    protected int id;
    protected String name;
    protected String serialNumber;

    protected boolean open = false;
    protected final static MirSdrApiRspLibrary API = MirSdrApiRspLibrary.INSTANCE;

    private static Device currentDevice = null;
    private final Set<StreamListener> streamListeners = new HashSet<>();
    private final Set<GainListener> gainListeners = new HashSet<>();

    public AbstractDevice(int id, String name, String serialNumber) {
        this.id = id;
        this.name = name;
        this.serialNumber = serialNumber;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSerialNumber() {
        return serialNumber;
    }

    @Override
    public String toString() {
        return "#" + id + " (" + name + ")";
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.serialNumber) + Objects.hashCode(this.id);
    }

    @Override
    public boolean equals(Object obj) {
        return getClass() == obj.getClass()
                && Objects.equals(this.id, ((AbstractDevice) obj).id)
                && Objects.equals(this.serialNumber, ((AbstractDevice) obj).serialNumber);
    }

    protected void selectDevice(Device device) {
        if (currentDevice == null || !currentDevice.equals(device)) {
            SdrPlay.checkResultCode(API.mir_sdr_SetDeviceIdx(device.getId()));
            currentDevice = device;
        }
    }

    @Override
    public void addGainReductionListener(GainListener listener) {
        gainListeners.add(listener);
    }

    @Override
    public void addStreamListener(StreamListener listener) {
        streamListeners.add(listener);
    }

    @Override
    public void removeGainReductionListener(GainListener listener) {
        gainListeners.remove(listener);
    }

    @Override
    public void removeStreamListener(StreamListener listener) {
        streamListeners.remove(listener);
    }

    @Override
    public void setPpmOffset(double value) {
        selectDevice(this);
        SdrPlay.checkResultCode(API.mir_sdr_SetPpm(value));
    }

    @Override
    public boolean isOpen() {
        return open;
    }

    @Override
    public void open(
            int gainReduction,
            int sampleRate,
            int tunerFrequency,
            Bandwidth bandWidth,
            IfType ifType
    ) {
        if (open) {
            throw new IllegalStateException("Device alreay open");
        }
        selectDevice(this);
        IntByReference samplesPerPacket = new IntByReference();
        SdrPlay.checkResultCode(API.mir_sdr_StreamInit(new IntByReference(gainReduction),
                sampleRate / 1e6,
                tunerFrequency / 1e6,
                bandWidth.value,
                ifType.value,
                0,
                new IntByReference(0),
                mir_sdr_SetGrModeT.mir_sdr_USE_SET_GR,
                samplesPerPacket, (xi, xq, _firstSampleNum, grChanged, rfChanged, fsChanged, _numSamples, _reset, cbContext) -> {
                    if (!streamListeners.isEmpty()) {
                        long firstSampleNum = (long) _firstSampleNum & 0xFFFFFFFF;
                        long numSamples = (long) _numSamples & 0xFFFFFFFF;
                        long reset = (long) _reset & 0xFFFFFFFF;
                        short[] iBuffer = xi.getShortArray(0, (int) numSamples);
                        short[] qBuffer = xq.getShortArray(0, (int) numSamples);
                        streamListeners.forEach(listener -> listener.onStreamData(firstSampleNum, iBuffer, qBuffer, reset > 0, grChanged > 0, rfChanged > 0, fsChanged > 0));
                    }
                },
                (_gRdB, _lnaGRdB, cbContext) -> {
                    if (!gainListeners.isEmpty()) {
                        long gRdB = (long) _gRdB & 0xFFFFFFFF;
                        long lnaGRdB = (long) _lnaGRdB & 0xFFFFFFFF;
                        gainListeners.forEach(listener -> listener.onGainChanged(gRdB, lnaGRdB));
                    }
                },
                Pointer.NULL
        ));
        open = true;
    }

    @Override
    public void close() {
        if (!open) {
            throw new IllegalStateException("Device alreay closed");
        }
        selectDevice(this);
        SdrPlay.checkResultCode(API.mir_sdr_StreamUninit());
        SdrPlay.checkResultCode(API.mir_sdr_ReleaseDeviceIdx());
        open = false;
    }

    @Override
    protected void finalize() throws Throwable {
        if (open) {
            close();
        }
        super.finalize();
    }

}
