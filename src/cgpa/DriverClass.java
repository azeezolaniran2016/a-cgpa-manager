/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cgpa;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author Olaniran Azeez Olawale (AZYSS)
 main class for this application
 */

public class DriverClass {
    static DB_Manipulator dbM ;  // Main db javadb
    private static Session[] sessions ;  // array of sessions
    
    
    /**
     * Constructor for this class
     */
    DriverClass (){
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //gimme the system default look and feel
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch( ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e){
        }
        
        //new thread to start up app in 
        Thread t = new Thread(){
            @Override
            public void run(){
                try{
                    dbM = new DB_Manipulator();
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null, "Error Occured\n" + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                try{
                    sessions = new Session[dbM.getTotalSessions()];
                    if(dbM.getUseCount() < 1){
                        fillData();
                        //create the department here
                        createDepartment(dbM);
                    }else if (dbM.getUseCount() < DB_Manipulator.MAXIMUM_TRIAL_COUNT){
                        //create the department here 
                        int i = LoginPane.createLoginPane(dbM.getUserName(), dbM.getUserPassword());
                        if (i == LoginPane.PASSWORDS_MATCH)
                        createDepartment(dbM);
                    }else{
                        AuxilliaryWindow expWindow = new AuxilliaryWindow(new JLabel(PNGGetter.getOutdatedVersionIcon()));
                        expWindow.setTitle("Outdated Version myGPA !!!");
                        expWindow.setLocation(DriverClass.setPositionToCenter(expWindow.getWidth(), expWindow.getHeight()));
                        expWindow.setVisible(true);
                    }  
                  }catch (Exception exp){
                    System.out.println("Exception thrown oh \n run method of DriverClass Line  " + exp.getMessage());
                    //do nothing
                    }
        
                }
            };
            //start the thread
            t.start();
        
        };//end of main method
        
    /**
     * Method to create an initial user data filling form
     * if the application is run for the first time
     */
    private static void fillData(){
        final UserDataForm udf = new UserDataForm(UserDataForm.FULL_DATA_FORM, dbM);
        udf.setTitle(Department.APP_TITLE);
        udf.setLocation(DriverClass.setPositionToCenter(udf.getWidth(), udf.getHeight()));
        //udf.setModal(true);
        //udf.setIconImage(new ImageIcon(UserDataForm.class.getClass().getResource("/multimedia/png/frameIcon.png")).getImage());
        udf.setVisible(true);
    }
    
    /**
     * Method to create the department
     * @param dbM database to load this departments data/state from
     */
    static void createDepartment(DB_Manipulator dbM){
        int totalSession = dbM.getTotalSessions();
        final Semester[] sms = new Semester[dbM.getTotalSemesters()];
        for(int i = 1; i <= dbM.getTotalSemesters(); i++){
            sms[i-1] = new Semester();
            sms[i-1].addCoursesToSemester(dbM.getSemesterCourseArray(i));
        }
        sessions = new Session[totalSession];
        int tCount = 0 ;
        for(int i = 0; i < sessions.length; i++){
            sessions[i] = new Session(sms[tCount], sms[tCount+1]);
            tCount += 2 ;   
        }
        Department dpt = new Department(sessions, dbM);
        
    }
    
    /**
     * Method to set a component position to the centre of the host system
     * @param w Width of the component
     * @param h height of the component
     * @return centre Point
     */
    static Point setPositionToCenter(int w, int h){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeigth = screenSize.getHeight();
        double width = w;
        double heigth = h;
        double x = (screenWidth - width) / 2 ;
        double y = (screenHeigth - heigth) / 2;
        Point p = new Point((int)x, (int)y);
        return p ;
    
    }  // Enf of setPositionToCenter()

}  // end of Driver Class declaration
