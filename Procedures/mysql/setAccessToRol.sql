USE `hdm`;
DROP procedure IF EXISTS `getAccessParent`;

DELIMITER $$
USE `hdm`$$
CREATE PROCEDURE `setAccessToRol`(IN pIdRol bigint(20), 
								  IN pIdAccess bigint(20), 
								  IN pIdParent bigint(20))
BEGIN
	DECLARE pExist INT DEFAULT 0;
    DECLARE pAux bigint(20);
	CASE pIdParent
		WHEN 0 THEN -- Accesso solo a un Hijo de su Padre
			SET pExist = (SELECT COUNT(*) AS cant FROM access_role WHERE id_role = pIdRol AND id_access = pIdAccess);
			IF pExist > 0 THEN
				DELETE FROM access_role
                WHERE 
					id_role = pIdRol AND 
					id_access = pIdAccess;
			ELSE
				SET pAux = (SELECT parent FROM access WHERE id = pIdAccess);            
				SET pExist = (SELECT COUNT(*) AS cant FROM access_role WHERE id_role = pIdRol AND id_access = pAux);
                IF pExist = 0 THEN
					INSERT INTO access_role
					SELECT id,pIdRol
					FROM access
					WHERE id = pAux;
                END IF;
				INSERT INTO access_role
				SELECT id,pIdRol
				FROM access
				WHERE id = pIdAccess;
			END IF;
		ELSE -- Acceso a Padre e Hijos			
            SET pExist = (
				SELECT COUNT(*)
				FROM (
						SELECT * 
						FROM access_role 
						WHERE 
							id_role = pIdRol AND 
							id_access IN(
								SELECT id 
								FROM access 
								WHERE parent = pIdParent)
						UNION
						SELECT * 
						FROM access_role 
						WHERE 
							id_role = pIdRol AND 
							id_access IN(
								SELECT id 
								FROM access 
								WHERE id = pIdParent) 
				) AS RESULT);
			IF pExist > 0 THEN
				DELETE FROM access_role
                WHERE 
					id_role = pIdRol AND 
					id_access IN(
						SELECT id 
						FROM access 
						WHERE parent = pIdParent
                        
                        UNION ALL
						
                        SELECT id 
						FROM access 
						WHERE id = pIdParent
					);
            ELSE
                INSERT INTO access_role(id_access, id_role)
				SELECT id,pIdRol
				FROM access
				WHERE id = pIdParent
                UNION 
				SELECT id,pIdRol
				FROM access
				WHERE parent = pIdParent;
            END IF;
	END CASE;
END$$

DELIMITER ;
