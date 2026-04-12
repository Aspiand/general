<?php

require "./bootstrap.php";

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Form</title>
    <link rel="stylesheet" href="style.css">

    <style>
        .container {
            border: 1px solid black;
            margin: 10px;
            margin-top: 100px;
        }

        .container form {
            padding: 10px;
        }

        form button {
            margin-top: 8px;
            width: 100%;
        }

        h1 {
            text-align: center;
        }
    </style>
</head>
<body>

<div class="container">

    <h1><?= ucfirst($_GET['action'] ?? 'Form') ?></h1>

    <form action="form.php" method="post">
        <label for="nis">Nis</label>
        <input type="text" name="nis">

        <br/>

        <label for="nis">Name</label>
        <input type="text" name="name" <?= ($_GET['action'] ?? '' === 'add') ? 'disabled' : '' ?>>

        <br/>

        <label for="nis">Class</label>
        <input type="text" name="class">

        <br />

        <button type="submit">Submit</button>

        <a href="index.php"><button type="button">Back</button></a>

    </form>

</div>
    
</body>
</html>