public class RankNode {
    int leftSize;
    RankNode left;
    RankNode right;
    int data;

    public RankNode(int data) {
        this.data = data;
    }

    public void insert(int d) {
        if (d <= data) {
            if (left == null) {
                left = new RankNode(d);
            } else {
                left.insert(d);
            }
            leftSize++;
        } else {
            if (right == null) {
                right = new RankNode(d);
            } else {
                right.insert(d);
            }
        }
    }

    public int getRank(int d) {
        if (d == data) {
            return leftSize;
        }
        if (d < data) {
            return (left == null ? -1 : left.getRank(d));
        } else {
            return (right == null ? -1 : (right.getRank(d) + 1 + leftSize));
        }
    }
    
    /*Delete a node from a bst (https://www.geeksforgeeks.org/binary-search-tree-set-2-delete/)
    1) Node to be deleted is leaf: Simply remove from the tree.
    2. Node to be deleted has only one child: Copy the child to the node and delete the child
    3. Node to be deleted has two children: 
       Find inorder successor of the node. Copy contents of the inorder 
       successor to the node and delete the inorder successor. Note that inorder predecessor can also be used.
       
       
    */
}
