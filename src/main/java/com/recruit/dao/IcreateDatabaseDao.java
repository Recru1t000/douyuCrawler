package com.recruit.dao;

import org.apache.ibatis.annotations.Update;

public interface IcreateDatabaseDao {
    @Update("CREATE TABLE IF NOT EXISTS `chatmsg${data}`" +
            "(" +
            "`id` INT  NOT NULL AUTO_INCREMENT UNIQUE," +
            "`userName` VARCHAR(99)  NOT NULL," +
            "`userId` VARCHAR(36)  NOT NULL," +
            "`txt` VARCHAR(150) DEFAULT NULL," +
            "`userLevel` INT DEFAULT NULL," +
            "`bnn` VARCHAR(15) DEFAULT NULL," +
            "`bl` INT DEFAULT NULL," +
            "`createTime` time  NOT NULL," +
            "PRIMARY KEY(`id`,`userId`)" +
            ")")
    void createChatMsg(String data);
    @Update("CREATE TABLE IF NOT EXISTS `noblenuminfo${data}`" +
            "(" +
            "`id` INT  NOT NULL AUTO_INCREMENT UNIQUE," +
            "`nobility` INT  NOT NULL," +
            "`total` INT NOT NULL," +
            "`createTime` time  NOT NULL," +
            "PRIMARY KEY(`id`)" +
            ")")
    void createNobleNumInfo(String data);
    @Update("CREATE TABLE IF NOT EXISTS `dbg${data}`" +
            "(" +
            "`id` INT  NOT NULL AUTO_INCREMENT UNIQUE," +
            "`userName` VARCHAR(99)  NOT NULL," +
            "`userId` VARCHAR(36)  NOT NULL," +
            "`giftId` INT DEFAULT NULL," +
            "`giftNum` INT DEFAULT NULL," +
            "`createTime` time  NOT NULL," +
            "PRIMARY KEY(`id`,`userId`)" +
            ")")
    void createDbg(String data);

}
