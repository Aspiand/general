CREATE TABLE `daftar_nomor` (            
  `kode` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(255) DEFAULT NULL,      
  `telepon` varchar(255) DEFAULT NULL,   
  `email` varchar(50) DEFAULT NULL,      
  `alamat` text,                         
  `status` varchar(24) DEFAULT NULL,     
  PRIMARY KEY (`kode`)                   
) ENGINE=InnoDB DEFAULT CHARSET=latin1
