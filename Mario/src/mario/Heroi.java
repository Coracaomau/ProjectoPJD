package mario;

import jge2d.AnimacaoSprite;
import jge2d.Ponto;
import jge2d.ResultadoColisao;
import jge2d.Sprite;
import jge2d.Teclado;
import jge2d.TileSet;
import jge2d.jogo.movimentos.MovimentoHeroiLivre;
import jge2d.jogo.movimentos.MovimentoHeroiSuportado;
import jge2d.jogo.movimentos.MovimentoVelocidadeComSentido;
import jge2d.jogo.movimentos.MovimentoVerticalBidireccional;

public class Heroi {

   public static final String ESQUERDA = "ESQUERDA";
   public static final String DIREITA = "DIREITA";
   public static final String PARADO = "PARADO";   
   public static final String PARADO_DIREITA = "PARADODIREITA";
   public static final String SALTAR = "SALTAR";
   public static final String SALTAR_DIREITA = "SALTAR_DIREITA";
   
   private Sprite sprite;
   private Ponto posicaoInicial;
   private int velocidadeAndar;
   private Ponto vectorGravidade;
   private int velocidadeSalto;
   private Nivel nivel;
   private String direcao;
   private String estado;

   private MovimentoVelocidadeComSentido movimento;

   private boolean morreu = false;

   public Heroi(Ponto posicaoInicial,  int velocidadeAndar, Ponto vectorGravidade, int velocidadeSalto, Nivel nivel, long tempo){

      this.posicaoInicial = posicaoInicial;
      this.velocidadeAndar = velocidadeAndar;
      this.vectorGravidade = vectorGravidade;
      this.velocidadeSalto = velocidadeSalto;
      this.nivel = nivel;

      TileSet imagens = nivel.getTilesHeroi();

      //Cria o movimento para o herói
      //movimento = new MovimentoHeroiSuportado(posicaoInicial, velocidadeAndar, tempo);
      movimento = new MovimentoHeroiLivre(posicaoInicial, vectorGravidade, velocidadeAndar, 
    		  velocidadeSalto, tempo);

      //Cria uma sprite (representação gráfica) para o Herói
      sprite = new Sprite("heroi", this, movimento);

      //Define as animações para o Herói
      sprite.adicionarAnimacao(ESQUERDA, new AnimacaoSprite(imagens, new int[] {}, new int[] {1,2,3}, new int[] {}, 0,100,0));
      sprite.adicionarAnimacao(DIREITA, new AnimacaoSprite(imagens, new int[] {}, new int[] {4,5,6}, new int[] {}, 0,100,0));
      sprite.adicionarAnimacao(PARADO, new AnimacaoSprite(imagens, new int[] {}, new int[] {1}, new int[] {}, 0,10000,0));
      sprite.adicionarAnimacao(PARADO_DIREITA, new AnimacaoSprite(imagens, new int[] {}, new int[] {4}, new int[] {}, 0,10000,0));
      sprite.adicionarAnimacao(SALTAR, new AnimacaoSprite(imagens, new int[] {}, new int[] {10, 11, 12}, new int[] {}, 0,100,0));
      sprite.adicionarAnimacao(SALTAR_DIREITA, new AnimacaoSprite(imagens, new int[] {}, new int[] {13, 14, 15}, new int[] {}, 0,100,0));
      
      //Escolhe animação Inicial
      sprite.setAnimacao(PARADO, false, tempo);

      //Avisa o motor que este objecto quer tratar colisões
      sprite.addSpriteColisionListener(this, "tratarColisao");                  
      
      nivel.getMapa().addSprite(sprite);
         
   }
   
   public void iterar (long tempo) {
	   
	   Teclado teclado = nivel.getTeclado();
	   if(teclado != null){
		   
		   if(teclado.isTeclaPressionada(Teclado.DISPARAR)){
			   saltar(tempo);
		   }
		   
		   if(teclado.isTeclaPressionada(Teclado.ESQUERDA)){
			   moverEsquerda(tempo);
		   } else if (teclado.isTeclaPressionada(Teclado.DIREITA)){
			   moverDireita(tempo);
		   }else{
			   parar(tempo);
		   }
	   }
   
   }

   public void moverDireita(long tempo) {
      //Altera o sentido de deslocamento de forma a se deslocar para a direita
      movimento.setSentidoDeslocamento(MovimentoHeroiSuportado.SENTIDO_DIREITA);
      //Altera a animação actual para a animação "direita"
      if (direcao != SALTAR_DIREITA){
    	  if(direcao != DIREITA){
    		  estado = "mover";
    		  sprite.setAnimacao(DIREITA, true, tempo);
    		  direcao = DIREITA;
    	  }
      }
      if (direcao == SALTAR){
    	  if(direcao != ESQUERDA){
    		  estado = "saltar";
    		  sprite.setAnimacao(SALTAR, true, tempo);
    		  direcao = ESQUERDA;
    	  }
      }
   }
   public void moverEsquerda(long tempo) {
      movimento.setSentidoDeslocamento(MovimentoHeroiSuportado.SENTIDO_ESQUERDA);
      if (direcao != SALTAR){
    	  if(direcao != ESQUERDA){
    		  estado = "mover";
    		  sprite.setAnimacao(ESQUERDA, true, tempo);
    		  direcao = ESQUERDA;
    	  }
      }
      if (direcao == SALTAR){
    	  if(direcao != DIREITA){
    		  estado = "saltar";
    		  sprite.setAnimacao(SALTAR_DIREITA, true, tempo);
    		  direcao = DIREITA;
    	  }
      }
   }
   
   public void parar(long tempo) {
      movimento.setSentidoDeslocamento(MovimentoHeroiSuportado.PARADO);
      if(direcao != PARADO){
    	  if(direcao == ESQUERDA){
    		  sprite.setAnimacao(PARADO, true, tempo);
    		  direcao = PARADO;
    	  }
    	  if(direcao == DIREITA){
    		  sprite.setAnimacao(PARADO_DIREITA, true, tempo);
    		  direcao = PARADO_DIREITA;
    	  }
      }
   }
   
   public void saltar(long tempo) {
	   
	   if (estado != "saltar"){
	   	movimento = new MovimentoHeroiLivre(movimento.getUltimaPosicao(),
				vectorGravidade, velocidadeAndar, velocidadeSalto, tempo);
	   	sprite.setMovimento(movimento);
	   	((MovimentoHeroiLivre) movimento).saltar();
	   	estado = "saltar";	
	   	
	    	 if(direcao == ESQUERDA || direcao == PARADO){
	    		  sprite.setAnimacao(SALTAR, true, tempo);
	    		  direcao = SALTAR;
	    	}
	    	 if(direcao == DIREITA || direcao == PARADO_DIREITA){
	    		 sprite.setAnimacao(SALTAR_DIREITA, true, tempo);
	    		 direcao = SALTAR_DIREITA;
	    	}
	   }
   }
   
   public void setPosicaoActual(Ponto posicaoHeroi, long tempoActualizacao){
	  movimento.setPosicaoRelativa(movimento, tempoActualizacao);
	  posicaoHeroi = posicaoInicial;
   }
   
   public void pararSalto(){
	   
   }
   
   public Ponto calcularPosicao(long tempo){
	   return posicaoInicial;
   }
   
   public Sprite getSprite() {
      return sprite;
   }
   
   public void morrer() {
      morreu=true;
   }
   
   //public void trataColisao(InimigoTerrestreMovel inimigo, ResultadoColisao resultado) {
     // morrer();
   //}
   
   public void tratarColisao(PlataformaFixaSimples plataforma, ResultadoColisao resultado){
	   if(resultado.resultadoObjecto1.tipoColisao == ResultadoColisao.COLISAO_POR_CIMA){
		   movimento = new MovimentoHeroiSuportado(resultado.resultadoObjecto1.pontoArraste, 
				   velocidadeAndar, resultado.tempoColisao);
		   sprite.setMovimento(movimento);
	   }else{
		   movimento.setPosicaoActual(resultado.resultadoObjecto1.pontoArraste, resultado.tempoColisao);
	   }
	   if(movimento.getUltimaPosicao() > plataforma.getPosicaoPlataforma()){
		   movimento = new MovimentoHeroiLivre(movimento.getUltimaPosicao(),
					vectorGravidade, velocidadeAndar, velocidadeSalto, tempo);
		   	sprite.setMovimento(movimento);
	   }
   }
   
   public void reiniciar(long tempo){
	   movimento.setPosicaoActual(posicaoInicial, tempo);
   }
}
