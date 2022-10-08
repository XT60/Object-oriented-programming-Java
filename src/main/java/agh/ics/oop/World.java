package agh.ics.oop;
import static java.lang.System.out;


public class World {
    public static void main(String[] args) {
        out.println("system wystartował");
        Direction[] arr = convertToDirection(args);
        run(arr);
        out.println("system zakończył działanie");
    }
    static void run(Direction[] arr){
//        out.println("zwierzak idzie do przodu");
        int len = arr.length;
//        String result  = "";
//        for(int i = 0; i < len - 1; i++){
//            result += arr[i] + ", ";
//        }
//        out.println(result + arr[len - 1]);
        out.println("Start");
        for(int i = 0; i < len; i++){
            switch(arr[i]){
                case f:
                    out.println("Zwierzak idzie do przodu");
                    break;
                case b:
                    out.println("Zwierzak idzie do tyłu");
                    break;
                case r:
                    out.println("Zwierzak skręca w prawo");
                    break;
                case l:
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
                    res[i] = Direction.f;
                    break;
                case "b":
                    res[i] = Direction.b;
                    break;
                case "r":
                    res[i] = Direction.r;
                    break;
                case "l":
                    res[i] = Direction.l;
                    break;
            }
        }
        return res;
    }
}
//13
