package types;

//Notem que podem faltar métodos na classe que permitam lidar melhor com os objectos.
public class FinalScoringFillingGame extends AbstractFillingGame{

	//TODO
	protected int score;

	/**
	 * 
	 * @param symbols
	 * @param numberOfUsedSymbols
	 * @param seed
	 * @param bootleSize
	 */
	public FinalScoringFillingGame(Filling[] symbols, int numberOfUsedSymbols, int seed, int bootleSize) {
		super(symbols, numberOfUsedSymbols, seed, bootleSize);
		this.jogadas = jogadas();
		this.score = 0;
	}

	/**
	 * 
	 * @param symbols
	 * @param numberOfUsedSymbols
	 * @param seed
	 * @param bootleSize
	 * @param score
	 */
	public FinalScoringFillingGame(Filling[] symbols, int numberOfUsedSymbols, int seed, int bootleSize, int score) {
		super(symbols, numberOfUsedSymbols, seed, bootleSize);
		this.jogadas = jogadas();
		this.score = score;
	}

	/**
	 * 
	 */
	@Override
	public void provideHelp() {
		if(score>0) {
			this.score-= 100;
		}
		Bottle helpBottle = new Bottle();
		helpBottle = getNewBootle();
		table.addBootle(helpBottle);
	}


	/**
	 * 
	 */
	public void updateScore() {
		
		if (isRoundFinished()) {
			
			if (jogadas < 10) {
				this.score+=1000;
			}
			else if (jogadas < 15) {
				this.score+=500;
			}
			else if (jogadas < 25) {
				this.score+=200;
			}
		}
		else {
			this.score-=100;
		}
	}
	/**
	 * 
	 */
	public boolean isRoundFinished(){
		return this.table.areAllFilled();
	}

	/**
	 * 
	 */
	@Override
	public int score() {
		return this.score;
	}

	
	/**
	 * 
	 */
	@Override
	public Bottle getNewBootle() {
		Bottle newBottle = new Bottle(getBottlesSize());
		return newBottle;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		if(!isRoundFinished()) {
			return "Score: " + score() + EOL + super.toString() + "Status: The round is not finished." + EOL + jogadas() + " moves have been used until now." + EOL; 
		}else {
			updateScore();
			return "Score: " + score() + EOL + super.toString() + "Status: This round is finished." + EOL + jogadas() + " moves were used." + EOL; 
		}
	 }
}