package za.ac.cput.Repository;

import za.ac.cput.Domain.Rsvp;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of IRsvpRepository using an in-memory list.
 */
public class RsvpRepository implements IRsvpRepository {
    private final List<Rsvp> rsvpList = new ArrayList<>();
    private static RsvpRepository repository = null;

    // Private constructor for Singleton pattern
    private RsvpRepository() {}

    /**
     * Returns the singleton instance of RsvpRepository.
     *
     * @return the singleton instance.
     */
    public static RsvpRepository getInstance() {
        if (repository == null) {
            repository = new RsvpRepository();
        }
        return repository;
    }

    @Override
    public Rsvp create(Rsvp rsvp) {
        if (rsvp != null) {
            rsvpList.add(rsvp);
            return rsvp;
        }
        return null;
    }

    @Override
    public Rsvp read(String rsvpID) {
        return rsvpList.stream()
                .filter(r -> r.getRsvpID().equals(rsvpID))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Rsvp update(Rsvp updatedRsvp) {
        if (updatedRsvp != null) {
            Rsvp existingRsvp = read(updatedRsvp.getRsvpID());
            if (existingRsvp != null) {
                rsvpList.remove(existingRsvp);
                rsvpList.add(updatedRsvp);
                return updatedRsvp;
            }
        }
        return null;
    }

    @Override
    public boolean delete(String rsvpID) {
        Rsvp rsvp = read(rsvpID);
        if (rsvp != null) {
            rsvpList.remove(rsvp);
            return true;
        }
        return false;
    }

    @Override
    public List<Rsvp> getAll() {
        return new ArrayList<>(rsvpList); // Return a copy to protect data integrity
    }
}
