USE `hdm`;
DROP procedure IF EXISTS `getAccessChild`;

DELIMITER $$
USE `hdm`$$
CREATE PROCEDURE `getAccessChild`(pIdRole bigint(20), pIdParent bigint(20))
BEGIN
	SELECT 
		a.id,
		a.code,
		a.description,
		a.icon,
        a.parent,
		1 active
	FROM access a 
	INNER JOIN access_role ar ON ar.id_access = a.id
	WHERE
		ar.id_role = pIdRole AND 
        a.parent = pIdParent
	UNION
	SELECT 
		id,
		code,
		description,
		icon,
        parent,
		0 active
	FROM access
	WHERE 
		parent = pIdParent AND 
        id not in(
			SELECT id_access 
			FROM access_role
			WHERE id_role = pIdRole
		)
	ORDER BY id;
END$$

DELIMITER ;

