package mario;

import jge2d.AnimacaoSprite;
import jge2d.Ponto;
import jge2d.Sprite;

public class PlataformaFixaSimples {

	public static final String NORMAL = "NORMAL";
	
	private Ponto posicaoInicial;
	private Nivel nivel;
	private Sprite sprite;
	
	public PlataformaFixaSimples(Ponto posicaoInicial, Nivel nivel, long tempo){
		this.posicaoInicial = posicaoInicial;
		this.nivel = nivel;
		
		sprite = new Sprite("PlataformaFixaSimples", this, posicaoInicial);
		
		sprite.adicionarAnimacao(NORMAL, new AnimacaoSprite(nivel.getTilesPlataformaFixaSimples(), new int[] {}, 
				new int [] {1, 2, 3, 4}, new int[] {}, 0, 100, 0));
		
		sprite.setAnimacao(NORMAL, false, tempo);
		
		nivel.getMapa().addSprite(sprite);	
	}
	
	public Ponto getPosicaoPlataforma(){
		return posicaoInicial;
	}
	
}
