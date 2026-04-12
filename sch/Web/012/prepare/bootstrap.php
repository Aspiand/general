<?php

try {
    $db = new PDO("sqlite:" . __DIR__ . "/database.sqlite");

    $db->exec("
        CREATE TABLE IF NOT EXISTS student (
            nis INTEGER PRIMARY KEY,
            name VARCHAR(25) NOT NULL,
            class VARCHAR(25) NOT NULL  
        );
    ");
} catch (Exception $e) {
    die($e->getMessage());
}