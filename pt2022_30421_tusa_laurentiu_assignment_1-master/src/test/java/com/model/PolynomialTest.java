package com.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialTest {

    @Test
    void getPol() {
        Monomial m = new Monomial(3, 1);
        ArrayList<Monomial> ml = new ArrayList<>();
        ml.add(m);
        Polynomial p = new Polynomial(ml);
        assertEquals(ml, p.getPol());
    }

    @Test
    void separateTerms() {
        Polynomial p1 = new Polynomial(new ArrayList<Monomial>());
        ArrayList<String> test = new ArrayList<>();
        test.add("-2x^2");
        test.add("+3x");
        test.add("-4");
        assertEquals(test, p1.separateTerms("-2x^2+3x-4"));
    }

    @Test
    void add() {//this test basically implies the correctness of most other methods
        Polynomial p1 = new Polynomial(new ArrayList<Monomial>());
        Polynomial p2 = new Polynomial(new ArrayList<Monomial>());

        p1.constructPol("2x^3-4x+2");
        p2.constructPol("-x^3+8x+2");
        assertEquals("+1x^3+4x+4", p1.add(p2));
    }
}