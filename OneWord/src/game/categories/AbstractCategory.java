package game.categories;

import game.util.Pair;

import javax.swing.text.html.Option;
import java.io.*;
import java.sql.Array;
import java.util.*;

public abstract class AbstractCategory implements CategoryTypesI
{
    //private int versionNumber;

    //as we ask questions, remove things from the hm so we don't ask them again!
    private HashMap<String, String> hmQuestionToAnswer = new HashMap<String, String>();
    private Random random = new Random();

    protected abstract String categoryName();
    protected abstract String sourceFilePath();
    protected abstract int sourceFileQuestionColumn();
    protected abstract int sourceFileAnswerColumn();
    protected abstract String preQuestionStr();
    protected abstract String postQuestionStr();

    //public AbstractCategory(int versionNumber)
    public AbstractCategory()
    {
        //this.versionNumber = versionNumber;
        init();
    }

    private void init()
    {
        File cacheFile = getCacheFile();
        initQuestions(cacheFile);
    }

    private File getCacheFile()
    {
        return new File(sourceFilePath());
    }
    private void initQuestions(File cacheFile)
    {
        System.out.println("Initialising questions for category " + categoryName());
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(cacheFile));
            String line = reader.readLine();
            while (line != null)
            {
                String[] datas = line.split(",");
                String question = datas[sourceFileQuestionColumn()];
                String formattedQuestion = getFormattedQuestion(question);
                String answer = datas[sourceFileAnswerColumn()];
                hmQuestionToAnswer.put(formattedQuestion, answer);
                line = reader.readLine();
            }
            reader.close();
            System.out.println("Finished initialising questions for category " + categoryName());
            return;
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        System.out.println("Failed to initialise questions for category " + categoryName());
    }

    public HashMap<String, Pair<String, Boolean>> checkAnswers(HashMap<String, String> hmUserQuestionToAnswer)
    {
        HashMap<String, Pair<String, Boolean>> retval = new HashMap<String, Pair<String, Boolean>>();

        Iterator<String> itQuestions = hmUserQuestionToAnswer.keySet().iterator();
        while (itQuestions.hasNext())
        {
            String question = itQuestions.next();
            String userAnswer = hmUserQuestionToAnswer.get(question);
            String correctAnswer = hmQuestionToAnswer.get(question);

            //User submitted a question we don't store here. Something gone wrong
            //todo - can definitely do bettere error handling here!
            if (correctAnswer == null)
            {
                retval.put(question, new Pair<String, Boolean>("", false));
            }
            else {
                retval.put(question, new Pair<String, Boolean>(correctAnswer, correctAnswer.equalsIgnoreCase(userAnswer)));
            }
        }
        return retval;
    }

    //e.g. if the user gets the question right, we don't want to ask it again.
    public void removeQuestion(String question)
    {
        hmQuestionToAnswer.remove(question);
    }

    public HashSet<String> getRandomQuestions(int numQuestions)
    {
        HashMap<String, String> hmQuestionToAnswerCopy = new HashMap<String, String>(hmQuestionToAnswer);
        int numQuestionsToGet = Math.min(hmQuestionToAnswerCopy.size(), numQuestions);
        HashSet<String> retval = new HashSet<String>();


        while (retval.size() < numQuestionsToGet)
        {
            String question = getRandomQuestion(hmQuestionToAnswerCopy);
            retval.add(question);
        }
        return retval;
    }
    private String getRandomQuestion(HashMap<String, String> hmQuestionToAnswerCopy)
    {
        int num = random.nextInt(hmQuestionToAnswerCopy.size() -1);
        Iterator<String> it = hmQuestionToAnswerCopy.keySet().iterator();
        int numIterations = 0;
        while (numIterations < num)
        {
            it.next();
            numIterations++;
        }
        return it.next();
    }
    private String getFormattedQuestion(String question)
    {
        return preQuestionStr() + question + postQuestionStr();
    }

    public void deleteQuestions(Set<String> questions)
    {
        Iterator<String> it = questions.iterator();
        while(it.hasNext())
        {
            removeQuestion(it.next());
        }
    }
}
