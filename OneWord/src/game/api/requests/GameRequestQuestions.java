package game.api.requests;

import game.Game;
import game.api.responses.GameResponse;
import game.util.Pair;

import java.util.Optional;
import java.util.Set;

public class GameRequestQuestions extends GameRequest
{

    @Override
    public int getRequestIdentifier()
    {
        return REQUEST_GET_QUESTIONS;
    }

    @Override
    public GameResponse doRequest(Game game)
    {
        if (data.isEmpty())
        {
            return new GameResponse(RESPONSE_QUESTIONS, Optional.empty());
        }

        Pair<String, Integer> questions = (Pair<String, Integer>) data.get();
        Optional<String> category = questions.getFirst();
        Optional<Integer> numQuestions = questions.getSecond();

        int num;
        if (category.isEmpty()
          || numQuestions.isEmpty() || (num = numQuestions.get()) <= 0)
        {
            return new GameResponse(RESPONSE_QUESTIONS, Optional.empty());
        }

        String cat = category.get();
        Set<String> questionsSet = game.getQuestions(cat, num);
        return new GameResponse(RESPONSE_QUESTIONS, Optional.of(questionsSet));
    }
}
