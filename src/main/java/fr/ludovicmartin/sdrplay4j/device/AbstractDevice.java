package fr.ludovicmartin.sdrplay4j.device;

import com.mirics.sdrplay.MirSdrApiRspLibrary;
import com.mirics.sdrplay.MirSdrApiRspLibrary.mir_sdr_SetGrModeT;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import fr.ludovicmartin.sdrplay4j.SdrPlay;
import java.util.Objects;

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
                samplesPerPacket, (PointerByReference xi, PointerByReference xq, int firstSampleNum, int grChanged, int rfChanged, int fsChanged, int numSamples, int reset, Pointer cbContext) -> {
                },
                (int gRdB, int lnaGRdB, Pointer cbContext) -> {
                },
                null
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
