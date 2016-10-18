package rafalib.vectorgraph;

/**
 * Created by salas on 27/09/2016.
 */

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.VectorDrawable;


public class vectordraw {
    VectorDrawable drawable;
    Context context;
    int draw_height, draw_width;
    Rect superficie;
    Resources res;

    public vectordraw(Context context) {
        res = context.getResources();

        superficie = new Rect();
    }

    public void loadsvg(String nombre, String carpeta, String paquete) {
        //int id=res.getIdentifier(nombre, "drawable", "com.simbolic.salas.simbolic");
        int id = res.getIdentifier(nombre, carpeta, paquete);
        drawable = (VectorDrawable) res.getDrawable(id, null);
    }

    public void loadsvg(String nombre) {
        int id = res.getIdentifier(nombre, "drawable", "com.simbolic.salas.simbolic");

        drawable = (VectorDrawable) res.getDrawable(id, null);
    }


    public void resize(int width, int height, double scale) {
        double mainX, mainY, newW, newH;

        draw_height = drawable.getIntrinsicHeight();
        draw_width = drawable.getIntrinsicWidth();

        newW = width * scale;
        mainX = (width * ((1 - scale) / 2));
        float rel = ((float) newW / (float) draw_width);
        newW = newW + mainX;


        newH = rel * draw_height;
        mainY = (height - newH) / 2;
        newH = newH + mainY;
        superficie.set((int) mainX, (int) mainY, (int) newW, (int) (newH));
        drawable.setBounds(superficie);


    }

    public void resize(int width, int height, double scale, double Xini, double Yini) {
        double mainX, mainY, newW, newH;

        draw_height = drawable.getIntrinsicHeight();
        draw_width = drawable.getIntrinsicWidth();

        newW = width * scale;
        mainX = Xini * width;
        float rel = ((float) newW / (float) draw_width);
        newW = newW + mainX;


        newH = rel * draw_height;
        mainY = Yini * width;
        newH = newH + mainY;
        superficie.set((int) mainX, (int) mainY, (int) newW, (int) (newH));
        drawable.setBounds(superficie);


    }

    public void dibujar(Canvas canvas) {



        drawable.draw(canvas);

    }

    public void alfa(int opacity) {
        drawable.setAlpha(opacity);
    }

    public void colorize(int opacity, int r, int g, int b) {
        Color color = new Color();


        drawable.setColorFilter(color.argb(opacity, r, g, b), PorterDuff.Mode.MULTIPLY);

    }
}