package businessGetSilver;


public class Administrateur {

  private String Login;
  private String Psw;
  private String role;

    public Administrateur() {
    }




    public Administrateur(String Login, String Psw, String role) {
        this.Login = Login;
        this.Psw = Psw;
        this.role = role;
    }

    public Administrateur(String Login, String Psw) {
        this.Login = Login;
        this.Psw = Psw;
    }



    public String getLogin() {
        return Login;
    }


    public void setLogin(String Login) {
        this.Login = Login;
    }


    public String getPsw() {
        return Psw;
    }

    public void setPsw(String Psw) {
        this.Psw = Psw;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }



  

  public static void main(String[] args) {
  
    //System.out.append("bonjour");

    
  }

}
