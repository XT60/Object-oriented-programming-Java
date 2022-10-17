package agh.ics.oop;

public class Animal {
    private MapDirection direction = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2,2);

    public void move(MoveDirection direction){
        switch(direction){
            case RIGHT:
                this.direction = this.direction.next();
                break;
            case LEFT:
                this.direction = this.direction.previous();
                break;
            case FORWARD, BACKWARD:
                Vector2d newPos = this.position.add(this.direction.toUnitVector());
                if (validatePos(newPos)){
                    this.position = newPos;
                }
                break;
        }
    }

    private boolean validatePos(Vector2d position){
        int x = position.getX();
        int y = position.getY();
        return 0 < Math.min(x, y) && Math.max(x, y) < 5;
    }
    @Override
    public String toString() {
        return this.position.toString() + ", " + this.direction.toString();
    }

    public boolean isAt(Vector2d position){
        return position.equals(this.position);
    }
}