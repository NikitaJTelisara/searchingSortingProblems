public class RankNode {
    RankNode left;
    RankNode right;
    int nextGreaterNo;
    int leftsize;
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
            leftsize++;
        } else {
            if (right == null) {
                right = new RankNode(d);
                nextGreaterNo = d;
            } else {
                nextGreaterNo = ((d < nextGreaterNo) ? d : nextGreaterNo);
                right.insert(d);
            }
        }
    }

    public int getRank(int d) {
        if (d == data) {
            return leftsize + 1;
        } else if (d < data) {
            return (left == null ? -1 : left.getRank(d));
        } else {
            return (right == null ? -1 : right.getRank(d) + leftsize + 1);
        }
    }

    public void print() {
        if (left != null) {
            left.print();
        }
        System.out.println(data);
        if (right != null) {
            right.print();
        }
    }


    /*Delete a node from a bst (https://www.geeksforgeeks.org/binary-search-tree-set-2-delete/)
    1) Node to be deleted is leaf: Simply remove from the tree.
    2. Node to be deleted has only one child: Copy the child to the node and delete the child
    3. Node to be deleted has two children:
       Find inorder successor of the node. Copy contents of the inorder
       successor to the node and delete the inorder successor. Note that inorder predecessor can also be used.

    */
    public RankNode remove(int d) {
        if (d == data) {
            if (isleaf()) {
                return null;
            } else if (left == null || right == null) {
                if (left == null) {
                    return right;
                } else if (right == null) {
                    return left;
                }
            } else if (left != null && right != null) {
                int n = getNextGreaterNo(nextGreaterNo);
                this.remove(nextGreaterNo);
                data = nextGreaterNo;
                nextGreaterNo = n;
                return this;
            }
        } else if (d < data) {
            left = (left == null ? null : left.remove(d));
        } else if (d > data) {
            right = (right == null ? null : right.remove(d));
        }
        return this;
    }

    public int getNextGreaterNo(int d){
        if(d== data){
            return nextGreaterNo;
        } if(d<data){
            return left.getNextGreaterNo(d);
        }  else{
            return right.getNextGreaterNo(d);
        }
    }

    public boolean isleaf(){
        return (left == null && right == null);
    }

    public boolean noleft(){
        return (left == null && right != null);
    }

    public boolean noright(){
        return (left == null && right != null);
    }

}



