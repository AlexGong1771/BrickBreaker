public enum StoneType {
    SMALL(1) , MEDIUM(2) , LARGE(3) , VERTICAL(3) , ANGLE(3);
private int size;
    StoneType(int size) {
this .size =size;
    }
    public int getSize(){
        return this.size;
    }
}
