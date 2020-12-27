package gui;

import java.awt.event.*; 
import java.awt.*; 
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener; 
class List extends JFrame  
{ 
      
    //frame 
    static JFrame f; 
      
    //lists 
    static JList b; 
   
  
    //main class 
    public static void main(String[] args) 
    { 
        //create a new frame  
        f = new JFrame("frame"); 
          
        //create a object 
        List s=new List(); 
        
        //create a panel 
        JPanel p =new JPanel(); 
          
        //create a new label 
        JLabel l= new JLabel("select the day of the week"); 
  
        //String array to store weekdays 
        String week[]= { "Monday","Tuesday","Wednesday", 
                         "Thursday","Friday","Saturday","Sunday"}; 
          
        //create list 
        b= new JList(week); 
          
        //set a selected index 
        b.setSelectedIndex(2); 
          
        
       
        //add list to panel 
        p.add(b); 
   
        f.add(p); 
          
        //set the size of frame 
        f.setSize(400,400); 
           
		f.show();
		b.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println(b.getSelectedValue());
			}
		});
    } 
      
      
} 