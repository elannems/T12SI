package jogo_antonio_elanne;

public class Tabuleiro {
	int[][]tabuleiro;
	int linhaInserida;
	
	public int getLinhaInserida() {
		return linhaInserida;
	}

	public void setLinhaInserida(int linhaInserida) {
		this.linhaInserida = linhaInserida;
	}

	public Tabuleiro(){
		tabuleiro = new int[6][7];
	}

	public int[][] getTabuleiro() {
		return tabuleiro;
	}
	
    public boolean isLegalMove(int column){
        return tabuleiro[0][column]==0;
    }

	public boolean setTabuleiroJogador(int coluna) {
		for(int i=5;i>=0;i--){
            if(tabuleiro[i][coluna] == 0) {
            	tabuleiro[i][coluna] = 2;
                return true;
            }
        }
        return false;
		//this.tabuleiro[linha][coluna] = 1;
	}
	
	public boolean setTabuleiroComputador(int coluna) {
		for(int i=5;i>=0;i--){
            if(tabuleiro[i][coluna] == 0) {
            	tabuleiro[i][coluna] = 1;
            	linhaInserida = i;
                return true;
            }
        }
        return false;
		//this.tabuleiro[linha][coluna] = 2;
	}
	
    public void undoMove(int column){
        for(int i=0;i<=5;++i){
            if(tabuleiro[i][column] != 0) {
            	tabuleiro[i][column] = 0;
                break;
            }
        }        
    }
  
	
}
