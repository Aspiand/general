DROP IF EXISTS `students`

CREATE TABLE `students` (             
  `sin` varchar(255) NOT NULL,        
  `name` varchar(50) NOT NULL,        
  `gender` enum('Man','Woman') DEFAULT NULL,
  `grade` char(3) DEFAULT NULL,       
  `major` varchar(50) DEFAULT NULL,   
  `address` text,                     
  PRIMARY KEY (`sin`)                 
)
