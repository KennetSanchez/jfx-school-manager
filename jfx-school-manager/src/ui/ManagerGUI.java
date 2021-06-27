package ui;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Manager;
import model.Student;

public class ManagerGUI {
    
    Stage mainStage;
    Stage popUpStage;
    Manager manager;
    
    public ManagerGUI(){
        manager = new Manager();
    }
    //----------------------------------------------------------------- LIST ALL STUDENTS -----------------------------------------------------------------
    
    @FXML
    private TableView<Student> LISTALLSTUDENTStvStudents;

    @FXML
    private TableColumn<Student, String> LISTALLSTUDENTStcFullName;

    @FXML
    private TableColumn<Student, String> LISTALLSTUDENTStcCourse;

    @FXML
    private TableColumn<Student, Long> LISTALLSTUDENTStcCost;

    @FXML
    private TableColumn<Student, String> LISTALLSTUDENTStcPayDate;

    @FXML
    private TableColumn<Student, String> LISTALLSTUDENTStcExtraSubjects;

    @FXML
    private TableColumn<Student, String> LISTALLSTUDENTStcHasRelatives;

    @FXML
    private TableColumn<Student, String> LISTALLSTUDENTStcOwe;

    @FXML
    private ComboBox<String> LISTALLSTUDENTStypeSort;

    @FXML
    private TextField LISTALLSTUDENTSNameSearched;
    
    @FXML
    void LISTALLSTUDENTSSearch(ActionEvent event) throws IOException {
        String studentName = LISTALLSTUDENTSNameSearched.getText();
        if(studentName.equals("")){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText("Campo vacío");
            alert.setContentText("Escribe el nombre e intenta nuevamente");
            alert.show();
        }else{
            ArrayList<Student> foundedStudents  = manager.searchStudent(studentName);
            showSearchedStudent(foundedStudents);
        }
    }

    @FXML
    void LISTALLSTUDENTSSort(ActionEvent event) {

    }

    //-----------------------------------------------------------------  SEARCHED STUDENT -----------------------------------------------------------------

    private void showSearchedStudent(ArrayList<Student> students) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchedStudent"));
        loader.setController(this);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        popUpStage.setScene(scene);
        popUpStage.show();
        mainStage.hide();

       
       if(students != null){
        ObservableList<Student> obStudents = FXCollections.observableArrayList(students);
        LISTALLSTUDENTStvStudents.setItems(obStudents);

        LISTALLSTUDENTStcFullName.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        LISTALLSTUDENTStcCost.setCellValueFactory(new PropertyValueFactory<Student, Long>("cost"));
        LISTALLSTUDENTStcHasRelatives.setCellValueFactory(new PropertyValueFactory<Student, String>("hasRelatives"));
        LISTALLSTUDENTStcCourse.setCellValueFactory(new PropertyValueFactory<Student, String>("course"));
        LISTALLSTUDENTStcExtraSubjects.setCellValueFactory(new PropertyValueFactory<Student, String>("extraSubjects"));
        LISTALLSTUDENTStcPayDate.setCellValueFactory(new PropertyValueFactory<Student, String>("payDate"));

       }else{
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("¡Error!");
        alert.setHeaderText("No hay estudiantes para mostrar");
        alert.setContentText("Intenta con otro nombre");
        alert.show();
       }
            
        
    }

}
