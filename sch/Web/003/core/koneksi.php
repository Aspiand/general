<?php

try {
    $database = "w003";
    
    $db = new PDO("mysql:host=127.0.0.1; dbname=$database", "root", "root");
    $db->setAttribute( PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION );
} catch (Exception $e) {
    die($e->getMessage());
}