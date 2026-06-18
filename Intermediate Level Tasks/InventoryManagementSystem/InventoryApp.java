import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class InventoryApp extends Application {

    private final InventoryManager manager = InventoryManager.getInstance();

    @Override
    public void start(Stage stage) {

    
        TableView<Product> table = new TableView<>();
        table.setItems(manager.getProducts());
        table.setFixedCellSize(35);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

      
        TableColumn<Product, Number> Table = new TableColumn<>("S.No");
        Table.setSortable(false);
        Table.setPrefWidth(50);

      
        Table.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(Number item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(String.valueOf(getIndex() + 1));
                }
            }
        });

        TableColumn<Product, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(d -> d.getValue().nameProperty());

        TableColumn<Product, Integer> qtyCol = new TableColumn<>("Quantity");
        qtyCol.setCellValueFactory(d -> d.getValue().quantityProperty().asObject());

        TableColumn<Product, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(d -> d.getValue().priceProperty().asObject());

        TableColumn<Product, Double> totalCol = new TableColumn<>("Total Value");
        totalCol.setCellValueFactory(d -> d.getValue().totalValueProperty().asObject());

        table.getColumns().addAll(Table, nameCol, qtyCol, priceCol, totalCol);

     
        TextField nameField = new TextField();
        TextField qtyField = new TextField();
        TextField priceField = new TextField();
        TextField searchField = new TextField();

        nameField.setPromptText("Product Name");
        qtyField.setPromptText("Quantity");
        priceField.setPromptText("Price");
        searchField.setPromptText("Search Product");

        nameField.setPrefHeight(35);
        qtyField.setPrefHeight(35);
        priceField.setPrefHeight(35);
        searchField.setPrefHeight(35);

       
        Button addBtn = new Button("Add Product");
        Button updateBtn = new Button("Update Product");
        Button deleteBtn = new Button("Delete Product");

        styleButton(addBtn, "#green");
        styleButton(updateBtn, "#yellow");
        styleButton(deleteBtn, "#red");

        FilteredList<Product> filtered =
                new FilteredList<>(manager.getProducts(), p -> true);

        searchField.textProperty().addListener((obs, o, val) ->
                filtered.setPredicate(p ->
                        p.getName().toLowerCase().contains(val.toLowerCase()))
        );
        table.setItems(filtered);

      
        addBtn.setOnAction(e -> {
            try {
                String name = nameField.getText();
                int qty = Integer.parseInt(qtyField.getText());
                double price = Double.parseDouble(priceField.getText());

                if (name.isEmpty() || !manager.isValidQuantity(qty)) {
                    showAlert("Invalid input!");
                    return;
                }

                manager.addProduct(new Product(name, qty, price));
                resetForm(table, nameField, qtyField, priceField);

            } catch (Exception ex) {
                showAlert("Enter valid data!");
            }
        });

        updateBtn.setOnAction(e -> {
            try {
                Product p = table.getSelectionModel().getSelectedItem();
                if (p != null) {
                    int qty = Integer.parseInt(qtyField.getText());
                    if (!manager.isValidQuantity(qty)) {
                        showAlert("Quantity cannot be negative!");
                        return;
                    }

                    p.setName(nameField.getText());
                    p.setQuantity(qty);
                    p.setPrice(Double.parseDouble(priceField.getText()));

                    resetForm(table, nameField, qtyField, priceField);
                    table.refresh();
                }
            } catch (Exception ex) {
                showAlert("Invalid input!");
            }
        });

        deleteBtn.setOnAction(e -> {
            Product p = table.getSelectionModel().getSelectedItem();
            if (p != null) {
                manager.deleteProduct(p);
                resetForm(table, nameField, qtyField, priceField);
            }
        });

        table.getSelectionModel().selectedItemProperty().addListener((obs, o, p) -> {
            if (p != null) {
                nameField.setText(p.getName());
                qtyField.setText(String.valueOf(p.getQuantity()));
                priceField.setText(String.valueOf(p.getPrice()));
            }
        });

       
        VBox form = new VBox(12,
                nameField, qtyField, priceField,
                addBtn, updateBtn, deleteBtn);

        form.setPadding(new Insets(15));
        form.setBackground(new Background(
                new BackgroundFill(Color.WHITE, new CornerRadii(10), Insets.EMPTY)));
        form.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY,
                BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));

        VBox root = new VBox(15, searchField, table, form);
        root.setPadding(new Insets(15));
        root.setBackground(new Background(
                new BackgroundFill(Color.web("#ECF0F1"), CornerRadii.EMPTY, Insets.EMPTY)));

        stage.setScene(new Scene(root, 750, 550));
        stage.setTitle("Inventory Management System with Serial Numbers");
        stage.show();
    }

   
    private void resetForm(TableView<Product> table, TextField... fields) {
        for (TextField f : fields) f.clear();
        table.getSelectionModel().clearSelection();
    }

    private void styleButton(Button btn, String color) {
        btn.setPrefHeight(35);
        btn.setStyle(
                "-fx-background-color: " + color + ";" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-background-radius: 8;"
        );
    }

    private void showAlert(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).show();
    }

    public static void main(String[] args) {
        launch();
    }
}
