package types;

//Notem que podem faltar métodos na classe que permitam lidar melhor com os objectos.

/** Representa um tabuleiro de jogo abstrato.
 * 
 */
public abstract class AbstractFillingGame implements FillingGame {

	public static String EOL = System.lineSeparator();
    	public static String empty = "⬜";
    
    /** 
     * Tabuleiro de jogo.
     */
	private Table table;
	
	/**
	 * Número de jogadas feitas.
	 */
	private int jogadas;
	
	/** Cria um tabuleiro de jogo abstrato.
	 * 
	 * @param symbols Array de simbolos a serem utilizados.
	 * @param numberOfUsedSymbols Número de símbolos para serem utilizados.
	 * @param seed Número da semente para o gerador.
	 * @param bootleSize Tamanho de cada garrafa.
	 */
	public AbstractFillingGame(Filling[] symbols, int numberOfUsedSymbols, int seed, int bootleSize) {
		
		this.table = new Table(symbols, numberOfUsedSymbols, seed, bootleSize);
		this.jogadas = 0;

	}
	

	/** Devolve o número de jogadas.
	 * 
	 * @return número de jogadas.
	 */
	public int jogadas() {
		return this.jogadas; 
	}


	/** Devolve o tamanho das garrafas do tabuleiro.
	 * 
	 * @return Tamanho das garrafas do tabuleiro.
	 */
	public int getBottlesSize() {
		return this.table.getSizeBottles();
	}

	
	/** Realiza uma jogada.
	 * 
	 * @param x Índice da garrafa que vai ceder o golo.
	 * @param y Índice da garrafa que vai receber o  golo.
	 */
	public void play(int x, int y) {
		table.pourFromTo(x, y);
		this.jogadas++;
	}

	
	/**
	 * 
	 */
	public void provideHelp() {

	}

	
	/** Cria uma nova garrafa.
	 * 
	 * @return Uma nova garrafa.
	 */
	public abstract Bottle getNewBootle();

	
	/** Atualiza o score.
	 * 
	 */
	public abstract void updateScore();

	/** Devolve o conteúdo que está no topo da garrafa com o índice x.
	 * @param x Índice da garrafa.
	 * @return O conteúdo que está no topo.
	 */
	public Filling top(int x) {
		return this.table.top(x);
	}

	
	/** Verifica se o conteúdo da  garrafa no índice x é igual.
	 * 
	 * @param x Índice da garrafa.
	 * @return true se for igual, false caso contrário.
	 */
	public boolean singleFilling(int x) {
		return this.table.singleFilling(x);
	}

	
	/** Verifica se a ronda está concluída.
	 * @return true se a ronda estiver concluída, false caso contrário.
	 */
	public abstract boolean isRoundFinished();


	/** Devolve o score atual.
	 * 
	 * @return score atual.
	 */
	public abstract int score();

	
	/** Inicia uma nova ronda.
	 * 
	 */
	public void startNewRound() {
		this.table.regenerateTable();
	}

	
	/** Verifica se cada garrafa possui o mesmo conteúdo dentro dela.
	 * 
	 * @return true se possuir, false caso contrário.
	 */
	public boolean areAllFilled() {
		return this.table.areAllFilled();
	}

	
	/** Representação do tabuleiro em string.
	 * 
	 * @return string da representação do tabuleiro.
	 */
	public String toString() {
		
		return this.table.toString();
	}

}
