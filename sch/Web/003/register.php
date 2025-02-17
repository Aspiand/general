<?php

require "core/init.php";
if (is_set("login", true)) {
    header("location: index.php");
}

?>

<!DOCTYPE html>
<html lang="en">

<?= template("head", [
    "title" => "Penggajian - Register",
    "author" => "Muhamad Nauval Azhar"
]) ?>

<body>

    <?php foreach ($users->message as $m) : ?>
        <?php alert("Register failed!", $m["message"]) ?>
    <?php endforeach ?>

    <section class="h-200 mb-5">
        <div class="container h-100">
            <div class="row justify-content-sm-center h-100">
                <div class="col-xxl-4 col-xl-5 col-lg-5 col-md-7 col-sm-9">

                    <div class="text-center my-5">
                        <img src="assets/img/rpl.png" alt="logo" width="130">
                    </div>

                    <div class="card shadow-lg">
                        <div class="card-body p-5 pb-2">

                            <h1 class="fs-4 card-title fw-bold mb-4 text-center">Register</h1>

                            <form method="POST" class="needs-validation" novalidate="" autocomplete="off">
                                <div class="mb-3">
                                    <label class="mb-2 text-muted" for="username">Name</label>
                                    <input id="username" type="text" class="form-control" name="username" required
                                        autofocus>

                                    <div class="invalid-feedback">
                                        Name is required
                                    </div>
                                </div>

                                <div class="mb-3">
                                    <label class="mb-2 text-muted" for="password">Password</label>
                                    <input id="password" type="password" class="form-control" name="password" required>

                                    <div class="invalid-feedback">
                                        Password is required
                                    </div>

                                </div>

                                <div class="align-items-center d-flex pt-2 pb-2">
                                    <button type="submit" class="btn btn-primary ms-auto w-100" name="action" value="register">
                                        Register
                                    </button>
                                </div>

                            </form>
                        </div>

                        <div class="card-footer py-3 border-0">
                            <div class="text-center">
                                Already have an account? <a href="login.php" class="text-dark">Login</a>
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

</html>