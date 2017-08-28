package astar.model;

public interface ITile {
	
	public int getX();
	public int getY();
	public int getCost() ;
	public Character getTileType();
	public ITile getParent() ;
	public void setParent(ITile parent);
	public double getH();
	public void setH(int x, int y);
	public int getG();
	public void setG(int g);
	public int getF();
	public void setF();
	
}
