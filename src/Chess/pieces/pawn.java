package Chess.pieces;

import BoardLayer.Board;
import BoardLayer.position;
import Chess.ChessMatch;
import Chess.ChessPiece;
import Chess.Color;

public class pawn extends ChessPiece{
	
	private ChessMatch chessMatch;

	public pawn(Board board, Color color,ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat =  new boolean[getBoard().getRows()][getBoard().getColumns()];
		position p = new position(0,0);
		
		if(getColor()==Color.WHITE) {
			p.setValues(position.getRow()-1, position.getColumn());
			if(getBoard().positionExists(p) && !getBoard().TheresIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow()-2, position.getColumn());
			position p2 = new position(position.getRow()-1, position.getColumn());
			if(getBoard().positionExists(p) && !getBoard().TheresIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().TheresIsAPiece(p2) && getMovecount()==0) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow()-1, position.getColumn()-1);
			if(getBoard().positionExists(p) && isthereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow()-1, position.getColumn()+1);
			if(getBoard().positionExists(p) && isthereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			
			if(position.getRow() == 3) {
				 position esquerda = new position (position.getRow(), position.getColumn()-1);
				 if(getBoard().positionExists(esquerda) && isthereOpponentPiece(esquerda) && getBoard().piece(esquerda) == chessMatch.getEnPassantVulnerable() ) {
					 mat[esquerda.getRow()-1][esquerda.getColumn()] = true;
				 }
				 
				 position direita = new position (position.getRow(), position.getColumn()+1);
				 if(getBoard().positionExists(direita) && isthereOpponentPiece(direita) && getBoard().piece(direita) == chessMatch.getEnPassantVulnerable() ) {
					 mat[direita.getRow()-1][direita.getColumn()] = true;
				 }
			}
			
			
		}else {
			
			p.setValues(position.getRow()+1, position.getColumn());
			if(getBoard().positionExists(p) && !getBoard().TheresIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow()+2, position.getColumn());
			position p2 = new position(position.getRow()+1, position.getColumn());
			if(getBoard().positionExists(p) && !getBoard().TheresIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().TheresIsAPiece(p2) && getMovecount()==0) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow()+1, position.getColumn()-1);
			if(getBoard().positionExists(p) && isthereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow()+1, position.getColumn()+1);
			if(getBoard().positionExists(p) && isthereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			if(position.getRow() == 4) {
				 position esquerda = new position (position.getRow(), position.getColumn()-1);
				 if(getBoard().positionExists(esquerda) && isthereOpponentPiece(esquerda) && getBoard().piece(esquerda) == chessMatch.getEnPassantVulnerable() ) {
					 mat[esquerda.getRow()+1][esquerda.getColumn()] = true;
				 }
				 
				 position direita = new position (position.getRow(), position.getColumn()+1);
				 if(getBoard().positionExists(direita) && isthereOpponentPiece(direita) && getBoard().piece(direita) == chessMatch.getEnPassantVulnerable() ) {
					 mat[direita.getRow()+1][direita.getColumn()] = true;
				 }
			}
			
			
		}
		
		return mat;
	}
	
	@Override
	public String toString() {
		return "P";
	}

	
}
