package com.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {
	
	
	
	
	/* Function Name:createResultFolder
	 * 
	 * Description: This method will call before the test execution .It will verify wheter the results folder exist in the current test directory
	 * 
	 * Author: Prathap
	 * 
	 * Date:10-Feb-2018
	 * 
	 * Reviewer:
	 * 
	 * Comments:
	 */
	
	public static String createResultFolder()
	{
		//Get the system test directory path
		String path="";
		String crntdir=System.getProperty("user.dir");
		
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-YYYY");

		Date d=new Date();
		
		//Format the date into dd-MM-YYYY
		System.out.println(sdf.format(d));
		System.out.println(crntdir);


		File f=new File(crntdir+"\\Results\\"+sdf.format(d));

		//Verify and create Result Folder
		if(! f.exists())
		{
			
			f.mkdirs();
			path=crntdir+"\\Results\\"+sdf.format(d);
		}
		else
		{
			path=crntdir+"\\Results\\"+sdf.format(d);
		}
		

return path;

		
	}
	

}
