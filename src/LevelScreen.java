class LevelScreen {
    private Main main;
    static int level;

    LevelScreen(Main main) {
        this.main = main;
        level = 5;
    }

    Main.States run() {

        main.textAlign(Main.CENTER, Main.CENTER);

        main.textSize(72);
        main.fill(255, 255, 255);
        main.text("Choose Level", main.width / 2f, 100);

        main.fill(255, 0, 0);
        main.text(Integer.toString(level), main.width / 2f, main.height / 2f);

        main.textSize(48);
        main.fill(255, 255, 255, 100);
        main.text("Press UP/DOWN to change", main.width / 2f, main.height - 150);
        main.text("Press ENTER to continue", main.width / 2f, main.height - 75);


        return Main.States.LEVEL;
    }

}
