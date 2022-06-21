package game.categories;

import game.categories.implementations.CategoryCountryToCapitalCity;
import game.categories.implementations.CategoryStateToCapital;

public final class CategoryFactory implements CategoryTypesI
{
    public static AbstractCategory factory(String identifier)
    {
        switch(identifier)
        {
            case CATEGORY_NAME_COUNTRIES_TO_CAPTIALS:
                return new CategoryCountryToCapitalCity();
            case CATEGORY_NAME_STATES_TO_CAPITALS:
                return new CategoryStateToCapital();
        }

        return null;
    }
}
