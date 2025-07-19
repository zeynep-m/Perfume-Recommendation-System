package perfumeRecommendationSystem;

import java.util.ArrayList;
import java.util.List;

public class PerfumeBST {
    PerfumeTreeNode root;

    public void insert(Perfume perfume) {
        root = insertRec(root, perfume);
    }

    private PerfumeTreeNode insertRec(PerfumeTreeNode root, Perfume perfume) {
        if (root == null) {
            return new PerfumeTreeNode(perfume);
        }
        if (perfume.name.compareToIgnoreCase(root.data.name) < 0) {
            root.left = insertRec(root.left, perfume);
        } else {
            root.right = insertRec(root.right, perfume);
        }
        return root;
    }

    public void displayAlphabetically() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(PerfumeTreeNode node) {
        if (node != null) {
            inOrderTraversal(node.left);
            node.data.printDetails();
            inOrderTraversal(node.right);
        }
    }

    public Perfume searchByName(String name) {
        return searchByNameRec(root, name);
    }

    private Perfume searchByNameRec(PerfumeTreeNode node, String name) {
        if (node == null) return null;
        if (node.data.name.equalsIgnoreCase(name)) return node.data;
        if (name.compareToIgnoreCase(node.data.name) < 0)
            return searchByNameRec(node.left, name);
        else
            return searchByNameRec(node.right, name);
    }

    // NEW METHOD: returns perfumes sorted alphabetically
    public List<Perfume> getAlphabeticallySortedList() {
        List<Perfume> sortedList = new ArrayList<>();
        inOrderCollect(root, sortedList);
        return sortedList;
    }

    private void inOrderCollect(PerfumeTreeNode node, List<Perfume> result) {
        if (node != null) {
            inOrderCollect(node.left, result);
            result.add(node.data);
            inOrderCollect(node.right, result);
        }
    }
}