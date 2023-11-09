import processing.core.PApplet;
import processing.core.PVector;

public class App extends PApplet {

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void settings() {
    size (1600, 600);
    }

PVector bold;
PVector boldVel;
PVector boldAcc;

PVector ag;
PVector aLuft;
PVector aM;

final float g = 9.82f;
final float rho = 1.29f;

final float mass=  0.05f;
final float r=  0.02f;
final float A = PI * sq(r);
final float Cd =  0.47f;
final float CA =  0.3f;
float rps;


public void setup() {
    background(0);
    frameRate (30);

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

//boldens acceleration er summen af de gældende accelerationer
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


println(rps);


boldAcc.set(0,0);


        }
    }