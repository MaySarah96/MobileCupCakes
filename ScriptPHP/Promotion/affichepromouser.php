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
$idUser = $_GET["uid"];
// get a login from memberFamily table
$result = mysql_query("select * from line_promo  , produit  where line_promo.idProd=produit.idProd and idUser=$idUser");

if (mysql_num_rows($result) > 0) {
    // looping through all results
    // products node
    $response["info"] = array();

    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $produit = array();
		$produit["idProd"] = $row["idProd"];
        $produit["nomProd"] = $row["nomProd"];
        $produit["qteStockProd"] = $row["qteStockProd"];
		$produit["typeProd"]  = utf8_encode ( $row["typeProd"]) ;
        $produit["prixProd"] = $row["prixProd"];
		        $produit["nv_prix"] = $row["nv_prix"];
        $produit["imageprod"] = $row["imageprod"];


        // push single login into final response array
        array_push($response["info"], $produit);
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