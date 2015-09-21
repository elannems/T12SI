package jogo_antonio_elanne;

/*
 * Adaptação do codigo disponivel em:
 * MiniMax: http://www.codebytes.in/2014/12/connect-4-ai-java-using-minimax.html
 * MiniMax com Podas: http://www.codebytes.in/2014/12/connect-4-minimax-with-alpha-beta.html
 */
public class IA {
	private Tabuleiro b;
	private int nextMoveLocation = -1;
	private int maxDepth = 8;
	int mediaIteracoes = 0;
	int qntItera = 0;
	int chamaIA = 0;

	public IA(Tabuleiro b) {
		this.b = b;
	}

	public int resultadoGame(Tabuleiro b) {
		int aiScore = 0, humanScore = 0;
		for (int i = 5; i >= 0; --i) {
			for (int j = 0; j <= 6; ++j) {
				if (b.getTabuleiro()[i][j] == 0)
					continue;

				// Verifica horizontal
				if (j <= 3) {
					for (int k = 0; k < 4; ++k) {
						if (b.getTabuleiro()[i][j + k] == 1)
							aiScore++;
						else if (b.getTabuleiro()[i][j + k] == 2)
							humanScore++;
						else
							break;
					}
					if (aiScore == 4)
						return 1;
					else if (humanScore == 4)
						return 2;
					aiScore = 0;
					humanScore = 0;
				}

				// Verifica vertical
				if (i >= 3) {
					for (int k = 0; k < 4; ++k) {
						if (b.getTabuleiro()[i - k][j] == 1)
							aiScore++;
						else if (b.getTabuleiro()[i - k][j] == 2)
							humanScore++;
						else
							break;
					}
					if (aiScore == 4)
						return 1;
					else if (humanScore == 4)
						return 2;
					aiScore = 0;
					humanScore = 0;
				}

				// Verifica diagonal direita superior
				if (j <= 3 && i >= 3) {
					for (int k = 0; k < 4; ++k) {
						if (b.getTabuleiro()[i - k][j + k] == 1)
							aiScore++;
						else if (b.getTabuleiro()[i - k][j + k] == 2)
							humanScore++;
						else
							break;
					}
					if (aiScore == 4)
						return 1;
					else if (humanScore == 4)
						return 2;
					aiScore = 0;
					humanScore = 0;
				}

				// verifica diagonal esquerda superior
				if (j >= 3 && i >= 3) {
					for (int k = 0; k < 4; ++k) {
						if (b.getTabuleiro()[i - k][j - k] == 1)
							aiScore++;
						else if (b.getTabuleiro()[i - k][j - k] == 2)
							humanScore++;
						else
							break;
					}
					if (aiScore == 4)
						return 1;
					else if (humanScore == 4)
						return 2;
					aiScore = 0;
					humanScore = 0;
				}
			}
		}

		for (int j = 0; j < 7; ++j) {
			// Se jogo nao terminou
			if (b.getTabuleiro()[0][j] == 0)
				return -1;
		}
		//continua atualizando tabuleiro
		return 0;
	}

	int calculaPontuacao(int aiScore, int moreMoves) {
		int moveScore = 4 - moreMoves;
		if (aiScore == 0)
			return 0;
		else if (aiScore == 1)
			return 1 * moveScore;
		else if (aiScore == 2)
			return 10 * moveScore;
		else if (aiScore == 3)
			return 100 * moveScore;
		else
			return 1000;
	}

	public int avaliaTabuleiro(Tabuleiro b) {

		int aiScore = 1;
		int score = 0;
		int blanks = 0;
		int k = 0, moreMoves = 0;
		for (int i = 5; i >= 0; --i) {
			for (int j = 0; j <= 6; ++j) {

				if (b.getTabuleiro()[i][j] == 0 || b.getTabuleiro()[i][j] == 2)
					continue;

				if (j <= 3) {
					for (k = 1; k < 4; ++k) {
						if (b.getTabuleiro()[i][j + k] == 1)
							aiScore++;
						else if (b.getTabuleiro()[i][j + k] == 2) {
							aiScore = 0;
							blanks = 0;
							break;
						} else
							blanks++;
					}

					moreMoves = 0;
					if (blanks > 0)
						for (int c = 1; c < 4; ++c) {
							int column = j + c;
							for (int m = i; m <= 5; m++) {
								if (b.getTabuleiro()[m][column] == 0)
									moreMoves++;
								else
									break;
							}
						}

					if (moreMoves != 0)
						score += calculaPontuacao(aiScore, moreMoves);
					aiScore = 1;
					blanks = 0;
				}

				if (i >= 3) {
					for (k = 1; k < 4; ++k) {
						if (b.getTabuleiro()[i - k][j] == 1)
							aiScore++;
						else if (b.getTabuleiro()[i - k][j] == 2) {
							aiScore = 0;
							break;
						}
					}
					moreMoves = 0;

					if (aiScore > 0) {
						int column = j;
						for (int m = i - k + 1; m <= i - 1; m++) {
							if (b.getTabuleiro()[m][column] == 0)
								moreMoves++;
							else
								break;
						}
					}
					if (moreMoves != 0)
						score += calculaPontuacao(aiScore, moreMoves);
					aiScore = 1;
					blanks = 0;
				}

				if (j >= 3) {
					for (k = 1; k < 4; ++k) {
						if (b.getTabuleiro()[i][j - k] == 1)
							aiScore++;
						else if (b.getTabuleiro()[i][j - k] == 2) {
							aiScore = 0;
							blanks = 0;
							break;
						} else
							blanks++;
					}
					moreMoves = 0;
					if (blanks > 0)
						for (int c = 1; c < 4; ++c) {
							int column = j - c;
							for (int m = i; m <= 5; m++) {
								if (b.getTabuleiro()[m][column] == 0)
									moreMoves++;
								else
									break;
							}
						}

					if (moreMoves != 0)
						score += calculaPontuacao(aiScore, moreMoves);
					aiScore = 1;
					blanks = 0;
				}

				if (j <= 3 && i >= 3) {
					for (k = 1; k < 4; ++k) {
						if (b.getTabuleiro()[i - k][j + k] == 1)
							aiScore++;
						else if (b.getTabuleiro()[i - k][j + k] == 2) {
							aiScore = 0;
							blanks = 0;
							break;
						} else
							blanks++;
					}
					moreMoves = 0;
					if (blanks > 0) {
						for (int c = 1; c < 4; ++c) {
							int column = j + c, row = i - c;
							for (int m = row; m <= 5; ++m) {
								if (b.getTabuleiro()[m][column] == 0)
									moreMoves++;
								else if (b.getTabuleiro()[m][column] == 1)
									;
								else
									break;
							}
						}
						if (moreMoves != 0)
							score += calculaPontuacao(aiScore, moreMoves);
						aiScore = 1;
						blanks = 0;
					}
				}

				if (i >= 3 && j >= 3) {
					for (k = 1; k < 4; ++k) {
						if (b.getTabuleiro()[i - k][j - k] == 1)
							aiScore++;
						else if (b.getTabuleiro()[i - k][j - k] == 2) {
							aiScore = 0;
							blanks = 0;
							break;
						} else
							blanks++;
					}
					moreMoves = 0;
					if (blanks > 0) {
						for (int c = 1; c < 4; ++c) {
							int column = j - c, row = i - c;
							for (int m = row; m <= 5; ++m) {
								if (b.getTabuleiro()[m][column] == 0)
									moreMoves++;
								else if (b.getTabuleiro()[m][column] == 1)
									;
								else
									break;
							}
						}
						if (moreMoves != 0)
							score += calculaPontuacao(aiScore, moreMoves);
						aiScore = 1;
						blanks = 0;
					}
				}
			}
		}
		return score;
	}

	public int minimax(int depth, int turn) {
		int resultadoGame = resultadoGame(b);
		if (resultadoGame == 1)
			return Integer.MAX_VALUE;
		else if (resultadoGame == 2)
			return Integer.MIN_VALUE;
		else if (resultadoGame == 0)
			return 0;

		if (depth == maxDepth)
			return avaliaTabuleiro(b);

		int maxScore = Integer.MIN_VALUE, minScore = Integer.MAX_VALUE;
		for (int j = 0; j <= 6; ++j) {
			if (!b.movimentoLegal(j))
				continue;

			if (turn == 1) {
				b.setTabuleiroComputador(j);
				int currentScore = minimax(depth + 1, 2);
				maxScore = Math.max(currentScore, maxScore);
				qntItera++;
				if (depth == 0) {
					if (maxScore == currentScore)
						nextMoveLocation = j;
				}
			} else if (turn == 2) {
				b.setTabuleiroJogador(j);
				int currentScore = minimax(depth + 1, 1);
				minScore = Math.min(currentScore, minScore);
			}
			b.desfazMovimento(j);
		}
		return turn == 1 ? maxScore : minScore;
	}

	public int minimaxComPoda(int depth, int turn, int alpha, int beta) {

		if (beta <= alpha) {
			if (turn == 1)
				return Integer.MAX_VALUE;
			else
				return Integer.MIN_VALUE;
		}
		int resultadoGame = resultadoGame(b);

		if (resultadoGame == 1)
			return Integer.MAX_VALUE / 2;
		else if (resultadoGame == 2)
			return Integer.MIN_VALUE / 2;
		else if (resultadoGame == 0)
			return 0;

		if (depth == maxDepth)
			return avaliaTabuleiro(b);

		int maxScore = Integer.MIN_VALUE, minScore = Integer.MAX_VALUE;

		for (int j = 0; j <= 6; ++j) {

			int currentScore = 0;

			if (!b.movimentoLegal(j))
				continue;

			if (turn == 1) {
				b.setTabuleiroComputador(j);
				currentScore = minimaxComPoda(depth + 1, 2, alpha, beta);
				qntItera++;
				if (depth == 0) {
					if (currentScore > maxScore)
						nextMoveLocation = j;
					if (currentScore == Integer.MAX_VALUE / 2) {
						b.desfazMovimento(j);
						break;
					}
				}

				maxScore = Math.max(currentScore, maxScore);

				alpha = Math.max(currentScore, alpha);
			} else if (turn == 2) {
				b.setTabuleiroJogador(j);
				currentScore = minimaxComPoda(depth + 1, 1, alpha, beta);
				minScore = Math.min(currentScore, minScore);

				beta = Math.min(currentScore, beta);
			}
			b.desfazMovimento(j);
			if (currentScore == Integer.MAX_VALUE
					|| currentScore == Integer.MIN_VALUE)
				break;
		}
		return turn == 1 ? maxScore : minScore;
	}

	public int getAIMove() {
		qntItera = 0;
		chamaIA++;
		nextMoveLocation = -1;
		minimax(0, 1);
		return nextMoveLocation;
	}

	public int getAIMoveWithPoda() {
		qntItera = 0;
		chamaIA++;
		nextMoveLocation = -1;
		minimaxComPoda(0, 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
		return nextMoveLocation;
	}

}