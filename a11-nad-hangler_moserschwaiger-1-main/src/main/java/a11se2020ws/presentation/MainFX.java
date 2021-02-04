package a11se2020ws.presentation;

import a11se2020ws.controller.BookController;
import a11se2020ws.model.Book;
import a11se2020ws.model.BookModel;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Represents the view in the MVC-Pattern
 * Presentation of the model (BookModel)
 * displays the BookList and allows User to Add,Delete and Edit Books
 */
public class MainFX extends Application implements IObserver {

  private ObservableList<Book> myList;
  private final ListView<Book> listView;

  private final BookModel model;
  private final BookController controller;

  /**
   * constructs a {@link MainFX} object
   */
  public MainFX(){
    model = BookModel.getInstance();
    model.registerView(this);
    controller = new BookController(model);

    myList = FXCollections.observableArrayList(model.getBookList());
    listView = new ListView<>(myList);
  }

  @Override
  public void start(final Stage stage){

    stage.setTitle("Book-Manager");

    BorderPane root = new BorderPane();

    HBox hbox = new HBox();
    hbox.setPadding(new Insets(15, 12, 15, 12));
    hbox.setSpacing(10);

    Button cmdAdd = new Button("Add Book");
    Button cmdDelete = new Button("Delete Book");
    Button cmdEdit = new Button("Edit Book");

    cmdAdd.setOnAction(new AddHandler());
    cmdDelete.setOnAction(new DeleteHandler());
    cmdEdit.setOnAction(new EditHandler());

    hbox.getChildren().addAll(cmdAdd, cmdDelete, cmdEdit);
    root.setTop(hbox);
    root.setCenter(listView);
    Scene scene = new Scene(root, 300, 300);
    stage.setScene(scene);
    stage.show();
  }

  @Override
  public void update() {
    listView.getItems().clear();
    myList = FXCollections.observableArrayList(model.getBookList());
    listView.getItems().addAll(myList);
  }

  /**
   * Innerclass for handling the add-button
   * */
  private class AddHandler implements EventHandler<ActionEvent>{

    @Override
    public void handle(ActionEvent event) {
      Dialog<Book> dialog = new Dialog<>();
      dialog.setTitle("Add Book");
      dialog.setHeaderText("Add a new Book");

      Label bookTitle = new Label("Title: ");
      Label bookAuthor = new Label("Author: ");
      Label bookYear = new Label("Year: ");
      Label bookIsbn = new Label("ISBN: ");

      TextField titleText = new TextField();
      TextField authorText = new TextField();
      TextField yearText = new TextField();
      TextField isbnText = new TextField();

      GridPane grid = new GridPane();
      grid.add(bookTitle, 1, 1);
      grid.add(titleText, 2,1);
      grid.add(bookAuthor, 1, 2);
      grid.add(authorText, 2,2);
      grid.add(bookYear, 1, 3);
      grid.add(yearText, 2,3);
      grid.add(bookIsbn, 1, 4);
      grid.add(isbnText, 2,4);

      dialog.getDialogPane().setContent(grid);

      ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
      ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
      dialog.getDialogPane().getButtonTypes().setAll(okButton,cancelButton);

      dialog.showAndWait();

      //if a field is not filled in, send alert
      if(titleText.getText().isEmpty() || authorText.getText().isEmpty() || yearText.getText().isEmpty() || isbnText.getText().isEmpty()){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("Information");
        alert.setContentText("Please fill all requested Fields");
        alert.showAndWait();
      }
      else {
        //if all fields are filled in, addBook to the controller
        controller.addBook(titleText.getText(), authorText.getText(), Integer.parseInt(yearText.getText()), Long.parseLong(isbnText.getText()));
      }
    }

  }

  /**
   * Innerclass for handling the delete-button
   **/
  private class DeleteHandler implements EventHandler<ActionEvent>{

    @Override
    public void handle(ActionEvent actionEvent) {
      int index = listView.getSelectionModel().getSelectedIndex();
      if(index >= 0) {
        Book removeBook = myList.get(index);
        controller.removeBook(removeBook.getIsbn());
      }
    }
  }

  /**
   * Innerclass for handling the edit-button
   **/
  private class EditHandler implements EventHandler<ActionEvent>{

    @Override
    public void handle(ActionEvent actionEvent) {
      int index = listView.getSelectionModel().getSelectedIndex();

      if(index >= 0) {
        Book editBook = myList.get(index);

        Dialog<Book> dialog = new Dialog<>();
        dialog.setTitle("Edit Book");
        dialog.setHeaderText("Edit an existing Book");

        Label bookTitle = new Label("Title: ");
        Label bookAuthor = new Label("Author: ");
        Label bookYear = new Label("Year: ");
        Label bookIsbn = new Label("ISBN: " );
        Label bookIsbnFixed = new Label (String.valueOf(editBook.getIsbn()));

        TextField titleText = new TextField(editBook.getTitle());
        TextField authorText = new TextField(editBook.getAuthor());
        TextField yearText = new TextField(String.valueOf(editBook.getYear()));

        GridPane grid = new GridPane();
        grid.add(bookTitle, 1, 1);
        grid.add(titleText, 2, 1);
        grid.add(bookAuthor, 1, 2);
        grid.add(authorText, 2, 2);
        grid.add(bookYear, 1, 3);
        grid.add(yearText, 2, 3);
        grid.add(bookIsbn, 1, 4);
        grid.add(bookIsbnFixed, 2, 4);

        dialog.getDialogPane().setContent(grid);

        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().setAll(okButton, cancelButton);

        dialog.showAndWait();

        //if a field is not filled in, send alert
        if(titleText.getText().isEmpty() || authorText.getText().isEmpty() || yearText.getText().isEmpty()){
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Error");
          alert.setHeaderText("Information");
          alert.setContentText("Please fill all requested Fields");
          alert.showAndWait();
        }
        else {
          //if all fields are filled in, commit edited book to controller
          controller.editBook(titleText.getText(), authorText.getText(), Integer.parseInt(yearText.getText()), editBook.getIsbn());
        }
      }
    }
  }
}

