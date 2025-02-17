<?php

require "core/init.php";
if (is_set("login", true)) {
    header("location: index.php");
}

?>

<!DOCTYPE html>
<html lang="en">

<?php if (!isset($_GET["theme"])) : ?>

    <!-- https://github.com/nauvalazhar/bootstrap-5-login-page -->

    <?= template("head", [
        "title" => "Login - Penggajian",
        "author" => "Muhamad Nauval Azhar"
    ]) ?>

    <body>
        <?php foreach ($users->message as $m): ?>
            <?php alert("Login gagal!", $m["message"]) ?>
        <?php endforeach ?>

        <section class="h-90 mb-5">
            <div class="container h-100">
                <div class="row justify-content-sm-center h-100">
                    <div class="col-xxl-4 col-xl-5 col-lg-5 col-md-7 col-sm-9">

                        <div class="text-center my-5">
                            <img src="assets/img/rpl.png" alt="logo" width="130">
                        </div>

                        <div class="card shadow-lg">
                            <div class="card-body p-5 pb-4">

                                <h1 class="fs-4 card-title fw-bold mb-4 text-center">Login</h1>

                                <form method="post" class="needs-validation" novalidate="" autocomplete="off">

                                    <div class="mb-3">
                                        <label class="mb-2 text-muted" for="username">Username</label>
                                        <input id="username" type="text" class="form-control" name="username" required
                                            autofocus>
                                        <div class="invalid-feedback">
                                            Username is empty
                                        </div>
                                    </div>

                                    <div class="mb-3">
                                        <div class="mb-2 w-100">
                                            <label class="text-muted" for="password">Password</label>
                                            <!-- <a href="forgot.html" class="float-end">
                                                Forgot Password?
                                            </a> -->
                                        </div>

                                        <input id="password" type="password" class="form-control" name="password" required>
                                        <div class="invalid-feedback">
                                            Password is required
                                        </div>
                                    </div>

                                    <div class="d-flex align-items-center">
                                        <div class="form-check">
                                            <input type="checkbox" name="remember" id="remember" class="form-check-input">
                                            <label for="remember" class="form-check-label">Remember Me</label>
                                        </div>

                                        <button type="submit" class="btn btn-primary ms-auto" name="action" value="login">
                                            Login
                                        </button>

                                    </div>

                                </form>
                            </div>

                            <div class="card-footer py-3 border-0">
                                <div class="text-center">
                                    Don't have an account? <a href="register.php" class="text-dark">Create One</a>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </section>

        <?= template("footer") ?>
        <?= template("js") ?>
    </body>

<?php elseif (is_set("theme", "1")) : ?>

    <!-- https://github.com/bedimcode/animated-login-form -->

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!--=============== REMIXICONS ===============-->
        <link href="https://cdn.jsdelivr.net/npm/remixicon@2.5.0/fonts/remixicon.css" rel="stylesheet">

        <title>Login - Penggajian</title>

        <style>
            /*=============== GOOGLE FONTS ===============*/
            @import url("https://fonts.googleapis.com/css2?family=Poppins:wght@400;500&display=swap");

            /*=============== VARIABLES CSS ===============*/
            :root {
                /*========== Colors ==========*/
                /*Color mode HSL(hue, saturation, lightness)*/
                --white-color: hsl(0, 0%, 100%);
                --black-color: hsl(0, 0%, 0%);
                /*========== Font and typography ==========*/
                /*.5rem = 8px | 1rem = 16px ...*/
                --body-font: "Poppins", sans-serif;
                --h1-font-size: 1.75rem;
                --normal-font-size: 1rem;
                --small-font-size: .813rem;
                /*========== Font weight ==========*/
                --font-medium: 500;
            }

            /*=============== BASE ===============*/
            * {
                box-sizing: border-box;
                padding: 0;
                margin: 0;
            }

            body,
            input,
            button {
                font-size: var(--normal-font-size);
                font-family: var(--body-font);
            }

            body {
                color: var(--white-color);
            }

            input,
            button {
                border: none;
                outline: none;
            }

            a {
                text-decoration: none;
            }

            img {
                max-width: 100%;
                height: auto;
            }

            /*=============== LOGIN ===============*/
            .login {
                position: relative;
                height: 100vh;
                display: grid;
                align-items: center;
            }

            .login__img {
                position: absolute;
                width: 100%;
                height: 100%;
                object-fit: cover;
                object-position: center;
            }

            .login__form {
                position: relative;
                background-color: hsla(0, 0%, 10%, 0.1);
                border: 2px solid var(--white-color);
                margin-inline: 1.5rem;
                padding: 2.5rem 1.5rem;
                border-radius: 1rem;
                backdrop-filter: blur(8px);
            }

            .login__title {
                text-align: center;
                font-size: var(--h1-font-size);
                font-weight: var(--font-medium);
                margin-bottom: 2rem;
            }

            .login__content,
            .login__box {
                display: grid;
            }

            .login__content {
                row-gap: 1.75rem;
                margin-bottom: 1.5rem;
            }

            .login__box {
                grid-template-columns: max-content 1fr;
                align-items: center;
                column-gap: 0.75rem;
                border-bottom: 2px solid var(--white-color);
            }

            .login__icon,
            .login__eye {
                font-size: 1.25rem;
            }

            .login__input {
                width: 100%;
                padding-block: 0.8rem;
                background: none;
                color: var(--white-color);
                position: relative;
                z-index: 1;
            }

            .login__box-input {
                position: relative;
            }

            .login__label {
                position: absolute;
                left: 0;
                top: 13px;
                font-weight: var(--font-medium);
                transition: top 0.3s, font-size 0.3s;
            }

            .login__eye {
                position: absolute;
                right: 0;
                top: 18px;
                z-index: 10;
                cursor: pointer;
            }

            .login__box:nth-child(2) input {
                padding-right: 1.8rem;
            }

            .login__check,
            .login__check-group {
                display: flex;
                align-items: center;
                justify-content: space-between;
            }

            .login__check {
                margin-bottom: 1.5rem;
            }

            .login__check-label,
            .login__forgot,
            .login__register {
                font-size: var(--small-font-size);
            }

            .login__check-group {
                column-gap: 0.5rem;
            }

            .login__check-input {
                width: 16px;
                height: 16px;
            }

            .login__forgot {
                color: var(--white-color);
            }

            .login__forgot:hover {
                text-decoration: underline;
            }

            .login__button {
                width: 100%;
                padding: 1rem;
                border-radius: 0.5rem;
                background-color: var(--white-color);
                font-weight: var(--font-medium);
                cursor: pointer;
                margin-bottom: 2rem;
            }

            .login__register {
                text-align: center;
            }

            .login__register a {
                color: var(--white-color);
                font-weight: var(--font-medium);
            }

            .login__register a:hover {
                text-decoration: underline;
            }

            /* Input focus move up label */
            .login__input:focus+.login__label {
                top: -12px;
                font-size: var(--small-font-size);
            }

            /* Input focus sticky top label */
            .login__input:not(:placeholder-shown).login__input:not(:focus)+.login__label {
                top: -12px;
                font-size: var(--small-font-size);
            }

            /*=============== BREAKPOINTS ===============*/
            /* For medium devices */
            @media screen and (min-width: 576px) {
                .login {
                    justify-content: center;
                }

                .login__form {
                    width: 432px;
                    padding: 4rem 3rem 3.5rem;
                    border-radius: 1.5rem;
                }

                .login__title {
                    font-size: 2rem;
                }
            }
        </style>
    </head>

    <body>
        <div class="login">
            <img src="https://raw.githubusercontent.com/bedimcode/animated-login-form/refs/heads/main/assets/img/login-bg.png" alt="login image" class="login__img">

            <form action="" method="post" class="login__form">
                <h1 class="login__title">Login</h1>

                <div class="login__content">
                    <div class="login__box">
                        <i class="ri-user-3-line login__icon"></i>

                        <div class="login__box-input">
                            <input type="text" name="username" required class="login__input" id="username" placeholder=" ">
                            <label for="username" class="login__label">Username</label>
                        </div>
                    </div>

                    <div class="login__box">
                        <i class="ri-lock-2-line login__icon"></i>

                        <div class="login__box-input">
                            <input type="password" name="password" required class="login__input" id="password"
                                placeholder=" ">
                            <label for="password" class="login__label">Password</label>
                            <i class="ri-eye-off-line login__eye" id="login-eye"></i>
                        </div>
                    </div>
                </div>

                <!-- <div class="login__check">
                    <div class="login__check-group">
                        <input type="checkbox" class="login__check-input" id="login-check">
                        <label for="login-check" class="login__check-label">Remember me</label>
                    </div>

                    <a href="#" class="login__forgot">Forgot Password?</a>
                </div> -->

                <button type="submit" class="login__button" name="action" value="login">Login</button>

                <p class="login__register">
                    Don't have an account? <a href="register.php">Register</a>
                </p>
            </form>
        </div>

        <script>
            /*=============== SHOW HIDDEN - PASSWORD ===============*/
            const showHiddenPass = (loginPass, loginEye) => {
                const input = document.getElementById(loginPass),
                    iconEye = document.getElementById(loginEye)

                iconEye.addEventListener('click', () => {
                    // Change password to text
                    if (input.type === 'password') {
                        // Switch to text
                        input.type = 'text'

                        // Icon change
                        iconEye.classList.add('ri-eye-line')
                        iconEye.classList.remove('ri-eye-off-line')
                    } else {
                        // Change to password
                        input.type = 'password'

                        // Icon change
                        iconEye.classList.remove('ri-eye-line')
                        iconEye.classList.add('ri-eye-off-line')
                    }
                })
            }

            showHiddenPass('password', 'login-eye')
        </script>
    </body>

<?php endif ?>

</html>