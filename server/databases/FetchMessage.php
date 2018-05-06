<?php
	
	include('connection.php');
	    	$flag  = array();
if($_SERVER['REQUEST_METHOD']=='POST')
	{
		
			    $conid=$_POST['contentId'];
    	        $sql = "select content,id FROM  query as q  WHERE q.id='$conid'";
    	        $res= mysqli_query($con,$sql);
    	         if($res)
    	         {
    	            while($row=mysqli_fetch_array($res))
    	            {
    	                $flag['content']=$row['content'];
    	                $qid=$row['id'];
    	                $fetchImg="select imagename from attachment where id='$qid'";
    	                $result=mysqli_query($con,$fetchImg);
    	                if($result)
    	                {
    	                    while($row=mysqli_fetch_array($result))
    	                    {
    	                        $imagen=$row['imagename'];
    	                       // $pdf = $client->convertURI('https://i-turmout.000webhostapp.com/Image/');
    	                        $url="https://i-turmout.000webhostapp.com/Image/".$imagen;
    	                        $flag['image']=$url;
    	                       
    	                    }
    	                }
    	              //  $course  = array();
    	              //  $course['content'] = $row['content'];
    	              //  $flag[]=$course;
    	            }
    	         }
			
	}
	echo json_encode($flag);
?>