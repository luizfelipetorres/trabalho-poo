package business;

import java.util.Objects;

public class Piece {
	private Position expected;
	private Position current;

	public Piece(Position esperada, Position atual) {
		super();
		this.expected = esperada;
		this.current = atual;
	}
	
	

	public Position getExpected() {
		return expected;
	}



	public void setExpected(Position expected) {
		this.expected = expected;
	}



	public Position getCurrent() {
		return current;
	}



	public void setCurrent(Position current) {
		this.current = current;
	}



	@Override
	public int hashCode() {
		return Objects.hash(current, expected);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Piece other = (Piece) obj;
		return Objects.equals(current, other.current) && Objects.equals(expected, other.expected);
	}
}
