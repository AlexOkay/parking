package parking;

import transport.Vehicle;

import java.util.HashMap;


public interface Parking
{
    boolean addVehicle(Vehicle vehicle);
    boolean removeVehicle(String number);
    HashMap<String,Vehicle> getPark();
    int  getMaxPlaces();
    int  getCurrentVehicleCount();
}
