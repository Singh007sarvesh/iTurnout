<?php

include('connection.php');

$response = array();

if($_SERVER['REQUEST_METHOD']=='POST')
	{
		if(isset($_POST['userid']) and
			isset($_POST['password']))
			{
				$uid = $_POST['userid'];


				if($uid[0]=='t' or $uid[0]=='T')
				{
					$sql = "SELECT teacherid,password FROM teacher WHERE teacherid = '$uid'";
					$result = mysqli_query($con,$sql);
	            	$numRows = $result->num_rows;
	            	
		            if($numRows>0)
		            {
		                $row = $result->fetch_array(MYSQLI_BOTH);
		                $Dbid = $row['teacherid'];
		                $DbPassword = $row['password'];
		            
		                if(($Dbid == $uid) and ($DbPassword == $_POST['password']))
		                 {
		                    $sql = "SELECT teacherid,teachername FROM teacher WHERE teacherid = '$uid'";
							$result = mysqli_query($con,$sql);	
							$row = $result->fetch_assoc();
            				$userDetails[] = $row; 

							foreach($userDetails as $data)
		        			{
		        				$response['error'] = false;
		        				$response['userid'] = $data['teacherid'];
		        				$response['name'] = $data['teachername'];
		        			}
		                 }
		                else
		                 {
		                     $response['error'] = true;
		                 	$response['message'] = "incorrect password";
		                 }
		            }
		            else
		            {
		                $response['error'] = true;
		                $response['message'] = "user not found";
		            }
				}
				else if($uid[0]=='a' or $uid[0]=='A')
				{
					$sql = "SELECT mid,password FROM moderator WHERE mid = '$uid'";
					$result = mysqli_query($con,$sql);
	            	$numRows = $result->num_rows;
	            	
		            if($numRows>0)
		            {
		                $row = $result->fetch_array(MYSQLI_BOTH);
		                $Dbid = $row['mid'];
		                $DbPassword = $row['password'];
		            
		                if(($Dbid == $uid) and ($DbPassword == $_POST['password']))
		                 {
		                    $sql = "SELECT mid,mname FROM moderator WHERE mid = '$uid'";
							$result = mysqli_query($con,$sql);	
							$row = $result->fetch_assoc();
            				$userDetails[] = $row; 

							foreach($userDetails as $data)
		        			{
		        				$response['error'] = false;
		        				$response['userid'] = $data['mid'];
		        				$response['name'] = $data['mname'];
		        			}
		                 }
		                else
		                 {
		                     $response['error'] = true;
		                 	$response['message'] = "incorrect password";
		                 }
		            }
		            else
		            {
		                $response['error'] = true;
		                $response['message'] = "user not found";
		            }
				}
				else
				{
					$sql = "SELECT studentid,password FROM student WHERE studentid = '$uid'";
					$result = mysqli_query($con,$sql);
	            	$numRows = $result->num_rows;
	            	
		            if($numRows>0)
		            {
		                $row = $result->fetch_array(MYSQLI_BOTH);
		                $Dbid = $row['studentid'];
		                $DbPassword = $row['password'];
		            
		                if(($Dbid == $uid) and ($DbPassword == $_POST['password']))
		                 {
		                    $sql = "SELECT studentid,studentname FROM student WHERE studentid = '$uid'";
							$result = mysqli_query($con,$sql);	
							$row = $result->fetch_assoc();
            				$userDetails[] = $row; 

							foreach($userDetails as $data)
		        			{
		        				$response['error'] = false;
		        				$response['userid'] = $data['studentid'];
		        				$response['name'] = $data['studentname'];
		        			}
		                 }
		                else
		                 {
		                     $response['error'] = true;
		                 	$response['message'] = "incorrect password";
		                 }
		            }
		            else
		            {
		                $response['error'] = true;
		                $response['message'] = "user not found";
		            }
				}
			}
	}
	echo json_encode($response);
?>