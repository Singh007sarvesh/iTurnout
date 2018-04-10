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
    			$sql2="select studentid from student where studentid='$did' ";
		        $res2=mysqli_query($con,$sql2);
		        $num=$res2->num_rows;
		        if($num<1)
		        {
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
				    $response['message'] = "User already exist";
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
