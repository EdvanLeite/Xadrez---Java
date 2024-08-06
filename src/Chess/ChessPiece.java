package Chess;

import BoardLayer.Board;
import BoardLayer.Piece;
import BoardLayer.position;

public abstract class ChessPiece extends Piece {
	
	private Color color;

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	
	protected boolean isthereOpponentPiece(position position) {
		ChessPiece p =(ChessPiece)getBoard().piece(position);
		return p != null && p.getColor() != color;
	}
	
}
