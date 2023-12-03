package types;


/**
 * An implementation of the types.Symbol interface
 * @author PCO Team
 */
public enum Squares implements Filling {
	YELLOW("🟨"),
	BROWN("🟫"),
	RED("🟥"),
	BLACK("⬛"),
	ORANGE("🟧"),
	GREEN("🟩"),
	PURPLE("🟪");

	
	
	private String rep;
	Squares(String s) {
		this.rep = s;
	}

	public String toString() {
		return this.rep;
	}

	public Squares[] fillings() {
		return Squares.values();
	}

}
