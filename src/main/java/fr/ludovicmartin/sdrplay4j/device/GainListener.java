package fr.ludovicmartin.sdrplay4j.device;

/**
 * Gain listener interface.
 *
 * @author Ludovic MARTIN - contact _A_T_ ludovicmartin.fr
 */
public interface GainListener {

    /**
     *
     * @param ifGainReduction new IF gain reduction value applied by the gain
     * update (dB).
     * @param lnaGainReduction LNA gain reduction value (dB).
     */
    public void onGainChanged(long ifGainReduction, long lnaGainReduction);
}
