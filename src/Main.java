import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;


public class Main extends PApplet {

    enum States {
        TITLE,
        LEVEL,
        GAME,
        RESULT,
        ACHIEVEMENT,
        PAUSE
    }

    private PFont font;

    PImage img;

    final static int KEY_F = 70;

    private TitleScreen titleScreen;
    private LevelScreen levelScreen;
    private AchievementScreen achievementScreen;
    private GameScreen gameScreen;
    private ResultScreen resultScreen;
    private PauseScreen pauseScreen;
    private GameControl gameControl;

    private States state = States.TITLE;

    public void settings() {
        fullScreen();
    }

    public void setup() {
        font = createFont("src/cour.ttf", 32);
        textFont(font);

        img = loadImage("src/pressF.jpg");

        gameControl = new GameControl(this);
        gameScreen = new GameScreen(this);
        titleScreen = new TitleScreen(this);
        levelScreen = new LevelScreen(this);
        resultScreen = new ResultScreen(this);
        achievementScreen = new AchievementScreen(this);
        pauseScreen = new PauseScreen(this);

        ResultScreen.results = loadStrings("best.txt");

        if (ResultScreen.results == null) {
            ResultScreen.results = new String[10];
            for (int i = 0; i < 10; i++)
                ResultScreen.results[i] = "0";
            saveStrings("best.txt", ResultScreen.results);
        }

    }

    public States getState() {
        return state;
    }

    public void setState(States state) {
        this.state = state;
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    @Override
    public void keyReleased() {
        gameControl.keyReleased();
    }

    public void draw() {
        background(0);

        switch (state) {
            case TITLE:
                state = titleScreen.run();
                break;
            case LEVEL:
                state = levelScreen.run();
                break;
            case GAME:
                frameRate(LevelScreen.level);
                state = gameScreen.run();
                break;
            case RESULT:
                state = resultScreen.run();
                break;
            case ACHIEVEMENT:
                state = achievementScreen.run();
                break;
            case PAUSE:
                pauseScreen.run();
                break;
        }
    }

    public void createGameScreen(){
        gameScreen = new GameScreen(this);
    }

    public static void main(String... args) {
        PApplet.main("Main");
    }
}

