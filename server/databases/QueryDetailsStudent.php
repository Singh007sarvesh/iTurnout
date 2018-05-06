<?php
	
	include('connection.php');
	    	$flag  = array();
        
if($_SERVER['REQUEST_METHOD']=='POST')
	{
		if(isset($_POST['userId']))
			{
			    $tid=$_POST['userId'];
    	        $sql = "select distinct(studentid),studentname from student as s,course as c,enrollment as e where c.ctid='$tid' and c.courseid=e.cid and e.sid=s.studentid";
    	        $res= mysqli_query($con,$sql);
    	         if($res)
    	         {
    	            while($row=mysqli_fetch_array($res))
    	            {
    	                $course  = array();
    	              //  echo $row['c'];
    	                $course['data'] = $row['studentname'];
    	                $course['data1'] = $row['studentid'];
    	                $flag[]=$course;
    	            }
    	           
    	         }
			}
	}
	
	      //   $flag['data']="DBMS";
	    
			echo json_encode(array('flag'=>$flag));
		//	echo json_encode($flag);

?>