<?php

require_once('connect.php');
require_once __DIR__ . '/db_connect.php';

// check for post data
if (isset($_GET['idses'])&&isset($_GET['iduser']))  {
    $idses = $_GET['idses'];
    $iduser = $_GET['iduser'];
    // get a login from memberFamily table
    //$result = mysql_query("INSERT INTO Educate(dateIscri,etatEduc,idUser,idSes,etatNotif)VALUES('NOW()','inscri',1,$pid,'non notifie');");
    $sql = "update Educate set etatNotif='deja notifie' where idSes= $idses AND idUser= $iduser";

    if (mysqli_query($conn, $sql)) {
        echo "success";
    } else {
        echo "Error: " . $sql . "<br>" . mysqli_error($conn);
    }
    mysqli_close($conn);
    }
    ?>