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
    $result = mysql_query("SELECT * FROM produit p , categorie c  where typeProd like '%Sal%' and p.idCat = c.idCat and p.idUser = $pid ");

    if (mysql_num_rows($result) > 0) {
    // looping through all results
    // products node
    $response["info"] = array();
    
    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $commande = array();
        $commande["nomProd"] = $row["nomProd"];
	    $commande["qteStockProd"] = $row["qteStockProd"];
	    $commande["prixProd"] = $row["prixProd"];
        $commande["imageprod"]= $row["imageprod"];
       $commande["nomCat"] = $row["nomCat"];
        $commande["idUser"]=$row["idUser"];
       
        $commande["idCat"]=$row["idCat"];
                $commande["QteAcheter"]=$row["QteAcheter"];       

	        $commande["idProd"]=$row["idProd"];


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
            $response["message"] = "Aucun produit trouvee";

            // echo no users JSON
            echo json_encode($response);
        }
  

?>