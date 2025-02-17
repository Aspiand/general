<?php

// Autoload Class
spl_autoload_register(function ($class) {
    require_once __DIR__ . "/classes/$class.php";
});

require __DIR__ . "/koneksi.php";
require __DIR__ . "/utils.php";
require __DIR__ . "/../vendor/autoload.php";

use Dompdf\Dompdf;

// Start Sessions
start();

$users = new Users($db);

if (is_set("action", "login")) {
    if ($users->login($_POST["username"], $_POST["password"])) {
        $_SESSION["login"] = true;
        $_SESSION["username"] = $_POST["username"];

        header("location: index.php");
    }
}

else if (
    is_set("action", "register") &&
    isset($_POST["username"]) &&
    isset($_POST["password"])
) {
    if ($users->add($_POST["username"],  $_POST["password"])) {
        header("location: login.php");
    }
}

else if (is_set("action", "logout")) {
    session_destroy();
    header("location: login.php");
}

else if (is_set("action", "dompdf")) {
    // Thanks to Malsoryz
    // https://github.com/Malsoryz/Penggajian-dompdf/blob/d8f307779917e210b861de91febe07ec70017110/php/print_pdf.php

    $stmt = $db->prepare("
        SELECT * FROM karyawan JOIN golongan ON karyawan.kode_golongan = golongan.kode_golongan
        WHERE nip = :nip
    ");
    $stmt->bindParam("nip", $_POST['karyawan_id'], PDO::PARAM_INT);
    $stmt->execute();
    $data = $stmt->fetch(PDO::FETCH_ASSOC);

    $nama = $data['nama'];
    $golongan = $data['golongan'];
    $bulan = ucwords($_POST['bulan']);
    $gaji_pokok = $_POST['gaji'];
    $tunjangan_jabatan = $_POST['jabatan'];
    $tunjangan1 = $_POST['nikah'];
    $tunjangan2 = $_POST['anak'];
    $bpjs = $_POST['bpjs'];
    $pajak = $_POST['pajak'];
    $gaji_bersih = $_POST['gaji_bersih'];

    $html = "
        <body style='font-family: sans-serif;'>
            <center>SLIP GAJI BULANAN 2024</center><br/>
            <table>
                <tr>
                    <td>Nama</td>
                    <td>:</td>
                    <td>$nama</td>
                </tr>
                <tr>
                    <td>Bulan</td>
                    <td>:</td>
                    <td>$bulan</td>
                </tr>
                <tr>
                    <td>Golongan&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>:</td>
                    <td>$golongan</td>
                </tr>
            </table>
            <br>
            Detail Pendapatan
            <br>
            <table border='1' cellpadding='5' cellspacing='0' width='100%'>
                <tr>
                    <td style='width: 200px;'>Gaji Pokok</td>
                    <td style='text-align: right;'>$gaji_pokok</td>
                </tr>
                <tr>
                    <td style='width: 200px;'>Tunjangan Jabatan</td>
                    <td style='text-align: right;'>$tunjangan_jabatan</td>
                </tr>
                <tr>
                    <td style='width: 200px;'>Tunjangan Suami/Istri</td>
                    <td style='text-align: right;'>$tunjangan1</td>
                </tr>
                <tr>
                    <td style='width: 200px;'>Tunjangan Anak</td>
                    <td style='text-align: right;'>$tunjangan2</td>
                </tr>
                <tr>
                    <td style='width: 200px;'>Potongan BPJS</td>
                    <td style='text-align: right;'>$bpjs</td>
                </tr>
                <tr>
                    <td style='width: 200px;'>Pajak</td>
                    <td style='text-align: right;'>$pajak</td>
                </tr>
                <tr>
                    <td style='width: 200px;'><strong>Diterima</strong></td>
                    <td style='text-align: right;'><strong>$gaji_bersih</strong></td>
                </tr>
            </table>
        </body>
    ";

    $dompdf = new Dompdf();
    $dompdf->loadHtml($html);
    $dompdf->setPaper('A4');
    $dompdf->render();
    $dompdf->stream("gaji_bulanan.pdf", ["Attachment" => 1]);
}