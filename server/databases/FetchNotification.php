<?php
	
	include('connection.php');
	    	$flag  = array();
if($_SERVER['REQUEST_METHOD']=='POST')
	{
		
			    $nid=$_POST['nid'];
    	        $sql = "select Content FROM  Notification as n  WHERE n.notificationid='$nid'";
    	        $res= mysqli_query($con,$sql);
    	         if($res)
    	         {
    	            while($row=mysqli_fetch_array($res))
    	            {
    	                $flag['content']=$row['Content'];
    	            }
    	         }
    	         $sql1="update Notification set status='0' where notificationid='$nid'";
    	         $res1=mysqli_query($con,$sql1);
	}
	echo json_encode($flag);
?>