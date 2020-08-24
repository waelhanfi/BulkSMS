/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package businessGetSilver;

public class User
{
	private int userId ;
	private String userName ;
	private String userPassword ;
	private String userRole ;
    private int userCredit;

    public int getUserCredit() {
        return userCredit;
    }

    public void setUserCredit(int userCredit) {
        this.userCredit = userCredit;
    }

	public String getUserRole()
	{
		return userRole;
	}
	public void setUserRole(String userRole)
	{
		this.userRole = userRole;
	}
	public int getUserId()
	{
		return userId;
	}
	public void setUserId(int userId)
	{
		this.userId = userId;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getUserPassword()
	{
		return userPassword;
	}
	public void setUserPassword(String userPassword)
	{
		this.userPassword = userPassword;
	}
}
