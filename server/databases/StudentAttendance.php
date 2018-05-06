<?php
	
	include('connection.php');
	    	$flag  = array();
	  
        
if($_SERVER['REQUEST_METHOD']=='POST')
	{ 
	    if(isset($_POST['userId'])and
		        isset($_POST['subjectId']))
			{
			    $sid=$_POST['userId'];
			    $subjectid=$_POST['subjectId'];
    	        $sql1="select count(sid) as a from attendance where sid='$sid' and cid='$subjectid' and 	presence=1";
    	        $sql2="select count(sid) as a from attendance where sid='$sid' and cid='$subjectid' and 	presence=0";
    	        $sql="select count(sid) as a from attendance where sid='$sid' and cid='$subjectid'";
			    $res=mysqli_query($con,$sql);
			    $res1=mysqli_query($con,$sql1);
			    $res2=mysqli_query($con,$sql2);
    	         if($res )
    	         {
    	             $course  = array();
    	            while($row=mysqli_fetch_array($res))
    	            {
    	               $course['message'] = $row['a'];
    	                //$flag[]=$course;
    	            }
    	             while($row=mysqli_fetch_array($res1))
    	            {
    	               $course['message1'] = $row['a'];
    	               // $flag[]=$course;
    	            }
    	             while($row=mysqli_fetch_array($res2))
    	            {
    	               $course['message2'] = $row['a'];
    	               // $flag[]=$course;
    	            }
    	            $flag[]=$course;
    	           
    	         }
			}
	}
			echo json_encode(array('flag'=>$flag));
?>
