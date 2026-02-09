<?php

try {
    $db = new PDO("sqlite:" . __DIR__ . "/w011.sqlite");
    $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    $db->exec("CREATE TABLE IF NOT EXISTS scans (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        url TEXT,
        trigger TEXT,
        pages_visited INTEGER
    )");
} catch (Exception $e) {
    die($e->getMessage());
}
