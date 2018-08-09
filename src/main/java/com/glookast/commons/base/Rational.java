package com.glookast.commons.base;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Rational implements Serializable, Comparable<Rational>
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
        simplify();
    }

    public Rational(Rational rational)
    {
        this.numerator = rational.numerator;
        this.denominator = rational.denominator;
        simplify();
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
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }

        boolean negative = numerator < 0;

        if (negative) {
            numerator = -numerator;
        }

        int divisor = gcd(numerator, denominator);
        if (divisor != 0) {
            numerator /= divisor;
            denominator /= divisor;
        }

        if (negative) {
            numerator = -numerator;
        }
    }

    @JsonIgnore
    public int getRoundedValue()
    {
        if (denominator == 0) {
            return 0;
        }
        return (numerator + (denominator / 2)) / denominator;
    }

    @JsonIgnore
    public double getDoubleValue()
    {
        if (denominator == 0) {
            return 0;
        }
        return (double) numerator / (double) denominator;
    }

    @Override
    public int compareTo(Rational o)
    {
        if (o == null) {
            return 1;
        }

        long a = ((long) numerator) * ((long) o.denominator);
        long b = ((long) denominator) * ((long) o.numerator);

        if (a > b) {
            return 1;
        } else if (a < b) {
            return -1;
        }

        return 0;
    }

    @Override
    public String toString()
    {
        return toString(this, StringType.RATIONAL);
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
                case RATIONAL:
                    return String.format("%d/%d", num, den);
                case DECIMAL:
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

    public enum StringType
    {
        RATIONAL,
        DECIMAL
    }
}
