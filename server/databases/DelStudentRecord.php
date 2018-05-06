<?php
	
	include('connection.php');
	$response  = array();
    if($_SERVER['REQUEST_METHOD']=='POST')
    {
    	$size= $_POST['size'];
    	$student=array();
        for($i = 0; $i < $size; $i++)
        {
	            $t="studentId".$i;
	            $student[] = $_POST["$t"];
        }
       
        foreach ($student as $k) 
        {
            $res1 = "Delete from student where studentid='$k'";
    		$res = mysqli_query($con,$res1);
	    }
    	if($res)
    	{
    	    $response['message'] = "Deleted";
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
