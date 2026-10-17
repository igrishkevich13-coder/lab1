public class ElementList {
    private Element[] data;
    private int size;

    public ElementList() {
        data = new Element[8];
        size = 0;
    }

    public void add(Element e) {
        if (size == data.length) {
            grow();
        }
        data[size++] = e;
    }

    private void grow() {
        Element[] bigger = new Element[data.length * 2];
        for (int i = 0; i < size; i++) {
            bigger[i] = data[i];
        }
        data = bigger;
    }

    public int size() {
        return size;
    }

    public Element get(int index) {
        return data[index];
    }
}
