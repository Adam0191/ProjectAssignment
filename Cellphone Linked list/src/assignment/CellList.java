// -----------------------------------------------------
// Assignment 4
// Question: PART II, CellList.java
// Written by: Fouad Serradj | ID : 40009794
// ---------------------------------------------

/*
 * The use of inner class for creating a single node will prevent privacy leak that would occur with external class through getters and setters
 */
package assignment;
import java.util.NoSuchElementException;

public class CellList implements Cloneable{
    private class CellNode implements Cloneable{
    	
        private CellPhone cp;
        private CellNode next;

        public CellNode()
        {
            cp=null;
            next=null;
        }

        public CellNode(CellPhone a, CellNode b)
        {
            this.cp=a;
            this.next=b;
        }

        public CellNode(CellNode cn)
        {
            this.cp=cn.cp;
            this.next=cn.next;
        }

        public CellPhone getOneCellPhone() {
            return cp;
        }

        public void setOneCellPhone(CellPhone oneCellPhone) {
            this.cp = oneCellPhone;
        }

        public CellNode getNext() {
            return next;
        }

        public void setNext(CellNode cn) {
            this.next = cn;
        }

        public CellNode clone()
        {
            return new CellNode(this);
        }

    }//end of inner class

    // Beginning of CellList class
    private CellNode head;
    private int size;

    // default constructor
    public CellList()
    {
        head=null;
        size=0;
    }

    // copy constructor
    public CellList(CellList cl)
    {
        size=cl.size;
        if (cl.head==null)//if the list passed is empty
        {
            head=null;
        }
        else
        {
            head=null;
            CellNode t1=cl.head,t2=null;
            while (t1!=null)
            {
                if(head==null)
                {
                    head=new CellNode(t1);//using copy constructor of CellNode to create a deep copy of CellPhone 
                    t2=head;
                }
                else
                {
                    t2.next=new CellNode(t1);//using copy constructor of CellNode to create a deep copy of CellPhone 
                    t2=t2.next;
                }
                t1=t1.next;
            }
            t2=null;
        }
    }

    public void addToStart(CellPhone cp)
    {
        head=new CellNode(cp, head);
        size++;
    }

    public void insertAtIndex(CellPhone a, int index)
    {
        try
        {
            if (index<0||index>(this.size-1))
                throw new NoSuchElementException("Out of the bound of the actual list.");

            if(index==0)
                addToStart(a);
            else
            {
                CellNode temp=head;
                for (int i=0;i<(index-1);i++)
                {
                    temp=temp.next;
                }
                temp.next=new CellNode(a, temp.next);
                temp=null;
                size++;
            }
        }
        catch (NoSuchElementException e)
        {
            System.out.println("==============================================================================");
            System.out.println(e.getMessage());
            System.out.println("The information has not been add.");
            System.out.println("==============================================================================");
        }

    }

    public void deleteFromIndex(int index)
    {
        try
        {
            if (index<0||index>(this.size-1))
                throw new NoSuchElementException("Out of the bound of the actual list.");

            if(index==0)
                deleteFromStart();//Check if the list is empty
            else
            {
                CellNode temp=head;
                for (int i=0;i<index-1;i++)
                {
                    temp=temp.next;
                }
                temp.next=temp.next.next;
                temp=null;
                size--;
            }
        }
        catch (NoSuchElementException e)
        {
            System.out.println("==============================================================================");
            System.out.println(e.getMessage());
            System.out.println("No information has been deleted.");
            System.out.println("==============================================================================");
        }
    }

    public void deleteFromStart()
    {
        if(head==null)
            return;
        head=head.next;
        size--;
    }

    public void replaceAtIndex(CellPhone a, int index)
    {
        if (index<0||index>(this.size-1))
            return;
        if (head==null)
            return;
        else
        {
            CellNode temp=head;
            for(int i=0;i<index;i++)
            {
                temp=temp.next;
            }
            temp.cp=a;
        }
    }

    private CellNode find(long serialNumber)
    {
        int iteration=0;
        if (head==null)
        {
            iteration++;
            return null;
        }
        else
        {
            CellNode temp=head;
            while(temp!=null&&temp.cp.getSerialNum()!=serialNumber)
            {
                temp=temp.next;
                iteration++;
            }
            System.out.println("The number of iteration performed is "+iteration+".");
            if (temp==null)
            {
                return null;
            }
            else
            {
                return temp;
            }
        }
    }

    public boolean contains(long serialNumber)
    {
        if (find(serialNumber)==null)
        {
            System.out.println("No same serial number can be found.");
            return false;
        }
        else
        {
            System.out.println("Same serial number is found, here is the information:");
            System.out.println(find(serialNumber).cp);
            return true;
        }

    }

    // showContent Method
    public void showContents()
    {
        if (head==null)
        {
            System.out.println("==============================================================================");
            System.out.println("There is nothing to display, the list is empty.");
            System.out.println("==============================================================================");
        }
        else
        {
            CellNode temp=head;
            System.out.println("==============================================================================");
            System.out.println("The current size of the list is "+size+". Here are the contents of the list:");
            System.out.println("==============================================================================");
            while(temp!=null)
            {
                System.out.println(temp.cp+" ---> ");
                temp=temp.next;
            }
        }
    }

    public boolean equals(CellList anotherOne)
    {
        if(this==null||anotherOne==null)
            return false;
        if(this.size!=anotherOne.size)
            return false;
        else
        {
            CellNode m=this.head;
            CellNode n=anotherOne.head;
            while(m!=null&&n!=null)
            {
                if(m.cp.equals(n.cp))
                {
                    m=m.next;
                    n=n.next;
                }
                else
                    return false;
            }
            return true;
        }
    }
}//end of class
