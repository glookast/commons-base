package com.glookast.commons.base;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PropertyList", namespace = "http://base.commons.glookast.com", propOrder = {
    "entry"
})
public class PropertyList implements Serializable
{
    protected final List<PropertyList.Entry> entry;

    public PropertyList()
    {
        entry = new ArrayList<>();
    }

    public PropertyList(PropertyList propertyList)
    {
        entry = new ArrayList<>();
        for (Entry e : propertyList.entry) {
            entry.add(new Entry(e));
        }
    }

    public List<PropertyList.Entry> getEntry()
    {
        return entry;
    }

    public void add(String key, String... value)
    {
        for (String v : value) {
            entry.add(new Entry(key, v));
        }
    }

    public void put(String key, String... value)
    {
        remove(key);
        add(key, value);
    }

    public void add(String key, int... value)
    {
        for (int v : value) {
            entry.add(new Entry(key, String.valueOf(v)));
        }
    }

    public void put(String key, int... value)
    {
        remove(key);
        add(key, value);
    }

    public void add(String key, long... value)
    {
        for (long v : value) {
            entry.add(new Entry(key, String.valueOf(v)));
        }
    }

    public void put(String key, long... value)
    {
        remove(key);
        add(key, value);
    }

    public void add(String key, boolean... value)
    {
        for (boolean v : value) {
            entry.add(new Entry(key, String.valueOf(v)));
        }
    }

    public void put(String key, boolean... value)
    {
        remove(key);
        add(key, value);
    }

    public void add(String key, double... value)
    {
        for (double v : value) {
            entry.add(new Entry(key, String.valueOf(v)));
        }
    }

    public void put(String key, double... value)
    {
        remove(key);
        add(key, value);
    }

    public void add(String key, UUID... value)
    {
        for (UUID v : value) {
            entry.add(new Entry(key, String.valueOf(v)));
        }
    }

    public void put(String key, UUID... value)
    {
        remove(key);
        add(key, value);
    }

    public boolean containsKey(String key)
    {
        for (Entry e : entry) {
            if (Objects.equals(e.getKey(), key)) {
                return true;
            }
        }
        return false;
    }

    public void remove(String key)
    {
        for (Iterator<Entry> it = entry.iterator(); it.hasNext(); ) {
            Entry e = it.next();
            if (Objects.equals(e.getKey(), key)) {
                it.remove();
            }
        }
    }

    public String getValue(String key, String defaultValue)
    {
        for (Entry e : entry) {
            if (Objects.equals(e.getKey(), key)) {
                return e.getValue();
            }
        }
        return defaultValue;
    }

    public String getValue(String key)
    {
        return getValue(key, null);
    }

    public int getIntValue(String key, int defaultValue)
    {
        try {
            for (Entry e : entry) {
                if (Objects.equals(e.getKey(), key)) {
                    return Integer.valueOf(e.getValue());
                }
            }
        } catch (Exception ex) {
        }
        return defaultValue;
    }

    public int getIntValue(String key)
    {
        return getIntValue(key, 0);
    }

    public long getLongValue(String key, long defaultValue)
    {
        try {
            for (Entry e : entry) {
                if (Objects.equals(e.getKey(), key)) {
                    return Long.valueOf(e.getValue());
                }
            }
        } catch (Exception ex) {
        }
        return defaultValue;
    }

    public long getLongValue(String key)
    {
        return getLongValue(key, 0);
    }

    public boolean getBoolValue(String key, boolean defaultValue)
    {
        try {
            for (Entry e : entry) {
                if (Objects.equals(e.getKey(), key)) {
                    return Boolean.valueOf(e.getValue());
                }
            }
        } catch (Exception ex) {
        }
        return defaultValue;
    }

    public boolean getBoolValue(String key)
    {
        return getBoolValue(key, false);
    }

    public double getDoubleValue(String key, double defaultValue)
    {
        try {
            for (Entry e : entry) {
                if (Objects.equals(e.getKey(), key)) {
                    return Double.valueOf(e.getValue());
                }
            }
        } catch (Exception ex) {
        }
        return defaultValue;
    }

    public double getDoubleValue(String key)
    {
        return getDoubleValue(key, 0.0);
    }

    public UUID getUUIDValue(String key, UUID defaultValue)
    {
        try {
            for (Entry e : entry) {
                if (Objects.equals(e.getKey(), key)) {
                    return UUID.fromString(e.getValue());
                }
            }
        } catch (Exception ex) {
        }
        return defaultValue;
    }

    public UUID getUUIDValue(String key)
    {
        return getUUIDValue(key, null);
    }

    public List<String> getAll(String key)
    {
        List<String> list = new ArrayList<>();
        for (Entry e : entry) {
            if (Objects.equals(e.getKey(), key)) {
                list.add(e.getValue());
            }
        }
        return list;
    }

    public void clear()
    {
        entry.clear();
    }

    @Override
    public String toString()
    {
        return "PropertyList{" + "entry=" + entry + '}';
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
        PropertyList that = (PropertyList) o;
        return Objects.equals(entry, that.entry);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(entry);
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "key",
        "value"
    })
    public static class Entry implements Serializable
    {
        @XmlElement(required = true)
        protected String key;
        @XmlElement(required = true)
        protected String value;

        public Entry()
        {
        }

        public Entry(Entry entry)
        {
            this.key = entry.key;
            this.value = entry.value;
        }

        public Entry(String key, String value)
        {
            this.key = key;
            this.value = value;
        }

        public String getKey()
        {
            return key;
        }

        public void setKey(String value)
        {
            this.key = value;
        }

        public String getValue()
        {
            return value;
        }

        public void setValue(String value)
        {
            this.value = value;
        }

        @Override
        public String toString()
        {
            return "Entry{" + "key=" + key + ", value=" + value + '}';
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
            Entry entry = (Entry) o;
            return Objects.equals(key, entry.key) &&
                   Objects.equals(value, entry.value);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(key, value);
        }
    }
}
