package entities;


 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.Id;

 @Entity(name="Users")
 public class UsersManager {


	 public UsersManager() {
		// TODO Auto-generated constructor stub
	}
 	@Id
 	@Column(name="FullName")
 	private String fullname;

 	public String getFullname() {
 		return fullname;
 	}


 	public void setFullname(String fullname) {
 		this.fullname = fullname;
 	}

 	public UsersManager( String fullname) {
 		this.fullname = fullname;

 	}
 	/**
 	 * This function override the "toString" function.
 	 */
 	@Override
 	public String toString() {
 	return "Users [FullName=" + fullname+"]";
 	}

  }
