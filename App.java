package joshs.bendcalculator;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class App extends Application 
{
    public static void main(String[] args)
    {
        launch(args);
    }
        
    private TextField radiusTextField;
    private TextField thicknessTextField;
    private TextField numOfBendsTextField;
    private Button calcBendAllowance;
    private Label resultLabelOne;
    private Label resultLabelTwo;
   
    @Override
    public void start(Stage stage) 
    {
        Image bendImage = new Image("file:C:\\Users\\joshs\\Documents\\NetBeansProjects\\BendCalculator\\Images\\BendAllowance.jpg");
        ImageView viewBendImage = new ImageView(bendImage);
        viewBendImage.setFitWidth(150);
        viewBendImage.setFitHeight(150);
        viewBendImage.setPreserveRatio(true);
        
        Label thicknessLabel = new Label("Material Thickness: ");
        Label radiusLabel = new Label("Bend Radius: ");
        Label numofBends = new Label("Number of Bends: ");
        
        radiusTextField = new TextField();
        thicknessTextField = new TextField();
        numOfBendsTextField = new TextField();
        
        calcBendAllowance = new Button("Calculate Allowances");
        resultLabelOne = new Label();
        resultLabelTwo= new Label();
        calcBendAllowance.setOnAction(new BendAllowanceCalcButtonHandler());
        
        HBox imageBox = new HBox(viewBendImage);
        imageBox.setPadding(new Insets(5));
        imageBox.setAlignment(Pos.CENTER);
        
        HBox hbox1 = new HBox(5, radiusLabel, radiusTextField);
        hbox1.setPadding(new Insets(5));
        hbox1.setAlignment(Pos.CENTER);
        
        HBox hbox2 = new HBox(5, thicknessLabel, thicknessTextField);
        hbox2.setPadding(new Insets(5));
        hbox2.setAlignment(Pos.CENTER);
        
        HBox hbox3 = new HBox(5, numofBends, numOfBendsTextField);
        hbox3.setPadding(new Insets(5));
        hbox3.setAlignment(Pos.CENTER);
        
        HBox hbox4 = new HBox(5, calcBendAllowance);
        hbox4.setPadding(new Insets(5));
        hbox4.setAlignment(Pos.CENTER);
        
        HBox hbox5 = new HBox(5, resultLabelOne);
        hbox5.setPadding(new Insets(5));
        hbox5.setAlignment(Pos.CENTER);
        
        HBox hbox6 = new HBox(5, resultLabelTwo);
        hbox6.setPadding(new Insets(5));
        hbox6.setAlignment(Pos.CENTER);
        
        VBox vbox = new VBox(5, imageBox, hbox1, hbox2, hbox3, hbox4, hbox5, hbox6);
        vbox.setPadding(new Insets(5));
        vbox.setAlignment(Pos.CENTER);
        
        Scene mainScene = new Scene(vbox);
        
        stage.setScene(mainScene);
        stage.setTitle("Bend Allowance Calculator");
        stage.show();
    }
    
    class BendAllowanceCalcButtonHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            String thickness, radius, numOfBends;
            double r = 0, t = 0, b = 0, stainlessAllowance, regularAllowance;
            
            thickness = (thicknessTextField.getText());
            radius = (radiusTextField.getText());
            numOfBends = (numOfBendsTextField.getText());
            
            if(thickness.isEmpty() == true || radius.isEmpty() == true || numOfBends.isEmpty() == true)
            {
                resultLabelOne.setText("Please enter positive numbers!");
            }
            else if(thickness.equals(".") || radius.equals(".") || numOfBends.equals("."))
            {
                resultLabelOne.setText("Please enter positive numbers!");
            }
            else if(Functions.isNumber(thickness, radius, numOfBends) == true)
            {
                r = Double.parseDouble(radius);
                t = Double.parseDouble(thickness);
                b = Double.parseDouble(numOfBends);
            }
            else
            {
                resultLabelOne.setText("Please enter positive numbers!");
            }
            if(r <= 0 || t <= 0 || b <= 0)
            {
                resultLabelOne.setText("Please enter positive numbers!");
            }
            else
            {
                stainlessAllowance = (Functions.stainlessAllowanceCalc(r, t) * b);
                regularAllowance = (Functions.regularAllowanceCalc(r, t) * b);
                resultLabelOne.setText(String.format("Regular Allowance: %.2f", regularAllowance));
                resultLabelTwo.setText(String.format("Stainless Allowance: %.2f", stainlessAllowance));
            }
        }
    }
}