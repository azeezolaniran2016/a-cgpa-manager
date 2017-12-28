/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cgpa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author AZYSS
 */
public class AuxilliaryWindow extends JDialog{
    
    /**
     * Window constructor
     * @param comp component to add to this window
     */
    AuxilliaryWindow(JComponent comp){
        super();
        
        //Exit Window Button
        JButton exitWindowButton = new JButton("   Exit   ");
        exitWindowButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                AuxilliaryWindow.this.dispose();
            }
        });
        
        //North panel
        /*
                JPanel auxNorthPanel = new JPanel();
        auxNorthPanel.setOpaque(false);
        auxNorthPanel.add(exitWindowButton);
        */
        JPanel southPanel = new JPanel();
        southPanel.add(exitWindowButton);
        southPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        southPanel.setOpaque(false);
        
        //Mid panel
        JPanel midPanel = new JPanel();
        midPanel.setLayout(new BorderLayout());
        midPanel.setOpaque(true);
        if(comp == null){
            //do nothing
        }else{
            midPanel.add(comp);
        }
        
        this.add(southPanel, BorderLayout.SOUTH);
        this.add(midPanel, BorderLayout.CENTER);
        this.setIconImage(PNGGetter.getFrameIcon());
        this.setModal(true);
        this.pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        //this.setTitle();
    }
    
    /*
    public static void main(String[] args){
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch( ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e){
        }
        AuxilliaryWindow aux = new AuxilliaryWindow(null, "title");
        //aux.setBackground(Color.yellow);
        //aux.setSize(new Dimension(500, 500));
        aux.setLocation(50, 50);
        aux.setVisible(true);
    }
    */
    
}
