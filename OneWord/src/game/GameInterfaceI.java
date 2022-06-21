package game;

import game.api.requests.GameRequest;
import game.api.responses.GameResponse;

/*
 * Abstract class to represent the interface that is playing the game (e.g. console application, mobile phone app, etc)
 */
public interface GameInterfaceI
{
    public GameRequest handleResponse(GameResponse response);
}
