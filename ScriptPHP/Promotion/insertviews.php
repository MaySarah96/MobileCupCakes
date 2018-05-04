<?php

require_once('connect.php');
require_once __DIR__ . '/db_connect.php';

// check for post data

$idUser = $_GET['uid'];
$idLinePromo = $_GET['uid1'];

				
$sql = "INSERT INTO views (idLinePromo,idUser) VALUES ($idLinePromo,$idUser)";

if (mysqli_query($conn, $sql)) {
echo "success";
} else {
echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}
mysqli_close($conn);
?>