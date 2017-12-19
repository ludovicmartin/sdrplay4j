package fr.ludovicmartin.sdrplay4j.exception;

import com.mirics.sdrplay.MirSdrApiRspLibrary;

/**
 * Fail SDRplay API exception.
 *
 * @author Ludovic MARTIN - contact _A_T_ ludovicmartin.fr
 */
public class FailApiException extends ApiException {

    public FailApiException() {
        super(MirSdrApiRspLibrary.mir_sdr_ErrT.mir_sdr_Fail);
    }

}
