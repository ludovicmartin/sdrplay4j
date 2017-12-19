package fr.ludovicmartin.sdrplay4j.exception;

import com.mirics.sdrplay.MirSdrApiRspLibrary;

/**
 * GainUpdateError SDRplay API exception.
 *
 * @author Ludovic MARTIN - contact _A_T_ ludovicmartin.fr
 */
public class GainUpdateErrorApiException extends ApiException {

    public GainUpdateErrorApiException() {
        super(MirSdrApiRspLibrary.mir_sdr_ErrT.mir_sdr_GainUpdateError);
    }

}
