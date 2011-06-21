/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lpad;

import java.lang.String;
import javax.swing.JOptionPane;
import javax.swing.UIManager;


public class SetLookAndFeel {

    public String look() {

        String[] lookAndFeel = {"javax.swing.plaf.metal.MetalLookAndFeel",
        "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel","com.sun.java.swing.plaf.motif.MotifLookAndFeel",
        "com.sun.java.swing.plaf.windows.WindowsLookAndFeel","com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel"};
        Object[] possibilities = {"ham", "spam", "yam"};
        String ans = (String)JOptionPane.showInputDialog(
                    null,
                    "Skin",
                    "Sekect Skin",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                   lookAndFeel,
                    lookAndFeel);

        JOptionPane.showMessageDialog(null, ans);
        //lookAndFeel=ans;

        return ans;
    }


}
