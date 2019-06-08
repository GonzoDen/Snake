class TitleScreen {
    private Main main;

    TitleScreen(Main main) {
        this.main = main;
    }

    Main.States run() {

        main.textAlign(Main.CENTER, Main.CENTER);

        main.textSize(72);
        main.fill(255, 255,255);
        main.text("Uroboros", main.width/2f, 100); //TODO change font

        main.fill(255, 0,0);
        main.text("Press any key to continue", main.width/2f, main.height/2f);

        main.textSize(48);
        main.fill(255, 255,255, 100);
        main.text("Made by Deniz Nazarova", main.width/2f, main.height- 75);

        return Main.States.TITLE;

    }
}