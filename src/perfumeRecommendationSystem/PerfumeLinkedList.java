package perfumeRecommendationSystem;
import java.util.*;

public class PerfumeLinkedList {
    PerfumeNode head;

    public void add(Perfume perfume) {
        PerfumeNode newNode = new PerfumeNode(perfume);
        if (head == null) {
            head = newNode;
        } else {
            PerfumeNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public List<Perfume> getAllPerfumes() {
        List<Perfume> perfumes = new ArrayList<>();
        PerfumeNode current = head;
        while (current != null) {
            perfumes.add(current.perfume);
            current = current.next;
        }
        return perfumes;
    }

    public List<Perfume> getByBasicPreferences(String gender, String intensity, String longevity) {
        List<Perfume> results = new ArrayList<>();
        PerfumeNode current = head;
        while (current != null) {
            Perfume p = current.perfume;
            if (p.getGender().equalsIgnoreCase(gender) &&
                p.getIntensity().equalsIgnoreCase(intensity) &&
                p.getLongevity().equalsIgnoreCase(longevity)) {
                results.add(p);
            }
            current = current.next;
        }
        return results;
    }

    public List<Perfume> search(String gender, String intensity, String longevity, String[] top, String[] middle, String[] base) {
        List<Perfume> results = new ArrayList<>();
        PerfumeNode current = head;

        while (current != null) {
            Perfume p = current.perfume;
            if (p.getGender().equalsIgnoreCase(gender) &&
                p.getIntensity().equalsIgnoreCase(intensity) &&
                p.getLongevity().equalsIgnoreCase(longevity) &&
                matches(p.getTopNotes(), top) &&
                matches(p.getMiddleNotes(), middle) &&
                matches(p.getBaseNotes(), base)) {
                results.add(p);
            }
            current = current.next;
        }

        // Print results
        if (results.isEmpty()) {
            System.out.println("Sorry, no perfumes match all your preferences.");
        } else {
            System.out.println("Here are your perfect matches:");
            for (Perfume p : results) {
                p.printDetails();
                System.out.println("-----------------------------");
            }
        }

        return results;
   }
    private boolean matches(String[] perfumeNotes, String[] selectedNotes) {
        if (selectedNotes.length == 0) return true;
        
        for (String note : selectedNotes) {
            for (String pNote : perfumeNotes) {
                if (pNote.equalsIgnoreCase(note.trim())) return true;
            }
        }
        return false;
    }
    
    private boolean matches(String[] perfumeNotes, String[] selectedNotes, boolean matchAllNotes) {
        if (selectedNotes.length == 0) return true;
        
        if (matchAllNotes) {
            for (String note : selectedNotes) {
                boolean foundMatch = false;
                for (String pNote : perfumeNotes) {
                    if (pNote.equalsIgnoreCase(note.trim())) {
                        foundMatch = true;
                        break;
                    }
                }
                if (!foundMatch) return false;
            }
            return true;
        } else {
            for (String note : selectedNotes) {
                for (String pNote : perfumeNotes) {
                    if (pNote.equalsIgnoreCase(note.trim())) return true;
                }
            }
            return false;
        }
    }
    public List<Perfume> getClosestMatches(String gender, String intensity, String longevity) {
        List<Perfume> matches = new ArrayList<>();

        for (Perfume p : getAllPerfumes()) {
            int matchScore = 0;

            if (p.getGender().equalsIgnoreCase(gender)) matchScore++;
            if (p.getIntensity().equalsIgnoreCase(intensity)) matchScore++;
            if (p.getLongevity().equalsIgnoreCase(longevity)) matchScore++;

            if (matchScore >= 2) { // At least 2 out of 3 preferences match
                matches.add(p);
            }
        }

        return matches;
    }
    public List<Perfume> filterByNotes(List<Perfume> perfumes, String[] top, String[] middle, String[] base) {
        return filterByNotes(perfumes, top, middle, base, false);
    }
    
    public List<Perfume> filterByNotes(List<Perfume> perfumes, String[] top, String[] middle, String[] base, boolean matchAllNotes) {
        List<Perfume> matches = new ArrayList<>();
        for (Perfume p : perfumes) {
            boolean topMatch = matches(p.getTopNotes(), top, matchAllNotes);
            boolean middleMatch = matches(p.getMiddleNotes(), middle, matchAllNotes);
            boolean baseMatch = matches(p.getBaseNotes(), base, matchAllNotes);

            if (topMatch && middleMatch && baseMatch) {
                matches.add(p);
            }
        }
        return matches;
    }
}
