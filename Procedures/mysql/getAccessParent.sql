USE `hdm`;
DROP procedure IF EXISTS `getAccessParent`;

DELIMITER $$
USE `hdm`$$
CREATE PROCEDURE `getAccessParent`(pIdRole bigint(20))
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
        a.parent IS NULL
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
		parent IS NULL AND
        id NOT IN(
		SELECT 
			id_access 
		FROM 
			access_role
		WHERE 
			id_role = pIdRole
    )
	ORDER BY id;	
END$$

DELIMITER ;

