<?php
require "DataBaseConfig.php";

class DataBase
{
    public $connect;
    public $data;
    private $sql;
    protected $servername;
    protected $username;
    protected $password;
    protected $databasename;

    public function __construct()
    {
        $this->data = null;
        $this->sql = null;
        $dbc = new DataBaseConfig();
        $this->servername = $dbc->servername;
        $this->username = $dbc->username;
        $this->password = $dbc->password;
        $this->databasename = $dbc->databasename;
    }

    function dbConnect()
    {
        $this->connect = mysqli_connect($this->servername, $this->username, $this->password, $this->databasename);
        return $this->connect;
    }

    function prepareData($data)
    {
        return mysqli_real_escape_string($this->connect, stripslashes(htmlspecialchars($data)));
    }

    function logIn($table, $mail, $password)
    {
        $mail = $this->prepareData($mail);
        $password = $this->prepareData($password);
        $this->sql = "select * from " . $table . " WHERE MAIL = '" . $mail . "'";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbusername = $row['MAIL'];
            $dbpassword = $row['PASSWORD'];
            if ($dbusername == $mail && password_verify($password, $dbpassword)) {
                $login = true;
            } else $login = false;
        } else $login = false;

        return $login;
    }

    function signUp($table,$Nom, $Prenom,$tel, $email, $password)
    {
        $nom = $this->prepareData($Nom);
        $prenom = $this->prepareData($Prenom);
        $tel = $this->prepareData($tel);
        $password = $this->prepareData($password);
        $email = $this->prepareData($email);
        $password = password_hash($password, PASSWORD_DEFAULT);
        $this->sql =
            "INSERT INTO " . $table . " (NOM,PRENOM,TEL,MAIL,PASSWORD) VALUES ('" . $nom . "','" . $prenom . "','" . $tel . "','" . $email . "','" .$password ."')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }

}

?>