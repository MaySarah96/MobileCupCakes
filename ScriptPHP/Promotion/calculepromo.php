<?php

require_once('connect.php');
require_once __DIR__ . '/db_connect.php';

// check for post data
if (isset($_GET['nv_prix'])&&isset($_GET['idProd']))  {
$nv_prix = $_GET['nv_prix'];
$idProd = $_GET['idProd'];


				
$sql = "update produit set nv_prix=  $nv_prix  where idProd= $idProd";

if (mysqli_query($conn, $sql)) {
echo "success";
} else {
echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}
mysqli_close($conn);
}
?>