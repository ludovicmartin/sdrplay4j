package fr.ludovicmartin.sdrplay4j.exception;

import com.mirics.sdrplay.MirSdrApiRspLibrary;

/**
 * OutOfRange SDRplay API exception.
 *
 * @author Ludovic MARTIN - contact _A_T_ ludovicmartin.fr
 */
public class OutOfRangeApiException extends ApiException {

    public OutOfRangeApiException() {
        super(MirSdrApiRspLibrary.mir_sdr_ErrT.mir_sdr_OutOfRange);
    }

}
