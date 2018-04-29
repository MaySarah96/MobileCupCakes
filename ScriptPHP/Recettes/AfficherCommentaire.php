<?php

/*
 * Following code will get single product details
 * A product is identified by product id (pid)
 */

// array for JSON response
$response = array();


// include db connect class
require_once __DIR__ . '/db_connect.php';

// connecting to db
$db = new DB_CONNECT();

if (isset($_GET['rid'])) {
    $idRec = $_GET['rid'];
	$result = mysql_query("select * from commentaire c,utilisateur u where c.idRec='$idRec' and c.ancestors='' and c.state=0 and c.idUser=u.id order by idCmnt DESC");
	
    if (mysql_num_rows($result) > 0) {
    // looping through all results
    // products node
    $response["info"] = array();
    
    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $commentaire = array();
        $commentaire["idCmnt"] = $row["idCmnt"];
		$body = utf8_encode ( $row["body"]) ;
		$commentaire["body"] = $body ;
		$commentaire["created_at"] = $row["created_at"];
		$commentaire["ancestors"] = $row["ancestors"];
		$commentaire["idUser"] = $row["idUser"];
		$commentaire["username"] = $row["username"];
        // push single login into final response array
        array_push($response["info"], $commentaire);
    }
    // success
   $response["success"] = 1;

    // echoing JSON response
    echo json_encode($response);
        } 
		else {
            // no user found
			$response["info"] = array();
            $response["success"] = 0;
            $response["message"] = "Aucun utilisateur trouvé";

            // echo no users JSON
            echo json_encode($response);
        }
  
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Champs vide";

    // echoing JSON response
    echo json_encode($response);
}

?>