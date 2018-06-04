USE `hdm`;
DROP procedure IF EXISTS `getConfigByClient`;

DELIMITER $$
USE `hdm`$$
CREATE PROCEDURE `getConfigByClient` (pCategory bigint(20), pClient bigint(20))
BEGIN
	SELECT * FROM configuration
    WHERE id_client = pClient AND id_category = pCategory
    ORDER BY id;
END$$

DELIMITER ;