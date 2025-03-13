-- MySQL dump 10.13  Distrib 5.7.35-38, for Linux (x86_64)
--
-- Host: localhost    Database: general
-- ------------------------------------------------------
-- Server version	5.7.35-38

DROP TABLE IF EXISTS `barang`;
CREATE TABLE `barang` (
  `kode` varchar(5) NOT NULL,
  `nama` varchar(20) DEFAULT NULL,
  `harga` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`kode`)
)

DROP TABLE IF EXISTS `pelanggan`;
CREATE TABLE `pelanggan` (
  `kode` varchar(5) NOT NULL,
  `nama` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`kode`)
)

DROP TABLE IF EXISTS `detail`;
CREATE TABLE `detail` (
  `no_faktur` varchar(5) NOT NULL,
  `kode_barang` varchar(5) DEFAULT NULL,
  `qty` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`no_faktur`),
  KEY `kode_barang` (`kode_barang`),
  FOREIGN KEY (`kode_barang`) REFERENCES `barang` (`kode`) ON DELETE CASCADE ON UPDATE CASCADE
)

DROP TABLE IF EXISTS `transaksi`;
CREATE TABLE `transaksi` (
  `no_faktur` varchar(5) DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `kode_pelanggan` varchar(5) DEFAULT NULL,
  KEY `no_faktur` (`no_faktur`),
  KEY `kode_pelanggan` (`kode_pelanggan`),
  FOREIGN KEY (`no_faktur`) REFERENCES `detail` (`no_faktur`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`kode_pelanggan`) REFERENCES `pelanggan` (`kode`) ON DELETE CASCADE ON UPDATE CASCADE
)