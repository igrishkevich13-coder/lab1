public class SparseMatrixMultiplier {

    static class RowAccumulator {
        private int[] cols;
        private double[] sums;
        private int size;

        RowAccumulator() {
            cols = new int[4];
            sums = new double[4];
            size = 0;
        }

        void add(int col, double value) {
            for (int i = 0; i < size; i++) {
                if (cols[i] == col) {
                    sums[i] += value;
                    return;
                }
            }
            if (size == cols.length) grow();
            cols[size] = col;
            sums[size] = value;
            size++;
        }

        private void grow() {
            int[] nc = new int[cols.length * 2];
            double[] ns = new double[sums.length * 2];
            for (int i = 0; i < size; i++) {
                nc[i] = cols[i];
                ns[i] = sums[i];
            }
            cols = nc;
            sums = ns;
        }
        void sortByCol() {
            for (int i = 1; i < size; i++) {
                int keyCol = cols[i];
                double keySum = sums[i];
                int j = i - 1;
                while (j >= 0 && cols[j] > keyCol) {
                    cols[j + 1] = cols[j];
                    sums[j + 1] = sums[j];
                    j--;
                }
                cols[j + 1] = keyCol;
                sums[j + 1] = keySum;
            }
        }

        int size() { return size; }
        int colAt(int i) { return cols[i]; }
        double sumAt(int i) { return sums[i]; }
    }

    public static CRSMatrix multiply(CSMatrix A, CSMatrix B) {
        if (A.getCols() != B.getRows()) {
            throw new IllegalArgumentException("Несовместимые размеры матриц для умножения");
        }

        int resultRows = A.getRows();
        int resultCols = B.getCols();

        
        RowAccumulator[] acc = new RowAccumulator[resultRows];
        for (int i = 0; i < resultRows; i++) {
            acc[i] = new RowAccumulator();
        }

        ElementList aElems = A.getElements();
        ElementList bElems = B.getElements();


        for (int i = 0; i < aElems.size(); i++) {
            Element a = aElems.get(i);
            for (int j = 0; j < bElems.size(); j++) {
                Element b = bElems.get(j);
                if (a.col == b.row) {
                    acc[a.row].add(b.col, a.value * b.value);
                }
            }
        }


    
        int total = 0;
        for (int i = 0; i < resultRows; i++) {
            acc[i].sortByCol();
            for (int k = 0; k < acc[i].size(); k++) {
                if (abs(acc[i].sumAt(k)) > 1e-10) total++;
            }
        }

        double[] values = new double[total];
        int[] colIndices = new int[total];
        int[] rowPointers = new int[resultRows + 1];

        int pos = 0;
        rowPointers[0] = 0;
        for (int i = 0; i < resultRows; i++) {
            for (int k = 0; k < acc[i].size(); k++) {
                double v = acc[i].sumAt(k);
                if (abs(v) > 1e-10) {
                    values[pos] = v;
                    colIndices[pos] = acc[i].colAt(k);
                    pos++;
                }
            }
            rowPointers[i + 1] = pos;
        }

        return new CRSMatrix(resultRows, resultCols, values, colIndices, rowPointers);
    }

    
    private static double abs(double v) {
        return (v < 0) ? -v : v;
    }
              }
