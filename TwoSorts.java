//***********************************************************************
// TwoSorts.java                                        
// Angie Cruz Martinez                                                           
// Christopher Bryan Rosana                                
// Junnel Reboquio                             
// CS-111B    Section 002 11:10 - 1:00PM                   
//***********************************************************************************
//Process Algorithm:
//1. Instantiate variables needed for the Applet
//2. Create a Listener for the button in init()
//3. Create an array with unique random numbers in init()
//4  Duplicate the values in one array to the other
//5. Initialize the indices
//6. Display the unsorted arrays in paint()
//7. Sort the values in the array by looping every time the button is press
//8. Call repaint() at the end of the button event to display the change in paint()
//8. Set the color to gray once both arrays are sorted(conditions are met)
//***********************************************************************************

import java.applet.Applet;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class TwoSorts extends Applet
{
	private final int APPLET_WIDTH = 600;
   private final int APPLET_HEIGHT = 600;
	int count = 0;
	Button butn1 = new Button("Sort Me!");
	int[] selection = new int[10];
	int[] insertion = new int[10];
	int min, temp1, temp2, scan;
	int selectionIndex, insertionIndex;
	
   public void init()
   {
      
		butn1.addActionListener(new Butn1Handler());
		add(butn1);
	  
		//--------------------------------------------------------------
		//This block of code fills the array with unique random numbers
		//--------------------------------------------------------------
		boolean[] beenUsed = new boolean[100];
		for (int i = 0; i < 100; i++)
		   beenUsed[i] = false;
		
		for (int j = 0; j < selection.length; j++) 
		{

			//find a number that's not been used yet
			int ran;
			do {
				ran = (int) (Math.random() * 100);
				}while (beenUsed[ran]);
		
		//put that number in the array, and mark it used in the beenUsed array
			selection[j] = ran;
			beenUsed[ran] = true;
		}
		
	//copy values of Array1 to Array2
	  for (int i = 0; i < selection.length; i++)
	  {
		insertion[i] = selection[i];
	  }	
	
      setBackground (Color.black);
      setSize (APPLET_WIDTH, APPLET_HEIGHT);
	   selectionIndex = 0;
	   insertionIndex = 1;
   }
   
   public void paint (Graphics page)
   {
      int xSelection = 60; //x coordinates of selection
      int xInsertion = 300; //x coordinates of insertion

		page.setColor (Color.green);
		page.drawString("Blue: Selection Sort", 60, 160);
		page.drawString("Yellow: Insertion Sort", 300, 160);
      
		if (selectionIndex < selection.length - 1 && insertionIndex < insertion.length)
		{         
         page.setColor(Color.white);
         page.drawString("Pass: " + (count), 260, 70);
            
         page.setColor(Color.blue); 
         for(int index = 0; index < selection.length; index++)
         {
            int y = 200; //y coordinates of selection
            int w = 10; //width
            int hSelection = selection[index]*3; //height

            page.fillRect(xSelection, y, w, hSelection);
            xSelection += 20;
         }
			                             
			page.setColor(Color.yellow); 
         for(int index = 0; index < insertion.length; index++)
         {
            int y = 200; //y coordinates of insertion
            int w = 10; //width
            int hInsertion = insertion[index]*3; //height

            page.fillRect(xInsertion, y, w, hInsertion);
            xInsertion += 20;
         }			
		}
		else
		{
			page.setColor (Color.gray);
			for(int index = 0; index < selection.length; index++)
            {
               int y = 200; //y coordinates of selection
               int w = 10;
               int hSelection = selection[index]*3;

               page.fillRect(xSelection, y, w, hSelection);
               xSelection += 20;
            }			
			page.setColor (Color.gray);
			for(int index = 0; index < insertion.length; index++)
            {
               int y = 200; //y coordinates of insertion
               int w = 10; //width
               int hInsertion = insertion[index]*3; //height

               page.fillRect(xInsertion, y, w, hInsertion);
               xInsertion += 20;
            }	
			page.drawString("All Done!", 260, 100);
			
		}
		
   }
   
    //Butn1Handler resets the click counter back to zero
    private class Butn1Handler implements ActionListener 
    {
		
        public void actionPerformed(ActionEvent e) 
        {
			if (count >= selection.length && count >= insertion.length+1 || count >= 9)
				repaint();
		
			else
			{
				min = selectionIndex;
				for (int scan = selectionIndex+1; scan < selection.length; scan++)
					if (selection[scan] < selection[min])
						min = scan;
					
				//Swap values
				temp1 = selection[min];
				selection[min] = selection[selectionIndex];
				selection[selectionIndex] = temp1;
				
				temp2 = insertion[insertionIndex];
				int pos;
				for (pos = insertionIndex -1; pos >= 0 && temp2 < insertion[pos]; pos--)
					insertion[pos+1] = insertion[pos];
				insertion[pos + 1] = temp2;
			}
			count++;
			selectionIndex++;
			insertionIndex++;
			repaint();
		   
        }
    }
}



