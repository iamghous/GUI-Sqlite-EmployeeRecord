

/**
* Employee class documentation here
* @author Noman Ghous
*/
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import com.toedter.calendar.JDateChooser;

import javafx.scene.control.ComboBox;
import net.proteanit.sql.DbUtils;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
 

public class ControllerGUI {

	protected static final String JTextField = null;
	private JFrame frame;
	private JTable table;
	private JTextField txtID;
	EmployeeDAO emd = new EmployeeDAO();
	Connection connect = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	private JComboBox comboBox;
	private JLabel lblImage;
	private JTextField txtName;
	private JTextField txtAddress;
	private JTextField txtPostCode;
	private JTextField txtNiNumber;
	private JTextField txtJobTitle;
	private JTextField txtSalary;
	private JTextField txtEmail;
	private JTextField txtLookUp;
	private JDateChooser dateChoosersd;
	private JDateChooser dateChooserdob;
	String filename;
	int s = 0;
	byte [] images;
	byte[] imageBytes; 
	int index = 0;
	
	
	private JTable table_1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ControllerGUI window = new ControllerGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void loadTable(){
	try{
		
		
		
		
		String querey = "SELECT * FROM employees";
		
		
		pst = connect.prepareStatement(querey);

		ResultSet rs = pst.executeQuery();
		
		
		
		table_1.setModel(DbUtils.resultSetToTableModel(rs));
		
		System.out.println("Table Loaded");
		
	}
	catch(Exception e1){
		
		System.out.println("Your code is shit");
	}
			
			
	}
	/**
	 * Create the application.
	 */
	public ControllerGUI() {
		initialize();
		
		connect = emd.getConnection();
		BindList();
		 loadTable();
		
	}
	
	
	
	
	
		 
	 
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		frame = new JFrame();
		frame.getContentPane().setBackground(UIManager.getColor("activeCaption"));
		frame.setBounds(100, 100, 1272, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		dateChooserdob = new JDateChooser();
		dateChooserdob.setBounds(168, 229, 131, 27);
		dateChooserdob.setDateFormatString("dd-MM-yyyy");
		frame.getContentPane().add(dateChooserdob);
		
		dateChoosersd = new JDateChooser();
		dateChoosersd.setBounds(168, 422, 131, 25);
		dateChoosersd.setDateFormatString("dd-MM-yyyy");
		frame.getContentPane().add(dateChoosersd);
		
		 comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"M", "F"}));
		comboBox.setBounds(168, 191, 63, 25);
		frame.getContentPane().add(comboBox);
		
		
		
		
		
		
		
		JButton insertButton = new JButton("INSERT");
		insertButton.setBounds(24, 543, 97, 25);
		insertButton.setBackground(new Color(0, 128, 0));
		insertButton.setForeground(Color.WHITE);
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
           try{
					
        	  String idd = txtID.getText();
        	  int id = Integer.parseInt(idd);
        	  
        	  
        	  
        	 
        	   
					
					String sql =  "   INSERT INTO employees VALUES(?, ?, ? , ? , ? , ? , ? , ? , ? , ? , ?,?)";
					
					pst = connect.prepareStatement(sql);
					
					pst.setInt(1, id);
					pst.setString(2, txtName.getText());
					String gender = comboBox.getSelectedItem().toString();
					pst.setString(3, gender);
					pst.setString(4, ((JTextField)dateChooserdob.getDateEditor().getUiComponent()).getText());
					pst.setString(5, txtAddress.getText());
					pst.setString(6, txtPostCode.getText() );
					pst.setString(7, txtNiNumber.getText());
					pst.setString(8, txtJobTitle.getText());
					pst.setString(9, ((JTextField)dateChoosersd.getDateEditor().getUiComponent()).getText());
					pst.setString(10, txtSalary.getText());
					pst.setString(11, txtEmail.getText());
		
					pst.setBytes(12, images);
					
				    pst.executeUpdate();
					System.out.println("Data Inserted Succefully");
					
					
				}
				catch(Exception notworking){
					
					System.out.println("YOU HAVE PUT THE WRONG *ID which already exist");
					txtID.setForeground(Color.red);
					
					
				}
           loadTable();	
				
				
			}
		});
		frame.getContentPane().add(insertButton);
		
		
		
		
		JButton updateButton = new JButton("UPDATE");
		updateButton.setBounds(133, 543, 97, 25);
		updateButton.setForeground(Color.WHITE);
		updateButton.setBackground(new Color(255, 140, 0));
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				 try{
					 String sql = "UPDATE employees SET ID = ?, Name = ?, Gender = ?, DOB = ?, Address = ?, PostCode = ?, NIN = ?, JobTitle = ?,  StartDate = ?, Salary = ?, Email = ?, Image =? WHERE ID = ?";
					
					 pst = connect.prepareStatement(sql);
					 pst.setString(1, txtID.getText());
					 pst.setString(2, txtName.getText());
					 String gender = comboBox.getSelectedItem().toString();
				     pst.setString(3, gender);
					 
					 pst.setString(4, ((JTextField)dateChooserdob.getDateEditor().getUiComponent()).getText());
					 pst.setString(5, txtAddress.getText());
					 pst.setString(6, txtPostCode.getText());
					 pst.setString(7, txtNiNumber.getText());
					 pst.setString(8, txtJobTitle.getText());
					 pst.setString(9, ((JTextField)dateChoosersd.getDateEditor().getUiComponent()).getText());
					 pst.setString(10, txtSalary.getText());
					 pst.setString(11, txtEmail.getText());
					 pst.setBytes(12, images);
					 pst.setString(13, txtID.getText());
					 pst.executeUpdate();
					 
							System.out.println("Updated Succefully");
						}
						catch(Exception N){
							System.out.println("Sorry , NOT Updated ");
							System.out.println(N);
						}
				
				 loadTable();
			}
		});
		frame.getContentPane().add(updateButton);
		
		JButton deleteButton = new JButton("DELETE");
		deleteButton.setBounds(238, 543, 97, 25);
		deleteButton.setBackground(new Color(220, 20, 60));
		deleteButton.setForeground(Color.WHITE);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					String sql = "DELETE FROM employees WHERE ID = " + txtID.getText();
					PreparedStatement pst = connect.prepareStatement(sql);
					pst.executeUpdate();
					System.out.println("Deleted Succefully");
				}
				catch(Exception e){
					
					System.out.println("Sorry there is some problem while DELTEING this data");
					
					
					
				}
				loadTable();
			}
		});
		frame.getContentPane().add(deleteButton);
		
		
		
		JLabel lblID = new JLabel("ID");
		lblID.setBounds(34, 131, 56, 16);
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(lblID);
		
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(34, 160, 74, 25);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(lblName);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(34, 258, 81, 32);
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(lblAddress);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(34, 490, 56, 16);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(lblEmail);
		
		JLabel lblPostCode = new JLabel("Post Code");
		lblPostCode.setBounds(34, 323, 74, 19);
		lblPostCode.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(lblPostCode);
		
		table = new JTable();
		table.setBounds(1057, 191, -238, 315);
		frame.getContentPane().add(table);
		lblImage = new JLabel("");
		lblImage.setBounds(482, 121, 193, 259);
		frame.getContentPane().add(lblImage);
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(34, 197, 56, 16);
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(lblGender);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setBounds(34, 226, 106, 30);
		lblDateOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(lblDateOfBirth);
		
		JLabel lblNiNumber = new JLabel("NI Number");
		lblNiNumber.setBounds(34, 355, 87, 25);
		lblNiNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(lblNiNumber);
		
		JLabel lblStartDate = new JLabel("Start Date");
		lblStartDate.setBounds(34, 422, 81, 25);
		lblStartDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(lblStartDate);
		
		JLabel lblJobTitle = new JLabel("Job Title");
		lblJobTitle.setBounds(34, 393, 106, 16);
		lblJobTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(lblJobTitle);
		
		JLabel lblSalary = new JLabel("Salary");
		lblSalary.setBounds(34, 460, 56, 16);
		lblSalary.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(lblSalary);
		
		txtID = new JTextField();
		txtID.setBounds(168, 129, 116, 22);
		txtID.setToolTipText("");
		frame.getContentPane().add(txtID);
		txtID.setColumns(10);
		
		JLabel label = new JLabel("*");
		label.setBounds(62, 132, 56, 16);
		frame.getContentPane().add(label);
		
		JButton loademployeedata = new JButton("LOAD THE TABLE");
		loademployeedata.setBounds(846, 45, 332, 32);
		loademployeedata.setForeground(Color.WHITE);
		loademployeedata.setBackground(Color.BLUE);
		loademployeedata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					
					
					
					 loadTable();
					
				}
				catch(Exception e1){
					
					System.out.println("Your code is shit");
				}
						
						
				}
				
				
			
		});
		frame.getContentPane().add(loademployeedata);
		
		txtName = new JTextField();
		txtName.setBounds(168, 164, 167, 22);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtAddress = new JTextField();
		txtAddress.setBounds(168, 264, 237, 42);
		frame.getContentPane().add(txtAddress);
		txtAddress.setColumns(10);
		
		txtPostCode = new JTextField();
		txtPostCode.setBounds(168, 322, 116, 22);
		frame.getContentPane().add(txtPostCode);
		txtPostCode.setColumns(10);
		
		txtNiNumber = new JTextField();
		txtNiNumber.setBounds(168, 357, 116, 22);
		frame.getContentPane().add(txtNiNumber);
		txtNiNumber.setColumns(10);
		
		txtJobTitle = new JTextField();
		txtJobTitle.setBounds(168, 391, 116, 22);
		frame.getContentPane().add(txtJobTitle);
		txtJobTitle.setColumns(10);
		
		txtSalary = new JTextField();
		txtSalary.setBounds(168, 458, 116, 22);
		frame.getContentPane().add(txtSalary);
		txtSalary.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(168, 488, 237, 22);
		frame.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		txtLookUp = new JTextField();
		txtLookUp.setBounds(34, 63, 147, 27);
		txtLookUp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
			try{
				String name1 = txtLookUp.getText();
				String sql = " SELECT * FROM employees WHERE ID = ? OR Name  LIKE '%" + name1 +"%'";
				
				
				pst = connect.prepareStatement(sql);
				pst.setString(1, txtLookUp.getText());
				
				rs = pst.executeQuery();
				 
				
				if (rs.next()){
					
					String id = rs.getString("ID");
					txtID.setText(id);
					String name = rs.getString("Name");
					txtName.setText(name);
					
					
					
					String gender = rs.getString("Gender");
					
					comboBox.setSelectedItem(gender);
					String dob = rs.getString("DOB");
					Date dateofbirth = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse(dob);
					dateChooserdob.getDateEditor().setDate(dateofbirth);
					
					String address = rs.getString("Address");
					txtAddress.setText(address);
					String postcode = rs.getString("PostCode");
					txtPostCode.setText(postcode);
					String ni = rs.getString("NIN");
					txtNiNumber.setText(ni);
					String jobtitle = rs.getString("Jobtitle");
					txtJobTitle.setText(jobtitle);
					String sd = rs.getString("StartDate");
					Date startdate = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse(sd);
					dateChoosersd.getDateEditor().setDate(startdate);
					
					String salary = rs.getString("Salary");
					txtSalary.setText(salary);
					String email = rs.getString("Email");
					txtEmail.setText(email);
					
					
				if(rs.getBytes("Image") != null){
						images = rs.getBytes("Image");
						ImageIcon img = new ImageIcon(images);
						Image im = img.getImage().getScaledInstance(200, 260, java.awt.Image.SCALE_AREA_AVERAGING);
						
					    ImageIcon rimg = new ImageIcon(im);
						lblImage.setIcon(rimg);
				}
						else{
							images = null;
						}
					
						
				}
				if (txtLookUp.getText().isEmpty()){
					txtID.setText("");
					txtName.setText("");
					comboBox.setSelectedItem("M");
					dateChooserdob.getDateEditor().setDate(null);
					txtAddress.setText("");
					txtPostCode.setText("");
					txtNiNumber.setText("");
					txtJobTitle.setText("");
					dateChoosersd.getDateEditor().setDate(null);
					txtSalary.setText("");
					txtEmail.setText("");
					txtLookUp.setText("");
					lblImage.setIcon(null);
					txtID.setForeground(Color.black);
					
				}
			}
				
			catch(Exception e){
				
			System.out.println(e);
			
			
			}
			
			}
		});
		txtLookUp.setForeground(Color.WHITE);
		txtLookUp.setBackground(new Color(0, 128, 128));
		frame.getContentPane().add(txtLookUp);
		txtLookUp.setColumns(10);
		
		JLabel lblIdLookup = new JLabel("ID and Name LOOKUP");
		lblIdLookup.setBounds(34, 21, 147, 42);
		lblIdLookup.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(lblIdLookup);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(24, 581, 97, 25);
		btnClear.setForeground(Color.RED);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtID.setText("");
				txtName.setText("");
				comboBox.setSelectedItem("M");
				dateChooserdob.getDateEditor().setDate(null);
				txtAddress.setText("");
				txtPostCode.setText("");
				txtNiNumber.setText("");
				txtJobTitle.setText("");
				dateChoosersd.getDateEditor().setDate(null);
				txtSalary.setText("");
				txtEmail.setText("");
				txtLookUp.setText("");
				lblImage.setIcon(null);
				txtID.setForeground(Color.black);
				
			}
		});
		frame.getContentPane().add(btnClear);
		
		
		
		JButton btnGetImage = new JButton("Get Image");
		btnGetImage.setBounds(514, 474, 139, 32);
		btnGetImage.setForeground(Color.WHITE);
		btnGetImage.setBackground(Color.BLUE);
		btnGetImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				try{
					String name1 = txtLookUp.getText();
					String sql = " SELECT Image FROM employees WHERE ID = ?" ;
					
					
					pst = connect.prepareStatement(sql);
					pst.setString(1, txtID.getText());
					
					
					rs = pst.executeQuery();
					
					
					 
					byte [] image;
					while (rs.next()){
						
						image = rs.getBytes("Image");
						ImageIcon img = new ImageIcon(image);
						Image im = img.getImage().getScaledInstance(200, 260, java.awt.Image.SCALE_AREA_AVERAGING);
					    ImageIcon rimg = new ImageIcon(im);
						lblImage.setIcon(rimg);
						
						
						
						
						
					}
				}
					
				catch(Exception e){
					
				System.out.println(e);
		
				
				}
			}

			
		});
		frame.getContentPane().add(btnGetImage);
		
		JButton btnAttachImage = new JButton("Attach Image");
		btnAttachImage.setBounds(512, 518, 141, 32);
		btnAttachImage.setForeground(Color.WHITE);
		btnAttachImage.setBackground(Color.BLUE);
		btnAttachImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				File file = chooser.getSelectedFile();
				
				filename = file.getAbsolutePath();
				try{
					
					File image = new File (filename);
					FileInputStream fis = new FileInputStream(image);
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					byte[] buf = new byte[1024];
					for (int readNum; (readNum =fis.read(buf)) !=-1; ){
						bos.write(buf, 0, readNum);
			
		}
					images= bos.toByteArray();
					
					System.out.println("it worked");
					
					ImageIcon img = new ImageIcon(images);
					Image im = img.getImage().getScaledInstance(200, 270, java.awt.Image.SCALE_AREA_AVERAGING);
				    ImageIcon rimg = new ImageIcon(im);
					lblImage.setIcon(rimg);
					
				}
				catch(Exception e){
					System.out.println("not worked");
				}
				
			}
		});
		frame.getContentPane().add(btnAttachImage);
		
		JButton btnNext = new JButton("next");
		btnNext.setBounds(603, 406, 97, 25);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
				if (BindList().size() >= index){
					
			index++;
				ShowPosInfo(index);
			}
				
			else{
					System.out.println("there is nothign ahead");
				}
			}
			
			catch(Exception sql){
				sql.printStackTrace();
			}
			
			}
		});
		frame.getContentPane().add(btnNext);
		
		JButton btnPrevious = new JButton("previous");
		btnPrevious.setBounds(465, 406, 97, 25);
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					index--;
					ShowPosInfo(index);
					
			}
		});
		frame.getContentPane().add(btnPrevious);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(751, 131, 462, 471);
		frame.getContentPane().add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		
		
		
		
		
		
		
		
	}
	public  ArrayList<Employee> BindList(){
        try{
           
            String sql = ("select ID, Name, Gender, DOB, Address,PostCode, NIN,JobTitle,StartDate, Salary, Email from employees");
            
            pst = connect.prepareStatement(sql);
			 rs = pst.executeQuery();
            ArrayList<Employee> list = new ArrayList<Employee>();
            
            Person per = new Employee();
            Employee emp = (Employee)per;
            
           while(rs.next())
           {
        	   String id = rs.getString("ID");
				
				String name = rs.getString("Name");
				
				String g = rs.getString("Gender"); 
				
				char n = 'n';
				for (int j = 0; j<g.length(); j++){
				       n = g.charAt(j);
					
				}
				
				char gender = n;
				
				
				
				String dob = rs.getString("DOB");
				
				
				String address = rs.getString("Address");
			
				String postcode = rs.getString("PostCode");
				
				String ni = rs.getString("NIN");
				
				String jobtitle = rs.getString("Jobtitle");
				
				String sd = rs.getString("StartDate");
				
				
				String salary = rs.getString("Salary");
				
				String email = rs.getString("Email");
				
				emp = new Employee(id, name,gender,dob,address,postcode,ni,jobtitle,sd,salary,email);
               
            
                list.add(emp);
            }
           
            return list;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
	public void ShowPosInfo(int index){
	
		
		
		txtID.setText("");
		txtName.setText("");
		comboBox.setSelectedItem("M");
		dateChooserdob.getDateEditor().setDate(null);
		txtAddress.setText("");
		txtPostCode.setText("");
		txtNiNumber.setText("");
		txtJobTitle.setText("");
		dateChoosersd.getDateEditor().setDate(null);
		txtSalary.setText("");
		txtEmail.setText("");
		txtLookUp.setText("");
		lblImage.setIcon(null);
		txtID.setForeground(Color.black);
		
		
		
		
		
		try{	 
		
       
	
        
		txtID.setText(BindList().get(index).getId());
		
		txtName.setText(BindList().get(index).getName());
		
	    char gender = BindList().get(index).getGender();
	    
	    String s = String.valueOf(gender);
		
		
        comboBox.setSelectedItem(s);
		
		String dob = BindList().get(index).getDob();
		Date dateofbirth = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse(dob);
		dateChooserdob.getDateEditor().setDate(dateofbirth);
		
		txtAddress.setText(BindList().get(index).getAddress());
		
		txtPostCode.setText(BindList().get(index).getPostcode());
		
		txtNiNumber.setText(BindList().get(index).getNatInscNo());
		
		
		txtJobTitle.setText(BindList().get(index).getTitle());
		
		String sd = BindList().get(index).getStartDate();
		Date startdate = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse(sd);
		dateChoosersd.getDateEditor().setDate(startdate);
		
		
		txtSalary.setText(BindList().get(index).getSalray());
		
		txtEmail.setText(BindList().get(index).getEmail());
		
		
	}
	catch(Exception e){
		e.printStackTrace();
	}
		
		try {
			
			String sql = " SELECT Image FROM employees WHERE ID = ?" ;
			pst = connect.prepareStatement(sql);
			pst.setString(1, txtID.getText());
			
			
			rs = pst.executeQuery();
			
			
			 
			byte image[] = null; 
			
			if (rs.next()){
				
				image = rs.getBytes("Image");
				ImageIcon img = new ImageIcon(image);
				Image im = img.getImage().getScaledInstance(200, 260, java.awt.Image.SCALE_AREA_AVERAGING);
			    ImageIcon rimg = new ImageIcon(im);
			    lblImage.setIcon(rimg);
			   
			
		}}
			catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	
	}	
	
}