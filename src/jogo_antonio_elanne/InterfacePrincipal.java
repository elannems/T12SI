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
	JMenuItem menuItemPoda = null;
	Icon preta, branca;
	Tabuleiro tabuleiro;
	boolean jogador1Vez, jogador2Vez;
	ImageIcon imageIcon;
	Icon icon;
	JOptionPane jOptionPane;
	Icon pecaVazia;
	JButton posicao = null;
	boolean jogoAndamento;
	boolean comPoda;
	IA ia;

	public InterfacePrincipal() {
		this.setSize(680, 600);
		this.setLocationRelativeTo(null);
		this.setContentPane(getJContentPane());
		this.setTitle("Ligue 4 - Antonio Taha 12200988, Elanne Souza 10101180");
	}

	private void start() {
		this.setContentPane(getJContentPane());
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
			menu.add(this.getMenuIniciar());
			menu.add(this.getMenuMinMaxPoda());

		}
		return menu;
	}

	private JMenuItem getMenuMinMaxPoda() {
		if (menuItemPoda == null) {
			menuItemPoda = new JMenuItem();
			menuItemPoda.setText("MiniMax com Poda");
			menuItemPoda.addActionListener(this);
		}
		return menuItemPoda;
	}

	private JMenuItem getMenuIniciar() {
		if (menuItemIniciar == null) {
			menuItemIniciar = new JMenuItem();
			menuItemIniciar.setText("MiniMax");
			menuItemIniciar.addActionListener(this);
		}
		return menuItemIniciar;
	}

	public void selecionaPosicao(int linha, int coluna) {
		if (tabuleiro.getTabuleiro()[linha][coluna] == 0 && jogoAndamento) {
			tabuleiro.setTabuleiroJogador(coluna);
			for (int i = 0; i <= 5; ++i) {
				for (int j = 0; j <= 6; ++j) {
					if (tabuleiro.getTabuleiro()[i][j] == 2) {
						mapaPosicao[(i)][(j)].setIcon(preta);
						if(i!=0)
							mapaPosicao[(i - 1)][(j)].setEnabled(true);
					}
				}
			}
			this.verificaVencedor();
			tabuleiro.setTabuleiroComputador(this.comPoda ? ia
					.getAIMoveWithPoda() : ia.getAIMove());
			JOptionPane.showMessageDialog(null,"Número de iterações da IA nesta rodada: "+this.ia.qntItera);
			this.ia.mediaIteracoes+=this.ia.qntItera;
			for (int i = 0; i <= 5; ++i) {
				for (int j = 0; j <= 6; ++j) {
					if (tabuleiro.getTabuleiro()[i][j] == 1) {
						mapaPosicao[(i)][(j)].setIcon(branca);
						mapaPosicao[(i - 1)][(j)].setEnabled(true);
					}
				}
			}
			this.verificaVencedor();
		}	
	}

	private void verificaVencedor() {

		int resultado = this.ia.resultadoGame(this.tabuleiro);
		if (resultado == 2) {
			JOptionPane.showMessageDialog(this, "Você venceu!");
			jogoAndamento = false;
			JOptionPane.showMessageDialog(this, "A média de iterações da IA foi: "+this.ia.mediaIteracoes/this.ia.chamaIA+" iterações.");
		} else if (resultado == 1) {
			JOptionPane.showMessageDialog(this, "Computador venceu!");
			jogoAndamento = false;
			JOptionPane.showMessageDialog(this, "A média de iterações da IA foi: "+this.ia.mediaIteracoes/this.ia.chamaIA+" iterações.");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == menuItemIniciar) {
			this.comPoda = false;
			this.start();
			this.pack();
			this.setSize(680, 600);

		} else if (e.getSource() == menuItemPoda) {
			this.comPoda = true;
			this.start();
			this.pack();
			this.setSize(680, 600);

		} else if (this.jogoAndamento) {
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 7; j++) {
					if (e.getSource() == mapaPosicao[i][j]) {
						selecionaPosicao(i, j);
					}
				}
			}

		}else if(!this.jogoAndamento){
			JOptionPane.showMessageDialog(this, "Por favor, clique no menu Iniciar e escolha um dos itens para iniciar uma partida.","INICIAR JOGO", JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
