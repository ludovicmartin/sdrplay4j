package fr.ludovicmartin.sdrplay4j.device;

import com.mirics.sdrplay.MirSdrApiRspLibrary.mir_sdr_Bw_MHzT;
import com.mirics.sdrplay.MirSdrApiRspLibrary.mir_sdr_If_kHzT;
import com.mirics.sdrplay.MirSdrApiRspLibrary.mir_sdr_RSPII_AntennaSelectT;
import java.util.Objects;

/**
 * Device interface.
 *
 * @author Ludovic MARTIN - contact _A_T_ ludovicmartin.fr
 */
public interface Device {

    /**
     * Antenna port type.
     */
    public static class AntennaPort {

        public static final AntennaPort A = new AntennaPort(mir_sdr_RSPII_AntennaSelectT.mir_sdr_RSPII_ANTENNA_A);
        public static final AntennaPort B = new AntennaPort(mir_sdr_RSPII_AntennaSelectT.mir_sdr_RSPII_ANTENNA_B);
        public static final AntennaPort HI_Z = new AntennaPort(-1);

        protected final int value;

        public AntennaPort(int value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        @Override
        public boolean equals(Object obj) {
            return (obj instanceof AntennaPort) && ((AntennaPort) obj).value == this.value;
        }

    }

    /**
     * Bandwidth type.
     */
    public static class Bandwidth {

        public static final Bandwidth BW_UNDEFINED = new Bandwidth(mir_sdr_Bw_MHzT.mir_sdr_BW_Undefined);
        public static final Bandwidth BW_0_200 = new Bandwidth(mir_sdr_Bw_MHzT.mir_sdr_BW_0_200);
        public static final Bandwidth BW_0_300 = new Bandwidth(mir_sdr_Bw_MHzT.mir_sdr_BW_0_300);
        public static final Bandwidth BW_0_600 = new Bandwidth(mir_sdr_Bw_MHzT.mir_sdr_BW_0_600);
        public static final Bandwidth BW_1_536 = new Bandwidth(mir_sdr_Bw_MHzT.mir_sdr_BW_1_536);
        public static final Bandwidth BW_5_000 = new Bandwidth(mir_sdr_Bw_MHzT.mir_sdr_BW_5_000);
        public static final Bandwidth BW_6_000 = new Bandwidth(mir_sdr_Bw_MHzT.mir_sdr_BW_6_000);
        public static final Bandwidth BW_7_000 = new Bandwidth(mir_sdr_Bw_MHzT.mir_sdr_BW_7_000);
        public static final Bandwidth BW_8_000 = new Bandwidth(mir_sdr_Bw_MHzT.mir_sdr_BW_8_000);

        public static final AntennaPort A = new AntennaPort(mir_sdr_RSPII_AntennaSelectT.mir_sdr_RSPII_ANTENNA_A);
        public static final AntennaPort B = new AntennaPort(mir_sdr_RSPII_AntennaSelectT.mir_sdr_RSPII_ANTENNA_B);
        public static final AntennaPort HI_Z = new AntennaPort(-1);

        protected final int value;

        public Bandwidth(int value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        @Override
        public boolean equals(Object obj) {
            return (obj instanceof Bandwidth) && ((Bandwidth) obj).value == this.value;
        }

    }

    /**
     * IF type.
     */
    public static class IfType {

        public static final IfType IF_UNDEFINED = new IfType(mir_sdr_If_kHzT.mir_sdr_IF_Undefined);
        public static final IfType IF_ZERO = new IfType(mir_sdr_If_kHzT.mir_sdr_IF_Zero);
        public static final IfType IF_0_450 = new IfType(mir_sdr_If_kHzT.mir_sdr_IF_0_450);
        public static final IfType IF_1_620 = new IfType(mir_sdr_If_kHzT.mir_sdr_IF_1_620);
        public static final IfType IF_2_048 = new IfType(mir_sdr_If_kHzT.mir_sdr_IF_2_048);

        protected final int value;

        public IfType(int value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        @Override
        public boolean equals(Object obj) {
            return (obj instanceof IfType) && ((IfType) obj).value == this.value;
        }

    }

    /**
     * Get the device ID.
     *
     * @return id
     */
    public int getId();

    /**
     * Get the device name.
     *
     * @return device name
     */
    public String getName();

    /**
     * Get the device serial number.
     *
     * @return serial number
     */
    public String getSerialNumber();

    /**
     * Add a gain reduction listener.
     *
     * @param listener listener
     */
    public void addGainReductionListener(GainListener listener);

    /**
     * Add a stream listener.
     *
     * @param listener listener
     */
    public void addStreamListener(StreamListener listener);

    /**
     * Remove a gain reduction listener.
     *
     * @param listener listener
     */
    public void removeGainReductionListener(GainListener listener);

    /**
     * Remove a stream listener.
     *
     * @param listener listener
     */
    public void removeStreamListener(StreamListener listener);

    /**
     * Set the PPM offset.
     *
     * @param value parts per million offset (e.g. +/-1 ppm specifies a +/-24Hz
     * error for a 24MHz crystal)
     */
    public void setPpmOffset(double value);

    /**
     * Set the antenna port.
     *
     * @param port port
     */
    public void setAntennaPort(AntennaPort port);

    /**
     * Check if device is open.
     *
     * @return true if open, false otherwise.
     */
    public boolean isOpen();

    /**
     * Open the device.
     *
     * @param gainReduction initial gain reduction in dB
     * @param sampleRate sample frequency in MHz (2-10MHz)
     * @param tunerFrequency tuner frequency in MHz
     * @param bandWidth bandwidth type
     * @param ifType IF type
     */
    public void open(
            int gainReduction,
            int sampleRate,
            int tunerFrequency,
            Bandwidth bandWidth,
            IfType ifType
    );

    /**
     * Close the device.
     */
    public void close();

}
