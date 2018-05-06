<?php
	
	include('connection.php');
	$response  = array();
    if($_SERVER['REQUEST_METHOD']=='POST')
    {
    	$size= $_POST['size'];
    	$teacher=array();
        for($i = 0; $i < $size; $i++)
        {
	            $t="userId".$i;
	            $teacher[] = $_POST["$t"];
        }
       
        foreach ($teacher as $k) 
        {
            $res1 = "Delete from teacher where teacherid='$k'";
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
