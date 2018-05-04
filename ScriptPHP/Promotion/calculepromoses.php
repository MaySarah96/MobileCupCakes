<?php

require_once('connect.php');
require_once __DIR__ . '/db_connect.php';

// check for post data
if (isset($_GET['nv_prix_ses'])&&isset($_GET['idSes']))  {
$nv_prix_ses = $_GET['nv_prix_ses'];
$idSes = $_GET['idSes'];


				
$sql = "update session set nv_prix_ses=  $nv_prix_ses  where idSes= $idSes";

if (mysqli_query($conn, $sql)) {
echo "success";
} else {
echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}
mysqli_close($conn);
}
?>