package game.categories;

import game.api.responses.GameResponse;
import game.util.Pair;

import java.util.*;

public class CategoryCache implements CategoryTypesI
{
    private final HashMap<String, AbstractCategory> cache = new HashMap<String, AbstractCategory>();

    public CategoryCache()
    {
        cacheCategory(CATEGORY_NAME_COUNTRIES_TO_CAPTIALS);
        cacheCategory(CATEGORY_NAME_STATES_TO_CAPITALS);
    }
    
    public AbstractCategory getFromCache(String categoryName)
    {
        cacheCategory(categoryName);
        return cache.get(categoryName);
    }

    private void cacheCategory(String categoryName)
    {
        if (!cache.containsKey(categoryName))
        {
            cache.put(categoryName, CategoryFactory.factory(categoryName));
        }
    }

    public Set<String> getCategories()
    {
        return cache.keySet();
    }

    public Set<String> getQuestions(String category, int numQuestions)
    {
        AbstractCategory abstractCategory = cache.get(category);
        if (abstractCategory == null)
        {
            return new HashSet<>();
        }
        return abstractCategory.getRandomQuestions(numQuestions);
    }

    public HashMap<String, Pair<String, Boolean>> checkAnswers(String category, HashMap<String, String> hmQuestionToAnswer)
    {
        AbstractCategory abstractCategory = cache.get(category);
        if (abstractCategory == null)
        {
            return new HashMap<String, Pair<String, Boolean>>();
        }

        return abstractCategory.checkAnswers(hmQuestionToAnswer);
    }

    public void deleteQuestions(String category, Set<String> questions)
    {
        AbstractCategory abstractCategory = cache.get(category);
        if (abstractCategory == null)
        {
            return; //TODO - Error Handling
        }

        abstractCategory.deleteQuestions(questions);
    }
}
