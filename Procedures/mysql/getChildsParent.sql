USE `hdm`;
DROP procedure IF EXISTS `getChildsParent`;

DELIMITER $$
USE `hdm`$$
CREATE PROCEDURE `getChildsParent`(IN pIdParent bigint(20))
BEGIN
	SELECT *
    FROM access
    WHERE parent = pIdParent
    ORDER BY id;
END$$

DELIMITER ;
