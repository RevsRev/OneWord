package game.api;

public interface GameApiConstantsI
{
    /*
     * Requests
     */
    public final int REQUEST_START = 0;
    public final int REQUEST_STOP = 1;
    public final int REQUEST_GET_CATEGORIES = 2;
    public final int REQUEST_CATEGORY = 3;
    public final int REQUEST_GET_QUESTIONS = 4;
    public final int REQUEST_CHECK_ANSWERS = 5;
    public final int REQUEST_REMOVE_QUESTIONS = 6;


    /*
     * Responses
     */
    public final int RESPONSE_START = 0;
    public final int RESPONSE_STOP = 1;
    public final int RESPONSE_CATEGORIES = 2;
    public final int RESPONSE_CATEGORY = 3;
    public final int RESPONSE_QUESTIONS = 4;
    public final int RESPONSE_CHECK_ANSWERS = 5;
    public final int RESPONSE_REMOVE_QUESTIONS = 6;
}
