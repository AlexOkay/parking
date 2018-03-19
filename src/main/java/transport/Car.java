package transport;


import java.util.Comparator;
import java.util.Objects;

public class Car  extends VehicleA
{
    private String type        = null;
    private String model       = null;
    final private int size        = 1;
    private int    parkMastery = 0;
    //blah blah property



    public Car(String number) {
        super(number);
    }


    public int getSize() {
        return size;
    }

    @Override
    public String getVehicleNumber() {
        return super.getVehicleNumber();
    }

    @Override
    public void setVehicleNumber(String number) {
        super.setVehicleNumber(number);
    }

    @Override
    public Vehicle getVehicle(String number) {
        return this;
    }

    @Override
    public void beep() {
        System.out.println("beep beep Car");
    }


}
