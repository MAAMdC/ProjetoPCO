package types;


/** Interface FillingGame que define métodos para a realização do jogo.
 * 
 */
public interface FillingGame {
	
	/** Realiza uma jogada no tabuleiro.
	 * @param x Índice da garrafa que vai dar o golo.
	 * @param y Índice da garrafa que vai receber o golo.
	 */
	public void play(int x, int y);
	
	/** Verifica se a ronda está concluída.
	 * @return true se a ronda estiver concluída, false caso contrário.
	 */
	public boolean isRoundFinished();
	
	/** Inicia uma nova ronda.
	 *
	 */
	public void startNewRound();
	
	/** Implementa uma nova garrafa (ou cup) ao tabuleiro.
	 * 
	 */
	public void provideHelp();
	
	/** Devolve o conteúdo que está no topo da garrafa com o índice x.
	 * @param x Índice da garrafa.
	 * @return O conteúdo que está no topo.
	 */
	public Filling top (int x);
	
	/** Verifica se o conteúdo da  garrafa no índice x é igual.
	 * 
	 * @param x Índice da garrafa.
	 * @return true se for igual, false caso contrário.
	 */
	public boolean singleFilling(int x);
	
	
	/** Devolve o score atual.
	 * 
	 * @return score atual.
	 */
	public int score();


}
