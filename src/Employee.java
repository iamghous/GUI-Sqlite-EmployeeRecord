
/**
* Employee class documentation here
* @author Noman Ghous
*/

public class Employee extends Person {

/** The id. */
private String id;

/** The salary. */
private String salary;

/** The start date. */
private String startDate;

/** The title. */
private String title;

/** The email. */
private String email;


/**
 * Instantiates a new employee.
 */
public Employee(){
	
}

/**
 * Instantiates a new employee.
 *
 * @param id the id
 * @param name the name
 * @param gender the gender
 * @param DOB the dob
 * @param address the address
 * @param postCode the post code
 * @param natInsNo the nat ins no
 * @param jobTitle the job title
 * @param startDate the start date
 * @param salary the salary
 * @param email the email
 */
public Employee(String id, String name, char gender, String DOB, String address, String postCode, String natInsNo, String jobTitle, String startDate, String salary, String email){
	super(name, gender, natInsNo,  DOB,  address,  postCode);
	this.id = id;
	this.salary = salary;
	this.startDate= startDate;
	this.title= jobTitle;
	this.email = email;
	
	
	
}

/**
 * Gets the id.
 *
 * @return the id
 */
public String getId() {
	return id;
}


/**
 * Sets the id.
 *
 * @param id the new id
 */
public void setId(String id) {
	this.id = id;
}


/**
 * Gets the salray.
 *
 * @return the salray
 */
public String getSalray() {
	return salary;
}


/**
 * Sets the salray.
 *
 * @param salray the new salray
 */
public void setSalray(String salray) {
	this.salary = salray;
}


/**
 * Gets the start date.
 *
 * @return the start date
 */
public String getStartDate() {
	return startDate;
}


/**
 * Sets the start date.
 *
 * @param startDate the new start date
 */
public void setStartDate(String startDate) {
	this.startDate = startDate;
}


/**
 * Gets the title.
 *
 * @return the title
 */
public String getTitle() {
	return title;
}


/**
 * Sets the title.
 *
 * @param title the new title
 */
public void setTitle(String title) {
	this.title = title;
}


/**
 * Gets the email.
 *
 * @return the email
 */
public String getEmail() {
	return email;
}


/**
 * Sets the email.
 *
 * @param email the new email
 */
public void setEmail(String email) {
	this.email = email;
}






public String toString() {
	super.toString();
	System.out.println("ID :  " + id + ", salray :  " + salary + ", startDate :  " + startDate + ", title :  " + title + ", email :  "
	+ email + "");
	System.out.println();
	System.out.println("----------------------------------------------------------------------------------");
	System.out.println();
	//return "Employee [id=" + id + ", salray=" + salary + ", startDate=" + startDate + ", title=" + title + ", email="
		//	+ email + "]";
	return null;
	
};
	
	
	
}
