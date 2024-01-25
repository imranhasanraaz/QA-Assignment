package data_access_object;
import lombok.Data;

@Data
public class Cart_DAO {
    private String Product;
    private String Price;
    private String Quantity;
    private String Subtotal;
}
