class AchievementScreen {
    private Main main;

    AchievementScreen(Main main) {
        this.main = main;
    }

    Main.States run() {

        main.textSize(72);
        main.fill(255, 255, 255);
        main.text("Congratilations!", main.width / 2f, 100);


        main.textSize(48);
        main.fill(255, 0, 0);
        main.text("You have unlocked Uroboros achievement!", main.width / 2f, main.height/2f);

        main.fill(255, 255, 255, 100);
        main.text("...but", main.width *0.75f, main.height - 75);


        return Main.States.ACHIEVEMENT;
    }
}
