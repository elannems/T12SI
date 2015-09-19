package jogo_antonio_elanne;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class InterfacePrincipal extends JFrame implements ActionListener{
	JPanel jContentPane = null;
	JButton mapaPosicao[][] = new JButton[6][7];
	JLabel mensagem = null;
	JMenu menu = null;
	JMenuBar menuBar=null;
	JMenuItem menuItemIniciar = null;
	Icon preta, branca;
	Tabuleiro tabuleiro;
	boolean jogador1Vez, jogador2Vez;
	ImageIcon imageIcon;
	Icon icon;
	JOptionPane jOptionPane;
	Icon pecaVazia;
	JButton posicao = null;
	
	public InterfacePrincipal(){
		super();
		start();
	}
	
	private void start() {
		this.setSize(680, 600);
		this.setLocationRelativeTo(null);
		this.setContentPane(getJContentPane());
		this.setTitle("Ligue 4 - Antonio Taha 12200988, Elanne Souza 10101180");

		this.tabuleiro = new Tabuleiro();
		this.jogador1Vez = true;
		this.jogador2Vez = false;
	}

	private JPanel getJContentPane(){
		//teste
		preta = new ImageIcon(getClass().getResource("peca-Preta.png"));
		branca = new ImageIcon(getClass().getResource("peca-Branca.png"));
		pecaVazia = new ImageIcon(getClass().getResource("posicaoVazia.png"));
		jContentPane = new JPanel();
		jContentPane.setLayout(null);
		int x;
		int y = 40;
		for(int i=0; i<6; i++){
			x = 20;
			for(int j=0; j<7; j++){
				posicao = new JButton();
				posicao.setBounds(new Rectangle(x, y, 90, 70));
				posicao.setIcon(pecaVazia);		
				posicao.addActionListener(this);
				if(i!=5)
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
	
	private JMenuItem getMenuIniciar(){
		if (menuItemIniciar == null) {
			menuItemIniciar = new JMenuItem();
			menuItemIniciar.setText("Iniciar");
			menuItemIniciar.addActionListener(this);
		}
		return menuItemIniciar;
	}
	
	public void selecionaPosicao(int linha, int coluna) {
		if(tabuleiro.getTabuleiro()[linha][coluna]==0){
			if(jogador1Vez)
				mapaPosicao[(linha)][(coluna)].setIcon(preta);
			else
				mapaPosicao[(linha)][(coluna)].setIcon(branca);
			if(linha!=0)
				mapaPosicao[(linha-1)][(coluna)].setEnabled(true);
			if(jogador1Vez){
				tabuleiro.setTabuleiroJogador(linha, coluna);
				jogador1Vez = false;
				jogador2Vez = true;
			}else{
				tabuleiro.setTabuleiroComputador(linha, coluna);
				jogador1Vez = true;
				jogador2Vez = false;
			}
			verificaVencedor(linha, coluna);
			
		}
		
	}

	private void verificaVencedor(int linha, int coluna) {
		boolean corIgual = true;
		int temp = linha;
		int cont=1;
		int cor;
		if(!jogador1Vez)
			cor = 1;
		else
			cor = 2;
		
		//verifica vertical
		while(corIgual && temp<5){
			temp++;
			if(tabuleiro.getTabuleiro()[temp][coluna]==cor)
				cont++;
			else
				corIgual = false;
		}	
		verificaQuemVenceu(cont, cor);
		cont=1;
		temp = coluna;
		
		//verifica horizontal esquerda
		while(corIgual && temp<6){
			temp++;
			if(tabuleiro.getTabuleiro()[linha][temp]==cor)
				cont++;
			else
				corIgual = false;
			System.out.println(cont);
			
		}
		temp = coluna;
		System.out.println("teste  "+temp);
		//verifica horizontal esquerda
		while(corIgual && temp>=0){
			temp--;
			if(tabuleiro.getTabuleiro()[linha][temp]==cor)
				cont++;
			else
				corIgual = false;
			System.out.println("aaa"+cont);
			
		}
		
		verificaQuemVenceu(cont, cor);
			
		
		
	}

	private void verificaQuemVenceu(int cont, int cor) {
		if(cont>=4 && cor==1)
			JOptionPane.showMessageDialog(this, "Você venceu!");
		else if(cont>=4 && cor==2)
			JOptionPane.showMessageDialog(this, "Computador venceu!");
		
		
	}

	private void verificaVertical(boolean corIgual, int temp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == menuItemIniciar){
			this.start();
			this.pack();
			this.setSize(680, 600);
			
		}
		for (int i = 0; i < 6; i++){
			for (int j = 0; j < 7; j++){
				if (e.getSource() == mapaPosicao[i][j]){
					selecionaPosicao(i, j);
				}
			}
		}
	}

}
