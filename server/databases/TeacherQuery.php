<?php
	
	include('connection.php');
	$response  = array();
	if($_SERVER['REQUEST_METHOD']=='POST')
	{
		
    			$content = $_POST['content'];
    			$sender=$_POST['senderId'];
    			$receiver=$_POST['receiverId'];
    			$date=date("Y/m/d");
    			$res = "INSERT INTO query(id,tid,sid,content,qdate)VALUES(NULL,'$sender','$receiver','$content','$date')";
    			$sql=mysqli_query($con,$res);
    			if($sql)
    			{
    				$response['message'] = "Successfully";
    			}
    			else
    			{
    				
    				$response['message'] = "Error occured..!..Failed";
    			}
	}
	else
	{
		
		$response['message'] = "Invalid Request";
	}
	echo json_encode($response);
?>
