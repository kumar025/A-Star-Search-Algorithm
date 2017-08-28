package astar.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import astar.model.TileComparator;
import astar.utils.TileUtils;

public class AStarSearch {
	
	private static TileComparator nodeComparator = getNodeComparator();
	private static TileUtils tileUtils = getTileUtils();
	
	private static TileComparator getNodeComparator() {
		if(nodeComparator == null){
			nodeComparator = new TileComparator();
		}
		return nodeComparator;
	}
	
	private static TileUtils getTileUtils() {
		if(tileUtils == null){
			tileUtils = new TileUtils();
		}
		return tileUtils;
	}

	
	public static Tile findPath(Tile source, Tile goal, Map<String, Tile> theMap) {
		List<Tile> closedList = new ArrayList<Tile>();
		List<Tile> openList = new ArrayList<Tile>();
		source.setParent(null);
		closedList.add(source);
		while(source != goal){
			source = findNextStep(source, goal, theMap, openList, closedList);
		}
		return source;
	}

	
	private static Tile findNextStep(Tile currentTile, Tile goal, Map<String, Tile> theMap
			, List<Tile> openList, List<Tile> closedList){
		
		List<Tile> potentialTileList = getPotentialTiles(currentTile, goal, theMap, openList, closedList);
		while(potentialTileList.isEmpty() && currentTile.getParent() != null){
			currentTile = currentTile.getParent();
			potentialTileList = getPotentialTiles(currentTile, goal, theMap, openList, closedList);
		}

		openList.addAll(potentialTileList);
		if(openList.contains(goal)){
			currentTile = goal;
		}else{
			currentTile = getMostEfficientTile(openList, currentTile);
		}
		
		closedList.add(currentTile);	
		
		return currentTile; 
	}
	
	private static Tile getMostEfficientTile(List<Tile> openList, Tile start) {
		Tile bestTile = Collections.min(openList, nodeComparator);
		openList.remove(bestTile);

		if(bestTile.getParent() == start || (bestTile.getParent() == start.getParent())){
			return bestTile;
		}
		
		return getMostEfficientTile(openList, start);
	}

	private static List<Tile> getPotentialTiles(Tile currentTile, Tile goal, Map<String, Tile> theMap
			, List<Tile> openList, List<Tile> closedList) {
		int x = currentTile.getX();
		int y = currentTile.getY();
		List<Tile> surroundingTiles = tileUtils.getSurroundingTiles(theMap, x, y);
		
		List<Tile> potentialNodes = new ArrayList<Tile>();
		for(Tile tile : surroundingTiles){
			if(closedList.contains(tile))
				continue;
			if(!openList.contains(tile)){
				tile.setParent(currentTile);
				tile.setG(currentTile.getG());
				tile.setH(goal.getX(), goal.getY());
				tile.setF();
				potentialNodes.add(tile);
			}else{
				if((currentTile.getG() + tile.getCost()) < (tile.getG())){
					tile.setParent(currentTile);
					tile.setG(currentTile.getG());
					tile.setF();
				}else{
					Tile parent = currentTile.getParent();
					if( (Math.abs(parent.getX() - tile.getX()) < 2 && Math.abs(parent.getY() - tile.getY()) < 2) 
							&& ((parent.getG()+ tile.getCost()) < tile.getG() )){
						tile.setParent(parent);
						tile.setG(parent.getG());
						tile.setF();
					}else{
						tile.setParent(currentTile);
						tile.setG(currentTile.getG());
						tile.setF();
						potentialNodes.add(tile);
					}
				}
			}
		}	
		return potentialNodes;
	}


}
