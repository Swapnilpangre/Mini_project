package student;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ListIterator;

import feereport.FeeReport;


public class StudentDaoImp implements StudentDao {
	ObjectOutputStream oos=null;
	ObjectInputStream ois=null;
	ArrayList<Student> students=new ArrayList<Student>();
	@SuppressWarnings("unchecked")
	@Override
	public boolean addStudent(Student student) {
		boolean b=false;
		if(FeeReport.check)
		{
			try
			   {
				students.clear();
				ois=new ObjectInputStream(new FileInputStream(FeeReport.s()));
				students=(ArrayList<Student>)ois.readObject();
				ois.close();
				student.setId(students.size()+1);
				students.add(student);
				oos=new ObjectOutputStream(new FileOutputStream(FeeReport.s()));
				oos.writeObject(students);
				oos.close();
				b=true;
	         }
			catch(Exception e){e.printStackTrace();}
		}
		else
		{
			try
			{
		        String sqlq = "INSERT INTO student(name,email,course,fee,paid,due,address,city,state,country,contact) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	            PreparedStatement st = FeeReport.c().prepareStatement(sqlq);
	            st.setString(1,student.getName());
	            st.setString(2,student.getEmail());
	            st.setString(3,student.getCourse());
	            st.setInt(4,student.getFee());
	            st.setInt(5,student.getPaid());
	            st.setInt(6,student.getDue());
	            st.setString(7,student.getAddress());
	            st.setString(8,student.getCity());
	            st.setString(9,student.getState());
	            st.setString(10,student.getCountry());
	            st.setLong(11,student.getContact());
	            st.executeUpdate();
			}
			catch(Exception e){e.printStackTrace();}
		}
	
		return b;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Student> displayStudent() {
		if(FeeReport.check)
		{
			try
			{
				students.clear();
				ois=new ObjectInputStream(new FileInputStream(FeeReport.s()));
				students=(ArrayList<Student>)ois.readObject();
				ois.close();
				System.out.println(students);
				
			}
			catch(Exception e){e.printStackTrace();}
		}
		else
		{
			try
			{
				students.clear();
			    java.sql.Statement selectStmt = FeeReport.c().createStatement();
			    ResultSet rs = ((java.sql.Statement) selectStmt).executeQuery("SELECT * FROM student");
				  while(rs.next()) { 
					  Student student=new Student();
					  student.setId(rs.getInt(1));
					  student.setName(rs.getString(2));
				      student.setEmail(rs.getString(3));
				      student.setCourse(rs.getString(4));
				      student.setFee(rs.getInt(5));
				      student.setPaid(rs.getInt(6));
				      student.setDue(rs.getInt(7));
				      student.setAddress(rs.getString(8));
				      student.setCity(rs.getString(9));
				      student.setState(rs.getString(10));
				      student.setCountry(rs.getString(11));
				      student.setContact(rs.getLong(12));
				      students.add(student);}	 
			}
			catch(Exception e){e.printStackTrace();}
		}
		return students;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Student loadStudent(int id) {
		Student stud=new Student();
		if(FeeReport.check)
		{
			try
			{
				ois=new ObjectInputStream(new FileInputStream(FeeReport.s()));
				students=(ArrayList<Student>)ois.readObject();
				ois.close();
				ListIterator<Student> li=students.listIterator();
				while(li.hasNext())
				{
					Student s=(Student)li.next();
					if(id==s.getId())
					{
						stud=s;
					}
				}
			}
			catch(Exception e){e.printStackTrace();}
			
		}
		else
		{
			try
			{
				String sqlq =("SELECT * FROM student WHERE id=?");
				PreparedStatement st= FeeReport.c().prepareStatement(sqlq);
				st.setInt(1,id);
				ResultSet rs = st.executeQuery(); 
				 if(rs.next()) { 
					 stud.setId(rs.getInt(1));
					  stud.setName(rs.getString(2));
				      stud.setEmail(rs.getString(3));
				      stud.setCourse(rs.getString(4));
				      stud.setFee(rs.getInt(5));
				      stud.setPaid(rs.getInt(6));
				      stud.setDue(rs.getInt(7));
				      stud.setAddress(rs.getString(8));
				      stud.setCity(rs.getString(9));
				      stud.setState(rs.getString(10));
				      stud.setCountry(rs.getString(11));
				      stud.setContact(rs.getLong(12));}
			}
			catch(Exception e){e.printStackTrace();}
		}
		 return stud;
	}

	@Override
	public boolean editStudent(int id,Student stud) {
		boolean a=false;
		if(FeeReport.check)
		{
			students.clear();
			students=displayStudent();
			for(Student s : students)
			{
				if(id==s.getId())
				{
					s.setName(stud.getName());
					s.setEmail(stud.getEmail());
					s.setCourse(stud.getCourse());
					s.setFee(stud.getFee());
					s.setDue(stud.getDue());
					s.setPaid(stud.getPaid());
					s.setAddress(stud.getAddress());
					s.setCity(stud.getCity());
					s.setState(stud.getState());
					s.setCountry(stud.getCountry());
					s.setContact(stud.getContact());
					
					try {
						oos = new ObjectOutputStream(new FileOutputStream(FeeReport.s()));
						oos.writeObject(students);
						oos.close();
						a=true;
					} 
					catch (IOException e) {e.printStackTrace();}
					
				}
			}
		}
		else
		{
			int count=0;
			try
			{
				String sqlq =("SELECT count(*) FROM student WHERE id=?");
				PreparedStatement st= FeeReport.c().prepareStatement(sqlq);
				st.setInt(1,id);
				ResultSet rs = st.executeQuery();
				rs.next();
				count = rs.getInt(1);
				st.close();
			}
			catch(Exception f){f.printStackTrace();}
			if(count>0)
			{
				try
				{
			        String sql = "UPDATE student SET name=?,email=?,course=?,fee=?,paid=?,due=?,address=?,city=?,state=?,country=?,contact=? WHERE id=?";
		            PreparedStatement pst = FeeReport.c().prepareStatement(sql);
		            pst.setString(1,stud.getName());
		            pst.setString(2,stud.getEmail());
		            pst.setString(3,stud.getCourse());
		            pst.setInt(4,stud.getFee());
		            pst.setInt(5,stud.getPaid());
		            pst.setInt(6,stud.getDue());
		            pst.setString(7,stud.getAddress());
		            pst.setString(8,stud.getCity());
		            pst.setString(9,stud.getState());
		            pst.setString(10,stud.getCountry());
		            pst.setLong(11,stud.getContact());
		            pst.setInt(12,id);
		            pst.executeUpdate();
		            a=true;
				}
				catch(Exception f){f.printStackTrace();}
			}
		}
		return a;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Student> feeStudent() {
		students.clear();
		
		if(FeeReport.check)
		{
			try {
				ObjectInputStream ois=new ObjectInputStream(new FileInputStream(FeeReport.s()));
				students=(ArrayList<Student>)ois.readObject();
				ois.close();
			} catch (IOException | ClassNotFoundException e) {e.printStackTrace();} 
		}
		else
		{
			try
			{
			    java.sql.Statement selectStmt = FeeReport.c().createStatement();
			    ResultSet rs = ((java.sql.Statement) selectStmt).executeQuery("SELECT id,name,due FROM student");
				 while(rs.next()) { 
					 if(rs.getInt(3)>0)
					 {
						 Student s=new Student();
						 s.setId(rs.getInt(1));
						 s.setName(rs.getString(2));
						 s.setDue(rs.getInt(3));
						 students.add(s);
					 }
					 }
			}
			catch(Exception e){System.out.println(e);}
		}
		return students;
	}

}
