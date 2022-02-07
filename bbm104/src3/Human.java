public class Human  extends Calliance{

    public Human(int initialPositionX,int initialPositionY) {
        this.initialPositionX = initialPositionX;
        this.initialPositionY = initialPositionY;
        maxHP = 100;
        currentHP = maxHP;
        AP= Constants.humanAP;
    }
    
}
