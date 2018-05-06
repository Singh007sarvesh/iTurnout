<?php
	
	include('connection.php');
	    	$flag  = array();
if($_SERVER['REQUEST_METHOD']=='GET')
	{
		  
		   $sql = "select teachername,teacherid,date from teacher";
    	        $res= mysqli_query($con,$sql);
    	         if($res)
    	         {
    	            while($row=mysqli_fetch_array($res))
    	            {
    	                $course  = array();
    	               $course['teacherName'] = $row['teachername'];
    	               $course['teacherId'] = $row['teacherid'];
    	              $course['date'] = $row['date'];
    	                $flag[]=$course;
    	               
    	            }
    	           
    	         }
	}
	echo json_encode(array('flag'=>$flag));
?>