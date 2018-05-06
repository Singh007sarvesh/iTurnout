<?php
	
	include('connection.php');
	    	$flag  = array();
        
if($_SERVER['REQUEST_METHOD']=='POST')
	{
		if(isset($_POST['studentId']))
			{
			    $sid=$_POST['studentId'];
    	        $sql="select subjectname,notificationid,date FROM  Notification WHERE sid='$sid' and status='1'";
    	        $res= mysqli_query($con,$sql);
    	         if($res)
    	         {
    	            while($row=mysqli_fetch_array($res))
    	            {
    	                $course  = array();
    	                $course['subjectName'] = $row['subjectname'];
    	                $course['nid'] = $row['notificationid'];
    	                $course['date'] = $row['date'];
    	                $flag[]=$course;
    	            }
    	           
    	         }
			}
	}
	echo json_encode(array('flag'=>$flag));

?>