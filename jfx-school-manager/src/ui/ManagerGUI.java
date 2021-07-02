package ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
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
    
    ArrayList<ExtraAsignatures> testAsignatures = new ArrayList<ExtraAsignatures>();
    Set<ExtraAsignatures> choosedAsignatures = new HashSet<ExtraAsignatures>();
    
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
    void MAINMENUEditStudent(ActionEvent event) throws IOException{
        showSelectAStudent();
        showEditStudent();
    }

    @FXML
    void RemoveStudent(ActionEvent event) throws IOException {
        showSelectAStudent();
    }

    //----------------------------------------------------------------- SELECT A STUDENT -----------------------------------------------------------------
    Student founded;

    @FXML
    private Pane SELECTASTUDENTsearchPane;

    @FXML
    private TableView<Student> SELECTASTUDENTtvStudent;

    @FXML
    private TableColumn<Student, String> SELECTASTUDENTtcFullName;

    @FXML
    private TableColumn<Student, String> SELECTASTUDENTtcId;

    @FXML
    private TableColumn<Student, String> SELECTASTUDENTtcCourse;

    @FXML
    private TextField SELECTASTUDENTtxtId;

    @FXML
    private Pane SELECTASTUDENTbasicPane;

    @FXML
    void SELECTASTUDENTaccept(ActionEvent event) throws IOException {
        selectedStudent = founded;
        showEditStudent();
    }

    @FXML
    void SELECTASTUDENTsearch(ActionEvent event) {
        String id = SELECTASTUDENTtxtId.getText();
        founded = manager.searchStudent(id);

        if(founded != null){
            SELECTASTUDENTtvStudent.setItems(FXCollections.observableArrayList(founded));
            SELECTASTUDENTtcFullName.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
            SELECTASTUDENTtcId.setCellValueFactory(new PropertyValueFactory<Student, String>("id"));
            SELECTASTUDENTtcCourse.setCellValueFactory(new PropertyValueFactory<Student, String>("course"));
            SELECTASTUDENTbasicPane.setVisible(false);
            SELECTASTUDENTsearchPane.setVisible(true);
        }

    }

    @FXML
    void SELECTASTUDENTback(ActionEvent event) throws IOException {
        showMainMenu();
    }
    
    //----------------------------------------------------------------- LIST ALL STUDENTS -----------------------------------------------------------------
    
    Student choosedStudent;

    @FXML
    private TableView<Student> LISTALLSTUDENTStvStudents;

    @FXML
    private TableColumn<Student, String> LISTALLSTUDENTStcFullName;

    @FXML
    private TableColumn<Student, String> LISTALLSTUDENTStcId;

    @FXML
    private TableColumn<Student, String> LISTALLSTUDENTStcCourse;

    @FXML
    private TableColumn<Student, Long> LISTALLSTUDENTStcCost;

    @FXML
    private TableColumn<Student, String> LISTALLSTUDENTStcExtraSubjects;

    @FXML
    private TableColumn<Student, String> LISTALLSTUDENTStcHasRelatives;

    @FXML
    private TableColumn<Student, String> LISTALLSTUDENTStcOwe;

    @FXML
    private TextField LISTALLSTUDENTSidSearched;

    @FXML
    void LISTALLSTUDENTSchoosingStudent(MouseEvent event) {
        if(event.getClickCount() == 2){
            choosedStudent = LISTALLSTUDENTStvStudents.getSelectionModel().getSelectedItem();
        }
    }
    
    @FXML
    void LISTALLSTUDENTSPaid(ActionEvent event) {
        if(choosedStudent != null){
            choosedStudent.setOws(NEGATION);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Hecho");
            alert.setHeaderText("El pago del estudiante fue registrado");
            alert.setContentText("Haga click en \"aceptar\" para continuar");
            alert.show();
            choosedStudent = null;
        }else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText("Ningun estudiante fue seleccionado");
            alert.setContentText("Eliga un estudiante e intente nuevamente");
            alert.show();
        }
    }
    
    @FXML
    void LISTALLSTUDENTSSearch(ActionEvent event) throws IOException {
        String studentId = LISTALLSTUDENTSidSearched.getText();
        if(studentId.equals("")){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText("Campo vacío");
            alert.setContentText("Escribe el nombre e intenta nuevamente");
            alert.show();
        }else{
            Student foundedStudents  = manager.searchStudent(studentId);
            showSearchedStudent(foundedStudents);
        }
    }

    @FXML
    void LISTALLSTUDENTSback(ActionEvent event) throws IOException{
        showMainMenu();
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
    void ADDSTUDENTadd(ActionEvent event) throws IOException {
        String names = ADDSTUDENTtxtName.getText();
        String lastNames = ADDSTUDENTtxtLastNames.getText();
        String id = ADDSTUDENTtxtId.getText();
        Courses course = ADDSTUDENTcbCourse.getSelectionModel().getSelectedItem();

        String fullName = names + " " + lastNames;
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
                    if(course != null){
                        completed = true;
                    }
                }
            }
        }

        if(completed){
            String msg = fullName + " con identificación " + id + " ha sido registrado(a) exitosamente en "+ course;
            
            ArrayList<ExtraAsignatures> asignatures = new ArrayList<ExtraAsignatures>();

             for(ExtraAsignatures asignature: choosedAsignatures){
                asignatures.add(asignature);
            }

            manager.addStudent(names, lastNames, course, hasRelatives, terapy, id, asignatures);

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("¡Hecho!");
            alert.setHeaderText("Matriculado exitosamente");
            alert.setContentText(msg);
            alert.showAndWait();
            showAddStudent();
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

    @FXML
    void ADDSTUDENTaddAsignature(MouseEvent event) {
        if(event.getClickCount() == 2){
            ExtraAsignatures choosed = ADDSTUDENTtvAvaibleAsignatures.getSelectionModel().getSelectedItem();
            choosedAsignatures.add(choosed);
            refreshAddStudentChoosedAsignatures();
        }
    }

    @FXML
    void ADDSTUDENTremoveAsignature(MouseEvent event) {
        if(event.getClickCount() == 2){
            ExtraAsignatures asignatureToRemove = ADDSTUDENTtvChoosedSignatures.getSelectionModel().getSelectedItem();
            choosedAsignatures.remove(asignatureToRemove);
            refreshAddStudentChoosedAsignatures();
        }
    }

    //-----------------------------------------------------------------  SEARCHED STUDENT -----------------------------------------------------------------

    @FXML
    private TableView<Student> SEARCHEDSTUDENTtv;

    @FXML
    private TableColumn<Student, String> SEARCHEDSTUDENTtcName;

    @FXML
    private TableColumn<Student, String> SEARCHEDSTUDENTtcId;

    @FXML
    private TableColumn<Student, String> SEARCHEDSTUDENTtcCourse;

    @FXML
    private TableColumn<Student, String> SEARCHEDSTUDENTtcExtra;

    @FXML
    private TableColumn<Student, Long> SEARCHEDSTUDENTtcCost;

    @FXML
    private TableColumn<Student, String> SEARCHEDSTUDENTtcOwe;

    @FXML
    private TableColumn<Student, String> SEARCHEDSTUDENTtcInclusion;

    @FXML
    void SEARCHEDSTUDENTPaid(ActionEvent event) {

    }

    @FXML
    void SEARCHEDSTUDENTPdidnotpay(ActionEvent event) {

    }

    @FXML
    void SEARCHEDSTUDENTback(ActionEvent event) throws IOException {
        showMainMenu();
    }

    //-----------------------------------------------------------------  EDIT STUDENT -----------------------------------------------------------------
    
    Student modifiedStudent;
    Student selectedStudent;
    
    @FXML
    private TextField EDITSTUDENTtxtName;

    @FXML
    private TextField EDITSTUDENTtxtLastNames;

    @FXML
    private ComboBox<Courses> EDITSTUDENTcbCourse;

    @FXML
    private TextField EDITSTUDENTtxtId;

    @FXML
    private CheckBox EDITSTUDENTcbHasRelatives;

    @FXML
    private CheckBox EDITSTUDENTcbHasnotRelatives;

    @FXML
    private CheckBox EDITSTUDENTcbHasTerapy;

    @FXML
    private CheckBox EDITSTUDENTcbHasnotTerapy;

    //This should be working with ExtraAsignatures
    @FXML
    private TableView<ExtraAsignatures> EDITSTUDENTtvChoosedSignatures;

    @FXML
    private TableColumn<ExtraAsignatures, String> EDITSTUDENTtcChoosedSignatures;

    
    @FXML
    private TableView<ExtraAsignatures> EDITSTUDENTtvAvaibleAsignatures;

    @FXML
    private TableColumn<ExtraAsignatures, String> EDITSTUDENTtcAvaibleAsignatures;

    @FXML
    void EDITSTUDENTaddAsignature(MouseEvent event) {
        ExtraAsignatures choosed = EDITSTUDENTtvAvaibleAsignatures.getSelectionModel().getSelectedItem();
        choosedAsignatures.add(choosed);
        refreshEditStudentChoosedAsignatures();
    }

    @FXML
    void EDITSTUDENTback(ActionEvent event) throws IOException {
        showMainMenu();    
    }

    @FXML
    void EDITSTUDENTedit(ActionEvent event) {

    }

    @FXML
    void EDITSTUDENTremoveAsignature(MouseEvent event) {
        if(event.getClickCount() == 2){
            ExtraAsignatures asignatureToRemove = EDITSTUDENTtvChoosedSignatures.getSelectionModel().getSelectedItem();
            manager.removeSubject(selectedStudent, asignatureToRemove);
            refreshEditStudentChoosedAsignatures();
        }
    }
    //-----------------------------------------------------------------  SHOW WINDOWS -----------------------------------------------------------------

    private void showSearchedStudent(Student foundedStudents) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchedStudent.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        popUpStage.setScene(scene);
        popUpStage.show();
        mainStage.hide();

       
       if(foundedStudents != null){
        ObservableList<Student> obStudents = FXCollections.observableArrayList(foundedStudents);
        LISTALLSTUDENTStvStudents.setItems(obStudents);

        LISTALLSTUDENTStcFullName.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        LISTALLSTUDENTStcCost.setCellValueFactory(new PropertyValueFactory<Student, Long>("cost"));
        LISTALLSTUDENTStcHasRelatives.setCellValueFactory(new PropertyValueFactory<Student, String>("hasRelatives"));
        LISTALLSTUDENTStcCourse.setCellValueFactory(new PropertyValueFactory<Student, String>("course"));
        LISTALLSTUDENTStcExtraSubjects.setCellValueFactory(new PropertyValueFactory<Student, String>("extraSubjects"));

       }else{
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("¡Error!");
        alert.setHeaderText("No hay estudiantes para mostrar");
        alert.setContentText("Intenta con otro nombre");
        alert.show();
       }
                 
    }

    private void showMainMenu() throws IOException{
        founded = null;
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

        ObservableList<Student> students = FXCollections.observableArrayList(manager.getStudents());
        LISTALLSTUDENTStvStudents.setItems(students);
        LISTALLSTUDENTStcFullName.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        LISTALLSTUDENTStcCourse.setCellValueFactory(new PropertyValueFactory<Student, String>("course"));
        LISTALLSTUDENTStcCost.setCellValueFactory(new PropertyValueFactory<Student, Long>("cost"));
        LISTALLSTUDENTStcId.setCellValueFactory(new PropertyValueFactory<Student, String>("id"));
        LISTALLSTUDENTStcHasRelatives.setCellValueFactory(new PropertyValueFactory<Student, String>("hasRelatives"));
        LISTALLSTUDENTStcOwe.setCellValueFactory(new PropertyValueFactory<Student, String>("ows"));
        LISTALLSTUDENTStcExtraSubjects.setCellValueFactory(new PropertyValueFactory<Student, String>("asignaturesString"));

        popUpStage.setScene(scene);
        mainStage.hide();
        popUpStage.show();
    }

    private void showAddStudent() throws IOException{
        choosedAsignatures.clear();
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

    private void refreshAddStudentChoosedAsignatures(){
        ArrayList<ExtraAsignatures> asignaturesArray = new ArrayList<ExtraAsignatures>();

        for(ExtraAsignatures asignature: choosedAsignatures){
            asignaturesArray.add(asignature);
        }
        
        if(asignaturesArray.size() != 0){
            ObservableList<ExtraAsignatures> asignaturesOb = FXCollections.observableArrayList(asignaturesArray);
            ADDSTUDENTtvChoosedSignatures.setItems(asignaturesOb);
            ADDSTUDENTtcChoosedSignatures.setCellValueFactory(new PropertyValueFactory<ExtraAsignatures, String>("name"));
        }else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText("Ningun elemento fue elegido");
            alert.setContentText("Intente nuevamente");
            alert.show();
        }
    }

    private void showEditStudent() throws IOException{
        if(selectedStudent != null){
        choosedAsignatures.clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditStudent.fxml"));
        loader.setController(this); 
        Parent root = loader.load();
        
        ArrayList<Courses> courses = manager.getCourses();
        ArrayList<ExtraAsignatures> asignatures = manager.getAsignatures();

        ObservableList<Courses> coursesOb = FXCollections.observableArrayList(courses);
        ObservableList<ExtraAsignatures> extrasOb = FXCollections.observableArrayList(asignatures);
              
        EDITSTUDENTcbCourse.setItems(coursesOb);
        EDITSTUDENTtvAvaibleAsignatures.setItems(extrasOb);
        EDITSTUDENTtcAvaibleAsignatures.setCellValueFactory(new PropertyValueFactory<ExtraAsignatures, String>("name"));

        Scene scene = new Scene(root);
        popUpStage.setScene(scene);
        mainStage.hide();

        EDITSTUDENTtxtName.setText(selectedStudent.getName());
        EDITSTUDENTtxtLastNames.setText(selectedStudent.getLastName());
        EDITSTUDENTtxtId.setText(selectedStudent.getId());
        //Just for testing purposes.
        EDITSTUDENTcbCourse.setValue(Courses.ONCE);
        
        if(selectedStudent.getHasRelatives().equals(AFFIRMATION)){
            EDITSTUDENTcbHasRelatives.setSelected(true);
        }else{
            EDITSTUDENTcbHasnotRelatives.setSelected(true);
        }

        if(selectedStudent.getHasTerapy().equals(AFFIRMATION)){
            EDITSTUDENTcbHasTerapy.setSelected(true);
        }else{
            EDITSTUDENTcbHasnotTerapy.setSelected(true);
        }

        EDITSTUDENTtvChoosedSignatures.setItems(FXCollections.observableArrayList(selectedStudent.getSubject()));
        EDITSTUDENTtcChoosedSignatures.setCellValueFactory(new PropertyValueFactory<ExtraAsignatures, String>("name"));

        popUpStage.show();
        }
    }

    private void refreshEditStudentChoosedAsignatures(){
        ArrayList<ExtraAsignatures> asignaturesArray = new ArrayList<ExtraAsignatures>();

        //This saves the registred asignatures.
        ArrayList<ExtraAsignatures> oldAsignatures = selectedStudent.getSubject();

        for(ExtraAsignatures asignature: oldAsignatures){
            asignaturesArray.add(asignature);
        }


        for(ExtraAsignatures asignature: choosedAsignatures){
            asignaturesArray.add(asignature);
        }
        
        if(asignaturesArray.size() != 0){
            ObservableList<ExtraAsignatures> asignaturesOb = FXCollections.observableArrayList(asignaturesArray);
            EDITSTUDENTtvChoosedSignatures.setItems(asignaturesOb);
            EDITSTUDENTtcChoosedSignatures.setCellValueFactory(new PropertyValueFactory<ExtraAsignatures, String>("name"));
        }else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText("Ningun elemento fue elegido");
            alert.setContentText("Intente nuevamente");
            alert.show();
        }
    }

    private void showSelectAStudent() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectAStudent.fxml"));
        loader.setController(this); 
        Parent root = loader.load();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
}
