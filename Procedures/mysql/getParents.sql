USE `hdm`;
DROP procedure IF EXISTS `getParents`;

DELIMITER $$
USE `hdm`$$
CREATE PROCEDURE `getParents`()
BEGIN
	SELECT *
    FROM access
    WHERE parent IS NULL
    ORDER BY id;
END$$

DELIMITER ;