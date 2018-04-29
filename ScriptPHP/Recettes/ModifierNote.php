<?php

require_once('connect.php');
require_once __DIR__ . '/db_connect.php';

$idUser=$_GET['uid'];
$idRec=$_GET['rid'];
$note=$_GET['note'];

$sql = "update Note set note = $note where idUser=$idUser and idRec=$idRec ";

if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}
mysqli_close($conn);
?>