<?php
	
	include('connection.php');
	$response  = array();
	if($_SERVER['REQUEST_METHOD']=='POST')
	{
		
    			$content = $_POST['content'];
    			$name = $_POST['name'];
    	    	$image = $_POST['image'];
    	    //	$image = $_FILES['image']['name'];
    			$sender=$_POST['studentId'];
    			$receiver=$_POST['teacherId'];
    			$status=$_POST['status'];
    			$uploadpath="Image/$name";
    			$ddate=date("Y/m/d");
    			$res = "INSERT INTO query(id,tid,sid,content,qdate)VALUES(NULL,'$sender','$receiver','$content','$ddate')";
    			$sql=mysqli_query($con,$res);
    			if($sql)
    			{
    			   // file_put_contents($uploadpath,base64_decode($image));
    				$res1="select id from query order by id DESC limit 1";
    				$sql1=mysqli_query($con,$res1);
    				if($sql1)
    				{
    				    
        			    if($status== "1")
        				{
        				    
        				    while($row=mysqli_fetch_array($sql1))
        	                {
            	                $qid = $row['id'];
        	                }
        				    $insertsql="INSERT INTO attachment(id,imagename) VALUES ('$qid','$name')";
        				    $sql2=mysqli_query($con,$insertsql);
        				    if($sql2)
        				    {
        				        	
        				         file_put_contents($uploadpath,base64_decode($image));
        				    }
        				}
    				}
    				$response['message'] = "Successfully";
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
?>
