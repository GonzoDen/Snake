import java.util.ArrayList;

public class Drawer {
    Main main;

    Drawer(Main main) {
        this.main = main;
    }

    void drawField(float SIDE, float[] x, float[] y) {
        main.translate(main.width / 2f, main.height / 2f);
        main.stroke(0, 0, 0);
        main.fill(255, 255, 255);
        main.rectMode(Main.CENTER);
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < y.length; j++) {
                main.rect(x[i], y[j], SIDE, SIDE);
            }
        }
    }
    void drawHead(ArrayList<Float> bodyX, ArrayList<Float> bodyY, ArrayList<GameScreen.Sides> curDir,float SIDE) {
        main.pushMatrix();
        main.noStroke();
        main.fill(0, 0, 0);
        main.translate(bodyX.get(0), bodyY.get(0));
        main.ellipse(0, 0, SIDE, SIDE);
        main.fill(255, 0, 0);
        int eyesR = 7;
        switch (curDir.get(0)) {
            case RIGHT:
                main.ellipse(eyesR, -eyesR, eyesR, eyesR);
                main.ellipse(eyesR, eyesR, eyesR, eyesR);
                break;
            case LEFT:
                main.ellipse(-eyesR, -eyesR, eyesR, eyesR);
                main.ellipse(-eyesR, eyesR, eyesR, eyesR);
                break;
            case DOWN:
                main.ellipse(eyesR, eyesR, eyesR, eyesR);
                main.ellipse(-eyesR, eyesR, eyesR, eyesR);
                break;
            case UP:
                main.ellipse(eyesR, -eyesR, eyesR, eyesR);
                main.ellipse(-eyesR, -eyesR, eyesR, eyesR);
                break;
        }
        main.popMatrix();

    }

    void drawBody(ArrayList<Float> bodyX, ArrayList<Float> bodyY, float SIDE) {
        main.fill(0, 0, 0);
        for (int i = 1; i < bodyX.size() - 1; i++) {
            main.pushMatrix();
            main.translate(bodyX.get(i), bodyY.get(i));
            main.ellipse(0, 0, SIDE, SIDE);
            main.popMatrix();
        }
    }

    void drawTail(ArrayList<Float> bodyX, ArrayList<Float> bodyY, ArrayList<GameScreen.Sides> curDir, float SIDE) {
        main.pushMatrix();
        main.translate(bodyX.get(bodyX.size() - 1), bodyY.get(bodyY.size() - 1));
        switch (curDir.get(curDir.size() - 1)) {
            case RIGHT:
                main.triangle(SIDE / 2, -SIDE / 2, SIDE / 2, SIDE / 2, -SIDE / 2, 0);
                break;
            case LEFT:
                main.triangle(-SIDE / 2, -SIDE / 2, -SIDE / 2, SIDE / 2, SIDE / 2, 0);
                break;
            case UP:
                main.triangle(-SIDE / 2, -SIDE / 2, SIDE / 2, -SIDE / 2, 0, SIDE / 2);
                break;
            case DOWN:
                main.triangle(-SIDE / 2, SIDE / 2, SIDE / 2, SIDE / 2, 0, -SIDE / 2);
                break;
        }
        main.popMatrix();

    }


    void drawApple(float appleX,float appleY, float SIDE) {
        main.pushMatrix();
        main.translate(appleX, appleY);
        main.fill(255, 0, 0);
        main.ellipse(0, 0, SIDE, SIDE);
        main.popMatrix();

    }

    void showText(int score) {
        main.textSize(72);
        main.fill(255, 0, 0);
        main.text("Level " + Integer.toString(LevelScreen.level), main.width / 2f, 75);

        main.textSize(50);
        main.fill(255,255, 255, 100);
        main.text("Press CTRL to pause", main.width / 2f, main.height - 200);

        main.textSize(24);
        main.fill(255, 255, 255);
        main.text("best score: ", 100, main.height - 100);

        main.text("score: ", main.width - 100, main.height - 100);

        main.fill(255, 0, 0);
        main.text(Integer.toString(score), main.width - 30, main.height - 100);
        main.text(ResultScreen.results[LevelScreen.level-1], 175, main.height - 100);
    }

}
