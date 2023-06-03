    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
     */
    package studentaddressbook;

    import java.io.BufferedReader;
    import java.io.BufferedWriter;
    import java.io.File;
    import java.io.FileReader;
    import java.io.FileWriter;
    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Optional;
    import javafx.application.Application;
    import static javafx.application.Application.launch;
    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;
    import javafx.collections.transformation.FilteredList;
    import javafx.geometry.Insets;
    import javafx.scene.Node;
    import javafx.scene.Scene;
    import javafx.scene.control.Alert;
    import javafx.scene.control.Alert.AlertType;
    import javafx.scene.control.Button;
    import javafx.scene.control.ButtonBar;
    import javafx.scene.control.ButtonType;
    import javafx.scene.control.DialogPane;
    import javafx.scene.control.Label;
    import javafx.scene.control.PasswordField;
    import javafx.scene.control.TableColumn;
    import javafx.scene.control.TableRow;
    import javafx.scene.control.TableView;
    import javafx.scene.control.TextField;
    import javafx.scene.control.cell.PropertyValueFactory;
    import javafx.scene.image.Image;
    import javafx.scene.image.ImageView;
    import javafx.scene.input.MouseEvent;
    import javafx.scene.layout.AnchorPane;
    import javafx.scene.paint.Color;
    import javafx.scene.text.Font;
    import javafx.scene.text.FontWeight;
    import javafx.scene.text.Text;
    import javafx.stage.Stage;

    /**
     *
     * @author nikka
     */

    public class StudentAddressBook extends Application {

        private ObservableList<StudentAddress> studentAddresses = FXCollections.observableArrayList();
        private final TableView<StudentAddress> studentTableView = new TableView<>();
        private StudentAddress selectedRowData;

        private Stage primaryStage;
        private Stage secondaryStage;
        
    @Override
    public void start(Stage primaryStage) {
        Image iCon = new Image("C:\\Users\\nikka\\OneDrive\\Documents\\NetBeansProjects\\StudentAddressBook\\src\\studentaddressbook\\icon.png");
        primaryStage.getIcons().add(iCon);
        this.primaryStage = primaryStage;

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(600, 310);
        anchorPane.setStyle("-fx-background-color: #78909C;");
        
        ImageView backgroundImageView = new ImageView(
        new Image("C:\\Users\\nikka\\OneDrive\\Documents\\NetBeansProjects\\StudentAddressBook\\src\\studentaddressbook\\lighterbg.png"));
        backgroundImageView.fitWidthProperty().bind(anchorPane.widthProperty());
        backgroundImageView.fitHeightProperty().bind(anchorPane.heightProperty());

        // Log in elements username textfield, password textfield, clear button, login button
        TextField usernameTextField = new TextField();
        usernameTextField.setLayoutX(235);
        usernameTextField.setLayoutY(230);
        usernameTextField.setPromptText("Username");
        usernameTextField.setStyle("-fx-border-color: black; -fx-border-width: 0 0 1 0; -fx-border-radius: 0;");
        usernameTextField.setStyle("-fx-background-radius: 20;");
        
        PasswordField passwordTextField = new PasswordField();
        passwordTextField.setLayoutX(235);
        passwordTextField.setLayoutY(265);
        passwordTextField.setPromptText("Password");
        passwordTextField.setStyle("-fx-border-color: black; -fx-border-width: 0 0 1 0; -fx-border-radius: 0;");
        passwordTextField.setStyle("-fx-background-radius: 20;");

        Button loginButton = new Button("Log In");
        loginButton.setLayoutX(335);
        loginButton.setLayoutY(300);
       // loginButton.setStyle("");
        loginButton.setStyle("-fx-background-radius: 20; -fx-background-color: #37474F; -fx-text-fill: white;");
        loginButton.setOnAction(e -> {
            String userTextGet = usernameTextField.getText();
            String passwordTextGet = passwordTextField.getText();
        
            // check if tama credentials
            if (userTextGet.equals("admin") && passwordTextGet.equals("admin")) {
                try {
                    openSecondaryStage();
                } catch (IOException ex) {
                   
                }
            } else {
                showAlert("Invalid credentials. Please try again.");
            }
        });

        Button clearButton = new Button("Clear");
        clearButton.setLayoutX(235);
        clearButton.setLayoutY(300);
        clearButton.setStyle("-fx-background-color: #37474F; -fx-text-fill: white; -fx-background-radius: 20;");
    //    clearButton.setStyle("-fx-background-radius: 10;");
        clearButton.setOnAction(e -> {
            usernameTextField.clear();
            passwordTextField.clear();
        });

        Text loginText = new Text("Log In");
        loginText.setLayoutX(240);
        loginText.setLayoutY(210);
        loginText.setFont(Font.font("Times New Roman", FontWeight.BOLD, 48));
        loginText.setFill(Color.web("#37474F"));
        loginText.setStroke(Color.BLACK);
        loginText.setStrokeWidth(0.8);

        ImageView loginPic = new ImageView(new Image("C:\\Users\\nikka\\OneDrive\\Documents\\NetBeansProjects\\StudentAddressBook\\src\\studentaddressbook\\LoginHuman.png"));
        loginPic.setLayoutX(262);
        loginPic.setLayoutY(80);
        loginPic.setFitWidth(90);
        loginPic.setFitHeight(90);

        anchorPane.getChildren().addAll(backgroundImageView, usernameTextField, passwordTextField, loginButton,
                clearButton, loginText, loginPic);
        anchorPane.setPadding(new Insets(10));

        Scene scene = new Scene(anchorPane);
        primaryStage.setTitle("Login Page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

  private void openSecondaryStage() throws IOException {
      Image iCon = new Image("C:\\Users\\nikka\\OneDrive\\Documents\\NetBeansProjects\\StudentAddressBook\\src\\studentaddressbook\\icon.png");
      secondaryStage = new Stage();
      secondaryStage.getIcons().add(iCon);

      AnchorPane root = new AnchorPane();
      root.setPrefSize(750, 500);
      root.setStyle("-fx-background-color: #CFD8DC;");

      Text manageLabel = new Text("Manage Student Addresses");
      manageLabel.setLayoutX(20);
      manageLabel.setLayoutY(80);
      manageLabel.setFont(new Font("Times New Roman", 48));
      manageLabel.setFill(Color.web("#455A64"));
         
      TextField searchTextField = new TextField();
      searchTextField.setLayoutX(20);
      searchTextField.setLayoutY(131);
      searchTextField.setPromptText("Search");
      searchTextField.setStyle("-fx-background-radius: 20;");
      
      Button buttonSearch = new Button("🔍");
      buttonSearch.setLayoutX(178);
      buttonSearch.setLayoutY(131);
      buttonSearch.setStyle("-fx-background-color: #37474F; -fx-text-fill: white; -fx-background-radius: 20;");

      Button buttonDelete = new Button("Delete");
      buttonDelete.setLayoutX(500);
      buttonDelete.setLayoutY(131);
      buttonDelete.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-background-radius: 20;");
      buttonDelete.setOnAction(e -> {
        // Check if a row is selected
        if (selectedRowData != null) {
            // Show confirmation dialog
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Confirmation");

        // Set the content text
        String contentText = "Are you sure you want to delete the selected row?";
        alert.setContentText(contentText);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-font-size: 11pt; -fx-text-fill: red; -fx-font-family: 'Times New Roman'; -fx-background-color: #CFD8DC;");
            
            // Customize the buttons
            ButtonType buttonTypeYes = new ButtonType("Yes");
            ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

            // Style the buttons
            Button yesButton = (Button) alert.getDialogPane().lookupButton(buttonTypeYes);
            Button noButton = (Button) alert.getDialogPane().lookupButton(buttonTypeNo);
            yesButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-background-radius: 20;");
            noButton.setStyle("-fx-background-color: #37474F; -fx-text-fill: white; -fx-background-radius: 20;");

            // Handle user's choice
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == buttonTypeYes) {

                // kunin index nung slected row
                int selectedIndex = studentTableView.getSelectionModel().getSelectedIndex();

                // Remove yung selected row sa table view
                StudentAddress deletedStudentAddress = studentTableView.getItems().remove(selectedIndex);

                // Delete yung naka select sa mismong csv file
                try {
                    deleteRowFromCSV(deletedStudentAddress, "D:\\Student_Addresses.csv");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                // Get the next row's data if available
                StudentAddress nextRowData = null;
                if (!studentTableView.getItems().isEmpty()) {
                    int nextIndex = selectedIndex;
                    if (nextIndex >= studentTableView.getItems().size()) {
                        nextIndex = studentTableView.getItems().size() - 1;
                    }
                    nextRowData = studentTableView.getItems().get(nextIndex);
                }

                if (nextRowData == null) {
                    studentTableView.getSelectionModel().clearSelection();
                }
            }
        }
    });

      Button buttonEdit = new Button("Edit");
      buttonEdit.setLayoutX(595);
      buttonEdit.setLayoutY(131);
      buttonEdit.setStyle("-fx-background-color: #37474F; -fx-text-fill: white; -fx-background-radius: 20;");
      buttonEdit.setOnAction(e -> {
          if (selectedRowData != null) {
              // Open mo yung edit popup
              handleRowSelection(selectedRowData);
          }
      });

      Button handlePlusButton = new Button("➕");
      handlePlusButton.setLayoutX(560);
      handlePlusButton.setLayoutY(131);
      handlePlusButton.setStyle("-fx-background-color: #37474F; -fx-text-fill: white; -fx-background-radius: 20;");
      handlePlusButton.setOnAction(e -> openAdditionalStage());

      // Load data from CSV file
      this.studentAddresses = FXCollections.observableArrayList(loadStudentAddressesFromCSV("D:\\Student_Addresses.csv"));

      ObservableList<StudentAddress> studentData = FXCollections.observableArrayList(studentAddresses);

      studentTableView.setItems(studentData);
      studentTableView.setLayoutX(20);
      studentTableView.setLayoutY(171);
      studentTableView.setPrefSize(611, 261);
      studentTableView.setStyle("-fx-control-inner-background: transparent;");

      studentTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

      TableColumn<StudentAddress, String> programColumn = new TableColumn<>("Program");
      programColumn.setStyle("-fx-text-fill: #FFFFFF;");
      TableColumn<StudentAddress, String> studentNameColumn = new TableColumn<>("Student Name");
      TableColumn<StudentAddress, String> barangayColumn = new TableColumn<>("Barangay");
      TableColumn<StudentAddress, String> purokColumn = new TableColumn<>("Purok");
      TableColumn<StudentAddress, String> streetColumn = new TableColumn<>("Street/ House No./ Land Mark");
      TableColumn<StudentAddress, String> contactColumn = new TableColumn<>("Parents Contact");

      programColumn.setCellValueFactory(new PropertyValueFactory<>("program"));
      studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));
      barangayColumn.setCellValueFactory(new PropertyValueFactory<>("barangay"));
      purokColumn.setCellValueFactory(new PropertyValueFactory<>("purok"));
      streetColumn.setCellValueFactory(new PropertyValueFactory<>("street"));
      contactColumn.setCellValueFactory(new PropertyValueFactory<>("parentsContact"));

      String rowSelectionStyle = "-fx-background-color: #FFAB91;";
      studentTableView.setStyle("-fx-control-inner-background: transparent; " + rowSelectionStyle);

      String cellStyle = "-fx-text-fill: #000000;";
          programColumn.setStyle(cellStyle);
          studentNameColumn.setStyle(cellStyle);
          barangayColumn.setStyle(cellStyle);
          purokColumn.setStyle(cellStyle);
          streetColumn.setStyle(cellStyle);
          contactColumn.setStyle(cellStyle);


        studentTableView.setRowFactory(tv -> {
            TableRow<StudentAddress> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && !row.isEmpty()) {
                    selectedRowData = row.getItem();
                }
            });
            return row;
        });
        
      studentTableView.getColumns().addAll(programColumn, studentNameColumn, barangayColumn,
              purokColumn, streetColumn, contactColumn);

      root.getChildren().addAll(searchTextField, buttonSearch, buttonEdit, handlePlusButton, studentTableView, manageLabel,buttonDelete);

        buttonSearch.setOnAction(e -> {
            String searchText = searchTextField.getText();
            boolean found = filterTableData(searchText, studentData);
            if (found) {
                studentTableView.getSelectionModel().clearSelection();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Data Not Found");
                alert.setHeaderText(null);
                alert.setContentText("The searched data does not exist.");
                alert.showAndWait();
            }
        });

        studentTableView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
        Node source = (Node) event.getTarget();
        if (!source.equals(studentTableView) && !source.equals(searchTextField)) {
            searchTextField.clear(); 
            studentTableView.setItems(studentData); 
            event.consume(); 
        }
    });

        AnchorPane.setLeftAnchor(searchTextField, 20.0);
        AnchorPane.setTopAnchor(searchTextField, 131.0);
        AnchorPane.setLeftAnchor(buttonSearch, 178.0);
        AnchorPane.setTopAnchor(buttonSearch, 131.0);
        AnchorPane.setRightAnchor(buttonEdit, 20.0);
        AnchorPane.setTopAnchor(buttonEdit, 131.0);
        AnchorPane.setRightAnchor(buttonDelete, 100.0);
        AnchorPane.setTopAnchor(buttonDelete, 131.0);
        AnchorPane.setRightAnchor(handlePlusButton, 65.0);
        AnchorPane.setTopAnchor(handlePlusButton, 131.0);
        AnchorPane.setLeftAnchor(studentTableView, 20.0);
        AnchorPane.setTopAnchor(studentTableView, 171.0);
        AnchorPane.setRightAnchor(studentTableView, 20.0);
        AnchorPane.setBottomAnchor(studentTableView, 20.0);
        AnchorPane.setLeftAnchor(manageLabel, 20.0);
        AnchorPane.setTopAnchor(manageLabel, 45.0);
      
      Scene secondaryScene = new Scene(root);
      secondaryStage.setScene(secondaryScene);
      secondaryStage.setTitle("Student Addresses");
      secondaryStage.show();

      // Close na primary stage para hindi two stages ang open
      primaryStage.close();
  }

    private List<StudentAddress> loadStudentAddressesFromCSV(String filePath) throws IOException {
        List<StudentAddress> studentAddresses = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            // Kung wala yung file, gumawa ng bago
            file.createNewFile();
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));

        // I-skip yung unang lina kasi header yun
        String headers = reader.readLine();

        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length == 6) {
                String program = data[0];
                String studentName = data[1];
                String barangay = data[2];
                String purok = data[3];
                String street = data[4];
                String parentsContact = data[5];

                StudentAddress studentAddress = new StudentAddress(program, studentName, barangay, purok, street, parentsContact);
                studentAddresses.add(studentAddress);
            }
        }
        reader.close();
        return studentAddresses;
    }

    private void openAdditionalStage() {
        Stage additionalStage = new Stage();
        additionalStage.setTitle("Add a Student");
        additionalStage.setResizable(false);

        Image iCon = new Image("C:\\Users\\nikka\\OneDrive\\Documents\\NetBeansProjects\\StudentAddressBook\\src\\studentaddressbook\\icon.png");
        additionalStage = new Stage();
        additionalStage.getIcons().add(iCon);

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(447, 358);
        anchorPane.setPadding(new Insets(10));
        anchorPane.setStyle("-fx-background-color: #CFD8DC;");

        Text addStudentText = new Text("Add Student Data");
        addStudentText.setLayoutX(39);
        addStudentText.setLayoutY(50);
        addStudentText.setFont(new Font("Times New Roman", 35));
        addStudentText.setFill(Color.web("#455A64"));

        // pagdeclare, design at positioning ng mga TextField
        TextField programTextField = new TextField();
        programTextField.setLayoutX(149);
        programTextField.setLayoutY(73);
        programTextField.setPrefWidth(227);
        programTextField.setStyle("-fx-background-radius: 20;");

        TextField studentNameTextField = new TextField();
        studentNameTextField.setLayoutX(149);
        studentNameTextField.setLayoutY(107);
        studentNameTextField.setPrefWidth(227);
        studentNameTextField.setStyle("-fx-background-radius: 20;");

        TextField barangayTextField = new TextField();
        barangayTextField.setLayoutX(149);
        barangayTextField.setLayoutY(142);
        barangayTextField.setPrefWidth(227);
        barangayTextField.setStyle("-fx-background-radius: 20;");

        TextField purokTextField = new TextField();
        purokTextField.setLayoutX(149);
        purokTextField.setLayoutY(180);
        purokTextField.setPrefWidth(227);
        purokTextField.setStyle("-fx-background-radius: 20;");

        TextField streetTextField = new TextField();
        streetTextField.setLayoutX(149);
        streetTextField.setLayoutY(216);
        streetTextField.setPrefWidth(227);
        streetTextField.setStyle("-fx-background-radius: 20;");

        TextField parentsContactTextField = new TextField();
        parentsContactTextField.setLayoutX(149);
        parentsContactTextField.setLayoutY(251);
        parentsContactTextField.setPrefWidth(227);
        parentsContactTextField.setStyle("-fx-background-radius: 20;");

        // Mga pagdeclare, design at positioning ng mga Label
        Text programLabel = new Text("Program:");
        programLabel.setLayoutX(41);
        programLabel.setLayoutY(90);
        programLabel.setStyle("-fx-text-fill: #FFFFFF;");

        Text studentNameLabel = new Text("Student Name:");
        studentNameLabel.setLayoutX(39);
        studentNameLabel.setLayoutY(124);
        studentNameLabel.setStyle("-fx-text-fill: #FFFFFF;");

        Text barangayLabel = new Text("Barangay:");
        barangayLabel.setLayoutX(39);
        barangayLabel.setLayoutY(159);
        barangayLabel.setStyle("-fx-text-fill: #FFFFFF;");

        Text purokLabel = new Text("Purok:");
        purokLabel.setLayoutX(40);
        purokLabel.setLayoutY(197);
        purokLabel.setStyle("-fx-text-fill: #FFFFFF;");

        Text streetLabel = new Text("Street/ House No./\nLand Mark:");
        streetLabel.setLayoutX(40);
        streetLabel.setLayoutY(228);
        streetLabel.setStyle("-fx-text-fill: #FFFFFF;");

        Text parentsContactLabel = new Text("Parents Contact:");
        parentsContactLabel.setLayoutX(41);
        parentsContactLabel.setLayoutY(268);
        parentsContactLabel.setWrappingWidth(113.344482421875);
        parentsContactLabel.setStyle("-fx-text-fill: #FFFFFF;");

        // listener sa parentsContactTextField para i-validate ang input kung number ba yon
        parentsContactTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                parentsContactTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }

            // Limit sa haba ng numbers
            if (parentsContactTextField.getText().length() > 11) {
                String limitedText = parentsContactTextField.getText().substring(0, 11);
                parentsContactTextField.setText(limitedText);
            }
        });

        // pagdeclare, design , at positioning ng mga Button
        Button addButton = new Button("Add");
        addButton.setLayoutX(337);
        addButton.setLayoutY(292);
        addButton.setStyle("-fx-background-color: #37474F; -fx-text-fill: white; -fx-background-radius: 20;");
        addButton.setOnAction(e -> {
            try {
                handleAddButton(programTextField, studentNameTextField, barangayTextField, purokTextField,
                        streetTextField, parentsContactTextField);
            } catch (IOException ex) {
               
            }
        });

        Button clearButton = new Button("Clear");
        clearButton.setLayoutX(274);
        clearButton.setLayoutY(292);
        clearButton.setStyle("-fx-background-color: #37474F; -fx-text-fill: white; -fx-background-radius: 20;");
        clearButton.setOnAction(e -> clearTextFields(programTextField, studentNameTextField, barangayTextField, purokTextField,
                streetTextField, parentsContactTextField));

        // Pagdeclare ng Label
        Label theLabel = new Label();
        theLabel.setLayoutX(149);
        theLabel.setLayoutY(325);


        anchorPane.getChildren().addAll(
                addStudentText,programTextField, studentNameTextField, barangayTextField, purokTextField,
                streetTextField, parentsContactTextField, programLabel, studentNameLabel, barangayLabel, purokLabel,
                streetLabel, parentsContactLabel, addButton, clearButton, theLabel
        );

        Scene scene = new Scene(anchorPane);
        additionalStage.setScene(scene);
        additionalStage.show();
    }

    private void handleAddButton(TextField programTextField, TextField studentNameTextField, TextField barangayTextField,
        TextField purokTextField, TextField streetTextField, TextField parentsContactTextField) throws IOException {
        String program = programTextField.getText();
        String studentName = studentNameTextField.getText();
        String barangay = barangayTextField.getText();
        String purok = purokTextField.getText();
        String street = streetTextField.getText();
        String parentsContact = parentsContactTextField.getText();

 
        parentsContactTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
               
                parentsContactTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }

            if (parentsContactTextField.getText().length() > 11) {
                String limitedText = parentsContactTextField.getText().substring(0, 11);
                parentsContactTextField.setText(limitedText);
            }
        });

        String address = String.format("%s, %s, %s, %s, %s, %s", program, studentName, barangay, purok, street,
                parentsContact);
        StudentAddress studentAddress = new StudentAddress(program, studentName, barangay, purok, street,
                parentsContact);

        this.studentAddresses.add(studentAddress);

        // Save the updated student addresses to a CSV file
        saveStudentAddressesToCSV(studentAddresses, "D:\\Student_Addresses.csv");
  
        clearTextFields(programTextField, studentNameTextField, barangayTextField, purokTextField, streetTextField,
                parentsContactTextField);

        studentTableView.getItems().add(studentAddress);
    }
    
        private void clearTextFields(TextField... textFields) {
            for (TextField textField : textFields) {
                textField.clear();
            }
        }

        public static void main(String[] args) {
            launch(args);
        }

        private void showAlert(String message) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }

        public class StudentAddress {
            private String program;
            private String studentName;
            private String barangay;
            private String purok;
            private String street;
            private String parentsContact;

        public StudentAddress(String program, String studentName, String barangay, String purok, String street, String parentsContact) {
            this.program = program;
            this.studentName = studentName;
            this.barangay = barangay;
            this.purok = purok;
            this.street = street;
            this.parentsContact = parentsContact;
            }

          public void setParentsContact(String parentsContact) {
              this.parentsContact = parentsContact;
          }

          public void setStreet(String street) {
              this.street = street;
          }

          public void setPurok(String purok) {
              this.purok = purok;
          }

          public void setBarangay(String barangay) {
              this.barangay = barangay;
          }

          public void setStudentName(String studentName) {
              this.studentName = studentName;
          }

          public void setProgram(String program) {
              this.program = program;
          }

          public String getProgram() {
              return program;
          }

          public String getStudentName() {
              return studentName;
          }

          public String getBarangay() {
              return barangay;
          }

          public String getPurok() {
              return purok;
          }

          public String getStreet() {
              return street;
          }

          public String getParentsContact() {
              return parentsContact;
          }  
          public String toCSVString() {
        return String.format("%s,%s,%s,%s,%s,%s", program, studentName, barangay, purok, street, parentsContact);
    }

}

    private boolean filterTableData(String searchText, ObservableList<StudentAddress> studentData) {
    // gagawa ng isang FilteredList gamit ang mga natanggap na studentData
    FilteredList<StudentAddress> filteredData = new FilteredList<>(studentData, p -> true);
    filteredData.setPredicate(studentAddress -> {
        // Ginagawa ang searchText na lowercase
        String lowerCaseSearchText = searchText.toLowerCase();
        
        // Tinitingnan kung ang mga field ng studentAddress ay nag cocontain ng searchText
        boolean match = studentAddress.getProgram().toLowerCase().contains(lowerCaseSearchText)
                || studentAddress.getStudentName().toLowerCase().contains(lowerCaseSearchText)
                || studentAddress.getBarangay().toLowerCase().contains(lowerCaseSearchText)
                || studentAddress.getPurok().toLowerCase().contains(lowerCaseSearchText)
                || studentAddress.getStreet().toLowerCase().contains(lowerCaseSearchText)
                || studentAddress.getParentsContact().toLowerCase().contains(lowerCaseSearchText);
        return match;
    });
    
    // I-Set ang filteredData as bagong dataset ng studentTableView.
    studentTableView.setItems(filteredData);

    // Nagbabalik ng true kung hindi empty ang filteredData.
    return !filteredData.isEmpty();
}

      private void handleRowSelection(StudentAddress rowData) {
        Stage editStage = new Stage();
        Image iCon = new Image("C:\\Users\\nikka\\OneDrive\\Documents\\NetBeansProjects\\StudentAddressBook\\src\\studentaddressbook\\icon.png");
        editStage.getIcons().add(iCon);
        editStage.setTitle("Edit Student");

        AnchorPane editLayout = new AnchorPane();
        editLayout.setPrefWidth(447);
        editLayout.setPrefHeight(358);
        editLayout.setStyle("-fx-background-color: #CFD8DC;");
        
        Text editStudentText = new Text("Edit Student Data");
        editStudentText.setLayoutX(39);
        editStudentText.setLayoutY(50);
        editStudentText.setFont(new Font("Times New Roman", 35));
        editStudentText.setFill(Color.web("#455A64"));

        TextField programTextField = new TextField(rowData.getProgram());
        programTextField.setPrefWidth(227);
        programTextField.setLayoutX(149);
        programTextField.setLayoutY(73);
        programTextField.setStyle("-fx-background-radius: 20;");

        TextField studentNameTextField = new TextField(rowData.getStudentName());
        studentNameTextField.setPrefWidth(227);
        studentNameTextField.setLayoutX(149);
        studentNameTextField.setLayoutY(107);
        studentNameTextField.setStyle("-fx-background-radius: 20;");

        TextField barangayTextField = new TextField(rowData.getBarangay());
        barangayTextField.setPrefWidth(227);
        barangayTextField.setLayoutX(149);
        barangayTextField.setLayoutY(142);
        barangayTextField.setStyle("-fx-background-radius: 20;");

        TextField purokTextField = new TextField(rowData.getPurok());
        purokTextField.setPrefWidth(227);
        purokTextField.setLayoutX(149);
        purokTextField.setLayoutY(180);
        purokTextField.setStyle("-fx-background-radius: 20;");
        

        TextField streetTextField = new TextField(rowData.getStreet());
        streetTextField.setPrefWidth(227);
        streetTextField.setLayoutX(149);
        streetTextField.setLayoutY(216);
        streetTextField.setStyle("-fx-background-radius: 20;");

        TextField parentsContactTextField = new TextField(rowData.getParentsContact());
        parentsContactTextField.setPrefWidth(227);
        parentsContactTextField.setLayoutX(149);
        parentsContactTextField.setLayoutY(251);
        parentsContactTextField.setStyle("-fx-background-radius: 20;");
                 // Add a listener to the parentsContactTextField to validate the input
            parentsContactTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    // Replace any non-digit characters with an empty string
                    parentsContactTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }

                //11 numbers lng tanggap
                if (parentsContactTextField.getText().length() > 11) {
                    String limitedText = parentsContactTextField.getText().substring(0, 11);
                    parentsContactTextField.setText(limitedText);
                }
            });

        Text programLabel2 = new Text("Program:");
        programLabel2.setLayoutX(41);
        programLabel2.setLayoutY(90);

        Text studentNameLabel2 = new Text("Student Name:");
        studentNameLabel2.setLayoutX(39);
        studentNameLabel2.setLayoutY(124);

        Text barangayLabel2 = new Text("Barangay:");
        barangayLabel2.setLayoutX(39);
        barangayLabel2.setLayoutY(159);

        Text purokLabel2 = new Text("Purok:");
        purokLabel2.setLayoutX(40);
        purokLabel2.setLayoutY(197);

        Text streetLabel2 = new Text("Street/ House No./\nLand Mark:");
        streetLabel2.setLayoutX(40);
        streetLabel2.setLayoutY(228);

        Text parentsContactLabel2 = new Text("Parents Contact:");
        parentsContactLabel2.setLayoutX(41);
        parentsContactLabel2.setLayoutY(268);
      

        Button saveButton = new Button("Save");
        saveButton.setStyle("-fx-background-color: #37474F; -fx-text-fill: white; -fx-background-radius: 20;");
        saveButton.setOnAction(e -> {
        // kukunin yung updated na student data sa edit student window
        String updatedProgram = programTextField.getText();
        String updatedStudentName = studentNameTextField.getText();
        String updatedBarangay = barangayTextField.getText();
        String updatedPurok = purokTextField.getText();
        String updatedStreet = streetTextField.getText();
        String updatedParentsContact = parentsContactTextField.getText();

        // bagong student address para sa updated na student data
        StudentAddress updatedAddress = new StudentAddress(updatedProgram, updatedStudentName, updatedBarangay, updatedPurok, updatedStreet, updatedParentsContact);

        // check namam kung meron na yung updated na student data sa file
        if (!studentAddresses.contains(updatedAddress)) {
            //remove na yung original na data kasi papalitan na nung updated
            studentAddresses.remove(rowData);

            // update na yung row nung mga updated student data
            rowData.setProgram(updatedProgram);
            rowData.setStudentName(updatedStudentName);
            rowData.setBarangay(updatedBarangay);
            rowData.setPurok(updatedPurok);
            rowData.setStreet(updatedStreet);
            rowData.setParentsContact(updatedParentsContact);

            // add na yung updated row sa studentAddresses na list
            studentAddresses.add(rowData);

            try {
                // Save the updated student addresses to a CSV file
                saveStudentAddressesToCSV(studentAddresses, "D:\\Student_Addresses.csv");
            } catch (IOException ex) {
               
            }

            // Refresh the table view to reflect the changes
            studentTableView.refresh();
        }

        // Close the edit popup
        editStage.close();
        });

        saveButton.setLayoutX(338);
        saveButton.setLayoutY(303);

        editLayout.getChildren().addAll(
            programLabel2,
            studentNameLabel2,
            barangayLabel2,
            purokLabel2,
            streetLabel2,
            parentsContactLabel2,
            programTextField,
            studentNameTextField,
            barangayTextField,
            purokTextField,
            streetTextField,
            parentsContactTextField,
            saveButton,
            editStudentText
        );

        Scene editScene = new Scene(editLayout);
        editStage.setScene(editScene);

        editStage.show();
    }

    private void saveStudentAddressesToCSV(List<StudentAddress> studentAddresses, String filePath) throws IOException {
        File file = new File(filePath);

        if (!file.exists()) {
            // If the file doesn't exist, create a new file
            file.createNewFile();
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        // Write the headers
        Text headerText = new Text("Program,Student Name,Barangay,Purok,Street/ House No./ Land Mark,Parents Contact");
        headerText.setFont(Font.font("Arial", FontWeight.BOLD, 12)); // Set the font weight to bold
        writer.write(headerText.getText());
        writer.newLine();

        // Write each student address as a line in the CSV file
        for (StudentAddress studentAddress : studentAddresses) {
            String line = String.format("%s,%s,%s,%s,%s,%s", studentAddress.getProgram(), studentAddress.getStudentName(),
                    studentAddress.getBarangay(), studentAddress.getPurok(), studentAddress.getStreet(),
                    studentAddress.getParentsContact());
            writer.write(line);
            writer.newLine();
        }
        writer.close();
    }

        private void deleteRowFromCSV(StudentAddress studentAddress, String filePath) throws IOException {
        File inputFile = new File(filePath);
        File tempFile = new File("D:\\temp.csv");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String lineToRemove = String.format("%s,%s,%s,%s,%s,%s", studentAddress.getProgram(), studentAddress.getStudentName(),
                studentAddress.getBarangay(), studentAddress.getPurok(), studentAddress.getStreet(),
                studentAddress.getParentsContact());
        String currentLine;

        while ((currentLine = reader.readLine()) != null) {
            if (currentLine.equals(lineToRemove)) {
                continue;
            }
            writer.write(currentLine);
            writer.newLine();
        }

        writer.close();
        reader.close();

        // Replace the original file with the modified temp file
        if (inputFile.delete()) {
            tempFile.renameTo(inputFile);
        } else {
            throw new IOException("Failed to delete the original CSV file.");
        }
    }
}