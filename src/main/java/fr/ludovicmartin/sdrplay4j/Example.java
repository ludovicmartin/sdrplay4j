package fr.ludovicmartin.sdrplay4j;

import fr.ludovicmartin.sdrplay4j.device.Device;
import java.io.ByteArrayOutputStream;

/**
 * Simple example program.
 *
 * @author Ludovic MARTIN - contact _A_T_ ludovicmartin.fr
 */
public class Example {

    public static void main(String[] args) throws Exception {
        SdrPlay sdr = SdrPlay.getInstance();

        sdr.setDebugMode(false);
        System.out.println("=========================================");
        System.out.println("API version: " + sdr.getApiVersion());
        System.out.println("Devices: ");
        sdr.getDevices().forEach(device -> System.out.println(" - " + device));
        System.out.println("=========================================");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Device device = sdr.getDevices().stream().findFirst().orElse(null);
        if (device != null) {
            System.out.println("Capturing from the first device found...");
            device.open(40, 2048000, 106800000, Device.Bandwidth.BW_1_536, Device.IfType.IF_ZERO);
            device.addGainReductionListener((ifGainReduction, lnaGainReduction) -> {
            });
            device.addStreamListener((firstSampleNumber, iBuffer, qBuffer, reseted, gainReductionChanged, tunerFrequencyChanged, sampleFrequencyChanged) -> {
                for (int i = 0; i < iBuffer.length; i++) {
                    int iValue = iBuffer[i];
                    int qValue = qBuffer[i];
                    outputStream.write((byte) (iValue >> 8 & 0xFF));
                    outputStream.write((byte) (iValue & 0xFF));
                    outputStream.write((byte) (qValue >> 8 & 0xFF));
                    outputStream.write((byte) (qValue & 0xFF));
                }
            });
            Thread.sleep(10000);
            device.close();

            System.out.println("Captured bytes: " + outputStream.toByteArray().length);
        }
    }
}
