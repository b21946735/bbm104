public class Troll extends Zorde{

    public Troll(int initialPositionX,int initialPositionY) {
        this.initialPositionX = initialPositionX;
        this.initialPositionY = initialPositionY;
        maxHP = 150;
        currentHP = maxHP;
        AP= Constants.trollAP;
    }
    
}
