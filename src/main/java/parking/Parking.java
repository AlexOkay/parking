package parking;

import exceptions.OutOfParkPlacesException;
import transport.Car;
import transport.Truck;
import transport.Vehicle;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;


public class Parking
{

    private int placesCount = 0;
    private int currentIter = 0;
    private int counter     = 1;
    private  Map<String,Vehicle> carSet = null;


    public Parking(int placesCount)
    {
        this.placesCount = placesCount;
        carSet = new ConcurrentHashMap<>(placesCount);
    }

    public void inGate() {

    }


     public boolean addVehicle(String key,Vehicle vehicle) {

             if (validateAddVehicle(vehicle)) {
                 carSet.put(key, vehicle);
                 currentIter = currentIter + counter;
                 return true;
             }
             return false;

    }


     public boolean removeVehicle(String number) {
        System.out.println("removeVehicle " + number);

            if (carSet.containsKey(number)) {
                Iterator<Map.Entry<String, Vehicle>> it = carSet.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, Vehicle> entry = it.next();
                    if (number.equals(entry.getKey())) {

                        validateCounter(entry.getValue());
                        it.remove();

                        System.out.println("Vehicle with number: " + number + " removed done.");
                        currentIter = currentIter - counter;
                        System.out.println("Количество после удаления : " + carSet.size() + " значение counter: " + currentIter);
                    }
                }
            } else {
                return false;
            }
            return true;

    }


    public TreeMap<ParkingManager.VehicleRegistrator,Vehicle> getAllVehicle() {
        return new TreeMap(carSet);
    }


    public int getMaxPlaces() {
        return placesCount;
    }


    public int getCurrentVehicleCount() {
        return currentIter;
    }


    private boolean validateAddVehicle(Vehicle vehicle)
    {
        if(vehicle == null) throw new IllegalArgumentException("vehicle cannot be null");
        if(carSet.containsKey(vehicle.getVehicleNumber())) throw new IllegalArgumentException("vehicle with the same number already parked");
        if(currentIter == placesCount) return false;
        if(placesCount < currentIter + validateCounter(vehicle))  return false;
        return true;
    }


    private int validateCounter(Vehicle vehicle)
    {
        if(vehicle instanceof Car)   { counter = 1; return counter;}
        if(vehicle instanceof Truck) { counter = 2; return counter;}
        return 0;
    }

}
