public class CRSMatrix {
    private int rows;
    private int cols;
    private double[] values;
    private int[] colIndices;
    private int[] rowPointers;

    public CRSMatrix(int rows, int cols, double[] values, int[] colIndices, int[] rowPointers) {
        this.rows = rows;
        this.cols = cols;
        this.values = values;
        this.colIndices = colIndices;
        this.rowPointers = rowPointers;
    }

    public double getValue(int row, int col) {
        for (int i = rowPointers[row]; i < rowPointers[row + 1]; i++) {
            if (colIndices[i] == col) {
                return values[i];
            }
        }
        return 0.0;
    }

    public int getRows() { return rows; }
    public int getCols() { return cols; }
    public double[] getValues() { return values; }
    public int[] getColIndices() { return colIndices; }
    public int[] getRowPointers() { return rowPointers; }

    public void print() {
        System.out.println("CRS Matrix " + rows + "x" + cols + ":");

        StringBuilder sb = new StringBuilder();
        sb.append("Values: [");
        for (int i = 0; i < values.length; i++) {
            if (i > 0) sb.append(", ");
            sb.append(CSMatrix.formatDouble(values[i]));
        }
        sb.append("]");
        System.out.println(sb.toString());

        sb = new StringBuilder();
        sb.append("ColIndices: [");
        for (int i = 0; i < colIndices.length; i++) {
            if (i > 0) sb.append(", ");
            sb.append(colIndices[i]);
        }
        sb.append("]");
        System.out.println(sb.toString());

        sb = new StringBuilder();
        sb.append("RowPointers: [");
        for (int i = 0; i < rowPointers.length; i++) {
            if (i > 0) sb.append(", ");
            sb.append(rowPointers[i]);
        }
        sb.append("]");
        System.out.println(sb.toString());

        System.out.println();
        System.out.println("Matrix view:");
        for (int i = 0; i < rows; i++) {
            sb = new StringBuilder();
            for (int j = 0; j < cols; j++) {
                sb.append(CSMatrix.padLeft(CSMatrix.formatDouble(getValue(i, j)), 6));
                sb.append(' ');
            }
            System.out.println(sb.toString());
        }
    }
}
