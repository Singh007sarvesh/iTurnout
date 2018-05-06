<?php
	
	include('connection.php');
	$response  = array();
	if($_SERVER['REQUEST_METHOD']=='POST')
	{
		if(isset($_POST['userid'])and
		    isset($_POST['changeold']) and
			isset($_POST['changenew'])and
			isset($_POST['changeconfirm']))
			{
			    $userid = $_POST['userid'];
    			$cold = $_POST['changeold'];
    			$cnew = $_POST['changenew'];
    			$cconfirm=$_POST['changeconfirm'];
    			if($cnew==$cconfirm)
    			{
    			    $sql = "SELECT * FROM student where studentid = '$userid' and password = '$cold'";
    			    $sql1 = "SELECT * FROM teacher where teacherid = '$userid' and password = '$cold'";
    			    $result =mysqli_query($con,$sql);
    			    $result1=mysqli_query($con,$sql1);
    			    $num = $result->num_rows;
    			    $num1 = $result1->num_rows;
    			    if($num>0 || $num1>0)
    			    {
    			        $change = "UPDATE student SET password='$cnew' WHERE studentid='$userid'";
    			        $change2 = "UPDATE teacher SET password='$cnew' WHERE teacherid='$userid'";
    			        $change1=mysqli_query($con,$change);
    			        $change3=mysqli_query($con,$change2);
    			        if($change1 || $change3)
            			{
            			   
                			$response['message'] = "Successfully Changed";
            			}
        			    else
        			    {
        			   
        				$response['message'] = "Error occured..!..Failed";
        			    }
    			    }
    			    else
    			    {
    			        $response['message'] = "Incorrect credentials";
    			    }
    			    
    			    //$change = "UPDATE student SET password='$cnew' WHERE studentid='$userid'";
    			   // $res1="UPDATE teacher SET password='$cnew' WHERE teacherid='$userid' and password='$cold'";
//$change1=mysqli_query($con,$change);
        		   // $sql1=mysqli_query($con,$res1);
        		   
        			
    			}
    			else
    			{
    			    $response['message'] = "Password not matching";
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
