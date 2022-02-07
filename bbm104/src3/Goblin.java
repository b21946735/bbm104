public class Goblin extends Zorde{

    public Goblin(int initialPositionX,int initialPositionY) {
        this.initialPositionX = initialPositionX;
        this.initialPositionY = initialPositionY;
        maxHP = 80;
        currentHP = maxHP;
        AP= Constants.goblinAP;
    }
    
}
