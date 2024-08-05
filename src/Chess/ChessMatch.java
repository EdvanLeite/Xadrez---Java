package Chess;

import BoardLayer.Board;
import BoardLayer.position;
import Chess.pieces.King;
import Chess.pieces.Rook;

public class ChessMatch {
	
	private Board board;

	public ChessMatch() {
		board = new Board(8,8);
		initialSetup();
	}
	

	public ChessPiece[][]getPieces(){
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for(int i=0;i<board.getRows();i++) {
			for(int j =0; j<board.getColumns();j++) {
				mat[i][j]= (ChessPiece) board.piece(i,j);
			}
		}
		
		return mat;
	}

	
	private void initialSetup() {
		board.PlacePiece(new Rook(board, Color.WHITE), new position (3,5));
		board.PlacePiece(new King(board, Color.WHITE), new position (0,4));
		board.PlacePiece(new King(board, Color.BLACK), new position (7,4));
		
	}
	
	
}
