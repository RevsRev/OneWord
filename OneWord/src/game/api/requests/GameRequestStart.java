package game.api.requests;

import game.Game;
import game.api.responses.GameResponse;

public class GameRequestStart extends GameRequest
{
    @Override
    public int getRequestIdentifier()
    {
        return REQUEST_START;
    }

    @Override
    public GameResponse doRequest(Game game)
    {
        return new GameResponse(RESPONSE_START, null);
        //todo make an optional
    }
}
