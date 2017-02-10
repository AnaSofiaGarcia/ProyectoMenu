package mx.itesm.sgc.proyectomenu;

import com.badlogic.gdx.Gdx;
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
 * Created by Lenovo L440 on 31/01/2017.
 */
public class PantallaMenu implements Screen {
    private static final float ANCHO = 1280 ;
    private static final float ALTO = 800;
    private final Menu menu;
    private OrthographicCamera camara;
    private Viewport vista;
    private Texture texturaFondo;
    private Texture texturaBotonPlay;
    private Stage escena;
    private SpriteBatch batch;

    public PantallaMenu(Menu menu) {
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
        TextureRegionDrawable trdBtnPlay = new TextureRegionDrawable(new TextureRegion(texturaBotonPlay));
        ImageButton btnPlay = new ImageButton(trdBtnPlay);
        btnPlay.setPosition(ANCHO/2 -btnPlay.getWidth()/2, 3*ALTO/4-btnPlay.getHeight()/2);
        escena.addActor(btnPlay);

        //evento del boton
        btnPlay.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("clicked", "me hicieron click");
                menu.setScreen(new PantallaJuego(menu));
            }
        });
        Gdx.input.setInputProcessor(escena);
        Gdx.input.setCatchBackKey(false);

    }

    private void cargarTexturas() {
        texturaFondo = new Texture ("Diana_Splash_11.jpg");
        texturaBotonPlay = new Texture("boton.jpg");

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
        texturaFondo.dispose();
        texturaBotonPlay.dispose();
    }
}
