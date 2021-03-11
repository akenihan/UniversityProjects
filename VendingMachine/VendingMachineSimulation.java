import java.util.Scanner;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.control.Label;

/**
   This program simulates a vending machine.
*/
public class VendingMachineSimulation
{ 
   public static void main(String[] args) throws IOException
   { 
    Scanner in = new Scanner(System.in);
	System.out.println("Would you like to use the G)UI or the C)ommand Line?");
	String interfaceOption = in.nextLine().toUpperCase();
	VendingMachine machine = new VendingMachine();
	if(interfaceOption.equals("C"))
	{
		  VendingMachineMenu menu = new VendingMachineMenu();
		  menu.run(machine);
		  
	}
	
	if(interfaceOption.equals("G"))
	{
		VendingMachineGUI gui = new VendingMachineGUI();
		gui.run(machine, new String[0]);
	}
	  
  }
  
 /* @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    // Hold two buttons in an HBox
    HBox hBox = new HBox();
    hBox.setSpacing(10);
    hBox.setAlignment(Pos.CENTER);
	final Label lb1 = new Label("test");
    Button bt1 = new Button("Operator");
    Button bt2 = new Button("User");
    hBox.getChildren().add(bt1);
    hBox.getChildren().add(bt2);
	HBox hBox1 = new HBox();
	hBox1.setSpacing(10);
    hBox1.setAlignment(Pos.CENTER);
    // Create and register the handler
	bt1.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					System.out.println("button 1");
				}
			});
			
	bt2.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			System.out.println("button 2");
		}
	});
    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(hBox);
    BorderPane.setAlignment(hBox, Pos.CENTER);
    Scene scene = new Scene(borderPane, 200, 150);
	
	HBox hBox1 = new HBox();
	BorderPane borderPane1 = new BorderPane();
    borderPane1.setCenter(hBox1);
    BorderPane1.setAlignment(hBox, Pos.CENTER);
    Scene scene = new Scene(borderPane, 200, 150);
	
    primaryStage.setTitle("Vending Machine"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }*/
}
