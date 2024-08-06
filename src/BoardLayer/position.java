package BoardLayer;

public class position {
	
	private Integer row;
	

	public position(Integer row, Integer column) {
		super();
		this.row = row;
		this.column = column;
	}
	
	public Integer getRow() {
		return row;
	}


	public Integer getColumn() {
		return column;
	}

	private Integer column;
	
	
	public void setValues(Integer row, Integer column) {
		this.row = row;
		this.column = column;
	}

	@Override
	public String toString() {
		return "position: " + row + ", " + column ;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public void setColumn(Integer column) {
		this.column = column;
	}
	
	

	
	
}
