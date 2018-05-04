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
    $result = mysql_query("SELECT * FROM produit p, Categorie c , utilisateur t where typeProd like '%Sucr%' and p.idCat=c.idCat and p.idUser=t.id");

    if (mysql_num_rows($result) > 0) {
    // looping through all results
    // products node
    $response["info"] = array();
    
    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $produit = array();
        $produit["nomProd"] = $row["nomProd"];
	$produit["qteStockProd"] = $row["qteStockProd"];
	$produit["prixProd"] = $row["prixProd"];
        $produit["imageprod"]= utf8_encode($row["imageprod"]);
        $produit["nomCat"] = $row["nomCat"];
        $produit["idCat"]=$row["idCat"];
         $produit["idUser"]=$row["idUser"];
         $produit["nom"]=$row["nom"];
        $produit["QteAcheter"]=$row["QteAcheter"];  
        $produit["idProd"]=$row["idProd"];
        
	

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
            $response["message"] = "Aucun produit trouvee";

            // echo no users JSON
            echo json_encode($response);
        }
  

?>