CREATE DATABASE collector2 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE collector2;

DROP TABLE IF EXISTS RuleInfo;
CREATE TABLE RuleInfo(ruleId VARCHAR(100) PRIMARY KEY,ruleName VARCHAR(60) NOT NULL);

DROP TABLE IF EXISTS UserRoleInfo;
CREATE TABLE UserRoleInfo(userRoleId VARCHAR(100) PRIMARY KEY,userRoleName VARCHAR(60) NOT NULL,
                          userRoleNote VARCHAR(512));

DROP TABLE IF EXISTS UserRoleRuleRelation;
CREATE TABLE UserRoleRuleRelation(relationRoleId VARCHAR(100) NOT NULL,relationRuleId VARCHAR(100) NOT NULL,
                                  PRIMARY KEY (relationRoleId,relationRuleId));


DROP TABLE IF EXISTS UserInfo;
CREATE TABLE UserInfo(userNum VARCHAR(100) PRIMARY KEY,userName VARCHAR(50),userPswd VARCHAR(20),userPhone VARCHAR(15),
                      userMajorityClass VARCHAR(50) NOT NULL,userNote VARCHAR(512),userRoleId VARCHAR(100) NOT NULL );

DROP TABLE IF EXISTS ClassInfo;
CREATE TABLE ClassInfo(classId VARCHAR(100) PRIMARY KEY,majorityName VARCHAR(50) NOT NULL,classGrade VARCHAR(10) NOT NULL,
                      classNum VARCHAR(10) NOT NULL,majorityClass VARCHAR(80) NOT NULL,monitorId VARCHAR(100) NOT  NULL,
                      classNote VARCHAR(512));

DROP TABLE IF EXISTS MessageInfo;
CREATE TABLE MessageInfo(messageId VARCHAR(100) PRIMARY KEY,recieverId VARCHAR(100) NOT NULL,
senderId varchar(100) NOT NULL,sendDateTime varchar(20),startTime VARCHAR(20),endTime
VARCHAR(20),currentStatus int(2),messageContent VARCHAR(512));

ALTER TABLE UserRoleRuleRelation ADD CONSTRAINT relationRoleFK FOREIGN KEY (relationRoleId) REFERENCES UserRoleInfo(userRoleId) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE UserRoleRuleRelation ADD CONSTRAINT relationRuleFK FOREIGN KEY (relationRuleId) REFERENCES RuleInfo(ruleId) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE UserInfo ADD CONSTRAINT userInfoRoleFK FOREIGN KEY (userRoleId) REFERENCES UserRoleInfo(userRoleId) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE MessageInfo ADD CONSTRAINT messageUserFK1 FOREIGN KEY (senderId) REFERENCES UserInfo(userNum) ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE MessageInfo ADD CONSTRAINT messageUserFk2 FOREIGN KEY (recieverId) REFERENCES UserInfo(userNum) ON UPDATE CASCADE ON DELETE CASCADE;

INSERT INTO UserRoleInfo(userRoleId,userRoleName,userRoleNote)VALUES
('2809ca86-ee93-406a-b6f4-038fa8866c2e','admin','');
INSERT INTO UserRoleInfo VALUES ('0fac12ab-5a70-4d69-b7d5-9ba787180a32','monitor','');
INSERT INTO UserRoleInfo VALUES ('ef50010f-e2f5-4e55-abc1-843d3723d61b','primary','');

INSERT INTO RuleInfo(ruleId, ruleName) VALUES ('bcfb0721-daea-44ce-ab7e-9b3ac288fd70','createClass');

INSERT INTO UserRoleRuleRelation (relationRoleId,relationRuleId) VALUES
('0fac12ab-5a70-4d69-b7d5-9ba787180a32','bcfb0721-daea-44ce-ab7e-9b3ac288fd70');