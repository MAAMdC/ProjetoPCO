package types;


/**
 * An implementation of the types.Symbol interface
 * @author PCO Team
 */
public enum Balls implements Filling {
	BALL1("①"),
	BALL2("②"), 
	BALL3("③"), 
	BALL4("④"), 
	BALL5("⑤"), 
	BALL6("⑥"),
	BALL7("⑦"),
	BALL8("⑧"),
	BALL9("⑨");
	
	
	private String rep;
	Balls(String s) {
		this.rep = s;
	}

	public String toString() {
		return this.rep;
	}

	public Balls[] fillings() {
		return Balls.values();
	}
}