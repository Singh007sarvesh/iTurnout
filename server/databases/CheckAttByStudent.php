<?php
	
	include('connection.php');
	    	$flag  = array();
	  
        
if($_SERVER['REQUEST_METHOD']=='POST')
	{
		if(isset($_POST['userid']))
			{
			    $sid=$_POST['userid'];
    	        $sql = "select coursename ,courseid as A from course as c,enrollment as e where e.sid='$sid' and e.cid=c.courseid ";
    	        $res= mysqli_query($con,$sql);
    	         if($res)
    	         {
    	            while($row=mysqli_fetch_array($res))
    	            {
    	                $course  = array();
    	   
    	              //  echo $row['c'];
    	               $course['subjectName'] = $row['coursename'];
    	                
    	                $course['subjectId'] = $row['A'];
    	                $flag[]=$course;
    	               
    	            }
    	           
    	         }
			}
	}
			echo json_encode(array('flag'=>$flag));
?>
