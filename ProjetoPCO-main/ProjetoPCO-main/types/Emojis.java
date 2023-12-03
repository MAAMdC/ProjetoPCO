package types;


/**
 * An implementation of the types.Symbol interface
 * @author PCO Team
 */
public enum Emojis implements Filling {
	SMILE("😃"),
	SAD("😒"),
	FURIOUS("😡"),
	ANGEL("😇"),
	BLIINK("😉"),
	EVIL("😈"),
	SUN("😎"),
	LOVE("😍");
	
	
	private String rep;
	Emojis(String s) {
		this.rep = s;
	}

	public String toString() {
		return this.rep;
	}

	public Emojis[] fillings() {
		return Emojis.values();
	}
}
