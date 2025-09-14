import java.util.Scanner;

class InvalidRideTypeException extends Exception {
    public InvalidRideTypeException(String message) {
        super(message);
    }
}

abstract class Ride {
    private String driverName;
    private String vehicleNumber;
    private double distance;

    public Ride(String driverName, String vehicleNumber, double distance) {
        this.driverName = driverName;
        this.vehicleNumber = vehicleNumber;
        this.distance = distance;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        if (distance > 0)
            this.distance = distance;
        else
            throw new IllegalArgumentException("Distance must be positive.");
    }

    public abstract double calculateFare();
}

class BikeRide extends Ride {
    public BikeRide(String driverName, String vehicleNumber, double distance) {
        super(driverName, vehicleNumber, distance);
    }

    public double calculateFare() {
        return getDistance() * 10;
    }
}

class CarRide extends Ride {
    public CarRide(String driverName, String vehicleNumber, double distance) {
        super(driverName, vehicleNumber, distance);
    }

    public double calculateFare() {
        return getDistance() * 20;
    }
}

public class RideSharingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            String rideType = sc.nextLine().trim().toLowerCase();
            double distance = Double.parseDouble(sc.nextLine().trim());

            if (distance <= 0) {
                System.out.println("Distance must be greater than 0.");
                return;
            }

            Ride ride;
            switch (rideType) {
                case "bike":
                    ride = new BikeRide("Alice", "BIKE123", distance);
                    break;
                case "car":
                    ride = new CarRide("Bob", "CAR456", distance);
                    break;
                default:
                    throw new InvalidRideTypeException("Invalid ride type");
            }

            System.out.println("Driver: " + ride.getDriverName());
            System.out.println("Vehicle No: " + ride.getVehicleNumber());
            System.out.println("Distance: " + ride.getDistance() + " km");
            System.out.println("Fare: " + ride.calculateFare());

        } catch (InvalidRideTypeException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid distance input.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            sc.close();
        }
    }
}
