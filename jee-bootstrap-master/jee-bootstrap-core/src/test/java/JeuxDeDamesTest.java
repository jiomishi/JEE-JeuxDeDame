

import org.junit.Before;
import org.junit.Test;

import MathieuPack.JeuxDeDamesGame;
import MathieuPack.JeuxDeDamesGameImpl;
import MathieuPack.PawnColor;

import static org.assertj.core.api.Assertions.*;
public class JeuxDeDamesTest {

	private JeuxDeDamesGame game;
	private JeuxDeDamesGame gameForEasyWin;

    @Before
    public void doBefore() throws Exception {
        game = new JeuxDeDamesGameImpl();
        gameForEasyWin = new JeuxDeDamesGameImpl(1);
        
    }

	PawnColor pawnBlack=PawnColor.BLACK;
	PawnColor pawnWhite=PawnColor.WHITE;
	
    
    
    @Test
    public void selectTest() throws Exception{
    	assertThat(game.select(0,0)).isEqualTo(PawnColor.BLACK);
    	assertThat(game.select(5,1)).isEqualTo(PawnColor.BLACK);
    	assertThat(game.select(6,8)).isEqualTo(PawnColor.WHITE);
    	assertThat(game.select(3,9)).isEqualTo(PawnColor.WHITE);
    	
    }
    
    @Test
    public void movement() throws Exception{
    	game.move(0,6,1,5);
    	assertThat(game.select(1,5)).isEqualTo(PawnColor.WHITE);
    	assertThat(game.select(0,6)).isEqualTo(null);
    	game.move(1,3,2,4);
    	assertThat(game.select(2,4)).isEqualTo(PawnColor.BLACK);
    	assertThat(game.select(1,3)).isEqualTo(null);
    	/*verifier si je selectionne rien*/
    	game.move(0,4,1,2);
    	/*verifier si le deplacement est pas possible: trop deplacement n'importe ou, soit un pion à cet endroit là*/
    	game.move(1,5,2,4);
    	game.move(1,5,9,9);
    	
    	
    }
    
    
    
    @Test
    public void takeOnePawn() throws Exception{
    	
    	assertThat(game.countWhite()).isEqualTo(20);
    	game.move(0,6,1,5);
    	game.move(1,5,2,4);
    	game.move(3,3,2,4);
    	assertThat(game.countWhite()).isEqualTo(19);
    	
    }
    
    /*victoire*/
    
    @Test
    public void victoir() throws Exception{
    	assertThat(gameForEasyWin.getWinner()).isNull();
    	gameForEasyWin.move(4,0,5,1);
    	assertThat(gameForEasyWin.getWinner()).isEqualTo(PawnColor.BLACK);
    	
    }
    
    
    /*dame*/
    
    
    /*double take without take back*/
    @Test
    public void take2() throws Exception{
    	game.move(2,6,3,5);
    	game.move(3,5,4,4);
    	game.move(6,6,7,5);
    	game.move(7,5,8,4);
    	game.move(5,7,6,6);
    	game.move(6,6,7,5);
    	/*premier coup noir*/
    	assertThat(game.countWhite()).isEqualTo(20);
    	game.move(5,3,4,4);
    	assertThat(game.countWhite()).isEqualTo(18);
    	game.move(1,3,2,4);
    	game.move(2,4,3,5);
    	/*premier coup Blanc*/
    	assertThat(game.countBlack()).isEqualTo(20);
    	game.move(6,8,5,7);
    	assertThat(game.countBlack()).isEqualTo(18);
    	game.move(7,5,6,4);
    	game.move(7,7,6,6);
    	/*second coup noir*/
    	assertThat(game.countWhite()).isEqualTo(18);
    	game.move(9,3,8,4);
    	assertThat(game.countWhite()).isEqualTo(16);
    	game.move(3,7,4,6);
    	game.move(5,7,6,8);
    	game.move(0,2,1,3);
    	/*troisieme coup noir*/
    	assertThat(game.countWhite()).isEqualTo(16);
    	game.move(1,3,2,4);
    	assertThat(game.countWhite()).isEqualTo(14);
    	game.move(4,8,5,7);
    	game.move(4,2,5,3);
    	/*quatrieme coup noir*/
    	assertThat(game.countWhite()).isEqualTo(14);
    	game.move(5,3,6,4);
    	assertThat(game.countWhite()).isEqualTo(12);
    	game.move(3,3,4,4);
    	game.move(4,4,5,5);
    	game.move(5,5,6,6);
    	/*second coup Blanc*/
    	assertThat(game.countBlack()).isEqualTo(17);
    	game.move(5,9,6,8);
    	assertThat(game.countBlack()).isEqualTo(15);
    	game.move(7,3,6,4);
    	game.move(6,4,5,5);
    	game.move(5,7,4,8);
    	/*troisieme coup Blanc*/
    	assertThat(game.countBlack()).isEqualTo(15);
    	game.move(3,9,4,8);
    	assertThat(game.countBlack()).isEqualTo(13);
    	game.move(6,2,5,3);
    	game.move(5,3,4,4);
    	game.move(7,1,6,2);
    	/*quatrieme coup Blanc*/
    	assertThat(game.countBlack()).isEqualTo(13);
    	game.move(3,5,4,4);
    	assertThat(game.countBlack()).isEqualTo(11);

    }
    
    @Test
    public void dame() throws Exception{
    game.move(2,6,3,5);
	game.move(3,5,4,4);
	game.move(6,6,7,5);
	game.move(7,5,8,4);
	game.move(5,7,6,6);
	game.move(6,6,7,5);
	game.move(5,3,4,4);
	game.move(1,3,2,4);
	game.move(2,4,3,5);
	game.move(6,8,5,7);
	game.move(7,5,6,4);
	game.move(7,7,6,6);
	game.move(9,3,8,4);
	game.move(3,7,4,6);
	game.move(5,7,6,8);
	game.move(0,2,1,3);
	game.move(1,3,2,4);
	game.move(4,8,5,7);
	game.move(4,2,5,3);
	game.move(5,3,6,4);
	game.move(3,3,4,4);
	game.move(4,4,5,5);
	game.move(5,5,6,6);
	game.move(5,9,6,8);
	game.move(7,3,6,4);
	game.move(6,4,5,5);
	game.move(5,7,4,8);
	game.move(4,8,5,9);
	assertThat(game.select(5,9)).isEqualTo(PawnColor.BLACK);
	game.dame();
	assertThat(game.select(5,9)).isEqualTo(PawnColor.DAME_BLACK);
	game.move(5,9,0,4);
	assertThat(game.select(0,4)).isEqualTo(PawnColor.DAME_BLACK);
	game.move(0,4,5,9);
	assertThat(game.countWhite()).isEqualTo(11);
	game.move(8,6,7,7);
	game.move(5,9,9,5);
	assertThat(game.countWhite()).isEqualTo(10);
	game.move(9,7,8,6);
	game.move(9,5,5,9);
	assertThat(game.countWhite()).isEqualTo(9);
	System.out.println(game);
}

}
