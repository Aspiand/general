<?php

require "./bootstrap.php";

$action = $_GET["action"] ?? "";

if ($action === "delete" && isset($_GET["nip"])) {
    $stmt = $db->prepare("DELETE FROM pegawai WHERE nip = :nip");
    $stmt->execute([
        "nip" => $_GET["nip"],
    ]);
}

$pegawai = $db->query("SELECT * FROM pegawai")->fetchAll(PDO::FETCH_ASSOC);

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pegawai</title>

    <style>
        body {
            display: flex;
            justify-content: center;
        }

        tr, td {
            padding: 10px;
        }

        .actions {
            display: flex;
        }
    </style>
</head>
<body>

    <table border="5px">
        <thead>
            <tr>
                <td>NIP</td>
                <td>Nama</td>
                <td>Tanggal Lahir</td>
                <td>Gaji Pokok</td>
                <td><a href="form.php?action=add"><button>Add</button></a></td>
            </tr>
        </thead>

        <tbody>
            <?php foreach ($pegawai as $p) : ?>
                <tr>
                    <td><?=$p["nip"]?></td>
                    <td><?=htmlspecialchars($p["nama"])?></td>
                    <td><?=$p["tanggal_lahir"]?></td>
                    <td><?=$p["gaji_pokok"]?></td>
                    <td>
                        <div class="actions">
                            <a href="form.php?action=update&nip=<?=$p['nip']?>"><button>Edit</button></a>
                            <a href="index.php?action=delete&nip=<?=$p['nip']?>"><button>Delete</button></a>
                        </div>
                    </td>
                </tr>
            <?php endforeach ?>
        </tbody>
    </table>

    <script>
        <?php if (isset($_SESSION["message"])) : $msg = $_SESSION["message"];?>
            alert(<?=$msg?>)
        <?php endif?>
    </script>
    
</body>
</html>