```sql
CREATE TABLE `students` (             
  `sin` varchar(255) NOT NULL,        
  `name` varchar(50) NOT NULL,        
  `gender` enum('W','M') DEFAULT NULL,
  `grade` char(3) DEFAULT NULL,       
  `major` varchar(50) DEFAULT NULL,   
  `address` text,                     
  PRIMARY KEY (`sin`)                 
) ENGINE=InnoDB DEFAULT CHARSET=latin1
```
