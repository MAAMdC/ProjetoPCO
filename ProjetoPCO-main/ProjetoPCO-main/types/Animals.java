package types;


/**
 * An implementation of the types.Symbol interface
 * @author PCO Team
 */
public enum Animals implements Filling {
	DRAGON("🐲"),
	WHALE("🐳"), 
	HORSE("🐴"), 
	MONKEY("🐒"), 
	PIG("🐷"), 
	FROG("🐸"),
	BEE("🐝"),
	ANT("🐜"),
	TURTLE("🐢"),
	SQUIRREL("🐿"),
	DOLPHIN("🐬"),
	FISH("🐠");
	
	private String rep;
	Animals(String s) {
		this.rep = s;
	}

	public String toString() {
		return this.rep;
	}

	public Animals[] fillings() {
		return Animals.values();
	}
}
