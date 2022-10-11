package agh.ics.oop;

public enum MapDirection {
    NORTH, SOUTH, WEST, EAST;

    @Override
    public String toString() {
        switch (this){
            case NORTH:
                return "Północ";
            case SOUTH:
                return "Południe";
            case EAST:
                return "Wschod";
            case WEST:
                return "Zachód";
        }
        return "Nieznany kierunek";
    }

    public MapDirection next(){
        switch (this) {
            case NORTH:
                return MapDirection.EAST;
            case EAST:
                return MapDirection.SOUTH;
            case SOUTH:
                return MapDirection.WEST;
            default:
                return MapDirection.NORTH;
        }
    }

    public MapDirection previous(){
        switch (this) {
            case NORTH:
                return MapDirection.WEST;
            case WEST:
                return MapDirection.SOUTH;
            case SOUTH:
                return MapDirection.EAST;
            default:
                return MapDirection.NORTH;

        }
    }

    public Vector2d toUnitVector(){
        switch (this) {
            case NORTH:
                return new Vector2d(0, 1);
            case WEST:
                return new Vector2d(-1, 0);
            case SOUTH:
                return new Vector2d(0, -1);
            default:
                return new Vector2d(1, 0);

        }
    }
}
