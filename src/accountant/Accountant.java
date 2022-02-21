package accountant;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Accountant implements Serializable {
	
	private String A_name;
	private String A_password;
	private String A_email;
	private long A_contact;
	public String getA_name() {
		return A_name;
	}
	
	public void setA_name(String a_name) {
		A_name = a_name;
	}
	
	public String getA_password() {
		return A_password;
	}
	
	public void setA_password(String a_password) {
		A_password = a_password;
	}
	
	public String getA_email() {
		return A_email;
	}
	
	public void setA_email(String a_email) {
		A_email = a_email;
	}
	
	public long getA_contact() {
		return A_contact;
	}
	
	public void setA_contact(long a_contact) {
		A_contact = a_contact;
	}
	
}
