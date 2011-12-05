use hqdb;

/*This script create all table of database*/

/*1.  Acount table*/
DROP TABLE IF EXISTS `Account`;
CREATE TABLE `Account`
(
`accountId` VARCHAR(20) not null,
`email` VARCHAR(50) NOT null,
`password` VARCHAR(50) NOT null,
`fullName` VARCHAR(50) NOT null,
PRIMARY KEY(`accountId`)
) Engine=InnoDB;

/*1. Bank Acount table*/
DROP TABLE IF EXISTS `BankAccount`;
CREATE TABLE `BankAccount`
(
`bankAccountId` VARCHAR(20) not null,
`bankAccountName` VARCHAR(50) NOT null,
`bankName` VARCHAR(50) NOT null,
`bankAccountNumber` VARCHAR(20) NOT null,
`routingNumber` VARCHAR(30),
`accountId` varchar(20), 
PRIMARY KEY(`bankAccountId`, `accountId`)
) Engine=InnoDB;



