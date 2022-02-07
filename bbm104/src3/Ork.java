public class Ork extends Zorde{

    public Ork(int initialPositionX,int initialPositionY) {
        this.initialPositionX = initialPositionX;
        this.initialPositionY = initialPositionY;
        maxHP = 200;
        currentHP = maxHP;
        AP= Constants.orkAP;
    }

    
}