package Chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import BoardLayer.Board;
import BoardLayer.Piece;
import BoardLayer.position;
import Chess.pieces.King;
import Chess.pieces.Rook;

public class ChessMatch {
	
	private Board board;
	private int turn;
	private Color colorPlayer ;
	private boolean check;
	
	private List <Piece> piecesOnTheBoard = new ArrayList<>();
	private List <Piece> capturedPieces = new ArrayList<>();

	
	
	public boolean getCheck() {
		return check;
	}
	
	public int getTurn() {
		return turn;
	}

	public Color getColorPlayer() {
		return colorPlayer;
	}


	public ChessMatch() {
		board = new Board(8,8);
		turn =1;
		colorPlayer = Color.WHITE;
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
		if(testCheck(colorPlayer)) {
			undoMove(source,target,capturedPiece);
			throw new ChessException("voce nao pode se colocar em check");
		}
		
		check = (testCheck(opponent(colorPlayer))) ? true : false;
		
		nextTurn();
		return (ChessPiece)capturedPiece;
	} 
	  
	private void validateSourcePosition(position position) {
		if(!board.TheresIsAPiece(position)) {
			throw new ChessException("Nao existe peça na posiçao de Origem ");
		}
		if(!board.piece(position).isThereanyPossibleMove()) {
			throw new ChessException("Nao existe movimentos possiveis");
		}
		if(colorPlayer != (((ChessPiece) board.piece(position)).getColor())) {
			throw new ChessException("Essa peça nao eh sua");
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
		
		if(capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		return capturedPiece;
	}
	
	
	private void undoMove(position source, position target, Piece capturedPiece) {
		Piece p = board.removePiece(target);
		board.PlacePiece(p, source);
		
		if(capturedPiece != null) {
			board.PlacePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}
	}
	

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.PlacePiece(piece, new ChessPosition (column,row).position());
		piecesOnTheBoard.add(piece);
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
	
	
	
	private void nextTurn() {
		turn ++;
		colorPlayer = (colorPlayer == Color.WHITE) ?Color.BLACK : Color.WHITE;
	}
	
	
	
	private Color opponent (Color color) {
		return (color == color.WHITE) ? color.BLACK : color.WHITE;
	}
	
	
	private ChessPiece king(Color color) {
		List <Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor()==color).collect(Collectors.toList());
		for(Piece p : list) {
			if(p instanceof King) {
				return (ChessPiece) p;
			}
		}
		
		throw new IllegalStateException("Nao Existe Rei de Tal Cor");
	}
	
	private boolean testCheck(Color color) {
		position kingPosition = king(color).getChessPosition().position();
		List <Piece> opponenPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor()==opponent(color)).collect(Collectors.toList());
		for(Piece p : opponenPieces) {
			boolean[][] mat = p.possibleMoves();
			if(mat[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;
			}
		}
		return false;
	}
	
	
	
	
}
