/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cgpa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 *
 * @author AZYSS
 * a java implementation of an Academic Session
 */
public class Session  extends JPanel{
    private double sessionCreditPoint ; //  total of all credit point for this session
    private double sessionUnit ; // total of all unit for this session
    private double sessionGradePoint ;  // grade point for this session
    
    private Semester firstSemester ;   // first semester
    private Semester secondSemester ;  // second semester
    
    private JPanel semesterOnePanel = new JPanel();
    private JPanel semesterTwoPanel = new JPanel();
    private JLabel advertLabel ;
    
    
    private ViewSemesterResults vsr ;
    
    /**
     * Constructor for an object of this class
     */
    Session(Semester o, Semester t){
        super();
        
        this.init( o, t);
    }
    
    /**
     * Constructor 2 for an object of this class
     */
    Session(){
        super();
    }
    
    /**
     * Method to carry out all necessary initialization for  
     * a new object of this class
     */
    private void init(Semester one, Semester two){
        this.setOpaque(false);
        
        this.firstSemester = one;
        this.secondSemester = two;
        
        //JLabel to hold semester one title
        JLabel semesterOneLabel = new JLabel();
        semesterOneLabel.setText("FIRST SEMESTER COURSES");
        semesterOneLabel.setHorizontalAlignment(JLabel.CENTER);
        
        //Semester One Panel
        semesterOnePanel.setOpaque(false);
        semesterOnePanel.setLayout(new BorderLayout());
        semesterOnePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        semesterOnePanel.add(semesterOneLabel, BorderLayout.NORTH);
        semesterOnePanel.add(this.firstSemester, BorderLayout.CENTER);
        
        //JLabel to hold semester two title
        JLabel semesterTwoLabel = new JLabel();
        semesterTwoLabel.setText("SECOND SEMESTER COURSES");
        semesterTwoLabel.setHorizontalAlignment(JLabel.CENTER);
        
        //Semester Two Panel
        semesterTwoPanel.setOpaque(false);
        semesterTwoPanel.setLayout(new BorderLayout());
        semesterTwoPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        semesterTwoPanel.add(semesterTwoLabel, BorderLayout.NORTH);
        semesterTwoPanel.add(this.secondSemester, BorderLayout.CENTER);
        
        //Buttons for actions in this semester
        JButton registerFirstSemesterCoursesButton = new JButton("Register/Remove Courses");
        registerFirstSemesterCoursesButton.setToolTipText("Register or remove courses in this semester");
        registerFirstSemesterCoursesButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("register first semester courses button pressed");
            }
        });
        
        JButton viewFirstSemesterResultsButton = new JButton("View Semester Results");
        viewFirstSemesterResultsButton.setToolTipText("View a break down of this semester results");
        viewFirstSemesterResultsButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                /*System.out.println("View first semester results button pressed");
                System.out.println("First Semester Total Unit = " + Session.this.firstSemester.getSemesterUnit());
                System.out.println("First Semester Total Credit Point = " + Session.this.firstSemester.getSemesterCreditPoint());
                System.out.println("First semester Registered courses = " + Session.this.firstSemester.getSemesterRegisteredCoursesCount());
                System.out.println("First Semester Missed Courses = " + Session.this.firstSemester.getSemesterMissedCoursesCount());
                System.out.println("First Semester Offered Courses = " + Session.this.firstSemester.getSemesterOfferedCoursesCount());
                System.out.println("First Semester Carry Overs = " + Session.this.firstSemester.getSemesterCarryOversCount());
                System.out.println("First Semester GP = " + Session.this.firstSemester.getSemesterGradePoint()); 
                */
                /*JOptionPane.showMessageDialog(Session.this.firstSemester,"Semester Registered Courses = " + Session.this.firstSemester.getSemesterRegisteredCoursesCount()
                        +"\nSemester Offered Courses = " + Session.this.firstSemester.getSemesterOfferedCoursesCount()
                        +"\nSemester Missed Courses = " + Session.this.firstSemester.getSemesterMissedCoursesCount()
                        +"\nSemester Carry Over Courses = " + Session.this.firstSemester.getSemesterCarryOversCount()
                        + "\n\nSemester Total Unit = " + Session.this.firstSemester.getSemesterUnit()
                        + "\nSession Session TCP = " + Session.this.firstSemester.getSemesterCreditPoint()
                        + "\n\nSemester GP = " + Session.this.firstSemester.getSemesterGradePoint(), "First Semester Results Break Down", JOptionPane.PLAIN_MESSAGE);
                */
                
            }
        });
        
        JButton resetAllCoursesInFirstSemesterButton = new JButton("Reset All Courses To Default");
        resetAllCoursesInFirstSemesterButton.setToolTipText("Resests all courses in this semester to their default values");
        resetAllCoursesInFirstSemesterButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("reset all courses in first semester button pressed");
                Session.this.firstSemester.resetSemesterCoursesToDefault();
            }
        });
        
        JButton registerSecondSemesterCoursesButton = new JButton("Register/Remove Courses");
        registerSecondSemesterCoursesButton.setToolTipText("Register or remove courses in this semester");
        registerSecondSemesterCoursesButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
               System.out.println("registerSecondSemesterCourses button was pressed");
            }
        });
        
        JButton viewSecondSemesterResultsButton = new JButton("View Semester Results");
        viewSecondSemesterResultsButton.setToolTipText("View a break down of this semester results");
        viewSecondSemesterResultsButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                /*System.out.println("view second semester results button has been pressed");
                System.out.println("Second Semester Total Unit = " + Session.this.secondSemester.getSemesterUnit());
                System.out.println("Second Semester Total Credit Point = " + Session.this.secondSemester.getSemesterCreditPoint());
                System.out.println("Second semester Registered courses = " + Session.this.secondSemester.getSemesterRegisteredCoursesCount());
                System.out.println("Second Semester Missed Courses = " + Session.this.secondSemester.getSemesterMissedCoursesCount());
                System.out.println("Second Semester Offered Courses = " + Session.this.secondSemester.getSemesterOfferedCoursesCount());
                System.out.println("Second Semester Carry Overs = " + Session.this.secondSemester.getSemesterCarryOversCount());
                System.out.println("Second Semester GP = " + Session.this.secondSemester.getSemesterGradePoint());
                */
                JOptionPane.showMessageDialog(Session.this.secondSemester,"Semester Registered Courses = " + Session.this.secondSemester.getSemesterRegisteredCoursesCount()
                        +"\nSemester Offered Courses = " + Session.this.secondSemester.getSemesterOfferedCoursesCount()
                        +"\nSemester Missed Courses = " + Session.this.secondSemester.getSemesterMissedCoursesCount()
                        +"\nSemester Carry Over Courses = " + Session.this.secondSemester.getSemesterCarryOversCount()
                        + "\n\nSemester Total Unit = " + Session.this.secondSemester.getSemesterUnit()
                        + "\nSession Session TCP = " + Session.this.secondSemester.getSemesterCreditPoint()
                        + "\n\nSemester GP = " + Session.this.secondSemester.getSemesterGradePoint(), "Second Semester Results Break Down", JOptionPane.PLAIN_MESSAGE);
            }
        });
        
        JButton resetAllCoursesInSecondSemesterButton = new JButton("Reset All Courses To Default");
        resetAllCoursesInSecondSemesterButton.setToolTipText("Resets all courses in this semester to their default values");
        resetAllCoursesInSecondSemesterButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("reset all courses in second semester button pressed");
                Session.this.secondSemester.resetSemesterCoursesToDefault();
            }
        });
        
        JButton viewSessionResultButton = new JButton("View Session Results");
        viewSessionResultButton.setToolTipText("View this session result break down");
        viewSessionResultButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("view session result button was pressed");
                System.out.println("Session Total Unit = " + Session.this.getSessionUnit());
                System.out.println("Session Session TCP = " + Session.this.getSessionCreditPoint());
                System.out.println("Session GP = " + Session.this.getSessionGradePoint());
                JOptionPane.showMessageDialog(Session.this,"Session Registered Courses = " + Session.this.getSessionRegisteredCoursesCount()
                        +"\nSession Offered Courses = " + Session.this.getSessionOfferedCoursesCount()
                        +"\nSession Missed Courses = " + Session.this.getSessionMissedCoursesCount()
                        +"\nSession Carry Over Courses = " + Session.this.getSessionCarryOversCount()
                        + "\n\nSession Total Unit = " + Session.this.getSessionUnit()
                        + "\nSession Session TCP = " + Session.this.getSessionCreditPoint()
                        + "\n\nSession GP = " + Session.this.getSessionGradePoint(), "Session Results Break Down", JOptionPane.PLAIN_MESSAGE);
            }
        });
        
        //panel to hold first semester buttons
        JPanel firstSemesterButtonsPanel = new JPanel();
        firstSemesterButtonsPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        firstSemesterButtonsPanel.setLayout(new BorderLayout());
        JLabel fLabel = new JLabel("First Semester");
        fLabel.setForeground(Color.BLUE);
        fLabel.setHorizontalAlignment(JLabel.CENTER);
        firstSemesterButtonsPanel.add(fLabel, BorderLayout.NORTH);
        JPanel bPanel = new JPanel();
        bPanel.setLayout(new GridLayout(3,1,5,0));
        bPanel.add(viewFirstSemesterResultsButton);
        bPanel.add(registerFirstSemesterCoursesButton);
        bPanel.add(resetAllCoursesInFirstSemesterButton);
        firstSemesterButtonsPanel.add(bPanel, BorderLayout.CENTER);
        
        //panel to hold second semester buttons
        JPanel secondSemesterButtonsPanel = new JPanel();
        secondSemesterButtonsPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        secondSemesterButtonsPanel.setLayout(new BorderLayout());
        JLabel sLabel = new JLabel("Second Semester");
        sLabel.setForeground(Color.BLUE);
        sLabel.setHorizontalAlignment(JLabel.CENTER);
        secondSemesterButtonsPanel.add(sLabel, BorderLayout.NORTH);
        JPanel cPanel = new JPanel();
        cPanel.setLayout(new GridLayout(3,1,5,0));
        cPanel.add(viewSecondSemesterResultsButton);
        cPanel.add(registerSecondSemesterCoursesButton);
        cPanel.add(resetAllCoursesInSecondSemesterButton);
        secondSemesterButtonsPanel.add(cPanel, BorderLayout.CENTER);
        
        //panel to hold session buttons
        JPanel sessionButtonPanel = new JPanel();
        sessionButtonPanel.setLayout(new BorderLayout());
        JLabel aLabel = new JLabel("Session");
        aLabel.setForeground(Color.BLUE);
        aLabel.setHorizontalAlignment(JLabel.CENTER);
        //sessionButtonPanel.add(aLabel, BorderLayout.NORTH);
        sessionButtonPanel.add(viewSessionResultButton, BorderLayout.NORTH);
        sessionButtonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        
        
        //main Panel to hold action buttons
        JPanel mainButtonPanel = new JPanel();
        mainButtonPanel.setLayout(new BorderLayout());
        mainButtonPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        JPanel hPanel = new JPanel();
        hPanel.setLayout(new GridLayout(1,2));
        hPanel.add(firstSemesterButtonsPanel);
        hPanel.add(secondSemesterButtonsPanel);
        mainButtonPanel.add(hPanel, BorderLayout.NORTH);
        mainButtonPanel.add(sessionButtonPanel, BorderLayout.CENTER);
        
        
        //advert label stuff
        this.advertLabel = new JLabel("place advert here");
        //this.advertLabel.setFont(new Font(Font.DIALOG, Font.ITALIC), 30);
        
        
        //panel to hold adverts
        JPanel advertPanel = new JPanel();
        advertPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY), "Adverts", TitledBorder.CENTER, TitledBorder.BELOW_TOP, null, Color.cyan));
        advertPanel.add(advertLabel);
        
        //panel to hold the advert panel and main button panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(mainButtonPanel, BorderLayout.SOUTH);
        centerPanel.add(advertPanel, BorderLayout.CENTER);
        
        //Main panel
        //JPanel mainPanel = new JPanel();
        //mainPanel.setLayout(new BorderLayout(2,2));
        //mainPanel.setOpaque(false);
        //mainPanel.setBackground(Color.pink);
       // mainPanel.add(semesterOnePanel, BorderLayout.WEST);
        //mainPanel.add(semesterTwoPanel, BorderLayout.EAST);
       // mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        this.setLayout(new BorderLayout());
        //this.add(mainPanel, BorderLayout.CENTER);
        this.setBackground(Color.BLACK);
    }
    
    /**
     * method to get the first semester in this session\
     * @param semester for this session
     */
    Semester getFirstSemester(){
        return this.firstSemester;
    }
    
    /**
     * method to get the first semester panel in this session
     * @return first semester in this session
     */
    JPanel getFirstSemesterPanel(){
        return this.semesterOnePanel;
    }
    
    
    /**
     * Method to get the second semester panel in this session
     * @param Semester for this session
     */
    Semester getSecondSemester(){
        return this.secondSemester;
    }
    
    
    /**
     * Method to get the second semester panel in this session
     */
    JPanel getSecondSemesterPanel(){
        return this.semesterTwoPanel ;
    }
    
    /**
     * Method to set this session credit point
     */
    void setSessionCreditPoint(){
        //this.firstSemester.getSemesterCreditPoint();
        //this.secondSemester.setSemesterCreditPoint();
        this.sessionCreditPoint = this.firstSemester.getSemesterCreditPoint() + this.secondSemester.getSemesterCreditPoint() ;
    }
    
    /**
     * Method to get this session credit point
     * @return session credit point
     */
    double getSessionCreditPoint(){
        this.setSessionCreditPoint();
        return this.sessionCreditPoint ;
    }
    
    /**
     * Method to set the session unit
     */
    void setSessionUnit(){
        this.sessionUnit = this.secondSemester.getSemesterUnit() + this.firstSemester.getSemesterUnit();
    }
    
    /**
     * Method to get this session unit
     * @return total unit for all courses in this session
     */
    double getSessionUnit(){
        this.setSessionUnit();
        return this.sessionUnit;
    }
    
    /**
     * Method to set this session grade point
     */
    void setSessionGradePoint(){
        if (this.getSessionUnit() > 0)
            this.sessionGradePoint = (this.getSessionCreditPoint() / this.getSessionUnit());
        else
            this.sessionGradePoint = 0;
    }
    
    /**
     * Method to get this session grade point
     * @return session GP
     */
    double getSessionGradePoint(){
        this.setSessionGradePoint();
        return this.sessionGradePoint;
    }
    
    /**
     * Method to get the total number of carry overs in this session
     * @return total number of carryovers in this session
     */
    int getSessionCarryOversCount(){
        return this.firstSemester.getSemesterCarryOversCount() + this.secondSemester.getSemesterCarryOversCount();
    }
    
    /**
     * Method to get the total number of courses registered in this session
     * @return total number of courses registered
     */
    int getSessionRegisteredCoursesCount(){
        return this.firstSemester.getSemesterRegisteredCoursesCount() + this.secondSemester.getSemesterRegisteredCoursesCount();
    }
    
    /**
     * Method to get the total number of missed courses
     * @return total number of missed courses in this session
     */
    int getSessionMissedCoursesCount(){
        return this.firstSemester.getSemesterMissedCoursesCount() + this.secondSemester.getSemesterMissedCoursesCount();
    }
    
    /**
     * Method to get the total number of courses offered in this session
     * @return total number of courses offered in this session
     */
    int getSessionOfferedCoursesCount(){
        return this.firstSemester.getSemesterOfferedCoursesCount() + this.secondSemester.getSemesterOfferedCoursesCount();
    }
    
    /**
     * method to view the first semester results
    */
    void viewFirstSemesterResult(){
        /*JOptionPane.showMessageDialog(Session.this.firstSemester,"Semester Registered Courses = " + Session.this.firstSemester.getSemesterRegisteredCoursesCount()
                        +"\nSemester Offered Courses = " + Session.this.firstSemester.getSemesterOfferedCoursesCount()
                        +"\nSemester Missed Courses = " + Session.this.firstSemester.getSemesterMissedCoursesCount()
                        +"\nSemester Carry Over Courses = " + Session.this.firstSemester.getSemesterCarryOversCount()
                        + "\n\nSemester Total Unit = " + Session.this.firstSemester.getSemesterUnit()
                        + "\nSession Session TCP = " + Session.this.firstSemester.getSemesterCreditPoint()
                        + "\n\nSemester GP = " + Session.this.firstSemester.getSemesterGradePoint(), "First Semester Results Break Down", JOptionPane.PLAIN_MESSAGE);
                        */
        AuxilliaryWindow window = new AuxilliaryWindow(new ViewSemesterResults(Session.this.getFirstSemester()));
        window.setSize(ViewSemesterResults.SEMESTER_RESULT_BOARD_DIMENSION);
        window.setTitle("Result Board");
        window.setLocationRelativeTo(this.getFirstSemester());
        window.setVisible(true);
    }
    
    /**
     * method to view the second semester results
     */
    void viewSecondSemesterResult(){
        /*JOptionPane.showMessageDialog(Session.this.secondSemester,"Semester Registered Courses = " + Session.this.secondSemester.getSemesterRegisteredCoursesCount()
                        +"\nSemester Offered Courses = " + Session.this.secondSemester.getSemesterOfferedCoursesCount()
                        +"\nSemester Missed Courses = " + Session.this.secondSemester.getSemesterMissedCoursesCount()
                        +"\nSemester Carry Over Courses = " + Session.this.secondSemester.getSemesterCarryOversCount()
                        + "\n\nSemester Total Unit = " + Session.this.secondSemester.getSemesterUnit()
                        + "\nSession Session TCP = " + Session.this.secondSemester.getSemesterCreditPoint()
                        + "\n\nSemester GP = " + Session.this.secondSemester.getSemesterGradePoint(), "Second Semester Results Break Down", JOptionPane.PLAIN_MESSAGE);
        */
        AuxilliaryWindow window = new AuxilliaryWindow(new ViewSemesterResults(Session.this.getSecondSemester()));
        window.setSize(ViewSemesterResults.SEMESTER_RESULT_BOARD_DIMENSION);
        window.setTitle("Result Board");
        window.setLocationRelativeTo(this.getSecondSemester());
        window.setVisible(true);
    }
    
    /**
     * method to view this session result breakdown
     */
    void viewSessionResult(JComponent parent){
        JOptionPane.showMessageDialog(parent,"Session Registered Courses = " + Session.this.getSessionRegisteredCoursesCount()
                        +"\nSession Offered Courses = " + Session.this.getSessionOfferedCoursesCount()
                        +"\nSession Missed Courses = " + Session.this.getSessionMissedCoursesCount()
                        +"\nSession Carry Over Courses = " + Session.this.getSessionCarryOversCount()
                        + "\n\nSession Total Unit = " + Session.this.getSessionUnit()
                        + "\nSession Session TCP = " + Session.this.getSessionCreditPoint()
                        + "\n\nSession GP = " + Session.this.getSessionGradePoint(), "Session Results Break Down", JOptionPane.PLAIN_MESSAGE);
    }
}//End of class declaration
