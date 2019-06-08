import java.util.ArrayList;

class GameScreen {
    private Main main;

    private Drawer drawer;
    static int level;
    static int score;
    private final float SIDE = 25;
    private float x[] = new float[30];
    private float y[] = new float[20];

    private ArrayList<Float> bodyX = new ArrayList<>();
    private ArrayList<Float> bodyY = new ArrayList<>();

    private float appleX;
    private float appleY;

    private ArrayList<Float> turnX = new ArrayList<>();
    private ArrayList<Float> turnY = new ArrayList<>();
    private ArrayList<Sides> turnDir = new ArrayList<>();

    enum Sides {
        UP,
        RIGHT,
        DOWN,
        LEFT
    }

    private ArrayList<Sides> curDir = new ArrayList<>();

    public ArrayList<Sides> getCurDir() {
        return curDir;
    }

    GameScreen(Main main) {
        this.main = main;
        drawer = new Drawer(main);
        score = 0;
        // Field
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < y.length; j++) {
                x[i] = -SIDE * 15 + SIDE * i;
                y[j] = -SIDE * 10 + SIDE * j;
            }
        }
        //Head
        bodyX.add(x[2]);
        bodyY.add(y[0]);
        curDir.add(Sides.RIGHT);

        //Body
        bodyX.add(x[1]);
        bodyY.add(y[0]);
        curDir.add(Sides.RIGHT);

        //Tail
        bodyX.add(x[0]);
        bodyY.add(y[0]);
        curDir.add(Sides.RIGHT);

//        appleX = x[(int) main.random(3, 29)];
//        appleY = y[(int) main.random(1, 19)];

    }

    Main.States run() {
        if (checkCollisionSnake()) {
            ResultScreen.score = score;
//            main.gameScreen = new GameScreen(main);
            main.createGameScreen();

            return Main.States.ACHIEVEMENT;
        }

        if (checkCollision()) {
            ResultScreen.score = score;


            main.createGameScreen();
            return Main.States.RESULT;
        }

        checkTurning();
        moveForward();
        eat();
        drawer.showText(score);
        drawer.drawField(SIDE,x,y);
        drawer.drawHead(bodyX,bodyY,curDir,SIDE);
        drawer.drawBody(bodyX,bodyY,SIDE);
        drawer.drawTail(bodyX,bodyY,curDir,SIDE);
        drawer.drawApple(appleX,appleY,SIDE);


        return Main.States.GAME;
    }

    void turn(Sides dir) {
        turnX.add(bodyX.get(0));
        turnY.add(bodyY.get(0));
        turnDir.add(dir);
        curDir.set(0, dir);

    }

    private void checkTurning() {
        for (int i = 1; i < bodyX.size(); i++) {
            for (int j = 0; j < turnX.size(); j++) {
                if (bodyX.get(i).equals(turnX.get(j)) && bodyY.get(i).equals(turnY.get(j))) {
                    curDir.set(i, turnDir.get(j));
                    if (i == bodyX.size() - 1) {
                        turnX.remove(j);
                        turnY.remove(j);
                        turnDir.remove(j);
                        j--;
                    }
                }
            }
        }
    }

    private boolean checkCollision() {
        float snakeX = bodyX.get(0);
        float snakeY = bodyY.get(0);
        Sides dir = curDir.get(0);
//        for (int i = 1; i < bodyX.size()-2; i++) {
//            if (snakeX == bodyX.get(i) && snakeY == bodyY.get(i))
//                return true;
//        }
        return snakeX == x[29] && dir == Sides.RIGHT ||
                snakeX == x[0] && dir == Sides.LEFT ||
                snakeY == y[0] && dir == Sides.UP ||
                snakeY == y[19] && dir == Sides.DOWN;

    }

    private boolean checkCollisionSnake() {
        float snakeX = bodyX.get(0);
        float snakeY = bodyY.get(0);
        Sides dir = curDir.get(0);

        for (int i = 1; i < bodyX.size(); i++) {
            if (snakeX == bodyX.get(i) && snakeY == bodyY.get(i))
                return true; //if return "true" method stops
        }

        return false;
    }

    private boolean checkCollisionApple() {
        if (bodyX.get(0) == appleX && bodyY.get(0) == appleY)
            return true;
        return false;
    }

    private void spawnApple() {
        appleX = x[(int) main.random(0, 29)];
        appleY = y[(int) main.random(0, 19)];
        for (int i =0; i< bodyX.size(); i++){
            if (appleX == bodyX.get(i) && appleY == bodyY.get(i)){
                appleX = x[(int) main.random(0, 29)];
                appleY = y[(int) main.random(0, 19)];
                i = 0;
            }

        }
    }

    private void eat() {
        if (checkCollisionApple()) {
            score++;
            spawnApple();

            //Add Body
            bodyX.add(bodyX.size() - 2, bodyX.get(bodyX.size() - 1));
            bodyY.add(bodyY.size() - 2, bodyY.get(bodyY.size() - 1));
            curDir.add(curDir.size() - 2, curDir.get(curDir.size() - 1));

            //Move Tail One Back
            switch (curDir.get(curDir.size() - 1)) {
                case UP:
                    bodyY.set(bodyY.size() - 1, bodyY.get(bodyY.size() - 1) + SIDE);
                    break;
                case RIGHT:
                    bodyX.set(bodyX.size() - 1, bodyX.get(bodyX.size() - 1) - SIDE);
                    break;
                case DOWN:
                    bodyY.set(bodyY.size() - 1, bodyY.get(bodyY.size() - 1) - SIDE);
                    break;
                case LEFT:
                    bodyX.set(bodyX.size() - 1, bodyX.get(bodyX.size() - 1) + SIDE);
                    break;
            }
        }
    }

    private void moveForward() {
        for (int i = 0; i < bodyX.size(); i++) {
            switch (curDir.get(i)) {
                case RIGHT:
                    bodyX.set(i, bodyX.get(i) + SIDE);
                    break;
                case DOWN:
                    bodyY.set(i, bodyY.get(i) + SIDE);
                    break;
                case LEFT:
                    bodyX.set(i, bodyX.get(i) - SIDE);
                    break;
                case UP:
                    bodyY.set(i, bodyY.get(i) - SIDE);
                    break;
            }
        }
    }
}