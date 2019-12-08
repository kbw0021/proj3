import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter; 
import java.net.Socket;
import java.util.Scanner;

public class ManagePurchaseUI {

        public JFrame view;

        public JButton btnLoad = new JButton("Load Purchase");
        public JButton btnSave = new JButton("Save Purchase");

        public JTextField txtPurchaseID = new JTextField(20);
        public JTextField txtProductID = new JTextField(20);
        public JTextField txtCustomerID = new JTextField(20);
        public JTextField txtPrice = new JTextField(20);
        public JTextField txtQuantity = new JTextField(20);
        public JTextField txtCost = new JTextField(20);
        public JTextField txtTax = new JTextField(20);
        public JTextField txtTotal = new JTextField(20);
        public JTextField txtDate = new JTextField(20);


        public ManagePurchaseUI() {
            this.view = new JFrame();

            view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            view.setTitle("Update Purchase Information");
            view.setSize(600, 400);
            view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

            JPanel panelButtons = new JPanel(new FlowLayout());
            panelButtons.add(btnLoad);
            panelButtons.add(btnSave);
            view.getContentPane().add(panelButtons);

            JPanel line1 = new JPanel(new FlowLayout());
            line1.add(new JLabel("PurchaseID "));
            line1.add(txtPurchaseID);
            view.getContentPane().add(line1);

            JPanel line2 = new JPanel(new FlowLayout());
            line2.add(new JLabel("ProductID "));
            line2.add(txtProductID);
            view.getContentPane().add(line2);

            JPanel line3 = new JPanel(new FlowLayout());
            line3.add(new JLabel("CustomerID "));
            line3.add(txtCustomerID);
            view.getContentPane().add(line3);

            JPanel line4 = new JPanel(new FlowLayout());
            line4.add(new JLabel("Date "));
            line4.add(txtDate);
            view.getContentPane().add(line4);



            /////////// Price quantity cost tax total ////////////////
            JPanel line5 = new JPanel(new FlowLayout());
            line5.add(new JLabel("Price "));
            line5.add(txtPrice);
            view.getContentPane().add(line5);

            JPanel line6 = new JPanel(new FlowLayout());
            line6.add(new JLabel("Quantity "));
            line6.add(txtQuantity);
            view.getContentPane().add(line6);

            JPanel line7 = new JPanel(new FlowLayout());
            line7.add(new JLabel("Cost "));
            line7.add(txtCost);
            view.getContentPane().add(line7);

            JPanel line8 = new JPanel(new FlowLayout());
            line8.add(new JLabel("Tax "));
            line8.add(txtTax);
            view.getContentPane().add(line8);

            JPanel line9 = new JPanel(new FlowLayout());
            line9.add(new JLabel("Total "));
            line9.add(txtTotal);
            view.getContentPane().add(line9);


            btnLoad.addActionListener(new LoadButtonListerner());

            btnSave.addActionListener(new SaveButtonListener());

        }

        public void run() {
            view.setVisible(true);
        }

        class LoadButtonListerner implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                PurchaseModel purchase = new PurchaseModel();
                Gson gson = new Gson();
                String id = txtPurchaseID.getText();

                if (id.length() == 0) {
                    JOptionPane.showMessageDialog(null, "PurchaseID cannot be null!");
                    return;
                }

                try {
                    purchase.mPurchaseID = Integer.parseInt(id);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "PurchaseID is invalid!");
                    return;
                }

                // do client/server

                try {
                    Socket link = new Socket("localhost", 1000);
                    Scanner input = new Scanner(link.getInputStream());
                    PrintWriter output = new PrintWriter(link.getOutputStream(), true);

                    MessageModel msg = new MessageModel();
                    msg.code = MessageModel.GET_PURCHASE;
                    msg.data = Integer.toString(purchase.mPurchaseID);
                    output.println(gson.toJson(msg)); // send to Server

                    msg = gson.fromJson(input.nextLine(), MessageModel.class);

                    if (msg.code == MessageModel.OPERATION_FAILED) {
                        JOptionPane.showMessageDialog(null, "Purchase NOT exists!");
                    }
                    else {
                        purchase = gson.fromJson(msg.data, PurchaseModel.class);
                        txtDate.setText(purchase.mDate);
                      //  txtPhone.setText(purchase.mPhone);
                      //  txtAddy.setText(purchase.mAddress);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

        class SaveButtonListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                PurchaseModel purchase = new PurchaseModel();
                Gson gson = new Gson();
                String id = txtPurchaseID.getText();

                if (id.length() == 0) {
                    JOptionPane.showMessageDialog(null, "purchaseID cannot be null!");
                    return;
                }

                try {
                    purchase.mPurchaseID = Integer.parseInt(id);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "purchaseID is invalid!");
                    return;
                }

     //           String name = txtProductID.getText();
     //           if (name.length() == 0) {
     //               JOptionPane.showMessageDialog(null, "productid cannot be empty!");
     //               return;
     //           }

       //         purchase.mProductID = name;


                //String phone = txtCustomerID.getText();
          //      if (phone.length() == 0) {
          //          JOptionPane.showMessageDialog(null, "purchase phone cannot be empty!");
          //          return;
           //     }
//
            //    purchase.mCustomerID = phone;
//
              //  String addy = txtAddy.getText();
              //  if (addy.length() == 0) {
                //    JOptionPane.showMessageDialog(null, "purchase Address cannot be empty!");
              //      return;
              //  }

              //  purchase.mAddress = addy;

                // all purchase infor is ready! Send to Server!


                try {
                    Socket link = new Socket("localhost", 1000);
                    Scanner input = new Scanner(link.getInputStream());
                    PrintWriter output = new PrintWriter(link.getOutputStream(), true);

                    MessageModel msg = new MessageModel();
                    msg.code = MessageModel.PUT_PURCHASE;
                    msg.data = gson.toJson(purchase);
                    output.println(gson.toJson(msg)); // send to Server

                    msg = gson.fromJson(input.nextLine(), MessageModel.class); // receive from Server

                    if (msg.code == MessageModel.OPERATION_FAILED) {
                        JOptionPane.showMessageDialog(null, "purchase is NOT saved successfully!");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "purchase is SAVED successfully!");
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
