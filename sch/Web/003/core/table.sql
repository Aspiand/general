-- User
CREATE TABLE `users` (
    `username` varchar(255) NOT NULL,
    `email` text,
    `password` text NOT NULL,
    PRIMARY KEY (`username`)
)

-- Core
CREATE TABLE golongan(
    kode_golongan INTEGER PRIMARY KEY AUTO_INCREMENT,
    golongan VARCHAR(10),
    gaji_pokok BIGINT
);

CREATE TABLE karyawan (
    nip INTEGER PRIMARY KEY AUTO_INCREMENT,
    nama VARCHAR(50),
    alamat TEXT,
    jk VARCHAR(1),
    kode_golongan INTEGER,
    FOREIGN KEY (`kode_golongan`) REFERENCES `golongan` (`kode_golongan`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE penggajian (
    kode_penggajian INTEGER PRIMARY KEY AUTO_INCREMENT,
    nip_karyawan INTEGER,
    tanggal DATE,
    status VARCHAR(10),
    FOREIGN KEY (`nip_karyawan`) REFERENCES `karyawan` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE
);
