package mx.itesm.sgc.proyectomenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Lenovo L440 on 03/02/2017.
 */
public class PantallaJuego implements Screen {
    private static final float ANCHO = 1280 ;
    private static final float ALTO = 800;
    private final Menu menu;
    private OrthographicCamera camara;
    private Viewport vista;
    private Texture texturaFondo;
    private Texture texturaBotonBack;
    private Stage escena;
    private SpriteBatch batch;

    public PantallaJuego(Menu menu) {

        this.menu = menu;
    }

    @Override
    public void show() {
        //camara y vista
        crearCamara();
        cargarTexturas();
        crearObjetos();

    }

    private void crearObjetos() {
        batch = new SpriteBatch();
        escena = new Stage(vista, batch);
        Image imgFondo = new Image(texturaFondo);
        escena.addActor(imgFondo);

        //boton
        TextureRegionDrawable trdBtnBack = new TextureRegionDrawable(new TextureRegion(texturaBotonBack));
        ImageButton btnBack = new ImageButton(trdBtnBack);
        btnBack.setPosition(0, 0);
        escena.addActor(btnBack);

        //evento del boton
        btnBack.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("clicked", "me hicieron click");
                menu.setScreen(new PantallaMenu(menu));
            }
        });
        Gdx.input.setInputProcessor(escena);

        Gdx.input.setInputProcessor(escena);
        Gdx.input.setCatchBackKey(true);

    }

    private void cargarTexturas() {
        texturaFondo = new Texture ("1234.jpg");
        texturaBotonBack = new Texture("back.png");

    }

    private void crearCamara() {
        camara = new OrthographicCamera (ANCHO,ALTO);
        camara.position.set(ANCHO/2, ALTO/2,0);
        camara.update();
        vista = new StretchViewport(ANCHO, ALTO,camara);
        //cada que pones camra hay que darle update a resize
    }

    @Override
    public void render(float delta) {

        borrarPantalla();
        escena.draw();

        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            menu.setScreen(new PantallaMenu(menu));
        }


    }

    private void borrarPantalla() {
        Gdx.gl.glClearColor(0,1,0,1); //(0,1,0,1)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    }

    @Override
    public void resize(int width, int height) {
        vista.update(width, height);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();

    }

    @Override
    public void dispose() {
        escena.dispose();
        texturaBotonBack.dispose();
        texturaFondo.dispose();

    }
}
