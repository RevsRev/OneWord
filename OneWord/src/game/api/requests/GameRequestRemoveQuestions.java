package game.api.requests;

import game.Game;
import game.api.responses.GameResponse;
import game.util.Pair;

import java.util.Optional;
import java.util.Set;

public class GameRequestRemoveQuestions extends GameRequest
{
    @Override
    public int getRequestIdentifier()
    {
        return REQUEST_REMOVE_QUESTIONS;
    }

    @Override
    public GameResponse doRequest(Game game)
    {
        if (data.isEmpty())
        {
            return new GameResponse(RESPONSE_REMOVE_QUESTIONS, Optional.empty());
        }

        Pair<String, Set<String>> categoryAndQuestions = (Pair<String, Set<String>>)data.get();

        Optional<String> first = categoryAndQuestions.getFirst();
        Optional<Set<String>> second = categoryAndQuestions.getSecond();

        if (first.isEmpty() || second.isEmpty())
        {
            return new GameResponse(RESPONSE_REMOVE_QUESTIONS, Optional.empty());
        }

        game.deleteQuestions(first.get(), second.get());
        return new GameResponse(RESPONSE_REMOVE_QUESTIONS, Optional.empty());
    }
}
