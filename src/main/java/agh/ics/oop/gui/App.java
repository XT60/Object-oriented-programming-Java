package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;

import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.*;

import static java.lang.System.out;


public class App extends Application implements IFrameChangeObserver{
    IEngine engine;
    GrassField map;
    private int columnWidth = 60;
    private int rowWidth = 60;

    private Vector2d lowerLeft;
    int mapHeight;
    int mapWidth;
    private Map <IMapElement, GuiElementBox> boxMap;

    private GridPane gridPane;
    private Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        Scene scene = new Scene(gridPane, 660, 660);
        primaryStage.setScene(scene);
        stage.show();
    }

    public void init(){
        boxMap = new HashMap<IMapElement, GuiElementBox>();
        try {
//            List<String> argList = getParameters().getRaw();
            List<String> argList = new ArrayList<String>();
            argList.add("left");
            argList.add("left");
            argList.add("right");
            argList.add("right");
            argList.add("forward");
            argList.add("forward");
            argList.add("backward");
            argList.add("backward");
            MoveDirection[] directions = new OptionsParser().parse(listToArray(argList));
            Vector2d[] positions = {new Vector2d(4, 4), new Vector2d(6, 4)};
            map = new GrassField(10);
            SimulationEngine engin = new SimulationEngine(directions, map, positions);
            engin.setObserver(this);
            engin.moveDelay = 500;
            engine = (IEngine)engin;
            initializeGridPane();

            Thread thread = new Thread((Runnable)engine);
            thread.start();
        }
        catch (IllegalArgumentException exc){
            System.out.println(exc.toString());
        }
        }

    private void initializeGridPane(){
        gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);

        lowerLeft = map.lowerLeftMapCorner();
        Vector2d upperRight = map.upperRightMapCorner();
        mapHeight = upperRight.y - lowerLeft.y + 1;
        mapWidth =  upperRight.x - lowerLeft.x + 1;

        createGrid(gridPane, mapWidth, mapHeight);
        drawBorderLabels(gridPane, mapWidth, mapHeight, lowerLeft);

        for (int i = 1 ; i < mapHeight + 1; i++) {
            for (int j = 1; j < mapWidth + 1; j++) {
                int worldY = upperRight.y - i + 1;
                int worldX = lowerLeft.x + j - 1;
                Vector2d worldPos = new Vector2d(worldX, worldY);
                if (map.isOccupied(worldPos)){
                    IMapElement element = (IMapElement)map.objectAt(worldPos);
                    VBox vBox;
                    if (boxMap.containsKey(element)){
                        vBox = boxMap.get(element).vBox;
                    }
                    else{
                        GuiElementBox elemBox = new GuiElementBox(element);
                        boxMap.put(element, elemBox);
                        vBox = elemBox.vBox;
                    }
                    gridPane.add(vBox, j, i, 1, 1);
                    GridPane.setHalignment(vBox, HPos.CENTER);

                }
            }
        }
    }

    private boolean isGridPositionInBounds(Vector2d gridPosition){
        return 0 < gridPosition.x && gridPosition.x < mapWidth &&
                0 < gridPosition.y && gridPosition.y < mapHeight;
    }
    private Vector2d convertToGridPosition(Vector2d worldPosition){
        int x = worldPosition.x - lowerLeft.x + 1;
        int y = lowerLeft.y + mapHeight - worldPosition.y;
        return new Vector2d(x, y);
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

    @Override
    public void newFrame(IMapElement element) {
        GuiElementBox elementBox = boxMap.get(element);
        Vector2d newPosition = element.getPosition();
//        out.println(newPosition.toString() + element.getDirection().toString());
        if (elementBox.didPositionChange(newPosition)){
            VBox vBox = elementBox.vBox;
            ObservableList<Node> children = gridPane.getChildren();
            children.remove((Node)vBox);
            Vector2d gridPosition = convertToGridPosition(newPosition);
            if (isGridPositionInBounds(gridPosition)){
                VBox overlappedVBox = elementBox.overlappedVBox;
                if (overlappedVBox != null){
                    Vector2d oldGridPosition = convertToGridPosition(elementBox.getOldPosition());
                    gridPane.add(overlappedVBox, oldGridPosition.x , oldGridPosition.y, 1, 1);
                    elementBox.overlappedVBox = null;
                }
                for (Node child : children) {
                    Integer column = GridPane.getColumnIndex(child);
                    Integer row = GridPane.getRowIndex(child);
                    if (column != null && row != null && column.intValue() == gridPosition.x && row.intValue() == gridPosition.y) {
                        elementBox.overlappedVBox = (VBox)child;
                        children.remove(child);
                        break;
                    }
                }
                gridPane.add(vBox, gridPosition.x , gridPosition.y, 1, 1);
            }
//            out.println("added to: " + gridPosition.toString());
        }
        elementBox.updateVBox(element);
    }

}

