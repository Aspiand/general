<?php
require "core/init.php";
check();

$BULAN = [
    "januari",
    "februari",
    "maret",
    "april",
    "mei",
    "juni",
    "juli",
    "agustus",
    "september",
    "oktober",
    "november",
    "desember"
];

$stmt = $db->prepare("SELECT * FROM karyawan JOIN golongan ON karyawan.kode_golongan = golongan.kode_golongan");
$stmt->execute();
$karyawan = $stmt->fetchAll(PDO::FETCH_ASSOC);

?>
<!DOCTYPE html>
<html lang="en">
<?= template("head", ["title" => "Penggajian"]) ?>

<body class="sb-nav-fixed">
    <script>
        let gaji = []
    </script>

    <?= template("navbar") ?>

    <div id="layoutSidenav">

        <?= template("sidebar") ?>

        <div id="layoutSidenav_content">

            <div class="container px-4 mt-4">
                <h3>Penggajian Karyawan</h3><br>
                <div class="card">
                    <div class="card-header bg-primary text-light">
                        Buat Slip Gaji
                    </div>

                    <div class="card-body">
                        <form action="" method="POST">
                            <label for="bulan">Bulan</label>
                            <select name="bulan" class="form-control mb-2" id="bulan">
                                <?php foreach ($BULAN as $b) : ?>
                                    <option value="<?= $b ?>">
                                        <?= ucfirst($b) ?>
                                    </option>
                                <?php endforeach ?>
                            </select>

                            <label for="karyawan_id">Nama Karyawan</label>
                            <select class="form-control mb-2" name="karyawan_id" id="karyawan_id">

                                <?php foreach ($karyawan as $k) : ?>
                                    <option value="<?= $k['nip'] ?>">
                                        <?= $k['nama'] ?> (Golongan: <?= $k['golongan'] ?>)
                                    </option>

                                    <script>
                                        gaji["<?= $k["nip"] ?>"] = <?= $k["gaji_pokok"] ?>
                                    </script>
                                <?php endforeach ?>

                            </select>

                            <input type="hidden" name="golongan" id="golongan" value="III/A">

                            <label for="gaji_pokok">Gaji Pokok</label>
                            <input value="2000" type="number" name="gaji_pokok" id="gaji_pokok" class="form-control mb-2" disabled>

                            <label for="tunjangan_jabatan">Tunjangan Jabatan</label>
                            <select class="form-control mb-2" name="tunjangan_jabatan" id="tunjangan_jabatan">
                                <option value="0">Tidak ada</option>
                                <option value="500000">Kepala Bagian</option>
                                <option value="250000">Staff</option>
                            </select>

                            <hr>
                            <p class="fw-bold">Tunjangan Lainnya</p>

                            <label for="tunjangan_nikah">Menikah</label>
                            <select class="form-control mb-2" name="tunjangan_nikah" id="tunjangan_nikah">
                                <option value="0">Belum</option>
                                <option value="250000">Sudah</option>
                            </select>

                            <label for="tunjangan_anak">Tunjangan Anak</label><br>
                            <input checked disabled class="form-check-input" type="radio" name="tunjangan_anak" value="0" id="anak_0">
                            <label for="anak_0">Tidak ada</label>

                            <input disabled class="form-check-input" type="radio" name="tunjangan_anak" value="200000" id="anak_1">
                            <label for="anak_1">1 Anak</label>

                            <input disabled class="form-check-input" type="radio" name="tunjangan_anak" value="400000" id="anak_2">
                            <label for="anak_2">2 Anak</label>

                            <hr>
                            <p class="fw-bold">Potongan</p>
                            <blockquote class="fw-bold fst-italic text-danger" style="font-family: monospace;">
                                * Potongan BPJS Kesehatan Sebesar Rp.34.000,- <br>
                                * Potongan Pajak 15% bagi Golongan IV, dan 5% bagi Golongan III
                            </blockquote>

                            <hr>
                            <p class="fw-bold">Rincian Slip Gaji</p>
                            <table class="table">
                                <tr>
                                    <td>Gaji</td>
                                    <td><input type="text" name="gaji" id="gaji" class="form-control-plaintext" value="Rp.0" readonly></td>
                                </tr>
                                <tr>
                                    <td>Tunjangan Jabatan</td>
                                    <td><input type="text" name="jabatan" id="jabatan" class="form-control-plaintext" value="Rp.0" readonly></td>
                                </tr>
                                <tr>
                                    <td>Tunjangan Suami</td>
                                    <td><input type="text" name="nikah" id="nikah" class="form-control-plaintext" value="Rp.0" readonly></td>
                                </tr>
                                <tr>
                                    <td>Tunjangan Anak</td>
                                    <td><input type="text" name="anak" id="anak" class="form-control-plaintext" value="Rp.0" readonly></td>
                                </tr>
                                <tr>
                                    <td>Potongan BPJS</td>
                                    <td><input type="text" name="bpjs" id="bpjs" class="form-control-plaintext" value="Rp.0" readonly></td>
                                </tr>
                                <tr>
                                    <td>Potongan Pajak</td>
                                    <td><input type="text" name="pajak" id="pajak" class="form-control-plaintext" value="Rp.0" readonly></td>
                                </tr>
                                <tr class="fw-bold">
                                    <td>Gaji Bersih</td>
                                    <td><input type="text" name="gaji_bersih" id="gaji_bersih" class="form-control-plaintext" value="Rp.0" readonly></td>
                                </tr>
                            </table>

                            <button id="calculate" type="button" class="form-control btn btn-primary mt-1 text-white" onclick="hitung()">Hitung Pendapatan</button>
                            <button id="slip" type="submit" class="form-control btn btn-success mb-3 mt-1" name="action" value="dompdf" hidden>Buat Slip</button>
                        </form>
                    </div>

                </div>
            </div>

            <?= template("footer") ?>

        </div>
    </div>

    <?= template("js") ?>
    <script>
        function hitung() {
            document.getElementById("slip").hidden = false

            const formater = Intl.NumberFormat("id-ID", {
                style: "currency",
                currency: "IDR",
                minimumFractionDigits: 0,
                maximumFractionDigits: 0
            })

            // if it works don't touch it
            /// start
            let golongan = document.getElementById("golongan").value;
            let gapok = parseInt(document.getElementById("gaji_pokok").value);
            let tunjab = parseInt(document.getElementById("tunjangan_jabatan").value);
            let tunjangan1 = parseInt(document.getElementById("tunjangan_nikah").value);
            let anak = document.querySelector("input[name='tunjangan_anak']:checked");

            let tunjangan2 = 0;
            if (anak) {
                tunjangan2 = parseInt(anak.value);
            }

            let bpjs = 34000;

            let pendapatan = Number(gapok + tunjab + tunjangan1 + tunjangan2);
            let bersih = 0;
            let potongan = 0;

            const arr = golongan.split("/");
            let gol = arr[0];
            if (gol == "III") {
                potongan = pendapatan * 0.05;
                bersih = pendapatan - (potongan + bpjs);
            } else if (gol == "IV") {
                potongan = pendapatan * 0.15;
                bersih = pendapatan - (potongan + bpjs);
            }

            // document.getElementById("gaji").value = "Rp." + (new Intl.NumberFormat().format(gapok));
            // document.getElementById("jabatan").value = "Rp." + (new Intl.NumberFormat().format(tunjab));
            // document.getElementById("nikah").value = "Rp." + (new Intl.NumberFormat().format(tunjangan1));
            // document.getElementById("anak").value = "Rp." + (new Intl.NumberFormat().format(tunjangan2));
            // document.getElementById("bpjs").value = "Rp." + (new Intl.NumberFormat().format(bpjs));
            // document.getElementById("pajak").value = "Rp." + (new Intl.NumberFormat().format(potongan));
            // document.getElementById("gaji_bersih").value = "Rp." + (new Intl.NumberFormat().format(bersih));
            /// end

            [
                ["gaji", gapok],
                ["jabatan", tunjab],
                ["nikah", tunjangan1],
                ["anak", tunjangan2],
                ["bpjs", bpjs],
                ["pajak", potongan],
                ["gaji_bersih", bersih]
            ].forEach(([element_id, value]) => {
                document.getElementById(element_id).value = formater.format(value)
            })
        }
    </script>

    <script>
        const karyawan_id = document.getElementById("karyawan_id")
        const gaji_pokok = document.getElementById("gaji_pokok")
        const radio_anak = document.querySelectorAll("input[name='tunjangan_anak']")
        const tunjangan_menikah = document.getElementById("tunjangan_nikah")

        tunjangan_menikah.addEventListener("change", () => {

            // disable radio tunjangan anak jika belum menikah
            radio_anak.forEach((radio) => {
                radio.disabled = tunjangan_menikah.value === "0"
            })

            // mengatur agar radio 'Tidak ada' dipilih secara
            // otomatis ketika status pernikahan berubah
            document.getElementById("anak_0").checked = true
        })


        // mengatur agar gaji pokok berubah sesuai dengan karyawan
        karyawan_id.addEventListener("change", () => {
            gaji_pokok.value = gaji[karyawan_id.value] || 0
        })

        karyawan_id.dispatchEvent(new Event("change"))
    </script>
</body>

</html>