package admin;

public class AdminDaoImp implements AdminDao {
	Admin am=new Admin();

	@Override
	public boolean check_Admin(String Username, String Password) {
		am.setAdmin_name("swapnil");
		am.setAdmin_password("swapnil");
		boolean b=false; 
		if(Username.equals(am.getAdmin_name())&&Password.equals(am.getAdmin_password()))
			b=true;
		else
			b=false;
		return b;
	}

}
