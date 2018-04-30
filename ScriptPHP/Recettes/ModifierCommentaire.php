<?php

require_once('connect.php');
require_once __DIR__ . '/db_connect.php';

$idCmnt=$_GET['idCmnt'];
$body=$_GET['body'];

$sql = "UPDATE commentaire set body='$body' WHERE idCmnt=$idCmnt ";

if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}
mysqli_close($conn);
?>