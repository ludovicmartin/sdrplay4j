package fr.ludovicmartin.sdrplay4j.exception;

import com.mirics.sdrplay.MirSdrApiRspLibrary;

/**
 * AlreadyInitialised SDRplay API exception.
 *
 * @author Ludovic MARTIN - contact _A_T_ ludovicmartin.fr
 */
public class AlreadyInitialisedApiException extends ApiException {

    public AlreadyInitialisedApiException() {
        super(MirSdrApiRspLibrary.mir_sdr_ErrT.mir_sdr_AlreadyInitialised);
    }

}
