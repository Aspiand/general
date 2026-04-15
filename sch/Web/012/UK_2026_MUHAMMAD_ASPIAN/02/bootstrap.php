<?php

try {
    // $db = new PDO("sqlite:" . __DIR__ . "/database.sqlite");
    $db = new PDO("mysql:host=localhost;dbname=aspian_ukk_2026", 'root', '');

    $db->exec("
        CREATE TABLE IF NOT EXISTS pegawai (
            nip INTEGER PRIMARY KEY,
            nama VARCHAR(25),
            tanggal_lahir VARCHAR(25),
            gaji_pokok INTEGER
        );
    ");
} catch (Exception $e) {
    die($e->getMessage());
}