package parking;

import transport.Vehicle;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ParkingManager extends Thread
{

    private Parking park;
    private Queue<Vehicle> vehicleQueue  = null;
    private Map<String,Integer> registry = null;
    private boolean isDepartWork = true;
    private boolean isArriveWork = true;
    private Thread  thread = null;


    public ParkingManager()
    {
        park         = new Parking(5);
        vehicleQueue = new ConcurrentLinkedQueue<>();
        registry     = new ConcurrentHashMap<>(5);
    }


     public void arrive(Vehicle vehicle)
    {
        vehicleQueue.add(vehicle);
        System.out.println(vehicle.getVehicleNumber() + " arrive;");
        if(!this.isAlive())  this.start();
    }

     public boolean inGate(Vehicle vehicle)
    {
        int time = getTime();
        System.out.println(vehicle.getVehicleNumber() + " inGate;  current time = " + time);
        VehicleRegistrator registrator = new VehicleRegistrator(time,getDepartTime(),vehicle);
        if(park.addVehicle(vehicle.getVehicleNumber(),vehicle))
        {
                registry.put(registrator.vehicle.getVehicleNumber(), time);
                return true;
        }
        else
            return false;
    }

    @Override
    public void run() {
        try
        {
                while(isArriveWork) {
                    TimeUnit.SECONDS.sleep(1);
                    if(vehicleQueue != null && vehicleQueue.size() > 0)
                    {
                        if(inGate(vehicleQueue.peek()))
                        {
                            vehicleQueue.remove();
                        }
                        if(thread == null || !thread.isAlive()) departThread().start();
                    } else isArriveWork = false;
                }
        }
        catch(InterruptedException e) {
        }
    }


    public Thread departThread()
    {

        Runnable r = () -> {
            try
            {
            while(isDepartWork)
            {
                TimeUnit.SECONDS.sleep(1);
                if(!(park.getCurrentVehicleCount() > 0) && !(vehicleQueue.size() > 0))
                {
                    isDepartWork = false;
                    System.out.println(park.getCurrentVehicleCount() + "  count cars");
                }
                else
                {
                        if (registry != null && registry.entrySet().size() > 0)
                            for (Map.Entry<String, Integer> ks : registry.entrySet()) {
                                int time = ks.getValue();
                                if (time == getTime()) {
                                    park.removeVehicle(ks.getKey());
                                    registry.entrySet().remove(ks);
                                }
                            }
                }
            }
            }
            catch(InterruptedException e) {
            }
        };

        thread = new Thread(r);
        return thread;
    }

    public void managerDown()
    {
        isDepartWork = false;
        isArriveWork = false;
    }

    synchronized public int getTime()
    {
        String millis = String.valueOf(System.currentTimeMillis());
        return Integer.parseInt(millis.substring(9,10));
    }
    public int getDepartTime()
    {
        return ((int)(Math.random() * 10));
    }
    public int getCurrentVehicleCount() {
        return park.getCurrentVehicleCount();
    }


    public class VehicleRegistrator implements Comparable<VehicleRegistrator>
    {
        private int     arriveTime = 0;
        private int     departTime = 0;
        private Vehicle vehicle    = null;


        public VehicleRegistrator(int arriveTime, int departTime,Vehicle vehicle)
        {

            this.arriveTime = arriveTime;
            this.departTime = departTime;
            this.vehicle = vehicle;
        }


        public Vehicle getVehicle() {
            return vehicle;
        }

        public void setVehicle(Vehicle vehicle) {
            this.vehicle = vehicle;
        }

        public int getArriveTime() {
            return arriveTime;
        }

        public int getDepartTime() {
            return departTime;
        }

        private int tarifCalculate(int hours)
        {
            return hours*2;
        }


        @Override
        public int compareTo(VehicleRegistrator o) {
            return Integer.compare(this.departTime, o.departTime);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof VehicleRegistrator)) return false;
            VehicleRegistrator that = (VehicleRegistrator) o;
            return getArriveTime() == that.getArriveTime() &&
                    getDepartTime() == that.getDepartTime();
        }

        @Override
        public int hashCode() {
            return Objects.hash(getArriveTime(), getDepartTime());
        }
    }
}
