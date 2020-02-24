delete from USERS;
INSERT INTO USERS VALUES (1, 'testUser1', 'mypassword123'),(2, 'Jane_Doe', 'welcome123'), (3, 'WaltD', '321xyz'),(4, 'FlowerDaisy', 'ILikeFlowers');

delete from EVENT;
INSERT INTO `EVENT` VALUES (1,'Meeting with Ann','2020-02-29','09:30:00','11:00:00','This is a very important meeting. Don\'t forget to bring a pen.',1),(2,'Dentist','2020-03-03','12:30:00',NULL,'Not sure how long this will last.',1),(3,'Fundraiser','2020-03-03','16:00:00','20:00:00',NULL,1),(4,'Doctor appt for J','2020-04-07','07:45:00',NULL,'Checkup',3),(5,'Phone interview','2020-04-06','10:00:00','10:30:00','They will call me',2);
