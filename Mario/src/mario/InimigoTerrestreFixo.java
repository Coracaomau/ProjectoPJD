package mario;

import jge2d.AnimacaoSprite;
import jge2d.Ponto;
import jge2d.Sprite;

public class InimigoTerrestreFixo {

public static final String NORMAL = "NORMAL";
	
	private Ponto posicaoInicial;
	private Nivel nivel;
	private Sprite sprite;
	
	public InimigoTerrestreFixo(Ponto posicaoInicial, Nivel nivel, long tempo){
		this.posicaoInicial = posicaoInicial;
		this.nivel = nivel;
		
		sprite = new Sprite("InimigoTerrestreFixo", this, posicaoInicial);
		
		sprite.adicionarAnimacao(NORMAL, new AnimacaoSprite(nivel.getTilesInimigoTerrestreFixo(), new int[] {}, 
				new int [] {1, 2}, new int[] {}, 0, 100, 0));
		
		sprite.setAnimacao(NORMAL, false, tempo);
		
		nivel.getMapa().addSprite(sprite);	
	}
}
