CREATE DATABASE collector2 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE collector2;

CREATE TABLE RuleInfo(ruleId VARCHAR(100) PRIMARY KEY,ruleName VARCHAR(60) NOT NULL);

CREATE TABLE UserRoleInfo(userRoleId VARCHAR(100) PRIMARY KEY,userRoleName VARCHAR(60) NOT NULL,
                          userRoleNote VARCHAR(512));

CREATE TABLE UserRoleRuleRelation(relationRoleId VARCHAR(100) NOT NULL,relationRuleId VARCHAR(100) NOT NULL,
                                  PRIMARY KEY (relationRoleId,relationRuleId));

-- ALTER TABLE UserRoleRuleRelation ADD CONSTRAINT relationRoleFK FOREIGN KEY relationRoleId REFERENCES UserRoleInfo(userRoleId) ON UPDATE CASCADE ON DELETE CASCADE;
-- ALTER TABLE UserRoleRuleRelation ADD CONSTRAINT relationRuleFK FOREIGN KEY relationRuleId REFERENCES RuleInfo(ruleId) ON UPDATE CASCADE ON DELETE CASCADE;


CREATE TABLE UserInfo(userNum VARCHAR(100) PRIMARY KEY,userName VARCHAR(50),userPswd VARCHAR(20),userPhone VARCHAR(15),
                      userMajorityClass VARCHAR(50) NOT NULL,userNote VARCHAR(512),userRoleId VARCHAR(100) NOT NULL );
--  ALTER TABLE UserInfo ADD CONSTRAINT userInfoRoleFK FOREIGN KEY userRoleId REFERENCES UserRoleInfo(userRoleId) ON UPDATE CASCADE ON DELETE CASCADE;

-- delete from UserInfo;delete from UserRoleInfo;delete from RuleInfo;delete from UserRoleRuleRelation;

CREATE TABLE CLassInfo(classId VARCHAR(100) PRIMARY KEY,majorityName VARCHAR(50) NOT NULL,classGrade VARCHAR(10) NOT NULL,
                      classNum VARCHAR(10) NOT NULL,majorityClass VARCHAR(80) NOT NULL,monitorId VARCHAR(100) NOT  NULL,
                      classNote VARCHAR(512));
