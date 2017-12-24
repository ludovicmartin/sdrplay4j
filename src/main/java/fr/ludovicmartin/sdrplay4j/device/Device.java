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

    public int getId();

    public String getName();

    public String getSerialNumber();

    public void addGainReductionListener(GainListener listener);

    public void addStreamListener(StreamListener listener);

    public void removeGainReductionListener(GainListener listener);

    public void removeStreamListener(StreamListener listener);

    public void setPpmOffset(double value);

    public void setAntennaPort(AntennaPort port);

    public boolean isOpen();

    public void open(
            int gainReduction,
            int sampleRate,
            int tunerFrequency,
            Bandwidth bandWidth,
            IfType ifType
    );

    public void close();

}
