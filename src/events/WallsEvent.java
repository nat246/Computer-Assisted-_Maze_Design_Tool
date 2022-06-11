package events;

import java.util.ArrayList;
import java.util.List;

public class WallsEvent {
    private List<CellListener> cellListener;

    public WallsEvent() {
        this.cellListener = new ArrayList<CellListener>();
    }

    public void addCellListener(CellListener listener) {
        this.cellListener.add(listener);
    }

    public void update() {
        cellListener.forEach(CellListener::wallChanged);
    }
}
