package com;

import com.model.Monomial;
import com.model.Polynomial;
import com.presenter.CalculatorPresenter;
import com.view.CalculatorView1;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        CalculatorPresenter calculatorPresenter = new CalculatorPresenter(new CalculatorView1());

     /*   System.out.println("Termeni:");
        String test = "x-21";
        Pattern pattern = Pattern.compile("([+-]?[^-+]+)");
        Matcher matcher = pattern.matcher(test);

        ArrayList<String> term = new ArrayList<>();
        while (matcher.find()) {
            term.add(matcher.group(1));
        }

        term.forEach(p->{
            System.out.println(p);
        });

        Pattern pattern2 = Pattern.compile("(([+-]?\\d{1,})(?=[xX])|[+-](\\d{1,})|(^\\d{1,}))|(^[+-]?x)");
        ArrayList<Integer> coef = new ArrayList<>();
        term.forEach(e->{
            Matcher matcher2 = pattern2.matcher(e);
            while (matcher2.find()) {
                if (matcher2.group(1) != null && matcher2.group(5) == null){
                    coef.add(Integer.parseInt(matcher2.group(1)));
                } else if (matcher2.group(1) == null && matcher2.group(5) != null) {//case with 1 or -1 as coefficient
                    Character c = matcher2.group(5).charAt(0);
                    if (c.equals('-')) {
                        coef.add(-1);
                    } else
                        coef.add(1);
                }
            }
        });
        System.out.println("Coeficienti:");
        coef.forEach(c->{
            System.out.println(c);
        });
        System.out.println("testare cu index: coef.size= " + coef.size());
        int p =0;
        while ( p < coef.size()){
            System.out.println(coef.get(p));
            p++;
        }

        Pattern pattern3 = Pattern.compile("(?<=[xX^])([+-]?\\d{1,})|([+-]\\d{1,}[xX]$|[+-][xX]$|^\\d{1,}[xX]$|^[xX]$)|([+-]\\d{1,}$|\\d{1,})");
        ArrayList<Integer> exp = new ArrayList<>();
        term.forEach(e->{
            Matcher matcher3 = pattern3.matcher(e);
            while (matcher3.find()) {
                if (matcher3.group(1) != null) {
                    exp.add(Integer.parseInt(matcher3.group(1)));

                } else if (matcher3.group(2) != null) {
                    exp.add(1);

                } else if (matcher3.group(3) != null) {
                    exp.add(0);

                } else {
                    System.out.println("Error at exponent input");
                }
            }
        });
        System.out.println("Exponenti:");
        exp.forEach(e->{
            System.out.println(e);
        });

        ArrayList<Monomial> monomialArrayList = new ArrayList<>();

        int z = 0;
        while ( z < exp.size()){
            Monomial aux = new Monomial(0, 0);
            aux.setCoefficient(coef.get(z));
            aux.setExponent(exp.get(z));
            System.out.println(aux.getCoefficient() + " " + aux.getExponent());
            monomialArrayList.add(aux);
            z++;
        }
                */
    }
}