package jogo_antonio_elanne;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class InterfacePrincipal extends JFrame implements ActionListener {
	JPanel jContentPane = null;
	JButton mapaPosicao[][] = new JButton[6][7];
	JLabel mensagem = null;
	JMenu menu = null;
	JMenuBar menuBar = null;
	JMenuItem menuItemIniciar = null;
	Icon preta, branca;
	Tabuleiro tabuleiro;
	boolean jogador1Vez, jogador2Vez;
	ImageIcon imageIcon;
	Icon icon;
	JOptionPane jOptionPane;
	Icon pecaVazia;
	JButton posicao = null;
	boolean jogoAndamento;
	IA ia;

	public InterfacePrincipal() {
		super();
		start();
	}

	private void start() {
		this.setSize(680, 600);
		this.setLocationRelativeTo(null);
		this.setContentPane(getJContentPane());
		this.setTitle("Ligue 4 - Antonio Taha 12200988, Elanne Souza 10101180");
		this.jogoAndamento = true;
		this.tabuleiro = new Tabuleiro();
		this.ia = new IA(tabuleiro);
		this.jogador1Vez = true;
		this.jogador2Vez = false;
	}

	private JPanel getJContentPane() {
		preta = new ImageIcon(getClass().getResource("peca-Preta.png"));
		branca = new ImageIcon(getClass().getResource("peca-Branca.png"));
		pecaVazia = new ImageIcon(getClass().getResource("posicaoVazia.png"));
		jContentPane = new JPanel();
		jContentPane.setLayout(null);
		int x;
		int y = 40;
		for (int i = 0; i < 6; i++) {
			x = 20;
			for (int j = 0; j < 7; j++) {
				posicao = new JButton();
				posicao.setBounds(new Rectangle(x, y, 90, 70));
				posicao.setIcon(pecaVazia);
				posicao.addActionListener(this);
				if (i != 5)
					posicao.setEnabled(false);
				jContentPane.add(posicao, null);
				mapaPosicao[i][j] = posicao;
				x += 88;
			}
			y += 70;
		}
		menuBar = new JMenuBar();
		menuBar.add(this.getMenu());
		this.setJMenuBar(menuBar);

		return jContentPane;
	}

	private JMenu getMenu() {
		if (menu == null) {
			menu = new JMenu();
			menu.setText("Jogo");
			menu.setBounds(new Rectangle(1, 0, 57, 21));
			menu.add(getMenuIniciar());

		}
		return menu;
	}

	private JMenuItem getMenuIniciar() {
		if (menuItemIniciar == null) {
			menuItemIniciar = new JMenuItem();
			menuItemIniciar.setText("Iniciar");
			menuItemIniciar.addActionListener(this);
		}
		return menuItemIniciar;
	}

	public void selecionaPosicao(int linha, int coluna) {
		if (tabuleiro.getTabuleiro()[linha][coluna] == 0 && jogoAndamento) {
			//if (jogador1Vez){
				//mapaPosicao[(linha)][(coluna)].setIcon(preta);
				tabuleiro.setTabuleiroJogador(coluna);
				ia.gameResult(tabuleiro);
				//verificaVencedor(linha, coluna);
				//jogador1Vez = false;
				//jogador2Vez = true;
				tabuleiro.setTabuleiroComputador(ia.getAIMove());
				ia.gameResult(tabuleiro);
				//verificaVencedor(linha, coluna);
				//System.out.println(ia.getAIMove(tabuleiro));
				for(int i=0; i<=5; ++i){
					for(int j=0; j<=6; ++j){
						System.out.print(tabuleiro.getTabuleiro()[i][j]);
						if(tabuleiro.getTabuleiro()[i][j]==1){
							mapaPosicao[(i)][(j)].setIcon(preta);
							mapaPosicao[(i - 1)][(j)].setEnabled(true);
						}else if(tabuleiro.getTabuleiro()[i][j]==2){
							mapaPosicao[(i)][(j)].setIcon(branca);
							mapaPosicao[(i - 1)][(j)].setEnabled(true);
						}
					}
					System.out.println();
				}
			//}
			/*else{
				mapaPosicao[(linha)][(coluna)].setIcon(branca);
				tabuleiro.setTabuleiroComputador(coluna);
				jogador1Vez = true;
				jogador2Vez = false;
			}
			if (linha != 0)
				mapaPosicao[(linha - 1)][(coluna)].setEnabled(true);*/

			verificaVencedor(linha, coluna);
		}
	}

	private void verificaVencedor(int linha, int coluna) {
		int cor;
		if (!jogador1Vez)
			cor = 1;
		else
			cor = 2;

		verificaVertical(linha, coluna, cor);

		verificaHorizontal(linha, coluna, cor);
		
		verificaDiagonalEsquerda(linha, coluna, cor);
		
		verificaDiagonalDireita(linha, coluna, cor);

	}

	private void verificaDiagonalDireita(int linha, int coluna, int cor) {
		int cont = 1;
		int tempC = coluna;
		int tempL = linha;
		boolean corIgual = true;
		
		//Diagonal Direita Superior
		while (corIgual && tempL > 0 && tempC<5) {
			tempL--;
			tempC++;
			if (tabuleiro.getTabuleiro()[tempL][tempC] == cor)
				cont++;
			else
				corIgual = false;
		}
		
		tempC = coluna;
		tempL = linha;
		corIgual = true;
		
		//Diagonal Esquerda Inferior
		while (corIgual && tempL < 5 && tempC>0) {
			tempL++;
			tempC--;
			if (tabuleiro.getTabuleiro()[tempL][tempC] == cor)
				cont++;
			else
				corIgual = false;
		}
		verificaQuemVenceu(cont, cor);
	}
	private void verificaDiagonalEsquerda(int linha, int coluna, int cor) {
		int cont = 1;
		int tempC = coluna;
		int tempL = linha;
		boolean corIgual = true;
		
		//Diagonal Direita Inferior
		while (corIgual && tempL < 5 && tempC<5) {
			tempL++;
			tempC++;
			if (tabuleiro.getTabuleiro()[tempL][tempC] == cor)
				cont++;
			else
				corIgual = false;
		}
		
		tempC = coluna;
		tempL = linha;
		corIgual = true;
		
		//Diagonal Esquerda Superior
		while (corIgual && tempL > 0 && tempC>0) {
			tempL--;
			tempC--;
			if (tabuleiro.getTabuleiro()[tempL][tempC] == cor)
				cont++;
			else
				corIgual = false;
		}
		verificaQuemVenceu(cont, cor);
	}
	


	private void verificaHorizontal(int linha, int coluna, int cor) {
		int cont = 1;
		int temp = coluna;
		boolean corIgual = true;

		// verifica horizontal esquerda
		while (corIgual && temp < 6) {
			temp++;
			if (tabuleiro.getTabuleiro()[linha][temp] == cor)
				cont++;
			else
				corIgual = false;

		}
		temp = coluna;
		corIgual = true;
		
		// verifica horizontal esquerda
		while (corIgual && temp > 0) {
			temp--;
			if (tabuleiro.getTabuleiro()[linha][temp] == cor)
				cont++;
			else
				corIgual = false;
		}
		verificaQuemVenceu(cont, cor);
	}

	private void verificaVertical(int linha, int coluna, int cor) {
		boolean corIgual = true;
		int temp = linha;
		int cont = 1;
		while (corIgual && temp < 5) {
			temp++;
			if (tabuleiro.getTabuleiro()[temp][coluna] == cor)
				cont++;
			else
				corIgual = false;
		}
		verificaQuemVenceu(cont, cor);

	}

	private void verificaQuemVenceu(int cont, int cor) {
		if (cont >= 4 && cor == 1) {
			JOptionPane.showMessageDialog(this, "Você venceu!");
			jogoAndamento = false;
		} else if (cont >= 4 && cor == 2) {
			JOptionPane.showMessageDialog(this, "Computador venceu!");
			jogoAndamento = false;
		}
	}

	private void verificaVertical(boolean corIgual, int temp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == menuItemIniciar) {
			this.start();
			this.pack();
			this.setSize(680, 600);

		}
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				if (e.getSource() == mapaPosicao[i][j]) {
					selecionaPosicao(i, j);
				}
			}
		}
	}

}
