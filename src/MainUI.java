
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainUI {
    public JFrame view;

    public JButton btnAddCustomer = new JButton("Add New Customer");
    public JButton btnAddPurchase = new JButton("Add New Purchase");
    public JButton btnAddProduct = new JButton("Add New Product");

    public JButton btnManageProduct = new JButton("Manage Products");
    public JButton btnManageCustomer = new JButton("Manage Customers");
    public JButton btnManagePurchase = new JButton("Manage Purchase");

    public MainUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setTitle("Store Management System");
        view.setSize(1000, 600);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JLabel title = new JLabel("Store Management System");

        title.setFont (title.getFont ().deriveFont (24.0f));
        view.getContentPane().add(title);

        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(btnManageProduct);
        panelButtons.add(btnManageCustomer);
        panelButtons.add(btnAddCustomer);
        panelButtons.add(btnAddPurchase);
        panelButtons.add(btnManagePurchase);
        panelButtons.add(btnAddProduct);

        view.getContentPane().add(panelButtons);


        btnAddPurchase.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddPurchaseUI ap = new AddPurchaseUI();
                ap.run();
            }
        });

        btnManageProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ManageProductUI ui = new ManageProductUI();
                ui.run();
            }
        });

        btnManagePurchase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ManagePurchaseUI ud = new ManagePurchaseUI();
                ud.run();
            }
        });

        btnManageCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ManageCustomerUI ui = new ManageCustomerUI();
                ui.run();
            }
        });

    }
}