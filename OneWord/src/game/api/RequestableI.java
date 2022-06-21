package game.api;

import game.api.requests.GameRequest;
import game.api.responses.GameResponse;

public interface RequestableI
{
    public GameResponse handleRequest(GameRequest request);
}
