<?php

require_once('connect.php');
require_once __DIR__ . '/db_connect.php';

$idCmd=$_GET['uid'];
$sujet=$_GET['sujet'];
$description=$_GET['description'];


$sql = "INSERT INTO feed_back (idCmd,sujet,description) VALUES ($idCmd,'$sujet','$description')";

if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}
mysqli_close($conn);
?>