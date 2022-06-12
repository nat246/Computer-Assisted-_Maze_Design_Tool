package events;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

/**
 * Responsible for adding / updating the status of the cells in a maze
 */
public class WallsEvent implements Serializable{
    private final List<CellListener> cellListener;

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
