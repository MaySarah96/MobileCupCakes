<?php

require_once('connect.php');
require_once __DIR__ . '/db_connect.php';

$idCmd=$_GET['uid'];
$qteAcheter=$_GET['qteAcheter'];
$idProd=$_GET['idProd'];


$sql = "INSERT INTO line_cmd (idCmd,qteAcheter,idProd) VALUES ($idCmd,$qteAcheter,$idProd)";

if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}
mysqli_close($conn);
?>