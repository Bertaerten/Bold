import processing.core.PApplet;
import processing.core.PVector;

public class App extends PApplet {

    public static void main(String[] args) {
        PApplet.main("Bold");
    }

    public void settings() {
    size (1600, 600);
    frameRate (30);
    }

PVector bold;
PVector boldVel;
PVector boldAcc;

PVector ag;
PVector aLuft;
PVector aM;

final float g = (float) 9.82;
final float rho = (float) 1.29;

final float mass= (float) 0.05;
final float r= (float) 0.02;
final float A = PI * sq(r);
final float Cd = (float) 0.47;
final float CA = (float) 0.3;
float rps;


public void setup() {
    background(0);
    bold = new PVector(0, 600);
    boldVel = new PVector(20, -10);
    boldAcc = new PVector(0, 0);

    ag = new PVector(0, g);
    aLuft = new PVector(0, 0);
    aM = new PVector(0, 0);

    rps = 200;
    }

    public void draw() {

//background (0);
// set retningen og størrelsen af luftmodstanden
aLuft.set(boldVel.x, boldVel.y);
aLuft.setMag(-0.5f*rho*A*Cd*boldVel.magSq()/mass);

float v = boldVel.mag();
aM.set(boldVel.y, -boldVel.x);
//aM.setMag(-0.5*CA*rho*A*(sq(v-2*PI*r*rps)-sq(v+2*PI*r*rps))/mass);
aM.setMag(-0.5f*CA*rho*A*(-8*PI*rps*r*v)/mass);

//reducer spinraten
rps = rps * 0.993f;

//boldens acceleration er summen af de gældende accelerationen
boldAcc.add(ag);
boldAcc.add(aLuft);
boldAcc.add(aM);

boldAcc.mult(0.03333f);





//opdater hastigheden
boldVel.add(boldAcc);
//opdater positionen
bold.add(boldVel);
//tegn bolden
circle(bold.x, bold.y, 10);

boldAcc.set(0,0);


        }
    }