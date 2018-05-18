<?php
	
	include('connection.php');
	    	$flag  = array();
        
if($_SERVER['REQUEST_METHOD']=='POST')
	{
		if(isset($_POST['studentId']))
			{
			    $userId=$_POST['studentId'];
                $sql = "SELECT * FROM query WHERE tid='$userId' or sid='$userId' order by id DESC";
                $result=mysqli_query($con,$sql);
    	         if($result)
    	         {
    	            while($row=mysqli_fetch_array($result))
    	            {
    	                $course  = array();
    	                $sender = $row['tid'];
    	                $receiver = $row['sid'];
    	                $course['date'] = $row['qdate'];
    	                $course['id'] = $row['id'];
                       // $course['senderId']=$row['tid'];
                        $sql1="select studentname,studentid from student where studentid='$sender' or studentid='$receiver'";
                        $res1=mysqli_query($con,$sql1);
                        $num1 = $res1->num_rows;
                        if($num1>0)
                        {
                            $row1=mysqli_fetch_array($res1);
                            if($sender==$row1['studentid'])
                            {
                                $course['senderId'] = $row1['studentid'];
                                $course['senderName'] = $row1['studentname'];
                            }
                           /* else
                            {
                                $course['$receiverId'] = $row1['studentid'];
                                $course['$receiverName'] = $row1['studentname'];
                            }*/
                            //$course['$userId1'] = $row1['studentid'];
                            //$course['$userName1'] = $row1['studentname'];
                        }

                        $sql2="select teachername,teacherid from teacher where teacherid='$sender' or teacherid='$receiver'";
                        $res2=mysqli_query($con,$sql2);
                        $num2 = $res2->num_rows;
                        if($num2>0)
                        {
                            $row2=mysqli_fetch_array($res2);
                            if($sender==$row2['teacherid'])
                            {
                                $course['senderId'] = $row2['teacherid'];
                                $course['senderName'] = $row2['teachername'];
                            }
                          /*  else
                            {
                                $course['$receiverId'] = $row2['teacherid'];
                                $course['$receiverName'] = $row2['teachername'];
                            }*/
                        }

    	                $flag[]=$course;
    	            }
    	           
    	         }
			}
	}
	echo json_encode(array('flag'=>$flag));
?>