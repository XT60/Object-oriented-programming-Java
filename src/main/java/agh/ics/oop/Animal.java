package agh.ics.oop;

public class Animal{
    private MapDirection direction;
    private Vector2d position;

    private static Boolean[][] spaceOccupation = {
            {false, false, false, false, false},
            {false, false, false, false, false},
            {false, false, false, false, false},
            {false, false, false, false, false},
            {false, false, false, false, false}
    };

    public Animal () throws FullWorldException{
        this.direction = MapDirection.NORTH;
        if (!this.spaceOccupation[2][2]){
            this.position = new Vector2d(2,2);
            this.spaceOccupation[2][2] = true;
        }
        else{
            boolean found = false;
            for(int y = 0; y < 5; y++){
                for(int x = 0; x < 5; x++){
                    if (!this.spaceOccupation[y][x]){
                        this.spaceOccupation[y][x] = true;
                        this.position = new Vector2d(x,y);
                        found = true;
                        break;
                    }
                }
            }
            if (!found){
                throw new FullWorldException("cannot create another animal, world is already full");
            }
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
                    this.spaceOccupation[this.position.getY()][this.position.getX()] = false;
                    this.position = newPos;
                    this.spaceOccupation[newPos.getY()][newPos.getX()] = true;
                }
                break;
            case BACKWARD:
                Vector2d nPos = this.position.subtract(this.direction.toUnitVector());
                if (validatePos(nPos)){
                    this.spaceOccupation[this.position.getY()][this.position.getX()] = false;
                    this.position = nPos;
                    this.spaceOccupation[nPos.getY()][nPos.getX()] = true;
                }
                break;
        }
    }

    private boolean validatePos(Vector2d position){
        int x = position.getX();
        int y = position.getY();
        return 0 <= Math.min(x, y) && Math.max(x, y) < 5 && !this.spaceOccupation[y][x];
    }

    @Override
    public String toString() {
        return this.position.toString() + ", " + this.direction.toString();
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

    public void handleDelete(){
        this.spaceOccupation[this.position.getY()][this.position.getX()] = false;
    }

//    @Override
//    public void close() throws Exception {
//        this.spaceOccupation[this.position.getY()][this.position.getX()] = false;
//    }
}