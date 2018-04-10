<?php
	
	include('connection.php');
	$response  = array();
	
	if($_SERVER['REQUEST_METHOD']=='POST')
	{
		if(isset($_POST['changeold']) and
			isset($_POST['changenew'])and
			isset($_POST['changeconfirm']))
			{
    			$cold = $_POST['changeold'];
    			$cnew = $_POST['changenew'];
    			$cconfirm=$_POST['changeconfirm'];
    			if($cnew==$cconfirm){
    			    $res = "update student set password = '$cnew' where password = '$cold' ";
    			    $res1 = "update teacher set password = '$cnew' where password = '$cold' ";
    			   }
    			$sql=mysqli_query($con,$res);
    			$sql1 =mysqli_query($con,$res1);
    			if($sql || $sql1)
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
    			
    			$response['message'] = "less values";
    		}
	}
	else
	{
		$response['message'] = "Invalid Request";
	}
	echo json_encode($response);
?>
