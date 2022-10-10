package agh.ics.oop;
import static java.lang.System.out;


public class World {
    public static void main(String[] args) {
//        out.println("system wystartował");
//        Direction[] arr = convertToDirection(args);
//        run(arr);
//        out.println("system zakończył działanie");
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
    }
    static void run(Direction[] arr){
        int len = arr.length;
        out.println("Start");
        for(int i = 0; i < len; i++){
            switch(arr[i]){
                case FRONT:
                    out.println("Zwierzak idzie do przodu");
                    break;
                case BACK:
                    out.println("Zwierzak idzie do tyłu");
                    break;
                case RIGHT:
                    out.println("Zwierzak skręca w prawo");
                    break;
                case LEFT:
                    out.println("Zwierzak skręca w lewo");
                    break;
            }
        }
        out.println("Stop");
    }
    static Direction[] convertToDirection(String[] arr){
        int len = arr.length;
        Direction[] res = new Direction[len];
        for(int i = 0; i < len; i++){
            switch(arr[i]){
                case "f":
                    res[i] = Direction.FRONT;
                    break;
                case "b":
                    res[i] = Direction.BACK;
                    break;
                case "r":
                    res[i] = Direction.RIGHT;
                    break;
                case "l":
                    res[i] = Direction.LEFT;
                    break;
                default:
                    res[i] = Direction.ERROR;
            }
        }
        return res;
    }
}
