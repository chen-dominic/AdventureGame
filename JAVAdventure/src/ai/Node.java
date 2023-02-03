package ai;

public class Node {
	
	Node parent;
	public int col;
	public int row;
	public int gCost;
	public int hCost;
	public int fCost;
	public boolean solid;
	public boolean open;
	public boolean checked;
	
	public Node(int col, int row) {
		this.col = col;
		this.row = row;
	}

}
