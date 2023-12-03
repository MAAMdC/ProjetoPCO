package types;

import java.util.Arrays;
import java.util.Iterator;

/** Representa uma garrafa (bottle).
 * 
 */
public class Bottle implements Iterable<Filling> {

	
	/**
	 * Tamanho predefinido da garrafa.
	 */
    public static int DEFAULT_SIZE = 5;
    
    /**
     * Representação em string da variável empty.
     */
    public static String empty = "⬜";
    
    /**
     * Representação da mudança de linha em string.
     */
    public static String EOL = System.lineSeparator();
    
    /**
     * 
     */
    protected int size;
    protected Filling[] content;
    
    
    /** Cria uma garrafa com um tamanho predefinido.
     * 
     */
    public Bottle() {
        this.size = DEFAULT_SIZE;
        this.content = new Filling[DEFAULT_SIZE];
    }
    
    
    /** Cria uma garrafa com um dado tamanho.
     * @param size Tamanho da garrafa.
     */
    public Bottle(int size) {
        this.size = size;
        this.content = new Filling[size];
    }
    
    /** Cria uma garrafa com um dado conteúdo.
     * @param content Array com o conteúdo de tipo Filling.
     */
    public Bottle(Filling[] content) {
        this.size = content.length;
        this.content = Arrays.copyOf(content, size);
    }
    
    
    /** Verifica se a garrafa está cheia.
     * @return true se estiver cheia, false caso contrário.
     */
    public boolean isFull() {
        for (Filling element : content) {
            if (element == null) {
                return false;
            }
        }
        return true;
    }

    
    /** Verifica se a garrafa está vazia.
     * @return true se estiver vazia, false caso contrário.
     */
    public boolean isEmpty() {
        for (Filling element : content) {
            if (element != null) {
                return false;
            }
        }
        return true;
    }

    /** Devolve o golo que se encontra no topo da garrafa.
     * @return Golo que está no topo.
     */
    public Filling top() {
        if (!isEmpty()) {
        	Filling top = content[0];
            for (int i = 1; i<size; i++) {
            	if (content[i] == null) {
            		return top;
            	}
            	else {
            		top = content[i];
            	}
            }
            return top;
        }else {
        	throw new ArrayIndexOutOfBoundsException();
        }    
    }

    /** Devolve o número de espaços livres que a garrafa tem.
     * @return Número de espaços livres que a garrafa tem.
     */
    public int spaceAvailable() {
        int usedSpaces = 0;
        for (Filling element : content) {
            if (element != null) {
                usedSpaces++;
            }
        }
        return size - usedSpaces;
    }

    /** Retira da garrafa o golo que está no topo.
     * 
     */
    public void pourOut() {
        if(!isEmpty()) {
        	int lastNonIndex = size - 1;
        	while(lastNonIndex >=0 && content[lastNonIndex] == null) {
        		lastNonIndex--;
        	}
        	
        	if(lastNonIndex >=0) {
        		content[lastNonIndex] = null;
        	}
        }
    }
    

    /** Adiciona à garrafa um golo, caso esta não esteja cheia.
     * 
     * @param s Golo a adicionar.
     * @return true se deu para adicionar, false caso contrário.
     */
    public boolean receive(Filling s) {
        if (!isFull()) {
            for (int i = 0; i < size; i++) {
            	if(content[i] == null) {
            		if(isEmpty()) {
            			content[i] = s;
            			return true;
            		}else {
            			if(top() == s) {
            				content[i] = s;
            				return true;
            			}
            			return false;
            		}
            	}
            }
        }
        return false;
    }
    
    /** Devolve o tamanho da garrafa.
     * @return Tamanho da garrafa.
     */
    public int size() {
        return this.size;
    }
    
    /** Verifica se todos os golos da garrafa são iguais.
     * @return true se os golos forem iguais, false caso contrário.
     */
    public boolean isSingleFilling() {
        if (!isEmpty()) {
            Filling firstElement = null;

            // Encontrar o primeiro elemento não nulo
            for (int i = 0; i < size; i++) {
                if (content[i] != null) {
                    firstElement = content[i];
                    break;
                }
            }
            
            if (firstElement == null) {
                return false; // se não houver elementos não nulos
            }

            // Verifica se todos os elementos não nulos são do mesmo tipo
            for (int i = 0; i < size; i++) {
                if (content[i] != null && !content[i].equals(firstElement)) {
                    return false; // se pelo menos um elemento for diferente do firstElement
                }
            }
            return true; // se todos os elementos não nulos forem do mesmo tipo
        }
        return true; // se a garrafa estiver vazia
    }


    /** Devolve o conteúdo da garrafa.
     * @return Array com conteúdo da garrafa.
     */
    public Filling[] getContent() {
        return Arrays.copyOf(content, size);
    }

    /** Devolve a representação da garrafa em string.
     * @return Representação em string da garrafa.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = size - 1; i >= 0; i--) {
        	if(content[i] != null) {
        		result.append(content[i]);
        	}else {
        		result.append(empty);
        	}
        	
        	result.append(EOL);
        }
        return result.toString();
    }

    /**
     * 
     */
    public Iterator<Filling> iterator() {
        return new Iterator<Filling>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size && content[currentIndex] != null;
            }

            @Override
            public Filling next() {
                if (hasNext()) {
                    Filling nextFilling = content[currentIndex];
                    currentIndex++;
                    return nextFilling;
                } else {
                    throw new IllegalStateException("Não há mais elementos na garrafa.");
                }
            }
        };
    }
}