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
 $pid = $_GET['uid'];


    // get a login from memberFamily table
    $result = mysql_query("SELECT * FROM produit p ,categorie c where p.idCat=c.idCat and p.idUser=$pid");

    if (mysql_num_rows($result) > 0) {
    // looping through all results
    // products node
    $response["info"] = array();
    
    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $Produit = array();
        $Produit["idProd"] = $row["idProd"];
        $Produit["nomProd"] = $row["nomProd"];
        $Produit["qteStockProd"] = $row["qteStockProd"];
        $Produit["typeProd"] = utf8_encode ($row["typeProd"]);
        $Produit["prixProd"] = $row["prixProd"];
	    $Produit["idCat"] = $row["idCat"];
        $Produit["nomCat"] = $row["nomCat"];
        $Produit["imageprod"] = $row["imageprod"];
        $Produit["idUser"] =$row["idUser"];
        $Produit["QteAcheter"]=$row["QteAcheter"];       
                $Produit["idProd"]=$["idProd"];

        // push single login into final response array
        array_push($response["info"],  $Produit);
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
            $response["message"] = "Aucun produit trouvé";

            // echo no users JSON
            echo json_encode($response);
        }
  

?>