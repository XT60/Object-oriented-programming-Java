package agh.ics.oop;

public class Grass implements IMapElement{
    private Vector2d pos;
    public Grass(Vector2d pos){
        this.pos = new Vector2d(pos);
    }

    public Vector2d getPosition(){
        return new Vector2d(this.pos);
    }

    @Override
    public String toString() {
        return "*";
    }
}
