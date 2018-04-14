<?php
	
	include('connection.php');
	    	$flag  = array();
        
if($_SERVER['REQUEST_METHOD']=='POST')
	{
		if(isset($_POST['courseid']))
			{
			    $cid=$_POST['courseid'];
    	        $sql = "select studentname as c from student as s,enrollment as e where e.cid='$cid' and e.sid=s.studentid ";
    	        $res= mysqli_query($con,$sql);
    	         if($res)
    	         {
    	            while($row=mysqli_fetch_array($res))
    	            {
    	                $course  = array();
    	              //  echo $row['c'];
    	                $course['data'] = $row['c'];
    	                $flag[]=$course;
    	            }
    	           
    	         }
			}
	}
	
	      //   $flag['data']="DBMS";
	    
			echo json_encode(array('flag'=>$flag));
		//	echo json_encode($flag);

?>