package views;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Васили on 26.12.2016.
 */
public class RightPanel extends JPanel {

       public void addPanel(JFrame owner){
            JPanel newPanel = new JPanel();
           newPanel.setLayout(new BoxLayout(newPanel,BoxLayout.Y_AXIS));
           newPanel.setSize(100, 200);
           newPanel.setVisible(true);
           owner.getContentPane().add(newPanel,BorderLayout.NORTH);
       }
}
