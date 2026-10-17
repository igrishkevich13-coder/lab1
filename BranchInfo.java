
public class BranchInfo {
    int[] branch;
    int branchLength;
    int branchings;

    public BranchInfo(int[] branch, int branchLength, int branchings) {
        this.branch = branch;
        this.branchLength = branchLength;
        this.branchings = branchings;
    }

    public void print() {
        System.out.print("[");
        for (int i = 0; i < branchLength; i++) {
            System.out.print(branch[i]);
            if (i < branchLength - 1) System.out.print(" -> ");
        }
        System.out.println("]  ветвлений: " + branchings);
    }
}
