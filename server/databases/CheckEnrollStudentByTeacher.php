<?php
	
	include('connection.php');
	    	$flag  = array();
        
if($_SERVER['REQUEST_METHOD']=='POST')
	{
		if(isset($_POST['courseId']))
			{
			    $cid=$_POST['courseId'];
    	        $sql = "select studentname as c,studentid as sid from student as s,enrollment as e where e.cid='$cid' and e.sid=s.studentid ";
    	        $res= mysqli_query($con,$sql);
    	         if($res)
    	         {
    	            while($row=mysqli_fetch_array($res))
    	            {
    	                $course  = array();
    	              //  echo $row['c'];
    	                $course['studentName'] = $row['c'];
    	                $course['studentId'] = $row['sid'];
    	                $flag[]=$course;
    	            }
    	           
    	         }
			}
	}
	
	      //   $flag['data']="DBMS";
	    
			echo json_encode(array('StudentData'=>$flag));
		//	echo json_encode($flag);

?>