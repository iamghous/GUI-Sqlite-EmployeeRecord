
/**
* Employee class documentation here
* @author Noman Ghous
*/
public class Person {

private String name;
private char gender;
private String natInscNo;
private String dob;
private String address;
private String postcode;

	
	public Person(String name, char gender, String natInscNo, String dob, String address, String postcode) {
	
	this.name = name;
	this.gender = gender;
	this.natInscNo = natInscNo;
	this.dob = dob;
	this.address = address;
	this.postcode = postcode;
}


	public Person(){
		
		
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public char getGender() {
		return gender;
	}


	public void setGender(char gender) {
		this.gender = gender;
	}


	public String getNatInscNo() {
		return natInscNo;
	}


	public void setNatInscNo(String natInscNo) {
		this.natInscNo = natInscNo;
	}


	public String getDob() {
		return dob;
	}


	public void setDob(String dob) {
		this.dob = dob;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPostcode() {
		return postcode;
	}


	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}




	
	public String toString() {
		System.out.println("Name :  " + name + ", Gender :  " + gender + ", NatInscNo :  " + natInscNo + ", DOB :  " + dob
		+ ", Address :  " + address + ", Postcode :  " + postcode);	

	//return "Person [name=" + name + ", gender=" + gender + ", natInscNo=" + natInscNo + ", dob=" + dob
		//		+ ", address=" + address + ", postcode=" + postcode + "]";
	return null;	
	};
	
}
