<?php

require_once('connect.php');
require_once __DIR__ . '/db_connect.php';

// check for post data

$id = $_GET['id'];


				
$sql = "update line_promo set etatLinePromo='en cours' where id= $id";

if (mysqli_query($conn, $sql)) {
echo "success";
} else {
echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}
mysqli_close($conn);
}
?>