<?php
	
	include('connection.php');
	$response  = array();
    if($_SERVER['REQUEST_METHOD']=='POST')
    {
        $ddate = date("Y/m/d");
    	$cid = $_POST['subjectId'];
    	$presence = "1";
    	$size= $_POST['size'];
    	$absize=$_POST['abSize'];
    //	$subjectid=$_POST['stuAbsent'];
    	$student=array();
    	$astudent=array();
        
        for($i = 0; $i < $size; $i++)
        {
	            $t="studentId".$i;
	            $student[] = $_POST["$t"];
        }
        for($x=0;$x<$absize;$x++)
        {
            $y="stuAbsent".$x;
            $astudent[]=$_POST["$y"];
        }
        foreach ($student as $k) 
        {
             $presence="1";
            foreach($astudent as $k1)
            {
                if($k==$k1)
                {
                   $presence="0";
                   break;
                }
            }
        
            $res1 = "INSERT INTO attendance(cid,sid,date,presence)VALUES('$cid','$k', '$ddate','$presence')";
    		$res = mysqli_query($con,$res1);
    		 $notify="select count(sid) as a from attendance where sid='$k' and presence='1' and cid='$cid' ";
             $notify1="select count(sid) as a from attendance where sid='$k' and cid='$cid'";
             $notifyres=mysqli_query($con,$notify);
             $notifyres1=mysqli_query($con,$notify1);
             $pre=0;
             $total=0;
             $total1=0;
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
    	            $pre=($total*100);
    	            try
    	            {
    	                if($total1==0);
    	                else
    	                $pre=($pre/$total1);
    	               
    	            }
    	            catch(Exception $e)
    	            {
    	                echo $e;
    	            }
    	            if($pre<81)
    	            {
    	                $subname="select coursename from course where courseid ='$cid'";
    	                $sub=mysqli_query($con,$subname);
    	                $subjectname="";
    	                if($sub)
    	                {
    	                    while($row10=mysqli_fetch_array($sub))
    	                    {
    	                         $subjectname=$row10['coursename'];
    	                    }
                           
    	                }
    	                $str="Your attendance has been sorted please check your Attendance and attend your class  ";
    	                $noo="insert into Notification(notificationid,Content,date,sid,status,subjectname)VALUES(null,'$str','$ddate','$k','1','$subjectname')";
    	                $nono=mysqli_query($con,$noo);
    	            }
             }
	    }
    	if($res)
    	{
    	    $response['message'] = "Done";
    	}
    	else
    	{
    	    $response['message'] = "Error occured..!..Failed";
    	}
	}
	else
	{
		
		$response['message'] = "Invalid Request";
	}
	 echo json_encode($response);
	/*$studentlist=array();
	 for($i1 = 0; $i1 <$size; $i1++)
        {
	            $t4="studentId".$i1;
	            $studentlist[] = $_POST["$t4"];
        }
        foreach ($studentlist as $k3)
         {
             $notify="select count(sid) as a from attendance where sid='$k3' and presence='1' ";
             $notify1="select count(sid) as a from attendance where sid='$k3' and presence='0' ";
             $notifyres=mysqli_query($con,$notify);
             $notifyres1=mysqli_query($con,$notify1);
             if($notifyres and $notifyres1 )
             {
    	            while($row5=mysqli_fetch_array($notifyres))
    	            {
    	               $total = $row5['a'];
    	                //$flag[]=$course;
    	                
    	            }
    	            while($row6=mysqli_fetch_array($notifyres1))
    	            {
    	               $total1 = $row6['a'];
    	                //$flag[]=$course;
    	                
    	            }
    	            $pre=($total1*100);
    	            try
    	            {
    	                if($total==0);
    	                else
    	                $pre=($pre/$total);
    	               
    	            }
    	            catch(Exception $e)
    	            {
    	                echo $e;
    	            }
    	            if($pre<81)
    	            {
    	                $no="insert into Notification(notificationid,Content,date,sid,status,subjectname)VALUES(null,'Your attendance has been sorted please check your Attendance and attend your class','$ddate','$k3','1','$cid')";
    	                $non=mysqli_query($con,$no);
    	            }
             }
         }*/
        
?>
