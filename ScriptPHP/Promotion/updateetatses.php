<?php

require_once('connect.php');
require_once __DIR__ . '/db_connect.php';

// check for post data

$idLine = $_GET['id'];


				
$sql = "update linepromoses set etatLinePromosession='en cours' where idLine= $idLine";

if (mysqli_query($conn, $sql)) {
echo "success";
} else {
echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}
mysqli_close($conn);
}
?>