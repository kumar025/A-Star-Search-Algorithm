package astar.caller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import astar.bean.AStarSearch;
import astar.bean.Tile;

public class PathReader {

	public static void main(String[] args) {
		try {
			File file = new File("resources/large_map.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = null;
			int y = 0;
			Tile source = null, goal = null;
			Map<String ,Tile> theMap = new HashMap<String, Tile>();
			List<String> straightLinePaths = new ArrayList<String>();
			while ((line = bufferedReader.readLine()) != null) {
				line = line.trim();
				straightLinePaths.add(line);
				char[] chars = line.toCharArray();
				for(int x = 0; x < chars.length ; x++){
					if(chars[x] == '~'){
						continue;
					}
					Tile tile = new Tile(x, y, chars[x]);
					if(chars[x] == '@'){
						source = tile;
					}
					else if(chars[x] == 'X'){
						goal = tile;
					}
					theMap.put(x+","+y, tile);
				}
				y++;
			}
			fileReader.close();
			
			if(source == null || goal == null){
				System.out.println("Please provide start and goal tile.");
				return;
			}
			
			Tile theTile = AStarSearch.findPath(source, goal, theMap);
			while(theTile != null){
				int lineNo = theTile.getY();
				char[] chars = straightLinePaths.get(lineNo).toCharArray();
				chars[theTile.getX()] = '#';
				straightLinePaths.set(lineNo, String.valueOf(chars));
				theTile = theTile.getParent();
			}
			
			for(String path : straightLinePaths){
				System.out.println(path);
			}
			
			
		} catch (IOException e) {
			System.out.println("An IOException occured while searching goal. Message:"+e.getMessage());
		}
		catch (Exception e) {
			System.out.println("An exception occured while searching goal. Message:"+e.getMessage());
		}


	}

}
