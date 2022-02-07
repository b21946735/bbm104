public class Zorde extends Character {

    
    @Override
    public boolean Alive() {
        if (currentHP<=0) {
            return false;
        }
        return true;
    }
    
}
