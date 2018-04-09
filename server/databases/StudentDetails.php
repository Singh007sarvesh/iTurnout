<?php
	
	include('connection.php');
	$response  = array();
	
	if($_SERVER['REQUEST_METHOD']=='POST')
	{
		if(isset($_POST['did']) and
			isset($_POST['dname']) and
			isset($_POST['dpassword']))
			{
    			$did = $_POST['did'];
    			$dname = $_POST['dname'];
    			$ddate = date("Y/m/d");
    			$dpasswd=$_POST['dpassword'];
    			$res1 = "INSERT INTO student(studentid, studentname, date,password) VALUES('$did','$dname', '$ddate','$dpasswd')";
    			$sql=mysqli_query($con,$res1);
    			if($sql)
    			{
    			  
    				
    				$response['message'] = "Successfully Registered!";
    			}
    			else
    			{
    			
    				$response['message'] = "Error occured..!..Failed";
    			}
		    }
    		else
    		{
    			
    			$response['message'] = "less values";
    		}
	}
	else
	{
		
		$response['message'] = "Invalid Request";
	}
	echo json_encode($response);
?>
