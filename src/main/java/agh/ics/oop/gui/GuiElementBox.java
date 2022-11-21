package agh.ics.oop.gui;

import agh.ics.oop.Animal;
import agh.ics.oop.IMapElement;
import agh.ics.oop.IPositionChangeObserver;
import agh.ics.oop.Vector2d;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.File;

public class GuiElementBox{
    private VBox vBox;
    private Label label;
    private boolean labelHasPosition;
    private String baseLabel;
    
    public GuiElementBox(IMapElement element){
        String path = "src/main/resources/" + element.getImageName();
        String absPath = new File(path).getAbsolutePath();
        Image image = new Image(absPath);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        
        if (element instanceof Animal){
            baseLabel = "Z ";
            labelHasPosition = true;
        }
        else{
            baseLabel = "Trawa";
            labelHasPosition = false;
        }
        
        label = new Label();
        vBox = new VBox(imageView, label);
        vBox.setAlignment(Pos.CENTER);
    }

    private void updateLabel(Vector2d newPosition){
        String newLabel = baseLabel;
        if(labelHasPosition){
            newLabel += newPosition.toString();
        }
        label.setText(newLabel);
    }

    public VBox getVBox(IMapElement element){
        updateLabel(element.getPosition());
        return vBox;
    }

}
