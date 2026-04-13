import java.util.*;
import java.sql.*;

public class VehicleService {
    Scanner o = new Scanner(System.in);

    public void addVehicle() {
        try {
            Connection con = DBConnection.getConnection();

            System.out.println("Enter No.of Vehicles you want to add");
            int n = o.nextInt();

            for (int i = 0; i < n; i++) {
                System.out.println("Enter ID");
                int id = o.nextInt();
                o.nextLine();

                System.out.println("Enter Name of the Vehicle");
                String name = o.nextLine();

                System.out.println("Enter No.of Wheels");
                int wheels = o.nextInt();

                System.out.println("Enter No.of Passenger");
                int passenger = o.nextInt();

                System.out.println("Enter Vehicle Number");
                String vehicleNumber = o.next();

                System.out.println("Enter No.of Seats");
                int seats = o.nextInt();

                System.out.println("Enter Price Per Day");
                double price = o.nextDouble();

                String query = "INSERT INTO Vehicles VALUES (?,?,?,?,?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(query);

                ps.setInt(1, id);
                ps.setString(2, name);
                ps.setString(3, vehicleNumber);
                ps.setInt(4, seats);
                ps.setInt(5, wheels);
                ps.setInt(6, passenger);
                ps.setDouble(7, price);
                ps.setBoolean(8, true);

                ps.executeUpdate();
                System.out.println("Vehicle added!");
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewVehicles() {
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM Vehicles";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            System.out.println("\nAll Vehicles:");
            System.out.println("----------------------------------------------------------");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("ID") + " | " +
                                rs.getString("vehicle_name") + " | " +
                                rs.getString("vehicle_number") + " | " +
                                rs.getInt("no_of_wheels") + " wheels | " +
                                rs.getInt("no_of_seats") + " seats | ₹" +
                                rs.getDouble("price_per_day") + " | " +
                                (rs.getBoolean("is_available") ? "Available" : "Rented")
                );
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void rentVehicle() {
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM Vehicles WHERE is_available=true";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            System.out.println("Available Vehicles:");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("ID") + " | " +
                                rs.getString("vehicle_name") + " | ₹" +
                                rs.getDouble("price_per_day")
                );
            }

            System.out.println("Enter Vehicle ID:");
            int id = o.nextInt();

            System.out.println("Enter days:");
            int days = o.nextInt();

            String priceQuery = "SELECT price_per_day FROM Vehicles WHERE ID=?";
            PreparedStatement ps2 = con.prepareStatement(priceQuery);
            ps2.setInt(1, id);
            ResultSet rs2 = ps2.executeQuery();

            if (rs2.next()) {
                double price = rs2.getDouble("price_per_day");
                double total = price * days;

                String update = "UPDATE Vehicles SET is_available=false WHERE ID=?";
                PreparedStatement ps3 = con.prepareStatement(update);
                ps3.setInt(1, id);
                ps3.executeUpdate();

                System.out.println("Vehicle rented!");
                System.out.println("Total: ₹" + total);
            } else {
                System.out.println("Invalid ID");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void endRental() {
        try {
            Connection con = DBConnection.getConnection();

            System.out.println("Enter Vehicle ID:");
            int id = o.nextInt();

            String query = "UPDATE Vehicles SET is_available=true WHERE ID=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println(" Vehicle returned!");
            } else {
                System.out.println(" Invalid ID");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}