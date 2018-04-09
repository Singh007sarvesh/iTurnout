<?php
	
	include('connection.php');
	$response  = array();
	
	if($_SERVER['REQUEST_METHOD']=='POST')
	{
		if(isset($_POST['tid']) and
			isset($_POST['tname']) and
			isset($_POST['password']))
			{
    			$tid = $_POST['tid'];
    			$tname = $_POST['tname'];
    			$rdate = date("Y/m/d");
    			$passwd=$_POST['password'];
    			$res1 = "INSERT INTO teacher(teacherid, teachername, date,password) VALUES('$tid','$tname', '$rdate','$passwd')";
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
