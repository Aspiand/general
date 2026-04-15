<?php

require "./bootstrap.php";

$action = $_GET["action"] ?? 'add';

$pegawai = [
    "nip" => "",
    "nama" => "",
    "tanggal_lahir" => "",
    "gaji_pokok" => "",
];

$err = [
    'nip' => '',
];

// echo "<pre>";
// var_dump([
//     'post' => $_POST,
//     'get' => $_GET, 
//     'nip' => isset($_POST["nip"]),
//     'action_update' => ($_POST["action"] ?? '') === 'update'
// ]);
// echo "<pre/>";

if (isset($_POST["nip"]) || isset($_GET["nip"])) {
    if (($_POST['action'] ?? '') === 'add') {
        try {
            $stmt = $db->prepare("INSERT INTO pegawai VALUES (:nip, :nama, :tanggal_lahir, :gaji_pokok)");
            $stmt->execute([
                "nip" => $_POST["nip"],
                "nama" => $_POST["nama"],
                "tanggal_lahir" => $_POST["tanggal_lahir"],
                "gaji_pokok" => $_POST["gaji_pokok"],
            ]);
            header("Location: index.php");
        } catch (Exception $e) {
            $err['nip'] = "Nip Tidak Boleh duplikat";
            var_dump($e);
        }
    }

    if (($_POST["action"] ?? '') === 'update') {
        $stmt = $db->prepare("UPDATE pegawai SET nama = :nama, tanggal_lahir = :tanggal_lahir, gaji_pokok = :gaji_pokok WHERE nip = :nip");
        $stmt->execute([
            "nip" => $_GET["nip"], // $_POST["nip"],
            "nama" => $_POST["nama"],
            "tanggal_lahir" => $_POST["tanggal_lahir"],
            "gaji_pokok" => $_POST["gaji_pokok"],
        ]);

        header("Location: index.php");
    }
}

if (isset($_GET["nip"])){
    $nip = $_GET["nip"];
    $pegawai = $db->query("SELECT * FROM pegawai WHERE nip = $nip")->fetch(PDO::FETCH_ASSOC);
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Form</title>

    <style>
        body {
            display: flex;
            justify-content: center;
        }

        form {
            border: 2px solid black;
            padding: 5px;
        }

        input {
            margin-top: 5px;
        }

        button {
            width: 265px;
            margin-top: 3px;
        }

        h1 {
            text-align: center;
        }
    </style>
</head>
<body>
    
<div class="container">
    <h1><?=ucfirst($action)?> Pegawai</h1>
    
    <form action="" method="post">
        <input type="hidden" name="action" value="<?=$action?>">

        <label for="nip">Nip</label>
        <input type="number" name="nip" required value="<?=$pegawai['nip']?>" <?= $action === 'update' ? 'disabled' : ''?>>
        <?php if ($err["nip"] !== '') : ?>
            <p style="color: red"><?=$err['nip']?></p>
        <?php endif ?>

        <br />
        
        <label for="nama">Nama</label>
        <input type="text" name="nama" required value="<?=$pegawai['nama']?>">
        
        <br />
        
        <label for="tanggal_lahir">Tanggal Lahir</label>
        <input type="date" name="tanggal_lahir" required value="<?=$pegawai['tanggal_lahir']?>">

        <br />
        
        <label for="gaji_pokok">Gaji Pokok</label>
        <input type="number" name="gaji_pokok" required value="<?=$pegawai['gaji_pokok']?>">

        <br />

        <button style="margin-top: 8px">Save</button>

        <br />

        <a class="btn-back" href="index.php"><button type="button">Back</button></a>
    </form>
</div>

</body>
</html>