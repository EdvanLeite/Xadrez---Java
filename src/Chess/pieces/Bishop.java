package Chess.pieces;

import BoardLayer.Board;
import BoardLayer.position;
import Chess.ChessPiece;
import Chess.Color;

public class Bishop extends ChessPiece {

	
	
	
	public Bishop(Board board, Color color) {
		super(board, color);
		
	}


	@Override
	public String toString() {
		return "B";
	}


	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat =  new boolean[getBoard().getRows()][getBoard().getColumns()];
		position p = new position(0,0);
		
		
		// Noroeste
		p.setValues(position.getRow() -1 , position.getColumn()-1);
		while(getBoard().positionExists(p) && !getBoard().TheresIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow()-1, p.getColumn()-1);
		}
		
		if (getBoard().positionExists(p) && isthereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		
		// Nordeste
		p.setValues(position.getRow() -1 , position.getColumn()+1);
		while(getBoard().positionExists(p) && !getBoard().TheresIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow()-1, p.getColumn()+1);
		}
				
		if (getBoard().positionExists(p) && isthereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		// Sudeste
		p.setValues(position.getRow() +1, position.getColumn()+1);
		while(getBoard().positionExists(p) && !getBoard().TheresIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow()+1, p.getColumn()+1);
		}
				
		if (getBoard().positionExists(p) && isthereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;		
		}
		
		
		
		// sudoeste
		p.setValues(position.getRow()+1 , position.getColumn()-1);
		while(getBoard().positionExists(p) && !getBoard().TheresIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow()+1, p.getColumn()-1);
		}
						
		if (getBoard().positionExists(p) && isthereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;		
		}
		
		return mat;
	}

}

