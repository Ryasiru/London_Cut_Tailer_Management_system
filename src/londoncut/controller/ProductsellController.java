package londoncut.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import londoncut.alerts.MessageAlert;
import londoncut.db.DBConnection;
import londoncut.models.SellProduct;
import londoncut.models.controller.OrdersController;
import londoncut.models.controller.ProductController;
import londoncut.models.controller.SellProductController;
import londoncut.tables.controller.tblProductController;
import londoncut.tables.tblProduct;

/**
 * FXML Controller class
 *
 * @author Yasiru
 */
public class ProductsellController implements Initializable {

    @FXML
    private JFXComboBox<String> comboproduct;
    @FXML
    private TextField txtqty;
    @FXML
    private TextField txtunitprice;
    @FXML
    private JFXButton btnaddProduct;
    @FXML
    private TableView<tblProduct> tblproduct;
    @FXML
    private TableColumn<tblProduct, String> colProduct;
    @FXML
    private TableColumn<tblProduct, Integer> colQty;
    @FXML
    private TableColumn<tblProduct, Integer> colPrice;

    private Measurement_4Controller controller;
    static int price;
    @FXML
    private JFXButton btndelete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboproduct.setItems(new ProductController().loadData());
        initiateTable();
    }

    @FXML
    private void addProduct(ActionEvent event) throws SQLException {
        price = 0;
        if (tblproduct.getItems().size() == 0) {
            int temp = Integer.parseInt(txtqty.getText()) * Integer.parseInt(txtunitprice.getText());
            new SellProductController().saveSellProduct(new SellProduct(new OrdersController().setSelectOrder(),
                    comboproduct.getEditor().getText(), Integer.parseInt(txtqty.getText()), temp));
            tblproduct.setItems(new tblProductController().loadData(new OrdersController().setSelectOrder()));
            new ProductController().updateQty(comboproduct.getEditor().getText(), Integer.parseInt(txtqty.getText()));
        } else {
            for (int i = 0; i < tblproduct.getItems().size(); i++) {
                if (tblproduct.getItems().get(i).getProduct().equals(comboproduct.getEditor().getText())) {
                    int total = tblproduct.getItems().get(i).getPrice();
                    int qty = tblproduct.getItems().get(i).getQty();
                    total = total + Integer.parseInt(txtqty.getText()) * Integer.parseInt(txtunitprice.getText());
                    qty = qty + Integer.parseInt(txtqty.getText());
                    String sql = "UPDATE sellproduct SET qty=?,price=? WHERE ordernumber=?";
                    Connection con = DBConnection.getInstance().getConnection();
                    PreparedStatement pstm = con.prepareStatement(sql);
                    pstm.setInt(1, qty);
                    pstm.setInt(2, total);
                    pstm.setString(3, new OrdersController().setSelectOrder());
                    pstm.executeUpdate();
                    tblproduct.setItems(new tblProductController().loadData(new OrdersController().setSelectOrder()));
                } else {
                    int temp = Integer.parseInt(txtqty.getText()) * Integer.parseInt(txtunitprice.getText());
                    new SellProductController().saveSellProduct(new SellProduct(new OrdersController().setSelectOrder(),
                            comboproduct.getEditor().getText(), Integer.parseInt(txtqty.getText()), temp));
                    tblproduct.setItems(new tblProductController().loadData(new OrdersController().setSelectOrder()));
                    new ProductController().updateQty(comboproduct.getEditor().getText(), Integer.parseInt(txtqty.getText()));
                }
            }
        }
        TableColumn<tblProduct, Integer> column = colPrice;
        List<Integer> list = new ArrayList();
        for (tblProduct item : tblproduct.getItems()) {
            list.add(column.getCellObservableValue(item).getValue());
        }
        for (int i = 0; i < tblproduct.getItems().size(); i++) {
            price += list.get(i);

        }
        controller.setProductTotal(price);
        txtqty.setText(null);
        txtunitprice.setText(null);
        comboproduct.getEditor().setText(null);
    }

    private void initiateTable() {
        colProduct.setCellValueFactory(new PropertyValueFactory<>("product"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    public void setController(Measurement_4Controller controller) {
        this.controller = controller;
    }

    @FXML
    private void autoLoad(KeyEvent event) {
        comboproduct.setItems(new ProductController().loadCombo(comboproduct.getEditor().getText()));
        comboproduct.show();
    }

    @FXML
    private void loadPrice(ActionEvent event) {
        txtunitprice.setText(new ProductController().getProductPrice(comboproduct.getEditor().getText()));
    }

    @FXML
    private void deleteProduct(ActionEvent event) {
        boolean bool = MessageAlert.confirm(tblproduct.getSelectionModel().getSelectedItem().getProduct());
        if (bool) {
            new SellProductController().deleteOrder(new OrdersController().setSelectOrder(),
                    tblproduct.getSelectionModel().getSelectedItem().getProduct());
            new ProductController().setQty(tblproduct.getSelectionModel().getSelectedItem().getProduct(),
                    tblproduct.getSelectionModel().getSelectedItem().getQty());
            tblproduct.setItems(new tblProductController().loadData(new OrdersController().setSelectOrder()));
        }

    }

}
