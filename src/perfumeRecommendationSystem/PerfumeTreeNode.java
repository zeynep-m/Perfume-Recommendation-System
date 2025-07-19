package perfumeRecommendationSystem;

//Node class for Binary Search Tree (BST)
public class PerfumeTreeNode {
 Perfume data;
 PerfumeTreeNode left, right;

 public PerfumeTreeNode(Perfume data) {
     this.data = data;
     this.left = this.right = null;
 	}
}