package game.api;

import game.api.requests.GameRequest;
import game.api.responses.GameResponse;

public interface RespondableI
{
    public GameRequest handleResponse(GameResponse response);
}
