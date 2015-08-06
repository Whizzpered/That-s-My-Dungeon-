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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yew_mentzaki
 */
public class XFG extends ArrayList<XObject> {

    public XFG() {
    }

    public XFG(int i) {
        super(i);
    }

    public XFG(String xfgNotation) {
        parse(xfgNotation);
    }

    public void set(String name, Object value) {
        get(name).set(value);
    }

    public void set(String name, String value) {
        get(name).set(value);
    }

    public void set(String name, int value) {
        get(name).set(value);
    }

    public void set(String name, float value) {
        get(name).set(value);
    }

    public void set(String name, double value) {
        get(name).set(value);
    }

    public void set(String name, boolean value) {
        get(name).set(value);
    }

    public XFG(File xfgFile) throws FileNotFoundException {
        String xfgNotation = new String();
        Scanner scanner = new Scanner(xfgFile);
        while (scanner.hasNextLine()) {
            xfgNotation += scanner.nextLine() + '\n';
        }
        parse(xfgNotation);
    }

    public boolean containsName(String name) {
        for (int i = 0; i < size(); i++) {
            if (get(i).name != null) {
                if (get(i).name.equals(name)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean contains(String name) {
        for (int i = 0; i < size(); i++) {
            if (get(i).name != null) {
                if (get(i).name.equals(name)) {
                    return true;
                }
            }
        }
        return false;
    }

    public XObject get(String name) {
        for (int i = 0; i < size(); i++) {
            if (get(i).name != null) {
                if (get(i).name.equals(name)) {
                    return get(i);
                }
            }
        }
        XObject o = new XObject(name);
        add(o);
        return o;
    }

    public void parse(String xfgNotation) {
        char[] c = xfgNotation.toCharArray();
        String name = new String();
        String value = new String();
        boolean readName = true, control = false;
        int blockDepth = 0;
        boolean blockReadName = true;
        for (int caret = 0; caret < c.length; caret++) {
            if (readName) {
                if (c[caret] == '=' && c[caret - 1] != '\\') {
                    readName = false;
                    name = name.trim();
                } else if (c[caret] == '\n' && name.trim().length() > 0) {
                    add(new XObject((Object) name.trim()));
                    readName = true;
                    name = new String();
                    value = new String();
                } else if (c[caret] == '{') {
                    readName = false;
                    blockDepth++;
                } else {
                    name += c[caret];
                }
            } else {
                if (blockDepth > 0) {
                    if (c[caret] == '}') {
                        blockDepth--;
                        if (blockDepth == 0) {
                            System.out.println("XObject!");
                            add(new XObject(name.trim(), value));
                            readName = true;
                            name = new String();
                            value = new String();
                            continue;
                        }
                    } else if (c[caret] == '{') {
                        blockDepth++;
                    }
                    value += c[caret];
                    if (blockReadName && c[caret] == '=' && c[caret - 1] != '\\') {
                        blockReadName = false;
                    }
                    if (c[caret] == '\n') {
                        blockReadName = true;
                    }
                    if (blockReadName && c[caret] == '{' && c[caret - 1] != '\\') {
                        blockDepth++;
                    }
                } else {
                    if (c[caret] == '\n') {
                        add(new XObject(name, (Object) value.trim()));
                        readName = true;
                        name = new String();
                        value = new String();
                    }
                    if (control) {
                        switch (c[caret]) {
                            case '0':
                                value += '\0';
                                break;
                            case 'a':
                                break;
                            case 'b':
                                value += '\b';
                                break;
                            case 't':
                                value += '\t';
                                break;
                            case 'n':
                                value += '\n';
                                break;
                            case 'v':
                                value += '\n';
                                break;
                            case 'f':
                                value += '\f';
                                break;
                            case 'r':
                                value += '\r';
                                break;
                            case '{':
                                value += '{';
                                break;
                            case '=':
                                value += '=';
                                break;
                            case '\\':
                                value += '\\';
                                break;
                        }
                    } else {
                        if (c[caret] == '\\') {
                            control = true;
                        } else {
                            value += c[caret];
                        }
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        String xfgNotation = new String();
        for (int i = 0; i < size(); i++) {
            xfgNotation += get(i).toString(0) + "\n";
        }
        return xfgNotation;
    }

    protected String normalString(String name) {
        name = name.replace("\0", "\\0");
        name = name.replace("\b", "\\b");
        name = name.replace("\t", "\\t");
        name = name.replace("\n", "\\n");
        name = name.replace("\f", "\\f");
        name = name.replace("\r", "\\r");
        name = name.replace("{", "\\{");
        name = name.replace("=", "\\=");
        name = name.replace("\\", "\\\\");
        return name;
    }

    protected String depthString(int depth) {
        String depthString = new String();
        for (int i = 0; i < depth; i++) {
            depthString += '\t';
        }
        return depthString;
    }

    public void writeToFile(File xfgFile) throws FileNotFoundException, IOException {
        if (!xfgFile.exists()) {
            xfgFile.createNewFile();
        }
        PrintWriter printWriter = new PrintWriter(xfgFile);
        for (String string : toString().split("\n")) {
            printWriter.println(string);
        }
        printWriter.close();
    }
}
