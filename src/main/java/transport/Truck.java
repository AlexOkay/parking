package transport;

public class Truck extends VehicleA
{
    private String type     = null;
    private String model    = null;
    final private int coef  = 2;
    final private int size        = 2;
    //blah blah property

    public Truck(String number) {
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
        return null;
    }

    @Override
    public void beep() {
        System.out.println("beep beep Truck");
    }
}
