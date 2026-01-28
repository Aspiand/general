<?php require "setup.php" ?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login/Register Page</title>
    <link rel="stylesheet" href="/users/style.css">
</head>

<body <?=(Utils::isset("hehe", "true") ? "class='hehe'" : null)?>>
    <main>
        <div class="top"><h1>Users</h1></div>
        <form action="" method="post">
            <div class="input">
                <input type="text" name="username" placeholder="Username" autocomplete="off" required>
                <input type="password" name="password" placeholder="Password" required>
            </div>
            
            <div class="button">
                <button name="action" value="login"><h3>Login</h3></button>
                <button name="action" value="register"><h3>Register</h3></button>
            </form>
        </div>
    </main>
<?php // var_dump($users->message) ?>
</body>
</html>