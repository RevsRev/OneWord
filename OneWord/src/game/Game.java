package game;

import game.api.*;
import game.api.requests.GameRequest;
import game.api.responses.GameResponse;
import game.categories.CategoryCache;
import game.util.Pair;

import java.util.HashMap;
import java.util.Set;

public class Game implements    GameApiConstantsI,
                                RequestableI
{
    private final CategoryCache categoryCache;

    public Game()
    {
        categoryCache = new CategoryCache();
    }

    public GameResponse handleRequest(GameRequest request)
    {
        return request.doRequest(this);
    }

    public Set<String> getCategories()
    {
        return categoryCache.getCategories();
    }
    public Set<String> getQuestions(String category, int numQuestions)
    {
        return categoryCache.getQuestions(category, numQuestions);
    }

    public HashMap<String, Pair<String, Boolean>> checkAnswers(String category, HashMap<String, String> hmQuestionToAnswer)
    {
        return categoryCache.checkAnswers(category, hmQuestionToAnswer);
    }

    public void deleteQuestions(String category, Set<String> questions)
    {
        categoryCache.deleteQuestions(category, questions);
    }
}
