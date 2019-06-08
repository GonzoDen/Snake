class PauseScreen {
    private Main main;

    PauseScreen(Main main) {
        this.main = main;
    }

    Main.States run() {
        main.textSize(72);
        main.fill(255, 255, 255);

        main.text("Press ENTER to continue", main.width / 2f, main.height/2f - 50);

        main.text("Press ESC to exit", main.width / 2f, main.height/2f + 50);


        return Main.States.PAUSE;
    }
}
