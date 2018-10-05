<?php
/**
 * Created by PhpStorm.
 * User: adma9717
 * Date: 29/09/18
 * Time: 11:17 AM
 */

// to show the error messages
error_reporting(E_ALL);
ini_set('display_errors', 1);

// this file import the database credentials to access it
require("../DatabaseCredentials.php");

// connection string
$db_connection_string = "host=".$db_host." db_name=".$db_name." user=".$db_user." password=".$db_password;
$connection = pg_connect($db_connection_string) or die ("Couldn't connected to the Series & Animes Database: ".pg_last_error());

// Strings must be escaped to prevent SQL ijection
