package transport;

import java.util.List;
import java.util.Objects;

public abstract class VehicleA implements Vehicle
{
    private String number      = null;

    public VehicleA(String number)
    {
        this.number      = number;
    }


    @Override
    public  String getVehicleNumber() {
        return number;
    }

    @Override
    public void setVehicleNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VehicleA)) return false;
        VehicleA vehicleA = (VehicleA) o;
        return Objects.equals(number, vehicleA.number);
    }

    @Override
    public int hashCode() {

        return Objects.hash(number);
    }
}