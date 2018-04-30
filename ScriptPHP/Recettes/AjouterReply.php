<?php

require_once('connect.php');
require_once __DIR__ . '/db_connect.php';

$idUser=$_GET['uid'];
$idRec=$_GET['rid'];
$body=$_GET['body'];
$ancestors=$_GET['ancestors'];

$sql = "INSERT INTO commentaire (idUser,IdRec,body,created_at,depth,state,ancestors) VALUES ($idUser,'$idRec','$body',NOW(),0,0,'$ancestors')";

if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}
mysqli_close($conn);
?>