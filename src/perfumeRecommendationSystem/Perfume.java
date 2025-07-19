package perfumeRecommendationSystem;

public class Perfume {
    String name;
    String gender;
    String[] topNotes;
    String[] middleNotes;
    String[] baseNotes;
    String intensity;
    String longevity;

    public Perfume(String name, String gender, String[] topNotes, String[] middleNotes, String[] baseNotes, String intensity, String longevity) {
        this.name = name;
        this.gender = gender;
        this.topNotes = topNotes;
        this.middleNotes = middleNotes;
        this.baseNotes = baseNotes;
        this.intensity = intensity;
        this.longevity = longevity;
    }

    public void printDetails() {
        System.out.println("Name: " + name);
        System.out.println("Top Notes: " + String.join(", ", topNotes));
        System.out.println("Middle Notes: " + String.join(", ", middleNotes));
        System.out.println("Base Notes: " + String.join(", ", baseNotes));
        System.out.println("Intensity: " + intensity);
        System.out.println("Longevity: " + longevity);
        System.out.println("-----------------------------");
    }

    public String[] getTopNotes() { return topNotes; }
    public String[] getMiddleNotes() { return middleNotes; }
    public String[] getBaseNotes() { return baseNotes; }
    public String getIntensity() { return intensity; }
    public String getLongevity() { return longevity; }
    public String getGender() { return gender; 
    }
    public String getName() {
        return name;
    }
}
