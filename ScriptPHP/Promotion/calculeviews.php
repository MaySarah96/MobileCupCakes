<?php

$response = array();


// include db connect class
require_once __DIR__ . '/db_connect.php';

// connecting to db
$db = new DB_CONNECT();
// check for post data
$id = $_GET['uid'];


				
$result = mysql_query("select count(idLinePromo) as countL from views where idLinePromo=$id");

if (mysql_num_rows($result) > 0) {
    // looping through all results
    // products node
    $response["info"] = array();

    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $views = array();
		$views["count"] = $row["countL"];
       


        // push single login into final response array
        array_push($response["info"], $views);
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

?>