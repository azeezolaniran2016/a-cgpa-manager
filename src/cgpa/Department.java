/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cgpa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

/**
 *
 * @author AZYSS
 an implementation of a Department
 */
public class Department extends JPanel{
    private Session[] sessions ;
    private JPanel advertBoardPanel ;
    private int currentPosition ;
    private JMenuBar jmBar ;
    static final String APP_TITLE = "myGPA 1.1.1";
    
    
    private DB_Manipulator dbM ;
    private JFrame deptFrame ;
    
    private JPanel firstSemesterPanel ;
    private JPanel secondSemesterPanel ;
    
    private JLabel sessionNameLabel ;
    
    /**
     * constructs a new department with the following sessions
     * @param ses session in this department
     */
    Department(Session[] ses,DB_Manipulator db){
        super();
        
        this.sessions = ses ;
        this.dbM = db ;
        
        this.initDepartment(db.getLastSessionViewed()-1);
        this.initMenuBar();
    }
    
    /**
     * method to initialise this component properly
     */
    private void initDepartment(int sPresent){
        this.currentPosition = sPresent  ;
        
        
        //Buttons for actions in this semester
        JButton registerFirstSemesterCoursesButton = new JButton(PNGGetter.getEditCoursesIcon());
        registerFirstSemesterCoursesButton.setText("Add/Remove Courses");
        registerFirstSemesterCoursesButton.setToolTipText("Register or remove courses in this semester");
        registerFirstSemesterCoursesButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("register first semester courses button pressed");
            }
        });
        
        JButton viewFirstSemesterResultsButton = new JButton(PNGGetter.getViewResultIcon());
        viewFirstSemesterResultsButton.setText("View Semester Result");
        //viewFirstSemesterResultsButton.setHorizontalTextPosition(SwingConstants.LEFT);
        viewFirstSemesterResultsButton.setToolTipText("View a break down of this semester results");
        viewFirstSemesterResultsButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                sessions[currentPosition].viewFirstSemesterResult();
            }
        });
        
        JButton resetAllCoursesInFirstSemesterButton = new JButton("Reset Semester Courses");
        resetAllCoursesInFirstSemesterButton.setIcon(PNGGetter.getResetCoursesIcon());
        resetAllCoursesInFirstSemesterButton.setToolTipText("Resests all courses in this semester to their default values");
        resetAllCoursesInFirstSemesterButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("reset all courses in first semester button pressed");
                Department.this.sessions[currentPosition].getFirstSemester().resetSemesterCoursesToDefault();
            }
        });
        
        JButton registerSecondSemesterCoursesButton = new JButton(PNGGetter.getEditCoursesIcon());
        registerSecondSemesterCoursesButton.setToolTipText("Register or remove courses in this semester");
        registerSecondSemesterCoursesButton.setText("Add/Remove Courses");
        registerSecondSemesterCoursesButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
               System.out.println("registerSecondSemesterCourses button was pressed");
            }
        });
        
        JButton viewSecondSemesterResultsButton = new JButton(PNGGetter.getViewResultIcon());
        viewSecondSemesterResultsButton.setText("View Semester Result");
        //viewSecondSemesterResultsButton.setHorizontalTextPosition(SwingConstants.LEFT);
        viewSecondSemesterResultsButton.setToolTipText("View a break down of this semester results");
        viewSecondSemesterResultsButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                sessions[currentPosition].viewSecondSemesterResult();
            }
        });
        
        JButton resetAllCoursesInSecondSemesterButton = new JButton("Reset Semester Courses");
        resetAllCoursesInSecondSemesterButton.setIcon(PNGGetter.getResetCoursesIcon());
        resetAllCoursesInSecondSemesterButton.setToolTipText("Resets all courses in this semester to their default values");
        resetAllCoursesInSecondSemesterButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("reset all courses in second semester button pressed");
                Department.this.sessions[currentPosition].getSecondSemester().resetSemesterCoursesToDefault();
            }
        });
        
        JButton viewSessionResultButton = new JButton("Session Result");
        //viewSessionResultButton.setHorizontalTextPosition(SwingConstants.LEFT);
        viewSessionResultButton.setIcon(PNGGetter.getSessionResultIcon());
        viewSessionResultButton.setToolTipText("View this session result break down");
        viewSessionResultButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                SessionResultsPanel rPanel = Department.createSessionResultTable(Department.this.sessions[currentPosition], Integer.toString(currentPosition + 1));
                
                final AuxilliaryWindow window = new AuxilliaryWindow(rPanel);
                
                if(currentPosition == (sessions.length - 1))
                    window.setTitle("Year " + (currentPosition + 1) + " (Spill-Over) Result Viewer");
                else if(currentPosition == (sessions.length - 2))
                    window.setTitle("Year " + (currentPosition + 1) + " (Finals) Result Viewer");
                else
                    window.setTitle("Year " + (currentPosition + 1) + " Result Viewer");
                window.pack();
                window.setLocationRelativeTo(advertBoardPanel);
                window.setVisible(true);
                
            }
        });
        
        //panel to hold first semester buttons
        JPanel firstSemesterButtonsPanel = new JPanel();
        firstSemesterButtonsPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        firstSemesterButtonsPanel.setLayout(new BorderLayout());
        JLabel fLabel = new JLabel("First Semester");
        fLabel.setForeground(Color.BLUE);
        fLabel.setHorizontalAlignment(JLabel.CENTER);
        firstSemesterButtonsPanel.add(fLabel, BorderLayout.NORTH);
        JPanel bPanel = new JPanel();
        bPanel.setLayout(new GridLayout(2,1,5,0));
        bPanel.add(viewFirstSemesterResultsButton);
        //bPanel.add(registerFirstSemesterCoursesButton);
        bPanel.add(resetAllCoursesInFirstSemesterButton);
        firstSemesterButtonsPanel.add(bPanel, BorderLayout.CENTER);
        
        //panel to hold second semester buttons
        JPanel secondSemesterButtonsPanel = new JPanel();
        secondSemesterButtonsPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        secondSemesterButtonsPanel.setLayout(new BorderLayout());
        JLabel sLabel = new JLabel("Second Semester");
        sLabel.setForeground(Color.BLUE);
        sLabel.setHorizontalAlignment(JLabel.CENTER);
        secondSemesterButtonsPanel.add(sLabel, BorderLayout.NORTH);
        JPanel cPanel = new JPanel();
        cPanel.setLayout(new GridLayout(2,1,5,0));
        cPanel.add(viewSecondSemesterResultsButton);
        //cPanel.add(registerSecondSemesterCoursesButton);
        cPanel.add(resetAllCoursesInSecondSemesterButton);
        secondSemesterButtonsPanel.add(cPanel, BorderLayout.CENTER);
        
        //button to navigate to the next session
        JButton nextSession = new JButton("Session");
        nextSession.setIcon(PNGGetter.getNextIcon());
        nextSession.setHorizontalTextPosition(SwingConstants.LEFT);
        nextSession.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                Department.this.navigateSesssionsView(1);
            }
        });
        
        //button to navigate to the previous session
        JButton previousSession = new JButton("Session");
        previousSession.setIcon(PNGGetter.getPrevIcon());
        previousSession.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                Department.this.navigateSesssionsView(-1);
            }
        });
        
        //Button to view the cgpa
        JButton viewCGPAButton = new JButton("") ;
        viewCGPAButton.setIcon(PNGGetter.getCGPAIcon());
        viewCGPAButton.setToolTipText("view all sessions result break down and overall cgpa");
        viewCGPAButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
            Thread t = new Thread(){  // Create a new thread to show this result board 
                @Override
                public void run(){
                    final AuxilliaryWindow window = new AuxilliaryWindow(new CGPAResultPanel(sessions, Department.this.dbM.getGradeSystem()));
                    window.pack();
                    window.setLocationRelativeTo(Department.this.advertBoardPanel);
                    window.setTitle("CGPA Viewer");
                    window.setVisible(true);
            
                    }
                };
            t.start();
            };
        });
        
        //panel to hold buttons
        JPanel sessionButtonPanel = new JPanel();
        sessionButtonPanel.setLayout(new BorderLayout());
        sessionButtonPanel.add(nextSession, BorderLayout.EAST);
        sessionButtonPanel.add(previousSession, BorderLayout.WEST);
        sessionButtonPanel.add(viewSessionResultButton, BorderLayout.CENTER);
        sessionButtonPanel.add(viewCGPAButton, BorderLayout.SOUTH);
        sessionButtonPanel.setOpaque(false);
        sessionButtonPanel.setBorder(BorderFactory.createLineBorder(new Color(255,0,255)));
        
        //advertPanel stuff
        JLabel advertLabel = new JLabel("place advert here");
        //this.advertLabel.setFont(new Font(Font.DIALOG, Font.ITALIC), 30);
        
        
        //panel to hold adverts
        this.advertBoardPanel = new JPanel();
        this.advertBoardPanel.setLayout(new BorderLayout());
        advertBoardPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), "Advert Board", TitledBorder.CENTER, TitledBorder.CENTER, null, Color.LIGHT_GRAY));
        advertBoardPanel.add(new LRAnimationPanel());
        
        //panel to hold buttons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BorderLayout());
        buttonsPanel.add(firstSemesterButtonsPanel, BorderLayout.WEST);
        buttonsPanel.add(secondSemesterButtonsPanel, BorderLayout.EAST);
        buttonsPanel.add(sessionButtonPanel, BorderLayout.SOUTH);
        
        
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createLineBorder(Color.darkGray));
        centerPanel.add(this.advertBoardPanel, BorderLayout.CENTER);
        centerPanel.add(buttonsPanel, BorderLayout.SOUTH);
        
        //semester panels
        firstSemesterPanel = new JPanel() ;
        firstSemesterPanel.setLayout(new BorderLayout());
        firstSemesterPanel.add(this.sessions[currentPosition].getFirstSemesterPanel());
        
        secondSemesterPanel = new JPanel() ;
        secondSemesterPanel.setLayout(new BorderLayout());
        secondSemesterPanel.add(this.sessions[currentPosition].getSecondSemesterPanel());
        
        //panel to hold the session name label
        this.sessionNameLabel = new JLabel(" Year " + (this.currentPosition + 1));
        if(this.currentPosition == (this.sessions.length - 1))  // we are in the spill over session
                    this.sessionNameLabel.setText(" Year " + (this.currentPosition + 1) + " (Spill-Over Session)");
        
        this.sessionNameLabel.setHorizontalAlignment(JLabel.CENTER);
        this.sessionNameLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
        
        //Panel to hold all components except the jmBar
        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(new BorderLayout());
        bodyPanel.add(this.sessionNameLabel, BorderLayout.NORTH);
        bodyPanel.add(firstSemesterPanel, BorderLayout.WEST);
        bodyPanel.add(secondSemesterPanel, BorderLayout.EAST);
        bodyPanel.add(centerPanel, BorderLayout.CENTER);
        
        //do all necessary MenuBar Initialization before adding it to the frame
        this.initMenuBar();
        
        
        
        this.add(bodyPanel, BorderLayout.CENTER);
        deptFrame = new JFrame(Department.APP_TITLE);
        deptFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        deptFrame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                int response = JOptionPane.showConfirmDialog(advertBoardPanel, "Save Changes Made ?", "Exit",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION){
                    Department.this.saveSemestersCourses();
                    Department.this.basicCloseOperation();
                }else if (response == JOptionPane.NO_OPTION){
                    Department.this.basicCloseOperation();
                }else{
                    //doNothing
                }
            }
        });
        deptFrame.add(this);
        deptFrame.setIconImage(PNGGetter.getFrameIcon());
        deptFrame.setJMenuBar(this.jmBar);
        deptFrame.pack();
        deptFrame.setResizable(false);
        deptFrame.setLocation(DriverClass.setPositionToCenter(deptFrame.getWidth(), deptFrame.getHeight()));
        deptFrame.setVisible(true);
        //this.add(this.jmBar, BorderLayout.SOUTH);
    }
    
    private void basicCloseOperation(){
        this.dbM.setLastSessionViewed(++Department.this.currentPosition); // save current session viewed
        this.dbM.updateUseCount();  //Update count
        //this.advertPanel.endAdvertPanelThread(); // end the infinite loop in the advert panel
        this.deptFrame.dispose();
    }
    
    private void loadSessions(){
        Thread t = new Thread(){
            @Override
            public void run(){
                int totalSession = dbM.getTotalSessions();
                final Semester[] sms = new Semester[dbM.getTotalSemesters()];
                
                for(int i = 1; i <= dbM.getTotalSemesters(); i++){
                    sms[i-1] = new Semester();
                    sms[i-1].addCoursesToSemester(dbM.getSemesterCourseArray(i));
                }//End of for loop
                
                Department.this.sessions = new Session[totalSession];
                int tCount = 0 ;
                
                for(int i = 0; i < Department.this.sessions.length; i++){
                    Department.this.sessions[i] = new Session(sms[tCount], sms[tCount+1]);
                    tCount += 2 ; 
            
            //this.sessions[i].getFirstSemester().repaint();
            //this.sessions[i].getFirstSemester().revalidate();
                }//End of For loop
                
                Department.this.firstSemesterPanel.removeAll();
                Department.this.secondSemesterPanel.removeAll();
                Department.this.firstSemesterPanel.add(Department.this.sessions[currentPosition].getFirstSemesterPanel());
                Department.this.secondSemesterPanel.add(Department.this.sessions[currentPosition].getSecondSemesterPanel());
                Department.this.firstSemesterPanel.repaint();
                Department.this.firstSemesterPanel.revalidate();
                Department.this.secondSemesterPanel.repaint();
                Department.this.secondSemesterPanel.revalidate();
                //System.out.println("end of run method in loadSessions() of Department class");
        //this.repaint();
        //this.revalidate();
            };  // end of run method
        };
        t.setDaemon(true);
        t.start();
        while(true){
            if(!t.isAlive()){
                JOptionPane.showMessageDialog(this, "Load Records Successful", "Load Records", JOptionPane.INFORMATION_MESSAGE);
                break; // break out of the while loop
            }
        }
    }
    
    JPanel getAdvertPanel(){
        return this.advertBoardPanel ;
    }
    
    private void saveSemestersCourses(){
        Thread t = new Thread(){  // Create new thread to save the semesters
            @Override
            public void run(){
        //}
                int i = 0;
                int j = 0 ;
                //try{
                while( i < Department.this.sessions.length){
                    dbM.saveSemesterCourses(sessions[i].getFirstSemester().getSemesterCourses(), j);
                    //System.out.println("Semester " + j + " Courses Saved");
                    ++j ;
                    dbM.saveSemesterCourses(sessions[i].getSecondSemester().getSemesterCourses(), j);
                    //System.out.println("Semester " + j + " Courses Saved");
                    ++j ;
                    ++i ;
                }
                //System.out.println("end of run method in saveSemesterCourses() of Department class");
        
               // }catch(Exception e){
               //     System.out.println("Exception thrown in saveSemestersCourses Department class method \n" +
               //     e.getMessage());
               // }
            };
        };
        t.setDaemon(true); // make t a daemon thread
        t.start();
        while(true){
            if(!t.isAlive()){
                System.out.println("tread is alive " + t.isAlive());
                JOptionPane.showMessageDialog(this, "Save Records Successful", "Save Records", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
        }
            
    }
    
    private void initMenuBar(){
        //About menuOptions
        final JMenuItem aboutMenuItem = new JMenuItem("About App");
        aboutMenuItem.setIcon(PNGGetter.getAboutIcon());
        aboutMenuItem.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                //Info Label
                JLabel infoLabel = new JLabel(PNGGetter.getAboutAppIcon());
                AuxilliaryWindow infoWindow = new AuxilliaryWindow(infoLabel);
                infoWindow.pack();
                infoWindow.setLocationRelativeTo(Department.this.advertBoardPanel);
                infoWindow.setVisible(true);
            }
        });
        JMenuItem viewUserInfoMenuItem = new JMenuItem("View/Edit User Info");
        viewUserInfoMenuItem.setIcon(PNGGetter.getViewIcon());
        viewUserInfoMenuItem.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                //View Editable User Data Form
                UserDataForm form = new UserDataForm(UserDataForm.CHANGE_DATA_FORM, Department.this.dbM);
                form.setLocationRelativeTo(Department.this.advertBoardPanel);
                form.setVisible(true);
            }
        });
        
        //About menu
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setIcon(PNGGetter.getHelpIcon());
        helpMenu.add(aboutMenuItem);
        helpMenu.addSeparator();
        helpMenu.add(viewUserInfoMenuItem);
        
        //Exit menu option
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setIcon(PNGGetter.getExitIcon());
        exitMenuItem.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(advertBoardPanel, "Save Changes Made And Exit ", "",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION){
                    Department.this.saveSemestersCourses();
                    Department.this.basicCloseOperation();
                }else if (response == JOptionPane.NO_OPTION){
                    Department.this.basicCloseOperation();
                }else{
                    //doNothing
                }
            }
        });
        
        //Log out menu Option
        JMenuItem logoutMenuItem = new JMenuItem("Log Out");
        logoutMenuItem.setIcon(PNGGetter.getLogoutIcon());
        logoutMenuItem.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                Department.this.deptFrame.setVisible(false);
                Department.this.saveSemestersCourses();
                int i = LoginPane.createLoginPane(dbM.getUserName(), dbM.getUserPassword());
                if(i == LoginPane.PASSWORDS_MATCH)
                    deptFrame.setVisible(true);
            }
        });
        
        //Load Last records menu option
        JMenuItem loadLastRecordsMenuItem = new JMenuItem("Load Last Records");
        loadLastRecordsMenuItem.setIcon(PNGGetter.getLoadIcon());
        loadLastRecordsMenuItem.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                Department.this.loadSessions();
            }
        });
        
        //Save present records menu option
        JMenuItem savePresentRecordsMenuItem = new JMenuItem("Save Present Records");
        savePresentRecordsMenuItem.setIcon(PNGGetter.getSaveIcon());
        savePresentRecordsMenuItem.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                Department.this.saveSemestersCourses();}
        });
        
        
        //print result MenuOption
        JMenuItem printResultMenuItem = new JMenuItem("print result");
        printResultMenuItem.setIcon(PNGGetter.getPrintIcon());
        printResultMenuItem.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(jmBar, "Sorry, Printing of results\n"
                        + " is not yet enabled. ", "Print Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        //plot resultGraph
        JMenuItem resultPlotterMenuItem = new JMenuItem("Plot Progress Graph");
        resultPlotterMenuItem.setIcon(PNGGetter.getPlotIcon());
        resultPlotterMenuItem.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(aboutMenuItem, "Progress Graph Plotter \n"
                        + "is not yet availaible. \n"
                        + "Sorry.", "Graph Plotter Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        /*changeUserDataMenuItem.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                //View Editable User Data Form
                UserDataForm form = new UserDataForm(UserDataForm.CHANGE_DATA_FORM, Department.this.dbM);
                form.setLocationRelativeTo(Department.this.advertBoardPanel);
                form.setVisible(true);
            }
        });
                */
        
        //Options Menu
        JMenu optionsMenu = new JMenu("Options");
        optionsMenu.setIcon(PNGGetter.getOptionsIcon());
        //optionsMenu.add(changeUserPasswordItem);
        //optionsMenu.add(changeUserDataMenuItem);
        //optionsMenu.add(printResultMenuItem);  // Not yet available
        //optionsMenu.add(resultPlotterMenuItem); // Not yet available
        optionsMenu.add(savePresentRecordsMenuItem);
        optionsMenu.add(loadLastRecordsMenuItem);
        optionsMenu.add(logoutMenuItem);
        optionsMenu.addSeparator();
        optionsMenu.add(exitMenuItem);
        
        //JMenu Bar Stuff
        jmBar = new JMenuBar();
        jmBar.add(helpMenu);
        jmBar.add(optionsMenu);
    }
    
    
    JMenuBar getDepartmentJMenuBar(){
        return this.jmBar ;
    }
    
    private void navigateSesssionsView(int move){
            currentPosition += (move) ;
            if((this.currentPosition >= this.sessions.length) || (this.currentPosition < 0) ){
                currentPosition -= (move) ;
                //JOptionPane.showMessageDialog(advertBoardPanel, "No more sessions to display", "OOPS!!!", JOptionPane.ERROR_MESSAGE);
            }else{
                this.firstSemesterPanel.removeAll();
                this.secondSemesterPanel.removeAll();
                this.firstSemesterPanel.add(this.sessions[currentPosition].getFirstSemesterPanel());
                this.secondSemesterPanel.add(this.sessions[currentPosition].getSecondSemesterPanel());
                this.firstSemesterPanel.repaint();
                this.firstSemesterPanel.revalidate();
                this.secondSemesterPanel.repaint();
                this.secondSemesterPanel.revalidate();
                this.sessionNameLabel.setText(" Year " + (this.currentPosition + 1));
                if(this.currentPosition == (this.sessions.length - 1))  // we are in the spill over session
                    this.sessionNameLabel.setText(" Year " + (this.currentPosition + 1) + " (Spill-Over Session)");
            }
    }
    
    static SessionResultsPanel createSessionResultTable(Session sess, String yr){
        final SessionResultsPanel rPanel = new SessionResultsPanel();
        rPanel.setTitle("Year " + yr + " Result Board");
        rPanel.setSemesterOneCO(sess.getFirstSemester().getSemesterCarryOversCount());
        rPanel.setSemesterTwoCO(sess.getSecondSemester().getSemesterCarryOversCount());
        rPanel.setSemesterOneGPA(sess.getFirstSemester().getSemesterGradePoint());
        rPanel.setSemesterTwoGPA(sess.getSecondSemester().getSemesterGradePoint());
        rPanel.setSemesterOneMC(sess.getFirstSemester().getSemesterMissedCoursesCount());
        rPanel.setSemesterTwoMC(sess.getSecondSemester().getSemesterMissedCoursesCount());
        rPanel.setSemesterOneTCP(sess.getFirstSemester().getSemesterCreditPoint());
        rPanel.setSemesterTwoTCP(sess.getSecondSemester().getSemesterCreditPoint());
        rPanel.setSemesterOneTNU(sess.getFirstSemester().getSemesterUnit());
        rPanel.setSemesterTwoTNU(sess.getSecondSemester().getSemesterUnit());
        rPanel.setSemesterOneTNC(sess.getFirstSemester().getSemesterRegisteredCoursesCount());
        rPanel.setSemesterTwoTNC(sess.getSecondSemester().getSemesterRegisteredCoursesCount());
        rPanel.setSessionGPA(sess.getSessionGradePoint());
        
        return rPanel ;
    }
}
