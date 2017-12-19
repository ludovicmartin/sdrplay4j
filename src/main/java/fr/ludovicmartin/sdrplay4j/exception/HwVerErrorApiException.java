package fr.ludovicmartin.sdrplay4j.exception;

import com.mirics.sdrplay.MirSdrApiRspLibrary;

/**
 * HwVerError SDRplay API exception.
 *
 * @author Ludovic MARTIN - contact _A_T_ ludovicmartin.fr
 */
public class HwVerErrorApiException extends ApiException {

    public HwVerErrorApiException() {
        super(MirSdrApiRspLibrary.mir_sdr_ErrT.mir_sdr_HwVerError);
    }

}
