<?php

require_once('connect.php');
require_once __DIR__ . '/db_connect.php';

$idUser=$_GET['uid'];
$idRec=$_GET['rid'];
$note=$_GET['note'];

$sql = "INSERT INTO note (idUser,IdRec,note,dateNote,etatNote) VALUES ($idUser,$idRec,$note,NOW(),'oui')";

if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}
mysqli_close($conn);
?>