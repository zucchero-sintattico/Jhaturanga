package jhaturanga.controllers.gametypemenu;

import jhaturanga.controllers.Controller;
import jhaturanga.model.game.gametypes.GameType;
import jhaturanga.model.player.Player;

public interface GameTypeMenuController extends Controller {

    // TODO: DOCUMENTA TOMMASO!!!

    Player getWhitePlayer();

    Player getBlackPlayer();

    int getNumberOfRow();

    int getNumberOfColumn();

    int getnNumbersOfGameTipes();

    void setGameType(GameType gameType);
}