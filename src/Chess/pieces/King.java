package Chess.pieces;

import BoardLayer.Board;
import BoardLayer.position;
import Chess.ChessMatch;
import Chess.ChessPiece;
import Chess.Color;

public class King extends ChessPiece{
	
	private ChessMatch chessMatch;

	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}
	
	@Override
	public String toString() {
		return "K";
	}
	
	private boolean canMove (position pos) {
		ChessPiece p = (ChessPiece)getBoard().piece(pos);
		return p == null || p.getColor() != getColor();
	}
	
	
	
	private boolean testRookCastling(position pos) {
		ChessPiece p = (ChessPiece)getBoard().piece(pos);
		return p != null && p instanceof Rook && p.getColor() ==getColor() && p.getMovecount() == 0;
	}
	

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat =  new boolean[getBoard().getRows()][getBoard().getColumns()];
		position p = new position(0,0);
		
		
		//Cima
		p.setValues(position.getRow()-1, position.getColumn());
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		

		//Baixo
		p.setValues(position.getRow()+1, position.getColumn());
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//esqueda
		p.setValues(position.getRow(), position.getColumn()-1);
		if(getBoard().positionExists(p) && canMove(p)) {
				mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Direita
		p.setValues(position.getRow(), position.getColumn()+1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		//nw
		p.setValues(position.getRow()-1, position.getColumn()-1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//ne
		p.setValues(position.getRow()-1, position.getColumn()+1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//sw
		p.setValues(position.getRow()+1, position.getColumn()-1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//se
		p.setValues(position.getRow()+1, position.getColumn()+1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		if( getMovecount()== 0 && !chessMatch.getCheck()) {
			
			position posT1 =  new position(position.getRow(),position.getColumn()+3);
			if(testRookCastling(posT1)) {
				position p1 =  new position(position.getRow(),position.getColumn()+1);
				position p2 =  new position(position.getRow(),position.getColumn()+2);
				if(getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
					mat[position.getRow()][position.getColumn()+2] = true;
				}
			}
			
		
			
			position posT2 =  new position(position.getRow(),position.getColumn()-4);
			if(testRookCastling(posT1)) {
				position p1 =  new position(position.getRow(),position.getColumn()-1);
				position p2 =  new position(position.getRow(),position.getColumn()-2);
				position p3 =  new position(position.getRow(),position.getColumn()-3);
				if(getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
					mat[position.getRow()][position.getColumn()+2] = true;
				}
			}
			
		}
		
		
		
		
				
		return mat;
	}
	
}
