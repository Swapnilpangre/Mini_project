package accountant;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ListIterator;

import feereport.FeeReport;

public class AccountantDaoImp implements AccountantDao {
	ArrayList<Accountant> accountants=new ArrayList<Accountant>();
	ObjectOutputStream oos=null;
	ObjectInputStream ois=null;
	@SuppressWarnings("unchecked")
	@Override
	public boolean addAccountant(Accountant accountant) {
		boolean b=false;
		if(FeeReport.check)
		{
			try
			   {
				accountants.clear();
				ois=new ObjectInputStream(new FileInputStream(FeeReport.a()));
				accountants=(ArrayList<Accountant>)ois.readObject();
				ois.close();
				accountants.add(accountant);
				oos=new ObjectOutputStream(new FileOutputStream(FeeReport.a()));
				oos.writeObject(accountants);
				oos.close();
				b=true;
	      }
			catch(Exception e){e.printStackTrace();}
		}
		else
		{
			try
			{
				String sqlq = "INSERT INTO accountant(name,password,email,contact) values (?, ?, ?, ?)";
	            PreparedStatement st = FeeReport.c().prepareStatement(sqlq);
	            st.setString(1,accountant.getA_name());
	            st.setString(2,accountant.getA_password());
	            st.setString(3,accountant.getA_email());
	            st.setLong(4,accountant.getA_contact());
	            b=true;
			}
			catch(Exception k){System.out.println(k);}
		}
		return b;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Accountant> displayAccountant() {
		accountants.clear();
		if(FeeReport.check)
		{
			try
			{
				accountants.clear();
				ois=new ObjectInputStream(new FileInputStream(FeeReport.a()));
				accountants=(ArrayList<Accountant>)ois.readObject();
				ois.close();
			}
			catch(Exception e){e.printStackTrace();}
		}
		else
		{
			try
			{
			    java.sql.Statement selectStmt = FeeReport.c().createStatement();
			    ResultSet rs = ((java.sql.Statement) selectStmt).executeQuery("SELECT * FROM accountant");
			    while(rs.next()) { 
			    	Accountant accountant=new Accountant();
					  accountant.setA_name(rs.getString(1));
					  accountant.setA_password(rs.getString(2));
					  accountant.setA_email(rs.getString(3));
					  accountant.setA_contact(rs.getLong(4));
					  accountants.add(accountant);
			    }    
			}
			catch(Exception e){e.printStackTrace();}
		}
		return accountants;
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean checkAccountant(String Name, String Password) {
		boolean b=false;
		String databasepass=null;
		if(FeeReport.check)
		{
		
			try
			{
				ObjectInputStream ois=new ObjectInputStream(new FileInputStream(FeeReport.a()));
				accountants=(ArrayList<Accountant>)ois.readObject();
				ois.close();
				ListIterator<Accountant> li=accountants.listIterator();
				while(li.hasNext())
				{
					Accountant a=(Accountant)li.next();
					if(a.getA_name().equals(Name))
						if(a.getA_password().equals(Password))
							b=true;	
				}
			}
			catch(Exception e){e.printStackTrace();}
		}
		else
		{
			try
			{
				
				String sql =("SELECT password FROM accountant WHERE name=?");
				PreparedStatement st= FeeReport.c().prepareStatement(sql);
				st.setString(1, Name);
				ResultSet rs=st.executeQuery();
				if(rs.next())
				{
					System.out.println(rs.getString(1));
					if(rs.getString(1).equals(Password))
						b=true;
				}
		    	
			}
			catch(Exception k){k.printStackTrace();}
				
		}
		return b;
	}
}

