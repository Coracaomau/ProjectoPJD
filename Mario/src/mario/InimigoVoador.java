package mario;

import jge2d.AnimacaoSprite;
import jge2d.Ponto;
import jge2d.Sprite;
import jge2d.jogo.movimentos.MovimentoVerticalBidireccional;

public class InimigoVoador {

public static final String NORMAL = "NORMAL";
	
	private Ponto posicaoInicial;
	private Nivel nivel;
	private Sprite sprite;
	private double deslocamentoY;
	private double velocidade;
	private MovimentoVerticalBidireccional movimento;
	
	public InimigoVoador(Ponto posicaoInicial, Nivel nivel, double deslocamentoY, double velocidade, long tempo){
		this.posicaoInicial = posicaoInicial;
		this.nivel = nivel;
		this.deslocamentoY = deslocamentoY;
		this.velocidade = velocidade;
		
		movimento = new MovimentoVerticalBidireccional(posicaoInicial, deslocamentoY, velocidade, tempo);
		
		sprite = new Sprite("InimigoVoador", this, movimento);
		
		sprite.adicionarAnimacao(NORMAL, new AnimacaoSprite(nivel.getTilesInimigoVoador(), new int[] {}, 
				new int [] {1, 2, 3, 4}, new int[] {}, 0, 100, 0));
		
		sprite.setAnimacao(NORMAL, false, tempo);
		
		nivel.getMapa().addSprite(sprite);	
	}

}
