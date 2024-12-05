import java.util.*;

class ReservationSystem {
    String id;
    String name;
    boolean isVIP;

    public Reservation(String id, String name, boolean isVIP) {
        this.id = id;
        this.name = name;
        this.isVIP = isVIP;
    }

    @Override
    public String toString() {
        return "Reservation[ID=" + id + ", Name=" + name + ", VIP=" + isVIP + "]";
    }
}

class ReservationPriorityComparator implements Comparator<Reservation> {
    public int compare(Reservation r1, Reservation r2) {
        return Boolean.compare(r2.isVIP, r1.isVIP); 
    }
}

public class RestaurantReservationSystem {
     PriorityQueue<Reservation> reservationQueue=new PriorityQueue<>(new ReservationPriorityComparator());

    public void addReservation(String id, String name, boolean isVIP) {
        Reservation reservation = new Reservation(id, name, isVIP);
        reservationQueue.offer(reservation);
        System.out.println("Added reservation: " + reservation);
    }

    public void processNextReservation() {
        Reservation nextReservation = reservationQueue.poll();
        if (nextReservation != null) {
            System.out.println("Processing reservation: " + nextReservation);
        } else {
            System.out.println("No reservations to process.");
        }
    }

    public static void main(String[] args) {
        RestaurantReservationSystem rrs = new RestaurantReservationSystem();
        rrs.addReservation("R001", "John", true);
        rrs.addReservation("R002", "Alice", false);
        rrs.addReservation("R003", "Bob", true);
        rrs.addReservation("R004", "David", false);

        rrs.processNextReservation();
        rrs.processNextReservation();
        rrs.processNextReservation(); 
    }
}