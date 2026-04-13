public class Vehicle {
    int id;
    String name;
    String vehicleNumber;
    int noOfWheels;
    int noOfSeats;
    int passenger;
    double pricePerDay;
    boolean isAvailable;

    public Vehicle(int id, String name, String vehicleNumber, int noOfWheels, int noOfSeats, int passenger, double pricePerDay, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.vehicleNumber = vehicleNumber;
        this.noOfWheels = noOfWheels;
        this.noOfSeats = noOfSeats;
        this.passenger=passenger;
        this.pricePerDay = pricePerDay;
        this.isAvailable = true;
    }
    @Override
    public String toString() {
        return id + " | " + name + " | " + vehicleNumber + " | " +
                noOfWheels + " wheels | " + noOfSeats + " seats | ₹" +
                pricePerDay + " | " + (isAvailable ? "Available" : "Rented") + "\n";
    }
}