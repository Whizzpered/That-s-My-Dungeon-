/*
 * Copyright (C) 2015 yew_mentzaki
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.tmd.xfg;

/**
 *
 * @author yew_mentzaki
 */
public class XObject extends XFG {

    String name;
    Object value;

    public void set(Object value) {
        this.value = value;
    }

    public void set(String value) {
        this.value = value;
    }

    public void set(int value) {
        this.value = new Integer(value);
    }

    public void set(float value) {
        this.value = new Float(value);
    }

    public void set(double value) {
        this.value = new Double(value);
    }

    public void set(boolean value) {
        this.value = (value ? "true" : "false");
    }

    public Object getObject() {
        return value;
    }

    public String getString() {
        if (value == null) {
            return "";
        } else {
            return value.toString();
        }
    }

    public int getInteger() {
        if (value instanceof String) {
            return Integer.parseInt(value.toString());
        }
        if (value instanceof Integer) {
            return ((Integer) value);
        }
        if (value instanceof Float) {
            return ((Float) value).intValue();
        }
        if (value instanceof Double) {
            return ((Double) value).intValue();
        }
        return 0;
    }

    public double getDouble() {
        if (value instanceof String) {
            return Double.parseDouble(value.toString());
        }
        if (value instanceof Integer) {
            return ((Integer) value).doubleValue();
        }
        if (value instanceof Float) {
            return ((Float) value).doubleValue();
        }
        if (value instanceof Double) {
            return ((Double) value);
        }
        return 0;
    }

    public float getFloat() {
        if (value instanceof String) {
            return Float.parseFloat(value.toString());
        }
        if (value instanceof Integer) {
            return ((Integer) value).floatValue();
        }
        if (value instanceof Float) {
            return ((Float) value);
        }
        if (value instanceof Double) {
            return ((Double) value).floatValue();
        }
        return 0;
    }

    public XObject(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public XObject(String name, String xfgNotation) {
        this.name = name;
        parse(xfgNotation);
    }

    public XObject(Object value) {
        this.value = value;
    }

    public XObject(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return toString(0);
    }

    protected String toString(int depth) {
        if (size() == 0) {
            if (name != null) {
                return depthString(depth) + name + " = " + normalString(value.toString());
            }
            return depthString(depth) + normalString(value.toString());
        } else {
            String xfgNotation = name + "{\n";
            for (int i = 0; i < size(); i++) {
                xfgNotation += get(i).toString(depth + 1) + "\n";
            }
            xfgNotation += depthString(depth) + "}";
            return xfgNotation;
        }
    }
}
