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

// check for post data
if (isset($_GET['uid'])) {
    $pid = $_GET['uid'];

    // get a login from memberFamily table
    $result = mysql_query("SELECT * FROM line_cmd l, produit p WHERE l.idProd=p.idProd and l.idCmd = $pid");

    if (mysql_num_rows($result) > 0) {
    // looping through all results
    // products node
    $response["info"] = array();
    
    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $commande = array();
        $commande["idProd"] = $row["idProd"];
	    $commande["qteAcheter"] = $row["qteAcheter"];
        $commande["idProd"]=$row["idProd"];
        $commande["nomProd"]=utf8_encode ($row["nomProd"]);
        $commande["qteStockProd"]=$row["qteStockProd"];
        $commande["prixProd"] = $row["prixProd"];
        $commande["imageprod"]= $row["imageprod"];
        $commande["typeProd"]=utf8_encode ($row["typeProd"]);
        // push single login into final response array
        array_push($response["info"], $commande);
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
            $response["message"] = "Aucune Commande trouvé";

            // echo no users JSON
            echo json_encode($response);
        }}
?>