import java.util.ArrayList;

public class ApplicationParameters
{
    public static final String MODE_QUIZ = "qz";
    public static final String MODE_DATA_EXTRACT = "data";

    private String applicationMode = "";

    public ApplicationParameters(String[] args)
    {
        for (int i=0; i<args.length; i++)
        {
            match(args[i]);
        }
    }

    private void match(String arg)
    {
        switch(arg)
        {
            case MODE_QUIZ:
                applicationMode = MODE_QUIZ;
                return;
            case MODE_DATA_EXTRACT:
                applicationMode = MODE_DATA_EXTRACT;
                return;
        }
    }

    public String getApplicationMode()
    {
        return applicationMode;
    }
}
