<?php
	
	include('connection.php');
	$response  = array();
	
	if($_SERVER['REQUEST_METHOD']=='POST')
	{
		if(isset($_POST['cid']) and
			isset($_POST['sid']))
			{
    			$cid = $_POST['cid'];
    			$sid = $_POST['sid'];
    			$res = "INSERT INTO enrollment(cid,sid) VALUES ('$cid' , '$sid')";
    			$sql=mysqli_query($con,$res);
    			if($sql)
    			{
    				$response['message'] = "Successfully Enrolled";
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
