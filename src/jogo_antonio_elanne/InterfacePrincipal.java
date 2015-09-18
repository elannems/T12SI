package jogo_antonio_elanne;

import javax.swing.*;

public class InterfacePrincipal extends JFrame {
	protected JPanel jContentPane = null;
	protected JLabel mapaPosicao[][] = new JLabel[8][8];
	private JLabel mensagem = null;
	private JMenu menu = null;
	private JMenuBar menuBar=null;
	private JMenuItem menuItemConectar = null;
	private JMenuItem menuItemIniciar = null;
	private JMenuItem menuItemDesconectar = null;
	
	public ImageIcon imageIcon;
	public Icon icon;
	public JOptionPane jOptionPane;
	
	private JLabel vPosicao00 = null;
	private JLabel vPosicao01 = null;
	private JLabel vPosicao02 = null;	
	private JLabel vPosicao03 = null;
	private JLabel vPosicao04 = null;
	private JLabel vPosicao05 = null;
	private JLabel vPosicao06 = null;
	private JLabel vPosicao07 = null;

	private JLabel vPosicao10 = null;
	private JLabel vPosicao11 = null;
	private JLabel vPosicao12 = null;	
	private JLabel vPosicao13 = null;
	private JLabel vPosicao14 = null;
	private JLabel vPosicao15 = null;
	private JLabel vPosicao16 = null;
	private JLabel vPosicao17 = null;
	
	private JLabel vPosicao20 = null;
	private JLabel vPosicao21 = null;
	private JLabel vPosicao22 = null;	
	private JLabel vPosicao23 = null;
	private JLabel vPosicao24 = null;
	private JLabel vPosicao25 = null;
	private JLabel vPosicao26 = null;
	private JLabel vPosicao27 = null;
	
	private JLabel vPosicao30 = null;
	private JLabel vPosicao31 = null;
	private JLabel vPosicao32 = null;	
	private JLabel vPosicao33 = null;
	private JLabel vPosicao34 = null;
	private JLabel vPosicao35 = null;
	private JLabel vPosicao36 = null;
	private JLabel vPosicao37 = null;
	
	private JLabel vPosicao40 = null;
	private JLabel vPosicao41 = null;
	private JLabel vPosicao42 = null;	
	private JLabel vPosicao43 = null;
	private JLabel vPosicao44 = null;
	private JLabel vPosicao45 = null;
	private JLabel vPosicao46 = null;
	private JLabel vPosicao47 = null;
	
	private JLabel vPosicao50 = null;
	private JLabel vPosicao51 = null;
	private JLabel vPosicao52 = null;	
	private JLabel vPosicao53 = null;
	private JLabel vPosicao54 = null;
	private JLabel vPosicao55 = null;
	private JLabel vPosicao56 = null;
	private JLabel vPosicao57 = null;
	
	private JLabel vPosicao60 = null;
	private JLabel vPosicao61 = null;
	private JLabel vPosicao62 = null;	
	private JLabel vPosicao63 = null;
	private JLabel vPosicao64 = null;
	private JLabel vPosicao65 = null;
	private JLabel vPosicao66 = null;
	private JLabel vPosicao67 = null;
	
	private JLabel vPosicao70 = null;
	private JLabel vPosicao71 = null;
	private JLabel vPosicao72 = null;	
	private JLabel vPosicao73 = null;
	private JLabel vPosicao74 = null;
	private JLabel vPosicao75 = null;
	private JLabel vPosicao76 = null;
	private JLabel vPosicao77 = null;
	
	public InterfacePrincipal(){
		super();
		start();
	}
	
	private void start() {
		this.setSize(764, 680);
		this.setContentPane(getJContentPane());
		this.setTitle("Ligue 4 - Antonio Taha 12200988, Elanne Souza 10101180");
	}
	
	private JPanel getJContentPane(){
		//teste
		Icon pecaVazia = new ImageIcon(getClass().getResource("vazia.png"));
		jContentPane = new JPanel();
		jContentPane.setLayout(null);
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				vPosicao00 = new JLabel();
				vPosicao00.setBounds(new Rectangle(20, 40, 90, 70));
				vPosicao00.setIcon(vazia);				
				vPosicao00.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseClicked(java.awt.event.MouseEvent e) {
						selecionaPosicao(0,0); 
					}
				});
			}
		}
		
	}

}
