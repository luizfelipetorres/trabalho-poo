package business;

import java.util.Objects;

public class Position {
	private int column;
	private int line;
	
	public Position(int line, int column) {
		super();
		this.column = column;
		this.line = line;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	@Override
	public int hashCode() {
		return Objects.hash(column, line);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		return column == other.column && line == other.line;
	}
}
