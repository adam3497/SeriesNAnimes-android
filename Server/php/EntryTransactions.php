<?php
/**
 * Created by PhpStorm.
 * User: adma9717
 * Date: 29/09/18
 * Time: 11:16 AM
 */

//the next lines allow to show the error in the server and some transactions
error_reporting(E_ALL);
ini_set( 'display_errors', 1);

//database files that manage the consults
$loginURL = "http://localhost/Server/php/db/login.php";

//get the action
$action = $_POST["action"];

if($action == "login"){
    //get the corresponding login data to make the login
    $username = $_POST["username"];
    $password = $_POST["password"];

    $loginData = [
        'username' => $username,
        'password' => $password
    ];

    //make the curl request
    $loginRequest = curl_init($loginURL);
    curl_setopt($loginRequest, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($loginRequest, CURLOPT_POSTFIELDS, $loginData);

    //execute
    $loginResponse = curl_exec($loginRequest);
    curl_close($loginRequest);

    //return the response from the login request
    echo $loginResponse;
}