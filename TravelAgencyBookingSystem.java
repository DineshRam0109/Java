import java.util.*;

class Trip {
    private String tripId;
    private String destination;
    private int duration; // in days
    private double price;
    private List<String> travelerIds;

    public Trip(String a, String b, int c, double d) {
        tripId = a;
        destination = b;
        duration = c;
        price = d;
        travelerIds = new ArrayList<>();
    }

    public String getTripId() {
        return tripId;
    }

    public String getDestination() {
        return destination;
    }

    public int getDuration() {
        return duration;
    }

    public double getPrice() {
        return price;
    }

    public List<String> getTravelerIds() {
        return travelerIds;
    }

    public boolean addTraveler(String travelerId) {
        if (!travelerIds.contains(travelerId)) {
            travelerIds.add(travelerId);
            return true;
        }
        return false;
    }

    public boolean cancelBooking(String travelerId) {
        return travelerIds.remove(travelerId);
    }

    public String getBookingStatus() {
        if (travelerIds.isEmpty()) {
            return "No bookings yet.";
        } else {
            return travelerIds.size() + " traveler(s) have booked.";
        }
    }

    @Override
    public String toString() {
        return "Trip ID: " + tripId + ", Destination: " + destination +
               ", Duration: " + duration + " days, Price: $" + price +
               ", Travelers: " + travelerIds;
    }
}

class TravelAgency {
    private Map<String, Trip> trips;

    public TravelAgency() {
        trips = new HashMap<>();
    }

    public boolean addTrip(String tripId, String destination, int duration, double price) {
        if (!trips.containsKey(tripId)) {
            Trip trip = new Trip(tripId, destination, duration, price);
            trips.put(tripId, trip);
            return true;
        }
        return false;
    }

    public boolean enrollTraveler(String tripId, String travelerId) {
        Trip trip = trips.get(tripId);
        if (trip != null) {
            return trip.addTraveler(travelerId);
        }
        return false;
    }

    public boolean cancelBooking(String tripId, String travelerId) {
        Trip trip = trips.get(tripId);
        if (trip != null) {
            return trip.cancelBooking(travelerId);
        }
        return false;
    }

    public List<Trip> filterTripsByDestination(String destination) {
        List<Trip> result = new ArrayList<>();
        for (Trip trip : trips.values()) {
            if (trip.getDestination().equalsIgnoreCase(destination)) {
                result.add(trip);
            }
        }
        return result;
    }

    public List<String> getAllTripsWithStatus() {
        List<String> result = new ArrayList<>();
        for (Trip trip : trips.values()) {
            result.add(trip.toString() + " - Booking Status: " + trip.getBookingStatus());
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TravelAgency agency = new TravelAgency();

        while (true) {
            System.out.println("\n===========================================");
            System.out.println(" Travel Agency Booking System ");
            System.out.println("===========================================");
            System.out.println(" 1. Add New Trip");
            System.out.println(" 2. Enroll Traveler");
            System.out.println(" 3. Cancel Traveler Booking");
            System.out.println(" 4. Filter Trips by Destination");
            System.out.println(" 5. View All Trips with Booking Status");
            System.out.println(" 6. Exit");
            System.out.println("===========================================");
            System.out.print("Choose an option: ");
            int option = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    System.out.println("\n--- Add New Trip ---");
                    System.out.print("Enter Trip ID: ");
                    String tripId = sc.nextLine();
                    System.out.print("Enter Destination: ");
                    String destination = sc.nextLine();
                    System.out.print("Enter Duration (in days): ");
                    int duration = sc.nextInt();
                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();
                    sc.nextLine(); // Consume newline
                    boolean added = agency.addTrip(tripId, destination, duration, price);
                    if (added) {
                        System.out.println("Trip added successfully!");
                    } else {
                        System.out.println("Trip with the same ID already exists.");
                    }
                    break;

                case 2:
                    System.out.println("\n--- Enroll Traveler ---");
                    System.out.print("Enter Trip ID: ");
                    tripId = sc.nextLine();
                    System.out.print("Enter Traveler ID: ");
                    String travelerId = sc.nextLine();
                    boolean enrolled = agency.enrollTraveler(tripId, travelerId);
                    if (enrolled) {
                        System.out.println("Traveler enrolled successfully!");
                    } else {
                        System.out.println("Enrollment failed. Either the trip doesn't exist or the traveler is already enrolled.");
                    }
                    break;

                case 3:
                    System.out.println("\n--- Cancel Traveler Booking ---");
                    System.out.print("Enter Trip ID: ");
                    tripId = sc.nextLine();
                    System.out.print("Enter Traveler ID: ");
                    travelerId = sc.nextLine();
                    boolean canceled = agency.cancelBooking(tripId, travelerId);
                    if (canceled) {
                        System.out.println("Booking canceled successfully!");
                    } else {
                        System.out.println("Cancellation failed. Either the trip or the traveler doesn't exist.");
                    }
                    break;

                case 4:
                    System.out.println("\n--- Filter Trips by Destination ---");
                    System.out.print("Enter Destination: ");
                    destination = sc.nextLine();
                    List<Trip> filteredTrips = agency.filterTripsByDestination(destination);
                    if (filteredTrips.isEmpty()) {
                        System.out.println("No trips available to " + destination);
                    } else {
                        System.out.println("Trips to " + destination + ":");
                        for (Trip trip : filteredTrips) {
                            System.out.println(trip);
                        }
                    }
                    break;

                case 5:
                    System.out.println("\n--- All Trips with Booking Status ---");
                    List<String> allTrips = agency.getAllTripsWithStatus();
                    if (allTrips.isEmpty()) {
                        System.out.println("No trips available.");
                    } else {
                        for (String tripInfo : allTrips) {
                            System.out.println(tripInfo);
                        }
                    }
                    break;

                case 6:
                    System.out.println("Exiting the system... Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }
}
