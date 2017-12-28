/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cgpa;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author AZYSS
 */
public class ViewSemesterResults extends JPanel {
    
    static final Dimension SEMESTER_RESULT_BOARD_DIMENSION = new Dimension(320, 340);
    
    /**
     * Constructor
     * @param sem semester to display results
     */
    ViewSemesterResults(Semester sem){
        super();
        int totCourses = sem.getSemesterRegisteredCoursesCount() ;
        System.out.println(totCourses);
        
        //table header
        String[] tableHeader = new String[]{"#", "Course","Unit", "Grade", "Score", "Missed ?"};
        String[][] rowData = new String[25][tableHeader.length];
        int tempVal = 0 ;
        //Result table stuff
        JTable resultTable = new JTable(rowData,tableHeader);
                     //fill table j0 = num, j1 = courseCode, j2 = unit, j3 = grade, j4 = score, j5 = course offered
        for (int i = 0; i < sem.getSemesterRegisteredCoursesCount(); i++){
            for(int j = 0; j < tableHeader.length; j++){
                if(j == 0){
                    resultTable.setValueAt(" " + Integer.toString( i + 1 ), i, j);
                }
                else if (j == 1){
                    resultTable.setValueAt(" " + sem.getSemesterCourses()[i].getCourseCode(), i, j);
                }
                else if (j == 2){
                    tempVal = (int)sem.getSemesterCourses()[i].getCourseUnit();
                    if(tempVal >= 0)
                        resultTable.setValueAt("  " + Integer.toString(tempVal), i, j);
                    else{
                        // do nothing
                        resultTable.setValueAt(" N/A", i, j);
                    }
                }
                else if(j == 3){
                    resultTable.setValueAt(" " + sem.getSemesterCourses()[i].getCourseGrade(), i, j);
                }
                else if(j == 4){
                    tempVal = (int)sem.getSemesterCourses()[i].getCourseScore();
                    if(tempVal >= 0){
                        resultTable.setValueAt("   " + tempVal, i, j);
                    }else{
                        resultTable.setValueAt(" N/A", i, j);
                    }
                }
                else if (j == 5){
                    // doNothing
                    if(sem.getSemesterCourses()[i].isCourseOffered())
                        resultTable.setValueAt("   NO", i, j);
                    else
                        resultTable.setValueAt("  YES", i, j);  
                }
            }
        }
        
        resultTable.setEnabled(false);
        resultTable.getColumn(tableHeader[0]).setMaxWidth(35);
        resultTable.getColumn(tableHeader[0]).setMinWidth(35);
        resultTable.getColumn(tableHeader[1]).setMaxWidth(60);
        resultTable.getColumn(tableHeader[1]).setMinWidth(60);
        resultTable.getColumn(tableHeader[2]).setMaxWidth(45);
        resultTable.getColumn(tableHeader[2]).setMinWidth(45);
        resultTable.getColumn(tableHeader[3]).setMaxWidth(45);
        resultTable.getColumn(tableHeader[3]).setMinWidth(45);
        resultTable.getColumn(tableHeader[4]).setMaxWidth(50);
        resultTable.getColumn(tableHeader[4]).setMinWidth(50);
        resultTable.getColumn(tableHeader[5]).setMaxWidth(60);
        resultTable.getColumn(tableHeader[5]).setMinWidth(60);
        
        //JScrollPane to hold result table
        JScrollPane tableScrollPane = new JScrollPane(resultTable);
        
        //Jpanel to hold table
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(tableScrollPane);
        
        //
        SemesterResultPanel codesPanel = new SemesterResultPanel();
        codesPanel.setTotalCourses(totCourses);
        codesPanel.setCO(sem.getSemesterCarryOversCount());
        codesPanel.setMC(sem.getSemesterMissedCoursesCount());
        codesPanel.setTCP(sem.getSemesterCreditPoint());
        codesPanel.setTNU(sem.getSemesterUnit());
        codesPanel.setGPA(sem.getSemesterGradePoint());
        codesPanel.setPreferredSize(new Dimension(200,148));
        
        //JPanel to hold Labels and texts
        JPanel labelsPanel = new JPanel();
        
        this.setLayout(new BorderLayout());
        this.add(tablePanel, BorderLayout.CENTER);
        this.add(codesPanel, BorderLayout.SOUTH);
    }
    
   /*
    public static void main(String[] args){
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch( ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e){
        }
        final Course[] course = new Course[]{new Course4("MAT 101", "", "a", 3,-1,true), 
            new Course4("PHY 101","", "a", 3,-1,true), 
            new Course4("PHY 151", "", "a", 3,-1,true), new Course("CHM 101","", "a", 3,-1,true),
            new Course("STA 101", "", "a", 3,-1,true), new Course("GNS 101", "", "a", 3,-1,true),
            new Course("GNS 103", ""), new Course("GNS 105", "", "a", 3,-1,true)};
        final Semester sem = new Semester();
        sem.addCoursesToSemester(course);
        
       
        AuxilliaryWindow window = new AuxilliaryWindow(new ViewSemesterResults(sem), "oops");
        window.setSize(ViewSemesterResults.SEMESTER_RESULT_BOARD_DIMENSION);
        window.setVisible(true);
        
        
        
        //JFrame window = new JFrame("test");
        //window.add(new ViewSemesterResults(sem));
        
    }
    */
}
