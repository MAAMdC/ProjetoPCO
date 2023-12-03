package types;


/**
 * Representa um copo (cup).
 */
public class Cup extends Bottle {
	
	/**
	 * Tamanho do copo.
	 */
	public static final int CUP_SIZE = 1;
	
	/**
	 * Número de vezes que o copo pode ser utilizado.
	 */
	public static final int TIMES_OF_USAGES = 3;
	
	/**
	 * Representação em string da variável empty.
	 */
	public static String empty = "⚪";

	/**
	 * Número de vezes que o copo foi usado.
	 */
	public int numberOfUsages;
	
	
	/**
	 * Cria um copo.
	 */
	public Cup() {
		super();
	    this.numberOfUsages = 0;
	}
	
	
	/** Cria um copo com um dado conteúdo.
	 * @param Conteúdo num array de tipo Filling.
	 */
	public Cup(Filling[] content) {
	    super(content);
	    this.numberOfUsages = 0;
	}

	@Override
	/** Recebe um golo caso o copo ainda não tenha excedido o limite de uso.
	 * @param O golo.
	 * @return true se deu para receber, false caso contrário.
	 */
	public boolean receive(Filling s) {
		if (numberOfUsages < TIMES_OF_USAGES) {
			boolean received = super.receive(s);
	            
			if (received) {
				numberOfUsages++;
	            } 
			return received;
			
	        } else {
	            System.out.println("O copo atingiu o número máximo de usos e não pode receber mais conteúdos.");
	            return false;
	    }
	}
	    
	/** Devolve a representação do copo em string.
	 * @return string com a representação do copo.
	 */
	public String toString() {
	    StringBuilder result = new StringBuilder();
	    for(int i = 0; i < size; i ++) {
	    	if(content[i] != null) {
	    		result.append(content[i]);
	    	}else {
	    		if(i == 3) {
	    			result.append(Bottle.empty);
	    		} else {
	    			result.append(empty);
	    		}
	    	}
	    		result.append(EOL);
	    }
	    return result.toString();
	}
	}
