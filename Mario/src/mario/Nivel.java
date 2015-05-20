/**
 * 
 */
package mario;

import java.awt.Component;
import java.util.Iterator;
import java.util.LinkedList;

import jge2d.INivel;
import jge2d.ISprite;
import jge2d.Ponto;
import jge2d.Rectangulo;
import jge2d.SpritedTileMap;
import jge2d.Teclado;
import jge2d.TileSet;

/**
 * @author Gustavo
 *
 */
public class Nivel implements INivel {
	
	private SpritedTileMap mapa;
	
	private Heroi heroi;

	private Teclado teclado;
	
	private TileSet tilesHeroi;

	private TileSet tilesPlataformaFixaSimples;
	
	private TileSet tilesPlataformaMovelVertical;
	
	private TileSet tilesPlataformaMovelHorizontal;
	
	private TileSet tilesInimigoTerrestreFixo;
	
	private TileSet tilesInimigoTerrestreMovel;
	
	private TileSet tilesInimigoVoador;
	
	public Nivel(){
		
	}

	/**
     * Cria todos os TileSets que serao necessarios ao longo do jogo
     * @param comp Componente para o qual as imagens s�o optimizadas
     */
    public void createTileSets(Component componente){
    	
    	tilesHeroi = new TileSet("/tiles/heroi.gif", componente, 20, 31);
    	
    	tilesPlataformaFixaSimples = new TileSet("/tiles/plataformaFixaSimples.gif", componente, 64, 16);
    	
    	tilesPlataformaMovelVertical = new TileSet("/tiles/plataformaMovelVertical.gif", componente, 90, 16);
    	
    	tilesPlataformaMovelHorizontal = new TileSet("/tiles/plataformaMovelHorizontal.gif", componente, 90, 16);
    	
    	tilesInimigoTerrestreFixo = new TileSet("/tiles/inimigoTerrestreFixo.gif", componente, 24, 33);
    	
    	tilesInimigoTerrestreMovel = new TileSet("/tiles/inimigoTerrestreMovel.gif", componente, 30, 28);
    	
    	tilesInimigoVoador = new TileSet("/tiles/inimigoVoador.gif", componente, 42, 21);
    }


    /**
     * Repete-se em cada iteracao do jogo
     * @param actualTime o tempo actual do jogo
     * @param areaVisivel rectangulo que define a area visivel do mapa
     * @return NAO_PERDEU caso nao tenha terminado e PERDEU_VIDA caso tenha perdido uma vida
     */
    public int iterar(long actualTime, Rectangulo areaVisivel){
    	heroi.iterar(actualTime);
    	
    	
    	return NAO_PERDEU;
    }

    /**
     * Accionado sempre que uma tecla e pressionada
     * @param teclado estado actual do teclado
     * @param tecla tecla que provocou a alteracao do teclado
     * @param tempo instante em que ocorreu a alteracao
     */
    public void keyPressed(Teclado teclado, int tecla, long tempo){
    	this.teclado = teclado;
    }

    /**
     * Accionado sempre que uma tecla e libertada
     * @param teclado estado actual do teclado
     * @param tecla tecla que provocou a alteracao do teclado
     * @param tempo instante em que ocorreu a alteracao
     */
    public void keyReleased(Teclado teclado, int tecla, long tempo){
    	this.teclado = teclado;
    }

    /**
     * A referencia do mapa e alterada para a referencia especificada
     * @param sprites a nova referencia do mapa
     */
    public void setMap(SpritedTileMap sprites){
    	mapa = sprites;    	
    }

    /**
     * Reinicia o jogo colocando o nivel no seu estado inicial
     * @param tempo o instante em que o nivel deve ser reiniciado
     */
    public void reiniciar(long tempo){
    	heroi.reiniciar(tempo);
    }


    /**
     * Devolve numero de pontos que o jogador conseguiu obter.
     * @return pontuacao obtida pelo jogador
     */
    public long getPontuacao(){
    	return 0L; //alterar
    }

    /**
     * Devolve numero de vidas de que o jogador ainda disp�e.
     * @return vidas do jogador
     */
    public int getVidas(){
    	return 3; //alterar
    }   

    /**
     * Adiciona uma PlataformaIndestrutivel ao mapa, numa determinada posicao, num instante especifico
     *
     * @param posicaoInicial posicao inicial da bola
     * @param tempoActual instante de tempo actual
     */
    public void adicionarPlataformaFixaSimples(Ponto posicaoInicial, long tempoActual){
    	new PlataformaFixaSimples(posicaoInicial, this, tempoActual);
    }

    /**
     * Adiciona uma Escada ao mapa, numa determinada posicao, num instante especifico
     *
     * @param posicaoInicial posicao inicial da bola
     * @param tempoActual instante de tempo actual
     */
    public void adicionarEscada(Ponto posicaoInicial, long tempoActual){
     	
    }
    
    public void adicionarPlataformaMovelVertical(Ponto posicaoInicial, double deslocamentoY, double velocidade, long tempo) {
    	new PlataformaMovelVertical(posicaoInicial, this, deslocamentoY, velocidade, tempo);
	}

	public void adicionarPlataformaMovelHorizontal(Ponto posicaoInicial, double deslocamentoX, double velocidade, long tempo) {
		new PlataformaMovelHorizontal(posicaoInicial, this, deslocamentoX, velocidade, tempo);
	}
    
    public SpritedTileMap getMapa(){
    	return mapa;
    }
    
    public TileSet getTilesHeroi(){
    	return tilesHeroi;
    }
    
	
	public Teclado getTeclado(){
		return teclado;
	}
	
	public Heroi getHeroi(){
		return heroi;
	}
	
	
	public ISprite adicionarHeroi(Ponto posicaoInicial, int velocidade, Ponto vectorGravidade, int velocidadeSalto, int nVidas, long tempoActual) {
		heroi = new Heroi(posicaoInicial, velocidade, vectorGravidade , velocidadeSalto, this, tempoActual);
    	 
    	return heroi.getSprite();
	}

	public void adicionarPlataformaRolanteDireita(Ponto posicaoInicial, double velocidade, long tempo) {
		
	}
	
	public void adicionarPlataformaRolanteEsquerda(Ponto posicaoInicial, double velocidade, long tempo) {
		
	}

	public void adicionarPlataformaSoluvel(Ponto posicaoInicial, int iteracoes, long tempo) {
		
	}

	public void adicionarPlataformaFixaSuspensa(Ponto posicaoInicial, long tempoActual) {
		
	}

	public void adicionarPlataformaMovelVerticalSuspensa(Ponto posicaoInicial, double deslocamentoY, double velocidade, long tempo) {
			
	}

	public void adicionarPlataformaMovelHorizontalSuspensa(Ponto posicaoInicial, double deslocamentoX, double velocidade, long tempo) {
		
	}

	public void adicionarMoeda(Ponto posicaoInicial, long tempoActual) {
			
	}

	public void adicionarInimigoVoador(Ponto posicaoInicial, double deslocamentoY, double velocidade, long tempo) {
		new InimigoVoador(posicaoInicial, this, deslocamentoY, velocidade, tempo);
	}

	public void adicionarInimigoTerrestreMovel(Ponto posicaoInicial, double deslocamentoX, double velocidade, long tempo) {
		new InimigoTerrestreMovel(posicaoInicial, this, deslocamentoX, velocidade, tempo);
	}

	public void adicionarInimigoTerrestreFixo(Ponto posicaoInicial, long tempoActual) {	
		new InimigoTerrestreFixo(posicaoInicial, this, tempoActual);
	}	
	
	public TileSet getTilesPlataformaFixaSimples(){
		return tilesPlataformaFixaSimples;	
	}
	
	public TileSet getTilesPlataformaMovelVertical(){
		return tilesPlataformaMovelVertical;	
	}
	
	public TileSet getTilesPlataformaMovelHorizontal(){
		return tilesPlataformaMovelHorizontal;	
	}
	
	public TileSet getTilesInimigoTerrestreFixo(){
		return tilesInimigoTerrestreFixo;	
	}
	
	public TileSet getTilesInimigoTerrestreMovel(){
		return tilesInimigoTerrestreMovel;	
	}
	
	public TileSet getTilesInimigoVoador(){
		return tilesInimigoVoador;	
	}
}
