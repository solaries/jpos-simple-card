use hqdb;

/* Script for foreign key for DB Story Online */
/*1.  User table*/
alter table `BankAccount`
add constraint `FK_BankAccount_Account` foreign key(`AccountId`) references `Account`(`AccountId`);