<?php

require __DIR__ . "/../core/init.php";

// debug
// echo "GET: ";
// var_dump($_GET);

// echo "<br>";
// echo "<br>";

// echo "POST: ";
// var_dump($_POST);
// end debug

$users = new Users($db);

if (isset($_SESSION["login"])) {
    header("Location: ../shop");
    exit;
}

if (
    isset($_POST["username"], $_POST["password"]) &&
    strlen($_POST["username"]) > 0 && strlen($_POST["username"]) > 0
) {
    $username = $_POST["username"];
    $password = $_POST["password"];

    if (Utils::isset("action", "login")) {
        if ($users->login($username, $password)) {
            $_SESSION["login"] = true;
            $_SESSION += $users->info($username);
            header("Location: ../shop");
            exit;
        }
    }

    else if (Utils::isset("action", "register")) {
        $users->add($username, $password);
    }
}