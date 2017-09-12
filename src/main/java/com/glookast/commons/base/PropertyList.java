package com.glookast.commons.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

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
        for (Iterator<Entry> it = entry.iterator(); it.hasNext();) {
            Entry e = it.next();
            if (Objects.equals(e.getKey(), key)) {
                it.remove();
            }
        }
    }

    public String get(String key, String defaultValue)
    {
        for (Entry e : entry) {
            if (Objects.equals(e.getKey(), key)) {
                return e.getValue();
            }
        }
        return defaultValue;
    }

    public int get(String key, int defaultValue)
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

    public long get(String key, long defaultValue)
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

    public String get(String key)
    {
        return get(key, null);
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
    }
}
