package game;

import game.api.GameApiConstantsI;
import game.api.requests.GameRequest;
import game.api.responses.GameResponse;

/*
 * The bridge that connects the GameInterface to the Game being played.
 */
public final class GameInterfaceBridge implements Runnable, GameApiConstantsI
{
    private final Game game;
    private boolean keepGoing = true;
    private final GameInterfaceI gameInterface;

    public GameInterfaceBridge(Game game, GameInterfaceI gameInterface)
    {
        this.game = game;
        this.gameInterface = gameInterface;
    }

    @Override
    public void run()
    {
        GameRequest request = GameRequest.factory(REQUEST_START);
        GameResponse response;
        while (keepGoing)
        {
            response = game.handleRequest(request);
            if (response.getResponseIdentifier() == RESPONSE_STOP)
            {
                stop();
            }

            request = gameInterface.handleResponse(response);
        }
    }

    public void stop()
    {
        keepGoing = false;
    }
}
