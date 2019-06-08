import java.security.Key;

class ResultScreen {
    static int score;
    static int best;
    private Main main;
    static String[] results;

    ResultScreen(Main main) {
        this.main = main;
    }

    Main.States run() {

        main.textSize(72);
        main.fill(255, 255, 255);
        main.text("Your result " + score, main.width / 2f, 100);

        main.image(main.img, main.width / 4, main.height / 4, main.width / 2, main.height / 2);

        main.textSize(48);
        main.fill(255, 0, 0);
        main.text("Press F to Pay Respects", main.width / 2f, main.height - 175);

        main.fill(255, 255, 255);
        if (best > score)
            main.text("You can more!", main.width / 2f, main.height - 75);
        else if (best == score)
            main.text("Almost new record!", main.width / 2f, main.height - 75);
        else {
            main.text("You set the new record!", main.width / 2f, main.height - 75);
            results[GameScreen.level - 1] = Integer.toString(score);
        }

        return Main.States.RESULT;
    }
}
