<?php

function template(string $template, array $variables = []) : string {

    extract($variables);

    ob_start();

    require __DIR__ . "/../assets/templates/$template.php";

    return ob_get_clean();
}

function is_set($key, $value) : bool {

    if (isset($_GET[$key]) && $_GET[$key] === $value) return true;

    elseif (isset($_POST[$key]) && $_POST[$key] === $value) return true;

    elseif (isset($_SESSION[$key]) && $_SESSION[$key] === $value) return true;

    return false;
}

function alert(string $title, string $message) : void {
    echo <<< EOD
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            <strong>$title</strong> $message
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    EOD;
}

function start() : void {
    if (session_status() === PHP_SESSION_NONE) session_start();
}

function check() : void {
    start();

    if (!is_set("login", true)) {
        header("location: login.php");
    }
}

function dump() : void {
    echo "<pre>";
    var_dump($_GET, $_POST, $_SESSION);
    echo "</pre>";
}