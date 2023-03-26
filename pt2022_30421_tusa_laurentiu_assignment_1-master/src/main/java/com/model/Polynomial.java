package com.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {
    private ArrayList<Monomial> pol;

    public Polynomial(ArrayList<Monomial> pol) {
        this.pol = pol;
    }

    public ArrayList<Monomial> getPol() {
        return pol;
    }

    public void setPol(ArrayList<Monomial> pol) {
        this.pol = pol;
    }

    public ArrayList<String> separateTerms(String input) {

        Pattern pattern = Pattern.compile("([+-]?[^-+]+)");
        Matcher matcher = pattern.matcher(input);

        ArrayList<String> term = new ArrayList<>();
        while (matcher.find()) {
            term.add(matcher.group(1));
        }
        return term;
    }

    public ArrayList<Integer> separateCoefficients(ArrayList<String> term) {
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
        return coef;
    }

    public ArrayList<Integer> separateExponents(ArrayList<String> term) {
        Pattern pattern3 = Pattern.compile("(?<=[xX^])([+-]?\\d{1,})|([+-]\\d{1,}[xX]$|[+-][xX]$|^\\d{1,}[xX]$|^[xX]$)|([+-]\\d{1,}$|\\d{1,}$)");
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
        return exp;
    }

    public ArrayList<Monomial> combine(ArrayList<Integer> coef, ArrayList<Integer> exp) {
        ArrayList<Monomial> mL = new ArrayList<>();
        int z = 0;
        while ( z < exp.size()){
            Monomial aux = new Monomial(0, 0);
            aux.setCoefficient(coef.get(z));
            aux.setExponent(exp.get(z));
            mL.add(aux);
            z++;
        }
        return mL;
    }

    public void constructPol (String input) {
        ArrayList<String> term;
        term = this.separateTerms(input);
        ArrayList<Monomial> monomials1;
        monomials1 = this.combine(this.separateCoefficients(term), this.separateExponents(term));
        this.setPol(monomials1);
    }

    public void build(Monomial m, StringBuilder result) {
        if(m.getExponent()>1){
            if(m.getCoefficient()>0){
                result.append("+").append(String.valueOf(m.getCoefficient())).append("x^").append(String.valueOf(m.getExponent()));
            } else {
                result.append(String.valueOf(m.getCoefficient())).append("x^").append(String.valueOf(m.getExponent()));
            }
        } else if (m.getExponent() == 1) {
            if(m.getCoefficient()>0) {
                result.append("+" + String.valueOf(m.getCoefficient()) + "x");
            } else {
                result.append(String.valueOf(m.getCoefficient()) + "x");
            }
        } else if (m.getExponent() == 0) {
            if(m.getCoefficient()>0) {
                result.append("+" + String.valueOf(m.getCoefficient()));
            } else {
                result.append(String.valueOf(m.getCoefficient()));
            }
        } else
            System.out.println("Exponent goes out of bounds!");
    }

    public void buildIntg(int e, float c, StringBuilder result) {
        if(e>1) {
            if(c>0){
                result.append("+" + String.valueOf(c) + "x^" +String.valueOf(e));
            } else {
                result.append(String.valueOf(c) + "x^" +String.valueOf(e));
            }
        } else if(e==1) {
            if(c>0) {
                result.append("+" + String.valueOf(c) + "x");
            } else {
                result.append(String.valueOf(c) + "x");
            }
        }
    }

    public String add(Polynomial p2) {
        StringBuilder result = new StringBuilder();
        ArrayList<Monomial> a = this.getPol();
        ArrayList<Monomial> b = p2.getPol();
        int x = 0, y = 0;
        while (x<a.size() && y<b.size()) {//can still compare exponents
            if(a.get(x).getExponent() > b.get(y).getExponent()){
                build(a.get(x), result);
                x++;
            } else if(a.get(x).getExponent() < b.get(y).getExponent()){
                build(b.get(y), result);
                y++;
            } else {//exponents are equal
                if(a.get(x).getCoefficient() + b.get(y).getCoefficient() != 0) {//if the terms simplify, don't build anything
                    build(new Monomial(a.get(x).getCoefficient() + b.get(y).getCoefficient(), a.get(x).getExponent()), result);
                }
                x++;
                y++;
            }
        }
        while(x<a.size()) {//copy the rest if so
            build(a.get(x), result);
            x++;
        }
        while(y<b.size()) {
            build(b.get(y), result);
            y++;
        }
        return result.toString();
    }

    public String subtract(Polynomial p2) {
        StringBuilder result = new StringBuilder();
        ArrayList<Monomial> a = this.getPol();
        ArrayList<Monomial> b = p2.getPol();
        b.forEach(t->{
            t.setCoefficient(t.getCoefficient()*(-1));
        });
        int x = 0, y = 0;
        while (x<a.size() && y<b.size()) {//can still compare exponents
            if(a.get(x).getExponent() > b.get(y).getExponent()){
                build(a.get(x), result);
                x++;
            } else if(a.get(x).getExponent() < b.get(y).getExponent()){//write term with *(-1)
                build(b.get(y), result);
                y++;
            } else {//exponents are equal
                if(a.get(x).getCoefficient() + b.get(y).getCoefficient() != 0) {//if the terms simplify, don't build anything
                    build(new Monomial(a.get(x).getCoefficient() + b.get(y).getCoefficient(), a.get(x).getExponent()), result);
                }
                x++;
                y++;
            }
        }
        while(x<a.size()) {//copy the rest if so
            build(a.get(x), result);
            x++;
        }
        while(y<b.size()) {
            build(b.get(y), result);
            y++;
        }
        return result.toString();
    }

    public String derive() {
        StringBuilder result = new StringBuilder();
        ArrayList<Monomial> tmp = this.getPol();
        tmp.forEach(t->{
            if(t.getExponent() > 0){
                t.setCoefficient(t.getCoefficient()*t.getExponent());
                t.setExponent(t.getExponent()-1);
            }
        });
        if (tmp.get(tmp.size()-1).getExponent() == 0) {
            tmp.remove(tmp.size() - 1);
        }
        tmp.forEach(u->{
            build(u, result);
        });
        return result.toString();
    }

    public String integrate() {
        StringBuilder result = new StringBuilder();
        ArrayList<Monomial> tmp = this.getPol();
        ArrayList<Integer> expIntg = new ArrayList<>();
        ArrayList<Float> coefIntg = new ArrayList<>();
        this.getPol().forEach(k->{
            expIntg.add(k.getExponent()+1);
            coefIntg.add((float) k.getCoefficient() / (float) (k.getExponent()+1));
        });
        int x= 0;
        while (x<expIntg.size()){
            buildIntg(expIntg.get(x), coefIntg.get(x), result);
            x++;
        }
        return result.toString();
    }
}