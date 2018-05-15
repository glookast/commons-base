package com.glookast.commons.base;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class RationalTest
{

    @Test
    public void getNumerator()
    {
        Rational r = new Rational(30000, 1001);

        assertEquals(30000, r.getNumerator());
    }

    @Test
    public void setNumerator()
    {
        Rational r = new Rational(30000, 1001);

        r.setNumerator(60000);

        assertEquals(60000, r.getNumerator());
    }

    @Test
    public void getDenominator()
    {
        Rational r = new Rational(30000, 1001);

        assertEquals(1001, r.getDenominator());
    }

    @Test
    public void setDenominator()
    {
        Rational r = new Rational(30000, 1001);

        r.setDenominator(1000);

        assertEquals(1000, r.getDenominator());
    }

    @Test
    public void simplify()
    {
        Rational r1 = new Rational(30000, 1001);
        r1.simplify();

        assertEquals(30000, r1.getNumerator());
        assertEquals(1001, r1.getDenominator());

        Rational r2 = new Rational(25000, 1000);
        r2.simplify();

        assertEquals(25, r2.getNumerator());
        assertEquals(1, r2.getDenominator());
    }

    @Test
    public void getRoundedValue()
    {
        Rational r = new Rational(30000, 1001);

        assertEquals(30, r.getRoundedValue());
    }

    @Test
    public void getDoubleValue()
    {
        Rational r = new Rational(30000, 1001);

        assertEquals(30000.0 / 1001.0, r.getDoubleValue(), 0.001);
    }

    @Test
    public void toString1()
    {
        Rational r1 = new Rational(30000, 1001);

        assertEquals(r1.toString(), "30000/1001");

        Rational r2 = new Rational(25, 1);

        assertEquals(r2.toString(), "25/1");
    }

    @Test
    public void toString2()
    {
        Rational r1 = new Rational(30000, 1001);

        assertEquals(r1.toString(Rational.StringType.Rational), "30000/1001");
        assertEquals(r1.toString(Rational.StringType.Decimal), "29.97");

        Rational r2 = new Rational(25, 1);

        assertEquals(r2.toString(Rational.StringType.Rational), "25/1");
        assertEquals(r2.toString(Rational.StringType.Decimal), "25");
    }

    @Test
    public void toString3()
    {
        Rational r1 = new Rational(30000, 1001);

        assertEquals(Rational.toString(r1, Rational.StringType.Rational), "30000/1001");
        assertEquals(Rational.toString(r1, Rational.StringType.Decimal), "29.97");

        Rational r2 = new Rational(25, 1);

        assertEquals(Rational.toString(r2, Rational.StringType.Rational), "25/1");
        assertEquals(Rational.toString(r2, Rational.StringType.Decimal), "25");
    }

    @Test
    public void equals()
    {
        Rational r1 = new Rational(30000, 1001);
        Rational r2 = new Rational(30000, 1001);
        Rational r3 = new Rational(30000, 1000);

        assertEquals(r1, r2);
        assertNotEquals(r1, r3);
    }

    @Test
    public void valueOf1()
    {
        Rational r1 = new Rational(30000, 1001);
        Rational r2 = Rational.valueOf("30000/1001");

        assertEquals(r1, r2);
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test()
    public void valueOf2()
    {
        exception.expect(NullPointerException.class);
        Rational r1 = Rational.valueOf(null);
    }

    @Test()
    public void valueOf3()
    {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("is not a valid Rational");
        Rational r1 = Rational.valueOf("/34/34");
    }
}