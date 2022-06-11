package events;

import java.util.ArrayList;
import java.util.List;

public class WallsEvent {
    private boolean state;
    private List<CellListener> cellListener;

    public WallsEvent(boolean state) {
        this.state = state;
        this.cellListener = new ArrayList<CellListener>();
    }

    public void addCellListener(CellListener listener) {
        this.cellListener.add(listener);
    }

    public void setState(boolean state) {
        this.state = state;
        cellListener.forEach(CellListener::wallChanged);
    }
}
