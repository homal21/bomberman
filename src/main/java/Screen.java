import javax.swing.*;
import java.awt.*;

public class Screen extends JPanel {
    private CardLayout mCardLayout;
    private game1 game1;
    private Game game;

    public Screen(game1 game1) {
        this.game1 = game1;
        mCardLayout = new CardLayout();
        setLayout(mCardLayout);
        game = new Game(this);
        add(game);
    }

    public game1 getGame() {
        return game1;
    }

}
