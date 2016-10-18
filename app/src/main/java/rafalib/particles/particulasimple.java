package rafalib.particles;

/**
 * Created by salas on 27/09/2016.
 */
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import processing.core.PVector;
public class particulasimple extends puntocolor {


    public particulasimple(PVector pos, float masa, boolean siempre, int vida , int viX, int viY){
                    super(pos, masa);
                    velocidad.x=viX;
                    velocidad.y=viY;
                    eterna=siempre;
                    lifespan=vida;

    }

    public void mostrar(Canvas canvas){

        RectF limites;
        Paint paint;


        limites = new RectF();
        float head=(float)Math.toDegrees(velocidad.heading());
        paint = new Paint();

        if (eterna==false){a=lifespan;};
        if (a<0 || a>255){a=0;}
        paint.setARGB(a, r, g, b);
        paint.setStyle(Paint.Style.FILL);
        //paint.setStrokeWidth(10);

        paint.setAntiAlias(true);
        //limites.set(posicion.x, posicion.y, posicion.x + masa * 2, posicion.y + masa * 2);
        //canvas.drawOval(limites, paint);
        canvas.save();
        canvas.translate(posicion.x, posicion.y);
        canvas.rotate(head, 0, 0);
        limites.set(0, 0, masa * 5, masa);
        canvas.drawRect(limites, paint);
        canvas.restore();

    }
}
