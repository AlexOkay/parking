package transport;

public interface Vehicle<T extends Vehicle>
{
    T  getVehicle(String number);
    String getVehicleNumber();
    void setVehicleNumber(String num);
    int getSize();
    void beep();
}
