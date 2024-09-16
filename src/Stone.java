public class Stone {
    private int x, y;
    private StoneType stoneType;

    public Stone(int x, int y, StoneType stoneType) {
        this.x = x;
        this.y = y;
        this.stoneType = stoneType;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public StoneType getStoneType() {
        return stoneType;
    }
    public boolean isEqualPosition(Stone other){
        return this.x == other.x && this.y == other.y;
    }
}
