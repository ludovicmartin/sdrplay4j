package fr.ludovicmartin.sdrplay4j.device;

import java.util.Objects;

/**
 * Abstract device.
 *
 * @author Ludovic MARTIN - contact _A_T_ ludovicmartin.fr
 */
public abstract class AbstractDevice implements Device {

    protected int id;
    protected String name;
    protected String serialNumber;

    public AbstractDevice(int id, String name, String serialNumber) {
        this.id = id;
        this.name = name;
        this.serialNumber = serialNumber;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSerialNumber() {
        return serialNumber;
    }

    @Override
    public String toString() {
        return "#" + id + " (" + name+")";
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.serialNumber) + Objects.hashCode(this.id);
    }

    @Override
    public boolean equals(Object obj) {
        return getClass() == obj.getClass()
                && Objects.equals(this.id, ((AbstractDevice) obj).id)
                && Objects.equals(this.serialNumber, ((AbstractDevice) obj).serialNumber);
    }

}
