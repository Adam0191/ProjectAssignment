// -----------------------------------------------------
// Assignment 4
// Question: PART II, CellListUtilization.java (contains the main method)
// Written by: Fouad Serradj | ID : 40009794
// ---------------------------------------------
package assignment;

import java.io.IOException;
import java.io.FileInputStream;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class CellListUtilization {


    public static void main(String[] args) {
        CellList listA=new CellList();
        CellList listB=new CellList();
        CellList listC;
        Scanner inputStream=null;
        try
        {
            inputStream=new Scanner(new FileInputStream("Cell_Info.txt"));
            fileToList(inputStream, listA);
            inputStream.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Problem with opening the file, program will terminate.");
            System.exit(0);
        }
        catch (IOException e)
        {
            System.out.println("Problem with reading the file, program will terminate.");
            System.exit(0);
        }


        listA.showContents();

        //prompt the user to search serial number in the list
        search(listA);
        System.out.println("Do you want to search another serial number? Y/N");
        Scanner input=new Scanner(System.in);
        char s='a';
        s=input.next().toUpperCase().charAt(0);
        while(s=='Y')
        {
            search(listA);
            System.out.println("Do you want to search another serial number? Y/N");
            s=input.next().toUpperCase().charAt(0);
        }

        //Here are some objects created to test the methods 
        CellPhone newOne=new CellPhone(1000000, "Huawei",699.99,2016);

        System.out.println("\n\nTest of insertAtIndex method:");
        System.out.println("1.Test with an index  bigger than the size of list.");
        listA.insertAtIndex(newOne, 24);//test with index bigger than size
        System.out.println("2.Test with an index 0 (special case).");
        listA.insertAtIndex(newOne, 0);//test with an index  0, it's test of addToStart method too
        System.out.println("3.Test with regular index (in this exemple it is 5.");
        listA.insertAtIndex(newOne, 5);//test regular
        System.out.println("The results of Tests made :");
        listA.showContents();

        System.out.println("\n\nTest of deleteFromIndex method.");
        System.out.println("1.Test with negative index.");
        listA.deleteFromIndex(-1);//test with negative index
        System.out.println("2.Test with regular index (in this case it s 5).");
        listA.deleteFromIndex(5);//test regular index for removal
        System.out.println("3.Test with index 0 (special case).");
        listA.deleteFromIndex(0);//test with index 0, it's test of deleteFromStart method too
        System.out.println("The results of Tests made.");
        listA.showContents();
        System.out.println("\n4. Test on empty list.");
        listB.deleteFromIndex(0);

        System.out.println("\n\nTest of repalceAtIndex method.");
        System.out.println("1. Test with index 0");
        listA.replaceAtIndex(newOne, 0);//test with index 0
        System.out.println("2.test regular index (in this case 5)");
        listA.replaceAtIndex(newOne,5);//test regular index
        System.out.println("The results of Tests made.");
        listA.showContents();
        System.out.println("\n3. Test on empty list.");
        listB.replaceAtIndex(newOne, 3);
        listB.showContents();

        System.out.println("\n\nTest the copy constructor of CellList class (its name is listC copied from ListA)");
        System.out.println("==============================================================================");
        listC=new CellList(listA);
        listC.showContents();

        System.out.println("\n\nTest the equals method of CellList class (between ListC and ListA and ListB)");
        System.out.println("==============================================================================");
        if(listC.equals(listA))
            System.out.println("ListA and ListC are equal");
        else
            System.out.println("ListA and ListC are not equal");
        if(listC.equals(listB))
            System.out.println("ListB and ListC are equal");
        else
            System.out.println("ListB and ListC are not equal");

    }//end of main method


    public static void fileToList(Scanner inputStream, CellList a) throws IOException
    {
    	//inputStream.useDelimiter(" ");
        while(inputStream.hasNextLine())
        {
        	// use of lineScanner to take over the first input from file, so we can divide line into pieces of info
            String line=inputStream.nextLine();
            Scanner lineScanner=new Scanner(line);
            String serialNumber=lineScanner.next();
            String brand=lineScanner.next();
            String price=lineScanner.next();
            String year=lineScanner.next();
            CellPhone newOne=new CellPhone(Long.parseLong(serialNumber),brand,Double.parseDouble(price),Integer.parseInt(year));
            // had to use wrapper classes since using the regular double and int for the year and prices won't work and generated mismatchInput exception
            
            if(!a.contains(Long.parseLong(serialNumber)))
            {
                a.addToStart(newOne);
            }
        }
    }

    public static void search(CellList a)
    {
        Scanner input=new Scanner(System.in);
        System.out.println("Would like to search for a device ? just enter the associated Serial number:");
        String number=input.next();
        a.contains(Long.parseLong(number));
    }


}//end of class
