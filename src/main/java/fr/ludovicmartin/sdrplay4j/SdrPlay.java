package fr.ludovicmartin.sdrplay4j;

import com.mirics.sdrplay.MirSdrApiRspLibrary;
import com.sun.jna.ptr.IntByReference;
import fr.ludovicmartin.sdrplay4j.device.Device;
import fr.ludovicmartin.sdrplay4j.device.SdrPlay2Device;
import fr.ludovicmartin.sdrplay4j.exception.AliasingErrorApiException;
import fr.ludovicmartin.sdrplay4j.exception.AlreadyInitialisedApiException;
import fr.ludovicmartin.sdrplay4j.exception.ApiException;
import fr.ludovicmartin.sdrplay4j.exception.FailApiException;
import fr.ludovicmartin.sdrplay4j.exception.FsUpdateErrorApiException;
import fr.ludovicmartin.sdrplay4j.exception.GainUpdateErrorApiException;
import fr.ludovicmartin.sdrplay4j.exception.HwErrorApiException;
import fr.ludovicmartin.sdrplay4j.exception.HwVerErrorApiException;
import fr.ludovicmartin.sdrplay4j.exception.InvalidParamApiException;
import fr.ludovicmartin.sdrplay4j.exception.NotEnabledApiException;
import fr.ludovicmartin.sdrplay4j.exception.NotInitialisedApiException;
import fr.ludovicmartin.sdrplay4j.exception.OutOfMemErrorApiException;
import fr.ludovicmartin.sdrplay4j.exception.OutOfRangeApiException;
import fr.ludovicmartin.sdrplay4j.exception.RfUpdateErrorApiException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * SDRplay API end-point. Singleton class, not thread-safe.
 *
 * @author Ludovic MARTIN - contact _A_T_ ludovicmartin.fr
 */
public class SdrPlay {

    private static final int MAX_DEVICE_COUNT = 16;
    private static SdrPlay instance;
    private boolean debugMode = false;
    private final static MirSdrApiRspLibrary API = MirSdrApiRspLibrary.INSTANCE;

    private SdrPlay() {
    }

    /**
     * Get the API end-point instance.
     *
     * @return API end-point instance
     */
    public static SdrPlay getInstance() {
        if (instance == null) {
            instance = new SdrPlay();
        }
        return instance;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
        API.mir_sdr_DebugEnable(debugMode ? 1 : 0);
    }

    /**
     * Get available devices.
     *
     * @return device list
     * @throws fr.ludovicmartin.sdrplay4j.exception.ApiException
     */
    public List<Device> getDevices() {
        return getDevices(true);
    }

    /**
     * Get all devices.
     *
     * @param availableOnly indicates that only available devices are listed
     * @return device list
     * @throws fr.ludovicmartin.sdrplay4j.exception.ApiException
     */
    public List<Device> getDevices(boolean availableOnly) {
        IntByReference numDevs = new IntByReference();
        MirSdrApiRspLibrary.mir_sdr_DeviceT.ByReference devices = new MirSdrApiRspLibrary.mir_sdr_DeviceT.ByReference();
        MirSdrApiRspLibrary.mir_sdr_DeviceT[] deviceData = (MirSdrApiRspLibrary.mir_sdr_DeviceT[]) devices.toArray(MAX_DEVICE_COUNT);
        checkResultCode(API.mir_sdr_GetDevices(devices, numDevs, MAX_DEVICE_COUNT));

        List<Device> result = new ArrayList(numDevs.getValue());
        for (int i = 0; i < numDevs.getValue(); i++) {
            try {
                MirSdrApiRspLibrary.mir_sdr_DeviceT deviceDataItem = deviceData[i];
                if (!availableOnly || deviceDataItem.devAvail == 1) {
                    Device device = null;
                    if (deviceDataItem.hwVer == 2) {
                        device = new SdrPlay2Device(i, deviceDataItem.DevNm.getString(0), deviceDataItem.SerNo.getString(0));
                    } else {
                        throw new Exception("Unsupported hardware: " + deviceDataItem.DevNm.getString(0));
                    }
                    result.add(device);
                }
            } catch (Exception ex) {
                Logger.getGlobal().log(Level.WARNING, ex.getMessage());
            }
        }
        return result;
    }

    /**
     * Check an API result code. An APIException is thrown if different from
     * <code>mir_sdr_Success</code>.
     *
     * @param code API result code
     * @throws ApiException
     */
    public static void checkResultCode(int code) throws ApiException {
        switch (code) {
            case MirSdrApiRspLibrary.mir_sdr_ErrT.mir_sdr_Success:
                return;
            case MirSdrApiRspLibrary.mir_sdr_ErrT.mir_sdr_Fail:
                throw new FailApiException();
            case MirSdrApiRspLibrary.mir_sdr_ErrT.mir_sdr_InvalidParam:
                throw new InvalidParamApiException();
            case MirSdrApiRspLibrary.mir_sdr_ErrT.mir_sdr_OutOfRange:
                throw new OutOfRangeApiException();
            case MirSdrApiRspLibrary.mir_sdr_ErrT.mir_sdr_GainUpdateError:
                throw new GainUpdateErrorApiException();
            case MirSdrApiRspLibrary.mir_sdr_ErrT.mir_sdr_RfUpdateError:
                throw new RfUpdateErrorApiException();
            case MirSdrApiRspLibrary.mir_sdr_ErrT.mir_sdr_FsUpdateError:
                throw new FsUpdateErrorApiException();
            case MirSdrApiRspLibrary.mir_sdr_ErrT.mir_sdr_HwError:
                throw new HwErrorApiException();
            case MirSdrApiRspLibrary.mir_sdr_ErrT.mir_sdr_AliasingError:
                throw new AliasingErrorApiException();
            case MirSdrApiRspLibrary.mir_sdr_ErrT.mir_sdr_AlreadyInitialised:
                throw new AlreadyInitialisedApiException();
            case MirSdrApiRspLibrary.mir_sdr_ErrT.mir_sdr_NotInitialised:
                throw new NotInitialisedApiException();
            case MirSdrApiRspLibrary.mir_sdr_ErrT.mir_sdr_NotEnabled:
                throw new NotEnabledApiException();
            case MirSdrApiRspLibrary.mir_sdr_ErrT.mir_sdr_HwVerError:
                throw new HwVerErrorApiException();
            case MirSdrApiRspLibrary.mir_sdr_ErrT.mir_sdr_OutOfMemError:
                throw new OutOfMemErrorApiException();
            default:
                throw new RuntimeException("Unknown API result code: " + code);
        }
    }
}
