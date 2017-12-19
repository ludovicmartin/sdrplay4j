package fr.ludovicmartin.sdrplay4j.exception;

import com.mirics.sdrplay.MirSdrApiRspLibrary;

/**
 * OutOfMemError SDRplay API exception.
 *
 * @author Ludovic MARTIN - contact _A_T_ ludovicmartin.fr
 */
public class OutOfMemErrorApiException extends ApiException {

    public OutOfMemErrorApiException() {
        super(MirSdrApiRspLibrary.mir_sdr_ErrT.mir_sdr_OutOfMemError);
    }

}
