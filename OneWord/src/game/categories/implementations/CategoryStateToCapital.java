package game.categories.implementations;

import game.categories.AbstractCategory;

public class CategoryStateToCapital extends AbstractCategory
{
    @Override
    protected String categoryName()
    {
        return CATEGORY_NAME_STATES_TO_CAPITALS;
    }

    @Override
    protected String sourceFilePath()
    {
        return CATEGORY_FILE_STATES_AND_CAPTIALS;
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
        return "What is the capital city of the US state ";
    }

    @Override
    protected String postQuestionStr()
    {
        return "?";
    }
}
