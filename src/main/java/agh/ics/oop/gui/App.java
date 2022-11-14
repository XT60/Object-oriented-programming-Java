package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.Iterator;
import java.util.List;

import static java.lang.System.out;


public class App extends Application {

    IEngine engine;
    GrassField map;
    @Override
    public void start(Stage primaryStage) throws Exception {
        try{
            engine.run();
            out.print(map.toString());
        }
        catch (IllegalArgumentException exc){
            System.out.println(exc.toString());
        }

        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        gridPane.getColumnConstraints().add(new ColumnConstraints(30));
        gridPane.getRowConstraints().add(new RowConstraints(30));

        Label label = new Label("Zwierzak");
        Label label2 = new Label("Zwierzak2");

        gridPane.add(label, 2, 2, 1, 1);
        gridPane.add(label2, 2, 3, 1, 1);

        Scene scene = new Scene(gridPane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void init(){
        try {
            List<String> argList = getParameters().getRaw();
            MoveDirection[] directions = new OptionsParser().parse(listToArray(argList));
            Vector2d[] positions = {new Vector2d(1, 2), new Vector2d(2, 6), new Vector2d(0, 7)};
            map = new GrassField(10);
            engine = new SimulationEngine(directions, map, positions);
        }
        catch (IllegalArgumentException exc){
            System.out.println(exc.toString());
        }
    }

    private String[] listToArray(List<String> list){
        String[] arr = new String[list.size()];
        Iterator<String> iterator = list.iterator();
        int i = 0;
        while (iterator.hasNext()){
            arr[i] = iterator.next();
            i ++;
        }
        return arr;
    }
}
