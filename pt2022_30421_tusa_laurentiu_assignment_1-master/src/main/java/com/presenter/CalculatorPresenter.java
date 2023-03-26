package com.presenter;

import com.view.CalculatorView1;
import com.model.Monomial;
import com.model.Polynomial;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CalculatorPresenter {
    private final CalculatorView1 calculatorView1;

    Polynomial p1 = new Polynomial(new ArrayList<Monomial>());
    Polynomial p2 = new Polynomial(new ArrayList<Monomial>());

    public CalculatorPresenter(CalculatorView1 calculatorView1) {
        this.calculatorView1 = calculatorView1;

        calculatorView1.addition(new addAction());
        calculatorView1.subtraction(new subtractAction());
        calculatorView1.derive(new deriveAction());
        calculatorView1.integrate(new integrateAction());
        calculatorView1.clear(new clearAction());
    }

    private class addAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            p1.constructPol(calculatorView1.getPol1());
            p2.constructPol(calculatorView1.getPol2());
            String result = p1.add(p2);
            calculatorView1.setResultDisplay(result);
        }
    }

    private class subtractAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            p1.constructPol(calculatorView1.getPol1());
            p2.constructPol(calculatorView1.getPol2());
            String result = p1.subtract(p2);
            calculatorView1.setResultDisplay(result);
        }
    }

    private class deriveAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            p1.constructPol(calculatorView1.getPol1());
            String result = p1.derive();
            calculatorView1.setResultDisplay(result);
        }
    }

    private class integrateAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            p1.constructPol(calculatorView1.getPol1());
            String result = p1.integrate();
            calculatorView1.setResultDisplay(result);
        }
    }

    private class clearAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            calculatorView1.setResultDisplay("");
        }
    }

}
