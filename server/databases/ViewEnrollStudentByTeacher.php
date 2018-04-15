<?php
	
	include('connection.php');
	    	$flag  = array();
        
if($_SERVER['REQUEST_METHOD']=='POST')
	{
		if(isset($_POST['userid']))
			{
			    $tid=$_POST['userid'];
			   
    	        $sql = "select coursename as c,courseid as id from course where ctid='$tid'";
    	        $res= mysqli_query($con,$sql);
    	         if($res)
    	         {
    	            while($row=mysqli_fetch_array($res))
    	            {
    	                $course  = array();
    	              //  echo $row['c'];
    	                $course['data'] = $row['c'];
    	                $course['data1'] = $row['id'];
    	                $flag[]=$course;
    	            }
    	           
    	         }
			}
	}
	
	      //   $flag['data']="DBMS";
	    
			echo json_encode(array('flag'=>$flag));
		//	echo json_encode($flag);

?>