package agh.ics.oop;

public class Animal{
    private MapDirection direction;
    private Vector2d position;
    private IWorldMap map;

//    I think this constructructor is no logner usable as ther is no way to determine if animal can be on position (2,2)
//    public Animal(){
//        this.direction = MapDirection.NORTH;
//        this.position = new Vector2d(2,2);
//    }

    public Animal (IWorldMap map){
        this.map = map;
        if(map.place(this)){
            this.direction = MapDirection.NORTH;
            this.position = new Vector2d(2,2);
        }
}

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map = map;
        if(map.place(this)){
            this.direction = MapDirection.NORTH;
            this.position = initialPosition;
        }
        else{
//            loop other vertices and if none of them is not occupied throw exception???
        }
    }

    public void move(MoveDirection direction){
        switch(direction){
            case RIGHT:
                this.direction = this.direction.next();
                break;
            case LEFT:
                this.direction = this.direction.previous();
                break;
            case FORWARD:
                Vector2d newPos = this.position.add(this.direction.toUnitVector());
                if (validatePos(newPos)){
                    this.position = newPos;
                }
                break;
            case BACKWARD:
                Vector2d nPos = this.position.subtract(this.direction.toUnitVector());
                if (validatePos(nPos)){
                    this.position = nPos;
                }
                break;
        }
    }

    private boolean validatePos(Vector2d position){
        int x = position.getX();
        int y = position.getY();
        return 0 <= Math.min(x, y) && Math.max(x, y) < 5 && this.map.canMoveTo(new Vector2d(x,y));
    }

    @Override
//    public String toString() {
//        return this.position.toString() + ", " + this.direction.toString();
//    }
    public String toString() {
        switch (this.direction){
            case NORTH:
                return "N";
            case SOUTH:
                return "S";
            case EAST:
                return "E";
            case WEST:
                return "W";
        };
        return "unexpected enum value";
    }

    public boolean isAt(Vector2d position){
        return position.equals(this.position);
    }

    public boolean areDirectionsEquals(MapDirection direction){
        return this.direction.equals(direction);
    }

    public boolean arePositionsEquals(Vector2d position){
        return this.position.equals(position);
    }

}