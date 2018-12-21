
package cellphonebill;
import javafx.application.Application;
import javafx.geometry.Insets ; 
import javafx.geometry.Pos ; 
import javafx.scene.control.Label ; 
import javafx.scene.control.TextField ; 
import javafx.scene.control.TextArea ; 
import javafx.scene.layout.ColumnConstraints ;
import javafx.scene.layout.GridPane ; 
import javafx.scene.layout.HBox; 
import javafx.scene.layout.VBox ; 
import javafx.scene.text.Font ; 
import javafx.scene.text.FontWeight ; 
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class CellPhoneBill extends Application {
    private final String SHOP_NAME = " Singh's Cell Phone Billing" ; //for title     
    private final double OVER_LIMIT_CHARGE = 15.0 ;   //price/GB for overlimit use of Data
   private final Plan[] planPrices =    //array of all the plans available 
     {
     new Plan("Plan A","0.0 GB","$50.0"), 
     new Plan("Plan B","2.0 GB","$60.0"),
    new Plan("Plan C","4.0 GB","$70.0"),
    new Plan("Plan D","10.0 GB","$90.0")
   };
private TextArea receipt ; //for the receipt on the right side of border pane     


@Override
    public void start(Stage primaryStage) {
      BorderPane root = new BorderPane();  //using Border Pane for the GUI
      root.setTop(Title());
      root.setLeft(PlanPrices());
      root.setCenter(RecieveAndProcess());
      root.setRight(Receipt());
      root.setStyle("-fx-background-color:lightyellow");
      root.setPadding(new Insets(5,10,0,10));   //spacing between the nodes
      BorderPane.setMargin(receipt,new Insets(5,10,0,5));
       
      Scene scene = new Scene(root); 
      primaryStage.setTitle(SHOP_NAME);
      primaryStage.setScene(scene);
      primaryStage.show(); 
    }
//-----------------------------------------------------------------------------//
    private HBox Title() {  //in the top position of the Border Pane   
        HBox tbox = new HBox(); 
        tbox.setAlignment(Pos.CENTER);  //center position to make it look good
        Font font36B = Font.font("Calibri", FontWeight.SEMI_BOLD,36); //defining font
        Label lblTitle = new Label(SHOP_NAME);  
        lblTitle.setFont(font36B);
        tbox.getChildren().add(lblTitle); 
        return tbox ; 
        }      
    //------------------------------------------------------------------------//
    private VBox PlanPrices() //in the left position of the Border Pane
    {
      VBox ppBox = new VBox();        
      HBox hbox = new HBox();  // to display the Plans and Prices in a horizontal way nicely
      hbox.setAlignment(Pos.CENTER);
      Font fonter = Font.font("Verdana",FontWeight.BOLD,16);
      Label lblpln = new Label();
      lblpln.setFont(fonter);
      lblpln.setText("Plan & Prices");
      hbox.getChildren().add(lblpln);
      ppBox.getChildren().add(hbox);
      
      GridPane grid = new GridPane() ;   //to have equally spacing between Hbox
      grid.setHgap(12); 
      grid.setVgap(22);
      ColumnConstraints column1 = new ColumnConstraints(); 
      column1.setPrefWidth(60);
   grid.getColumnConstraints().add(column1); 
   //filling the grid with plans and services array
   
   
   for (int row=0; row<planPrices.length; row++) //adding labels in grid showing plans and prices
   { 
Label lblnme = new Label(); 
Font fontver = Font.font("Verdana",FontWeight.NORMAL,16); //defining the font  
lblnme.setFont(fontver);
lblnme.setText(String.format("%6s", planPrices[row].getName()));
grid.add(lblnme,0, row);
Label lbldata = new Label(); 
lbldata.setFont(fontver);
lbldata.setText(String.format("%8s",planPrices[row].getData()));
grid.add(lbldata, 1, row);
Label lblcharge = new Label(); 
lblcharge.setFont(fontver);
lblcharge.setText(String.format("%8s", planPrices[row].getChargePerGb()));
grid.add(lblcharge,2,row);  //adding all the labels in the grid
   }
   ppBox.getChildren().add(grid); 
   return ppBox ; 
   }
 //---------------------------------------------------------------------------//   
    private VBox RecieveAndProcess() //this method recieve inputs using TextFields and and defines the buttons
    {
        VBox cDbox = new VBox(); 
         HBox hbox = new HBox(); 
      hbox.setAlignment(Pos.CENTER);
      Font fonter = Font.font("Verdana",FontWeight.BOLD,16);
      Label lblcd = new Label();
      lblcd.setFont(fonter);
      lblcd.setText("Customer's Data");
      hbox.getChildren().add(lblcd);
      cDbox.getChildren().add(hbox);
      //making grid pane 
        GridPane grid = new GridPane(); 
        grid.setHgap(22); 
        grid.setVgap(22);
         ColumnConstraints column1 = new ColumnConstraints(); 
      column1.setPrefWidth(40);
   grid.getColumnConstraints().add(column1); 
   
   //adding the required textfields and label to the grid 
   TextField nameT = new TextField();
   Label name = new Label();
   Font fontm = Font.font("Verdana",FontWeight.NORMAL,16);  
   name.setFont(fontm);
   name.setText("Name");
   
   grid.add(nameT,3, 0);  //using row 4 to make look good 
   grid.add(name,4,0);
   
        TextField planT = new TextField();
   Label plan = new Label();
 plan.setFont(fontm);
   plan.setText("Plan");
 
   grid.add(planT,3, 1);
   grid.add(plan,4,1); 
   
         TextField gBT = new TextField();
   Label gB = new Label();
 gB.setFont(fontm);
   gB.setText("GB Used");
 
   grid.add(gBT,3, 2);
   grid.add(gB,4,2); 
 
   
 HBox hbox1 = new HBox();   //for Buttons  
        hbox1.setSpacing(20.0);
        hbox1.setPrefHeight(50.0);
        hbox1.setAlignment(Pos.CENTER);
        
//now defining the buttons         
        
Button btnCompute = new Button("Compute"); 
btnCompute.setPrefSize(80,20) ; 
btnCompute.setOnAction( e ->
{
      
    try{
     final double OVER_LIMIT_RATE = 15.0 ;
    //declaring the variables 
        double limit ;  
        double price ;
        double dataUsed = Double.valueOf(gBT.getText()); //fetching the value from TextField (user's input) 
        double bill ; 
        double overLimit ;     

       String planSelected = planT.getText(); //from TextField (user's input) 
   String nau = nameT.getText(); //from TextField (user's input) 
        //getting the right plan 
        if(planSelected.equals("A")||planSelected.equals("a"))
        {
            limit = 0.0; 
            price = 50.00 ; 
        }
        else if (planSelected.equals("B")||planSelected.equals("b"))
                {
                    limit = 2.0; 
                    price = 60.00;                  
                }
        else if (planSelected.equals("C")||planSelected.equals("c"))
        {
            limit = 4.0; 
            price = 70.00; 
        }
        else  if (planSelected.equals("D")||planSelected.equals("d"))
        {
            limit = 10.0; 
            price = 90.00;
        } 
        else  //if none of the plan is selected 
        {
            throw new NumberFormatException ("Warning!"); 
                    }        
        
        if(dataUsed<=limit)
        {
        overLimit = 0.00;
        }
        else 
        {
                        overLimit = Math.ceil(dataUsed - limit);
        }
             
    bill =  price + overLimit * OVER_LIMIT_CHARGE;  //formula to compute bill  
    
     
     receipt.setText(String.format("Customer's Bill \n\n"+
             "Name:%s \n"
             + "Please pay $%6.2f",nau, bill));
    }
catch (NumberFormatException d)
{
     System.out.println(); //to make it look good 
  receipt.setText(" Please be carefull! Illegal input detected!");
}
    
});


Button btnClear = new Button("Clear"); 
btnClear.setPrefSize(80,20);
btnClear.setOnAction(e -> 
{  
   receipt.clear();  // clears the receipt
   
   //clearig TextFields
   
   nameT.setText("");
   planT.setText("");
   gBT.setText("") ;
}); 


Button btnExit = new Button("Exit"); 
btnExit.setPrefSize(80,20);
btnExit.setOnAction(e -> System.exit(0));
btnExit.setOnKeyPressed ( e-> System.exit(0)); 


hbox1.getChildren().addAll(btnCompute, btnClear, btnExit);
cDbox.getChildren().addAll(grid);

cDbox.getChildren().add(hbox1);
     return cDbox ; 
    } //end of the method RecieveAndProcess 
    //-----------------------------------------------------------------------//

private TextArea Receipt() //receipt for displaying the output 
{
    receipt = new TextArea(); 
    Font fontver = Font.font("Verdana",FontWeight.NORMAL,14);
    receipt.setFont(fontver);
    receipt.setPrefWidth(380.0);   
    return receipt; 
}

    public static void main(String[] args) {
 
   
   System.out.println();
        launch(args);
    }
    
} //end of the class 
