package Chess;

import BoardLayer.Board;
import BoardLayer.Piece;
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
	
	
	
	public boolean[][] possubleMoves(ChessPosition sourcePosition){
		position pos = sourcePosition.position();
		validateSourcePosition(pos);
		return board.piece(pos).possibleMoves();
	} 
	
	
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		position source = sourcePosition.position();
		position target = targetPosition.position();
		validateSourcePosition (source);
		validadeTargetPosition (source,target);
		Piece capturedPiece = makeMove(source,target);
		return (ChessPiece)capturedPiece;
	} 
	  
	private void validateSourcePosition(position position) {
		if(!board.TheresIsAPiece(position)) {
			throw new ChessException("Nao existe peça na posiçao de Origem ");
		}
		if(!board.piece(position).isThereanyPossibleMove()) {
			throw new ChessException("Nao existe movimentos possiveis");
		}
		
	}
	
	private void validadeTargetPosition(position source, position target) {
		if(!board.piece(source).possibleMove(target)) {
			throw new ChessException ("Posicao de destino nao eh valida!");
		}
	}
	
	
	private Piece makeMove (position source, position target) {
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.PlacePiece(p, target); 
		return capturedPiece;
		
		
	}
	
	
	

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.PlacePiece(piece, new ChessPosition (column,row).position());
	}
	
	
	private void initialSetup() {
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
		
	}
	
	
}
