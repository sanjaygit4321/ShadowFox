import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InventoryManager {

    private static InventoryManager instance;
    private final ObservableList<Product> products;

    private InventoryManager() {
        products = FXCollections.observableArrayList();
    }

    public static InventoryManager getInstance() {
        if (instance == null) {
            instance = new InventoryManager();
        }
        return instance;
    }

    public ObservableList<Product> getProducts() {
        return products;
    }

    public boolean isValidQuantity(int qty) {
        return qty >= 0;
    }

    public void addProduct(Product p) {
        products.add(p);
    }

    public void deleteProduct(Product p) {
        products.remove(p);
    }
}
