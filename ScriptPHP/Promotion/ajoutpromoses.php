<?php

require_once('connect.php');
require_once __DIR__ . '/db_connect.php';

// check for post data
$idSes = $_GET['idSes'];
$dateDeb = $_GET['dateDeb'];
$dateFin = $_GET['dateFin'];
$idPromo = $_GET['idPromo'];

				
$sql = "INSERT INTO linepromoses (idSes,dateDeb,dateFin,idPromo,etatLinePromosession) VALUES ($idSes,$dateDeb,$dateFin,$idPromo,'en cours')";

if (mysqli_query($conn, $sql)) {
echo "success";
} else {
echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}
mysqli_close($conn);
?>