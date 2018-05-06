<?php
	
	include('connection.php');
	$response  = array();
	
	if($_SERVER['REQUEST_METHOD']=='POST')
	{
	
			    $sid = $_POST['studentId'];
    			$cid = $_POST['oldCourseId'];
    			$nid = $_POST['newCourseId'];
    			$res = "SELECT * FROM enrollment  where cid = '$cid' and sid = '$sid' ";
    			$sql=mysqli_query($con,$res);
    			$num = $sql->num_rows;
    			if($num>0)
    			{
    			    $change = "UPDATE enrollment SET cid='$nid' WHERE sid='$sid' ";
    			    $updateResult=mysqli_query($con,$change);
    			    if($updateResult)
    				$response['message'] = "Successfully Updated";
    				else
    				$response['message'] = "Error occured..!..Failed";
    				
    			}
    			else
    			{
    				
    				$response['message'] = "Please Check UserId and CourseId";
    			}
		    }
    		
	else
	{
		
		$response['message'] = "Invalid Request";
	}
	echo json_encode($response);
?>
