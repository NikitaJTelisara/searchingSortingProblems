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
}
