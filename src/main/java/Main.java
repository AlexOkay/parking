import com.mifmif.common.regex.Generex;

import parking.Parking;
import parking.ParkingManager;
import transport.Car;
import transport.Truck;
import transport.Vehicle;


import java.util.*;

public class Main
{

    public static void main(String[] args) {
        st();
    }



    private static void st()
    {
        ParkingManager parkingManager = new ParkingManager();
      //  Vehicle vehicle = null;
        List<String> vehicleNumbers = null;

        for(int i = 0; i< 10;i++)
        {
            Vehicle vehicle = null;
            String number = getSaltString();
            if(i % 2 == 0)  vehicle = new Car(number);
            else vehicle = new Truck(number);
            parkingManager.arrive(vehicle);

        }

    }


   /* private static void Iter1()
    {
        Parking parking = new Parking();
        Vehicle vehicle = null;
        List<String> vehicleNumbers = null;


        //делаем 10ть транспортных средств, записываем номера
        vehicleNumbers = new ArrayList<>();
        for(int i = 0; i< 10;i++)
        {
            String number = getSaltString();
            if(i % 2 == 0)  vehicle = new Car(number);
            else vehicle = new Truck(number);
            parking.addVehicle(vehicle);
            vehicleNumbers.add(number);
            System.out.println(number);
        }
        //пошумим
        parking.getAllVehicle().forEach((k,v) -> v.beep());

        System.out.println("На стоянке: " + parking.getCurrentVehicleCount() + " машин");
        //удалим все машины со стоянки
        for(String number : vehicleNumbers)
        {
            parking.removeVehicle(number);
            System.out.println("На стоянке: " + parking.getCurrentVehicleCount() + " машин");
        }
        //vehicleNumbers.stream().map(x -> parking.removeVehicle(x)).count();

        //все в норме, стоянка пуста
        System.out.println(parking.getAllVehicle().isEmpty());
    }*/

    //генератор случайных строк, из регулярки
    protected static String getSaltString() {
        Generex generex = new Generex("[А-Я][А-Я][0-9][0-9][0-9][А-Я]");
        String randomStr = generex.random();
        return randomStr;
    }

}
