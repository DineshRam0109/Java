import java.util.*;

class ParticipantManagement {
    private String id;
    private String name;
    private String neighborhood;

    public Participant(String id, String name, String neighborhood) {
        this.id = id;
        this.name = name;
        this.neighborhood = neighborhood;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Neighborhood: " + neighborhood;
    }
}

public class WorkshopRegistration {
    private Map<String, LinkedList<Participant>> neighborhoodMap = new HashMap<>();
    private Set<String> registeredIds = new HashSet<>();

    public void registerParticipant(String id, String name, String neighborhood) {
        if (registeredIds.contains(id)) {
            System.out.println("Participant ID already exists: " + id);
            return;
        }

        LinkedList<Participant> participants = neighborhoodMap.getOrDefault(neighborhood, new LinkedList<>());
        if (participants.size() >= 10) {
            System.out.println("Neighborhood " + neighborhood + " has reached the maximum number of participants.");
            return;
        }

        Participant participant = new Participant(id, name, neighborhood);
        participants.add(participant);
        neighborhoodMap.put(neighborhood, participants);
        registeredIds.add(id);
        System.out.println("Participant registered successfully: " + participant);
    }

    public void displayRegistrations() {
        System.out.println("Registered Participants by Neighborhood:");
        for (Map.Entry<String, LinkedList<Participant>> entry : neighborhoodMap.entrySet()) {
            System.out.println("Neighborhood: " + entry.getKey());
            for (Participant participant : entry.getValue()) {
                System.out.println("  " + participant);
            }
        }
    }

    public static void main(String[] args) {
        WorkshopRegistration registration = new WorkshopRegistration();
        registration.registerParticipant("001", "Alice", "Downtown");
        registration.registerParticipant("002", "Bob", "Uptown");
        registration.registerParticipant("003", "Charlie", "Downtown");
        registration.registerParticipant("001", "David", "Midtown");
        registration.registerParticipant("004", "Eve", "Downtown");
        registration.registerParticipant("005", "Frank", "Downtown");
        registration.registerParticipant("006", "Grace", "Downtown");
        registration.registerParticipant("007", "Heidi", "Downtown");
        registration.registerParticipant("008", "Ivan", "Downtown");
        registration.registerParticipant("009", "Judy", "Downtown");
        registration.registerParticipant("010", "Mallory", "Downtown");
        registration.registerParticipant("011", "Niaj", "Downtown"); 

        registration.displayRegistrations();
    }
}