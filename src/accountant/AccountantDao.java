package accountant;

import java.util.ArrayList;

public interface AccountantDao {
	
	public boolean addAccountant(Accountant accountant);
	public ArrayList<Accountant> displayAccountant();
	public boolean checkAccountant(String Name,String Password);
	
}
