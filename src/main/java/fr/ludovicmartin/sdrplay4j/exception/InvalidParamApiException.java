package fr.ludovicmartin.sdrplay4j.exception;

import com.mirics.sdrplay.MirSdrApiRspLibrary;

/**
 * InvalidParam SDRplay API exception.
 *
 * @author Ludovic MARTIN - contact _A_T_ ludovicmartin.fr
 */
public class InvalidParamApiException extends ApiException {

    public InvalidParamApiException() {
        super(MirSdrApiRspLibrary.mir_sdr_ErrT.mir_sdr_InvalidParam);
    }

}
