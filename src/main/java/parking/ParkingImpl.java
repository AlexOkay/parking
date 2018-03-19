package parking;

import exceptions.OutOfParkPlacesException;
import transport.Car;
import transport.Truck;
import transport.Vehicle;


import java.util.HashMap;


public class ParkingImpl implements Parking
{

    private int placesCount        = 0;
    private int currentIter = 0;
    private int counter     = 1;
    private HashMap<String,Vehicle> carSet = null;


    public ParkingImpl(int placesCount)
    {
        this.placesCount = placesCount;
        carSet = new HashMap<>(placesCount);
    }
    public ParkingImpl()
    {
        carSet = new HashMap<>();
    }

    @Override
    public boolean addVehicle(Vehicle vehicle) {

        validateAddVehicle(vehicle);
        carSet.put(vehicle.getVehicleNumber(),vehicle);
        currentIter = currentIter + counter;
        return true;
    }

    @Override
    public boolean removeVehicle(String number) {
        if(carSet.containsKey(number))  {carSet.remove(number); System.out.println("Vehicle with number: " + number + " removed done.");}
        else throw new IllegalArgumentException("parking doesn't contain this vehicle");
        currentIter--;
        return true;
    }

    @Override
    public HashMap<String,Vehicle> getPark() {
        return carSet;
    }

    @Override
    public int getMaxPlaces() {
        return placesCount;
    }

    @Override
    public int getCurrentVehicleCount() {
        return currentIter;
    }


    private void validateAddVehicle(Vehicle vehicle)
    {
        if(vehicle == null) throw new IllegalArgumentException("vehicle cannot be null");
        if(carSet.containsKey(vehicle.getVehicleNumber())) throw new IllegalArgumentException("vehicle with the same number already parked");
       // if(currentIter == size) throw new OutOfParkPlacesException("parking is full");
       // if(size < currentIter + validateCounter(vehicle))  throw new OutOfParkPlacesException("parking is full");
    }

    /*
    private int validateCounter(Vehicle vehicle)
    {
        if(vehicle instanceof Car)   { counter = 1; return counter;}
        if(vehicle instanceof Truck) { counter = 2; return counter;}
        return 0;
    }
    */
}
