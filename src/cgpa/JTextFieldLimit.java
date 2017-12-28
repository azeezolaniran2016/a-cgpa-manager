/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cgpa;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author AZYSS
 * This class shall be used to set a limit to 
 * the number of characters that can be entered
 * into a JTextField
 */
public class JTextFieldLimit extends PlainDocument{
    
    private int limit ;  // number of character limit
    
    /**
     * Constructor for an object of this class
     * @param l 
     */
    JTextFieldLimit(int l){
        super() ;
        this.limit = l ;
    }
    
    JTextFieldLimit(int l, boolean b ){
        super() ;
        this.limit = l ;
    }
    
    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException{
        if(str == null)
            return ;
        if((this.getLength() + str.length()) <= this.limit){
            super.insertString(offset, str, attr);
        }else{
            
        }
    }
    
}
