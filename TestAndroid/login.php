<?php 
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['mail']) && isset($_POST['password'])) {
    if ($db->dbConnect()){
        if ($db->logIn("UTILISATEUR", $_POST["mail"],$_POST['password'])) {
            echo "Login Success";
        }else echo "Username or Password wrong";
    }else echo "Error: Database connection";
}else echo "All fields are required";




?>