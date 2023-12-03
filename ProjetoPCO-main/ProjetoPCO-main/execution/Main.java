package execution;

import java.util.Scanner;

import types.*;

public class Main {

	
	
	
	public static void main(String[] args) {
		BettingFillingGame game1;
		FinalScoringFillingGame game2;
		Scanner in = new Scanner(System.in);
		System.out.println("Bem-Vindo ao jogo Water Sort Puzzle!\n");
		System.out.println("Aqui o utilizador vai poder experimentar um jogo feitos pelos alunos de 2ºano de LTI da Faculdade de Ciências de Lisboa!\n");
		System.out.println("Escolha um modo de jogo: BettingFillingGame ou FinalScoringFillingGames (1 ou 2)");
		int opcao = in.nextInt();
		System.out.println("-----------");
		if(opcao == 1) {
			Filling[] symbols = null;
			System.out.println("Escolha o tipo de símbolos que quer utilizar (1 a 4): ");
			System.out.println("1- Animals\n2- Balls\n3- Emojis\n4- Squares");
			switch(in.nextInt()) {
			case 1:
				symbols = Animals.values();
				break;
			case 2:
				symbols = Balls.values();
				break;
			case 3: 
				symbols = Emojis.values();
				break;
			case 4:
				symbols = Squares.values();
				break;
			}
			System.out.println("-----------");
			System.out.println("Qual o número de símbolos que quer utilizar?");
			int numberOfUsedSymbols = in.nextInt();
			System.out.println("-----------");
			System.out.println("Qual o número da seed?");
			int seed = in.nextInt();
			System.out.println("-----------");
			System.out.println("Qual o tamanho de cada garrafa?");
			int bottleSize = in.nextInt();
			System.out.println("-----------");
			System.out.println("Qual o número da aposta?");
			int bet = in.nextInt();
			System.out.println("-----------");
			System.out.println("Diga o número máximo de jogadas? (máximo 10 jogadas)");
			int maxPlays = in.nextInt();
			System.out.println("-----------");
			while(maxPlays >10) {
				System.out.println("Diga o número máximo de jogadas? (máximo 10 jogadas)");
				maxPlays = in.nextInt();
				System.out.println("-----------");
			}
			game1 = new BettingFillingGame(symbols, numberOfUsedSymbols, seed, bottleSize, 0, bet, maxPlays);
			System.out.println(game1.toString());
			boolean exited = false;
			while(!exited) {
				System.out.println("Escolha uma opção:");
				System.out.println("1- Ajuda\n2- Jogar\n3- Recomeçar a ronda");
				switch (in.nextInt()) {
				case 1:
					game1.provideHelp();
					break;
				case 2:
					System.out.println("Qual garrafa é que quer escolher?");
					int i = in.nextInt();
					System.out.println("Onde quer meter o elemento?");
					int j = in.nextInt();
					game1.play(i, j);
					break;
				case 3:
					game1.startNewRound();
					break;
				}
				System.out.println(game1.toString());
				if(game1.isRoundFinished()) {
					System.out.println("Deseja continuar?");
					switch(in.next()) {
					case "Sim":
						int score = game1.score();
						game1 = new BettingFillingGame(symbols, numberOfUsedSymbols, seed, bottleSize, score, bet, maxPlays);
						break;
					case "Não":
						System.out.println("Até a próxima!");
						exited = true;
						break;
					}
				}
			}
			
		}else {
			Filling[] symbols = null;
			System.out.println("Escolha o tipo de símbolos que quer utilizar (1 a 4): ");
			System.out.println("1- Animals\n2- Balls\n3- Emojis\n4- Squares");
			switch(in.nextInt()) {
			case 1:
				symbols = Animals.values();
				break;
			case 2:
				symbols = Balls.values();
				break;
			case 3: 
				symbols = Emojis.values();
				break;
			case 4:
				symbols = Squares.values();
				break;
			}
			System.out.println("-----------");
			System.out.println("Qual o número de símbolos que quer utilizar?");
			int numberOfUsedSymbols = in.nextInt();
			System.out.println("-----------");
			System.out.println("Qual o número da seed?");
			int seed = in.nextInt();
			System.out.println("-----------");
			System.out.println("Qual o tamanho de cada garrafa?");
			int bottleSize = in.nextInt();
			System.out.println("-----------");
			game2 = new FinalScoringFillingGame(symbols, numberOfUsedSymbols, seed, bottleSize);
			System.out.println(game2.toString());
			boolean exited = false;
			while(!exited) {
				System.out.println("Escolha uma opção:");
				System.out.println("1- Ajuda\n2- Jogar\n3- Recomeçar a ronda");
				switch (in.nextInt()) {
				case 1:
					game2.provideHelp();
					break;
				case 2:
					System.out.println("Qual garrafa é que quer escolher?");
					int i = in.nextInt();
					System.out.println("Onde quer meter o elemento?");
					int j = in.nextInt();
					game2.play(i, j);
					break;
				case 3:
					game2.startNewRound();
					break;
				}
				System.out.println(game2.toString());
				if(game2.isRoundFinished()) {
					System.out.println("Deseja continuar?");
					switch(in.next()) {
					case "Sim":
						int score = game2.score();
						game2 = new FinalScoringFillingGame(symbols, numberOfUsedSymbols, seed, bottleSize, score);
						game2.toString();
						break;
					case "Não":
						System.out.println("Até a próxima!");
						exited = true;
						break;
					}
				}
			}
		}
	}
}
