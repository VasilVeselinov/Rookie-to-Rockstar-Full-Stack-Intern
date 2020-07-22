package GreenVSRed;

public class Cell {
    private int colorAsInt;
    private boolean hasChange = false;

    public Cell(int colorAsInt) {
        this.colorAsInt = colorAsInt;
    }

    public int getColorAsInt() {
        return colorAsInt;
    }

    public void setColorAsInt(int colorAsInt) {
        this.colorAsInt = colorAsInt;
    }

    public boolean isHasChange() {
        return hasChange;
    }

    public void setHasChange(boolean hasChange) {
        this.hasChange = hasChange;
    }
}
