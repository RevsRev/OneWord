package game.api.requests;

import game.Game;
import game.api.GameApiConstantsI;
import game.api.responses.GameResponse;

import java.util.Optional;

public abstract class GameRequest implements GameApiConstantsI
{
    Optional<Object> data = Optional.empty();
    public abstract int getRequestIdentifier();
    public abstract GameResponse doRequest(Game game);

    protected GameRequest(){}
    public static GameRequest factory(int requestIdentifier)
    {
        switch(requestIdentifier)
        {
            case REQUEST_START:
                return new GameRequestStart();
            case REQUEST_GET_CATEGORIES:
                return new GameRequestCategories();
            case REQUEST_CATEGORY:
                return new GameRequestCategory();
            case REQUEST_GET_QUESTIONS:
                return new GameRequestQuestions();
            case REQUEST_CHECK_ANSWERS:
                return new GameRequestAnswer();
            case REQUEST_REMOVE_QUESTIONS:
                return new GameRequestRemoveQuestions();
            default:
                return new GameRequestStop();
        }
    }

    public void setData(Object data)
    {
        this.data = Optional.of(data);
    }
    public Optional<Object> getData()
    {
        return data;
    }
}
