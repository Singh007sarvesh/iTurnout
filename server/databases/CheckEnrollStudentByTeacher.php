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
    	                $course['data'] = $row['c'];
    	                $course['data1'] = $row['sid'];
    	                $check=$row['sid'];
    		            $notify="select count(sid) as a from attendance where sid='$check' and presence='1' and cid='$cid' ";
                        $notify1="select count(sid) as a from attendance where sid='$check' and cid='$cid'";
                        $notifyres=mysqli_query($con,$notify);
                        $notifyres1=mysqli_query($con,$notify1);
                        $pre=(int)0;
                        $total=(int)0;
                        $total1=(int)0;
                        if($notifyres || $notifyres1 )
                        {
                	           while($row5=mysqli_fetch_array($notifyres))
                	           {
                	            $total =(int) $row5['a'];
                	           }
                	           while($row6=mysqli_fetch_array($notifyres1))
                	           {
                	               $total1 =(int) $row6['a'];
                	           }
                	           $pre=(int)($total*100);
                	           try
                	           {
                	               if($total1==0);
                	               else
                	               $pre=(int)($pre/$total1);
                	               
                	           }
                	           catch(Exception $e)
                	           {
                	               echo $e;
                	           }
                         }
                        $course['total']=  (string) $pre;
    	                $flag[]=$course;
    	            }
    	           
    	         }
			}
	}
	
	      //   $flag['data']="DBMS";
	    
			echo json_encode(array('flag'=>$flag));
		//	echo json_encode($flag);

?>