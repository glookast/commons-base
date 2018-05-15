package com.glookast.commons.base;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Rational", namespace = "http://base.commons.glookast.com", propOrder = {
    "numerator",
    "denominator"
})
public class Rational implements Serializable
{
    protected int numerator;
    protected int denominator;

    public Rational()
    {
    }

    public Rational(int numerator, int denominator)
    {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public int getNumerator()
    {
        return numerator;
    }

    public void setNumerator(int value)
    {
        this.numerator = value;
    }

    public int getDenominator()
    {
        return denominator;
    }

    public void setDenominator(int value)
    {
        this.denominator = value;
    }

    private int gcd(int a, int b)
    {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public void simplify()
    {
        int divisor = gcd(numerator, denominator);
        if (divisor != 0) {
            numerator /= divisor;
            denominator /= divisor;
        }
    }

    public int getRoundedValue()
    {
        if (denominator == 0) {
            return 0;
        }
        return (numerator + (denominator / 2)) / denominator;
    }

    public double getDoubleValue()
    {
        if (denominator == 0) {
            return 0;
        }
        return (double) numerator / (double) denominator;
    }

    public enum StringType
    {
        Rational,
        Decimal
    }

    @Override
    public String toString()
    {
        return toString(this, StringType.Rational);
    }

    public String toString(StringType stringType)
    {
        return toString(this, stringType);
    }

    public static String toString(Rational r, StringType stringType)
    {
        if (r != null && r.getDenominator() != 0) {
            int num = r.getNumerator();
            int den = r.getDenominator();

            switch (stringType) {
                case Rational:
                    return String.format("%d/%d", num, den);
                case Decimal:
                    if (((num / den) * den) == num) {
                        return String.format("%d", num / den);
                    } else {
                        return String.format("%.2f", r.getDoubleValue());
                    }
            }
        }

        return "NaN";
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Rational rational = (Rational) o;
        return numerator == rational.numerator &&
               denominator == rational.denominator;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(numerator, denominator);
    }

    public static Rational valueOf(String value)
    {
        if (value == null) {
            throw new NullPointerException();
        }

        Throwable cause = null;

        try {
            String[] split = value.split("/");
            if (split.length == 2) {
                int numerator = Integer.valueOf(split[0]);
                int denominator = Integer.valueOf(split[1]);
                return new Rational(numerator, denominator);
            }
        } catch (Exception ex) {
            cause = ex;
        }

        throw new IllegalArgumentException("'" + value + "' is not a valid Rational", cause);
    }
}
