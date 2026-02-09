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

function add_scanned_keywords(
    string $url,
    string $trigger,
    int $pages_visited,
): array {
    global $db;

    $db->beginTransaction();

    $stmt = $db->prepare("
        INSERT INTO scans (url, trigger, pages_visited)
        VALUES (:url, :trigger, :pages_visited)
    ");

    $binds = [
        ":url" => $url,
        ":trigger" => $trigger,
        ":pages_visited" => $pages_visited,
    ];

    foreach ($binds as $k => $v) {
        $stmt->bindValue($k, $v);
    }

    try {
        $stmt->execute();
    } catch (Exception $e) {
        error_log($e);
        return [false, "Failed to create record: $e"];
    }

    return [true, "Record Created"];
}