<?php
require 'vendor/autoload.php';
use Dompdf\Dompdf;

$db_path = __DIR__ . "/database.sqlite3";

try {
    $db = new PDO("sqlite:$db_path");
    $db->setAttribute( PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION );
} catch (Exception $e) {
    if ( (PHP_OS_FAMILY !== "Windows") && ($e->getCode() === 14) ) {
        $file_info = stat($db_path);

        echo "<pre>";
        var_dump([
            "File Permission" => [
                "User" => posix_getpwuid($file_info['uid'])['name'],
                "Group" => posix_getgrgid($file_info['gid'])['name'],
                "Permission" => substr(sprintf('%o', $file_info['mode']), -4)    
            ],
            "Message" => $e->getMessage(),
        ]);
        echo "</pre>";
    };

    die($e->getMessage());
}

function is_set($key, $value) : bool {
    return (
        (
            isset($_GET[$key]) && $_GET[$key] == $value
        ) || (
            isset($_POST[$key]) && $_POST[$key] == $value
        )
    ) ? true : false;
};

$db->exec("
    CREATE TABLE IF NOT EXISTS `siswa` (
        `nis`	INTEGER NOT NULL UNIQUE,
        `nama`	TEXT NOT NULL,
        `kelas`	TEXT,
        `jk`	TEXT,
        PRIMARY KEY(`nis` AUTOINCREMENT)
    );
");

// PDF
if (is_set("action", "print")) {
    $tmp = "";
    foreach ($db->query(
        "SELECT * FROM `siswa` ORDER BY nama ASC"
    )->fetchAll(PDO::FETCH_ASSOC) as $i => $item) {
        $tmp .= "
            <tr>
                <td>$i</td>
                <td>".$item["nama"]."</td>
                <td>".$item["kelas"]."</td>
                <td>".$item["jk"]."</td>
            </tr>
        ";
    };

    $pdf = new Dompdf();
    $pdf->loadHtml("
        <center><h3>Daftar Nama Siswa</h3></center>
        <hr/><br/>
        <table border='1' width='100%'>
            <tr>
                <td>No</td>
                <td>Nama</td>
                <td>Kelas</td>
                <td>Jenis Kelamin</td>
            </tr>

            $tmp
        </table>
    ");
    $pdf->setPaper("A4");
    $pdf->render();
    $pdf->stream("Report.pdf");
}

// Delete
else if ( is_set("action", "delete") && isset($_GET["nis"]) ) {
    $stmt = $db->prepare("DELETE FROM `siswa` WHERE nis = :nis");
    $stmt->bindParam(":nis", $_GET["nis"], PDO::PARAM_INT);
    $stmt->execute();
}

// Insert
else if ( is_set("action", "add") ) {
    if (
        (isset($_POST["input_name"]) && strlen($_POST["input_name"]) > 0) &&
        (isset($_POST["input_kelas"]) && strlen($_POST["input_kelas"]) > 0) &&
        (isset($_POST["input_jk"]) && strlen($_POST["input_jk"]) > 0)
    ) {
        $stmt = $db->prepare("INSERT INTO `siswa` (nama, kelas, jk) VALUES (:nama, :kelas, :jk)");
        $stmt->bindValue(":nama", $_POST["input_name"], PDO::PARAM_STR);
        $stmt->bindValue(":kelas", $_POST["input_kelas"], PDO::PARAM_STR);
        $stmt->bindValue(":jk", $_POST["input_jk"], PDO::PARAM_STR);
        $stmt->execute();
    };
}

// Update
else if ( is_set("action", "update") ) {
    if (isset($_POST["input_nis"])) {
        $stmt = $db->prepare("UPDATE `siswa` SET nama = :nama, kelas = :kelas, jk = :jenis_kelamin WHERE nis = :nis");
        $stmt->bindValue(":nis", $_POST["input_nis"], PDO::PARAM_INT);
        $stmt->bindValue(":nama", $_POST["input_name"] ?? "Fulan", PDO::PARAM_STR);
        $stmt->bindValue(":kelas", $_POST["input_kelas"] ?? "Undefined", PDO::PARAM_STR);
        $stmt->bindValue(":jenis_kelamin", $_POST["input_jk"] ?? "Undefined", PDO::PARAM_STR);
        $stmt->execute();
    };
}

// Delete All Data
else if ( is_set("action", "reset") ) {
    $db->exec("DELETE FROM siswa");
}

// Generate Dummy Data
else if ( is_set("action", "generate") ) {
    $array_kelas  = ["X", "XI", "XII"];
    $array_jk     = ["Pria", "Wanita", "Stainless Steel"];

    $kelas = $array_kelas[array_rand($array_kelas)];
    $jk = $array_jk[array_rand($array_jk)];

    $db->exec("INSERT INTO siswa (nama, kelas, jk) VALUES ('Astagfirullah', '$kelas', '$jk')");
};

$list_siswa = $db->query("SELECT * FROM `siswa`")->fetchAll(PDO::FETCH_ASSOC);

// Debug
echo "<pre>";
var_dump($_POST);
echo "</pre>";