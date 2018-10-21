<?php
/**
 * Created by PhpStorm.
 * User: adma9717
 * Date: 09/10/18
 * Time: 10:50 PM
 */

//next lines is to show the error messages
error_reporting(0);
ini_set('display_errors', 1);

//import the php file with de credentials of the loginData base
require("DatabaseCredentials.php");

//connection string
$string_connection = "host=".$db_host." dbname=".$db_name." user=".$db_user." password=".$db_password;
$connection = pg_connect($string_connection) or die("Couldn't connected to the Series & Animes database".pg_last_error());
session_start();

//get the data to make the consult
$username = pg_escape_string($_POST['username']);
$password = pg_escape_string($_POST['password']);

//consult for the user
$queryUser = 'select * from users where user_name = $1';
$queryUserResult = pg_query_params($connection, $queryUser, array($username));

//looks if there is an user with the given user name in the database
if($currentUser = pg_fetch_array($queryUserResult)){
    $currentUserPassword = $currentUser["user_password"];

    //validate if the password given is equals than the password in the database
    //password_verify(password, currentUserPassword)
    if($password == $currentUserPassword){
        //the password is correct
        echo "userOk";
    }
    else{
        //the password is incorrect but the user is registered
        echo "userError";
    }
}
else{
    //the user given is not in the database
    echo "userNotRegister";
}

