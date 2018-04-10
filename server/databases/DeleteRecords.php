<?php
	
	include('connection.php');
	$response  = array();
	if($_SERVER['REQUEST_METHOD']=='POST')
	{
	        $sql="delete from Notification";
	        $sql1="delete from query";
	        $sql2="delete from attendance";
	        $sql3="delete from course";
	        $sql4="delete from enrollment";
	        
	          $res= mysqli_query($con,$sql);
	          $res1=mysqli_query($con,$sql1);
	          $res2=mysqli_query($con,$sql2);
	          $res3=mysqli_query($con,$sql3);
	          $res4=mysqli_query($con,$sql4);
    			if($res && $res1 && $res2 && $res3 && $res4)
    			{
    			   	$response['error'] = false;
    				$response['message'] = "Successfully Deleted";
    			}
    			else
    			{
    			    $response['error'] = true;
    				$response['message'] = "Error occured..!..Failed";
    			}
	}
	else
	{
	
		$response['message'] = "Invalid Request";
	}
	echo json_encode($response);
?>
