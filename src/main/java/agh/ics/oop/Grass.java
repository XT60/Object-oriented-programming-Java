package agh.ics.oop;

public class Grass implements IMapElement{
    private Vector2d position;
    public Grass(Vector2d position){
        this.position = new Vector2d(position);
    }

    @Override
    public String toString() {
        return "*";
    }

    @Override
    public Vector2d getPosition() {
        return new Vector2d(this.position);
    }
}
