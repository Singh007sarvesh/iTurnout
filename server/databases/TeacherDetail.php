<?php
	
	include('connection.php');
	    	$flag  = array();
        
if($_SERVER['REQUEST_METHOD']=='POST')
	{
		if(isset($_POST['userId']))
			{
			    $sid=$_POST['userId'];
    	        $sql = "select DISTINCT(teacherid),teachername from teacher as t,enrollment as e,course as c WHERE e.sid='$sid' and e.cid =c.courseid and c.ctid=t.teacherid";
    	        $res= mysqli_query($con,$sql);
    	         if($res)
    	         {
    	            while($row=mysqli_fetch_array($res))
    	            {
    	                $course  = array();
    	              //  echo $row['c'];
    	                $course['teacherId'] = $row['teacherid'];
    	                $course['teacherName'] = $row['teachername'];
    	                $flag[]=$course;
    	            }
    	           
    	         }
			}
	}
	
	      //   $flag['data']="DBMS";
	    
			echo json_encode(array('flag'=>$flag));
		//	echo json_encode($flag);

?>