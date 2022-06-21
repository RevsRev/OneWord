package game.console_application;

import game.GameInterfaceI;
import game.api.GameApiConstantsI;
import game.api.requests.GameRequest;
import game.api.requests.GameRequestCategories;
import game.api.requests.GameRequestCategory;
import game.api.requests.GameRequestQuestions;
import game.api.responses.GameResponse;
import game.util.Pair;

import javax.lang.model.type.IntersectionType;
import javax.swing.text.html.Option;
import java.util.*;

public class ConsoleGameInterface implements    GameInterfaceI,
                                                GameApiConstantsI
{
    private String category = "";
    private int numQuestionsBatchSize = 10;
    private Set<String> questions;
    @Override
    public GameRequest handleResponse(GameResponse response)
    {
        int responseIdentifier = response.getResponseIdentifier();
        switch (responseIdentifier)
        {
            case (RESPONSE_START):
                return handleStart();
            case (RESPONSE_STOP):
                return handleStop();
            case (RESPONSE_CATEGORIES):
                return handleCategories(response);
            case (RESPONSE_CATEGORY):
                return handleCategory();
            case (RESPONSE_QUESTIONS):
                return handleQuestions(response);
            case (RESPONSE_CHECK_ANSWERS):
                return handleAnswers(response);
            case RESPONSE_REMOVE_QUESTIONS:
                return handleDeleteQuestions();
            default:
                return GameRequest.factory(RESPONSE_STOP);
        }
    }

    public GameRequest handleStart()
    {
        writeLine("Querying API for available question categories...");
        return GameRequest.factory(REQUEST_GET_CATEGORIES);
    }

    public GameRequest handleStop()
    {
        writeLine("Stopping Game.");
        return stopGame();
    }
    public GameRequest stopGame()
    {
        return GameRequest.factory(REQUEST_STOP);
    }

    public GameRequest handleCategories(GameResponse response)
    {
        writeLine("Available categories are:");
        writeLine("");
        Optional data = response.getData();
        Set<String> categories;

        if (data.isEmpty()
            || (categories = (Set<String>)data.get()).isEmpty())
        {
            writeLine("No available categories to play.");
            return stopGame();
        }


        Iterator<String> it = categories.iterator();
        while(it.hasNext())
        {
            writeLine(it.next());
        }

        writeLine("");
        writeLine("Please select a category to play.");
        writeLine("Your choice: ");
        String userInput = readLine();
        while (!categories.contains(userInput))
        {
            writeLine("Invalid category selected, please try again.");
            writeLine("Your choice: ");
            userInput = readLine();
        }

        category = userInput;
        return GameRequest.factory(REQUEST_CATEGORY);
    }

    public GameRequest handleCategory()
    {
        return getQuestionsRequest();
    }

    private GameRequest getQuestionsRequest()
    {
        Pair<String, Integer> data = new Pair<String, Integer>(category, numQuestionsBatchSize);
        GameRequest request = GameRequest.factory(GameApiConstantsI.REQUEST_GET_QUESTIONS);
        request.setData(data);
        return request;
    }

    public GameRequest handleQuestions(GameResponse response)
    {
        Optional data = response.getData();
        if (data.isEmpty()
          || (questions = (Set<String>)data.get()).isEmpty())
        {
            writeLine("No Questions to ask for this category!");
            return GameRequest.factory(REQUEST_GET_CATEGORIES);
        }

        //Now let's play the game with some questions!
        return playGame();
    }
    private GameRequest playGame()
    {
        if (questions == null || questions.isEmpty())
        {
            return getQuestionsRequest();
        }

        Iterator<String> it = questions.iterator();
        String question = it.next();
        writeLine(question);
        String ans = readLine();

        return getAnswerRequest(question, ans);
    }

    private GameRequest handleAnswers(GameResponse response)
    {
        Optional<HashMap<String, Pair<String,Boolean>>> checks = (Optional<HashMap<String, Pair<String, Boolean>>>) response.getData();
        if (checks.isEmpty())
        {
            //todo- error handling!
            writeLine("Something went wrong. Select a category to play again.");
            return GameRequest.factory(REQUEST_GET_CATEGORIES);
        }

        HashSet<String> questionsCorrect = new HashSet<String>();

        HashMap<String, Pair<String, Boolean>> correctAnswers = checks.get();
        Iterator<String> itQuestions = correctAnswers.keySet().iterator();
        while (itQuestions.hasNext())
        {
            String question = itQuestions.next();
            Pair<String, Boolean> answers = correctAnswers.get(question);
            if  (answers.getSecond().isPresent() ? answers.getSecond().get() : false)
            {
                writeLine("Correct!");
                questionsCorrect.add(question);
            }
            else
            {
                writeLine("Incorrect! The correct answer was " + answers.getFirst().get());
            }
            questions.remove(question);
        }
        return getDeleteRequest(questionsCorrect);
    }
    private GameRequest getDeleteRequest(Set<String> questions)
    {
        GameRequest delete = GameRequest.factory(REQUEST_REMOVE_QUESTIONS);
        delete.setData(new Pair<String,Set<String>>(category, questions));
        return delete;
    }

    private GameRequest handleDeleteQuestions()
    {
        //just continue playing
        return playGame();
    }

    private GameRequest getAnswerRequest(String question, String ans)
    {
        HashMap<String, String> hmQuestionToAnswer = new HashMap<String, String>();
        hmQuestionToAnswer.put(question, ans);
        return getAnswerRequest(hmQuestionToAnswer);
    }
    private GameRequest getAnswerRequest(HashMap<String, String> hmQuestionToAnswer)
    {
        GameRequest ansRequest = GameRequest.factory(REQUEST_CHECK_ANSWERS);
        ansRequest.setData(new Pair<String, HashMap<String,String>>(category, hmQuestionToAnswer));
        return ansRequest;
    }

    private void writeLine(String s)
    {
        if (System.console() != null)
        {
            System.console().writer().println(s);
            return;
        }

        System.out.println(s);
    }
    private String readLine()
    {
        if (System.console() != null)
        {
            return System.console().readLine();
        }

        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
