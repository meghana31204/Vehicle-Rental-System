import java.util.*;

public class Vehicle_Rental {
    public static void main(String[] args) {
        Scanner o = new Scanner(System.in);
        VehicleService service = new VehicleService();

        while (true) {
            System.out.println("\nHello, Welcome to Ayodhya Vehicle Rental Services ");
            System.out.println("------------------------------------------------");
            System.out.println("1. Add Vehicle");
            System.out.println("2. Rent a Vehicle");
            System.out.println("3. End Rental");
            System.out.println("4. View Active Rentals");
            System.out.println("6. Exit");

            System.out.print("Enter your option: ");
            int option = o.nextInt();

            switch (option) {
                case 1:
                    service.addVehicle();
                    break;

                case 2:
                    service.rentVehicle();
                    break;

                case 3:
                    service.endRental();
                    break;

                case 4:
                    service.viewVehicles();
                    break;

                case 5:
                    System.out.println("Thank you! Visit again");
                    System.exit(0);

                default:
                    System.out.println("Invalid option");
            }
        }
    }
}