package Chess.pieces;

import BoardLayer.Board;
import BoardLayer.position;
import Chess.ChessPiece;
import Chess.Color;

public class knghits extends ChessPiece{

	public knghits(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "N";
	}
	
	private boolean canMove (position pos) {
		ChessPiece p = (ChessPiece)getBoard().piece(pos);
		return p == null || p.getColor() != getColor();
	}
	

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat =  new boolean[getBoard().getRows()][getBoard().getColumns()];
		position p = new position(0,0);
		
		

		p.setValues(position.getRow()-1, position.getColumn()-2);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		

	
		p.setValues(position.getRow()+1, position.getColumn()+2);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		p.setValues(position.getRow()-2, position.getColumn()-1);
		if(getBoard().positionExists(p) && canMove(p)) {
				mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		p.setValues(position.getRow()+2, position.getColumn()+1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
	
		p.setValues(position.getRow()-1, position.getColumn()+2);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
	
		p.setValues(position.getRow()+1, position.getColumn()-2);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
	
		p.setValues(position.getRow()+2, position.getColumn()-1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
	
		p.setValues(position.getRow()-2, position.getColumn()+1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
				
		return mat;
	}
	
}
