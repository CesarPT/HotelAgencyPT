package Classes;

public class objparking {
    public String GroupId;
    public String ParkingSpot;
    public double Price;
    public boolean Indoor;
    public String IndoorString;
    public boolean Occupied;
    public String OccupiedString;

    public objparking(String parkingSpot, double price, boolean indoor, boolean occupied) {
        this.ParkingSpot = parkingSpot;
        this.Price = price;
        this.Indoor = indoor;
        this.IndoorString = indoor ? "Indoor" : "Outdoor";
        this.Occupied = occupied;
        this.OccupiedString = occupied ? "Sim" : "Não";
    }

    public String getGroupId() {
        return GroupId;
    }

    public double getPrice() {
        return Price;
    }

    public String getParkingSpot() {
        return ParkingSpot;
    }

    public String getIndoorString() {
        return IndoorString;
    }
    public String getOccupiedString() {
        return OccupiedString;
    }

    public void setGroupId(String groupId) {
        GroupId = groupId;
    }

    public void setParkingSpot(String parkingSpot) {
        ParkingSpot = parkingSpot;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public void setIndoor(boolean indoor) {
        Indoor = indoor;
    }

    public void setOccupied(boolean occupied) {
        Occupied = occupied;
    }
}
