package fr.ludovicmartin.sdrplay4j.device;

/**
 * Stream listener interface.
 *
 * @author Ludovic MARTIN - contact _A_T_ ludovicmartin.fr
 */
@FunctionalInterface
public interface StreamListener {

    /**
     * Invoked on stream data.
     *
     * @param firstSampleNumber number of first sample in buffer (used for
     * synchronous updates). Note that this is divided in the API by the
     * decimationFactor, to make it relative to the output sample rate. The
     * values specified for sample number and period for synchronous updates
     * should also be relative to the output sample rate.
     * @param iBuffer real part values
     * @param qBuffer imaginary part values
     * @param reseted indicates if a re-initialisation has occurred and that the
     * local buffering should be reset.
     * @param gainReductionChanged indicates the gain reduction has changed.
     * @param tunerFrequencyChanged indicates the tuner frequency has changed.
     * @param sampleFrequencyChanged indicates the sample frequency has changed.
     */
    public void onStreamData(long firstSampleNumber, short[] iBuffer, short[] qBuffer, boolean reseted, boolean gainReductionChanged, boolean tunerFrequencyChanged, boolean sampleFrequencyChanged);
}
