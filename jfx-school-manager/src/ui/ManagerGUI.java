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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Courses;
import model.ExtraAsignatures;
import model.Manager;
import model.Student;

public class ManagerGUI {
    
    //This variables are going to replace the booleans of the checkboxes for their values in a String. They are in spanish because the program is being developed for a colombian school.
    private final static String AFFIRMATION = "Sí";
    private final static String NEGATION = "No";

    Stage mainStage;
    Stage popUpStage;
    Manager manager;
    
    public ManagerGUI() throws IOException{
        manager = new Manager();
        
        mainStage = new Stage();
        popUpStage = new Stage();
        showMainMenu();
    }

    //----------------------------------------------------------------- MAIN MENU -----------------------------------------------------------------
    @FXML
    void MAINMENUAddStudent(ActionEvent event)throws IOException {
        showAddStudent();
    }

    @FXML
    void MAINMENUAllStudentsList(ActionEvent event) throws IOException{
        showStudentsList();
    }

    @FXML
    void MAINMENUEditStuednt(ActionEvent event) throws IOException{
        
    }

    @FXML
    void RemoveStudent(ActionEvent event) {
        
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

    //-----------------------------------------------------------------  ADD STUDENT -----------------------------------------------------------------

    @FXML
    private TextField ADDSTUDENTtxtName;

    @FXML
    private TextField ADDSTUDENTtxtLastNames;

    @FXML
    private ComboBox<Courses> ADDSTUDENTcbCourse;

    @FXML
    private TextField ADDSTUDENTtxtId;

    @FXML
    private CheckBox ADDSTUDENTcbHasRelatives;

    @FXML
    private CheckBox ADDSTUDENTcbHasnotRelatives;

    @FXML
    private CheckBox ADDSTUDENTcbHasTerapy;

    @FXML
    private CheckBox ADDSTUDENTcbHasnotTerapy;

    @FXML
    private TableView<ExtraAsignatures> ADDSTUDENTtvChoosedSignatures;

    @FXML
    private TableColumn<ExtraAsignatures, String> ADDSTUDENTtcChoosedSignatures;

    @FXML
    private TableView<ExtraAsignatures> ADDSTUDENTtvAvaibleAsignatures;

    @FXML
    private TableColumn<ExtraAsignatures, String> ADDSTUDENTtcAvaibleAsignatures;

    @FXML
    void ADDSTUDENTadd(ActionEvent event) {
        String names = ADDSTUDENTtxtName.getText();
        String lastNames = ADDSTUDENTtxtLastNames.getText();
        String id = ADDSTUDENTtxtId.getText();
        String course = ADDSTUDENTcbCourse.getSelectionModel().getSelectedItem()+"";

        String hasRelatives = "";
        if(ADDSTUDENTcbHasRelatives.isSelected()){
            hasRelatives = AFFIRMATION;
        }else if(ADDSTUDENTcbHasnotRelatives.isSelected()){
            hasRelatives = NEGATION;
        }

        String terapy = "";
        if(ADDSTUDENTcbHasTerapy.isSelected()){
            terapy = AFFIRMATION;
        }else if(ADDSTUDENTcbHasnotTerapy.isSelected()){
            terapy = NEGATION;
        }
        boolean completed = false;
        
        //Verification
        if(names != ""){
            if(lastNames != ""){
                if(id != ""){
                    if(course != ""){
                        completed = true;
                    }
                }
            }
        }

        if(completed){
            String msg = names + lastNames + " con identificación " + id + "ha sido registrado(a) exitosamente en "+ course;
            long cost = 0;
            manager.addStudent(names, course, hasRelatives, cost, terapy);

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("¡Hecho!");
            alert.setHeaderText("Matriculado exitosamente");
            alert.setContentText(msg);
            alert.show();
        }else{
            String msg = "Revisa los campos obligatorios y vuelve a intentar";
            
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText("Faltan campos por llenar");
            alert.setContentText(msg);
            alert.show();
        }
        
    }

    @FXML
    void ADDSTUDENTcancel(ActionEvent event) throws IOException{
        showMainMenu();
    }
    //-----------------------------------------------------------------  SHOW WINDOWS -----------------------------------------------------------------

    private void showSearchedStudent(ArrayList<Student> students) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchedStudent.fxml"));
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

    private void showMainMenu() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml")); 
        loader.setController(this); 
        Parent root = loader.load(); 
        Scene scene = new Scene(root); 
        mainStage.setScene(scene); 
        mainStage.show(); 
        popUpStage.hide();      
    }

    private void showStudentsList() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListAllStudents.fxml"));
        loader.setController(this); 
        Parent root = loader.load();
        Scene scene = new Scene(root);
        popUpStage.setScene(scene);
        mainStage.hide();
        popUpStage.show();
    }

    private void showAddStudent() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddStudent.fxml"));
        loader.setController(this); 
        Parent root = loader.load();
        
        ArrayList<Courses> courses = manager.getCourses();
        ArrayList<ExtraAsignatures> asignatures = manager.getAsignatures();

        ObservableList<Courses> coursesOb = FXCollections.observableArrayList(courses);
        ObservableList<ExtraAsignatures> extrasOb = FXCollections.observableArrayList(asignatures);
        
       
        ADDSTUDENTcbCourse.setItems(coursesOb);
        ADDSTUDENTtvAvaibleAsignatures.setItems(extrasOb);
        ADDSTUDENTtcAvaibleAsignatures.setCellValueFactory(new PropertyValueFactory<ExtraAsignatures, String>("name"));

        Scene scene = new Scene(root);
        popUpStage.setScene(scene);
        mainStage.hide();
        popUpStage.show();
    }
}
