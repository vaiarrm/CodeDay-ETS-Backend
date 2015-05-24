package MatchMaker;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Database.JDBCConnection;

public class createAppointment {

	public void createAppt(AppointmentDetails apptDetails){
		
		JDBCConnection jcon = new JDBCConnection();
		Connection conn = jcon.connectionManager();
		ResultSet rs 	= null;
		Statement stmt  = null;
		String maxApptID		= "SELECT (MAX(APPOINTMENT_ID) + 1) FROM APPOINTMENT_DETAILS";
		
		
		try {
			rs = stmt.executeQuery(maxApptID);
			rs.next();
			int ApptID  	= rs.getInt(1) ;
			String driverIdQuery = "SELECT DRIVER_ID FROM DRIVER WHERE DRIVER_ZIP_CODE =  "
									+ "\'"+ apptDetails.getApptResidentZipCode() + "\'" ;
			rs = stmt.executeQuery(driverIdQuery);
			rs.next();
			int DriverId 	= rs.getInt(1) ;
			int resId;
			String residentIdQuery = "SELECT RESIDENT_ID FROM RESIDENT WHERE "
									+ " RESIDENT_PHONE = " 
									+ "\'"+ apptDetails.getApptResidentPhoneNumber() + "\'"
									+ " RESIDENT_ZIP_CODE = "
									+ "\'" + apptDetails.getApptResidentZipCode() + "\'";
			try {
				rs = stmt.executeQuery(residentIdQuery);
				rs.next();
				resId  	= rs.getInt(1) ;
			} catch (SQLException e) {
				//e.printStackTrace();
				String maxResID		= "SELECT (MAX(RESIDENT_ID) + 1) FROM RESIDENT";
				ResultSet rs1 	= stmt.executeQuery(maxResID);
				rs1.next();
				resId  	= rs1.getInt(1) ;
				
				String residentInsertQuery = 	"INSERT INTO RESIDENT VALUES ( " 
						+	resId 		+	 " , " 
						+ "\'" + apptDetails.getApptResidentAddress() 	+ "\'" +  " , " 
						+ "\'" + apptDetails.getApptResidentAddrCity() 	+ "\'" +  " , " 
						+ "\'" + apptDetails.getApptResidentAddrState() 	+ "\'" +  " , " 
						+ "\'" + apptDetails.getApptResidentZipCode() 	+ "\'" +  " , " 
						+ "\'" + apptDetails.getApptResidentPhoneNumber() 	+ "\'" +  " , "
						+ "\'" + apptDetails.getApptResidentLastName()	+ "\'" +  " , "
						+ "\'" + apptDetails.getApptResidentFirstName()	+ "\'" +  " , "
						+ "\'" + apptDetails.getApptResidentEmergencyContactInfo()	+ "\'" +  " , " 
						+ " )";
			}
			
									
			String insertQuery = 	"INSERT INTO DRIVER VALUES ( "
					+  	ApptID +  " , " 
					+	DriverId +	 " , " 
					+	resId 		+	 " , " 
					+ "\'" + apptDetails.getApptResidentAddress() 	+ "\'" +  " , " 
					+ "\'" + apptDetails.getApptResidentAddrCity() 	+ "\'" +  " , " 
					+ "\'" + apptDetails.getApptResidentAddrState() 	+ "\'" +  " , " 
					+ "\'" + apptDetails.getApptResidentZipCode() 	+ "\'" +  " , " 
					+ "\'" + apptDetails.getApptResidentApptAddress()	+ "\'" +  " , "
					+ "\'" + apptDetails.getApptResidentapptAddrCity()	+ "\'" +  " , "
					+ "\'" + apptDetails.getApptResidentapptAddrState()	+ "\'" +  " , "
					+ "\'" + apptDetails.getApptResidentApptZipCode()	+ "\'" +  " , " 
					+ " )";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	

}
