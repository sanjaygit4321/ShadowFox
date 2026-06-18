import javafx.beans.property.*;

public class Product {

    private final StringProperty name = new SimpleStringProperty();
    private final IntegerProperty quantity = new SimpleIntegerProperty();
    private final DoubleProperty price = new SimpleDoubleProperty();
    private final ReadOnlyDoubleWrapper totalValue = new ReadOnlyDoubleWrapper();

    public Product(String name, int quantity, double price) {
        this.name.set(name);
        this.quantity.set(quantity);
        this.price.set(price);

        totalValue.bind(this.quantity.multiply(this.price));
    }

    public String getName() { return name.get(); }
    public void setName(String name) { this.name.set(name); }
    public StringProperty nameProperty() { return name; }

    public int getQuantity() { return quantity.get(); }
    public void setQuantity(int quantity) { this.quantity.set(quantity); }
    public IntegerProperty quantityProperty() { return quantity; }

    public double getPrice() { return price.get(); }
    public void setPrice(double price) { this.price.set(price); }
    public DoubleProperty priceProperty() { return price; }

    public double getTotalValue() { return totalValue.get(); }
    public ReadOnlyDoubleProperty totalValueProperty() {
        return totalValue.getReadOnlyProperty();
    }
}
