<?php

require_once('connect.php');
require_once __DIR__ . '/db_connect.php';

$idUser=$_GET['uid'];
$dateLiv=$_GET['dateLiv'];
$addLiv=$_GET['addLiv'];
$montantCmd=$_GET['montantCmd'];


$sql = "INSERT INTO commande (idUser,dateCmd,montantCmd,dateLivCmd,addLiv,etatLivCmd,etatCmd) VALUES ($idUser,NOW(),$montantCmd,'$dateLiv','$addLiv','en cour','vrai')";

if (mysqli_query($conn, $sql)) {
    echo mysqli_insert_id($conn);
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}
mysqli_close($conn);
?>