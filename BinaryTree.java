public class BinaryTree {
    TreeNode root;

    public BinaryTree() {
        this.root = null;
    }

    // Вставка как в BST — просто чтобы удобно было строить дерево
    public void insert(int value) {
        root = insertRec(root, value);
    }

    private TreeNode insertRec(TreeNode node, int value) {
        if (node == null) {
            return new TreeNode(value);
        }
        if (value < node.value) {
            node.left = insertRec(node.left, value);
        } else if (value > node.value) {
            node.right = insertRec(node.right, value);
        }
        return node;
    }

    

    public void printTree() {
        if (root == null) {
            System.out.println("(дерево пустое)");
            return;
        }
        printTreeRec(root, 0);
    }

    private void printTreeRec(TreeNode node, int level) {
        if (node == null) return;

        // Сначала правый ребёнок (он окажется выше)
        printTreeRec(node.right, level + 1);

        // Отступ по уровню + сам узел
        for (int i = 0; i < level; i++) {
            System.out.print("    ");
        }
        // Помечаем листья скобками — так виднее, где конец ветви
        boolean isLeaf = (node.left == null && node.right == null);
        if (isLeaf) {
            System.out.println("[" + node.value + "]");
        } else {
            System.out.println(" " + node.value);
        }

        // Потом левый ребёнок (он окажется ниже)
        printTreeRec(node.left, level + 1);
    }

    // =========================================================
    //  ПОИСК ВЕТВЕЙ С МАКСИМАЛЬНЫМ ЧИСЛОМ ВЕТВЛЕНИЙ
    //  (без изменений)
    // =========================================================

    public BranchInfo[] findBranchesWithMaxBranchings() {
        int totalNodes = countNodes(root);
        if (totalNodes == 0) {
            return new BranchInfo[0];
        }

        int[] pathBuffer = new int[totalNodes];

        BranchInfo[] best = new BranchInfo[totalNodes];
        int[] bestCount = new int[1];
        int[] maxBranchings = new int[1];
        maxBranchings[0] = -1;

        collectBest(root, pathBuffer, 0, 0,
                    best, bestCount, maxBranchings);

        BranchInfo[] result = new BranchInfo[bestCount[0]];
        for (int i = 0; i < bestCount[0]; i++) {
            result[i] = best[i];
        }
        return result;
    }

    private void collectBest(TreeNode node,
                             int[] pathBuffer,
                             int depth,
                             int branchings,
                             BranchInfo[] best,
                             int[] bestCount,
                             int[] maxBranchings) {

        if (node == null) return;

        pathBuffer[depth] = node.value;

        boolean isBranching = (node.left != null && node.right != null);

        if (node.left == null && node.right == null) {
            int totalBranchings = branchings + (isBranching ? 1 : 0);

            if (totalBranchings > maxBranchings[0]) {
                maxBranchings[0] = totalBranchings;
                bestCount[0] = 0;
            }
            if (totalBranchings == maxBranchings[0]) {
                int[] copy = new int[depth + 1];
                for (int i = 0; i <= depth; i++) {
                    copy[i] = pathBuffer[i];
                }
                best[bestCount[0]] = new BranchInfo(copy, depth + 1, totalBranchings);
                bestCount[0]++;
            }
            return;
        }

        int nextBranchings = branchings + (isBranching ? 1 : 0);

        if (node.left != null) {
            collectBest(node.left, pathBuffer, depth + 1,
                        nextBranchings, best, bestCount, maxBranchings);
        }
        if (node.right != null) {
            collectBest(node.right, pathBuffer, depth + 1,
                        nextBranchings, best, bestCount, maxBranchings);
        }
    }

    private int countNodes(TreeNode node) {
        if (node == null) return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }
}
