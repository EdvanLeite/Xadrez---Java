package BoardLayer;

import javax.swing.text.Position;

public class Board {

	
	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	public Board(int rows, int columns) {
		if (rows<1 || columns < 1) {
			throw new BordException("Erro Criando Tabuleiro. Eh necessatio que tenha pelo menos 1 linha e 1 coluna");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	
	
	public Piece  piece(int rows, int columns) {
		if(!positionExists(rows,columns)) {
			throw new BordException ("Possicao Inexistente");
		}
		return  pieces[rows][columns];
	}
	
	public Piece  piece(position pos ) {
		if(!positionExists(pos)) {
			throw new BordException ("Possicao Inexistente");
		}
		return  pieces[pos.getRow()][pos.getColumn()];
	}
	
	public void PlacePiece (Piece piece, position position) {
		if(TheresIsAPiece(position)) {
			throw new BordException ("Possicao Ja Ocupada");
		}
		
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
		
	}
	
	
	public boolean positionExists (int row, int column) {
		return row >=0 && row < rows    &&  column >= 0 && column < columns;
		
	}
	
	public boolean positionExists (position position) {
		return positionExists(position.getRow(), position.getColumn());
	}
	
	
	
	public boolean TheresIsAPiece(position position) {
		if(!positionExists(position)) {
			throw new BordException ("Possicao Inexistente");
		}
		
		return piece(position) != null;
				
	}
}
