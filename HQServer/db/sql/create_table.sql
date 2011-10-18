use hqdb;

/*This script create all table of database*/

/*1.  Acount table*/
DROP TABLE IF EXISTS `Account`;
CREATE TABLE `Account`
(
`AccountId` VARCHAR(10) not null,
`Email` VARCHAR(50) NOT null,
`Password` VARCHAR(50) NOT null,
`FirstLastName` VARCHAR(50) NOT null,
PRIMARY KEY(`AccountId`)
) Engine=InnoDB;

/*1. Bank Acount table*/
DROP TABLE IF EXISTS `BankAccount`;
CREATE TABLE `BankAccount`
(
`BankAccountId` VARCHAR(10) not null,
`BankAccountName` VARCHAR(50) NOT null,
`BankName` VARCHAR(50) NOT null,
`BankAccountNumber` VARCHAR(20) NOT null,
`RoutingNumber` VARCHAR(30),
`AccountId` varchar(50) 
PRIMARY KEY(`BankAccountId`, `AccountId`)
) Engine=InnoDB;



