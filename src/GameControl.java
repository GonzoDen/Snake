class GameControl {

    private Main main;

    GameControl(Main main) {
        this.main = main;
    }

    public void keyReleased() {
        switch (main.getState()) {
            case TITLE:
                if (main.keyPressed) {
                    if (main.keyCode == Main.CONTROL) {
                        main.setState(Main.States.PAUSE);
                    }
                    main.setState(Main.States.LEVEL);
                }
                break;
            case LEVEL:
                if (main.keyCode == Main.ENTER) {
                    GameScreen.level = LevelScreen.level;
                    main.setState(Main.States.GAME);
                }
                if (main.keyCode == Main.UP && LevelScreen.level < 10)
                    LevelScreen.level++;
                if (main.keyCode == Main.DOWN && LevelScreen.level > 1)
                    LevelScreen.level--;
                break;
            case GAME:
                switch (main.keyCode) {
                    case Main.UP:
                        if (main.getGameScreen().getCurDir().get(0) == GameScreen.Sides.LEFT ||
                                main.getGameScreen().getCurDir().get(0) == GameScreen.Sides.RIGHT)
                            main.getGameScreen().turn(GameScreen.Sides.UP);
                        break;
                    case Main.DOWN:
                        if (main.getGameScreen().getCurDir().get(0) == GameScreen.Sides.LEFT ||
                                main.getGameScreen().getCurDir().get(0) == GameScreen.Sides.RIGHT)
                            main.getGameScreen().turn(GameScreen.Sides.DOWN);
                        break;
                    case Main.LEFT:
                        if (main.getGameScreen().getCurDir().get(0) == GameScreen.Sides.UP ||
                                main.getGameScreen().getCurDir().get(0) == GameScreen.Sides.DOWN)
                            main.getGameScreen().turn(GameScreen.Sides.LEFT);
                        break;
                    case Main.RIGHT:
                        if (main.getGameScreen().getCurDir().get(0) == GameScreen.Sides.UP ||
                                main.getGameScreen().getCurDir().get(0) == GameScreen.Sides.DOWN)
                            main.getGameScreen().turn(GameScreen.Sides.RIGHT);
                        break;
                    case Main.CONTROL:
                        main.setState(Main.States.PAUSE);
                        break;
                }
                break;
            case RESULT:
                if (main.keyCode == Main.KEY_F) {
                    GameScreen.level = LevelScreen.level;
                    main.setState(Main.States.LEVEL);
                }
                break;
            case ACHIEVEMENT:
                if (main.keyCode == Main.ENTER) {
                    GameScreen.level = LevelScreen.level;
                    main.setState(Main.States.RESULT);
                }
                break;
            case PAUSE:
                if (main.keyCode == Main.ENTER) {
                    main.setState(Main.States.GAME);
                }

                if (main.keyCode == Main.ESC) {
                    System.exit(0);
                }
                break;
        }
    }
}
