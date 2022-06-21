package game.console_application;

import game.Game;
import game.GameInterfaceBridge;
import game.GameInterfaceI;

public class ConsoleMain implements Runnable
{
    private final Game game = new Game();
    private final GameInterfaceI consoleGameInterface = new ConsoleGameInterface();
    private final GameInterfaceBridge gameInterfaceBridge= new GameInterfaceBridge(game, consoleGameInterface);

    public void run()
    {
        gameInterfaceBridge.run();
    }
}
