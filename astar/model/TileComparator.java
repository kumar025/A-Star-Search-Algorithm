package astar.model;

import java.util.Comparator;

public class TileComparator implements Comparator<ITile> {
	public int compare(ITile a, ITile b) {
		if (a.getF() == b.getF()) {
			return (int) (a.getG() - b.getG());
		} else {
			return (int) (a.getF() - b.getF());
		}
	}
}
