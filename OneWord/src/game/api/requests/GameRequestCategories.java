package game.api.requests;

import game.Game;
import game.api.responses.GameResponse;

import java.util.Optional;
import java.util.Set;

public class GameRequestCategories extends GameRequest
{
    @Override
    public int getRequestIdentifier()
    {
        return REQUEST_GET_CATEGORIES;
    }

    @Override
    public GameResponse doRequest(Game game)
    {
        Set<String> categories = game.getCategories();
        return new GameResponse(RESPONSE_CATEGORIES, Optional.of(categories));
    }
}
