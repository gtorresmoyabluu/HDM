USE `hdm`;
DROP procedure IF EXISTS `getAccessChildByIdRol`;

DELIMITER $$
USE `hdm`$$
CREATE PROCEDURE `getAccessChildByIdRol`(pIdRole bigint(20), pIdParent bigint(20))
BEGIN
	SELECT 
		a.id,
		a.code,
		a.description,
		a.icon,
        a.parent,
        1 AS active
	FROM access a
	INNER JOIN access_role ar ON ar.id_access = a.id
	WHERE
		ar.id_role = pIdRole AND 
        a.parent = pIdParent
	ORDER BY a.id; 
END$$

DELIMITER ;