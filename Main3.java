public class Main {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        int[] values = {50, 30, 70, 20, 40, 60, 80, 10, 45, 75, 90, 46};
        for (int v : values) {
            tree.insert(v);
        }

        System.out.println("Построенное дерево (корень слева, листья в [скобках]):");
        tree.printTree();
        System.out.println();

        System.out.println("Ищем ветви с максимальным числом ветвлений...\n");

        BranchInfo[] result = tree.findBranchesWithMaxBranchings();

        if (result.length == 0) {
            System.out.println("Дерево пустое.");
            return;
        }

        System.out.println("Максимальное число ветвлений: " + result[0].branchings);
        System.out.println("Найденные ветви:");
        for (int i = 0; i < result.length; i++) {
            result[i].print();
        }
    }
}
