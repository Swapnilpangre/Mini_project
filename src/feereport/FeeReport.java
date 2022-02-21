package feereport;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import admin.AdminDao;
import admin.AdminDaoImp;
import accountant.AccountantDao;
import accountant.AccountantDaoImp;
import student.StudentDao;
import student.StudentDaoImp;
import accountant.Accountant;
import student.Student;
public class FeeReport {
	public static boolean check=false;
	
	Scanner sc=new Scanner(System.in);
	AdminDao ad=new AdminDaoImp();
	AccountantDao ac=new AccountantDaoImp();
	StudentDao st=new StudentDaoImp();
	Accountant accountant=new Accountant();
	Student student=new Student();
	ArrayList<Accountant> accountants=new ArrayList<Accountant>();
	ArrayList<Student> students=new ArrayList<Student>();
	FeeReport()
	{
		swtching();
	}
	public static Connection c()
	{
		Connection c = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c=DriverManager.getConnection("jdbc:mysql://localhost/miniproject","root","P@ssword123");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return c;
	}
	public static File s()
	{
		File file=new File("/home/swapnil/Desktop/java ATDev/IO_Project/Student.txt");
		return file;
	}
	public static File a()
	{
		File file=new File("/home/swapnil/Desktop/java ATDev/IO_Project/Accountant.txt");
		return file;
	}
	
	public void Admin_login()
	{
		System.out.println("Enter Admin name");
		String aname=sc.next();
		System.out.println("Enter Admin password");
		String apassword=sc.next();
		if(ad.check_Admin(aname, apassword))
		{
			int j=0;
			while(j==0)
			{
				System.out.println("\n wellcome to Admin Section \n");
				System.out.println("1. Add Accountant");
				System.out.println("2. Display Accountant");
				System.out.println("3. exit");
				int k=sc.nextInt();
				if(k==1)
					{
					String name,password,email,contact;
					System.out.println("\n Enter Accountant information \n");
					
					System.out.println("Enter Name");
					while (!(name = sc.nextLine()).trim().matches("[A-Za-z ]+")) 
					{System.out.print("\n Invalid re-enter : ");}accountant.setA_name(name);
					
					System.out.println("Enter Password");
					while(!((password=sc.nextLine()).trim().length()>4)) {
						System.out.print("\nShort password re-enter : ");}accountant.setA_password(password);
					
					System.out.println("Enter Email");
					while(!(email=sc.nextLine()).matches("[A-Za-z0-9]+[@][a-z]+[.][a-z]+")) {
						System.out.print("\nInvalid email re-enter : ");}accountant.setA_email(email);
					
					System.out.println("Enter contact");
					while (!(contact = sc.nextLine()).trim().matches("[0-9]+")) {
						System.out.print("Invalid fee re-enter : ");}accountant.setA_contact(Long.parseLong(contact));
					if(ac.addAccountant(accountant))
						System.out.println("Accountant add Succrssfully");
					else
						System.out.println("Accountant not added !!!!");
					}
				else if(k==2)
					{
					accountants.clear();
					accountants=ac.displayAccountant();
					for(Accountant a : accountants)
					{
						System.out.print("Name = "+a.getA_name()+"\t");
						System.out.print("Password = "+a.getA_password()+"\t");
						System.out.print("Email = "+a.getA_email()+"\t");			
						System.out.print("Contact = "+a.getA_contact()+"\n");
					}
					System.out.println();
					}
				else if(k==3)
					j++;
				else
					System.out.println("number is invalid");
			}
			
		}
		else
			System.out.println("Invalid Name Or Password");
	
	}
	public void Accountant_login()
	{	
		System.out.println("Enter Accountant name");
		String sname=sc.next();
		System.out.println("Enter Accountant password");
		String spassword=sc.next();
		if(ac.checkAccountant(sname, spassword))
		{
			int j=0;
			while(j==0)
			{
				System.out.println("\n wellcome to Accountant Section \n");
				System.out.println("1. Add Student");
				System.out.println("2. Display student");
				System.out.println("3. Load student");
				System.out.println("4. Edit student");
				System.out.println("5. fee student");
				System.out.println("6. exit");
				int k=sc.nextInt();
				if(k==1)
					{
					String name,email,course,fee,paid,address,city,state,country,contact;
					System.out.println("\n Enter student information \n");
					
					System.out.println("Enter name");
					while (!(name = sc.nextLine()).trim().matches("[A-Za-z]+")) {
						System.out.print("Invalid re-enter : ");}student.setName(name);
					
					System.out.println("Enter Email");
					while (!(email = sc.nextLine()).trim().matches("[A-Za-z0-9]+[@][a-z]+[.][a-z]+")) {
						System.out.print("Invalid e-mail re-enter : ");}student.setEmail(email);
						
					System.out.println("Enter course");
					while (!(course = sc.nextLine()).trim().matches("[A-Za-z]+")) {
						System.out.print("Invalid re-enter : ");}student.setCourse(course);
					
					System.out.println("Enter fee");
					while (!(fee = sc.nextLine()).trim().matches("[0-9]+")) {
						System.out.print("Invalid fee re-enter : ");}student.setFee(Integer.parseInt(fee));
					System.out.println("Enter paid");
					
					student.setPaid(sc.nextInt());
					while (!(paid = sc.nextLine()).trim().matches("[0-9]+")) {
						System.out.print("Invalid paid re-enter : ");}student.setPaid(Integer.parseInt(paid));
			
					student.setDue(student.getFee()-student.getPaid());
					
					System.out.println("Enter address");
					while (!(address = sc.nextLine()).trim().matches("[A-Za-z0-9 -]+")) {
						System.out.print("Invalid characters re-enter : ");}student.setAddress(address);
					
					System.out.println("Enter city");
					while (!(city = sc.nextLine()).trim().matches("[A-Za-z]+")) {
						System.out.print("Invalid characters re-enter : ");}student.setCity(city);
					
					System.out.println("Enter state");
					while (!(state = sc.nextLine()).trim().matches("[A-Za-z]+")) {
						System.out.print("Invalid characters re-enter : ");}student.setState(state);
					
					System.out.println("Enter country");
					while (!(country = sc.nextLine()).trim().matches("[A-Za-z ]+")) {
						System.out.print("Invalid characters re-enter : ");}student.setCountry(country);
					
					System.out.println("Enter contact");
					while (!(contact = sc.nextLine()).trim().matches("[0-9]+")) {
						System.out.print("Invalid contact re-enter : ");}student.setContact(Long.parseLong(contact));
					if(st.addStudent(student))
						System.out.println("Student add Succrssfully");
					else
						System.out.println("Student not added !!!!");
					}
				else if(k==2)
					{
					students.clear();
					students=st.displayStudent();
					for(int i=0;i<students.size();i++)
					{
						System.out.print("id = "+students.get(i).getId()+"\t");
						System.out.print("Name = "+students.get(i).getName()+"\t");
						System.out.print("Email = "+students.get(i).getEmail()+"\t");
						System.out.print("Course = "+students.get(i).getCourse()+"\t");
						System.out.print("Fee = "+students.get(i).getFee()+"\t");
						System.out.print("Paid = "+students.get(i).getPaid()+"\t");
						System.out.print("Due = "+students.get(i).getDue()+"\t");
						System.out.print("Address = "+students.get(i).getAddress()+"\t");
						System.out.print("City = "+students.get(i).getCity()+"\t");
						System.out.print("State = "+students.get(i).getState()+"\t");
						System.out.print("Country = "+students.get(i).getCountry()+"\t");
						System.out.print("Contact = "+students.get(i).getContact()+"\n");
					}
					System.out.println();
					}
				else if(k==3)
					{
					String id;
					System.out.println("Enter student id");
					while (!(id = sc.nextLine()).trim().matches("[0-9]+")) {
						System.out.print("Invalid id re-enter : ");}
					student=st.loadStudent(Integer.parseInt(id));
					if(student.getName() != null)
					{
						System.out.print("id = "+student.getId()+"\t");
						System.out.print("Name = "+student.getName()+"\t");
						System.out.print("Email = "+student.getEmail()+"\t");
						System.out.print("Course = "+student.getCourse()+"\t");
						System.out.print("Fee = "+student.getFee()+"\t");
						System.out.print("Paid = "+student.getPaid()+"\t");
						System.out.print("Due = "+student.getDue()+"\t");
						System.out.print("Address = "+student.getAddress()+"\t");
						System.out.print("City = "+student.getCity()+"\t");
						System.out.print("State = "+student.getState()+"\t");
						System.out.print("Country = "+student.getCountry()+"\t");
						System.out.print("Contact = "+student.getContact()+"\n");
					}
					else
						System.out.println("Record not Found");
					}
				else if(k==4)
					{
					students.clear();
					String id;
					System.out.println("Enter student id");
					while (!(id = sc.nextLine()).trim().matches("[0-9]+")) {
						System.out.print("Invalid id re-enter : ");}
					student=st.loadStudent(Integer.parseInt(id));
					if(student.getName() != null)
					{
						System.out.print("id = "+student.getId()+"\t");
						System.out.print("Name = "+student.getName()+"\t");
						System.out.print("Email = "+student.getEmail()+"\t");
						System.out.print("Course = "+student.getCourse()+"\t");
						System.out.print("Fee = "+student.getFee()+"\t");
						System.out.print("Paid = "+student.getPaid()+"\t");
						System.out.print("Due = "+student.getDue()+"\t");
						System.out.print("Address = "+student.getAddress()+"\t");
						System.out.print("City = "+student.getCity()+"\t");
						System.out.print("State = "+student.getState()+"\t");
						System.out.print("Country = "+student.getCountry()+"\t");
						System.out.print("Contact = "+student.getContact()+"\n");
						
						System.out.println();
						student=students.get(0);
						System.out.println("1. Update Name");
						System.out.println("2. Update Email");
						System.out.println("3. Update Course");
						System.out.println("4. Update Fee");
						System.out.println("5. Update Paid");
						System.out.println("6. Update Address");
						System.out.println("7. Update City");
						System.out.println("8. Update State");
						System.out.println("9. Update Country");
						System.out.println("10. Update Contact");
						String CH;
						System.out.println("Enter option");
						while (!(CH = sc.nextLine()).trim().matches("[0-9]+")) {
							System.out.print("Invalid number re-enter : ");}
						int ch=Integer.parseInt(CH);
						String name,email,course,fee,paid,address,city,state,country,contact;
						switch(ch)
						{
						case 1:{  
							System.out.println("Enter name");
							while (!(name = sc.nextLine()).trim().matches("[A-Za-z ]+")) 
							{System.out.print("\n Invalid re-enter : ");}student.setName(name);
							break;}
						case 2:{
							System.out.println("Enter Email");
							while (!(email = sc.nextLine()).trim().matches("[A-Za-z0-9]+[@][a-z]+[.][a-z]+")) {
								System.out.print("Invalid e-mail re-enter : ");}student.setEmail(email);
								break;}
						case 3:{
							System.out.println("Enter course");
							while (!(course = sc.nextLine()).trim().matches("[A-Za-z]+")) {
								System.out.print("Invalid re-enter : ");}student.setCourse(course);
						break;}
						case 4:{
							System.out.println("Enter fee");
							while (!(fee = sc.nextLine()).trim().matches("[0-9]+")) {
								System.out.print("Invalid fee re-enter : ");}student.setFee(Integer.parseInt(fee));
								student.setDue(student.getFee()-student.getPaid());
							break;}
						case 5:{
							System.out.println("Enter paid");
							student.setPaid(sc.nextInt());
							while (!(paid = sc.nextLine()).trim().matches("[0-9]+")|student.getFee()<Integer.parseInt(paid)) {
								System.out.print("Invalid paid re-enter : ");}student.setPaid(Integer.parseInt(paid));
								student.setDue(student.getFee()-student.getPaid());
							break;}
						case 6:{
							System.out.println("Enter address");
							while (!(address = sc.nextLine()).trim().matches("[A-Za-z0-9 -]+")) {
								System.out.print("Invalid characters re-enter : ");}student.setAddress(address);
							break;}
						case 7:{
							System.out.println("Enter city");
							while (!(city = sc.nextLine()).trim().matches("[A-Za-z]+")) {
								System.out.print("Invalid characters re-enter : ");}student.setCity(city);
							break;}
						case 8:{
							System.out.println("Enter state");
							while (!(state = sc.nextLine()).trim().matches("[A-Za-z]+")) {
								System.out.print("Invalid characters re-enter : ");}student.setState(state);
							break;}
						case 9:{
							System.out.println("Enter country");
							while (!(country = sc.nextLine()).trim().matches("[A-Za-z ]+")) {
								System.out.print("Invalid characters re-enter : ");}student.setCountry(country);
							break;}
						case 10:{
							System.out.println("Enter contact");
							while (!(contact = sc.nextLine()).trim().matches("[0-9]+")) {
								System.out.print("Invalid contact re-enter : ");}student.setContact(Long.parseLong(contact));
							break;}
						default:
							System.out.println("invalied number entre");
						}
						if(st.editStudent(Integer.parseInt(id), student))
							System.out.println("Record edit Succrssfully");
					}
					else
						System.out.println("Record not Found");
					
					}
				else if(k==5)
				{
					students.clear();
					students=st.feeStudent();
					for(Student s : students)
					{
						System.out.print("id = "+s.getId()+"\t");
						System.out.print("Name = "+s.getName()+"\t");
						System.out.print("Due = "+s.getDue()+"\t");
						System.out.println();
					}
					
				}
				else if(k==6)
					j++;
				else
					System.out.println("number is invalid");
			}
			
		}
		else
			System.out.println("Invalid Name Or Password");
	}
	public void loginFlow()
	{
		int j=0;
		while(j==0)
		{
			System.out.println("1. Admin Login");
			System.out.println("2. Accountant Login");
			System.out.println("3. Exit");
			int i=sc.nextInt();
			if(i==1)
				Admin_login();
			else if(i==2)
				Accountant_login();
			else if(i==3)
				j++;
			else
				System.out.println("number is invalid");
		}
	}
	public void swtching()
	{
		int a=0;
		while(a==0)
		{
			System.out.println("1. Operation on File");
			System.out.println("2. Operation on Database");
			System.out.println("3. Exit");
			int b=sc.nextInt();
			if(b==1)
				{FeeReport.check=true;loginFlow();}
			else if(b==2)
				{FeeReport.check=false;loginFlow();}
			else if(b==3)
				a++;
			else
				System.out.println("Enter number is invalid");
		}
		
	}
	public static void main(String[] args) {

		new FeeReport();
	}
}
