<div id="layoutSidenav_nav">
    <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
        <div class="sb-sidenav-menu">
            <div class="nav">

                <a class="nav-link" href="index.php">
                    <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                    Dashboard
                </a>

                <div class="sb-sidenav-menu-heading">Core</div>

                <a class="nav-link" href="register.php">
                    <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                    Golongan
                </a>

                <a class="nav-link" href="penggajian.php">
                    <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                    Penggajian
                </a>

            </div>
        </div>

        <div class="sb-sidenav-footer">
            <div class="small">Logged in as: <?= $_SESSION["username"] ?? "Unknown" ?></div>
        </div>
    </nav>
</div>