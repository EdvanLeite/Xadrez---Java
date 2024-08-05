package BoardLayer;

public class Piece {

	
	protected position position;
	private Board board;
	

	public Piece(Board board) {
		super();
		this.board = board;
		position = null;
	}

	protected Board getBoard() {
		return board;
	}

	
	
	
	
}
