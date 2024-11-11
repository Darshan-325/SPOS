import java.util.*;
public class testJni1
{
static{
System.loadLibrary("native");
}
public static void main(String args[])
{
	Scanner sc=new Scanner(System.in);
	System.out.println("enter first no:");
	int n1=sc.nextInt();
	System.out.println("enter Second no:");
	int n2=sc.nextInt();
	int ch;
	do
	{
		System.out.println("1.add");
		System.out.println("2.sub");
		System.out.println("3.multi");
		System.out.println("4.squ");
		System.out.println("5.exit");
		System.out.println("enter your choice:");
		ch=sc.nextInt();
		switch(ch)
		{
			case 1:
			System.out.println("add is "+ new testJni1().add(n1,n2));
			break;
			
			case 2:
			System.out.println("sub is "+ new testJni1().sub(n1,n2));
			break;
			
			case 3:
			System.out.println("multi is "+ new testJni1().multi(n1,n2));
			break;
			
			case 4:
			System.out.println("square is "+ new testJni1().squ(n1));
			break;
		}
	}while(ch!=5);
			

	
}
private native int add(int n1,int n2);
private native int sub(int n1,int n2);
private native int multi(int n1,int n2);
private native int squ(int n1);
}
