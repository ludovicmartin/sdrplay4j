package fr.ludovicmartin.sdrplay4j.exception;

import com.mirics.sdrplay.MirSdrApiRspLibrary;
import java.util.HashMap;
import java.util.Map;

/**
 * SDRplay API exception.
 *
 * @author Ludovic MARTIN - contact _A_T_ ludovicmartin.fr
 */
public abstract class ApiException extends RuntimeException {

    private final int code;
    private static final Map<Integer, String> MESSAGES = new HashMap<Integer, String>() {
        {
            put(MirSdrApiRspLibrary.mir_sdr_ErrT.mir_sdr_Fail, "Other failure mechanism (thread / mutex create)");
            put(MirSdrApiRspLibrary.mir_sdr_ErrT.mir_sdr_InvalidParam, "NULL pointers");
            put(MirSdrApiRspLibrary.mir_sdr_ErrT.mir_sdr_OutOfRange, "Requested parameters outside of allowed range");
            put(MirSdrApiRspLibrary.mir_sdr_ErrT.mir_sdr_GainUpdateError, "Previous update has not yet been applied");
            put(MirSdrApiRspLibrary.mir_sdr_ErrT.mir_sdr_RfUpdateError, "Previous update has not yet been applied");
            put(MirSdrApiRspLibrary.mir_sdr_ErrT.mir_sdr_FsUpdateError, "Previous update has not yet been applied");
            put(MirSdrApiRspLibrary.mir_sdr_ErrT.mir_sdr_HwError, "Failed to access device");
            put(MirSdrApiRspLibrary.mir_sdr_ErrT.mir_sdr_AliasingError, "Requested parameters will cause aliasing");
            put(MirSdrApiRspLibrary.mir_sdr_ErrT.mir_sdr_AlreadyInitialised, "API has been initialised previously");
            put(MirSdrApiRspLibrary.mir_sdr_ErrT.mir_sdr_NotInitialised, "API has not been initialised");
            put(MirSdrApiRspLibrary.mir_sdr_ErrT.mir_sdr_NotEnabled, "The requested change has not been applied");
            put(MirSdrApiRspLibrary.mir_sdr_ErrT.mir_sdr_HwVerError, "Incorrect device");
            put(MirSdrApiRspLibrary.mir_sdr_ErrT.mir_sdr_OutOfMemError, "Out of memory");
        }
    };

    public ApiException(int code) {
        super(MESSAGES.get(code));
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
