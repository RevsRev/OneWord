package game.api.requests;

import game.Game;
import game.api.responses.GameResponse;

public class GameRequestStop extends GameRequest
{

    @Override
    public int getRequestIdentifier()
    {
        return REQUEST_STOP;
    }

    @Override
    public GameResponse doRequest(Game game)
    {
        return new GameResponse(RESPONSE_STOP, null);
        //todo - change data to be an optional
    }
}
