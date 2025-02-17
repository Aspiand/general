<?php

require "core/init.php";

$query = <<<EOT
-- User table
CREATE TABLE IF NOT EXISTS `users` (
    `username` VARCHAR(255) NOT NULL,
    `password` TEXT NOT NULL,
    PRIMARY KEY (`username`)
);

-- Core tables
CREATE TABLE IF NOT EXISTS `golongan` (
    `kode_golongan` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `golongan` VARCHAR(10),
    `gaji_pokok` BIGINT
);

CREATE TABLE IF NOT EXISTS `karyawan` (
    `nip` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `nama` VARCHAR(50),
    `alamat` TEXT,
    `jk` VARCHAR(1),
    `kode_golongan` INTEGER,
    FOREIGN KEY (`kode_golongan`) REFERENCES `golongan` (`kode_golongan`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS `penggajian` (
    `kode_penggajian` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `nip_karyawan` INTEGER,
    `tanggal` DATE,
    `status` VARCHAR(10),
    FOREIGN KEY (`nip_karyawan`) REFERENCES `karyawan` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE
);

DELETE FROM `users`;
DELETE FROM `golongan`;
DELETE FROM `karyawan`;
DELETE FROM `penggajian`;

INSERT INTO `users` (`username`, `password`) VALUES
('aspian', '$2y$10\$wFnbU5YaTbL.7vk45l/TW.KldtjZa1SYNzMe9CtCSmjuNaf5wldO6'), -- 123
('admin', '$2y$10\$yEgkfpuz.EAW0gNqEs9zpeTXr.74pzfLY3PknmXOQK/taaugGZRlW'); -- 321

INSERT INTO `golongan` (`golongan`, `gaji_pokok`) VALUES 
('III/A', 3600000),
('III/B', 3750000),
('III/C', 4000000),
('III/D', 4250000),
('IV/A', 4750000),
('IV/B', 5000000);

INSERT INTO `karyawan` (`nama`, `alamat`, `jk`, `kode_golongan`) VALUES 
('Ahmad Kasim', 'Di sana', 'l', 2),
('Dimas', 'Di sini', 'l', 3),
('Rusdi', 'Di luar', 'l', 5),
('Hinata', 'idk', 'p', 6);

INSERT INTO `penggajian` (`nip_karyawan`, `tanggal`, `status`) VALUES 
(1, '2024-09-11', 'menikah'),
(2, '2024-11-09', 'single'),
(3, '2024-12-1', 'single'),
(4, '2024-12-12', 'menikah');
EOT;

try {
    $db->exec($query);
    echo "Success!\n";
} catch (PDOException $e) {
    echo "Error: " . $e->getMessage();
}