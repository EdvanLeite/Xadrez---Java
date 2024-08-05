package BoardLayer;

public class Board {

	
	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	public Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	
	
	public Piece  piece(int rows, int columns) {
		return  pieces[rows][columns];
	}
	
	public Piece  piece(position pos ) {
		return  pieces[pos.getRow()][pos.getColumn()];
	}
	
	
	
	
	public void PlacePiece (Piece piece, position position) {
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
		
	}
	
}
