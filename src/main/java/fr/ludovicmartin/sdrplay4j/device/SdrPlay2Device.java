package fr.ludovicmartin.sdrplay4j.device;

import fr.ludovicmartin.sdrplay4j.SdrPlay;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * SDRplay2 device.
 *
 * @author Ludovic MARTIN - contact _A_T_ ludovicmartin.fr
 */
public class SdrPlay2Device extends AbstractDevice {

    public SdrPlay2Device(int id, String name, String serialNumber) {
        super(id, name, serialNumber);
    }

    @Override
    public void setAntennaPort(AntennaPort port) {
        if (port.equals(AntennaPort.A) || port.equals(AntennaPort.B)) {
            SdrPlay.checkResultCode(API.mir_sdr_RSPII_AntennaControl(port.value));
            SdrPlay.checkResultCode(API.mir_sdr_AmPortSelect(0));
        } else if (port.equals(AntennaPort.HI_Z)) {
            if (isOpen()) {
                Logger.getGlobal().log(Level.INFO, "Selecting Hi-Z port has no effect while device is open");
            }
            SdrPlay.checkResultCode(API.mir_sdr_AmPortSelect(1));
        } else {
            throw new IllegalArgumentException("Illegal antenna port: " + port);
        }
    }
}
