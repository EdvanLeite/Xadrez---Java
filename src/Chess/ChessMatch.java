package Chess;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import BoardLayer.Board;
import BoardLayer.Piece;
import BoardLayer.position;
import Chess.pieces.Bishop;
import Chess.pieces.King;
import Chess.pieces.Queen;
import Chess.pieces.Rook;
import Chess.pieces.knghits;
import Chess.pieces.pawn;

public class ChessMatch {
	
	private Board board;
	private int turn;
	private Color colorPlayer ;
	private boolean check;
	private boolean checkMate;
	private ChessPiece enPassantVulnerable;
	private ChessPiece promoted;
	

	private List <Piece> piecesOnTheBoard = new ArrayList<>();
	private List <Piece> capturedPieces = new ArrayList<>();

	
	
	
	
	public boolean getCheckMate() {
		return checkMate;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public int getTurn() {
		return turn;
	}
	
	

	public Color getColorPlayer() {
		return colorPlayer;
	}
	
	
	public ChessPiece getpromoted() {
		return promoted;
	}

	public ChessPiece getEnPassantVulnerable() {
		return enPassantVulnerable;
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
		
		ChessPiece movedPiece = (ChessPiece) board.piece(target);
		
		promoted = null;
		
		if(movedPiece instanceof pawn) {
			if((movedPiece.getColor() == Color.WHITE && target.getRow() == 0) ||(movedPiece.getColor() == Color.BLACK && target.getRow() == 7)) {
				promoted = (ChessPiece)board.piece(target);
				promoted = replacePromotedPiece("Q");
				
			}
		}
		
		check = (testCheck(opponent(colorPlayer))) ? true : false;
		
		if(testCheckMate(opponent(colorPlayer))) {
			checkMate = true;
		}else {
			nextTurn();
		}
		
		
		if(movedPiece instanceof pawn && (target.getRow() == source.getRow()+2 ||target.getRow() == source.getRow()-2)) { 
			enPassantVulnerable = movedPiece;
		}else {
			enPassantVulnerable = null;
		}
		
		
		
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
	
	
	public ChessPiece replacePromotedPiece(String Type) {
		if(promoted == null) {
			throw new IllegalStateException("nao ha peça para ser promovida");
		}
		if(!Type.equals("B") && !Type.equals("N") &&!Type.equals("R") &&!Type.equals("Q")) {
			return promoted;
		}
		position pos = promoted.getChessPosition().position();
		Piece p = board.removePiece(pos);
		piecesOnTheBoard.remove(p);
		
		ChessPiece newPiece   = newPiece(Type, promoted.getColor());
			board.PlacePiece(newPiece, pos);
			piecesOnTheBoard.add(newPiece);
			
			return newPiece;
		}
		
	
	
	private ChessPiece newPiece(String type, Color color) {
		if(type.equals("B")) return new Bishop(board, color);
		if(type.equals("N")) return new knghits(board, color);
		if(type.equals("Q")) return new Queen(board, color);
		 return new Rook(board, color);
	}
	
	
	private Piece makeMove (position source, position target) {
		ChessPiece p = (ChessPiece)board.removePiece(source);
		p.increaseMoveCount();
		Piece capturedPiece = board.removePiece(target);
		board.PlacePiece(p, target); 
		
		if(capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		
		if (p instanceof King && target.getColumn() ==  source.getColumn()-2) {
			position sourceT =  new position(source.getRow(), source.getColumn()+3);	
			position targetT = new position(source.getRow(), source.getColumn()+1); 
			ChessPiece Rook = (ChessPiece)board.removePiece(sourceT);
			board.PlacePiece(Rook, targetT);
			Rook.increaseMoveCount();
		}
		
		if (p instanceof King && target.getColumn() ==  source.getColumn()-2) {
			position sourceT =  new position(source.getRow(), source.getColumn()-4);	
			position targetT = new position(source.getRow(), source.getColumn()-1); 
			ChessPiece Rook = (ChessPiece)board.removePiece(sourceT);
			board.PlacePiece(Rook, targetT);
			Rook.increaseMoveCount();
		}
		
		
		
		if(p instanceof pawn ) {
			if(source.getColumn() != target.getColumn() && capturedPiece == null) {
				position pawnPos;
				if(p.getColor() == Color.WHITE) {
					pawnPos =new position(target.getRow()+1,target.getColumn());
				}else {
					pawnPos =new position(target.getRow()-1,target.getColumn());
				}
				capturedPiece = board.removePiece(pawnPos);
				capturedPieces.add(capturedPiece);
				piecesOnTheBoard.remove(capturedPiece);
			}
		}
		
		
		return capturedPiece;
	}
	
	
	private void undoMove(position source, position target, Piece capturedPiece) {
		ChessPiece p = (ChessPiece)board.removePiece(target);
		p.decreaseMovecount();
		board.PlacePiece(p, source);
		
		if(capturedPiece != null) {
			board.PlacePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}
		
		
		if (p instanceof King && target.getColumn() ==  source.getColumn()-2) {
			position sourceT =  new position(source.getRow(), source.getColumn()+3);	
			position targetT = new position(source.getRow(), source.getColumn()+1); 
			ChessPiece Rook = (ChessPiece)board.removePiece(targetT);
			board.PlacePiece(Rook, sourceT);
			Rook.decreaseMovecount();
		}
		
		if (p instanceof King && target.getColumn() ==  source.getColumn()-2) {
			position sourceT =  new position(source.getRow(), source.getColumn()-4);	
			position targetT = new position(source.getRow(), source.getColumn()-1); 
			ChessPiece Rook = (ChessPiece)board.removePiece(targetT);
			board.PlacePiece(Rook, sourceT);
			Rook.decreaseMovecount();
		}
		
		if(p instanceof pawn ) {
			if(source.getColumn() != target.getColumn() && capturedPiece == enPassantVulnerable) {
				ChessPiece pawn = (ChessPiece)board.removePiece(target);
				position pawnPos;
				if(p.getColor() == Color.WHITE) {
					pawnPos =new position(3,target.getColumn());
				}else {
					pawnPos =new position(4,target.getColumn());
				}
				board.PlacePiece(pawn, pawnPos);
			}
		}
		
	}
	

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.PlacePiece(piece, new ChessPosition (column,row).position());
		piecesOnTheBoard.add(piece);
	}
	
	
	private void initialSetup() {
		placeNewPiece('a', 1, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE,this));
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));
        placeNewPiece('a', 2, new pawn(board, Color.WHITE,this));
        placeNewPiece('b', 2, new pawn(board, Color.WHITE,this));
        placeNewPiece('c', 2, new pawn(board, Color.WHITE,this));
        placeNewPiece('d', 2, new pawn(board, Color.WHITE,this));
        placeNewPiece('e', 2, new pawn(board, Color.WHITE,this));
        placeNewPiece('f', 2, new pawn(board, Color.WHITE,this));
        placeNewPiece('g', 2, new pawn(board, Color.WHITE,this));
        placeNewPiece('h', 2, new pawn(board, Color.WHITE,this));
        placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('b', 1, new knghits(board, Color.WHITE));
        placeNewPiece('g', 1, new knghits(board, Color.WHITE));
        placeNewPiece('d', 1, new Queen(board, Color.WHITE));
        

        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new King(board, Color.BLACK,this));
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));
        placeNewPiece('a', 7, new pawn(board, Color.BLACK,this));
        placeNewPiece('b', 7, new pawn(board, Color.BLACK,this));
        placeNewPiece('c', 7, new pawn(board, Color.BLACK,this));
        placeNewPiece('d', 7, new pawn(board, Color.BLACK,this));
        placeNewPiece('e', 7, new pawn(board, Color.BLACK,this));
        placeNewPiece('f', 7, new pawn(board, Color.BLACK,this));
        placeNewPiece('g', 7, new pawn(board, Color.BLACK,this));
        placeNewPiece('h', 7, new pawn(board, Color.BLACK,this));
        placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('b', 8, new knghits(board, Color.BLACK));
        placeNewPiece('g', 8, new knghits(board, Color.BLACK));
        placeNewPiece('d', 8, new Queen(board, Color.BLACK));
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
	
	
	private boolean testCheckMate(Color color) {
		if(!testCheck(color)) {
			return false;
		}
		List <Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor()==color).collect(Collectors.toList());
		for(Piece p : list) {
			boolean[][]mat = p.possibleMoves(); 
			for(int i=0;i<mat.length;i++) {
				for(int j=0;j<mat.length;j++) {
					if(mat[i][j]) {
						position source = ((ChessPiece)p).getChessPosition().position();
						position target = new position(i,j);
						Piece capturedPiece = makeMove(source, target);
						boolean testCheck = testCheck(color);
						undoMove(source,target,capturedPiece);
						if(!testCheck) {
							return false;
						}
					}
				}
			}
		}
		
		return true;
	}
	
	
	
	
}
