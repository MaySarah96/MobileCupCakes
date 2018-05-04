<?php

require_once('connect.php');
require_once __DIR__ . '/db_connect.php';

// check for post data

$id = $_GET['id'];
$acheter = $_GET['acheter'];
$stock = $_GET['stock'];
echo $acheter;
echo $id;
echo $stock ;

				
$sql = "update produit set qteStockProd=$stock and QteAcheter=$acheter where idProd=$id";

if (mysqli_query($conn, $sql)) {
echo "success";
} else {
echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}
mysqli_close($conn);

?>