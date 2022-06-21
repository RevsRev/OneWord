package game.api.requests;

import game.Game;
import game.api.responses.GameResponse;
import game.util.Pair;

import java.util.HashMap;
import java.util.Optional;

public class GameRequestAnswer extends GameRequest
{
    @Override
    public int getRequestIdentifier()
    {
        return REQUEST_CHECK_ANSWERS;
    }

    @Override
    public GameResponse doRequest(Game game)
    {
        if (data.isEmpty())
        {
            return new GameResponse(RESPONSE_CHECK_ANSWERS, Optional.empty());
        }

        Pair<String, HashMap<String, String>> categoryAndAnswers = (Pair<String, HashMap<String, String>>)(data.get());
        Optional<String> category = categoryAndAnswers.getFirst();
        Optional<HashMap<String, String>> hmQuestionToAnswers = categoryAndAnswers.getSecond();

        if (category.isEmpty() || hmQuestionToAnswers.isEmpty())
        {
            //todo - could probably do better error handling here
            return new GameResponse(RESPONSE_CHECK_ANSWERS, Optional.empty());
        }

        HashMap<String, Pair<String, Boolean>> checks = game.checkAnswers(category.get(), hmQuestionToAnswers.get());
        return new GameResponse(RESPONSE_CHECK_ANSWERS, Optional.of(checks));
    }
}
