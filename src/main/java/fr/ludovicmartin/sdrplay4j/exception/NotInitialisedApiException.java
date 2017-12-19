package fr.ludovicmartin.sdrplay4j.exception;

import com.mirics.sdrplay.MirSdrApiRspLibrary;

/**
 * NotInitialised SDRplay API exception.
 *
 * @author Ludovic MARTIN - contact _A_T_ ludovicmartin.fr
 */
public class NotInitialisedApiException extends ApiException {

    public NotInitialisedApiException() {
        super(MirSdrApiRspLibrary.mir_sdr_ErrT.mir_sdr_NotInitialised);
    }

}
