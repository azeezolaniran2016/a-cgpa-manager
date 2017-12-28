/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cgpa;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author AZYSS
 */
public class Course4 extends Course{
    
    private static final double GRADE_SYSTEM = 4.0 ; // 4 point grade system.
    
    /**
     * Constructor for this course
     * @param cCode course code
     * @param cName course name
     */
    Course4(String cCode, String cName){
        super(cCode, cName);
    }
    
    Course4(String code, String title, String grade, int unit, int courseScore, boolean registered ){
        super( code, title, grade, unit, courseScore, registered );
    }
    
    @Override
    void doGradeComboBoxInit(){
        //super.doGradeComboBoxInit();
        String[] grades = new String[]{"Grade ?", "   A", "   B", "   C", "   D", "   F", "   P"};
        this.gradeComboBox = new JComboBox(grades);
        this.gradeComboBox.setToolTipText("Grade");
        this.gradeComboBox.setOpaque(false) ;
            this.gradeComboBox.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                
            // change the grade color to red if its an F
            if(Course4.this.gradeComboBox.getSelectedIndex() == 5)
                Course4.this.getCourseCodeLabel().setForeground(Color.red);
            else
                Course4.this.getCourseCodeLabel().setForeground(Color.black);
                 }
        });
            this.gradeComboBox.setRenderer(new DefaultListCellRenderer(){
            @Override
            public void paint(Graphics g){
                setForeground(Color.black);
                super.paint(g);
            }
            });
    }
    
    @Override 
    double getCourseUnit(){
        if(this.gradeComboBox.getSelectedIndex() == 6){  // grade p
            return 0 ;
        }
        try{
            return Double.parseDouble(this.getUnitTextField().getText()) ;
        }catch (NumberFormatException nfe){
            return -1 ;
        }
    }
    
    @Override
    double evaluateGradeTCP(){
        double evaluant = 0;
        switch(this.gradeComboBox.getSelectedIndex()){
            case 6:
                evaluant = 0 ;
                this.setCourseCarried(false); ;
                break ;
            case 1:
                evaluant = 4 * getCourseUnit() ;
                this.setCourseCarried(false); ;
                break ;
            case 2:
                evaluant = 3 * getCourseUnit() ;
                this.setCourseCarried(false); ;
                break ;
            case 3:
                evaluant = 2 * getCourseUnit() ;
                this.setCourseCarried(false); ;
                break ;
            case 4:
                evaluant = 1 * getCourseUnit() ;
                this.setCourseCarried(false); ;
                break ;
            case 5:
                evaluant = 0 * getCourseUnit() ;
                this.setCourseCarried(true) ;
                break ;
            case 0:
                JOptionPane.showMessageDialog(gradeComboBox, "Select a grade.", this.getCourseCode(), JOptionPane.ERROR_MESSAGE);
                this.enableCourse();
                break ;
        }
        return evaluant ;
    }
    
    /**
     *
     * @param grade
     */
    @Override
    protected void setCourseGrade(String grade){
        if(grade.equalsIgnoreCase("   A")){
            this.gradeComboBox.setSelectedIndex(1);
            this.gradeComboBox.setForeground(Color.BLACK);
        }
        else if(grade.equalsIgnoreCase("   B")){
            this.gradeComboBox.setSelectedIndex(2);
            this.gradeComboBox.setForeground(Color.BLACK);
        }
        else if(grade.equalsIgnoreCase("   C")){
            this.gradeComboBox.setSelectedIndex(3);
            this.gradeComboBox.setForeground(Color.BLACK);
        }
        else if(grade.equalsIgnoreCase("   D")){
            this.gradeComboBox.setSelectedIndex(4);
            this.gradeComboBox.setForeground(Color.BLACK);
        }
        /*
        else if(grade.equalsIgnoreCase("E")){
            this.gradeComboBox.setSelectedIndex(5);
            this.gradeComboBox.setForeground(Color.BLACK);
        }
        */
        else if(grade.equalsIgnoreCase("   F")){
            this.gradeComboBox.setSelectedIndex(5);
            this.gradeComboBox.setForeground(Color.red);
        }
        else if(grade.equalsIgnoreCase("   P")){
            this.gradeComboBox.setSelectedIndex(6);
            this.gradeComboBox.setForeground(Color.BLACK);
        }
        else{
            this.gradeComboBox.setSelectedIndex(0);
            this.gradeComboBox.setForeground(Color.BLACK);
        }
    }
    
    /**
     * method to check if this course if carried
     * @return return true if this course is carried and false if it is not
     */
    @Override
    boolean isCourseCarried(){
        if(this.gradeComboBox.getSelectedIndex() == 5) // f
            return true;
        else
            return false ;
    }
    
      /**
     * Method to determine the equivalent grade of the scores
     * @param grade grade to set
     */
    @Override
    protected void setGradeFromScore(double grade){
        if(this.gradeComboBox.getSelectedIndex() == 7){
            //do nothing
        }else{
            if(grade < 45)
                this.gradeComboBox.setSelectedIndex(5);
            else if(grade >= 45 && grade < 50)
                this.gradeComboBox.setSelectedIndex(4);
            else if(grade >= 50 && grade < 60)
                this.gradeComboBox.setSelectedIndex(3);
            else if(grade >= 60 && grade < 70)
                this.gradeComboBox.setSelectedIndex(2);
            else if(grade >= 70 && grade < 100)
                this.gradeComboBox.setSelectedIndex(1);
            else{
            // do nothing
            }
        }
        
    }
}
