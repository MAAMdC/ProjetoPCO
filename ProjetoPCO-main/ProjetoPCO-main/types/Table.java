package types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/** Representa um tabuleiro.
 * 
 */
public class Table {

	public static String EOL = System.lineSeparator();
	public static final String empty = "⬜";
	public static final int DIFICULTY = 3;
	public static final int DEFAULT_BOTTLE_SIZE = 5; 

	/**
	 * Dificuldade do jogo.
	 */
	protected int dificulty;

	/**
	 * Tamanho das garrafas que vão ser usadas. 
	*/
	protected int bottleSize;

	/**
	 * Número de simbolos a ser usados.
	 */
	protected int numberOfUsedSymbols;

	/**
	 * Conjunto de garrafas que farão o tabuleiro de jogo.
	 */
	protected ArrayList<Bottle> table;

	/**
	 * Simbolos a serem uasdos.
	 */
	protected Filling[] symbols;

	/**
	 * Semente a ser usada.
	 */
	protected int seed;
	
	/** Cria um tabuleiro.
	 * 
	 * @param symbols
	 * @param numberOfUsedSymbols
	 * @param seed
	 * @param bootleSize
	 */
	public Table(Filling[] symbols, int numberOfUsedSymbols, int seed, int bootleSize) {
		
		this.symbols = symbols;
		this.numberOfUsedSymbols = numberOfUsedSymbols;
		this.seed = seed;
		this.bottleSize = bootleSize;
		this.table = new ArrayList<Bottle>();
		
		int maxBottleQuantity = Math.min(symbols.length, numberOfUsedSymbols);

		Random randomSeed = new Random(this.seed);

		HashMap<Filling, Integer> symbolCounter = new HashMap<Filling, Integer>();

		for (int i = 0; i < this.numberOfUsedSymbols; i++) {
		    symbolCounter.put(symbols[i], 0);
		}
		for (int i = 0; i < maxBottleQuantity; i++) {
		    Filling[] bottleList = new Filling[this.bottleSize];
		    for (int j = 0; j < this.bottleSize; j++) {
		        Filling usedSymbol = this.symbols[randomSeed.nextInt(this.numberOfUsedSymbols)];
		        int symbolCount = symbolCounter.get(usedSymbol);
		        if (symbolCount < this.bottleSize) {
		            bottleList[j] = usedSymbol;
		            symbolCounter.merge(usedSymbol, 1, Integer::sum);
		        } else {
		            j--;
		        }
		    }
		    Bottle bottle = new Bottle(bottleList);
		    table.add(bottle);
		}
		for (int i = 0; i < DIFICULTY; i++) {
		    Bottle emptyBottle = new Bottle(this.bottleSize);
		    table.add(emptyBottle);
		}
	}

	/** Gera uma nova tabela.
	 * 
	 */
	public void regenerateTable() {

        //Está a dar erro nos testes, mas está a gerar de forma completamente eficiente uma nova table.

        table.clear();  // Limpa a tabela para reconstruir
        int maxBottleQuantity = Math.min(symbols.length, numberOfUsedSymbols);

        Random rand = new Random(); 
        int numberOfFillingUsages;
        int[] usages_list = new int[maxBottleQuantity];
        for (int i = 0; i < usages_list.length; i++) {
            usages_list[i] = 0;
        }


        for (int i = 0; i < maxBottleQuantity; i++) {
            Filling[] bottleList = new Filling[this.bottleSize];
            for (int j = 0; j < this.bottleSize; j++) {
                boolean isDone = false;
                while (!isDone) {
                    numberOfFillingUsages = rand.nextInt(this.numberOfUsedSymbols);
                    Filling usedSymbol = this.symbols[numberOfFillingUsages];
                    System.out.println(numberOfFillingUsages);
                    System.out.println(usedSymbol);
                    int symbolCount = usages_list[numberOfFillingUsages];

                    if (symbolCount < this.bottleSize) {
                        bottleList[j] = usedSymbol;
                        symbolCount++;
                        usages_list[numberOfFillingUsages] = symbolCount;
                        isDone = true;
                    }
                }

            }
            Bottle bottle = new Bottle(bottleList);
            table.add(bottle);
        }
        for (int i = 0; i < DIFICULTY; i++) {
            Bottle emptyBottle = new Bottle(this.bottleSize);
            table.add(emptyBottle);
        }
    }

	/** Verifica se o conteúdo da garrafa no índice x é igual.
	 * @param x Índice da garrafa.
	 * @return true se for igual, false caso contrário.
	 */
	public boolean singleFilling(int x) {
		
		return table.get(x).isSingleFilling();
	}
	
	/** Verifica se a garrafa no índice x está vazia.
	 * @param x Índice da garrafa.
	 * @return true se está vazia, false caso contrário.
	 */
	public boolean isEmpty(int x) {
		return table.get(x).isEmpty();
	}

	/** Verifica se a garrafa no índice x está cheia.
	 * @param x Índice da garrafa.
	 * @return true se estiver cheia, false caso contrário.
	 */
	public boolean isFull(int x) {
		return table.get(x).isFull();
	}

	/** Verifica se cada garrafa possui o mesmo conteúdo dentro dela.
	 * 
	 * @return true se possuir, false caso contrário.
	 */
	public boolean areAllFilled() {
		for(Bottle b : table) {
			if(!b.isSingleFilling()) {
				return false;
			}else {
				Filling primeiro = b.getContent()[0];
				for(int i = 0; i < this.bottleSize; i++) {
					if(primeiro != b.getContent()[i]) {
						return false;
					}
				}
			}
		}
		return true;
	}


	/** Desloca o golo do topo de uma garrafa para o topo de outra garrafa.
	 * 
	 * @param x Índice da garrafa que vai ceder o golo.
	 * @param y Índice da garrafa que vai receber o golo.
	 */
	public void pourFromTo(int i, int j) {
		Filling conteudo = top(i);
		// como o cup_size é só um, ele só pode armazenar um simbolo
		if(table.get(j) instanceof Cup) {
			// se o cup estiver vazio, adiciona o elemento
			if(table.get(j).isEmpty()) {
				table.get(i).pourOut();
				table.get(j).receive(conteudo);
			// caso contrário, não faz nada
			}else {
				
			}
		}else {
			// caso não seja cup, verifica se está vazia e enquanto o top de i for igual ao conteudo
			// ele está sempre a adicionar à bottle vazia
			if(table.get(j).isEmpty()) {
				while(conteudo == top(i)) {
					table.get(i).pourOut();
					table.get(j).receive(conteudo);
				}		
			}else {
				// caso não esteja vazia, verifica se a bottle não está vazia e se o top de i é igual ao top de j
				// para adicionar os símbolos
				if(!table.get(j).isFull() && top(i) == top(j)) {
					while(!table.get(i).isEmpty() && conteudo == top(i)) {
						table.get(i).pourOut();
						table.get(j).receive(conteudo);
					}
				}else {
					if(!table.get(j).isFull()) {
						while(!table.get(i).isEmpty()) {
							table.get(i).pourOut();
							table.get(j).receive(conteudo);
						}
					}
				}
			}
		}	
	}

	/** Adiciona ao tabuleiro uma nova garrafa.
	 * @param bottle
	 */
	public void addBootle(Bottle bottle) {
	    table.add(bottle);
	}
	
	/** Devolve o tamanho das garrafas do tabuleiro.
	 * 
	 * @return Tamanho das garrafas do tabuleiro.
	 */
	public int getSizeBottles() {
		return this.bottleSize;
	}


	/**
	 * 
	 * @param x
	 * @return
	 */
	public Filling top(int x) {
		return table.get(x).top();
	}

	/**
	 * 
	 */
	public String toString() {
	    StringBuilder result = new StringBuilder();
	    for (int i = this.bottleSize - 1; i >= 0; i--) {
	        for (Bottle b : table) {
	            Filling topFilling = b.getContent()[i];
	            
	            if(topFilling != null) {
	            	result.append(topFilling.toString()).append("    ");
	            }else {
	            	if(b instanceof Cup) {
	            		// adiciona um quadrado quando i é maior que zero, ou seja,
	            		// quando i é igual a 1, o valor da variável CUP_SIZE
	            		if(i > 0) {
	            			result.append(Cup.empty).append("    ");
	            		}else {
	            			result.append(empty).append("    ");
	            		}
	            	}else {
	            		result.append(empty).append("    ");
	            	}
	            }
	        }
	        result.append(EOL);
	    }

	    return result.toString();
	}

}
