/*
 *  Authors:
 *     Whizzpered,
 *     Yew_Mentzaki.
 */
package org.tmd.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.tmd.xfg.XFG;
import org.tmd.xfg.XObject;

/**
 *
 * @author yew_mentzaki
 */
public class GameLocale {

    private static XFG currentLocale;
    private static XFG[] locales;

    public static void load(String currentLocale) {
        locales = new XFG[3];
        try {
            locales[0] = new XFG(new File("locale/en_US.locale"));
            locales[1] = new XFG(new File("locale/de_DE.locale"));
            locales[2] = new XFG(new File("locale/ru_RU.locale"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameLocale.class.getName()).log(Level.SEVERE, null, ex);
        }
        setCurrentLocale(currentLocale);
    }

    public static void setCurrentLocale(String currentLocale) {
        for (XFG l : locales) {
            if (l.get("locale_name").getString().equals(currentLocale)) {
                GameLocale.currentLocale = l;
                return;
            }
        }
    }

    public static String get(String key) {
        String o = currentLocale.get(key).getString();
        if (o == null || o.length() < 0) {
            return key;
        } else {
            return o;
        }
    }
}
