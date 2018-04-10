<?php
	
	include('connection.php');
	$response  = array();
	
	if($_SERVER['REQUEST_METHOD']=='POST')
	{
		if(isset($_POST['cid']) and
			isset($_POST['cname'])and
			isset($_POST['ctid']))
			{
    			$cid = $_POST['cid'];
    			$cname = $_POST['cname'];
    			$ctid=$_POST['ctid'];
    			$check="select courseid from course where courseid='$cid' ";
    			$res2=mysqli_query($con,$check);
    			$num=$res2->num_rows;
    			if($num<1)
    			{
        			$res1 = "INSERT INTO course(courseid, coursename,ctid) VALUES('$cid','$cname','$ctid')";
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
    			    $response['message'] = "Subject already exist";
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
