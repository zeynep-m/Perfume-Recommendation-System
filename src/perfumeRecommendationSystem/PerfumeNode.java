package perfumeRecommendationSystem;

public class PerfumeNode {
    Perfume perfume;
    PerfumeNode next;

    public PerfumeNode(Perfume perfume) {
        this.perfume = perfume;
        this.next = null;
    }
}
