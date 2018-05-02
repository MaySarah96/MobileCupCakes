<?php

// array for JSON response
$response = array();


// include db connect class
require_once __DIR__ . '/db_connect.php';

// connecting to db
$db = new DB_CONNECT();
if (isset($_GET['uid'])&&isset($_GET['date'])) {

    $pid = $_GET['uid'];
    $date = $_GET['date'];
    // get a login from memberFamily table
    $result = mysql_query( "SELECT * FROM Session s,Educate e WHERE s.idSes=e.idSes AND e.etatNotif='non notifie' AND e.idSes= $pid AND s.dateDebSes='$date' ;");
    
    if (mysql_num_rows($result) > 0) 
    {
        // looping through all results
        // products node
        $response["info"] = array();
        
        while ($row = mysql_fetch_array($result)) {
            $session = array();
        $session["dateIscri"] = $row["dateIscri"];
        $session["etatEduc"] = $row["etatEduc"];
        $session["idSes"] = $row["idSes"];
        // push single login into final response array
        array_push($response["info"], $session);
        }
        // success
        $response["success"] = 1;

        // echoing JSON response
        echo json_encode($response);
    } 
	else 
    {
            // no user found
			$response["info"] = array();
            $response["success"] = 0;
            $response["message"] = "Aucun utilisateur trouvé";

            // echo no users JSON
            echo json_encode($response);
    }
  } 
  else 
  {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Champs vide";

    // echoing JSON response
    echo json_encode($response);
  }
?>