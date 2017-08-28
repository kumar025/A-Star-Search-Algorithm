package astar.bean;

import astar.model.ITile;


public class Tile implements ITile{
	
	private int x;
	private int y;
	private int cost;
	private Character tileType;
	private int h;
	private int g;//movement cost
	private int f;
	private Tile parent;
	
	public Tile(int x, int y, char tileType) {
		this.x = x;
		this.y = y;
		this.tileType = tileType;
		this.cost = getCost();
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getCost() {
		switch (tileType) {
		case '~':
			cost = -1;break;
		case '*':
			cost = 2;break;
		case '^':
			cost = 3;break;
		default:
			cost = 1;break;
		}
		return cost;
	}
	
	public Character getTileType() {
		return tileType;
	}
	

	public Tile getParent() {
		return parent;
	}

	public void setParent(ITile parent) {
		this.parent = (Tile) parent;
	}

	public double getH() {
		return h;
	}

	public void setH(int x, int y) {
		h = Math.abs(this.x - x)+ Math.abs(this.y - y);
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g + cost;
	}

	public int getF() {
		return f;
	}

	public void setF() {
		this.f = this.g + this.h;
	}
	
	public boolean equals(Object o){
		if(o.getClass() != Tile.class)
			return false;
		Tile node = (Tile)o;
		return this.x == node.x && this.y == node.y;
	}



	
}

