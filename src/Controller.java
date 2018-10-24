
/**
* Controller class documentation here
* @author Noman Ghous
*/
public class Controller {

	public static void main(String[] args) {
		
  EmployeeDAO emp = new EmployeeDAO();
  Employee empp = new Employee();
  
    
    //emp.selectAllEmployeeByName("Noman");
     //emp.selectAllEmployees();
  
  //   emp.insertEmployee(empp);
  emp.InsertEmplyeeAtID(empp, "10002");
    //emp.deleteEmployeeAtID("noman");
      
  
	}

}
