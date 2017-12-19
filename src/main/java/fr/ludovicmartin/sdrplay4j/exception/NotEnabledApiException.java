package fr.ludovicmartin.sdrplay4j.exception;

import com.mirics.sdrplay.MirSdrApiRspLibrary;

/**
 * NotEnabled SDRplay API exception.
 *
 * @author Ludovic MARTIN - contact _A_T_ ludovicmartin.fr
 */
public class NotEnabledApiException extends ApiException {

    public NotEnabledApiException() {
        super(MirSdrApiRspLibrary.mir_sdr_ErrT.mir_sdr_NotEnabled);
    }

}
