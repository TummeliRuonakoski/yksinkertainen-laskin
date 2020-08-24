package application;
	
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class Main extends Application {
	
	String laskutoimitus = "";
	Font kirjasinlaji = new Font(16);
	
	@Override
	public void start(Stage näyttämö) {
		näyttämö.setTitle("Laskin");
		BorderPane päätaso = new BorderPane();
		Scene kulissi = new Scene(päätaso,400,500);
		VBox luvut = new VBox(15);
		Label ensimmäinenLabel = new Label("ensimmäinen luku");
		Label toinenLabel = new Label("toinen luku");
		TextField lukuyksi = new TextField();
		TextField lukukaksi = new TextField();
		Label tulosLabel = new Label("tulos");
		TextField tulos = new TextField();
		tulos.setEditable(false);
		päätaso.setPadding(new Insets(10, 20, 20, 20));
		ensimmäinenLabel.setFont(kirjasinlaji);
		toinenLabel.setFont(kirjasinlaji);
		tulosLabel.setFont(kirjasinlaji);
		lukuyksi.setFont(kirjasinlaji);
		lukukaksi.setFont(kirjasinlaji);
		
		
		
		luvut.getChildren().addAll(ensimmäinenLabel, lukuyksi, toinenLabel, lukukaksi);		
		VBox radiopainikePystylaatikko = new VBox(15);
		radiopainikePystylaatikko.setPadding(new Insets(10, 0, 0, 0));
		
		ToggleGroup painikeryhmä = new ToggleGroup();
		RadioButton radiopainike1 = new RadioButton();
		RadioButton radiopainike2 = new RadioButton();
		RadioButton radiopainike3 = new RadioButton();
		RadioButton radiopainike4 = new RadioButton();
		
		radiopainike1.setText("+");
		radiopainike2.setText("-");
		radiopainike3.setText("/");
		radiopainike4.setText("*");
		
		radiopainike1.setToggleGroup(painikeryhmä);
		radiopainike2.setToggleGroup(painikeryhmä);
		radiopainike3.setToggleGroup(painikeryhmä);
		radiopainike4.setToggleGroup(painikeryhmä);
		
		painikeryhmä.selectedToggleProperty().addListener(new ChangeListener<Toggle>()  
        { 

			@Override
			public void changed(ObservableValue<? extends Toggle> arg0, Toggle arg1, Toggle arg2) {


				if (painikeryhmä.getSelectedToggle() == radiopainike1) {
					laskutoimitus = "+";
				} else if (painikeryhmä.getSelectedToggle() == radiopainike2) {
					laskutoimitus = "-";
				} else if (painikeryhmä.getSelectedToggle() == radiopainike3) {
					laskutoimitus = "/";
				} else {
					laskutoimitus = "*";
				}
				
			} 
        });
		
		radiopainikePystylaatikko.getChildren().addAll(radiopainike1,radiopainike2,radiopainike3,radiopainike4, tulosLabel, tulos);
		
		HBox painikeVaakalaatikko = new HBox(15);
		painikeVaakalaatikko.setPadding(new Insets(10, 0, 10, 0));
		Button laske = new Button("laske");
		laske.setOnAction(e -> {
			double d_tulos = 0;
			try {
			double ensimmäinenluku = Double.parseDouble(lukuyksi.getText());
			double toinenluku = Double.parseDouble(lukukaksi.getText());
			if(laskutoimitus == "+") {
				d_tulos = ensimmäinenluku + toinenluku;
			}
			else if(laskutoimitus == "-") {
				d_tulos = ensimmäinenluku - toinenluku;
			}
			else if(laskutoimitus == "/") {
				d_tulos = ensimmäinenluku / toinenluku;
			}
			else if(laskutoimitus == "*") {
				d_tulos = ensimmäinenluku * toinenluku;
			}
			tulos.setText("" + d_tulos);
		
			}
			catch(NumberFormatException n) { 
				Alert ilmoitus = new Alert(AlertType.ERROR);
				ilmoitus.setHeaderText("Et syöttänyt lukua");
				ilmoitus.show();
			}
	
		});
		Button sulje = new Button ("sulje");
		sulje.setOnAction(e -> {
			näyttämö.close();
		});
		
		
		painikeVaakalaatikko.getChildren().addAll(laske, sulje);
		
		päätaso.setTop(luvut);
		päätaso.setCenter(radiopainikePystylaatikko);
		päätaso.setBottom(painikeVaakalaatikko);
		
		näyttämö.setScene(kulissi);
		näyttämö.show();
		
		
	}
	
	public static void main(String[] args) {
		launch(args); 
	}
}
