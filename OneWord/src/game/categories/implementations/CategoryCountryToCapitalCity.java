package game.categories.implementations;

import game.categories.AbstractCategory;

public class CategoryCountryToCapitalCity extends AbstractCategory
{
    @Override
    protected String categoryName()
    {
        return CATEGORY_NAME_COUNTRIES_TO_CAPTIALS;
    }

    @Override
    protected String sourceFilePath()
    {
        return CATEGORY_FILE_COUNTRIES_AND_CAPITALS;
    }

    @Override
    protected int sourceFileQuestionColumn()
    {
        return 0;
    }

    @Override
    protected int sourceFileAnswerColumn()
    {
        return 1;
    }

    @Override
    protected String preQuestionStr()
    {
        return "What is the captial city of ";
    }

    @Override
    protected String postQuestionStr()
    {
        return "?";
    }
}
