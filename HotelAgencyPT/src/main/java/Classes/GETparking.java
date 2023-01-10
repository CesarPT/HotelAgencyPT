package Classes;

import java.util.List;

public class GETparking {
    public String Status;
    public List<objparking> Parking;

    public GETparking(String parkingSpot, double price, boolean indoor, boolean occupied) {

    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public List<objparking> getParking() {
        return Parking;
    }

    public void setParking(List<objparking> parking) {
        Parking = parking;
    }
}

