package Chess;

import BoardLayer.Board;
import BoardLayer.Piece;
import BoardLayer.position;

public abstract class ChessPiece extends Piece {
	
	private Color color;
	private int moveCount;
	
	
	
	

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	public void increaseMoveCount() {
		moveCount++;
	}
	
	public void decreaseMovecount() {
		moveCount--;
	}
	
	public int getMovecount() {
		return moveCount;
	}

	
	protected boolean isthereOpponentPiece(position position) {
		ChessPiece p =(ChessPiece)getBoard().piece(position);
		return p != null && p.getColor() != color;
	}
	
	
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}
	
}
