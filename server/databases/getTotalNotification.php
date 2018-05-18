<?php
	
	include('connection.php');
	    	$flag  = array();
        
if($_SERVER['REQUEST_METHOD']=='POST')
	{
		if(isset($_POST['studentId']))
			{
			    $sid=$_POST['studentId'];
    	        $sql="select count(sid) as a FROM Notification WHERE sid='M150054CA' and status='1' ";
    	        $res= mysqli_query($con,$sql);
    	         if($res)
    	         {
    	            while($row=mysqli_fetch_array($res))
    	            {
    	                
    	                $flag["message"]=$row['a'];
    	            }
    	           
    	         }
			}
	}
	echo json_encode($flag);

?>