/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cgpa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * @author Olaniran Azeez Olawale (AZYSS) 
 This class implements a Semester.
 It consists of a number of courses for the semester.
 */
public class Semester extends JPanel{
    final static int MAXIMUM_COURSES = 25 ;  // Maximum courses a semester panel can hold
    private double semesterCreditPoint ; // total credit point from all courses in this semester
    private double semesterUnit ;  // total unit for all courses in this semester
    private double semesterGradePoint; // grade point in this semester
    private int semeterCarryOversCount;  // number of carryovers in this semester
    private int semesterMissedCoursesCount; // number of courses missed in this semester
    private int semesterRegisteredCoursesCount; // number of courses registered in this semester
    private int SemesterOfferedCoursesCount ; // number of courses offered this semester
    
    private String semesterName ;
    //private JButton addCoursesButton ;
    JPanel topPanel ;
    private JPanel coursesPanel; // panel to hold the courses in this semester
    //private JLabel semesterNameLabel;
    private Course[] semesterCourses ;  // an array to hold all the courses in this semeter
    
    
    /**
     * Constructor for this class
     * @param name of this semester
     */
    Semester (String nm){
        super();  // initialize the panel properly
        this.semesterName = nm;
        this.init();
    }
    
    /**
     * constructor for the semester class (takes no argument)
     */
    Semester (){
        super();  // initialize the panel properly
        this.init();
    }
    
    /**
     * constructor 2
     */
    
    /**
     * method to do the basic initialization for the components of this class
     */
    private void init(){
        //semester label stuff
        //this.semesterNameLabel = new JLabel(this.semesterName);
        //this.semesterNameLabel.setHorizontalAlignment(JLabel.CENTER);
        //this.semesterNameLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
        //this.semesterNameLabel.setForeground(Color.BLUE);
        //this.semesterNameLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        
        JLabel courseCodeLabel = new JLabel("Course code");
        courseCodeLabel.setHorizontalAlignment(JLabel.CENTER);
        courseCodeLabel.setForeground(Color.BLUE);
        
        JLabel courseGradeLabel = new JLabel("Grade");
        courseGradeLabel.setHorizontalAlignment(JLabel.CENTER);
        courseGradeLabel.setForeground(Color.BLUE);
        
        JLabel courseUnitLabel = new JLabel("Unit");
        courseUnitLabel.setHorizontalAlignment(JLabel.CENTER);
        courseUnitLabel.setForeground(Color.BLUE);
        
        JLabel courseScoreLabel = new JLabel("Score (%)");
        courseScoreLabel.setHorizontalAlignment(JLabel.CENTER);
        courseScoreLabel.setForeground(Color.BLUE);
        
        JLabel courseOfferedLabel = new JLabel(" R ?") ;
        courseOfferedLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        courseOfferedLabel.setForeground(Color.BLUE);
        courseOfferedLabel.setHorizontalAlignment(JLabel.CENTER);
        
        JLabel deleteCourseLabel = new JLabel(PNGGetter.getDeleteIcon()) ;
        deleteCourseLabel.setText("?");
        deleteCourseLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        deleteCourseLabel.setForeground(Color.BLUE);
        deleteCourseLabel.setHorizontalAlignment(JLabel.CENTER);
        //deleteCourseLabel.setBorder(BorderFactory.createLineBorder(Color.black,3));
        
        //JPanel topTopPanel = new JPanel();
        //topTopPanel.setLayout(new BorderLayout());
        //topTopPanel.add(this.semesterNameLabel);
        
        //top panel stuff
        topPanel = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
        //super.paint(g);
        //g.setColor(Color.blue);
                for (int i = 1; i <= 3; i++){
                    g.drawLine((this.getWidth()/4)*i, 0, (this.getWidth()/4)*i, this.getHeight());
                }
            }
        };
        topPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        topPanel.setOpaque(false);
        topPanel.setLayout(new GridLayout(1,4,0,0));
        topPanel.add(courseCodeLabel);
        topPanel.add(courseGradeLabel);
        topPanel.add(courseUnitLabel);
        topPanel.add(courseScoreLabel);
        //topPanel.setPreferredSize(new Dimension(320,20));
        
        JPanel northPanel = new JPanel();
        northPanel.setOpaque(false);
        northPanel.setLayout(new BorderLayout());
        northPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        northPanel.add(topPanel, BorderLayout.CENTER);
        JPanel aux = new JPanel();
        aux.setLayout(new GridLayout(1,2));
        aux.add(courseOfferedLabel);
        aux.add(deleteCourseLabel);
        //aux.add(new JLabel(""));
        northPanel.add(aux, BorderLayout.EAST);
        //northPanel.setPreferredSize(new Dimension(300, 20));
        
        //northPanelScorllPane
        JScrollPane northScrollPane = new JScrollPane(northPanel);
        northScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        northScrollPane.setFocusable(false);
        northScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        //courses panel stuff
        this.coursesPanel = new JPanel();
        this.coursesPanel.setLayout(new GridLayout(Semester.MAXIMUM_COURSES,1,0,0));
        //this.coursesPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //this.coursesPanel.setPreferredSize(new Dimension(302,420));
        JScrollPane scrollPane = new JScrollPane(this.coursesPanel);
        //scrollPane.setPreferredSize(new Dimension(302,400));
        coursesPanel.setAutoscrolls(true);
        
        //main panel stuff
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setOpaque(false);
        mainPanel.add(northScrollPane, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.setPreferredSize(new Dimension(340,345));
        
        //this panel stuff
        this.add(mainPanel, BorderLayout.CENTER);
    }
    
    /**
     * method to set the courses held in this semester
     */
    void setSemesterCourses(Course[] courses){
        this.semesterCourses = courses ;
    }
    
    /**
     * method to get the courses present in this semester
     * @return Array containing all the courses in this semester
     */
    Course[] getSemesterCourses(){
        return this.semesterCourses ;
    }
    
    /**
     * Method to add courses to this semester
     * @param courses to be added to this semester
     */
    void addCoursesToSemester(Course[] courses){
        //remove previous courses first
        this.coursesPanel.removeAll();
        int i = 1 ;
        //ensure the courses arent null
        if(courses != null){
            this.semesterCourses = courses ;
            for (final Course semesterCourse : this.semesterCourses) {
                this.coursesPanel.add(semesterCourse);
                i++ ;
            }
        }else{
            //do nothing
        }
        for(; i <= Semester.MAXIMUM_COURSES; i++){
            //after add the real courses add new AddCourses panel to fill up remaining space
            final AddCourse addCourse = new AddCourse(i);
            addCourse.getAddCourseButton().addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    //create a new course
                    Course course = CourseCreator.createCourse(DriverClass.dbM.getGradeSystem(),
                            (Semester.this.semesterCourses.length + 1), Semester.this.topPanel); // null pointer exception expected here 
                    //if the user cancel the create course dialog box
                    if (course == null){
                        //do nothing
                    }
                    else{
                        addCourseToSemester(course);
                    }
                }
            });
            this.coursesPanel.add(addCourse);
        }
        
        // at this point all courses must have been added to the semester
        for(final Course c : Semester.this.semesterCourses){
           /* c.getDelecteCourseButton().addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    //int delete = JOptionPane.showConfirmDialog(topPanel, "Delete " + c.getCourseCode(), "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    //if (delete == JOptionPane.YES_OPTION)  Code causes the delete to malfunction :(
                    Semester.this.deleteCourseFromSemester(c);
                }
            });
            */
            
            c.getDelecteCourseButton().addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    if(e.getClickCount() == 2 && !c.isCourseOffered()){
                       //int delete = JOptionPane.showConfirmDialog(topPanel, "Delete " + c.getCourseCode(), "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    //if (delete == JOptionPane.YES_OPTION)  Code causes the delete to malfunction :(
                    Semester.this.deleteCourseFromSemester(c); 
                    }
                }
                    
            });
        }
        
    }
    
    /**
     * Method to set this semester credit point
     */
    void setSemesterCreditPoint(){
        double tempCP = 0.0;
        for(Course semesterCourse : this.semesterCourses ){
            if(semesterCourse.isCourseOffered()){
                semesterCourse.getCourseCreditPoint();
                tempCP += semesterCourse.getCourseCreditPoint();
            }
        }
        this.semesterCreditPoint = tempCP;
    }
    
    /**
     * Method to get this semester credit point
     * @return semesterCreditPoint
     */
    double getSemesterCreditPoint(){
        this.setSemesterCreditPoint();
        return this.semesterCreditPoint;
    }
    
    /**
     * method to set the total unit in this semester
     */
    void setSemesterUnit(){
        this.semesterUnit = 0 ;
        double tempUnit = 0;
        for(Course semesterCourse : this.semesterCourses){
            if(semesterCourse.isCourseOffered()){
                semesterCourse.getCourseUnit();
                tempUnit += semesterCourse.getCourseUnit();
            }
        }
        this.semesterUnit = tempUnit ;
    }
    
    /**
     * method to get the total units in this semester
     * @return total units in this semester 
     * this method should be called after it's set method
     */
    double getSemesterUnit(){
        this.setSemesterUnit();
        return this.semesterUnit;
    }
    
    /**
     * Method to set this semesters GP
     */
    void setSemesterGradePoint(){
        //avoid divide by zero error
        if(this.getSemesterUnit() != 0)
            this.semesterGradePoint = this.getSemesterCreditPoint() / this.getSemesterUnit();
        else
            this.semesterGradePoint = 0 ;
    }
    
    /**
     * method to get this semesters grade point
     * @return GP for this semester
     * this method should be called after calling its set method
     */
    double getSemesterGradePoint(){
        this.setSemesterGradePoint();
        return this.semesterGradePoint ;
    }
    
    /**
     * Method to set the total number of courses missed in this semester
     */
    void setSemesterMissedCoursesCount(){
        int count = 0;
        for(Course semesterCourse : this.semesterCourses){
            if (!semesterCourse.isCourseOffered())
                ++count;
        }
        semesterMissedCoursesCount = count ;
    }
    
    /**
     * Method to get the number of courses missed in this semester
     * @return total number of courses missed in this semester
     */
    int getSemesterMissedCoursesCount(){
        this.setSemesterMissedCoursesCount();
        return this.semesterMissedCoursesCount;
    }
    
    /**
     * Method to set the number of courses offered in this semester
     */
    void setSemesterOfferedCoursesCount(){
        int count = 0 ;
        for(Course semesterCourse : this.semesterCourses){
            if(semesterCourse.isCourseOffered())
                ++count ;
            
            this.SemesterOfferedCoursesCount = count ;   
        }
    }
    
    /**
     * Method to get the total number of courses offered in this semester
     */
    int getSemesterOfferedCoursesCount(){
        this.setSemesterOfferedCoursesCount();
        return this.SemesterOfferedCoursesCount ;
    }
    
    /**
     * Method to set the total number of courses registered for this semester
     */
    void setSemesterRegisteredCoursesCount(){
        this.semesterRegisteredCoursesCount = this.semesterCourses.length ;
    }
    
    /**
     * Method to get the total number of courses registered for this semester
     */
    int getSemesterRegisteredCoursesCount(){
        this.setSemesterRegisteredCoursesCount();
        return this.semesterRegisteredCoursesCount ;
    }
    
    /**
     * Method to set the semester carry overs count
     */
    void setSemesterCarryOversCount(){
        int count = 0;
        for(Course semesterCourse : semesterCourses){
            if (semesterCourse.isCourseCarried() && semesterCourse.isCourseOffered())
                ++count ;
        }
        this.semeterCarryOversCount = count ;
    }
    
    /**
     * Method to get the carryover count in this semester
     * @return number of carry overs
     */
    int getSemesterCarryOversCount(){
        this.setSemesterCarryOversCount();
        return this.semeterCarryOversCount ;
    }
    
    /**
     * Method to reset all courses in this semester to their default state
     */
    void resetSemesterCoursesToDefault(){
        for(Course sesCourse : semesterCourses){
            sesCourse.resetCourse();
        }
    }
    
    /**
     * Class to implement an instance of a course adder
     */
    class AddCourse extends JPanel{
        private JButton addCourseButton ;
        
        /**
         * Constructor for this class
         * @param courseNum Number of the course
         * @param addCourseButton button to add a new course
         */
        AddCourse(int courseNum){
            super();
            JLabel courseLabel = new JLabel("   Course #" + courseNum + " : No Record Found");
            addCourseButton = new JButton("Create Course");
            addCourseButton.setForeground(Color.black);
            addCourseButton.setToolTipText("Click to create a new course to this semester");
            this.setLayout(new BorderLayout());
            this.add(courseLabel, BorderLayout.CENTER);
            this.add(addCourseButton, BorderLayout.EAST);
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            this.setVisible(true);  
        }
        
        JButton getAddCourseButton(){
            return addCourseButton ;
        }
    }
    
    /**
     * Method to add a course to this semester
     * @param courseToAdd Course to be added
     */
    private void addCourseToSemester(Course courseToAdd){
        int num = this.semesterCourses.length;
        //create a new course array that's 1 greater than the old course array
        Course[] coursesTotal = new Course[num + 1];
        int i = 0 ;
        //add old courses to the new course array
        for (; i < num; ++i){
            coursesTotal[i] = (this.semesterCourses[i]);
        }
        //add the new course to the new course
        coursesTotal[i] = courseToAdd;
        //add the new courses to the semester
        this.addCoursesToSemester(coursesTotal);
        this.coursesPanel.repaint();
        this.coursesPanel.revalidate();
    }
    
    /**
     * Method to delete a course from the existing array of courses in this semester
     * @param courseToDelete course to be deleted
     */
    private void deleteCourseFromSemester(Course courseToDelete){
        int num = this.semesterCourses.length;
        //new arraylist to hold the old courses array
        ArrayList<Course> courses = new ArrayList(num);
        for(int i = 0 ; i < num; i++){
            courses.add(this.semesterCourses[i]);
        }
        //remove course to delete from newly created array list
        courses.remove(courseToDelete);
        //set the semester courses array to point to the updated arraylist and update it to the array list courses
        int j = courses.size();
        this.semesterCourses = new Course[j];
        for (int i = 0; i < j; ++i){
            this.semesterCourses[i] = courses.get(i);
        }
        //add the updated courses to this semester
        this.addCoursesToSemester(this.semesterCourses);
        this.coursesPanel.repaint();
        this.coursesPanel.revalidate();
    }
}
