import game.Game;
import game.GameInterfaceBridge;
import game.console_application.ConsoleGameInterface;
import game.console_application.ConsoleMain;

import java.util.Vector;

public class Main
{
    public static void main(String[] args)
    {
        ApplicationParameters appParms = new ApplicationParameters(args);

        String mode = appParms.getApplicationMode();
        if (ApplicationParameters.MODE_QUIZ.equals(mode))
        {
            GameInterfaceBridge bridge = new GameInterfaceBridge(new Game(), new ConsoleGameInterface());
            bridge.run();
            return;
        }

        if (ApplicationParameters.MODE_DATA_EXTRACT.equals(mode))
        {
            //do a data extract
        }
    }
}
