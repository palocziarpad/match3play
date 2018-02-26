package com.abasc.game;

import com.abasc.game.board.rules.NormalRule;
import com.abasc.game.board.rules.Rule;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.*;

public class GameScreen extends AbstractGameScreen {

    private static final int BOARD_WIDTH = 640;
    private static final int BOARD_HEIGHT = 480;
    private static final int GRID_CELL = 32;
    private static float celladjustx=1;
    private static float celladjusty=1;
    SpriteBatch batch;
    Texture img;
    GuiBoard board;
    boolean togled = false;
    private ShapeRenderer shapeRenderer;
    private Viewport viewport;
    private Camera camera;
    private float timer = 0.01f;

    private byte toggledx=0;
    private byte toggledy=0;
    Rule normalRule;
    public GameScreen(JeweledGame game) {
        super(game);
        img = new Texture("badlogic.jpg");
        board = new GuiBoard();
        Music music = Gdx.audio.newMusic(Gdx.files.internal("theme.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();
        normalRule = new NormalRule(board);
    }

    @Override
    public void show() {
        Gdx.graphics.setWindowedMode(BOARD_WIDTH, BOARD_HEIGHT);

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(BOARD_WIDTH / 2, BOARD_HEIGHT / 2, 0);
        viewport = new FitViewport(BOARD_WIDTH, BOARD_HEIGHT, camera);
        viewport.apply();
        shapeRenderer = new ShapeRenderer();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch = new SpriteBatch();


    }

    @Override
    public void render(float delta) {
        clearScreen();
        drawGrid();
        drawItems();
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(Color.BLACK.getRed(), Color.BLACK.getGreen(), Color.BLACK.getBlue(), Color.BLACK.getAlpha());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height,true);
        celladjusty=BOARD_HEIGHT>height?BOARD_HEIGHT/height:height/BOARD_HEIGHT ;
        celladjustx=BOARD_WIDTH>width?BOARD_WIDTH/width:width/BOARD_WIDTH ;
        Gdx.app.log("MyTag", "celladjustx "+ celladjustx+ " celladjusty"+celladjusty);
       // camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
    }

    private void drawItems() {
        batch.setProjectionMatrix(camera.projection);
        batch.setTransformMatrix(camera.view);

        batch.begin();
        board.draw(batch);
        batch.end();
        queryInput();

    }

    private void queryInput() {
        timer -= Gdx.graphics.getDeltaTime();
        if (timer > 0) {
            return;
        }

        if (Gdx.input.isTouched()) {

            boolean leftPressed = Gdx.input.isButtonPressed(Input.Buttons.LEFT);
            if (!leftPressed) {
                return;
            }

            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            touchPos = camera.unproject(touchPos);
          
            byte xpos = (byte) (Math.round(touchPos.x) / (GRID_CELL*celladjustx));
            byte ypos = (byte) (Math.round(touchPos.y) / (GRID_CELL*celladjusty));
            //TODO check if lt 0 and gt BOARD_WIDTH or BOARD_HEIGHT
            if (togled) {
                if (ItemType.isHighLighted( board.board[xpos][ypos] )) {
                    Gdx.app.log("MyTag", "isHighLighted");
                    board.board[xpos][ypos] =  ItemType.toggle( board.board[xpos][ypos] ) ;

                }else {
                    Gdx.app.log("MyTag", "switchElementsAndUnToggle");
                    board.switchElementsAndUnToggle(xpos,ypos,toggledx,toggledy);
                   while (normalRule.check()){
                        normalRule.apply();
                        normalRule.refill();
                        try {
							Thread.sleep(300);
						} catch (InterruptedException e) {
							
						}
                    }
                }
                togled = false;
                Gdx.app.log("MyTag", "untogled");

            } else {
                togled = true;
                toggleItem(touchPos.x, touchPos.y);
                Gdx.app.log("MyTag", "togled");
            }
            timer = 0.3F;
        }


    }

    private void toggleItem(float x, float y) {
        Gdx.app.log("MyTag", "x " + x + "y " + y);
        byte xpos = (byte) (Math.round(x) / GRID_CELL);
        byte ypos = (byte) (Math.round(y) / GRID_CELL);
        Gdx.app.log("MyTag", "xpos " + xpos + "ypos " + ypos);
        board.board[xpos][ypos] = ItemType.toggle( board.board[xpos][ypos] ) ;

        toggledx=xpos;
        toggledy=ypos;
    }

    private void drawGrid() {
        shapeRenderer.setProjectionMatrix(camera.projection);
        shapeRenderer.setTransformMatrix(camera.view);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        shapeRenderer.setColor(1, 1, 1, 1);
        for (int x = 0; x < viewport.getWorldWidth(); x += GRID_CELL) {
            for (int y = 0; y < viewport.getWorldHeight(); y += GRID_CELL) {
                shapeRenderer.rect(x, y, GRID_CELL, GRID_CELL);
            }
        }
        shapeRenderer.end();
    }
}
