public class Token {
    public int value;
    public char tier;
    public String name;
    Token(char tier,int value,String name) {
        this.tier = tier;
        this.value = value;
        this.name = name;
    }

    public int getTier() {
        return -(int)tier;
    }
    public int getValue() {
        return value;
    }
}
