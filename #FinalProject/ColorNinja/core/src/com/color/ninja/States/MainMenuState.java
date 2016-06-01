package com.color.ninja.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.color.ninja.MyColorNinja;

/**
 * CLass representing the MainMenu.
 */
public class MainMenuState extends State {
    private Stage stage;

    private Sprite title;
    private Sprite newGameBtnSprite;
    private Sprite settingsBtnSprite;
    private Sprite scoresBtnSprite;

    private Button newGameBtn;
    private Button settingsBtn;
    private Button scoresBtn;

    // Pixel values for the main menu
    private float buttonsWidth;
    private float buttonsHeight;
    private float buttonsSpace; // space in the y-axis between 2 buttons
    private float buttonsBegin; // y-coordinate of the first button


    public MainMenuState(GameStateManager gsm) {
        super(gsm);

        title = new Sprite(new Texture("title1.png"));
        newGameBtnSprite = new Sprite(new Texture ("new game1.png"));
        settingsBtnSprite = new Sprite (new Texture ("settings1.png"));
        scoresBtnSprite = new Sprite (new Texture("scores1.png"));

        newGameBtn = new Button(new SpriteDrawable(newGameBtnSprite));
        settingsBtn = new Button(new SpriteDrawable(settingsBtnSprite));
        scoresBtn = new Button(new SpriteDrawable(scoresBtnSprite));

        stage = new Stage();

        // Menu button properties
        buttonsWidth = MyColorNinja.WIDTH / 2;
        buttonsHeight = MyColorNinja.HEIGHT / 8;
        buttonsSpace = MyColorNinja.HEIGHT / 15;
        buttonsBegin = 2 * MyColorNinja.HEIGHT / 4;

        // Title's position and size
        title.setSize(8 * MyColorNinja.WIDTH / 10, MyColorNinja.HEIGHT / 4);
        title.setCenterX(MyColorNinja.WIDTH / 2);
        title.setY(MyColorNinja.HEIGHT - MyColorNinja.HEIGHT / 20 - title.getHeight());

        // Buttons' position and size
        newGameBtn.setSize(buttonsWidth, buttonsHeight);
        settingsBtnSprite.setSize(buttonsWidth, buttonsHeight);
        scoresBtnSprite.setSize(buttonsWidth, buttonsHeight);

        newGameBtn.setX(MyColorNinja.WIDTH / 2 - newGameBtn.getWidth() / 2);
        settingsBtnSprite.setCenterX(MyColorNinja.WIDTH / 2);
        scoresBtnSprite.setCenterX(MyColorNinja.WIDTH / 2);

        newGameBtn.setY(buttonsBegin);
        settingsBtnSprite.setY(newGameBtn.getY() - buttonsHeight - buttonsSpace);
        scoresBtnSprite.setY(settingsBtnSprite.getY() - buttonsHeight - buttonsSpace);

        stage.addActor(newGameBtn);

        Gdx.input.setInputProcessor(stage);

        newGameBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("ClickListener works.");

            }
        });

        System.out.println(newGameBtn.isTouchable());

    }

    @Override
    protected void handleInput() {


    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin(); // Open batch to add things to it. A batch works like a box which is filled with images.

        // draws title sprite

        title.draw(sb);

        // draws game options buttons
        newGameBtn.draw(sb, 1);
        settingsBtn.draw(sb, 1);
        scoresBtn.draw(sb, 1);

        sb.end();   // Close batch.

    }

    @Override
    public void dispose() {
        title.getTexture().dispose();
        newGameBtnSprite.getTexture().dispose();
        settingsBtnSprite.getTexture().dispose();
        scoresBtnSprite.getTexture().dispose();
    }
}