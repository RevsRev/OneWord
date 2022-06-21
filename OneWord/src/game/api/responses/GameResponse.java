package game.api.responses;

import java.util.Optional;

public class GameResponse
{
    private final int responseIdentifier;
    private final Optional data;

    public GameResponse(int responseIdentifier, Optional data)
    {
        this.responseIdentifier = responseIdentifier;
        this.data = data;
    }

    public int getResponseIdentifier()
    {
        return responseIdentifier;
    }
    public Optional getData() { return data;}
}
