public class Main {
    public static void main(String[] args) {
        System.out.println("=== Тест 1: Простое умножение 2x3 * 3x2 ===");

        CSMatrix A = new CSMatrix(2, 3);
        A.addElement(0, 0, 1);
        A.addElement(0, 2, 2);
        A.addElement(1, 1, 3);
        A.addElement(1, 2, 4);

        CSMatrix B = new CSMatrix(3, 2);
        B.addElement(0, 0, 5);
        B.addElement(1, 1, 6);
        B.addElement(2, 0, 7);
        B.addElement(2, 1, 8);

        System.out.println("Матрица A:");
        A.print();

        System.out.println("Матрица B:");
        B.print();

        CRSMatrix C = SparseMatrixMultiplier.multiply(A, B);

        System.out.println("Результат (CRS):");
        C.print();

        System.out.println("\n=== Тест 2: Более разреженные матрицы 3x3 * 3x3 ===");

        CSMatrix A2 = new CSMatrix(3, 3);
        A2.addElement(0, 0, 1);
        A2.addElement(1, 2, 2);
        A2.addElement(2, 1, 3);

        CSMatrix B2 = new CSMatrix(3, 3);
        B2.addElement(0, 1, 4);
        B2.addElement(1, 0, 5);
        B2.addElement(2, 2, 6);

        System.out.println("Матрица A2:");
        A2.print();

        System.out.println("Матрица B2:");
        B2.print();

        CRSMatrix C2 = SparseMatrixMultiplier.multiply(A2, B2);

        System.out.println("Результат (CRS):");
        C2.print();
    }
}
