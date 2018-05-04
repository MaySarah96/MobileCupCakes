<?php

require_once('connect.php');
require_once __DIR__ . '/db_connect.php';

// check for post data
$idProd = $_GET['idProd'];
$dateDeb = $_GET['dateDeb'];
$dateFin = $_GET['dateFin'];
$idPromo = $_GET['idPromo'];

				
$sql = "INSERT INTO line_promo (idProd,dateDeb,dateFin,idPromo,etatLinePromo) VALUES ($idProd,$dateDeb,$dateFin,$idPromo,'en cours')";

if (mysqli_query($conn, $sql)) {
echo "success";
} else {
echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}
mysqli_close($conn);
?>