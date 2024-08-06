package Chess.pieces;

import BoardLayer.Board;
import BoardLayer.position;
import Chess.ChessPiece;
import Chess.Color;

public class Rook extends ChessPiece {

	public Rook(Board board, Color color) {
		super(board, color);
	}
	
	
	@Override
	public String toString() {
		return "R";
	}


	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat =  new boolean[getBoard().getRows()][getBoard().getColumns()];
		position p = new position(0,0);
		
		
		// Cima
		p.setValues(position.getRow() -1 , position.getColumn());
		while(getBoard().positionExists(p) && !getBoard().TheresIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow()-1);
		}
		
		if (getBoard().positionExists(p) && isthereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		
		// Baixo
		p.setValues(position.getRow() +1 , position.getColumn());
		while(getBoard().positionExists(p) && !getBoard().TheresIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow()+1);
		}
				
		if (getBoard().positionExists(p) && isthereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		// Esquerda
		p.setValues(position.getRow() , position.getColumn()-1);
		while(getBoard().positionExists(p) && !getBoard().TheresIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn()-1);
		}
				
		if (getBoard().positionExists(p) && isthereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;		
		}
		
		
		
		// Direita
		p.setValues(position.getRow() , position.getColumn()+1);
		while(getBoard().positionExists(p) && !getBoard().TheresIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn()+1);
		}
						
		if (getBoard().positionExists(p) && isthereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;		
		}
		
		return mat;
	}

}
