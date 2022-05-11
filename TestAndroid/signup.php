<?php 
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['Nom'])&& isset($_POST['prenom']) && isset($_POST['email'])&& isset($_POST['tel'])&& isset($_POST['password'])) {
    if($db->dbConnect()){
        if ($db->signUp("UTILISATEUR", $_POST['Nom'],$_POST["prenom"],$_POST['tel'], $_POST['email'], $_POST['password'])){
            echo "Sign Up Success";
        }else echo "Sign up Failed";
    }else echo "Error: Database Connection";
}else echo "All fields are required"
?>