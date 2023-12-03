package types;


//Notem que podem faltar métodos na classe que permitam lidar melhor com os objectos.
public class BettingFillingGame extends AbstractFillingGame{
	private int score;
    private int bet;
    private int maxPlays = 10;

	/**
	 * 
	 * @param symbols
	 * @param numberOfUsedSymbols
	 * @param seed
	 * @param bootleSize
	 * @param score
	 * @param bet
	 * @param maxPlays
	 */
	public BettingFillingGame(Filling[] symbols, int numberOfUsedSymbols, int seed, 
			int bottleSize, int score, int bet, int maxPlays) {
		
		super(symbols, numberOfUsedSymbols, seed, bottleSize);
        this.score = score;
        this.bet = bet;
        this.maxPlays = maxPlays;
	}

	/**
	 * 
	 */
	@Override
	public void provideHelp() {
	    // Reduz o número máximo de jogadas permitidas
	    maxPlays--;
	    Cup vazio = new Cup();
	    table.addBootle(vazio);
	}

	/**
	 * 
	 */
	@Override
	public int score() {
		return score;
	}

	/**
	 * 
	 */
	@Override
	public boolean isRoundFinished() {
	    if (maxPlays == 0 || jogadas() >= maxPlays || areAllFilled()) {
	        return true;
	    }
	    return false;
	}

	/**
	 * 
	 * @return
	 */
	public int numberOfPlaysLeft() {
	    return maxPlays - jogadas();
	}


	/**
	 * 
	 * @return
	 */
	@Override
	public Bottle getNewBootle() {
		Bottle newBottle = new Bottle(getBottlesSize());
		return newBottle;
	}

	/**
	 * 
	 * @return
	 */
	@Override
    public void updateScore() {
        if(isRoundFinished()) {
        	if(!areAllFilled())
        		if(score > 0) {
        			score -= 2 * bet;
        		}else {
        			score = 0;
        		}
        		
        	else
        		score += 2 * bet;
        }
    }
	
	@Override
   public String toString() {
		if(!isRoundFinished()) {
			return "Score: " + score() + EOL + super.toString() + "Status: " + jogadas() + " moves have been used until now. You still have " + numberOfPlaysLeft() + " moves left." + EOL; 
		}else {
			updateScore();
			return "Score: " + score() + EOL + super.toString() + "Status: This round is finished." + EOL + jogadas() + " moves were used." + EOL; 
		}
    }


}
