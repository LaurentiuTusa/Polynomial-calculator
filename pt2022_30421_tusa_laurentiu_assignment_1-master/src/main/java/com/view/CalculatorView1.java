package com.view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class CalculatorView1 extends JFrame{
    private JPanel calculator;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel Pol1;
    private JLabel Pol2;
    private JButton ADDButton;
    private JButton INTEGRATEButton;
    private JButton SUBTRACTButton;
    private JButton DERIVEButton;
    private JLabel result;
    private JLabel resultDisplay;
    private JButton ClearButton;

    public CalculatorView1(){
        this.setVisible(true);
        this.setContentPane(calculator);
        this.setSize(1200, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public String getPol1() {
        return textField1.getText();
    }

    public String getPol2() {
        return textField2.getText();
    }

    public void setResultDisplay(String output) {
        this.resultDisplay.setText(output);
    }
    public void addition(ActionListener action) {ADDButton.addActionListener(action);}
    public void subtraction(ActionListener action) {SUBTRACTButton.addActionListener(action);}
    public void derive(ActionListener action) {DERIVEButton.addActionListener(action);}
    public void integrate(ActionListener action) {INTEGRATEButton.addActionListener(action);}
    public void clear(ActionListener action) {ClearButton.addActionListener(action);}

}
