package fr.ludovicmartin.sdrplay4j.exception;

import com.mirics.sdrplay.MirSdrApiRspLibrary;

/**
 * RfUpdateError SDRplay API exception.
 *
 * @author Ludovic MARTIN - contact _A_T_ ludovicmartin.fr
 */
public class RfUpdateErrorApiException extends ApiException {

    public RfUpdateErrorApiException() {
        super(MirSdrApiRspLibrary.mir_sdr_ErrT.mir_sdr_RfUpdateError);
    }

}
