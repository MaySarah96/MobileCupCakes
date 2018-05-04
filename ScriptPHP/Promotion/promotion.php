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

// get a login from memberFamily table
$result = mysql_query("select * from line_promo l , produit p , categorie c  WHERE c.idCat=p.idCat and l.idProd=p.idProd and l.etatLinePromo ='en cours'");

if (mysql_num_rows($result) > 0) {
    // looping through all results
    // products node
    $response["info"] = array();

    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $promtion = array();
		$promtion["idLinePromo"]= $row["id"];
        $promtion["nomProd"] = utf8_encode ($row["nomProd"]);
        $promtion["prixProd"] = $row["prixProd"];
        $promtion["nv_prix"] = $row["nv_prix"];
        $promtion["qteStockProd"] = $row["qteStockProd"];
        $promtion["dateDeb"] = $row["dateDeb"];
        $promtion["dateFin"] = $row["dateFin"];
        $promtion["imageprod"] = utf8_encode ($row["imageprod"]);
		//$promtion["views"] = $row["views"];

        // push single login into final response array
        array_push($response["info"], $promtion);
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