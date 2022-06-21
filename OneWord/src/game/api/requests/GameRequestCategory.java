package game.api.requests;

import game.Game;
import game.api.requests.GameRequest;
import game.api.responses.GameResponse;

import java.util.Optional;
import java.util.Set;

public class GameRequestCategory extends GameRequest
{
    @Override
    public int getRequestIdentifier()
    {
        return REQUEST_CATEGORY;
    }

    @Override
    public GameResponse doRequest(Game game)
    {
        return new GameResponse(RESPONSE_CATEGORY, Optional.empty());
    }
}
