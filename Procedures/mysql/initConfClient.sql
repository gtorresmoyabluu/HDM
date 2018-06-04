USE `hdm`;
DROP procedure IF EXISTS `initConfClient`;

DELIMITER $$
USE `hdm`$$
CREATE  PROCEDURE `initConfClient`(p_IdClient bigint(20))
BEGIN
	INSERT INTO configuration(
		data_key,
        data_value,
        fieldtype,
        fieldvalues,
        ttcreation,
        id_client,
        id_category)
	SELECT 
		data_key,
        '' data_value,
        fieldtype,
        fieldvalues,
        NOW() ttcreation,
        p_IdClient id_client,
        id_category
    FROM configuration
    WHERE id_client IS NULL;
    COMMIT;
END$$

DELIMITER ;

