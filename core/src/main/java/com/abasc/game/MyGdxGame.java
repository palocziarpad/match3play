package com.abasc.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyGdxGame extends ApplicationAdapter {

	private static final int BOARD_WIDTH = 640;
	private static final int BOARD_HEIGHT = 480;
	private static final int GRID_CELL = 32;
	private ShapeRenderer shapeRenderer;
	private Viewport viewport;

	SpriteBatch batch;
	Texture img;
	private Camera camera;
	@Override
	public void create () {
		batch = new SpriteBatch();
	}

	@Override
	public void render () {
		Gdx.graphics.setWindowedMode(BOARD_WIDTH, BOARD_HEIGHT);
		shapeRenderer = new ShapeRenderer();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		 //camera.position.set(BOARD_WIDTH / 2, BOARD_HEIGHT / 2, 0);
		viewport = new FitViewport(BOARD_WIDTH , BOARD_HEIGHT , camera);
		 // viewport.apply();

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
         drawGrid();
		batch.end();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}


	private void drawGrid() {
		shapeRenderer.setProjectionMatrix(camera.projection);
		shapeRenderer.setTransformMatrix(camera.view);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1,1,1,1);
		for (int x = 0; x < viewport.getWorldWidth(); x += GRID_CELL) {
			for (int y = 0; y < viewport.getWorldHeight(); y += GRID_CELL) {
				shapeRenderer.rect(x, y, GRID_CELL, GRID_CELL);
			}
		}
		shapeRenderer.end();
	}
}
