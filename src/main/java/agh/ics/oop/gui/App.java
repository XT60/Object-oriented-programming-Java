package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;

import javafx.geometry.HPos;
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
    private int columnWidth = 30;
    private int rowWidth = 30;
    @Override
    public void start(Stage primaryStage) throws Exception {
        try{
            engine.run();
            out.print(map.toString());
        }
        catch (IllegalArgumentException exc){
            System.out.println(exc.toString());
        }
        draw(primaryStage);


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

    private void draw(Stage primaryStage){
        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);

        Vector2d lowerLeft = map.lowerLeftMapCorner();
        Vector2d upperRight = map.upperRightMapCorner();
        int mapHeight = upperRight.y - lowerLeft.y + 1;
        int mapWidth =  upperRight.x - lowerLeft.x + 1;

        createGrid(gridPane, mapWidth, mapHeight);
        drawBorderLabels(gridPane, mapWidth, mapHeight, lowerLeft);

        for (int i = 1 ; i < mapHeight + 1; i++) {
            for (int j = 1; j < mapWidth + 1; j++) {
                int worldY = upperRight.y - i + 1;
                int worldX = lowerLeft.x + j - 1;
                Vector2d worldPos = new Vector2d(worldX, worldY);
                if (map.isOccupied(worldPos)){
                    Object obj = map.objectAt(worldPos);
                    addLabel(gridPane, obj.toString(), j, i);
                }
            }
        }

        Scene scene = new Scene(gridPane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createGrid(GridPane gridPane,  int mapWidth, int mapHeight){
        for (int i = 0 ; i < mapHeight + 1; i++) {
            gridPane.getRowConstraints().add(new RowConstraints(rowWidth));
        }
        for (int j = 0; j < mapWidth + 1; j++) {
            gridPane.getColumnConstraints().add(new ColumnConstraints(columnWidth));
        }
    }

    private void drawBorderLabels(GridPane gridPane, int mapWidth, int mapHeight, Vector2d lowerLeft){
        for (int i = 1; i < mapWidth + 1; i++) {
            String msg = Integer.toString(lowerLeft.x + i - 1);
            addLabel(gridPane, msg, i, 0);
        }

        for (int i = 1; i < mapHeight + 1; i++) {
            String msg = Integer.toString(lowerLeft.y + mapHeight - i);
            addLabel(gridPane, msg, 0, i);
        }

        addLabel(gridPane, "y/x", 0, 0);
    }

    private void addLabel(GridPane gridPane, String message, int columnIndex, int rowIndex ){
        Label label = new Label(message);
        gridPane.add(label, columnIndex, rowIndex, 1, 1);
        GridPane.setHalignment(label, HPos.CENTER);
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
