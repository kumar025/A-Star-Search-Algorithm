package astar.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import astar.bean.Tile;

public class TileUtils {

	public List<Tile> getSurroundingTiles(Map<String, Tile> theMap, int x, int y) {
		List<Tile> surroundingTiles = new ArrayList<Tile>();
		if(theMap.containsKey((x-1)+","+(y-1))){
			surroundingTiles.add(theMap.get((x-1)+","+(y-1)));
		}
		if(theMap.containsKey((x-1)+","+(y))){
			surroundingTiles.add(theMap.get((x-1)+","+(y)));
		}
		if(theMap.containsKey((x-1)+","+(y+1))){
			surroundingTiles.add(theMap.get(x-1+","+(y+1)));
		}
		if(theMap.containsKey(x+","+(y-1))){
			surroundingTiles.add(theMap.get(x+","+(y-1)));
		}
		if(theMap.containsKey(x+","+(y+1))){
			surroundingTiles.add(theMap.get(x+","+(y+1)));
		}
		if(theMap.containsKey(x+1+","+(y-1))){
			surroundingTiles.add(theMap.get(x+1+","+(y-1)));
		}
		if(theMap.containsKey((x+1)+","+y)){
			surroundingTiles.add(theMap.get(x+1+","+y));
		}
		if(theMap.containsKey((x+1)+","+(y+1))){
			surroundingTiles.add(theMap.get((x+1)+","+(y+1)));
		}
		return surroundingTiles;
	}
	
	
	
	
}
