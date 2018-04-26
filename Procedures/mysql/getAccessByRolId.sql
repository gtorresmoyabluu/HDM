USE `hdm`;
DROP procedure IF EXISTS `getAccessByRolId`;

DELIMITER $$
USE `hdm`$$
CREATE PROCEDURE `getAccessByRolId`(in pIdRole bigint(20))
BEGIN
	SELECT 
		a.id,
		a.code,
		a.description,
		a.icon,
        IFNULL(a.parent,0) AS parent,
        1 AS active
	FROM access a
	INNER JOIN access_role ar ON ar.id_access = a.id
	WHERE 
		ar.id_role = pIdRole AND 
        a.parent IS NULL 
	ORDER BY a.id;
END$$

DELIMITER ;