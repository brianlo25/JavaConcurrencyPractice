package designpattern.chapter03;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class VehicleTracker {
    private Map<String, Location> locationMap = new ConcurrentHashMap<>();

    public void updateLocation(String vehicleId, Location newLocation){
        locationMap.put(vehicleId, newLocation);
    }
}
