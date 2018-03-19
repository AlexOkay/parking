package transport;


import java.util.Objects;

public class Car  extends VehicleA
{
    private String type  = null;
    private String model = null;
    private int    size  = 1;

    //blah blah property



    public Car(String number) {
        super(number);
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
