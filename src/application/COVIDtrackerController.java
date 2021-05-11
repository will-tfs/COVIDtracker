package application;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class COVIDtrackerController implements Initializable {

    @FXML
    private TableView<COVID> Table;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtPopulation;

    @FXML
    private TextField txtMorts;

    @FXML
    private TableColumn<COVID,String> ProvinceColumn;

    @FXML
    private TableColumn<COVID,Double> PopulationColumn;

    @FXML
    private Button btnClear;

    @FXML
    private TableColumn<COVID,Double> DateColumn;

    @FXML
    private Button btnRecommencer;

    @FXML
    private TableColumn<COVID, Double> InfectionsColumn;

    @FXML
    private TableColumn<COVID,Double> MortsColumn;

    @FXML
    private Button btnModifier;

    @FXML
    private Text txt1;

    @FXML
    private ComboBox<String> cbProvince;

    @FXML
    private Button btnAjouter;

    @FXML
    private TextField txtInfections;
    
    
    private ObservableList<String> list=(ObservableList<String>) FXCollections.observableArrayList("Alberta", "British Columbia", "Manitoba","New Brunswick","Northwest Territories","Newfoundland and Labrador","Nova Scotia","Nunavut","Ontario","PEI","Quebec","Saskatachewan","Yukon");
    
    public ObservableList<COVID> COVIDData=FXCollections.observableArrayList();
    
    public ObservableList<COVID> getCOVIDData()
    {
    	return COVIDData;
    }
    
    @Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		cbProvince.setItems(list);
    
		DateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
		MortsColumn.setCellValueFactory(new PropertyValueFactory<>("Mort"));
		PopulationColumn.setCellValueFactory(new PropertyValueFactory<>("Population"));
		ProvinceColumn.setCellValueFactory(new PropertyValueFactory<>("Province"));
		InfectionsColumn.setCellValueFactory(new PropertyValueFactory<>("Infections"));
		Table.setItems(COVIDData);
		
		btnModifier.setDisable(false);
		btnClear.setDisable(false);
		btnRecommencer.setDisable(false);
		
		showCOVID(null);
		
		Table.getSelectionModel().selectedItemProperty().addListener((
				observable, oldValue, newValue)-> showCOVID (newValue));
			
	}
    
	@FXML
		public void ajouter()
		{
			COVID tmp=new COVID();
			
			tmp=new COVID();
			tmp.setDate(Double.parseDouble(txtDate.getText()));
			tmp.setPopulation(Double.parseDouble(txtPopulation.getText()));
			tmp.setInfections(Double.parseDouble(txtInfections.getText()));
			tmp.setMort(Double.parseDouble(txtMorts.getText()));
			tmp.setProvince(cbProvince.getValue());
			COVIDData.add(tmp);
			clearFields();
				 
		}

		@FXML
		void clearFields() 
		{
			cbProvince.setValue(null);
			txtMorts.setText("");
			txtPopulation.setText("");
			txtDate.setText("");
			txtInfections.setText("");

		}

	    
		
		public void showCOVID(COVID covid)
		{
			if(covid !=null)
			{
				cbProvince.setValue(covid.getProvince());
				txtDate.setText(Double.toString(covid.getDate()));	
				txtInfections.setText(Double.toString(covid.getInfections()));	
				txtPopulation.setText(Double.toString(covid.getPopulation()));	
				txtMorts.setText(Double.toString(covid.getMort()));	
				btnModifier.setDisable(false);
				btnClear.setDisable(false);
				btnRecommencer.setDisable(false);
			}
			else
			{
				clearFields();
			}
		}

		
		@FXML
		public void deleteCOVID()
		{
			int selectedIndex = Table.getSelectionModel().getSelectedIndex();
			if(selectedIndex >=0)
			{
				Alert alert=new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Effacer");
				alert.setContentText("Confirmer l'Effasage!");
				Optional<ButtonType> result=alert.showAndWait();
				if(result.get()==ButtonType.OK)
					Table.getItems().remove(selectedIndex);	
			}
			
		}
		
		public void verifNum()
		{
			txtDate.textProperty().addListener((observable,oldValue,newValue)->
			{
				if(!newValue.matches("^[0-9](\\.[0-9]+)?$"))
				{
					txtDate.setText(newValue.replaceAll("[^\\d*\\.]",""));
				}
			});
			
			txtPopulation.textProperty().addListener((observable,oldValue,newValue)->
			{
				if(!newValue.matches("^[0-9](\\.[0-9]+)?$"))
				{
					txtPopulation.setText(newValue.replaceAll("[^\\d*\\.]",""));
				}
			});
			
			txtInfections.textProperty().addListener((observable,oldValue,newValue)->
			{
				if(!newValue.matches("^[0-9](\\.[0-9]+)?$"))
				{
					txtInfections.setText(newValue.replaceAll("[^\\d*\\.]",""));
				}
			});
			
			txtMorts.textProperty().addListener((observable,oldValue,newValue)->
			{
				if(!newValue.matches("^[0-9](\\.[0-9]+)?$"))
				{
					txtMorts.setText(newValue.replaceAll("[^\\d*\\.]",""));
				}
			});
		}
		
	/// Verifier les Champs
		private boolean noEmptyInput()
		{
			String errorMessage="";
			if(txtDate.getText().trim().equals(""))
			{
				errorMessage+="Le Champ nom ne doit pas etre vide! \n";
			}
			if(txtPopulation.getText()==null|| txtPopulation.getText().length()==0)
			{
				errorMessage+="Le champ prenom ne doit pas etre vide! \n";
			}
			if(txtInfections.getText()==null||txtInfections.getText().length()==0)
			{
				errorMessage+="Le champ age ne doit pas etre vide! \n";
			}
			if(txtMorts.getText()==null||txtMorts.getText().length()==0)
			{
				errorMessage+="Le champ age ne doit pas etre vide! \n";
			}
			if(cbProvince.getValue()==null)
			{
				errorMessage+="Le champ department ne doit pas etre vode! \n";
			}
			
			if(errorMessage.length()==0)
			{
				return true;
				
			}
			else
			{
				Alert alert=new Alert(AlertType.ERROR);
				alert.setTitle("Champs Manquants");
				alert.setHeaderText("Completer les Champs Manquants");
				alert.setContentText(errorMessage);
				alert.showAndWait();
				return false;
			}
					
		}

}