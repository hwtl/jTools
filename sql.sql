ALTER TABLE `gudlike_fishing`.`t_point` 
CHANGE COLUMN `pointType` `typeId` INT(11) NULL ;



CREATE 
    ALGORITHM = MERGE 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `v_point` AS
    SELECT 
        `a`.`id` AS `id`,
        `a`.`typeId` AS `typeId`,
        `a`.`remark` AS `remark`,
        `a`.`createTime` AS `createTime`,
        `a`.`creator` AS `creator`,
        `a`.`updateTime` AS `updateTime`,
        `a`.`updator` AS `updator`,
        `b`.`typeName` AS `typeName`
    FROM
        (`t_point` `a`
        JOIN `t_point_type` `b` ON ((`a`.`typeId` = `b`.`id`)))
        
        
ALTER TABLE `gudlike_fishing`.`t_point` 
ADD COLUMN `latitude` DECIMAL(18,15) NULL AFTER `updator`,
ADD COLUMN `longitude` DECIMAL(18,15) NULL AFTER `latitude`;
