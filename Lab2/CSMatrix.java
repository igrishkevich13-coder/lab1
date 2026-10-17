public class CSMatrix {
    private int rows;
    private int cols;
    private ElementList elements;

    public CSMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.elements = new ElementList();
    }

    public void addElement(int row, int col, double value) {
        if (value != 0.0) {
            elements.add(new Element(row, col, value));
        }
    }

    public double get(int row, int col) {
        for (int i = 0; i < elements.size(); i++) {
            Element e = elements.get(i);
            if (e.row == row && e.col == col) {
                return e.value;
            }
        }
        return 0.0;
    }

    public int getRows() { return rows; }
    public int getCols() { return cols; }
    public ElementList getElements() { return elements; }

    public void print() {
        System.out.println("Размер: " + rows + "x" + cols);
        System.out.println("Элементы (row, col, value):");
        for (int i = 0; i < elements.size(); i++) {
            Element e = elements.get(i);
            System.out.println("  (" + e.row + ", " + e.col + ", " + formatDouble(e.value) + ")");
        }

        System.out.println("Матрица:");
        for (int i = 0; i < rows; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < cols; j++) {
                sb.append(padLeft(formatDouble(get(i, j)), 6));
                sb.append(' ');
            }
            System.out.println(sb.toString());
        }
        System.out.println();
    }

    
    public static String formatDouble(double v) {
        long sign = (v < 0) ? -1 : 1;
        double a = (v < 0) ? -v : v;
        long whole = (long) a;
        long frac = (long) (a * 10 + 0.5) % 10;
        if (whole == 0 && frac == 0) sign = 1;
        return (sign < 0 ? "-" : "") + whole + "." + frac;
    }

    
    public static String padLeft(String s, int width) {
        if (s.length() >= width) return s;
        StringBuilder sb = new StringBuilder();
        for (int i = s.length(); i < width; i++) sb.append(' ');
        sb.append(s);
        return sb.toString();
    }
  }
