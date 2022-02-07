public class Elf extends Calliance{

    public Elf(int initialPositionX,int initialPositionY) {
        this.initialPositionX = initialPositionX;
        this.initialPositionY = initialPositionY;
        maxHP = 70;
        currentHP = maxHP;
        AP= Constants.elfAP;
    }
    
}
