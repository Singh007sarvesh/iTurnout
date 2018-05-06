<?php
	
	include('connection.php');
	    	$flag  = array();
if($_SERVER['REQUEST_METHOD']=='GET')
	{
		  
		   $sql = "select studentname,studentid,date from student";
    	   $res= mysqli_query($con,$sql);
    	   if($res)
    	   {
        	    while($row=mysqli_fetch_array($res))
        	    {
        	        $course  = array();
        	        $course['studentName'] = $row['studentname'];
        	        $course['studentId'] = $row['studentid'];
        	        $course['sDate'] = $row['date'];
        	        $flag[]=$course;
        	   }
    	  }
	}
	echo json_encode(array('flag'=>$flag));
?>