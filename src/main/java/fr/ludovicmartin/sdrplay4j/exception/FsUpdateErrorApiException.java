package fr.ludovicmartin.sdrplay4j.exception;

import com.mirics.sdrplay.MirSdrApiRspLibrary;

/**
 * FsUpdateError SDRplay API exception.
 *
 * @author Ludovic MARTIN - contact _A_T_ ludovicmartin.fr
 */
public class FsUpdateErrorApiException extends ApiException {

    public FsUpdateErrorApiException() {
        super(MirSdrApiRspLibrary.mir_sdr_ErrT.mir_sdr_FsUpdateError);
    }

}
