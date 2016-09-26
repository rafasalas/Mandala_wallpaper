package rafalib.particles;

/**
 * Created by salas on 02/03/2016.
 */



import java.util.Random;

import processing.core.PVector;
import rafalib.particles.Mat_point;

public class puntocolor extends Mat_point{
    public int r,g,b,a;
    public puntocolor(PVector pos, float masa){
        super(pos, masa);
        Random rnd=new Random();
        r=rnd.nextInt(255);
        g=rnd.nextInt(255);
        b=rnd.nextInt(255);
        a=rnd.nextInt(125);
    }





}
