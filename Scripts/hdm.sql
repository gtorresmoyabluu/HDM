-- Insert Access
insert into access(id,description,parent,code,icon) values(1,'Administración',NULL,'administration','settings');
insert into access(id,description,parent,code,icon) values(2,'Gestion de Usuarios',1,'users','account_circle');
insert into access(id,description,parent,code,icon) values(3,'Gestión de Roles',1,'roles','supervisor_account');
insert into access(id,description,parent,code,icon) values(4,'Gestion de Logs',1,'logs','announcement');
insert into access(id,description,parent,code,icon) values(5,'Control de Permisos',1,'access','assignment');
insert into access(id,description,parent,code,icon) values(6,'Gestion de Licencias',1,'licence','security');
insert into access(id,description,parent,code,icon) values(7,'Arranque & Parada',1,'reload','repeat');
insert into access(id,description,parent,code,icon) values(8,'Configuración del Sistema',1,'configuration','settings');
insert into access(id,description,parent,code,icon) values(9,'Gestión Categorias',1,'category','view_headline');
insert into access(id,description,parent,code,icon) values(10,'Gestion Inventario',NULL,'inventory','view_list');
insert into access(id,description,parent,code,icon) values(11,'Fabricantes',10,'manufacturer','business');
insert into access(id,description,parent,code,icon) values(12,'Modelos',10,'model','devices');
insert into access(id,description,parent,code,icon) values(13,'CPEs',10,'cpe','devices');
insert into access(id,description,parent,code,icon) values(14,'Administracion Protocolos',10,'protocol','settings');
insert into access(id,description,parent,code,icon) values(15,'Provisionamiento',NULL,'provisioning','lock');
insert into access(id,description,parent,code,icon) values(16,'Funcionalidades TR069',15,'tr069',NULL);
insert into access(id,description,parent,code,icon) values(17,'Funcionalidades XMPP',15,'xmpp',NULL);
insert into access(id,description,parent,code,icon) values(18,'Programación Periódica',NULL,'programming','schedule');
insert into access(id,description,parent,code,icon) values(19,'Programaciones Existentes',18,'view_prog','today');
insert into access(id,description,parent,code,icon) values(20,'Conf. Envio Periodico',18,'send_periodic','send');
insert into access(id,description,parent,code,icon) values(21,'Gestion Firmware',NULL,'firmware','storage');
insert into access(id,description,parent,code,icon) values(22,'Vista de ficheros ',21,'view_files','folder');
insert into access(id,description,parent,code,icon) values(23,'Cambio Firmware',21,'change_firmware','file_upload');
insert into access(id,description,parent,code,icon) values(24,'Reportes',NULL,'reports','report');

-- Insert ROL Admin
insert into role(id,name) values(1,'ADMIN');

-- Insert Access by Rol
insert into access_role(id_access,id_role)
select
	id,1
from access
where id <> 9;

-- Insert User Admin
INSERT INTO user(id,active,creation_date,email,firstname,last_login,last_modification,lastname,password,username,id_client,id_role)
VALUES (1,1,now(),'admin@admin.cl','Administrator',NULL,NULL,'System HDM','$2a$16$Hj12reASapBD8b6mA5D83uZuO/pGcx7YE19OqK9ey2dcnxpcopbGC','admin',NULL,1);

-- Insert Messages Only Language ES
insert into message(id,locale,code,description) values(1,'es','login_user','Usuario');
insert into message(id,locale,code,description) values(2,'es','login_passwd','Contraseña');
insert into message(id,locale,code,description) values(3,'es','profile_menu','Perfil');
insert into message(id,locale,code,description) values(4,'es','profile_title','Perfil del usuario logado');
insert into message(id,locale,code,description) values(5,'es','users_title','Gestión de Usuarios');
insert into message(id,locale,code,description) values(6,'es','users_title_search','Búsqueda de usuarios');
insert into message(id,locale,code,description) values(7,'es','users_title_detail','Detalle de usuario');
insert into message(id,locale,code,description) values(8,'es','users_title_edit','Edición de usuario');
insert into message(id,locale,code,description) values(9,'es','users_title_add','Alta de usuario');
insert into message(id,locale,code,description) values(10,'es','users_filter_username','Usuario');
insert into message(id,locale,code,description) values(11,'es','users_filter_role','Rol');
insert into message(id,locale,code,description) values(12,'es','users_username','Usuario');
insert into message(id,locale,code,description) values(13,'es','users_passwd','Contraseña');
insert into message(id,locale,code,description) values(14,'es','users_passwdconf','Repita contraseña');
insert into message(id,locale,code,description) values(15,'es','users_passwd_weak','Débil');
insert into message(id,locale,code,description) values(16,'es','users_passwd_good','Buena');
insert into message(id,locale,code,description) values(17,'es','users_passwd_strong','Excelente');
insert into message(id,locale,code,description) values(18,'es','users_role','Rol');
insert into message(id,locale,code,description) values(19,'es','users_role_sadmin','Superadministrador');
insert into message(id,locale,code,description) values(20,'es','users_ttLastLogin','Último login');
insert into message(id,locale,code,description) values(21,'es','users_table_title','Listado de usuarios');
insert into message(id,locale,code,description) values(22,'es','users_table_username','Usuario');
insert into message(id,locale,code,description) values(23,'es','users_table_role','Rol');
insert into message(id,locale,code,description) values(24,'es','users_table_ttLastLogin','Último login');
insert into message(id,locale,code,description) values(25,'es','roles_title','Gestión de roles');
insert into message(id,locale,code,description) values(26,'es','roles_administration','Administración de permisos');
insert into message(id,locale,code,description) values(27,'es','roles_minroles','roles');
insert into message(id,locale,code,description) values(28,'es','roles_creation_startingrole','Rol de partida');
insert into message(id,locale,code,description) values(29,'es','roles_homepage','Página de inicio');
insert into message(id,locale,code,description) values(30,'es','roles_delete_deleteroles','Eliminar rol/es');
insert into message(id,locale,code,description) values(31,'es','roles_permissions','Permisos');
insert into message(id,locale,code,description) values(32,'es','roles_verticalheaders','Cabeceras verticales');
insert into message(id,locale,code,description) values(33,'es','roles_resetpermissions','Reestablecer permisos');
insert into message(id,locale,code,description) values(34,'es','roles_savepermissions','Guardar permisos');
insert into message(id,locale,code,description) values(35,'es','roles_addrole','Añadir rol');
insert into message(id,locale,code,description) values(36,'es','roles_editrole','Editar rol');
insert into message(id,locale,code,description) values(37,'es','roles_deleteroles','Eliminar roles');
insert into message(id,locale,code,description) values(38,'es','roles_deleteselected','Eliminar seleccionados');
insert into message(id,locale,code,description) values(39,'es','roles_showall','Mostrar todos');
insert into message(id,locale,code,description) values(40,'es','roles_togglepermissions','Expandir/Contraer');
insert into message(id,locale,code,description) values(41,'es','roles_grantwithdraw','Conceder/retirar todos los permisos');
insert into message(id,locale,code,description) values(42,'es','conndev_title','Dispositivos conectados');
insert into message(id,locale,code,description) values(43,'es','conndev_title_indentify','Identificación de dispositivo');
insert into message(id,locale,code,description) values(44,'es','conndev_title_edit','Edición de dispositivo');
insert into message(id,locale,code,description) values(45,'es','conndev_serviceId','Id. de servicio');
insert into message(id,locale,code,description) values(46,'es','conndev_conn_title','{0} CONECTADOS');
insert into message(id,locale,code,description) values(47,'es','conndev_block_title','{0} BLOQUEADOS');
insert into message(id,locale,code,description) values(48,'es','conndev_ident_title','{0} REGISTRADOS');
insert into message(id,locale,code,description) values(49,'es','conndev_withoutid','<Sin Identificar>');
insert into message(id,locale,code,description) values(50,'es','conndev_name','Nombre Equipo:');
insert into message(id,locale,code,description) values(51,'es','conndev_ip','IP:');
insert into message(id,locale,code,description) values(52,'es','conndev_id','ID:');
insert into message(id,locale,code,description) values(53,'es','conndev_ssid','SSID:');
insert into message(id,locale,code,description) values(54,'es','conndev_block','Bloquear');
insert into message(id,locale,code,description) values(55,'es','conndev_unblock','Desbloq.');
insert into message(id,locale,code,description) values(56,'es','conndev_identify','Identificar');
insert into message(id,locale,code,description) values(57,'es','conndev_label','Nombre dispositivo');
insert into message(id,locale,code,description) values(58,'es','conndev_type','Tipo dispositivo');
insert into message(id,locale,code,description) values(59,'es','conndev_type_smartphone','Smartphone');
insert into message(id,locale,code,description) values(60,'es','conndev_type_tablet','Tablet');
insert into message(id,locale,code,description) values(61,'es','conndev_type_tv','Televisión');
insert into message(id,locale,code,description) values(62,'es','conndev_type_desktop','Ordenador de sobremesa');
insert into message(id,locale,code,description) values(63,'es','conndev_type_laptop','Ordenador portátil');
insert into message(id,locale,code,description) values(64,'es','conndev_type_console','Videoconsola');
insert into message(id,locale,code,description) values(65,'es','conndev_type_printer','Impresora');
insert into message(id,locale,code,description) values(66,'es','conndev_type_harddisk','Disco duro');
insert into message(id,locale,code,description) values(67,'es','conndev_type_camera','Cámara de fotos');
insert into message(id,locale,code,description) values(68,'es','conndev_type_video','Cámara de video');
insert into message(id,locale,code,description) values(69,'es','conndev_type_server','Servidor');
insert into message(id,locale,code,description) values(70,'es','conndev_type_unclassified','Sin clasificar');
insert into message(id,locale,code,description) values(71,'es','conndev_type_unknown','Desconocido');
insert into message(id,locale,code,description) values(72,'es','conndev_progress_msg','Obteniendo información...');
insert into message(id,locale,code,description) values(73,'es','conndev_parctl','Control parental');
insert into message(id,locale,code,description) values(74,'es','conndev_parctl_on','Habilitar control parental');
insert into message(id,locale,code,description) values(75,'es','conndev_parctl_on_short','Habilitar CP');
insert into message(id,locale,code,description) values(76,'es','conndev_parctl_off','Deshabilitar control parental');
insert into message(id,locale,code,description) values(77,'es','conndev_parctl_off_short','Deshabilitar CP');
insert into message(id,locale,code,description) values(78,'es','conndev_parctl_deactive','Control parental desactivado');
insert into message(id,locale,code,description) values(79,'es','conndev_parctl_active','Control parental activado');
insert into message(id,locale,code,description) values(80,'es','conndev_conn_usb','USB');
insert into message(id,locale,code,description) values(81,'es','conndev_conn_ethernet','Ethernet');
insert into message(id,locale,code,description) values(82,'es','conndev_conn_wifi','Wifi');
insert into message(id,locale,code,description) values(83,'es','conndev_weekdays','Días de la semana');
insert into message(id,locale,code,description) values(84,'es','conndev_mininit','Hora de inicio');
insert into message(id,locale,code,description) values(85,'es','conndev_minend','Hora de fin');
insert into message(id,locale,code,description) values(86,'es','logviewer_title','Visor de Logs');
insert into message(id,locale,code,description) values(87,'es','logviewer_title_search','Búsqueda de logs');
insert into message(id,locale,code,description) values(88,'es','logviewer_filter_message','Mensaje');
insert into message(id,locale,code,description) values(89,'es','logviewer_filter_severity','Gravedad');
insert into message(id,locale,code,description) values(90,'es','logviewer_filter_lines','Número de líneas');
insert into message(id,locale,code,description) values(91,'es','logviewer_table_title','Últimos mensajes de log');
insert into message(id,locale,code,description) values(92,'es','logviewer_table_severity','Gravedad');
insert into message(id,locale,code,description) values(93,'es','logviewer_table_timestamp','Timestamp');
insert into message(id,locale,code,description) values(94,'es','logviewer_table_class','Clase');
insert into message(id,locale,code,description) values(95,'es','logviewer_table_message','Mensaje');
insert into message(id,locale,code,description) values(96,'es','logviewer_trace_view','Ver traza completa');
insert into message(id,locale,code,description) values(97,'es','logviewer_trace_mp_title','Traza completa del error');
insert into message(id,locale,code,description) values(98,'es','confwifi_title','Configuración wifi');
insert into message(id,locale,code,description) values(99,'es','confwifi_title_share','Enviar contraseña');
insert into message(id,locale,code,description) values(100,'es','confwifi_title_confif','Configurar WiFi');
insert into message(id,locale,code,description) values(101,'es','confwifi_serviceId','Id. de servicio');
insert into message(id,locale,code,description) values(102,'es','confwifi_status_on','La red WiFi de tu modem está encendida.');
insert into message(id,locale,code,description) values(103,'es','confwifi_status_off','La red WiFi de tu modem no está habilitada.');
insert into message(id,locale,code,description) values(104,'es','confwifi_enctype_open','Se ha detectado que la seguridad de tu red está desactivada.');
insert into message(id,locale,code,description) values(105,'es','confwifi_enctype_low','Se ha detectado que la seguridad de tu red es baja.');
insert into message(id,locale,code,description) values(106,'es','confwifi_turnOn','Encender');
insert into message(id,locale,code,description) values(107,'es','confwifi_turnOff','Apagar');
insert into message(id,locale,code,description) values(108,'es','confwifi_share','Enviar');
insert into message(id,locale,code,description) values(109,'es','confwifi_config','Configurar');
insert into message(id,locale,code,description) values(110,'es','confwifi_share_message','Se enviará el siguiente mensaje:');
insert into message(id,locale,code,description) values(111,'es','confwifi_share_ssid','<b>Red WiFi:</b> {0}');
insert into message(id,locale,code,description) values(112,'es','confwifi_share_enctype','<b>Seguridad:</b> {0}');
insert into message(id,locale,code,description) values(113,'es','confwifi_share_enctype_open','Su red WiFi está abierta, no tiene contraseña');
insert into message(id,locale,code,description) values(114,'es','confwifi_share_passwd','<b>Contraseña:</b> {0}');
insert into message(id,locale,code,description) values(115,'es','confwifi_share_mail_ssid','Nombre red WiFi: {0}');
insert into message(id,locale,code,description) values(116,'es','confwifi_share_mail_enctype','Seguridad: {0}');
insert into message(id,locale,code,description) values(117,'es','confwifi_share_mail_enctype_open','Su red WiFi está abierta, no tiene contraseña');
insert into message(id,locale,code,description) values(118,'es','confwifi_share_mail_passwd','Contraseña: {0}');
insert into message(id,locale,code,description) values(119,'es','confwifi_ssid','Nombre red WiFi');
insert into message(id,locale,code,description) values(120,'es','confwifi_enctype','Seguridad');
insert into message(id,locale,code,description) values(121,'es','confwifi_passwd','Contraseña');
insert into message(id,locale,code,description) values(122,'es','confwifi_passwd_open','WiFi Abierta');
insert into message(id,locale,code,description) values(123,'es','confwifi_passwd_noreadable','******');
insert into message(id,locale,code,description) values(124,'es','confwifi_channel','Canal');
insert into message(id,locale,code,description) values(125,'es','confwifi_autochannel_on','Canal automático');
insert into message(id,locale,code,description) values(126,'es','confwifi_progress_msg','Obteniendo información...');
insert into message(id,locale,code,description) values(127,'es','trb_title','Diagnóstico');
insert into message(id,locale,code,description) values(128,'es','trb_trbIdType','Tipo de ID');
insert into message(id,locale,code,description) values(129,'es','trb_trbIdType_clientid','ID de cliente');
insert into message(id,locale,code,description) values(130,'es','trb_trbIdType_workorder','Orden de trabajo');
insert into message(id,locale,code,description) values(131,'es','trb_trbIdType_serviceid','ID de servicio');
insert into message(id,locale,code,description) values(132,'es','trb_trbIdType_devicemac','MAC de dispositivo');
insert into message(id,locale,code,description) values(133,'es','trb_trbIdType_deviceserial','Serial de dispositivo');
insert into message(id,locale,code,description) values(134,'es','trb_diag_infoheader','Información adicional');
insert into message(id,locale,code,description) values(135,'es','trb_diag_actheader','Recomendaciones');
insert into message(id,locale,code,description) values(136,'es','trb_progress_msg','Iniciando diagnóstico...');
insert into message(id,locale,code,description) values(137,'es','trb_session_state','Estado sesión');
insert into message(id,locale,code,description) values(138,'es','trb_session_state_resolved','Resuelto');
insert into message(id,locale,code,description) values(139,'es','trb_session_state_n2','Escalado 2° nivel');
insert into message(id,locale,code,description) values(140,'es','trb_session_state_massive','Avería masiva');
insert into message(id,locale,code,description) values(141,'es','trb_session_close','Cerrar sesión');
insert into message(id,locale,code,description) values(142,'es','trb_closingcode','Código de cierre');
insert into message(id,locale,code,description) values(143,'es','trb_block_blockdev','Bloquear dispositivo');
insert into message(id,locale,code,description) values(144,'es','trb_block_unblockdev','Desbloquear dispositivo');
insert into message(id,locale,code,description) values(145,'es','trball_tab_diag','Diagnóstico');
insert into message(id,locale,code,description) values(146,'es','trball_tab_vtec','Vista técnica');
insert into message(id,locale,code,description) values(147,'es','trball_tab_cwifi','Configuración WiFi');
insert into message(id,locale,code,description) values(148,'es','trball_tab_rdev','Dispositivos conectados');
insert into message(id,locale,code,description) values(149,'es','trball_tab_interf','Optimización WiFi');
insert into message(id,locale,code,description) values(150,'es','trball_tab_hist','Histórico del cliente');
insert into message(id,locale,code,description) values(151,'es','trball_vtect_param_title','Parámetros');
insert into message(id,locale,code,description) values(152,'es','trball_vtect_action_title','Acciones');
insert into message(id,locale,code,description) values(153,'es','trball_tab_currenttrb','Diag Hist');
insert into message(id,locale,code,description) values(154,'es','trball_tab_currentvtec','VTec Hist');
insert into message(id,locale,code,description) values(155,'es','trball_tab_currentrdev','Disp Hist');
insert into message(id,locale,code,description) values(156,'es','trball_tab_currentcwifi','Conf WiFi Hist');
insert into message(id,locale,code,description) values(157,'es','trball_tab_currentinterf','Optim WiFi Hist');
insert into message(id,locale,code,description) values(158,'es','hst_table_type','Tipo');
insert into message(id,locale,code,description) values(159,'es','hst_table_service','Servicio');
insert into message(id,locale,code,description) values(160,'es','hst_table_context','Contexto');
insert into message(id,locale,code,description) values(161,'es','hst_table_contextid','Identificador');
insert into message(id,locale,code,description) values(162,'es','hst_table_tt','Timestamp');
insert into message(id,locale,code,description) values(163,'es','hst_table_detail','Detalle');
insert into message(id,locale,code,description) values(164,'es','hst_trb_ok','OK');
insert into message(id,locale,code,description) values(165,'es','hst_table_icon_type_diag','Diagnóstico');
insert into message(id,locale,code,description) values(166,'es','hst_table_icon_type_cwifi','Configuración WiFi');
insert into message(id,locale,code,description) values(167,'es','hst_table_icon_type_cdev','Dispositivos conectados');
insert into message(id,locale,code,description) values(168,'es','hst_table_icon_type_wifioptimization','Optimización WiFi');
insert into message(id,locale,code,description) values(169,'es','hst_table_icon_type_action','Acción');
insert into message(id,locale,code,description) values(170,'es','hst_table_icon_type_notif','Notificación');
insert into message(id,locale,code,description) values(171,'es','hst_table_icon_type_suggest','Sugerencia');
insert into message(id,locale,code,description) values(172,'es','hst_table_icon_type_survey','Encuesta');
insert into message(id,locale,code,description) values(173,'es','hst_table_icon_type_speedtest','Test de veolocidad');
insert into message(id,locale,code,description) values(174,'es','hst_table_icon_type_hubwifi','Hub Wifi');
insert into message(id,locale,code,description) values(175,'es','hst_table_icon_type_tutorial','Tutorial');
insert into message(id,locale,code,description) values(176,'es','hst_table_icon_type_block','Bloqueo de dispositivo');
insert into message(id,locale,code,description) values(177,'es','hst_table_icon_type_unblock','Desbloqueo de dispositivo');
insert into message(id,locale,code,description) values(178,'es','hst_table_icon_type_addparctrlpol','Añadido control parental');
insert into message(id,locale,code,description) values(179,'es','hst_table_icon_type_dropparctrlpol','Borrado control parental');
insert into message(id,locale,code,description) values(180,'es','hst_table_icon_src_callcenter','Callcenter');
insert into message(id,locale,code,description) values(181,'es','hst_table_icon_src_cert','Certificación');
insert into message(id,locale,code,description) values(182,'es','hst_table_icon_src_smart','Smartphone');
insert into message(id,locale,code,description) values(183,'es','hst_table_icon_src_web','Web');
insert into message(id,locale,code,description) values(184,'es','hst_table_icon_ctx_diag','Diagnóstico');
insert into message(id,locale,code,description) values(185,'es','hst_table_icon_ctx_cwifi','Configuración WiFi');
insert into message(id,locale,code,description) values(186,'es','hst_table_icon_ctx_cdev','Dispositivos conectados');
insert into message(id,locale,code,description) values(187,'es','hst_table_icon_ctx_interf','Optimización WiFi');
insert into message(id,locale,code,description) values(188,'es','interf_rec','Recomendaciones');
insert into message(id,locale,code,description) values(189,'es','interf_rec_fake1_title','Problemas de interferencias 5 GHz');
insert into message(id,locale,code,description) values(190,'es','interf_rec_fake1_msg','Hemos detectado que tienes bastantes interferencias en la red de 5GHz con las redes de tus vecinos. Recomendamos cambiar el canal para solucionar el problema.');
insert into message(id,locale,code,description) values(191,'es','interf_rec_fake2_title','Problemas de cobertura');
insert into message(id,locale,code,description) values(192,'es','interf_rec_fake2_msg','Hemos detecado que el nivel de señal de tu red WiFi es muy baja en alguno de los dispositivos conectados. Para poder solucionar este problema, es necesario la compra de un repetidor de tu señal.');
insert into message(id,locale,code,description) values(193,'es','interf_status','Situación actual');
insert into message(id,locale,code,description) values(194,'es','interf_evol','Evolución canal últimas 24 horas');
insert into message(id,locale,code,description) values(195,'es','interf_dev','Dispositivos conectados');
insert into message(id,locale,code,description) values(196,'es','interf_dev_id','Identificador');
insert into message(id,locale,code,description) values(197,'es','interf_dev_ip','IP');
insert into message(id,locale,code,description) values(198,'es','interf_dev_conn','Conexión');
insert into message(id,locale,code,description) values(199,'es','interf_dev_dev','Dispositivo');
insert into message(id,locale,code,description) values(200,'es','interf_dev_band','Banda');
insert into message(id,locale,code,description) values(201,'es','interf_dev_linkspeed','Linkspeed');
insert into message(id,locale,code,description) values(202,'es','interf_hist','Histórico');
insert into message(id,locale,code,description) values(203,'es','interf_hist_type','Tipo');
insert into message(id,locale,code,description) values(204,'es','interf_hist_service','Servicio');
insert into message(id,locale,code,description) values(205,'es','interf_hist_tt','Timestamp');
insert into message(id,locale,code,description) values(206,'es','interf_hist_detail','Detalle');
insert into message(id,locale,code,description) values(207,'es','trb_linked','Diagnóstico enlazado');
insert into message(id,locale,code,description) values(208,'es','trb_linked_dialog_notif_header','Envío de notificación');
insert into message(id,locale,code,description) values(209,'es','trb_linked_dialog_notif_body','¿Desea envíar la notificación de diagnóstico enlazado al dispositivo <b>{0}</b> modelo <b>{1}</b>?');
insert into message(id,locale,code,description) values(210,'es','trb_linked_dialog_wait_header','Envío de notificación');
insert into message(id,locale,code,description) values(211,'es','trb_linked_dialog_wait_body_1','Se ha enviado una notificación de diagnóstico al terminal asociado al siguiente servicio:');
insert into message(id,locale,code,description) values(212,'es','trb_linked_dialog_wait_body_2','Por favor, indique al cliente que acepte dicha notificación para comenzar el diagnóstico.');
insert into message(id,locale,code,description) values(213,'es','trb_linked_dialog_wait_response','Esperando respuesta...');
insert into message(id,locale,code,description) values(214,'es','trb_linked_dialog_wait_finish','Diagnóstico detectado. Pulse <b>Aceptar</b> para continuar.');
insert into message(id,locale,code,description) values(215,'es','trb_linked_dialog_timeout_header','Envío de notificación');
insert into message(id,locale,code,description) values(216,'es','trb_linked_dialog_timeout_body','Parece que no hay conectividad con el terminal del cliente.<br><br>Por favor, solicite al cliente que acceda a la aplicación SCHAMAN y pulse el icono <b>Diagnóstico</b>.<br><br>Le aparecerá la vista inicial del modo de diagnóstico donde habrá de pulsar el botón verde <b>Comenzar Diagnóstico</b>.<br><br>Una vez completado el diagnóstico en la parte inferior de la pantalla le aparecerá un código que debe indicar al agente de Call Center.<br><br>Pulsando sobre el código podrá visualizarlo de forma más cómoda.');
insert into message(id,locale,code,description) values(217,'es','trb_linked_dialog_trberr_header','Envío de notificación');
insert into message(id,locale,code,description) values(218,'es','trb_linked_dialog_trberr_body','Se ha producido un error en la ejecución del diagnóstico.<br><br>Inténtelo de nuevo pasados unos minutos. Si el error persiste, contacte con su administrador.');
insert into message(id,locale,code,description) values(219,'es','trb_linked_manufacturer','Fabricante:');
insert into message(id,locale,code,description) values(220,'es','trb_linked_model','Modelo:');
insert into message(id,locale,code,description) values(221,'es','trb_linked_system','Sistema:');
insert into message(id,locale,code,description) values(222,'es','trb_linked_enterofflinecode','Introduzca el código offline');
insert into message(id,locale,code,description) values(223,'es','trb_linked_offlinecode','Código offline');
insert into message(id,locale,code,description) values(224,'es','trb_linked_notif_diag_text','Pulsa para ejecutar un diagnóstico asistido en tu teléfono');
insert into message(id,locale,code,description) values(225,'es','trb_linked_notif_action_text','Pulsa para realizar una acción asistida en tu teléfono');
insert into message(id,locale,code,description) values(226,'es','obsrules_title','Gestión de reglas de obsolescencia');
insert into message(id,locale,code,description) values(227,'es','obsrules_title_search','Búsqueda de reglas de obsolescencia');
insert into message(id,locale,code,description) values(228,'es','obsrules_title_detail','Detalle de regla de obsolescencia');
insert into message(id,locale,code,description) values(229,'es','obsrules_title_edit','Edición de regla de obsolescencia');
insert into message(id,locale,code,description) values(230,'es','obsrules_title_add','Alta de regla de obsolescencia');
insert into message(id,locale,code,description) values(231,'es','obsrules_filter_os','Sist Operativo');
insert into message(id,locale,code,description) values(232,'es','obsrules_filter_app','Aplicación');
insert into message(id,locale,code,description) values(233,'es','obsrules_pos','Posición');
insert into message(id,locale,code,description) values(234,'es','obsrules_status','Estado');
insert into message(id,locale,code,description) values(235,'es','obsrules_imsi','SIM IMSI');
insert into message(id,locale,code,description) values(236,'es','obsrules_os','Sist Operativo');
insert into message(id,locale,code,description) values(237,'es','obsrules_osversion','Versión SO');
insert into message(id,locale,code,description) values(238,'es','obsrules_app','Aplicación');
insert into message(id,locale,code,description) values(239,'es','obsrules_appversion','Versión App');
insert into message(id,locale,code,description) values(240,'es','obsrules_manufacturer','Fabricante');
insert into message(id,locale,code,description) values(241,'es','obsrules_model','Modelo');
insert into message(id,locale,code,description) values(242,'es','obsrules_message','Mensaje');
insert into message(id,locale,code,description) values(243,'es','obsrules_disclairmer','(*) Con Igual que... puede usar el comodín * al principio y/o al final del valor');
insert into message(id,locale,code,description) values(244,'es','obsrules_table_title','Listado de reglas de obsolescencia');
insert into message(id,locale,code,description) values(245,'es','obsrules_table_pos','Pos.');
insert into message(id,locale,code,description) values(246,'es','obsrules_table_imsi','IMSI');
insert into message(id,locale,code,description) values(247,'es','obsrules_table_os','SO');
insert into message(id,locale,code,description) values(248,'es','obsrules_table_osversion','Ver SO');
insert into message(id,locale,code,description) values(249,'es','obsrules_table_app','App');
insert into message(id,locale,code,description) values(250,'es','obsrules_table_appversion','Ver App');
insert into message(id,locale,code,description) values(251,'es','obsrules_table_manufacturer','Fabricante');
insert into message(id,locale,code,description) values(252,'es','obsrules_table_model','Modelo');
insert into message(id,locale,code,description) values(253,'es','obsrules_table_state','Estado');
insert into message(id,locale,code,description) values(254,'es','obsrules_table_message','Mensaje');
insert into message(id,locale,code,description) values(255,'es','bbar_menu','Botonera');
insert into message(id,locale,code,description) values(256,'es','bbar_title','Gestión de botonera');
insert into message(id,locale,code,description) values(257,'es','bbar_title_save','Alta de botonera');
insert into message(id,locale,code,description) values(258,'es','bbar_title_add','Alta de nuevo elemento de botonera');
insert into message(id,locale,code,description) values(259,'es','bbar_title_edit','Edición de elemento de botonera');
insert into message(id,locale,code,description) values(260,'es','bbar_title_detail','Detalle de elemento');
insert into message(id,locale,code,description) values(261,'es','bbar_title_sync','Sincronización de botonera');
insert into message(id,locale,code,description) values(262,'es','bbar_version','Versión actual');
insert into message(id,locale,code,description) values(263,'es','bbar_version_inedit','(En proceso de modificación)');
insert into message(id,locale,code,description) values(264,'es','bbar_ttcreation','Fecha');
insert into message(id,locale,code,description) values(265,'es','bbar_enabled','Activo');
insert into message(id,locale,code,description) values(266,'es','bbar_pos','Posición');
insert into message(id,locale,code,description) values(267,'es','bbar_id','Identificador');
insert into message(id,locale,code,description) values(268,'es','bbar_label','Etiqueta');
insert into message(id,locale,code,description) values(269,'es','bbar_parent','Menú');
insert into message(id,locale,code,description) values(270,'es','bbar_type','Tipo');
insert into message(id,locale,code,description) values(271,'es','bbar_login','Login');
insert into message(id,locale,code,description) values(272,'es','bbar_link','Link');
insert into message(id,locale,code,description) values(273,'es','bbar_platforms','Plataformas');
insert into message(id,locale,code,description) values(274,'es','bbar_screens','Pantallas');
insert into message(id,locale,code,description) values(275,'es','bbar_params','Parámetros');
insert into message(id,locale,code,description) values(276,'es','bbar_params_add','Añadir un parámetro');
insert into message(id,locale,code,description) values(277,'es','bbar_params_table_label','Nombre');
insert into message(id,locale,code,description) values(278,'es','bbar_params_table_value','Valor');
insert into message(id,locale,code,description) values(279,'es','bbar_param_new_title','Nuevo parámetro');
insert into message(id,locale,code,description) values(280,'es','bbar_param_edit_title','Edición de parámetro');
insert into message(id,locale,code,description) values(281,'es','bbar_param_name','Nombre');
insert into message(id,locale,code,description) values(282,'es','bbar_param_value','Valor');
insert into message(id,locale,code,description) values(283,'es','bbar_icons','Iconos');
insert into message(id,locale,code,description) values(284,'es','bbar_icons_disclaimer','(Fichero ZIP de 100Kb. máximo)');
insert into message(id,locale,code,description) values(285,'es','bbar_table_title','Items de la botonera');
insert into message(id,locale,code,description) values(286,'es','bbar_table_pos','Posición');
insert into message(id,locale,code,description) values(287,'es','bbar_table_id','Identificador');
insert into message(id,locale,code,description) values(288,'es','bbar_table_enabled','Activo');
insert into message(id,locale,code,description) values(289,'es','bbar_table_label','Etiqueta');
insert into message(id,locale,code,description) values(290,'es','bbar_table_type','Tipo');
insert into message(id,locale,code,description) values(291,'es','bbar_table_menu','Menú');
insert into message(id,locale,code,description) values(292,'es','bbar_table_login','Login');
insert into message(id,locale,code,description) values(293,'es','bbar_table_platforms','Plataformas');
insert into message(id,locale,code,description) values(294,'es','bbar_table_screens','Pantallas');
insert into message(id,locale,code,description) values(295,'es','bbar_preview','Previsualizar');
insert into message(id,locale,code,description) values(296,'es','bbar_preview_json','Ver fichero JSON');
insert into message(id,locale,code,description) values(297,'es','bbar_preview_title','Previsualización botonera');
insert into message(id,locale,code,description) values(298,'es','bbar_sync_url','URL');
insert into message(id,locale,code,description) values(299,'es','client_title','Clientes');
insert into message(id,locale,code,description) values(300,'es','client_title_search','Búsqueda de clientes');
insert into message(id,locale,code,description) values(301,'es','client_title_detail','Detalle de cliente');
insert into message(id,locale,code,description) values(302,'es','client_title_edit','Edición de cliente');
insert into message(id,locale,code,description) values(303,'es','client_title_add','Alta de cliente');
insert into message(id,locale,code,description) values(304,'es','client_filter_client','Cliente');
insert into message(id,locale,code,description) values(305,'es','client_client','Cliente:');
insert into message(id,locale,code,description) values(306,'es','client_passwd','Contraseña:');
insert into message(id,locale,code,description) values(307,'es','client_passwdconf','Repita contraseña:');
insert into message(id,locale,code,description) values(308,'es','client_numser','Servicios:');
insert into message(id,locale,code,description) values(309,'es','client_ttLastlogin','Último login:');
insert into message(id,locale,code,description) values(310,'es','client_ttCreation','Creación:');
insert into message(id,locale,code,description) values(311,'es','client_ttModification','Modificación:');
insert into message(id,locale,code,description) values(312,'es','client_table_title','Listado de clientes');
insert into message(id,locale,code,description) values(313,'es','client_table_client','Cliente');
insert into message(id,locale,code,description) values(314,'es','client_table_numser','Servicios');
insert into message(id,locale,code,description) values(315,'es','client_table_ttLastlogin','Último login');
insert into message(id,locale,code,description) values(316,'es','client_table_ttCreation','Creación');
insert into message(id,locale,code,description) values(317,'es','client_table_ttModification','Modificación');
insert into message(id,locale,code,description) values(318,'es','service_title','Servicios');
insert into message(id,locale,code,description) values(319,'es','service_title_search','Búsqueda de servicios');
insert into message(id,locale,code,description) values(320,'es','service_title_detail','Detalle de servicio');
insert into message(id,locale,code,description) values(321,'es','service_title_edit','Edición de servicio');
insert into message(id,locale,code,description) values(322,'es','service_title_add','Alta de servicio');
insert into message(id,locale,code,description) values(323,'es','service_filter_client','Cliente');
insert into message(id,locale,code,description) values(324,'es','service_filter_type','Tipo');
insert into message(id,locale,code,description) values(325,'es','service_filter_description','Descripción');
insert into message(id,locale,code,description) values(326,'es','service_filter_service','Servicio');
insert into message(id,locale,code,description) values(327,'es','service_filter_imsi','IMSI');
insert into message(id,locale,code,description) values(328,'es','service_filter_ttCreation','Creación');
insert into message(id,locale,code,description) values(329,'es','service_filter_ttModification','Modificación');
insert into message(id,locale,code,description) values(330,'es','service_client','Cliente:');
insert into message(id,locale,code,description) values(331,'es','service_type','Tipo:');
insert into message(id,locale,code,description) values(332,'es','service_description','Descripción:');
insert into message(id,locale,code,description) values(333,'es','service_service','Servicio:');
insert into message(id,locale,code,description) values(334,'es','service_imsi','IMSI:');
insert into message(id,locale,code,description) values(335,'es','service_ttCreation','Creación:');
insert into message(id,locale,code,description) values(336,'es','service_ttModification','Modificación:');
insert into message(id,locale,code,description) values(337,'es','service_table_title','Listado de servicios');
insert into message(id,locale,code,description) values(338,'es','service_table_client','Cliente');
insert into message(id,locale,code,description) values(339,'es','service_table_type','Tipo');
insert into message(id,locale,code,description) values(340,'es','service_table_description','Descripción');
insert into message(id,locale,code,description) values(341,'es','service_table_service','Servicio');
insert into message(id,locale,code,description) values(342,'es','service_table_imsi','IMSI');
insert into message(id,locale,code,description) values(343,'es','service_table_ttCreation','Creación');
insert into message(id,locale,code,description) values(344,'es','service_table_ttModification','Modificación');
insert into message(id,locale,code,description) values(345,'es','service_type_internet','Internet');
insert into message(id,locale,code,description) values(346,'es','service_type_land','Fijo');
insert into message(id,locale,code,description) values(347,'es','service_type_mobile','Móvil');
insert into message(id,locale,code,description) values(348,'es','service_type_unknown','Desconocido');
insert into message(id,locale,code,description) values(349,'es','service_type_ai','Servicios xDSL indirecto');
insert into message(id,locale,code,description) values(350,'es','service_type_ftth','Servicios de fibra (FTTH)');
insert into message(id,locale,code,description) values(351,'es','service_type_fu','Servicios de xDSL directo');
insert into message(id,locale,code,description) values(352,'es','service_type_movil','Servicios de móvil');
insert into message(id,locale,code,description) values(353,'es','service_type_nebacobre','Servicios de Neba sobre cobre');
insert into message(id,locale,code,description) values(354,'es','service_type_nebaftth','Servicios de Neba sobre FTTH');
insert into message(id,locale,code,description) values(355,'es','device_title','Dispositivos');
insert into message(id,locale,code,description) values(356,'es','device_title_search','Búsqueda de dispositivos');
insert into message(id,locale,code,description) values(357,'es','device_title_detail','Detalle de dispositivo');
insert into message(id,locale,code,description) values(358,'es','device_title_edit','Edición de dispositivo');
insert into message(id,locale,code,description) values(359,'es','device_title_add','Alta de dispositivo');
insert into message(id,locale,code,description) values(360,'es','device_filter_device','Dispositivo');
insert into message(id,locale,code,description) values(361,'es','device_filter_manufacturer','Fabricante');
insert into message(id,locale,code,description) values(362,'es','device_filter_model','Modelo');
insert into message(id,locale,code,description) values(363,'es','device_filter_system','Sistema');
insert into message(id,locale,code,description) values(364,'es','device_filter_so','SO');
insert into message(id,locale,code,description) values(365,'es','device_filter_sover','SO ver.');
insert into message(id,locale,code,description) values(366,'es','device_filter_client','Cliente');
insert into message(id,locale,code,description) values(367,'es','device_filter_service','Servicio');
insert into message(id,locale,code,description) values(368,'es','device_filter_ttModification','Modificación');
insert into message(id,locale,code,description) values(369,'es','device_filter_pushtoken','PT');
insert into message(id,locale,code,description) values(370,'es','device_device','Dispositivo:');
insert into message(id,locale,code,description) values(371,'es','device_imsi','IMSI:');
insert into message(id,locale,code,description) values(372,'es','device_manufacturer','Fabricante:');
insert into message(id,locale,code,description) values(373,'es','device_model','Modelo:');
insert into message(id,locale,code,description) values(374,'es','device_system','Sistema:');
insert into message(id,locale,code,description) values(375,'es','device_so','SO:');
insert into message(id,locale,code,description) values(376,'es','device_sover','SO ver.:');
insert into message(id,locale,code,description) values(377,'es','device_client','Cliente:');
insert into message(id,locale,code,description) values(378,'es','device_service','Servicio:');
insert into message(id,locale,code,description) values(379,'es','device_pushtoken','PT:');
insert into message(id,locale,code,description) values(380,'es','device_ttCreation','Creación:');
insert into message(id,locale,code,description) values(381,'es','device_ttModification','Modificación:');
insert into message(id,locale,code,description) values(382,'es','device_table_title','Listado de dispositivos');
insert into message(id,locale,code,description) values(383,'es','device_table_device','Dispositivo');
insert into message(id,locale,code,description) values(384,'es','device_table_clients','Clientes');
insert into message(id,locale,code,description) values(385,'es','device_table_manufacturer','Fabricante');
insert into message(id,locale,code,description) values(386,'es','device_table_model','Modelo');
insert into message(id,locale,code,description) values(387,'es','device_table_system','Sistema');
insert into message(id,locale,code,description) values(388,'es','device_table_so','SO');
insert into message(id,locale,code,description) values(389,'es','device_table_sover','SO ver.');
insert into message(id,locale,code,description) values(390,'es','device_table_client','Cliente');
insert into message(id,locale,code,description) values(391,'es','device_table_service','Servicio');
insert into message(id,locale,code,description) values(392,'es','device_table_ttCreation','Creación');
insert into message(id,locale,code,description) values(393,'es','device_table_ttModification','Modificación');
insert into message(id,locale,code,description) values(394,'es','device_table_pushtoken','PT');
insert into message(id,locale,code,description) values(395,'es','device_table_lastUse','Último Uso');
insert into message(id,locale,code,description) values(396,'es','device_so_unknown','Desconocido');
insert into message(id,locale,code,description) values(397,'es','device_so_android','Android');
insert into message(id,locale,code,description) values(398,'es','device_so_ios','IOS');
insert into message(id,locale,code,description) values(399,'es','device_so_windows','Windows');
insert into message(id,locale,code,description) values(400,'es','device_so_linux','Linux');
insert into message(id,locale,code,description) values(401,'es','device_so_mac','Mac OS');
insert into message(id,locale,code,description) values(402,'es','manufacturer_title','Fabricantes');
insert into message(id,locale,code,description) values(403,'es','manufacturer_title_search','Búsqueda de fabricantes');
insert into message(id,locale,code,description) values(404,'es','manufacturer_title_detail','Detalle de fabricante');
insert into message(id,locale,code,description) values(405,'es','manufacturer_title_edit','Edición de fabricante');
insert into message(id,locale,code,description) values(406,'es','manufacturer_title_add','Alta de fabricante');
insert into message(id,locale,code,description) values(407,'es','manufacturer_filter_manufacturer','Fabricante');
insert into message(id,locale,code,description) values(408,'es','manufacturer_filter_numcpes','N° CPEs');
insert into message(id,locale,code,description) values(409,'es','manufacturer_manufacturer','Fabricante:');
insert into message(id,locale,code,description) values(410,'es','manufacturer_numcpes','N° CPEs:');
insert into message(id,locale,code,description) values(411,'es','manufacturer_total','%Total:');
insert into message(id,locale,code,description) values(412,'es','manufacturer_ttCreation','Creación:');
insert into message(id,locale,code,description) values(413,'es','manufacturer_ttModification','Modificación:');
insert into message(id,locale,code,description) values(414,'es','manufacturer_table_title','Listado de fabricantes');
insert into message(id,locale,code,description) values(415,'es','manufacturer_table_manufacturer','Fabricante');
insert into message(id,locale,code,description) values(416,'es','manufacturer_table_numcpes','N° CPEs');
insert into message(id,locale,code,description) values(417,'es','manufacturer_table_total','%Total');
insert into message(id,locale,code,description) values(418,'es','manufacturer_table_ttCreation','Creación');
insert into message(id,locale,code,description) values(419,'es','manufacturer_table_ttModification','Modificación');
insert into message(id,locale,code,description) values(420,'es','model_title','Modelos');
insert into message(id,locale,code,description) values(421,'es','model_title_search','Búsqueda de modelos');
insert into message(id,locale,code,description) values(422,'es','model_title_detail','Detalle de modelo');
insert into message(id,locale,code,description) values(423,'es','model_title_edit','Edición de modelo');
insert into message(id,locale,code,description) values(424,'es','model_title_add','Alta de modelo');
insert into message(id,locale,code,description) values(425,'es','model_filter_manufacturer','Fabricante');
insert into message(id,locale,code,description) values(426,'es','model_filter_model','Modelo');
insert into message(id,locale,code,description) values(427,'es','model_filter_numcpes','N° CPEs');
insert into message(id,locale,code,description) values(428,'es','model_manufacturer','Fabricante:');
insert into message(id,locale,code,description) values(429,'es','model_model','Modelo:');
insert into message(id,locale,code,description) values(430,'es','model_numcpes','N° CPEs:');
insert into message(id,locale,code,description) values(431,'es','model_total','%Mod:');
insert into message(id,locale,code,description) values(432,'es','model_ttCreation','Creación:');
insert into message(id,locale,code,description) values(433,'es','model_ttModification','Modificación:');
insert into message(id,locale,code,description) values(434,'es','model_table_title','Listado de modelos');
insert into message(id,locale,code,description) values(435,'es','model_table_manufacturer','Fabricante');
insert into message(id,locale,code,description) values(436,'es','model_table_model','Modelo');
insert into message(id,locale,code,description) values(437,'es','model_table_numcpes','N° CPEs');
insert into message(id,locale,code,description) values(438,'es','model_table_total','%Mod');
insert into message(id,locale,code,description) values(439,'es','model_table_ttCreation','Creación');
insert into message(id,locale,code,description) values(440,'es','model_table_ttModification','Modificación');
insert into message(id,locale,code,description) values(441,'es','access_title','Equipos de acceso');
insert into message(id,locale,code,description) values(442,'es','access_title_search','Búsqueda de equipos de acceso');
insert into message(id,locale,code,description) values(443,'es','access_title_detail','Detalle de equipo de acceso');
insert into message(id,locale,code,description) values(444,'es','access_title_edit','Edición de equipo de acceso');
insert into message(id,locale,code,description) values(445,'es','access_title_add','Alta de equipo de acceso');
insert into message(id,locale,code,description) values(446,'es','access_title_upload','Carga de equipo de acceso');
insert into message(id,locale,code,description) values(447,'es','access_filter_hostname','Equipo');
insert into message(id,locale,code,description) values(448,'es','access_filter_provisiongroup','Grupo de Provisión');
insert into message(id,locale,code,description) values(449,'es','access_filter_zone','Zona');
insert into message(id,locale,code,description) values(450,'es','access_filter_subzone','Subzona');
insert into message(id,locale,code,description) values(451,'es','access_filter_node','Nodo');
insert into message(id,locale,code,description) values(452,'es','access_filter_ipaddress','Dirección IP');
insert into message(id,locale,code,description) values(453,'es','access_filter_giaddress','Giaddress');
insert into message(id,locale,code,description) values(454,'es','access_filter_accesstype','Tipo');
insert into message(id,locale,code,description) values(455,'es','access_filter_numcpes','N° CPEs');
insert into message(id,locale,code,description) values(456,'es','access_hostname','Equipo:');
insert into message(id,locale,code,description) values(457,'es','access_provisiongroup','Grupo de Provisión:');
insert into message(id,locale,code,description) values(458,'es','access_zone','Zona:');
insert into message(id,locale,code,description) values(459,'es','access_subzone','Subzona:');
insert into message(id,locale,code,description) values(460,'es','access_node','Nodo:');
insert into message(id,locale,code,description) values(461,'es','access_ipaddress','Dirección IP:');
insert into message(id,locale,code,description) values(462,'es','access_giaddress','Giaddress:');
insert into message(id,locale,code,description) values(463,'es','access_accesstype','Tipo:');
insert into message(id,locale,code,description) values(464,'es','access_numcpes','N° CPEs:');
insert into message(id,locale,code,description) values(465,'es','access_ttCreation','Creación:');
insert into message(id,locale,code,description) values(466,'es','access_ttModification','Modificación:');
insert into message(id,locale,code,description) values(467,'es','access_table_title','Listado de equipos de acceso');
insert into message(id,locale,code,description) values(468,'es','access_table_hostname','Equipo');
insert into message(id,locale,code,description) values(469,'es','access_table_provisiongroup','Grupo de Provisión');
insert into message(id,locale,code,description) values(470,'es','access_table_zone','Zona');
insert into message(id,locale,code,description) values(471,'es','access_table_subzone','Subzona');
insert into message(id,locale,code,description) values(472,'es','access_table_node','Nodo');
insert into message(id,locale,code,description) values(473,'es','access_table_ipaddress','Dirección IP');
insert into message(id,locale,code,description) values(474,'es','access_table_giaddress','Giaddress');
insert into message(id,locale,code,description) values(475,'es','access_table_accesstype','Tipo');
insert into message(id,locale,code,description) values(476,'es','access_table_numcpes','N° CPEs');
insert into message(id,locale,code,description) values(477,'es','access_table_ttcreation','Creación');
insert into message(id,locale,code,description) values(478,'es','access_table_ttmodification','Modificación');
insert into message(id,locale,code,description) values(479,'es','firmware_title','Firmwares');
insert into message(id,locale,code,description) values(480,'es','firmware_title_search','Búsqueda de firmwares');
insert into message(id,locale,code,description) values(481,'es','firmware_title_detail','Detalle de firmware');
insert into message(id,locale,code,description) values(482,'es','firmware_title_edit','Edición de firmware');
insert into message(id,locale,code,description) values(483,'es','firmware_title_add','Alta de firmware');
insert into message(id,locale,code,description) values(484,'es','firmware_title_upload','Carga de firmware');
insert into message(id,locale,code,description) values(485,'es','firmware_filter_hostname','Equipo de acceso');
insert into message(id,locale,code,description) values(486,'es','firmware_filter_cmts','CTMS');
insert into message(id,locale,code,description) values(487,'es','firmware_filter_provisiongroup','Grupo de Provisión');
insert into message(id,locale,code,description) values(488,'es','firmware_filter_node','Nodo');
insert into message(id,locale,code,description) values(489,'es','firmware_filter_access','Acceso');
insert into message(id,locale,code,description) values(490,'es','firmware_filter_manufacturer','Fabricante');
insert into message(id,locale,code,description) values(491,'es','firmware_filter_model','Modelo');
insert into message(id,locale,code,description) values(492,'es','firmware_filter_firmware','Firmware');
insert into message(id,locale,code,description) values(493,'es','firmware_filter_numcpes','N° CPEs');
insert into message(id,locale,code,description) values(494,'es','firmware_cmts','CTMS:');
insert into message(id,locale,code,description) values(495,'es','firmware_provisiongroup','Grupo de Provisión:');
insert into message(id,locale,code,description) values(496,'es','firmware_node','Nodo:');
insert into message(id,locale,code,description) values(497,'es','firmware_access','Acceso:');
insert into message(id,locale,code,description) values(498,'es','firmware_manufacturer','Fabricante:');
insert into message(id,locale,code,description) values(499,'es','firmware_model','Modelo:');
insert into message(id,locale,code,description) values(500,'es','firmware_firmware','Firmware:');
insert into message(id,locale,code,description) values(501,'es','firmware_numcpes','N° CPEs:');
insert into message(id,locale,code,description) values(502,'es','firmware_ttCreation','Creación:');
insert into message(id,locale,code,description) values(503,'es','firmware_ttModification','Modificación:');
insert into message(id,locale,code,description) values(504,'es','firmware_table_title','Listado de firmwares');
insert into message(id,locale,code,description) values(505,'es','firmware_table_cmts','CTMS');
insert into message(id,locale,code,description) values(506,'es','firmware_table_provisiongroup','Grupo de Provisión');
insert into message(id,locale,code,description) values(507,'es','firmware_table_node','Nodo');
insert into message(id,locale,code,description) values(508,'es','firmware_table_access','Acceso');
insert into message(id,locale,code,description) values(509,'es','firmware_table_manufacturer','Fabricante');
insert into message(id,locale,code,description) values(510,'es','firmware_table_model','Modelo');
insert into message(id,locale,code,description) values(511,'es','firmware_table_firmware','Firmware');
insert into message(id,locale,code,description) values(512,'es','firmware_table_numcpes','N° CPEs');
insert into message(id,locale,code,description) values(513,'es','firmware_table_mods','%Mod');
insert into message(id,locale,code,description) values(514,'es','firmware_table_ttCreation','Creación');
insert into message(id,locale,code,description) values(515,'es','firmware_table_ttModification','Modificación');
insert into message(id,locale,code,description) values(516,'es','firmwareFile_title','Repositorio de Firmware');
insert into message(id,locale,code,description) values(517,'es','firmwareFile_title_search','Búsqueda de firmwares');
insert into message(id,locale,code,description) values(518,'es','firmwareFile_title_detail','Detalle de firmware');
insert into message(id,locale,code,description) values(519,'es','firmwareFile_title_edit','Edición de firmware');
insert into message(id,locale,code,description) values(520,'es','firmwareFile_title_add','Alta de firmware');
insert into message(id,locale,code,description) values(521,'es','firmwareFile_title_upload','Carga de firmware');
insert into message(id,locale,code,description) values(522,'es','firmwareFile_filter_name','Nombre');
insert into message(id,locale,code,description) values(523,'es','firmwareFile_filter_description','Fichero');
insert into message(id,locale,code,description) values(524,'es','firmwareFile_filter_manufacturer','Fabricante');
insert into message(id,locale,code,description) values(525,'es','firmwareFile_filter_model','Modelo');
insert into message(id,locale,code,description) values(526,'es','firmwareFile_filter_firmware','Firmware');
insert into message(id,locale,code,description) values(527,'es','firmwareFile_filter_numcpes','N° CPEs');
insert into message(id,locale,code,description) values(528,'es','firmwareFile_name','Nombre:');
insert into message(id,locale,code,description) values(529,'es','firmwareFile_description','Fichero:');
insert into message(id,locale,code,description) values(530,'es','firmwareFile_manufacturer','Fabricante:');
insert into message(id,locale,code,description) values(531,'es','firmwareFile_model','Modelo:');
insert into message(id,locale,code,description) values(532,'es','firmwareFile_firmware','Firmware:');
insert into message(id,locale,code,description) values(533,'es','firmwareFile_numcpes','N° CPEs:');
insert into message(id,locale,code,description) values(534,'es','firmwareFile_ttCreation','Creación:');
insert into message(id,locale,code,description) values(535,'es','firmwareFile_ttModification','Modificación:');
insert into message(id,locale,code,description) values(536,'es','firmwareFile_table_title','Listado de firmwares');
insert into message(id,locale,code,description) values(537,'es','firmwareFile_table_name','Nombre');
insert into message(id,locale,code,description) values(538,'es','firmwareFile_table_description','Fichero');
insert into message(id,locale,code,description) values(539,'es','firmwareFile_table_manufacturer','Fabricante');
insert into message(id,locale,code,description) values(540,'es','firmwareFile_table_model','Modelos');
insert into message(id,locale,code,description) values(541,'es','firmwareFile_table_firmware','Versión Firmware');
insert into message(id,locale,code,description) values(542,'es','firmwareFile_table_numcpes','N° CPEs');
insert into message(id,locale,code,description) values(543,'es','firmwareFile_table_ttCreation','Creación');
insert into message(id,locale,code,description) values(544,'es','firmwareFile_table_ttModification','Modificación');
insert into message(id,locale,code,description) values(545,'es','cpe_title','CPEs');
insert into message(id,locale,code,description) values(546,'es','cpe_title_reinventoring','Reinventariado de CPEs');
insert into message(id,locale,code,description) values(547,'es','cpe_title_search','Búsqueda de CPEs');
insert into message(id,locale,code,description) values(548,'es','cpe_title_detail','Detalle de CPE');
insert into message(id,locale,code,description) values(549,'es','cpe_title_edit','CWMP');
insert into message(id,locale,code,description) values(550,'es','cpe_title_add','Alta de CPE');
insert into message(id,locale,code,description) values(551,'es','cpe_title_upload','Carga de CPE');
insert into message(id,locale,code,description) values(552,'es','cpe_filter_cpeid','Id.');
insert into message(id,locale,code,description) values(553,'es','cpe_filter_mac','MAC');
insert into message(id,locale,code,description) values(554,'es','cpe_filter_ip','IP');
insert into message(id,locale,code,description) values(555,'es','cpe_filter_series','N° Serie');
insert into message(id,locale,code,description) values(556,'es','cpe_filter_state','Estado');
insert into message(id,locale,code,description) values(557,'es','cpe_filter_error','Último error');
insert into message(id,locale,code,description) values(558,'es','cpe_filter_access','Acceso');
insert into message(id,locale,code,description) values(559,'es','cpe_filter_hostname','Hostname');
insert into message(id,locale,code,description) values(560,'es','cpe_filter_provisiongroup','Grupo de Provisión');
insert into message(id,locale,code,description) values(561,'es','cpe_filter_node','Nodo');
insert into message(id,locale,code,description) values(562,'es','cpe_filter_firmware','Firmware');
insert into message(id,locale,code,description) values(563,'es','cpe_filter_giaddress','Gateway');
insert into message(id,locale,code,description) values(564,'es','cpe_filter_manufacturer','Fabricante');
insert into message(id,locale,code,description) values(565,'es','cpe_filter_model','Modelo');
insert into message(id,locale,code,description) values(566,'es','cpe_filter_category1','Categoría 1');
insert into message(id,locale,code,description) values(567,'es','cpe_filter_category2','Categoría 2');
insert into message(id,locale,code,description) values(568,'es','cpe_filter_category3','Categoría 3');
insert into message(id,locale,code,description) values(569,'es','cpe_filter_category4','Categoría 4');
insert into message(id,locale,code,description) values(570,'es','cpe_filter_category5','Categoría 5');
insert into message(id,locale,code,description) values(571,'es','cpe_cpeid','Id.:');
insert into message(id,locale,code,description) values(572,'es','cpe_mac','MAC:');
insert into message(id,locale,code,description) values(573,'es','cpe_ip','IP:');
insert into message(id,locale,code,description) values(574,'es','cpe_series','N° Serie:');
insert into message(id,locale,code,description) values(575,'es','cpe_state','Estado:');
insert into message(id,locale,code,description) values(576,'es','cpe_error','Último error:');
insert into message(id,locale,code,description) values(577,'es','cpe_access','Acceso:');
insert into message(id,locale,code,description) values(578,'es','cpe_hostname','Hostname:');
insert into message(id,locale,code,description) values(579,'es','cpe_provisiongroup','Grupo de Provisión:');
insert into message(id,locale,code,description) values(580,'es','cpe_node','Nodo:');
insert into message(id,locale,code,description) values(581,'es','cpe_firmware','Firmware:');
insert into message(id,locale,code,description) values(582,'es','cpe_giaddress','Gateway:');
insert into message(id,locale,code,description) values(583,'es','cpe_manufacturer','Fabricante:');
insert into message(id,locale,code,description) values(584,'es','cpe_model','Modelo:');
insert into message(id,locale,code,description) values(585,'es','cpe_connection_role','Rol:');
insert into message(id,locale,code,description) values(586,'es','cpe_connection_template','Plantilla:');
insert into message(id,locale,code,description) values(587,'es','cpe_connection_usercomr','Community Lectura/User:');
insert into message(id,locale,code,description) values(588,'es','cpe_connection_pwdcomw','Community Write/Password:');
insert into message(id,locale,code,description) values(589,'es','cpe_connection_type','Tipo de conexión:');
insert into message(id,locale,code,description) values(590,'es','cpe_connection_cwmpid','CWMPID:');
insert into message(id,locale,code,description) values(591,'es','cpe_connection_cwmpurl','CWMPURL:');
insert into message(id,locale,code,description) values(592,'es','cpe_connection_hash','HASH:');
insert into message(id,locale,code,description) values(593,'es','cpe_category1','Categoría 1:');
insert into message(id,locale,code,description) values(594,'es','cpe_category2','Categoría 2:');
insert into message(id,locale,code,description) values(595,'es','cpe_category3','Categoría 3:');
insert into message(id,locale,code,description) values(596,'es','cpe_category4','Categoría 4:');
insert into message(id,locale,code,description) values(597,'es','cpe_category5','Categoría 5:');
insert into message(id,locale,code,description) values(598,'es','cpe_ttip','Fecha IP:');
insert into message(id,locale,code,description) values(599,'es','cpe_ttidentification','Fecha identificación:');
insert into message(id,locale,code,description) values(600,'es','cpe_tterror','Fecha último error:');
insert into message(id,locale,code,description) values(601,'es','cpe_ttcreation','Creación:');
insert into message(id,locale,code,description) values(602,'es','cpe_ttmodification','Modificación:');
insert into message(id,locale,code,description) values(603,'es','cpe_table_title','Listado de CPEs');
insert into message(id,locale,code,description) values(604,'es','cpe_table_cpeid','Id.');
insert into message(id,locale,code,description) values(605,'es','cpe_table_mac','MAC');
insert into message(id,locale,code,description) values(606,'es','cpe_table_ip','IP');
insert into message(id,locale,code,description) values(607,'es','cpe_table_series','N° Serie');
insert into message(id,locale,code,description) values(608,'es','cpe_table_state','Estado');
insert into message(id,locale,code,description) values(609,'es','cpe_table_error','Último error');
insert into message(id,locale,code,description) values(610,'es','cpe_table_access','Acceso');
insert into message(id,locale,code,description) values(611,'es','cpe_table_hostname','Hostname');
insert into message(id,locale,code,description) values(612,'es','cpe_table_provisiongroup','Grupo de Provisión');
insert into message(id,locale,code,description) values(613,'es','cpe_table_node','Nodo');
insert into message(id,locale,code,description) values(614,'es','cpe_table_firmware','Firmware');
insert into message(id,locale,code,description) values(615,'es','cpe_table_giaddress','Gateway');
insert into message(id,locale,code,description) values(616,'es','cpe_table_manufacturer','Fabricante');
insert into message(id,locale,code,description) values(617,'es','cpe_table_model','Modelo');
insert into message(id,locale,code,description) values(618,'es','cpe_table_connection_role','Rol');
insert into message(id,locale,code,description) values(619,'es','cpe_table_connection_template','Plantilla');
insert into message(id,locale,code,description) values(620,'es','cpe_table_connection_usercomr','Community Lectura/User');
insert into message(id,locale,code,description) values(621,'es','cpe_table_connection_pwdcomw','Community Write/Password');
insert into message(id,locale,code,description) values(622,'es','cpe_table_connection_type','Tipo de conexión');
insert into message(id,locale,code,description) values(623,'es','cpe_table_connection_cwmpid','CWMPID');
insert into message(id,locale,code,description) values(624,'es','cpe_table_connection_cwmpurl','CWMPURL');
insert into message(id,locale,code,description) values(625,'es','cpe_table_connection_hash','HASH');
insert into message(id,locale,code,description) values(626,'es','cpe_table_category1','Categoría 1');
insert into message(id,locale,code,description) values(627,'es','cpe_table_category2','Categoría 2');
insert into message(id,locale,code,description) values(628,'es','cpe_table_category3','Categoría 3');
insert into message(id,locale,code,description) values(629,'es','cpe_table_category4','Categoría 4');
insert into message(id,locale,code,description) values(630,'es','cpe_table_category5','Categoría 5');
insert into message(id,locale,code,description) values(631,'es','cpe_table_ttip','Fecha IP');
insert into message(id,locale,code,description) values(632,'es','cpe_table_ttidentification','Fecha identificación');
insert into message(id,locale,code,description) values(633,'es','cpe_table_tterror','Fecha último error');
insert into message(id,locale,code,description) values(634,'es','cpe_table_ttcreation','Creación');
insert into message(id,locale,code,description) values(635,'es','cpe_table_ttmodification','Modificación');
insert into message(id,locale,code,description) values(636,'es','cpe_cwmp_getparval','Get Parameter Values');
insert into message(id,locale,code,description) values(637,'es','cpe_cwmp_par','Parámetro');
insert into message(id,locale,code,description) values(638,'es','cpe_cwmp_val','Valor');
insert into message(id,locale,code,description) values(639,'es','cpe_cwmp_getparnam','Get Parameter Names');
insert into message(id,locale,code,description) values(640,'es','cpe_cwmp_path','Path Parámetro');
insert into message(id,locale,code,description) values(641,'es','cpe_cwmp_next','Siguiente Nivel:');
insert into message(id,locale,code,description) values(642,'es','cpe_cwmp_rw','Lectura/Escritura');
insert into message(id,locale,code,description) values(643,'es','cpe_cwmp_getrpcmet','Get RPC Methods');
insert into message(id,locale,code,description) values(644,'es','cpe_cwmp_rpcmet','Métodos RPC');
insert into message(id,locale,code,description) values(645,'es','cpe_cwmp_others','Others');
insert into message(id,locale,code,description) values(646,'es','cpe_cwmp_setparval','Set Parameter Values');
insert into message(id,locale,code,description) values(647,'es','cpe_cwmp_addobject','Add Object');
insert into message(id,locale,code,description) values(648,'es','cpe_cwmp_delobject','Delete Object');
insert into message(id,locale,code,description) values(649,'es','cpe_cwmp_objnam','Object Name');
insert into message(id,locale,code,description) values(650,'es','cpe_cwmp_parkey','Parameter Key');
insert into message(id,locale,code,description) values(651,'es','tutorial_title','Tutoriales');
insert into message(id,locale,code,description) values(652,'es','tutorial_title_search','Búsqueda de tutoriales');
insert into message(id,locale,code,description) values(653,'es','tutorial_title_detail','Detalle de tutorial');
insert into message(id,locale,code,description) values(654,'es','tutorial_title_edit','Edición de tutorial');
insert into message(id,locale,code,description) values(655,'es','tutorial_title_add','Alta de tutorial');
insert into message(id,locale,code,description) values(656,'es','tutorial_title_upload','Carga de tutorial');
insert into message(id,locale,code,description) values(657,'es','tutorial_filter_name','Nombre');
insert into message(id,locale,code,description) values(658,'es','tutorial_name','Nombre:');
insert into message(id,locale,code,description) values(659,'es','tutorial_role','Rol:');
insert into message(id,locale,code,description) values(660,'es','tutorial_manufacturer','Fabricante:');
insert into message(id,locale,code,description) values(661,'es','tutorial_model','Modelo:');
insert into message(id,locale,code,description) values(662,'es','tutorial_ttCreation','Creación:');
insert into message(id,locale,code,description) values(663,'es','tutorial_ttModification','Modificación:');
insert into message(id,locale,code,description) values(664,'es','tutorial_table_title','Listado de tutoriales');
insert into message(id,locale,code,description) values(665,'es','tutorial_table_name','Nombre');
insert into message(id,locale,code,description) values(666,'es','tutorial_table_role','Rol');
insert into message(id,locale,code,description) values(667,'es','tutorial_table_manufacturer','Fabricante');
insert into message(id,locale,code,description) values(668,'es','tutorial_table_model','Modelo');
insert into message(id,locale,code,description) values(669,'es','tutorial_table_ttcreation','Creación');
insert into message(id,locale,code,description) values(670,'es','tutorial_table_ttmodification','Modificación');
insert into message(id,locale,code,description) values(671,'es','tutorial_edit_newtutorial','Nuevo tutorial');
insert into message(id,locale,code,description) values(672,'es','tutorial_edit_editor','Editor');
insert into message(id,locale,code,description) values(673,'es','tutorial_edit_changename','Cambiar nombre');
insert into message(id,locale,code,description) values(674,'es','tutorial_edit_page_newpage','Nueva página');
insert into message(id,locale,code,description) values(675,'es','tutorial_edit_page_newpagecontent','<p>Contenido de la página...</p>');
insert into message(id,locale,code,description) values(676,'es','tutorial_edit_page_deletepage','Eliminar página');
insert into message(id,locale,code,description) values(677,'es','tutorial_edit_page_pageid','ID de página');
insert into message(id,locale,code,description) values(678,'es','tutorial_edit_page_changepage','Cambio de página');
insert into message(id,locale,code,description) values(679,'es','tutorial_edit_button_createbutton','Crear botón');
insert into message(id,locale,code,description) values(680,'es','tutorial_edit_button_action','Acción');
insert into message(id,locale,code,description) values(681,'es','tutorial_edit_button_actiontype','Tipo de acción');
insert into message(id,locale,code,description) values(682,'es','tutorial_edit_button_value','Valor');
insert into message(id,locale,code,description) values(683,'es','tutorial_edit_button_role','Rol');
insert into message(id,locale,code,description) values(684,'es','tutorial_edit_button_label','Etiqueta');
insert into message(id,locale,code,description) values(685,'es','tutorial_edit_button_buttonprops','Propiedades del botón');
insert into message(id,locale,code,description) values(686,'es','tutorial_edit_exiteditor','Salir del editor');
insert into message(id,locale,code,description) values(687,'es','tutorial_edit_pagecontent','<p>Contenido de la página...</p>');
insert into message(id,locale,code,description) values(688,'es','tutorial_edit_map_title','Mapa');
insert into message(id,locale,code,description) values(689,'es','tutorial_edit_map_zoomin','Acercar');
insert into message(id,locale,code,description) values(690,'es','tutorial_edit_map_zoomout','Alejar');
insert into message(id,locale,code,description) values(691,'es','tutorial_linkedTutorial','Tutorial diagnóstico enlazado');
insert into message(id,locale,code,description) values(692,'es','multimedia_title','Multimedia');
insert into message(id,locale,code,description) values(693,'es','multimedia_title_search','Búsqueda de multimedia');
insert into message(id,locale,code,description) values(694,'es','multimedia_title_detail','Detalle de multimedia');
insert into message(id,locale,code,description) values(695,'es','multimedia_title_edit','Edición de multimedia');
insert into message(id,locale,code,description) values(696,'es','multimedia_title_add','Alta de multimedia');
insert into message(id,locale,code,description) values(697,'es','multimedia_title_upload','Carga de multimedia');
insert into message(id,locale,code,description) values(698,'es','multimedia_filter_name','Nombre');
insert into message(id,locale,code,description) values(699,'es','multimedia_filter_contenttype','Tipo de contenido');
insert into message(id,locale,code,description) values(700,'es','multimedia_name','Nombre:');
insert into message(id,locale,code,description) values(701,'es','multimedia_contenttype','Tipo de contenido:');
insert into message(id,locale,code,description) values(702,'es','multimedia_ttCreation','Creación:');
insert into message(id,locale,code,description) values(703,'es','multimedia_ttModification','Modificación:');
insert into message(id,locale,code,description) values(704,'es','multimedia_table_title','Listado de multimedia');
insert into message(id,locale,code,description) values(705,'es','multimedia_table_name','Nombre');
insert into message(id,locale,code,description) values(706,'es','multimedia_table_media','Media');
insert into message(id,locale,code,description) values(707,'es','multimedia_table_contenttype','Tipo de contenido');
insert into message(id,locale,code,description) values(708,'es','multimedia_table_ttcreation','Creación');
insert into message(id,locale,code,description) values(709,'es','multimedia_table_ttmodification','Modificación');
insert into message(id,locale,code,description) values(710,'es','polinex_title','Listado de MACs de Inclusión/Exclusión');
insert into message(id,locale,code,description) values(711,'es','polinex_title_search','Búsqueda de MACs');
insert into message(id,locale,code,description) values(712,'es','polinex_title_detail','Detalle de MAC');
insert into message(id,locale,code,description) values(713,'es','polinex_title_edit','Edición de MAC');
insert into message(id,locale,code,description) values(714,'es','polinex_title_add','Alta de MAC');
insert into message(id,locale,code,description) values(715,'es','polinex_title_upload','Carga de MACs');
insert into message(id,locale,code,description) values(716,'es','polinex_filter_name','Nombre');
insert into message(id,locale,code,description) values(717,'es','polinex_filter_type','Tipo');
insert into message(id,locale,code,description) values(718,'es','polinex_filter_mac','MACs');
insert into message(id,locale,code,description) values(719,'es','polinex_name','Nombre:');
insert into message(id,locale,code,description) values(720,'es','polinex_type','Tipo:');
insert into message(id,locale,code,description) values(721,'es','polinex_type_exglobal','Exclusión global');
insert into message(id,locale,code,description) values(722,'es','polinex_type_exespec','Exclusión específica');
insert into message(id,locale,code,description) values(723,'es','polinex_type_incl','Inclusión');
insert into message(id,locale,code,description) values(724,'es','polinex_mac','MACs:');
insert into message(id,locale,code,description) values(725,'es','polinex_ttCreation','Creación:');
insert into message(id,locale,code,description) values(726,'es','polinex_ttModification','Modificación:');
insert into message(id,locale,code,description) values(727,'es','polinex_table_title','Listado de MACs');
insert into message(id,locale,code,description) values(728,'es','polinex_table_name','Nombre');
insert into message(id,locale,code,description) values(729,'es','polinex_table_type','Tipo');
insert into message(id,locale,code,description) values(730,'es','polinex_table_mac','MACs');
insert into message(id,locale,code,description) values(731,'es','polinex_table_ttCreation','Creación');
insert into message(id,locale,code,description) values(732,'es','polinex_table_ttModification','Modificación');
insert into message(id,locale,code,description) values(733,'es','template_title','Plantillas');
insert into message(id,locale,code,description) values(734,'es','template_title_search','Búsqueda de plantillas');
insert into message(id,locale,code,description) values(735,'es','template_title_detail','Detalle de plantilla');
insert into message(id,locale,code,description) values(736,'es','template_title_edit','Edición de plantilla');
insert into message(id,locale,code,description) values(737,'es','template_title_add','Alta de plantilla');
insert into message(id,locale,code,description) values(738,'es','template_title_upload','Carga de plantilla');
insert into message(id,locale,code,description) values(739,'es','template_filter_name','Fichero');
insert into message(id,locale,code,description) values(740,'es','template_name','Fichero:');
insert into message(id,locale,code,description) values(741,'es','template_manufacturer','Fabricante:');
insert into message(id,locale,code,description) values(742,'es','template_model','Modelo:');
insert into message(id,locale,code,description) values(743,'es','template_operativeSystem','Sistema Operativo:');
insert into message(id,locale,code,description) values(744,'es','template_role','Rol:');
insert into message(id,locale,code,description) values(745,'es','template_MD5Read','Firma MD5:');
insert into message(id,locale,code,description) values(746,'es','template_md5Ok','MD5:');
insert into message(id,locale,code,description) values(747,'es','template_ttCreation','Creación:');
insert into message(id,locale,code,description) values(748,'es','template_ttModification','Modificación:');
insert into message(id,locale,code,description) values(749,'es','template_table_title','Listado de plantillas');
insert into message(id,locale,code,description) values(750,'es','template_table_name','Fichero');
insert into message(id,locale,code,description) values(751,'es','template_table_manufacturer','Fabricante');
insert into message(id,locale,code,description) values(752,'es','template_table_model','Modelo');
insert into message(id,locale,code,description) values(753,'es','template_table_operativeSystem','Sistema Operativo');
insert into message(id,locale,code,description) values(754,'es','template_table_role','Rol');
insert into message(id,locale,code,description) values(755,'es','template_table_MD5Read','Firma MD5');
insert into message(id,locale,code,description) values(756,'es','template_table_md5Ok','MD5');
insert into message(id,locale,code,description) values(757,'es','template_table_ttcreation','Creación');
insert into message(id,locale,code,description) values(758,'es','template_table_ttmodification','Modificación');
insert into message(id,locale,code,description) values(759,'es','scene_title','Gestor de Escenarios');
insert into message(id,locale,code,description) values(760,'es','scene_title_search','Búsqueda de escenario');
insert into message(id,locale,code,description) values(761,'es','scene_title_detail','Detalle de escenario');
insert into message(id,locale,code,description) values(762,'es','scene_title_edit','Edición de escenario');
insert into message(id,locale,code,description) values(763,'es','scene_title_add','Alta de escenario');
insert into message(id,locale,code,description) values(764,'es','scene_title_upload','Carga de escenario');
insert into message(id,locale,code,description) values(765,'es','scene_filter_file','Nombre');
insert into message(id,locale,code,description) values(766,'es','scene_filter_service','Servicio');
insert into message(id,locale,code,description) values(767,'es','scene_filter_description','Descripción');
insert into message(id,locale,code,description) values(768,'es','scene_file','Nombre:');
insert into message(id,locale,code,description) values(769,'es','scene_service','Servicio:');
insert into message(id,locale,code,description) values(770,'es','scene_description','Descripción:');
insert into message(id,locale,code,description) values(771,'es','scene_ttCreation','Creación:');
insert into message(id,locale,code,description) values(772,'es','scene_ttModification','Modificación:');
insert into message(id,locale,code,description) values(773,'es','scene_table_title','Listado de escenarios');
insert into message(id,locale,code,description) values(774,'es','scene_table_file','Nombre');
insert into message(id,locale,code,description) values(775,'es','scene_table_service','Servicio');
insert into message(id,locale,code,description) values(776,'es','scene_table_description','Descripción');
insert into message(id,locale,code,description) values(777,'es','scene_table_ttcreation','Creación');
insert into message(id,locale,code,description) values(778,'es','scene_table_ttmodification','Modificación');
insert into message(id,locale,code,description) values(779,'es','polunitary_title','Políticas Unitarias de Gestión de Firmware');
insert into message(id,locale,code,description) values(780,'es','polunitary_title_search','Búsqueda de Políticas Unitarias');
insert into message(id,locale,code,description) values(781,'es','polunitary_title_detail','Detalle de políticas unitarias');
insert into message(id,locale,code,description) values(782,'es','polunitary_title_edit','Edición de políticas unitarias');
insert into message(id,locale,code,description) values(783,'es','polunitary_title_add','Alta de políticas unitarias');
insert into message(id,locale,code,description) values(784,'es','polunitary_title_chart','Evolución en los últimos 30 Días');
insert into message(id,locale,code,description) values(785,'es','polunitary_filter_name','Política');
insert into message(id,locale,code,description) values(786,'es','polunitary_filter_mac','Mac');
insert into message(id,locale,code,description) values(787,'es','polunitary_filter_hostname','Acceso');
insert into message(id,locale,code,description) values(788,'es','polunitary_filter_manufacturer','Fabricante');
insert into message(id,locale,code,description) values(789,'es','polunitary_filter_model','Modelo');
insert into message(id,locale,code,description) values(790,'es','polunitary_filter_firmware','Firmware CPE');
insert into message(id,locale,code,description) values(791,'es','polunitary_filter_frworigin','Firmware Inicial');
insert into message(id,locale,code,description) values(792,'es','polunitary_filter_frwobjetive','Firmware Objetivo');
insert into message(id,locale,code,description) values(793,'es','polunitary_filter_frwobjetivever','Versión F.O.');
insert into message(id,locale,code,description) values(794,'es','polunitary_filter_error','Error de Migración');
insert into message(id,locale,code,description) values(795,'es','polunitary_name','Política:');
insert into message(id,locale,code,description) values(796,'es','polunitary_mac','Mac:');
insert into message(id,locale,code,description) values(797,'es','polunitary_hostname','Acceso:');
insert into message(id,locale,code,description) values(798,'es','polunitary_manufacturer','Fabricante:');
insert into message(id,locale,code,description) values(799,'es','polunitary_model','Modelo:');
insert into message(id,locale,code,description) values(800,'es','polunitary_firmware','Firmware CPE:');
insert into message(id,locale,code,description) values(801,'es','polunitary_frworigin','Firmware Inicial:');
insert into message(id,locale,code,description) values(802,'es','polunitary_frwobjetive','Firmware Objetivo:');
insert into message(id,locale,code,description) values(803,'es','polunitary_frwobjetivever','Versión F.O.:');
insert into message(id,locale,code,description) values(804,'es','polunitary_error','Error de Migración:');
insert into message(id,locale,code,description) values(805,'es','polunitary_ttidf','Identificación:');
insert into message(id,locale,code,description) values(806,'es','polunitary_ttmig','Migración:');
insert into message(id,locale,code,description) values(807,'es','polunitary_ttCreation','Creación:');
insert into message(id,locale,code,description) values(808,'es','polunitary_ttModification','Modificación:');
insert into message(id,locale,code,description) values(809,'es','polunitary_table_title','Listado de políticas unitarias');
insert into message(id,locale,code,description) values(810,'es','polunitary_table_name','Política');
insert into message(id,locale,code,description) values(811,'es','polunitary_table_mac','Mac');
insert into message(id,locale,code,description) values(812,'es','polunitary_table_hostname','Acceso');
insert into message(id,locale,code,description) values(813,'es','polunitary_table_manufacturer','Fabricante');
insert into message(id,locale,code,description) values(814,'es','polunitary_table_model','Modelo');
insert into message(id,locale,code,description) values(815,'es','polunitary_table_firmware','Firmware CPE');
insert into message(id,locale,code,description) values(816,'es','polunitary_table_frworigin','Firmware Inicial');
insert into message(id,locale,code,description) values(817,'es','polunitary_table_frwobjetive','Firmware Objetivo');
insert into message(id,locale,code,description) values(818,'es','polunitary_table_frwobjetivever','Versión F.O.');
insert into message(id,locale,code,description) values(819,'es','polunitary_table_error','Error de Migración');
insert into message(id,locale,code,description) values(820,'es','polunitary_table_ttidf','Identificación');
insert into message(id,locale,code,description) values(821,'es','polunitary_table_ttmig','Migración');
insert into message(id,locale,code,description) values(822,'es','polunitary_table_ttcreation','Creación');
insert into message(id,locale,code,description) values(823,'es','polunitary_table_ttmodification','Modificación');
insert into message(id,locale,code,description) values(824,'es','polmassive_title','Políticas Masivas de Gestión de Firmware');
insert into message(id,locale,code,description) values(825,'es','polmassive_title_search','Búsqueda de políticas Masivas');
insert into message(id,locale,code,description) values(826,'es','polmassive_title_detail','Detalle de políticas masivas');
insert into message(id,locale,code,description) values(827,'es','polmassive_title_edit','Edición de políticas masivas');
insert into message(id,locale,code,description) values(828,'es','polmassive_title_add','Alta de políticas masivas');
insert into message(id,locale,code,description) values(829,'es','polmassive_title_chart','Evolución en los últimos 30 Días');
insert into message(id,locale,code,description) values(830,'es','polmassive_filter_name','Política');
insert into message(id,locale,code,description) values(831,'es','polmassive_filter_macfilter','Filtro de Mac');
insert into message(id,locale,code,description) values(832,'es','polmassive_filter_state','Estado');
insert into message(id,locale,code,description) values(833,'es','polmassive_filter_state_active','Activo');
insert into message(id,locale,code,description) values(834,'es','polmassive_filter_state_inactive','Inactivo');
insert into message(id,locale,code,description) values(835,'es','polmassive_filter_priority','Prioridad');
insert into message(id,locale,code,description) values(836,'es','polmassive_filter_priority_low','Baja');
insert into message(id,locale,code,description) values(837,'es','polmassive_filter_priority_medium','Media');
insert into message(id,locale,code,description) values(838,'es','polmassive_filter_priority_high','Alta');
insert into message(id,locale,code,description) values(839,'es','polmassive_filter_bydefault','Valor por defecto');
insert into message(id,locale,code,description) values(840,'es','polmassive_filter_exception','Excepción');
insert into message(id,locale,code,description) values(841,'es','polmassive_filter_mininit','Inicio de Ventana');
insert into message(id,locale,code,description) values(842,'es','polmassive_filter_minend','Fin de Ventana');
insert into message(id,locale,code,description) values(843,'es','polmassive_filter_ttinit','Inicio de Periodo');
insert into message(id,locale,code,description) values(844,'es','polmassive_filter_ttend','Fin de Periodo');
insert into message(id,locale,code,description) values(845,'es','polmassive_filter_manufacturer','Fabricante');
insert into message(id,locale,code,description) values(846,'es','polmassive_filter_inclussion','Inclusión de MACs');
insert into message(id,locale,code,description) values(847,'es','polmassive_filter_exclussion','Exclusión de MACs');
insert into message(id,locale,code,description) values(848,'es','polmassive_filter_model','Modelo');
insert into message(id,locale,code,description) values(849,'es','polmassive_filter_access','Equipos de Acceso');
insert into message(id,locale,code,description) values(850,'es','polmassive_filter_frworigin','Versiones Firmware Iniciales');
insert into message(id,locale,code,description) values(851,'es','polmassive_filter_frwobjetive','Fichero Firmware Objetivo');
insert into message(id,locale,code,description) values(852,'es','polmassive_filter_frwobjetivever','Versión Firmware Objetivo');
insert into message(id,locale,code,description) values(853,'es','polmassive_filter_ttcreation','Creación');
insert into message(id,locale,code,description) values(854,'es','polmassive_filter_ttmodification','Modificación');
insert into message(id,locale,code,description) values(855,'es','polmassive_name','Política:');
insert into message(id,locale,code,description) values(856,'es','polmassive_macfilter','Filtro de Mac:');
insert into message(id,locale,code,description) values(857,'es','polmassive_state','Estado:');
insert into message(id,locale,code,description) values(858,'es','polmassive_state_active','Activo');
insert into message(id,locale,code,description) values(859,'es','polmassive_state_inactive','Inactivo');
insert into message(id,locale,code,description) values(860,'es','polmassive_priority','Prioridad:');
insert into message(id,locale,code,description) values(861,'es','polmassive_priority_low','Baja');
insert into message(id,locale,code,description) values(862,'es','polmassive_priority_medium','Media');
insert into message(id,locale,code,description) values(863,'es','polmassive_priority_high','Alta');
insert into message(id,locale,code,description) values(864,'es','polmassive_bydefault','Valor por defecto:');
insert into message(id,locale,code,description) values(865,'es','polmassive_bydefault_true','Sí');
insert into message(id,locale,code,description) values(866,'es','polmassive_bydefault_false','No');
insert into message(id,locale,code,description) values(867,'es','polmassive_exception','Excepción:');
insert into message(id,locale,code,description) values(868,'es','polmassive_exception_true','Sí');
insert into message(id,locale,code,description) values(869,'es','polmassive_exception_false','No');
insert into message(id,locale,code,description) values(870,'es','polmassive_mininit','Inicio de Ventana:');
insert into message(id,locale,code,description) values(871,'es','polmassive_minend','Fin de Ventana:');
insert into message(id,locale,code,description) values(872,'es','polmassive_ttinit','Inicio de Periodo:');
insert into message(id,locale,code,description) values(873,'es','polmassive_ttend','Fin de Periodo:');
insert into message(id,locale,code,description) values(874,'es','polmassive_manufacturer','Fabricante:');
insert into message(id,locale,code,description) values(875,'es','polmassive_inclussion','Inclusión de MACs:');
insert into message(id,locale,code,description) values(876,'es','polmassive_exclussion','Exclusión de MACs:');
insert into message(id,locale,code,description) values(877,'es','polmassive_model','Modelo:');
insert into message(id,locale,code,description) values(878,'es','polmassive_access','Equipos de Acceso:');
insert into message(id,locale,code,description) values(879,'es','polmassive_frworigin','Versiones Firmware Iniciales:');
insert into message(id,locale,code,description) values(880,'es','polmassive_frwobjetive','Fichero Firmware Objetivo:');
insert into message(id,locale,code,description) values(881,'es','polmassive_frwobjetivever','Versión Firmware Objetivo:');
insert into message(id,locale,code,description) values(882,'es','polmassive_ttcreation','Creación:');
insert into message(id,locale,code,description) values(883,'es','polmassive_ttmodification','Modificación:');
insert into message(id,locale,code,description) values(884,'es','polmassive_weekdays','Días de la semana');
insert into message(id,locale,code,description) values(885,'es','polmassive_numinicial','#Ini:');
insert into message(id,locale,code,description) values(886,'es','polmassive_numobjetivo','#Obj:');
insert into message(id,locale,code,description) values(887,'es','polmassive_numtotal','#I+O:');
insert into message(id,locale,code,description) values(888,'es','polmassive_migpercentage','%Mig:');
insert into message(id,locale,code,description) values(889,'es','polmassive_category1','Categoría 1');
insert into message(id,locale,code,description) values(890,'es','polmassive_category2','Categoría 2');
insert into message(id,locale,code,description) values(891,'es','polmassive_category3','Categoría 3');
insert into message(id,locale,code,description) values(892,'es','polmassive_category4','Categoría 4');
insert into message(id,locale,code,description) values(893,'es','polmassive_category5','Categoría 5');
insert into message(id,locale,code,description) values(894,'es','polmassive_table_title','Listado de políticas masivas');
insert into message(id,locale,code,description) values(895,'es','polmassive_table_name','Política');
insert into message(id,locale,code,description) values(896,'es','polmassive_table_macfilter','Filtro de Mac');
insert into message(id,locale,code,description) values(897,'es','polmassive_table_state','Estado');
insert into message(id,locale,code,description) values(898,'es','polmassive_table_priority','Prioridad');
insert into message(id,locale,code,description) values(899,'es','polmassive_table_bydefault','Valor por defecto');
insert into message(id,locale,code,description) values(900,'es','polmassive_table_bydefault_true','Sí');
insert into message(id,locale,code,description) values(901,'es','polmassive_table_bydefault_false','No');
insert into message(id,locale,code,description) values(902,'es','polmassive_table_exception','Excepción');
insert into message(id,locale,code,description) values(903,'es','polmassive_table_exception_true','Sí');
insert into message(id,locale,code,description) values(904,'es','polmassive_table_exception_false','No');
insert into message(id,locale,code,description) values(905,'es','polmassive_table_mininit','Inicio de Ventana');
insert into message(id,locale,code,description) values(906,'es','polmassive_table_minend','Fin de Ventana');
insert into message(id,locale,code,description) values(907,'es','polmassive_table_ttinit','Inicio de Periodo');
insert into message(id,locale,code,description) values(908,'es','polmassive_table_ttend','Fin de Periodo');
insert into message(id,locale,code,description) values(909,'es','polmassive_table_manufacturer','Fabricante');
insert into message(id,locale,code,description) values(910,'es','polmassive_table_inclussion','Inclusión de MACs');
insert into message(id,locale,code,description) values(911,'es','polmassive_table_exclussion','Exclusión de MACs');
insert into message(id,locale,code,description) values(912,'es','polmassive_table_model','Modelo');
insert into message(id,locale,code,description) values(913,'es','polmassive_table_access','Equipos de Acceso');
insert into message(id,locale,code,description) values(914,'es','polmassive_table_frworigin','Versiones Firmware Iniciales');
insert into message(id,locale,code,description) values(915,'es','polmassive_table_frwobjetive','Fichero Firmware Objetivo');
insert into message(id,locale,code,description) values(916,'es','polmassive_table_frwobjetivever','Versión Firmware Objetivo');
insert into message(id,locale,code,description) values(917,'es','polmassive_table_ttcreation','Creación');
insert into message(id,locale,code,description) values(918,'es','polmassive_table_ttmodification','Modificación');
insert into message(id,locale,code,description) values(919,'es','polmassive_table_numinicial','#Ini');
insert into message(id,locale,code,description) values(920,'es','polmassive_table_numobjetivo','#Obj');
insert into message(id,locale,code,description) values(921,'es','polmassive_table_numtotal','#I+O');
insert into message(id,locale,code,description) values(922,'es','polmassive_table_migpercentage','%Mig');
insert into message(id,locale,code,description) values(923,'es','polmassive_table_weekdays','Días de la semana');
insert into message(id,locale,code,description) values(924,'es','polmassive_task_running','Política en ejecución');
insert into message(id,locale,code,description) values(925,'es','polbase_title','Políticas Base de Gestión de Firmware');
insert into message(id,locale,code,description) values(926,'es','polbase_title_search','Búsqueda de Políticas Base');
insert into message(id,locale,code,description) values(927,'es','polbase_title_detail','Detalle de políticas base');
insert into message(id,locale,code,description) values(928,'es','polbase_title_edit','Edición de políticas base');
insert into message(id,locale,code,description) values(929,'es','polbase_title_add','Alta de políticas base');
insert into message(id,locale,code,description) values(930,'es','polbase_title_chart','Evolución en los últimos 30 Días');
insert into message(id,locale,code,description) values(931,'es','polbase_filter_name','Política');
insert into message(id,locale,code,description) values(932,'es','polbase_filter_macfilter','Filtro de Mac');
insert into message(id,locale,code,description) values(933,'es','polbase_filter_state','Estado');
insert into message(id,locale,code,description) values(934,'es','polbase_filter_state_active','Activo');
insert into message(id,locale,code,description) values(935,'es','polbase_filter_state_inactive','Inactivo');
insert into message(id,locale,code,description) values(936,'es','polbase_filter_priority','Prioridad');
insert into message(id,locale,code,description) values(937,'es','polbase_filter_priority_low','Baja');
insert into message(id,locale,code,description) values(938,'es','polbase_filter_priority_medium','Media');
insert into message(id,locale,code,description) values(939,'es','polbase_filter_priority_high','Alta');
insert into message(id,locale,code,description) values(940,'es','polbase_filter_bydefault','Valor por defecto');
insert into message(id,locale,code,description) values(941,'es','polbase_filter_exception','Excepción');
insert into message(id,locale,code,description) values(942,'es','polbase_filter_mininit','Inicio de Ventana');
insert into message(id,locale,code,description) values(943,'es','polbase_filter_minend','Fin de Ventana');
insert into message(id,locale,code,description) values(944,'es','polbase_filter_ttinit','Inicio de Periodo');
insert into message(id,locale,code,description) values(945,'es','polbase_filter_ttend','Fin de Periodo');
insert into message(id,locale,code,description) values(946,'es','polbase_filter_manufacturer','Fabricante');
insert into message(id,locale,code,description) values(947,'es','polbase_filter_inclussion','Inclusión de MACs');
insert into message(id,locale,code,description) values(948,'es','polbase_filter_exclussion','Exclusión de MACs');
insert into message(id,locale,code,description) values(949,'es','polbase_filter_model','Modelo');
insert into message(id,locale,code,description) values(950,'es','polbase_filter_access','Equipos de Acceso');
insert into message(id,locale,code,description) values(951,'es','polbase_filter_frworigin','Versiones Firmware Iniciales');
insert into message(id,locale,code,description) values(952,'es','polbase_filter_frwobjetive','Fichero Firmware Objetivo');
insert into message(id,locale,code,description) values(953,'es','polbase_filter_frwobjetivever','Versión Firmware Objetivo');
insert into message(id,locale,code,description) values(954,'es','polbase_filter_ttcreation','Creación');
insert into message(id,locale,code,description) values(955,'es','polbase_filter_ttmodification','Modificación');
insert into message(id,locale,code,description) values(956,'es','polbase_name','Política:');
insert into message(id,locale,code,description) values(957,'es','polbase_macfilter','Filtro de Mac:');
insert into message(id,locale,code,description) values(958,'es','polbase_state','Estado:');
insert into message(id,locale,code,description) values(959,'es','polbase_state_active','Activo');
insert into message(id,locale,code,description) values(960,'es','polbase_state_inactive','Inactivo');
insert into message(id,locale,code,description) values(961,'es','polbase_priority','Prioridad:');
insert into message(id,locale,code,description) values(962,'es','polbase_priority_low','Baja');
insert into message(id,locale,code,description) values(963,'es','polbase_priority_medium','Media');
insert into message(id,locale,code,description) values(964,'es','polbase_priority_high','Alta');
insert into message(id,locale,code,description) values(965,'es','polbase_bydefault','Valor por defecto:');
insert into message(id,locale,code,description) values(966,'es','polbase_bydefault_true','Sí');
insert into message(id,locale,code,description) values(967,'es','polbase_bydefault_false','No');
insert into message(id,locale,code,description) values(968,'es','polbase_exception','Excepción:');
insert into message(id,locale,code,description) values(969,'es','polbase_exception_true','Sí');
insert into message(id,locale,code,description) values(970,'es','polbase_exception_false','No');
insert into message(id,locale,code,description) values(971,'es','polbase_mininit','Inicio de Ventana:');
insert into message(id,locale,code,description) values(972,'es','polbase_minend','Fin de Ventana:');
insert into message(id,locale,code,description) values(973,'es','polbase_ttinit','Inicio de Periodo:');
insert into message(id,locale,code,description) values(974,'es','polbase_ttend','Fin de Periodo:');
insert into message(id,locale,code,description) values(975,'es','polbase_manufacturer','Fabricante:');
insert into message(id,locale,code,description) values(976,'es','polbase_inclussion','Inclusión de MACs:');
insert into message(id,locale,code,description) values(977,'es','polbase_exclussion','Exclusión de MACs:');
insert into message(id,locale,code,description) values(978,'es','polbase_model','Modelo:');
insert into message(id,locale,code,description) values(979,'es','polbase_access','Equipos de Acceso:');
insert into message(id,locale,code,description) values(980,'es','polbase_frworigin','Versiones Firmware Iniciales:');
insert into message(id,locale,code,description) values(981,'es','polbase_frwobjetive','Fichero Firmware Objetivo:');
insert into message(id,locale,code,description) values(982,'es','polbase_frwobjetivever','Versión Firmware Objetivo:');
insert into message(id,locale,code,description) values(983,'es','polbase_ttcreation','Creación:');
insert into message(id,locale,code,description) values(984,'es','polbase_ttmodification','Modificación:');
insert into message(id,locale,code,description) values(985,'es','polbase_weekdays','Días de la semana');
insert into message(id,locale,code,description) values(986,'es','polbase_numpermitidos','#Perm');
insert into message(id,locale,code,description) values(987,'es','polbase_numpermpercentage','%Perm');
insert into message(id,locale,code,description) values(988,'es','polbase_numobjetivo','#Base');
insert into message(id,locale,code,description) values(989,'es','polbase_numobjpercentage','%Base');
insert into message(id,locale,code,description) values(990,'es','polbase_numtotal','#Tot');
insert into message(id,locale,code,description) values(991,'es','polbase_table_title','Listado de políticas base');
insert into message(id,locale,code,description) values(992,'es','polbase_table_name','Política');
insert into message(id,locale,code,description) values(993,'es','polbase_table_macfilter','Filtro de Mac');
insert into message(id,locale,code,description) values(994,'es','polbase_table_state','Estado');
insert into message(id,locale,code,description) values(995,'es','polbase_table_priority','Prioridad');
insert into message(id,locale,code,description) values(996,'es','polbase_table_bydefault','Valor por defecto');
insert into message(id,locale,code,description) values(997,'es','polbase_table_bydefault_true','Sí');
insert into message(id,locale,code,description) values(998,'es','polbase_table_bydefault_false','No');
insert into message(id,locale,code,description) values(999,'es','polbase_table_exception','Excepción');
insert into message(id,locale,code,description) values(1000,'es','polbase_table_exception_true','Sí');
insert into message(id,locale,code,description) values(1001,'es','polbase_table_exception_false','No');
insert into message(id,locale,code,description) values(1002,'es','polbase_table_mininit','Inicio de Ventana');
insert into message(id,locale,code,description) values(1003,'es','polbase_table_minend','Fin de Ventana');
insert into message(id,locale,code,description) values(1004,'es','polbase_table_ttinit','Inicio de Periodo');
insert into message(id,locale,code,description) values(1005,'es','polbase_table_ttend','Fin de Periodo');
insert into message(id,locale,code,description) values(1006,'es','polbase_table_manufacturer','Fabricante');
insert into message(id,locale,code,description) values(1007,'es','polbase_table_inclussion','Inclusión de MACs');
insert into message(id,locale,code,description) values(1008,'es','polbase_table_exclussion','Exclusión de MACs');
insert into message(id,locale,code,description) values(1009,'es','polbase_table_model','Modelo');
insert into message(id,locale,code,description) values(1010,'es','polbase_table_access','Equipos de Acceso');
insert into message(id,locale,code,description) values(1011,'es','polbase_table_frworigin','Versiones Firmware Iniciales');
insert into message(id,locale,code,description) values(1012,'es','polbase_table_frwobjetive','Fichero Firmware Objetivo');
insert into message(id,locale,code,description) values(1013,'es','polbase_table_frwobjetivever','Versión Firmware Objetivo');
insert into message(id,locale,code,description) values(1014,'es','polbase_table_ttcreation','Creación');
insert into message(id,locale,code,description) values(1015,'es','polbase_table_ttmodification','Modificación');
insert into message(id,locale,code,description) values(1016,'es','polbase_table_numpermitidos','#Perm');
insert into message(id,locale,code,description) values(1017,'es','polbase_table_numpermpercentage','%Perm');
insert into message(id,locale,code,description) values(1018,'es','polbase_table_numobjetivo','#Base');
insert into message(id,locale,code,description) values(1019,'es','polbase_table_numobjpercentage','%Base');
insert into message(id,locale,code,description) values(1020,'es','polbase_table_numtotal','#Tot');
insert into message(id,locale,code,description) values(1021,'es','polbase_table_weekdays','Días de la semana');
insert into message(id,locale,code,description) values(1022,'es','polbase_task_running','Política en ejecución');
insert into message(id,locale,code,description) values(1023,'es','server_title','Menú de Arranque/Parada');
insert into message(id,locale,code,description) values(1024,'es','server_title_start','Arrancar servidor');
insert into message(id,locale,code,description) values(1025,'es','server_title_stop','Parar servidor');
insert into message(id,locale,code,description) values(1026,'es','suggestion_title','Listado de Sugerencias');
insert into message(id,locale,code,description) values(1027,'es','suggestion_title_search','Búsqueda de Sugerencias');
insert into message(id,locale,code,description) values(1028,'es','suggestion_title_detail','Detalle de Sugerencia');
insert into message(id,locale,code,description) values(1029,'es','suggestion_filter_client','Cliente');
insert into message(id,locale,code,description) values(1030,'es','suggestion_filter_manufacturer','Fabricante');
insert into message(id,locale,code,description) values(1031,'es','suggestion_filter_model','Modelo');
insert into message(id,locale,code,description) values(1032,'es','suggestion_filter_os','Sistema operativo');
insert into message(id,locale,code,description) values(1033,'es','suggestion_filter_osversion','Versión del SO');
insert into message(id,locale,code,description) values(1034,'es','suggestion_filter_appversion','Versión de la APP');
insert into message(id,locale,code,description) values(1035,'es','suggestion_filter_message','Mensaje');
insert into message(id,locale,code,description) values(1036,'es','suggestion_client','Cliente:');
insert into message(id,locale,code,description) values(1037,'es','suggestion_manufacturer','Fabricante:');
insert into message(id,locale,code,description) values(1038,'es','suggestion_model','Modelo:');
insert into message(id,locale,code,description) values(1039,'es','suggestion_os','Sistema operativo:');
insert into message(id,locale,code,description) values(1040,'es','suggestion_osversion','Versión del SO:');
insert into message(id,locale,code,description) values(1041,'es','suggestion_appversion','Versión de la APP:');
insert into message(id,locale,code,description) values(1042,'es','suggestion_message','Mensaje:');
insert into message(id,locale,code,description) values(1043,'es','suggestion_ttCreation','Creación:');
insert into message(id,locale,code,description) values(1044,'es','suggestion_ttModification','Modificación:');
insert into message(id,locale,code,description) values(1045,'es','suggestion_table_title','Listado de Sugerencias');
insert into message(id,locale,code,description) values(1046,'es','suggestion_table_client','Cliente');
insert into message(id,locale,code,description) values(1047,'es','suggestion_table_manufacturer','Fabricante');
insert into message(id,locale,code,description) values(1048,'es','suggestion_table_model','Modelo');
insert into message(id,locale,code,description) values(1049,'es','suggestion_table_os','Sistema operativo');
insert into message(id,locale,code,description) values(1050,'es','suggestion_table_osversion','Versión del SO');
insert into message(id,locale,code,description) values(1051,'es','suggestion_table_appversion','Versión de la APP');
insert into message(id,locale,code,description) values(1052,'es','suggestion_table_message','Mensaje');
insert into message(id,locale,code,description) values(1053,'es','suggestion_table_ttCreation','Creación');
insert into message(id,locale,code,description) values(1054,'es','suggestion_table_ttModification','Modificación');
insert into message(id,locale,code,description) values(1055,'es','suggestion_so_android','Android');
insert into message(id,locale,code,description) values(1056,'es','suggestion_so_ios','IOS');
insert into message(id,locale,code,description) values(1057,'es','suggestion_so_windows','Windows');
insert into message(id,locale,code,description) values(1058,'es','suggestion_so_linux','Linux');
insert into message(id,locale,code,description) values(1059,'es','suggestion_so_mac','Mac OS');
insert into message(id,locale,code,description) values(1060,'es','event_title','Eventos del Sistema');
insert into message(id,locale,code,description) values(1061,'es','event_title_search','Búsqueda de eventos');
insert into message(id,locale,code,description) values(1062,'es','event_title_detail','Detalle de evento');
insert into message(id,locale,code,description) values(1063,'es','event_filter_name','Nombre');
insert into message(id,locale,code,description) values(1064,'es','event_filter_value','Valor');
insert into message(id,locale,code,description) values(1065,'es','event_filter_date_ini','Fecha inicial');
insert into message(id,locale,code,description) values(1066,'es','event_filter_date_end','Fecha final');
insert into message(id,locale,code,description) values(1067,'es','event_name','Nombre');
insert into message(id,locale,code,description) values(1068,'es','event_value','Valor');
insert into message(id,locale,code,description) values(1069,'es','event_type_start','Arranque de Schaman');
insert into message(id,locale,code,description) values(1070,'es','event_type_stop','Parada de Schaman');
insert into message(id,locale,code,description) values(1071,'es','event_type_deploy','Arranque del servidor de aplicaciones');
insert into message(id,locale,code,description) values(1072,'es','event_type_deployver','Despliegue de Versión');
insert into message(id,locale,code,description) values(1073,'es','event_ttcreation','Creación');
insert into message(id,locale,code,description) values(1074,'es','event_ttmodification','Modificación');
insert into message(id,locale,code,description) values(1075,'es','event_table_title','Listado de eventos');
insert into message(id,locale,code,description) values(1076,'es','event_table_name','Nombre');
insert into message(id,locale,code,description) values(1077,'es','event_table_value','Valor');
insert into message(id,locale,code,description) values(1078,'es','event_table_ttcreation','Creación');
insert into message(id,locale,code,description) values(1079,'es','event_table_ttmodification','Modificación');
insert into message(id,locale,code,description) values(1080,'es','configuration_title','Menú de Configuración');
insert into message(id,locale,code,description) values(1081,'es','configuration_subtitle','Schaman se está ejecutando. Pare Schaman para poder editar la configuración.');
insert into message(id,locale,code,description) values(1082,'es','configuration_licence','Licencia');
insert into message(id,locale,code,description) values(1083,'es','configuration_licence_license','Código de Licencia:');
insert into message(id,locale,code,description) values(1084,'es','configuration_licence_version','- Versión:');
insert into message(id,locale,code,description) values(1085,'es','configuration_licence_fexpiracion','- Fecha de Expiración:');
insert into message(id,locale,code,description) values(1086,'es','configuration_licence_limCPEs','- Límite de CPEs:');
insert into message(id,locale,code,description) values(1087,'es','configuration_licence_dirIP','- Dirección IP Asociada:');
insert into message(id,locale,code,description) values(1088,'es','configuration_schaman','Schaman');
insert into message(id,locale,code,description) values(1089,'es','configuration_schaman_autoStart','Autoarranque:');
insert into message(id,locale,code,description) values(1090,'es','configuration_schaman_icmpType','Pruebas ICMP:');
insert into message(id,locale,code,description) values(1091,'es','configuration_schaman_language','Idioma:');
insert into message(id,locale,code,description) values(1092,'es','configuration_schaman_ip','Dirección IP del sistema:');
insert into message(id,locale,code,description) values(1093,'es','configuration_location','Localización');
insert into message(id,locale,code,description) values(1094,'es','configuration_location_dhcpAgentActive','Activo:');
insert into message(id,locale,code,description) values(1095,'es','configuration_location_radiusServerPort','Puerto servidor Radius');
insert into message(id,locale,code,description) values(1096,'es','configuration_location_radiusServerSharedSecret','Radius Shared Secret:');
insert into message(id,locale,code,description) values(1097,'es','configuration_location_radiusFilterActive','Filtro Antiavalanchas Radius:');
insert into message(id,locale,code,description) values(1098,'es','configuration_location_radiusFilterBufferSize','Tamaño del Buffer del Filtro:');
insert into message(id,locale,code,description) values(1099,'es','configuration_location_radiusFilterLogActive','Mostrar MACs Duplicadas en el Log:');
insert into message(id,locale,code,description) values(1100,'es','configuration_identification','Identificación');
insert into message(id,locale,code,description) values(1101,'es','configuration_identification_identifierActive','Activo:');
insert into message(id,locale,code,description) values(1102,'es','configuration_identification_identifierBlock','Tamaño del Bloque de Identificación:');
insert into message(id,locale,code,description) values(1103,'es','configuration_identification_identifierSimConnections','Conexiones Simultáneas del Proceso:');
insert into message(id,locale,code,description) values(1104,'es','configuration_identification_identifierInfPeriod','Caducidad de la Identificación:');
insert into message(id,locale,code,description) values(1105,'es','configuration_identification_identifierCrtPeriod','Priorización de Identificación de Equipos Nuevos:');
insert into message(id,locale,code,description) values(1106,'es','configuration_identification_identifierErrPeriod','Espera para Reidentificación tras un Error:');
insert into message(id,locale,code,description) values(1107,'es','configuration_troubleshoot','Troubleshoot');
insert into message(id,locale,code,description) values(1108,'es','configuration_troubleshoot_troubleshootActive','Activo:');
insert into message(id,locale,code,description) values(1109,'es','configuration_troubleshoot_multimediaURL','URL de Publicación de los Ficheros Multimedia:');
insert into message(id,locale,code,description) values(1110,'es','configuration_troubleshoot_progressTMax','Timeout máximo barra de progreso (segundos):');
insert into message(id,locale,code,description) values(1111,'es','configuration_troubleshoot_progressTExp','Timeout esperado barra de progreso (segundos):');
insert into message(id,locale,code,description) values(1112,'es','configuration_troubleshoot_progressExp','Porcentaje esperado barra de progreso:');
insert into message(id,locale,code,description) values(1113,'es','configuration_troubleshoot_progressTEnd','Timeout finalización barra de progreso (segundos):');
insert into message(id,locale,code,description) values(1114,'es','configuration_acs','ACS');
insert into message(id,locale,code,description) values(1115,'es','configuration_acs_acsProvActive','Provisión:');
insert into message(id,locale,code,description) values(1116,'es','configuration_acs_acsURL','URL:');
insert into message(id,locale,code,description) values(1117,'es','configuration_acs_acsPassword','Password:');
insert into message(id,locale,code,description) values(1118,'es','configuration_acs_acsInformInterval','Intervalo de Inform (segundos):');
insert into message(id,locale,code,description) values(1119,'es','configuration_acs_acsProvCode','Código de Provisión:');
insert into message(id,locale,code,description) values(1120,'es','configuration_acs_xmppServerIp','IP Servidor XMPP:');
insert into message(id,locale,code,description) values(1121,'es','configuration_acs_xmppServerPort','Puerto Servidor XMPP:');
insert into message(id,locale,code,description) values(1122,'es','configuration_acs_xmppServerDomain','Dominio Servidor XMPP:');
insert into message(id,locale,code,description) values(1123,'es','configuration_acs_xmppUser','Usuario Servidor XMPP:');
insert into message(id,locale,code,description) values(1124,'es','configuration_acs_xmppPassword','Password Servidor XMPP:');
insert into message(id,locale,code,description) values(1125,'es','configuration_acs_xmppResource','Recurso Servidor XMPP:');
insert into message(id,locale,code,description) values(1126,'es','configuration_acs_xmppAdminUser','Usuario Administración Servidor XMPP:');
insert into message(id,locale,code,description) values(1127,'es','configuration_acs_xmppAdminPassword','Password Administración Servidor XMPP:');
insert into message(id,locale,code,description) values(1128,'es','configuration_oneclick','OneClick');
insert into message(id,locale,code,description) values(1129,'es','configuration_oneclick_ockActive','Activo:');
insert into message(id,locale,code,description) values(1130,'es','configuration_oneclick_ockSessionTimeout','Timeout de Sesión:');
insert into message(id,locale,code,description) values(1131,'es','configuration_oneclick_ockPassword','Password de Acceso:');
insert into message(id,locale,code,description) values(1132,'es','configuration_oneclick_ockBotoneraType','Tipo de Botonera:');
insert into message(id,locale,code,description) values(1133,'es','configuration_oneclick_ockBotoneraProxyURL','URL al WS de Producción de Botonera:');
insert into message(id,locale,code,description) values(1134,'es','configuration_oneclick_ockBotoneraIconsURL','URL a los Iconos de la Botonera:');
insert into message(id,locale,code,description) values(1135,'es','configuration_oneclick_sincroTimestamp','Última sincronización:');
insert into message(id,locale,code,description) values(1136,'es','configuration_oneclick_ockLinkedNotifTTL','Tiempo de Vida Notificaciones Diagnóstico Enlazado (segundos):');
insert into message(id,locale,code,description) values(1137,'es','configuration_oneclick_ockBotoneraSyncTt','Última sincronización:');
insert into message(id,locale,code,description) values(1138,'es','configuration_oneclick_ockAndroidApiKey','API Key Android:');
insert into message(id,locale,code,description) values(1139,'es','configuration_oneclick_ockIOSApiKey','API Key iOS:');
insert into message(id,locale,code,description) values(1140,'es','configuration_oneclick_ockLinkedTimeout','Timeout Diagnóstico Enlazado (segundos):');
insert into message(id,locale,code,description) values(1141,'es','configuration_cleaning_database','Limpieza BBDD');
insert into message(id,locale,code,description) values(1142,'es','configuration_cleaning_database_databaseCleanerActive','Activo:');
insert into message(id,locale,code,description) values(1143,'es','configuration_cleaning_database_databaseCleanerThreshold','Margen de Borrado Dispositivos Inactivos:');
insert into message(id,locale,code,description) values(1144,'es','configuration_cleaning_database_databaseCleanerCliThreshold','Margen de Borrado Clientes Inactivos:');
insert into message(id,locale,code,description) values(1145,'es','configuration_cleaning_database_databaseCleanerTime','Hora de Borrado Dispositivos Inactivos:');
insert into message(id,locale,code,description) values(1146,'es','configuration_cleaning_database_databaseCleanerCliTime','Hora de Borrado Clientes Inactivos:');
insert into message(id,locale,code,description) values(1147,'es','configuration_cleaning_database_databaseCleanerWrnTime','Hora de Borrado Mensajes Antiguos:');
insert into message(id,locale,code,description) values(1148,'es','configuration_log','Log');
insert into message(id,locale,code,description) values(1149,'es','configuration_log_logFile','Fichero de Log:');
insert into message(id,locale,code,description) values(1150,'es','configuration_firmware','Firmware');
insert into message(id,locale,code,description) values(1151,'es','configuration_firmware_firmwareActive','Activo:');
insert into message(id,locale,code,description) values(1152,'es','configuration_firmware_firmwareLibraryPath','Path al Repositorio de Firmware:');
insert into message(id,locale,code,description) values(1153,'es','configuration_firmware_firmwareBlock','Tamaño del Bloque de Actualización:');
insert into message(id,locale,code,description) values(1154,'es','configuration_firmware_firmwareSimConnections','Conexiones Simultáneas del Proceso:');
insert into message(id,locale,code,description) values(1155,'es','configuration_firmware_firmwareWait2IdfPeriod','Espera para Reidentificación tras una Migración:');
insert into message(id,locale,code,description) values(1156,'es','configuration_firmware_firmwarePriorAlta','Multiplicador prioridad Alta:');
insert into message(id,locale,code,description) values(1157,'es','configuration_firmware_firmwarePriorMedia','Multiplicador prioridad Media:');
insert into message(id,locale,code,description) values(1158,'es','configuration_firmware_firmwarePriorBaja','Multiplicador prioridad Baja:');
insert into message(id,locale,code,description) values(1159,'es','configuration_reports','Informes');
insert into message(id,locale,code,description) values(1160,'es','configuration_reports_elasticURL','URL de ElasticSearch');
insert into message(id,locale,code,description) values(1161,'es','configuration_reports_kibanaSthbndURL','URL Informe Integraciones SouthBound:');
insert into message(id,locale,code,description) values(1162,'es','configuration_reports_kibanaActivityURL','URL Informe Actividad Callcenter:');
insert into message(id,locale,code,description) values(1163,'es','configuration_reports_kibanaAgenteURL','URL Informe Desempeño por Agente:');
insert into message(id,locale,code,description) values(1164,'es','configuration_reports_kibanaCausalesURL','URL Informe Detalle Causales:');
insert into message(id,locale,code,description) values(1165,'es','configuration_proactive_kibanaProacDashboardURL','URL Dashboard Proactivo:');
insert into message(id,locale,code,description) values(1166,'es','configuration_value_activo','Activo');
insert into message(id,locale,code,description) values(1167,'es','configuration_value_inactivo','Inactivo');
insert into message(id,locale,code,description) values(1168,'es','configuration_list_wifi_wifi','Con WiFi');
insert into message(id,locale,code,description) values(1169,'es','configuration_list_wifi_always','Siempre');
insert into message(id,locale,code,description) values(1170,'es','configuration_list_int_ext_true','Interno');
insert into message(id,locale,code,description) values(1171,'es','configuration_list_int_ext_false','Externo');
insert into message(id,locale,code,description) values(1172,'es','configuration_list_icmp_java','Java (Recomendado)');
insert into message(id,locale,code,description) values(1173,'es','configuration_list_icmp_linux','Linux (Afecta al Rendimiento)');
insert into message(id,locale,code,description) values(1174,'es','configuration_list_icmp_solaris','Solaris (Afecta al Rendimiento)');
insert into message(id,locale,code,description) values(1175,'es','configuration_list_icmp_windows','Windows (Afecta al Rendimiento)');
insert into message(id,locale,code,description) values(1176,'es','configuration_list_icmp_macosx','Mac OS X (Afecta al Rendimiento)');
insert into message(id,locale,code,description) values(1177,'es','configuration_list_language_es_ES','Español');
insert into message(id,locale,code,description) values(1178,'es','configuration_list_language_en_EN','English');
insert into message(id,locale,code,description) values(1179,'es','configuration_proactive','Proactivo');
insert into message(id,locale,code,description) values(1180,'es','configuration_proactive_kibanaMassiveURL','URL Informe Masivo:');
insert into message(id,locale,code,description) values(1181,'es','configuration_proactive_kibanaSelfURL','URL Informe Self:');
insert into message(id,locale,code,description) values(1182,'es','configuration_proactive_kibanaDslURL','URL Informe DSL:');
insert into message(id,locale,code,description) values(1183,'es','debug_title','Desarrollo');
insert into message(id,locale,code,description) values(1184,'es','debug_location_add','Forzar localización de dispositivo');
insert into message(id,locale,code,description) values(1185,'es','debug_location_delete','Forzar Borrado de Dispositivo (MAC):');
insert into message(id,locale,code,description) values(1186,'es','debug_location_massive','Localización masiva de dispositivos (Sólo Desarrollo)');
insert into message(id,locale,code,description) values(1187,'es','debug_identification_add','Forzar identificación de dispositivo');
insert into message(id,locale,code,description) values(1188,'es','debug_identification_massive','Identificación masiva de dispositivos (Inicio Manual)');
insert into message(id,locale,code,description) values(1189,'es','debug_stat_increase','Incrementar estadística');
insert into message(id,locale,code,description) values(1190,'es','debug_stat_init','Inicializar estadísticas persistentes');
insert into message(id,locale,code,description) values(1191,'es','debug_mac','MAC');
insert into message(id,locale,code,description) values(1192,'es','debug_ip','IP');
insert into message(id,locale,code,description) values(1193,'es','debug_provisiongroup','Grupo de provisión');
insert into message(id,locale,code,description) values(1194,'es','debug_giaddress','GiAddress');
insert into message(id,locale,code,description) values(1195,'es','debug_service','Clase de servicio');
insert into message(id,locale,code,description) values(1196,'es','debug_dhcp','IP DHCP');
insert into message(id,locale,code,description) values(1197,'es','debug_nthreads','N° de hilos');
insert into message(id,locale,code,description) values(1198,'es','debug_nrequest','N° peticiones por hilo');
insert into message(id,locale,code,description) values(1199,'es','debug_manufacturer','Fabricante');
insert into message(id,locale,code,description) values(1200,'es','debug_model','Modelo');
insert into message(id,locale,code,description) values(1201,'es','debug_firmware','Firmware');
insert into message(id,locale,code,description) values(1202,'es','debug_role','Rol');
insert into message(id,locale,code,description) values(1203,'es','debug_type','Conexión');
insert into message(id,locale,code,description) values(1204,'es','debug_usercomr','Community Lectura/User');
insert into message(id,locale,code,description) values(1205,'es','debug_pwdcomw','Community Escritura/Password');
insert into message(id,locale,code,description) values(1206,'es','debug_template','Plantilla');
insert into message(id,locale,code,description) values(1207,'es','debug_statCode','Código de Estadística');
insert into message(id,locale,code,description) values(1208,'es','debug_statValue','Valor');
insert into message(id,locale,code,description) values(1209,'es','menu_home','INICO');
insert into message(id,locale,code,description) values(1210,'es','menu_profile','PERFIL');
insert into message(id,locale,code,description) values(1211,'es','menu_profilelower','Perfil');
insert into message(id,locale,code,description) values(1212,'es','menu_hdm','HDM');
insert into message(id,locale,code,description) values(1213,'es','menu_inventory','Inventario');
insert into message(id,locale,code,description) values(1214,'es','menu_access','Equipos de Acceso');
insert into message(id,locale,code,description) values(1215,'es','menu_manufacturers','Fabricantes');
insert into message(id,locale,code,description) values(1216,'es','menu_models','Modelos');
insert into message(id,locale,code,description) values(1217,'es','menu_cpes','CPEs');
insert into message(id,locale,code,description) values(1218,'es','menu_reinventoring','Reinventariado de CPEs');
insert into message(id,locale,code,description) values(1219,'es','menu_firmware','Firmware');
insert into message(id,locale,code,description) values(1220,'es','menu_firmwares','Situación Actual');
insert into message(id,locale,code,description) values(1221,'es','menu_polunitary','Políticas Unitarias');
insert into message(id,locale,code,description) values(1222,'es','menu_polmassive','Políticas Masivas');
insert into message(id,locale,code,description) values(1223,'es','menu_polbase','Políticas base');
insert into message(id,locale,code,description) values(1224,'es','menu_firmwarerepository','Repositorio de Firmware');
insert into message(id,locale,code,description) values(1225,'es','menu_polinex','Listados de MACs');
insert into message(id,locale,code,description) values(1226,'es','menu_diagnosis','DIAGNÓSTICO');
insert into message(id,locale,code,description) values(1227,'es','menu_troubleshoot','Diagnosticar');
insert into message(id,locale,code,description) values(1228,'es','menu_config','CONFIGURACIÓN');
insert into message(id,locale,code,description) values(1229,'es','menu_templates','Plantillas');
insert into message(id,locale,code,description) values(1230,'es','menu_scenes','Escenarios');
insert into message(id,locale,code,description) values(1231,'es','menu_lookupfile','Ficheros lookup');
insert into message(id,locale,code,description) values(1232,'es','menu_tutorials','Tutoriales');
insert into message(id,locale,code,description) values(1233,'es','menu_multimedia','Multimedia');
insert into message(id,locale,code,description) values(1234,'es','menu_administration','ADMINISTRACIÓN');
insert into message(id,locale,code,description) values(1235,'es','menu_gen','General');
insert into message(id,locale,code,description) values(1236,'es','menu_server','Menú de Arranque/Parada');
insert into message(id,locale,code,description) values(1237,'es','menu_configurationmenu','Menú de Configuración');
insert into message(id,locale,code,description) values(1238,'es','menu_users','Gestión Usuarios');
insert into message(id,locale,code,description) values(1239,'es','menu_roles','Gestión Roles');
insert into message(id,locale,code,description) values(1240,'es','menu_log_viewer','Visor de Logs');
insert into message(id,locale,code,description) values(1241,'es','menu_oneclick','Oneclick');
insert into message(id,locale,code,description) values(1242,'es','menu_obs_rules','Reglas de obsolescencia');
insert into message(id,locale,code,description) values(1243,'es','menu_buttonbar','Botonera');
insert into message(id,locale,code,description) values(1244,'es','menu_warningmsg','Mensajes de aviso');
insert into message(id,locale,code,description) values(1245,'es','menu_platform','Versión de la plataforma');
insert into message(id,locale,code,description) values(1246,'es','menu_reports','INFORMES');
insert into message(id,locale,code,description) values(1247,'es','menu_health','Salud');
insert into message(id,locale,code,description) values(1248,'es','menu_dbsthbnd','Integraciones SouthBound');
insert into message(id,locale,code,description) values(1249,'es','menu_dbsthbnd2','Integraciones SouthBound (eCharts)');
insert into message(id,locale,code,description) values(1250,'es','menu_diagcor','Diagnóstico Correctivo');
insert into message(id,locale,code,description) values(1251,'es','menu_dbactivity','Actividad Callcenter');
insert into message(id,locale,code,description) values(1252,'es','menu_dbactivity2','Actividad Callcenter (eCharts)');
insert into message(id,locale,code,description) values(1253,'es','menu_dbcausales','Detalle causales');
insert into message(id,locale,code,description) values(1254,'es','menu_dbcausales2','Detalle causales (eCharts)');
insert into message(id,locale,code,description) values(1255,'es','menu_dbagente','Desempeño por agente');
insert into message(id,locale,code,description) values(1256,'es','menu_dbagente2','Desempeño por agente (eCharts)');
insert into message(id,locale,code,description) values(1257,'es','menu_inventoryevo','Evolución Inventario');
insert into message(id,locale,code,description) values(1258,'es','menu_clients','Clientes');
insert into message(id,locale,code,description) values(1259,'es','menu_services','Servicios');
insert into message(id,locale,code,description) values(1260,'es','menu_devices','Dispositivos Oneclick');
insert into message(id,locale,code,description) values(1261,'es','menu_suggestions','Sugerencias');
insert into message(id,locale,code,description) values(1262,'es','menu_events','Eventos');
insert into message(id,locale,code,description) values(1263,'es','menu_certif','Certificar');
insert into message(id,locale,code,description) values(1264,'es','menu_proactive','PROACTIVO');
insert into message(id,locale,code,description) values(1265,'es','menu_polproactive','Políticas proactivas');
insert into message(id,locale,code,description) values(1266,'es','menu_prodashboard','Dashboard');
insert into message(id,locale,code,description) values(1267,'es','menu_troubleshoot_linkedDiag','Diagnostico enlazado');
insert into message(id,locale,code,description) values(1268,'es','menu_server_stopstart','Arrancar/parar Schaman');
insert into message(id,locale,code,description) values(1269,'es','menu_configurationmenu_edit','Editar configuración');
insert into message(id,locale,code,description) values(1270,'es','menu_cpes_cwmp','CWMP');
insert into message(id,locale,code,description) values(1271,'es','wmsg_title','Gestión de mensajes de aviso');
insert into message(id,locale,code,description) values(1272,'es','wmsg_title_search','Búsqueda de mensajes de aviso');
insert into message(id,locale,code,description) values(1273,'es','wmsg_title_detail','Detalle de mensaje de aviso');
insert into message(id,locale,code,description) values(1274,'es','wmsg_title_edit','Edición de mensaje de aviso');
insert into message(id,locale,code,description) values(1275,'es','wmsg_title_add','Alta de mensaje de aviso');
insert into message(id,locale,code,description) values(1276,'es','wmsg_table_title','Listado de mensajes de aviso');
insert into message(id,locale,code,description) values(1277,'es','wmsg_table_start','Comienzo');
insert into message(id,locale,code,description) values(1278,'es','wmsg_table_finish','Finalización');
insert into message(id,locale,code,description) values(1279,'es','wmsg_table_msg','Mensaje');
insert into message(id,locale,code,description) values(1280,'es','wmsg_table_type','Tipo');
insert into message(id,locale,code,description) values(1281,'es','wmsg_start','Comienzo');
insert into message(id,locale,code,description) values(1282,'es','wmsg_finish','Finalización');
insert into message(id,locale,code,description) values(1283,'es','wmsg_msg','Mensaje');
insert into message(id,locale,code,description) values(1284,'es','wmsg_type','Tipo');
insert into message(id,locale,code,description) values(1285,'es','wmsg_filter_msg','Mensaje');
insert into message(id,locale,code,description) values(1286,'es','wmsg_filter_type','Tipo');
insert into message(id,locale,code,description) values(1287,'es','platform_title','Versión de la plataforma');
insert into message(id,locale,code,description) values(1288,'es','platform_botonera','Botonera');
insert into message(id,locale,code,description) values(1289,'es','platform_tutorial','Tutoriales');
insert into message(id,locale,code,description) values(1290,'es','platform_apn','APNs');
insert into message(id,locale,code,description) values(1291,'es','platform_template','Plantillas');
insert into message(id,locale,code,description) values(1292,'es','platform_ooklaservers','Servidores Ookla');
insert into message(id,locale,code,description) values(1293,'es','platform_others','Otros');
insert into message(id,locale,code,description) values(1294,'es','platform_ockBotoneraURL','URL a la botonera de OneClick');
insert into message(id,locale,code,description) values(1295,'es','platform_ockBotoneraDown','Cuándo se baja la botonera (wifi/always)');
insert into message(id,locale,code,description) values(1296,'es','platform_ockTutorialesVer','Versión de tutoriales');
insert into message(id,locale,code,description) values(1297,'es','platform_ockTutorialesURL','URL de los tutoriales');
insert into message(id,locale,code,description) values(1298,'es','platform_ockTutorialesDown','Cuándo se baja los tutoriales (wifi/always)');
insert into message(id,locale,code,description) values(1299,'es','platform_ockXmlApnsVer','Versión del fichero de APNs');
insert into message(id,locale,code,description) values(1300,'es','platform_ockXmlApnsURL','URL del fichero de APNs');
insert into message(id,locale,code,description) values(1301,'es','platform_ockXmlApnsDown','Cuándo se baja el fichero de APNSs (wifi/always)');
insert into message(id,locale,code,description) values(1302,'es','platform_ockXmlMasterVer','Versión de la plantilla master');
insert into message(id,locale,code,description) values(1303,'es','platform_ockXmlMasterURL','URL de la plantilla master');
insert into message(id,locale,code,description) values(1304,'es','platform_ockXmlMasterDown','Cuándo se baja el fichero master (wifi/always)');
insert into message(id,locale,code,description) values(1305,'es','platform_ockXmlDeviceVer','Versión de la plantilla del dispositivo');
insert into message(id,locale,code,description) values(1306,'es','platform_ockXmlDeviceURL','URL de la plantilla del dispositivo');
insert into message(id,locale,code,description) values(1307,'es','platform_ockXmlDeviceDown','Cuándo se baja el fichero plantilla de dispositivo (wifi/always)');
insert into message(id,locale,code,description) values(1308,'es','platform_ockOoklaServersVer','Versión de servidores Ookla');
insert into message(id,locale,code,description) values(1309,'es','platform_ockOoklaServersURL','URL de servidores Ookla');
insert into message(id,locale,code,description) values(1310,'es','platform_ockOoklaServersDown','Cuándo se baja el fichero de servidores Ookla (wifi/always)');
insert into message(id,locale,code,description) values(1311,'es','platform_ockSamsungKey','Clave de samsung');
insert into message(id,locale,code,description) values(1312,'es','platform_ockLoginTimeout','Timeout de sesión');
insert into message(id,locale,code,description) values(1313,'es','platform_tutorials','Tutoriales');
insert into message(id,locale,code,description) values(1314,'es','platform_apns','APNS');
insert into message(id,locale,code,description) values(1315,'es','platform_master','Plantilla master');
insert into message(id,locale,code,description) values(1316,'es','platform_device','Plantilla de dispositivo');
insert into message(id,locale,code,description) values(1317,'es','platform_ookla','Servidores Ookla');
insert into message(id,locale,code,description) values(1318,'es','lookupfile_title','Ficheros lookup');
insert into message(id,locale,code,description) values(1319,'es','lookupfile_title_search','Búsqueda de ficheros lookup');
insert into message(id,locale,code,description) values(1320,'es','lookupfile_title_detail','Detalle de fichero lookup');
insert into message(id,locale,code,description) values(1321,'es','lookupfile_title_edit','Edición de fichero lookup');
insert into message(id,locale,code,description) values(1322,'es','lookupfile_title_add','Alta de fichero lookup');
insert into message(id,locale,code,description) values(1323,'es','lookupfile_title_upload','Carga de ficheros lookup');
insert into message(id,locale,code,description) values(1324,'es','lookupfile_filter_key','Nombre');
insert into message(id,locale,code,description) values(1325,'es','lookupfile_key','Nombre:');
insert into message(id,locale,code,description) values(1326,'es','lookupfile_content','Contenido:');
insert into message(id,locale,code,description) values(1327,'es','lookupfile_ttCreation','Creación:');
insert into message(id,locale,code,description) values(1328,'es','lookupfile_ttModification','Modificación:');
insert into message(id,locale,code,description) values(1329,'es','lookupfile_table_title','Listado de ficheros lookup');
insert into message(id,locale,code,description) values(1330,'es','lookupfile_table_key','Nombre');
insert into message(id,locale,code,description) values(1331,'es','lookupfile_table_content','Contenido');
insert into message(id,locale,code,description) values(1332,'es','lookupfile_table_ttcreation','Creación');
insert into message(id,locale,code,description) values(1333,'es','lookupfile_table_ttmodification','Modificación');
insert into message(id,locale,code,description) values(1334,'es','dashboard_filter_type_quick','Rápida');
insert into message(id,locale,code,description) values(1335,'es','dashboard_filter_type_relative','Relativa');
insert into message(id,locale,code,description) values(1336,'es','dashboard_filter_type_absolute','Absoluta');
insert into message(id,locale,code,description) values(1337,'es','dashboard_filter_dateIni','Fecha inicial');
insert into message(id,locale,code,description) values(1338,'es','dashboard_filter_dateEnd','Fecha final');
insert into message(id,locale,code,description) values(1339,'es','polproactive_title','Políticas Proactivas');
insert into message(id,locale,code,description) values(1340,'es','polproactive_title_search','Búsqueda de políticas proactivas');
insert into message(id,locale,code,description) values(1341,'es','polproactive_title_detail','Detalle de políticas proactivas');
insert into message(id,locale,code,description) values(1342,'es','polproactive_title_edit','Edición de políticas proactivas');
insert into message(id,locale,code,description) values(1343,'es','polproactive_title_add','Alta de políticas proactivas');
insert into message(id,locale,code,description) values(1344,'es','polproactive_title_chart','Informe de políticas proactivas');
insert into message(id,locale,code,description) values(1345,'es','polproactive_filter_name','Nombre');
insert into message(id,locale,code,description) values(1346,'es','polproactive_name','Nombre:');
insert into message(id,locale,code,description) values(1347,'es','polproactive_ttcreation','Creación:');
insert into message(id,locale,code,description) values(1348,'es','polproactive_ttmodification','Modificación:');
insert into message(id,locale,code,description) values(1349,'es','polproactive_table_title','Listado de políticas proactivas');
insert into message(id,locale,code,description) values(1350,'es','polproactive_table_name','Nombre');
insert into message(id,locale,code,description) values(1351,'es','polproactive_table_trigger','Disparador');
insert into message(id,locale,code,description) values(1352,'es','polproactive_table_executor','Ejecutor');
insert into message(id,locale,code,description) values(1353,'es','polproactive_table_schedule','Horario');
insert into message(id,locale,code,description) values(1354,'es','polproactive_table_resources','% Recursos');
insert into message(id,locale,code,description) values(1355,'es','polproactive_table_ttcreation','Creación');
insert into message(id,locale,code,description) values(1356,'es','polproactive_table_ttmodification','Modificación');
insert into message(id,locale,code,description) values(1357,'es','polproactive_dialog_add','Añadir nueva política proactiva');
insert into message(id,locale,code,description) values(1358,'es','polproactive_trigger','Disparador');
insert into message(id,locale,code,description) values(1359,'es','polproactive_executor','Ejecutor');
insert into message(id,locale,code,description) values(1360,'es','polproactive_notifications','Notificaciones');
insert into message(id,locale,code,description) values(1361,'es','polproactive_programmer','Programador');
insert into message(id,locale,code,description) values(1362,'es','polproactive_confirm','Confirmación');
insert into message(id,locale,code,description) values(1363,'es','polproactive_triggertype','Tipo de disparador');
insert into message(id,locale,code,description) values(1364,'es','polproactive_serviceid','Id de servicio');
insert into message(id,locale,code,description) values(1365,'es','polproactive_captoralarm','Alarma Captor');
insert into message(id,locale,code,description) values(1366,'es','polproactive_webservice','Web Service');
insert into message(id,locale,code,description) values(1367,'es','polproactive_csvfile','Fichero CSV');
insert into message(id,locale,code,description) values(1368,'es','polproactive_ticketingstatus','Estado del ticket');
insert into message(id,locale,code,description) values(1369,'es','polproactive_alarmId','Id de Alarma');
insert into message(id,locale,code,description) values(1370,'es','polproactive_inputType','Tipo');
insert into message(id,locale,code,description) values(1371,'es','polproactive_inputValue','Valor');
insert into message(id,locale,code,description) values(1372,'es','polproactive_ticketorigin','Origen del ticket');
insert into message(id,locale,code,description) values(1373,'es','polproactive_ticketorigin_callcenter','Call Center');
insert into message(id,locale,code,description) values(1374,'es','polproactive_ticketorigin_oneclick','Oneclick');
insert into message(id,locale,code,description) values(1375,'es','polproactive_ticketorigin_all','Todos');
insert into message(id,locale,code,description) values(1376,'es','polproactive_ticketorigin_field','Certificación');
insert into message(id,locale,code,description) values(1377,'es','polproactive_currentstatus','Estado actual');
insert into message(id,locale,code,description) values(1378,'es','polproactive_currentstatus_resolved','Resuelto');
insert into message(id,locale,code,description) values(1379,'es','polproactive_previousstatus','Estado previo');
insert into message(id,locale,code,description) values(1380,'es','polproactive_status_tocallcenter','Escalado a Call Center');
insert into message(id,locale,code,description) values(1381,'es','polproactive_status_noresolved','No resuelto (Encuesta)');
insert into message(id,locale,code,description) values(1382,'es','polproactive_status_tomassive','Asociado a masiva');
insert into message(id,locale,code,description) values(1383,'es','polproactive_status_close','Cierre Masiva');
insert into message(id,locale,code,description) values(1384,'es','polproactive_status_tocc','Escalado a Call Center');
insert into message(id,locale,code,description) values(1385,'es','polproactive_status_ton2','Escalado N2');
insert into message(id,locale,code,description) values(1386,'es','polproactive_status_send','Envío terreno');
insert into message(id,locale,code,description) values(1387,'es','polproactive_status_resolved','Resuelto');
insert into message(id,locale,code,description) values(1388,'es','polproactive_delay','Retardo (horas)');
insert into message(id,locale,code,description) values(1389,'es','polproactive_policyId','Id política');
insert into message(id,locale,code,description) values(1390,'es','polproactive_executortype','Tipo de ejecutor');
insert into message(id,locale,code,description) values(1391,'es','polproactive_executortype_schaman','Ejecutor SCHAMAN CORE');
insert into message(id,locale,code,description) values(1392,'es','polproactive_notificatortype','Notificador');
insert into message(id,locale,code,description) values(1393,'es','polproactive_notificatortype_support','Incluir en panel soporte');
insert into message(id,locale,code,description) values(1394,'es','polproactive_notificatortype_email','Enviar email');
insert into message(id,locale,code,description) values(1395,'es','polproactive_notificatortype_snmp','Enviar trap SNMP');
insert into message(id,locale,code,description) values(1396,'es','polproactive_notificatortype_push','Enviar Push cliente');
insert into message(id,locale,code,description) values(1397,'es','polproactive_notificatortype_pushsms','Enviar SMS/Push cliente');
insert into message(id,locale,code,description) values(1398,'es','polproactive_notificatortype_sms','Enviar SMS cliente');
insert into message(id,locale,code,description) values(1399,'es','polproactive_notificatortype_wscert','Enviar WS Certificación');
insert into message(id,locale,code,description) values(1400,'es','polproactive_panelId','ID del panel');
insert into message(id,locale,code,description) values(1401,'es','polproactive_text','Texto');
insert into message(id,locale,code,description) values(1402,'es','polproactive_description','Descripción');
insert into message(id,locale,code,description) values(1403,'es','polproactive_logic','Lógica');
insert into message(id,locale,code,description) values(1404,'es','polproactive_logic_true','Sin error');
insert into message(id,locale,code,description) values(1405,'es','polproactive_logic_false','Con error');
insert into message(id,locale,code,description) values(1406,'es','polproactive_detail','Detalle');
insert into message(id,locale,code,description) values(1407,'es','polproactive_issue','Asunto');
insert into message(id,locale,code,description) values(1408,'es','polproactive_select','Selector');
insert into message(id,locale,code,description) values(1409,'es','polproactive_select_true','Siempre');
insert into message(id,locale,code,description) values(1410,'es','polproactive_select_false','Sólo si cliente está subscrito');
insert into message(id,locale,code,description) values(1411,'es','polproactive_subtitle','Título');
insert into message(id,locale,code,description) values(1412,'es','polproactive_message','Mensaje');
insert into message(id,locale,code,description) values(1413,'es','polproactive_deeplink','DeepLink');
insert into message(id,locale,code,description) values(1414,'es','polproactive_tag','Tag');
insert into message(id,locale,code,description) values(1415,'es','polproactive_weekdays','Días de la semana');
insert into message(id,locale,code,description) values(1416,'es','polproactive_schedule','Horario');
insert into message(id,locale,code,description) values(1417,'es','polproactive_dateini','Fecha de inicio');
insert into message(id,locale,code,description) values(1418,'es','polproactive_dateend','Fecha de fin');
insert into message(id,locale,code,description) values(1419,'es','polproactive_resources','Asignación de recursos (%)');
insert into message(id,locale,code,description) values(1420,'es','polproactive_starttime','Hora de inicio');
insert into message(id,locale,code,description) values(1421,'es','polproactive_endtime','Hora de fin');
insert into message(id,locale,code,description) values(1422,'es','polproactive_fileName','Fichero');
insert into message(id,locale,code,description) values(1423,'es','configuration_reports_kibanaAgentURL','URL Informe de Rendimiento por Agente');
insert into message(id,locale,code,description) values(1424,'es','global_title','HDM');
insert into message(id,locale,code,description) values(1425,'es','global_footer','Copyright 2018 Bluu Chile - All Rights Reserved');
insert into message(id,locale,code,description) values(1426,'es','button_login','Entrar');
insert into message(id,locale,code,description) values(1427,'es','button_logout','Cerrar sesión');
insert into message(id,locale,code,description) values(1428,'es','button_detail','Detalle');
insert into message(id,locale,code,description) values(1429,'es','button_edit','Editar');
insert into message(id,locale,code,description) values(1430,'es','button_delete','Borrar');
insert into message(id,locale,code,description) values(1431,'es','button_delete_all','Borrar todo');
insert into message(id,locale,code,description) values(1432,'es','button_remove','Eliminar');
insert into message(id,locale,code,description) values(1433,'es','button_add','Añadir');
insert into message(id,locale,code,description) values(1434,'es','button_accept','Aceptar');
insert into message(id,locale,code,description) values(1435,'es','button_cancel','Cancelar');
insert into message(id,locale,code,description) values(1436,'es','button_export','Exportar');
insert into message(id,locale,code,description) values(1437,'es','button_load','Cargar');
insert into message(id,locale,code,description) values(1438,'es','button_search','Buscar');
insert into message(id,locale,code,description) values(1439,'es','button_clear','Limpiar');
insert into message(id,locale,code,description) values(1440,'es','button_save','Guardar');
insert into message(id,locale,code,description) values(1441,'es','button_togglercolumns','Mostrar/Ocultar columnas');
insert into message(id,locale,code,description) values(1442,'es','button_download','Descargar');
insert into message(id,locale,code,description) values(1443,'es','button_download_all','Descargar todo');
insert into message(id,locale,code,description) values(1444,'es','button_share','Compartir');
insert into message(id,locale,code,description) values(1445,'es','button_solve','Solucionar');
insert into message(id,locale,code,description) values(1446,'es','button_visualize','Visualizar');
insert into message(id,locale,code,description) values(1447,'es','button_inventariado','Reinventariar');
insert into message(id,locale,code,description) values(1448,'es','button_upgrade','Actualizar');
insert into message(id,locale,code,description) values(1449,'es','button_identify','Identificar');
insert into message(id,locale,code,description) values(1450,'es','button_active','Activar');
insert into message(id,locale,code,description) values(1451,'es','button_inactive','Desactivar');
insert into message(id,locale,code,description) values(1452,'es','button_clean','Limpiar');
insert into message(id,locale,code,description) values(1453,'es','button_continue','Continuar');
insert into message(id,locale,code,description) values(1454,'es','button_execute','Ejecutar');
insert into message(id,locale,code,description) values(1455,'es','button_tutorial','Ver tutorial');
insert into message(id,locale,code,description) values(1456,'es','button_refresh','Actualizar');
insert into message(id,locale,code,description) values(1457,'es','button_refresh_all','Actualizar todo');
insert into message(id,locale,code,description) values(1458,'es','button_play','Reproducir');
insert into message(id,locale,code,description) values(1459,'es','button_reset','Reestablecer');
insert into message(id,locale,code,description) values(1460,'es','button_reset_all','Reestablecer todos');
insert into message(id,locale,code,description) values(1461,'es','button_sync','Sincronizar');
insert into message(id,locale,code,description) values(1462,'es','button_skip','Omitir');
insert into message(id,locale,code,description) values(1463,'es','button_copy','Copiar');
insert into message(id,locale,code,description) values(1464,'es','button_reboot','Reiniciar');
insert into message(id,locale,code,description) values(1465,'es','button_return','Volver');
insert into message(id,locale,code,description) values(1466,'es','button_lock','Bloquear');
insert into message(id,locale,code,description) values(1467,'es','button_unlock','Desbloquear');
insert into message(id,locale,code,description) values(1468,'es','button_next','Siguiente');
insert into message(id,locale,code,description) values(1469,'es','button_back','Anterior');
insert into message(id,locale,code,description) values(1470,'es','button_chart','Ver Informe');
insert into message(id,locale,code,description) values(1471,'es','combo_select','Seleccione...');
insert into message(id,locale,code,description) values(1472,'es','label_yes','Sí');
insert into message(id,locale,code,description) values(1473,'es','label_no','No');
insert into message(id,locale,code,description) values(1474,'es','label_show','Mostrar');
insert into message(id,locale,code,description) values(1475,'es','label_hide','Ocultar');
insert into message(id,locale,code,description) values(1476,'es','label_name','Nombre');
insert into message(id,locale,code,description) values(1477,'es','table_empty','Sin resultados');
insert into message(id,locale,code,description) values(1478,'es','table_not_empty','Se han encontrado <b>{0}</b> elementos');
insert into message(id,locale,code,description) values(1479,'es','table_records','Registros');
insert into message(id,locale,code,description) values(1480,'es','table_of','de');
insert into message(id,locale,code,description) values(1481,'es','table_page','Página');
insert into message(id,locale,code,description) values(1482,'es','table_pages','Páginas');
insert into message(id,locale,code,description) values(1483,'es','table_export_current','Exportar vista');
insert into message(id,locale,code,description) values(1484,'es','table_export_all','Exportar todo');
insert into message(id,locale,code,description) values(1485,'es','disclaimer_delete','Está a punto de borrar el registro seleccionado. Esta acción no podrá ser deshecha. ¿Desea continuar?');
insert into message(id,locale,code,description) values(1486,'es','disclaimer_delete_all','Está a punto de borrar todos los registros. Esta acción no podrá ser deshecha. ¿Desea continuar?');
insert into message(id,locale,code,description) values(1487,'es','header_warning','ATENCIÓN');
insert into message(id,locale,code,description) values(1488,'es','upload_file_select','Seleccionar');
insert into message(id,locale,code,description) values(1489,'es','upload_file_upload','Subir');
insert into message(id,locale,code,description) values(1490,'es','upload_file_cancel','Cancelar');
insert into message(id,locale,code,description) values(1491,'es','upload_template_download','Desargue');
insert into message(id,locale,code,description) values(1492,'es','upload_template_here','aquí');
insert into message(id,locale,code,description) values(1493,'es','upload_template_template','la plantilla');
insert into message(id,locale,code,description) values(1494,'es','tt_creation','Creación');
insert into message(id,locale,code,description) values(1495,'es','tt_modification','Modificación');
insert into message(id,locale,code,description) values(1496,'es','tt_sync','Sincronización');
insert into message(id,locale,code,description) values(1497,'es','mpheader_confirmation','Confirmación');
insert into message(id,locale,code,description) values(1498,'es','weekday_sunday','Domingo');
insert into message(id,locale,code,description) values(1499,'es','weekday_monday','Lunes');
insert into message(id,locale,code,description) values(1500,'es','weekday_tuesday','Martes');
insert into message(id,locale,code,description) values(1501,'es','weekday_wednesday','Miércoles');
insert into message(id,locale,code,description) values(1502,'es','weekday_thursday','Jueves');
insert into message(id,locale,code,description) values(1503,'es','weekday_friday','Viernes');
insert into message(id,locale,code,description) values(1504,'es','weekday_saturday','Sábado');
insert into message(id,locale,code,description) values(1505,'es','period_hour','Hora');
insert into message(id,locale,code,description) values(1506,'es','period_day','Día');
insert into message(id,locale,code,description) values(1507,'es','period_week','Semana');
insert into message(id,locale,code,description) values(1508,'es','period_month','Mes');
insert into message(id,locale,code,description) values(1509,'es','period_year','Año');
insert into message(id,locale,code,description) values(1510,'es','period_seconds_ago','Segundos atrás');
insert into message(id,locale,code,description) values(1511,'es','period_minutes_ago','Minutos atrás');
insert into message(id,locale,code,description) values(1512,'es','period_hours_ago','Horas atrás');
insert into message(id,locale,code,description) values(1513,'es','period_days_ago','Días atrás');
insert into message(id,locale,code,description) values(1514,'es','period_weeks_ago','Semanas atrás');
insert into message(id,locale,code,description) values(1515,'es','period_months_ago','Meses atrás');
insert into message(id,locale,code,description) values(1516,'es','period_years_ago','Años atrás');
insert into message(id,locale,code,description) values(1517,'es','period_seconds_from','Segundos adelante');
insert into message(id,locale,code,description) values(1518,'es','period_minutes_from','Minutos adelante');
insert into message(id,locale,code,description) values(1519,'es','period_hours_from','Horas adelante');
insert into message(id,locale,code,description) values(1520,'es','period_days_from','Días adelante');
insert into message(id,locale,code,description) values(1521,'es','period_weeks_from','Semanas adelante');
insert into message(id,locale,code,description) values(1522,'es','period_months_from','Meses adelante');
insert into message(id,locale,code,description) values(1523,'es','period_years_from','Años adelante');
insert into message(id,locale,code,description) values(1524,'es','period_today','Hoy');
insert into message(id,locale,code,description) values(1525,'es','period_this_week','Esta semana');
insert into message(id,locale,code,description) values(1526,'es','period_this_month','Este mes');
insert into message(id,locale,code,description) values(1527,'es','period_this_year','Este año');
insert into message(id,locale,code,description) values(1528,'es','period_day_far','El día hasta ahora');
insert into message(id,locale,code,description) values(1529,'es','period_week_date','Semana hasta la fecha');
insert into message(id,locale,code,description) values(1530,'es','period_month_date','Mes hasta la fecha');
insert into message(id,locale,code,description) values(1531,'es','period_year_date','Año hasta la fecha');
insert into message(id,locale,code,description) values(1532,'es','period_yesterday','Ayer');
insert into message(id,locale,code,description) values(1533,'es','period_day_before','Día antes de ayer');
insert into message(id,locale,code,description) values(1534,'es','period_day_week','Este día la semana pasada');
insert into message(id,locale,code,description) values(1535,'es','period_previous_week','Semana anterior');
insert into message(id,locale,code,description) values(1536,'es','period_previous_month','Mes anterior');
insert into message(id,locale,code,description) values(1537,'es','period_previous_year','Año anterior');
insert into message(id,locale,code,description) values(1538,'es','period_15_min','Últimos 15 minutos');
insert into message(id,locale,code,description) values(1539,'es','period_30_min','Últimos 30 minutos');
insert into message(id,locale,code,description) values(1540,'es','period_1_hour','Última hora');
insert into message(id,locale,code,description) values(1541,'es','period_4_hour','Últimas 4 horas');
insert into message(id,locale,code,description) values(1542,'es','period_12_hour','Últimas 12 horas');
insert into message(id,locale,code,description) values(1543,'es','period_24_hour','Últimas 24 horas');
insert into message(id,locale,code,description) values(1544,'es','period_7_day','Últimos 7 días');
insert into message(id,locale,code,description) values(1545,'es','period_30_day','Últimos 30 días');
insert into message(id,locale,code,description) values(1546,'es','period_60_day','Últimos 60 días');
insert into message(id,locale,code,description) values(1547,'es','period_90_day','Últimos 90 días');
insert into message(id,locale,code,description) values(1548,'es','period_6_month','Últimos 6 meses');
insert into message(id,locale,code,description) values(1549,'es','period_1_year','Último año');
insert into message(id,locale,code,description) values(1550,'es','period_2_year','Últimos 2 años');
insert into message(id,locale,code,description) values(1551,'es','period_5_year','Últimos 5 años');
insert into message(id,locale,code,description) values(1552,'es','period_seconds_five','5 segundos');
insert into message(id,locale,code,description) values(1553,'es','period_seconds_ten','10 segundos');
insert into message(id,locale,code,description) values(1554,'es','period_seconds_thirty','30 segundos');
insert into message(id,locale,code,description) values(1555,'es','period_seconds_fortyfive','45 segundos');
insert into message(id,locale,code,description) values(1556,'es','period_minutes_one','1 minuto');
insert into message(id,locale,code,description) values(1557,'es','period_minutes_five','5 minutos');
insert into message(id,locale,code,description) values(1558,'es','period_minutes_fifteen','15 minutos');
insert into message(id,locale,code,description) values(1559,'es','period_minutes_thirty','30 minutos');
insert into message(id,locale,code,description) values(1560,'es','period_hours_one','1 hora');
insert into message(id,locale,code,description) values(1561,'es','period_hours_two','2 horas');
insert into message(id,locale,code,description) values(1562,'es','period_hours_twelve','12 horas');
insert into message(id,locale,code,description) values(1563,'es','period_hours_twentyfour','24 horas');
insert into message(id,locale,code,description) values(1564,'es','period_from','Desde:');
insert into message(id,locale,code,description) values(1565,'es','period_to','Hasta:');
insert into message(id,locale,code,description) values(1566,'es','error_sessionTimeOut_header','Sesión caducada');
insert into message(id,locale,code,description) values(1567,'es','error_sessionTimeOut_text','La sesión ha caducado, por favor, pulse sobre el botón para volver al inicio.');
insert into message(id,locale,code,description) values(1568,'es','validator_notempty ','El campo no puede estar vacío');
insert into message(id,locale,code,description) values(1569,'es','validator_notempty','El campo no puede estar vacío');
insert into message(id,locale,code,description) values(1570,'es','validator_positive','El campo debe ser un número mayor que cero');
insert into message(id,locale,code,description) values(1571,'es','default_error_title','Error');
insert into message(id,locale,code,description) values(1572,'es','default_error_desc','Error en la operación');
insert into message(id,locale,code,description) values(1573,'es','401_error_title','Acceso denegado');
insert into message(id,locale,code,description) values(1574,'es','general_operationerror','Error al realizar la operación');
insert into message(id,locale,code,description) values(1575,'es','401_error_desc','No está autorizado para acceder a este recurso');
insert into message(id,locale,code,description) values(1576,'es','license_dateExpired','Su licencia ha caducado');
insert into message(id,locale,code,description) values(1577,'es','license_dateCloseToExpire','Su licencia está próxima a caducar');
insert into message(id,locale,code,description) values(1578,'es','license_cpeThreshold','El número de dispositivos inventariados excede el contratado en la licencia');
insert into message(id,locale,code,description) values(1579,'es','license_cpeThresholdLess10','El número de dispositivos inventariados está próximo al contratado en la licencia');
insert into message(id,locale,code,description) values(1580,'es','license_ipChanged','La dirección IP del servidor asociada a la licencia ha cambiado');
insert into message(id,locale,code,description) values(1581,'es','license_pattern','La licencia tiene un patrón inválido');
insert into message(id,locale,code,description) values(1582,'es','license_update','Póngase en contacto con Bluu para Actualizar la Licencia');
insert into message(id,locale,code,description) values(1583,'es','login_user_required','Usuario: Valor requerido.');
insert into message(id,locale,code,description) values(1584,'es','login_passwd_required','Contraseñ: Valor requerido.');
insert into message(id,locale,code,description) values(1585,'es','login_error','Usuario incorrecto');
insert into message(id,locale,code,description) values(1586,'es','users_username_required','Valor de usuario requerido');
insert into message(id,locale,code,description) values(1587,'es','users_passwd_required','Valor de contraseña requerido');
insert into message(id,locale,code,description) values(1588,'es','users_passwdconf_required','Valor de confirmación de contraseña requerido');
insert into message(id,locale,code,description) values(1589,'es','users_passwd_different','La contraseña y su confirmación no coinciden');
insert into message(id,locale,code,description) values(1590,'es','users_role_required','Valor de rol requerido');
insert into message(id,locale,code,description) values(1591,'es','users_username_exits','Ya existe el usuario introducido');
insert into message(id,locale,code,description) values(1592,'es','users_add_ok','Usuario dado de alta');
insert into message(id,locale,code,description) values(1593,'es','users_add_ok_log','Usuario {0} dado de alta por {1}');
insert into message(id,locale,code,description) values(1594,'es','users_delete_ok','Usuario eliminado');
insert into message(id,locale,code,description) values(1595,'es','users_delete_ok_log','Usuario {0} eliminado por {1}');
insert into message(id,locale,code,description) values(1596,'es','users_update_ok','Usuario modificado');
insert into message(id,locale,code,description) values(1597,'es','users_update_ok_log','Usuario {0} modificado por {1}');
insert into message(id,locale,code,description) values(1598,'es','users_profile_ok','Modificado el perfil del usuario');
insert into message(id,locale,code,description) values(1599,'es','users_profile_ok_log','Usuario {0} ha modificado su perfil');
insert into message(id,locale,code,description) values(1600,'es','roles_nameexits','El nombre ya esta en uso');
insert into message(id,locale,code,description) values(1601,'es','roles_creation_namerequired','Debes introducir el nombre del rol');
insert into message(id,locale,code,description) values(1602,'es','roles_creation_homepagerequired','Debes introducir la página de inicio');
insert into message(id,locale,code,description) values(1603,'es','roles_creation_success','Rol creado con Éxito');
insert into message(id,locale,code,description) values(1604,'es','roles_edit_success','Rol actualizado con Éxito');
insert into message(id,locale,code,description) values(1605,'es','roles_admin_fail_homepagepermission','El permiso pertenece a la rama de permisos de la página de inicio');
insert into message(id,locale,code,description) values(1606,'es','roles_delete_disclaim','¿Estás seguro de qué quieres eliminar éste/estos rol/es? Los cambios no podrán deshacerse');
insert into message(id,locale,code,description) values(1607,'es','roles_delete_success','Rol {0} borrado con Éxito.');
insert into message(id,locale,code,description) values(1608,'es','roles_changes_success','Rol {0} actualizado con Éxito');
insert into message(id,locale,code,description) values(1609,'es','roles_delete_fail_roleused','EL rol {0} esta asignado a un usuario');
insert into message(id,locale,code,description) values(1610,'es','conndev_serviceId_required','Valor de Id de servicio requerido');
insert into message(id,locale,code,description) values(1611,'es','conndev_label_required','Valor de Nombre de dispositivo requerido');
insert into message(id,locale,code,description) values(1612,'es','conndev_type_required','Valor de Tipo de dispositivo requerido');
insert into message(id,locale,code,description) values(1613,'es','conndev_identify_ok_log','Dispositivo {0} del servicio {1} identificado por {2}');
insert into message(id,locale,code,description) values(1614,'es','conndev_update_ok_log','Dispositivo {0} del servicio {1} editado por {2}');
insert into message(id,locale,code,description) values(1615,'es','conndev_delete_ok_log','Dispositivo {0} del servicio {1} eliminado por {2}');
insert into message(id,locale,code,description) values(1616,'es','conndev_block_ok_log','Dispositivo {0} del servicio {1} bloqueado por {2}');
insert into message(id,locale,code,description) values(1617,'es','conndev_unblock_ok_log','Dispositivo {0} del servicio {1} desbloqueado por {2}');
insert into message(id,locale,code,description) values(1618,'es','conndev_mininit_required','Valor de hora de inicio requerido');
insert into message(id,locale,code,description) values(1619,'es','conndev_minend_required','Valor de hora de fin requerido');
insert into message(id,locale,code,description) values(1620,'es','logviewer_severity_required','Valor de gravedad requerido');
insert into message(id,locale,code,description) values(1621,'es','logviewer_lines_required','Valor de número de líneas requerido');
insert into message(id,locale,code,description) values(1622,'es','logviewer_error','No se ha podido descargar el fichero de log');
insert into message(id,locale,code,description) values(1623,'es','confwifi_serviceId_required','Valor de Id. de servicio requerido');
insert into message(id,locale,code,description) values(1624,'es','confwifi_serviceId_wologin_required','No se ha propocionado la informacion necesaria');
insert into message(id,locale,code,description) values(1625,'es','confwifi_serviceId_wologin_method','No se ha realizado la petición mediante POST');
insert into message(id,locale,code,description) values(1626,'es','confwifi_ssid_required','Valor de Red WiFi requerido');
insert into message(id,locale,code,description) values(1627,'es','confwifi_channel_required','Valor de canal requerido');
insert into message(id,locale,code,description) values(1628,'es','confwifi_enctype_required','Valor de seguridad requerido');
insert into message(id,locale,code,description) values(1629,'es','confwifi_passwd_required','Valor de contraseña requerido');
insert into message(id,locale,code,description) values(1630,'es','confwifi_agent_null','No dispone de interfaz WiFi');
insert into message(id,locale,code,description) values(1631,'es','actexecutor_default_ok','La acción se ha ejecutado correctamente');
insert into message(id,locale,code,description) values(1632,'es','actexecutor_default_ko','Se ha producido un error en la ejecución de la acción');
insert into message(id,locale,code,description) values(1633,'es','actexecutor_executing','Ejecutando acción...');
insert into message(id,locale,code,description) values(1634,'es','trb_serviceId_required','Valor de Id de servicio requerido');
insert into message(id,locale,code,description) values(1635,'es','trb_serviceId_wologin_required','No se ha propocionado la información necesaria');
insert into message(id,locale,code,description) values(1636,'es','trb_serviceId_wologin_method','No se ha realizado la petición mediante POST');
insert into message(id,locale,code,description) values(1637,'es','trb_template_master_error','No existe la plantilla MASTER');
insert into message(id,locale,code,description) values(1638,'es','trb_template_smartphone_error','No existe la plantilla smartphone');
insert into message(id,locale,code,description) values(1639,'es','trb_template_front_error','No existe la plantilla FRONT');
insert into message(id,locale,code,description) values(1640,'es','trb_template_ims_error','No existe la plantilla IMS');
insert into message(id,locale,code,description) values(1641,'es','trb_template_radius_error','No existe la plantilla RADIUS');
insert into message(id,locale,code,description) values(1642,'es','trb_template_dslam_error','No existe la plantilla DSLAM');
insert into message(id,locale,code,description) values(1643,'es','trb_template_olt_error','No existe la plantilla OLT');
insert into message(id,locale,code,description) values(1644,'es','trb_template_acs_error','No existe la plantilla ACS');
insert into message(id,locale,code,description) values(1645,'es','trb_template_jira_error','No existe la plantilla JIRA');
insert into message(id,locale,code,description) values(1646,'es','trb_template_ibercom_error','No existe la plantilla IBERCOM');
insert into message(id,locale,code,description) values(1647,'es','trb_template_login_error','No existe la plantilla LOGIN');
insert into message(id,locale,code,description) values(1648,'es','trb_topology_empty','Topología vacía');
insert into message(id,locale,code,description) values(1649,'es','trb_history_loading_error','Error cargando el histórico del cliente');
insert into message(id,locale,code,description) values(1650,'es','trb_session_problem','Problema con la sessión del diagnóstico');
insert into message(id,locale,code,description) values(1651,'es','trb_block_errorunblocking','Error desbloqueando el dispositivo');
insert into message(id,locale,code,description) values(1652,'es','trb_block_errorblocking','Error bloqueando el dispositivo');
insert into message(id,locale,code,description) values(1653,'es','trb_block_unblocksuccess','Dispositivo {0} desbloqueado');
insert into message(id,locale,code,description) values(1654,'es','trb_block_blocksuccess','Dispositivo {0} bloqueado');
insert into message(id,locale,code,description) values(1655,'es','trball_notrunning','Schaman no está arrancado');
insert into message(id,locale,code,description) values(1656,'es','trball_notactive','El módulo de Troubleshoot no está activo');
insert into message(id,locale,code,description) values(1657,'es','trball_idValue_required','Valor de identificador requerido');
insert into message(id,locale,code,description) values(1658,'es','trball_idType_required','Valor de tipo de identificador requerido');
insert into message(id,locale,code,description) values(1659,'es','trball_idType_notvalid','Tipo de identificador no válido');
insert into message(id,locale,code,description) values(1660,'es','trball_session_close_required','Estado de cierre requerido');
insert into message(id,locale,code,description) values(1661,'es','trb_linked_serviceId_notfound','No se ha encontrado el servicio');
insert into message(id,locale,code,description) values(1662,'es','trb_linked_serviceId_notclient','No se ha encontrado ningún cliente asociado al servicio');
insert into message(id,locale,code,description) values(1663,'es','trb_linked_device','No se ha seleccionado ningún dispositivo');
insert into message(id,locale,code,description) values(1664,'es','trb_linked_devices_notfound','No se ha encontrado dispositivos asociados al servicio');
insert into message(id,locale,code,description) values(1665,'es','trb_linked_notif_error','La notificación no se ha enviado');
insert into message(id,locale,code,description) values(1666,'es','trb_linked_offlinecode_required','Código offline requerido');
insert into message(id,locale,code,description) values(1667,'es','trb_linked_offlinecode_problem','Error cargando código offline');
insert into message(id,locale,code,description) values(1668,'es','trb_linked_offlinecode_success','Código offline cargado con Éxito');
insert into message(id,locale,code,description) values(1669,'es','obsrules_add_ok','Regla de obsolescencia creada');
insert into message(id,locale,code,description) values(1670,'es','obsrules_add_ok_log','Regla de obsolescencia {0} creada por {1}');
insert into message(id,locale,code,description) values(1671,'es','obsrules_edit_ok','Regla de obsolescencia modificada');
insert into message(id,locale,code,description) values(1672,'es','obsrules_edit_ok_log','Regla de obsolescencia {0} modificada por {1}');
insert into message(id,locale,code,description) values(1673,'es','obsrules_delete_ok','Regla de obsolescencia eliminada');
insert into message(id,locale,code,description) values(1674,'es','obsrules_delete_ok_log','Regla de obsolescencia {0} eliminada por {1}');
insert into message(id,locale,code,description) values(1675,'es','obsrules_position_required','El campo Posición es obligatorio');
insert into message(id,locale,code,description) values(1676,'es','obsrules_position_min','La posición al menos debe ser 1');
insert into message(id,locale,code,description) values(1677,'es','obsrules_position_max','La posición debe ser igual o menor a {0}');
insert into message(id,locale,code,description) values(1678,'es','obsrules_sim_imsi_wildcard_repeated','El campo SIM IMSI tiene el caracter comodín repetido');
insert into message(id,locale,code,description) values(1679,'es','obsrules_sim_imsi_wildcard_minlength','El campo SIM IMSI ha de tener algún caracter además del comodín');
insert into message(id,locale,code,description) values(1680,'es','obsrules_sim_imsi_wildcard_position','El campo SIM IMSI tiene el caracter comodín en posición incorrecta');
insert into message(id,locale,code,description) values(1681,'es','obsrules_dev_os_wildcard_repeated','El campo Sistema operativo tiene el caracter comodín repetido');
insert into message(id,locale,code,description) values(1682,'es','obsrules_dev_os_wildcard_minlength','El campo Sistema operativo ha de tener algún caracter además del comodín');
insert into message(id,locale,code,description) values(1683,'es','obsrules_dev_os_wildcard_position','El campo Sistema operativo tiene el caracter comodín en posición incorrecta');
insert into message(id,locale,code,description) values(1684,'es','obsrules_dev_osversion_wildcard_repeated','El campo Versión SO tiene el caracter comodín repetido');
insert into message(id,locale,code,description) values(1685,'es','obsrules_dev_osversion_wildcard_minlength','El campo Versión SO ha de tener algún caracter además del comodín');
insert into message(id,locale,code,description) values(1686,'es','obsrules_dev_osversion_wildcard_position','El campo Versión SO tiene el caracter comodín en posición incorrecta');
insert into message(id,locale,code,description) values(1687,'es','obsrules_dev_app_wildcard_repeated','El campo Aplicación tiene el caracter comodín repetido');
insert into message(id,locale,code,description) values(1688,'es','obsrules_dev_app_wildcard_minlength','El campo Aplicación ha de tener algún caracter además del comodín');
insert into message(id,locale,code,description) values(1689,'es','obsrules_dev_app_wildcard_position','El campo Aplicación tiene el caracter comodín en posición incorrecta');
insert into message(id,locale,code,description) values(1690,'es','obsrules_dev_appversion_wildcard_repeated','El campo Versión App tiene el caracter comodín repetido');
insert into message(id,locale,code,description) values(1691,'es','obsrules_dev_appversion_wildcard_minlength','El campo Versión App ha de tener algún caracter además del comodín');
insert into message(id,locale,code,description) values(1692,'es','obsrules_dev_appversion_wildcard_position','El campo Versión App tiene el caracter comodín en posición incorrecta');
insert into message(id,locale,code,description) values(1693,'es','obsrules_manufacturer_wildcard_repeated','El campo Fabricante tiene el caracter comodín repetido');
insert into message(id,locale,code,description) values(1694,'es','obsrules_manufacturer_wildcard_minlength','El campo Fabricante ha de tener algún caracter además del comodín');
insert into message(id,locale,code,description) values(1695,'es','obsrules_manufacturer_wildcard_position','El campo Fabricante tiene el caracter comodín en posición incorrecta');
insert into message(id,locale,code,description) values(1696,'es','obsrules_model_wildcard_repeated','El campo Modelo tiene el caracter comodín repetido');
insert into message(id,locale,code,description) values(1697,'es','obsrules_model_wildcard_minlength','El campo Modelo ha de tener algún caracter además del comodín');
insert into message(id,locale,code,description) values(1698,'es','obsrules_model_wildcard_position','El campo Modelo tiene el caracter comodín en posición incorrecta');
insert into message(id,locale,code,description) values(1699,'es','obsrules_status_required','El campo Estado es obligatorio');
insert into message(id,locale,code,description) values(1700,'es','obsrules_sim_imsi_required','El campo SIM IMSI debe ser rellenado (se ha rellenado su operador)');
insert into message(id,locale,code,description) values(1701,'es','obsrules_sim_imsi_oper_required','El campo Operador para SIM IMSI debe ser rellenado (se ha rellenado su valor)');
insert into message(id,locale,code,description) values(1702,'es','obsrules_sim_imsi_regexp','El campo SIM IMSI contiene caracteres no válidos');
insert into message(id,locale,code,description) values(1703,'es','obsrules_os_required','El campo Sist Operativo debe ser rellenado (se ha rellenado su operador)');
insert into message(id,locale,code,description) values(1704,'es','obsrules_os_oper_required','El campo Operador para Sist Operativo debe ser rellenado (se ha rellenado su valor)');
insert into message(id,locale,code,description) values(1705,'es','obsrules_os_oper_regexp','El campo Sist Operativo contiene caracteres no válidos');
insert into message(id,locale,code,description) values(1706,'es','obsrules_osversion_required','El campo Versión SO debe ser rellenado (se ha rellenado su operador)');
insert into message(id,locale,code,description) values(1707,'es','obsrules_osversion_oper_required','El campo Operador para Versión SO debe ser rellenado (se ha rellenado su valor)');
insert into message(id,locale,code,description) values(1708,'es','obsrules_osversion_regexp','El campo Versión SO contiene caracteres no válidos');
insert into message(id,locale,code,description) values(1709,'es','obsrules_osversion_regexp2','El campo Versión SO contiene caracteres no válidos (o no tiene el formato adecuado)');
insert into message(id,locale,code,description) values(1710,'es','obsrules_osversion_pointend','El campo Versión SO no puede acabar en .');
insert into message(id,locale,code,description) values(1711,'es','obsrules_osversion_pointend2','El campo Versión SO no puede acabar en . para ninguno de los extremos del rango');
insert into message(id,locale,code,description) values(1712,'es','obsrules_app_required','El campo Aplicación debe ser rellenado (se ha rellenado su operador)');
insert into message(id,locale,code,description) values(1713,'es','obsrules_app_oper_required','El campo Operador para Aplicación debe ser rellenado (se ha rellenado su valor)');
insert into message(id,locale,code,description) values(1714,'es','obsrules_app_regexp','El campo Aplicación contiene caracteres no válidos');
insert into message(id,locale,code,description) values(1715,'es','obsrules_appversion_required','El campo Versión App. debe ser rellenado (se ha rellenado su operador)');
insert into message(id,locale,code,description) values(1716,'es','obsrules_appversion_oper_required','El campo Operador para Versión App debe ser rellenado (se ha rellenado su valor)');
insert into message(id,locale,code,description) values(1717,'es','obsrules_appversion_regexp','El campo Versión App. contiene caracteres no válidos');
insert into message(id,locale,code,description) values(1718,'es','obsrules_appversion_regexp2','El campo Versión App. contiene caracteres no válidos (o no tiene el formato adecuado)');
insert into message(id,locale,code,description) values(1719,'es','obsrules_appversion_pointend','El campo Versión App. no puede acabar en .');
insert into message(id,locale,code,description) values(1720,'es','obsrules_appversion_pointend2','El campo Versión App. no puede acabar en . para ninguno de los extremos del rango');
insert into message(id,locale,code,description) values(1721,'es','obsrules_manufacturer_required','El campo Fabricante debe ser rellenado (se ha rellenado su operador)');
insert into message(id,locale,code,description) values(1722,'es','obsrules_manufacturer_oper_required','El campo Operador para Fabricante debe ser rellenado (se ha rellenado su valor)');
insert into message(id,locale,code,description) values(1723,'es','obsrules_manufacturer_regexp','El campo Fabricante contiene caracteres no válidos');
insert into message(id,locale,code,description) values(1724,'es','obsrules_model_required','El campo Modelo debe ser rellenado (se ha rellenado su operador)');
insert into message(id,locale,code,description) values(1725,'es','obsrules_model_oper_required','El campo Operador para Modelo debe ser rellenado (se ha rellenado su valor)');
insert into message(id,locale,code,description) values(1726,'es','obsrules_model_regexp','El campo Modelo contiene caracteres no válidos');
insert into message(id,locale,code,description) values(1727,'es','bbar_param_name_required','El campo Nombre es obligatorio');
insert into message(id,locale,code,description) values(1728,'es','bbar_param_name_exits','Ya existe un parámetro con el mismo nombre');
insert into message(id,locale,code,description) values(1729,'es','bbar_identifier_required','El campo Identificador es obligatorio');
insert into message(id,locale,code,description) values(1730,'es','bbar_identifier_exits','No se permiten identificadores repetidos');
insert into message(id,locale,code,description) values(1731,'es','bbar_position_required','El campo Posición es obligatorio');
insert into message(id,locale,code,description) values(1732,'es','bbar_position_positive','El campo Posición debe ser un número mayor o igual a 1');
insert into message(id,locale,code,description) values(1733,'es','bbar_position_exits_android_handset','No se permiten elemento en la misma posición (%d) dentro del mismo nivel (Plat: Android-Handset)');
insert into message(id,locale,code,description) values(1734,'es','bbar_position_exits_android_tablet','No se permiten elemento en la misma posición (%d) dentro del mismo nivel (Plat: Android-Tablet)');
insert into message(id,locale,code,description) values(1735,'es','bbar_position_exits_ios_handset','No se permiten elemento en la misma posición (%d) dentro del mismo nivel (Plat: iOS-Handset)');
insert into message(id,locale,code,description) values(1736,'es','bbar_position_exits_ios_tablet','No se permiten elemento en la misma posición (%d) dentro del mismo nivel (Plat: iOS-Tablet)');
insert into message(id,locale,code,description) values(1737,'es','bbar_position_exits_windows','No se permiten elemento en la misma posición (%d) dentro del mismo nivel (Plat: Windows)');
insert into message(id,locale,code,description) values(1738,'es','bbar_position_exits_linux','No se permiten elemento en la misma posición (%d) dentro del mismo nivel (Plat: Linux)');
insert into message(id,locale,code,description) values(1739,'es','bbar_position_exits_mac','No se permiten elemento en la misma posición (%d) dentro del mismo nivel (Plat: Mac Os X)');
insert into message(id,locale,code,description) values(1740,'es','bbar_text_required','El campo Etiqueta es obligatorio');
insert into message(id,locale,code,description) values(1741,'es','bbar_type_required','El campo Tipo es obligatorio');
insert into message(id,locale,code,description) values(1742,'es','bbar_link_required','El campo Link es obligatorio');
insert into message(id,locale,code,description) values(1743,'es','bbar_menu_cycle','No se puede asociar al menú indicado ya que ?te depende actualmente de ?l');
insert into message(id,locale,code,description) values(1744,'es','bbar_icons_required','El campo Iconos es obligatorio');
insert into message(id,locale,code,description) values(1745,'es','bbar_icons_name_notfound','No se han encontrado los siguientes iconos:');
insert into message(id,locale,code,description) values(1746,'es','bbar_icons_name_notequals','El descripcion de los ficheros no es igual para todos los iconos');
insert into message(id,locale,code,description) values(1747,'es','bbar_add_ok','Botonera dada de alta');
insert into message(id,locale,code,description) values(1748,'es','bbar_add_ok_log','Botonera dada de alta por {0}');
insert into message(id,locale,code,description) values(1749,'es','bbar_preview_select_empty','No ha seleccionado plataforma y/o pantalla');
insert into message(id,locale,code,description) values(1750,'es','bbar_sync_url_required','El campo URL es obligatorio');
insert into message(id,locale,code,description) values(1751,'es','bbar_sync_ok','Botonera sincronizada correctamente');
insert into message(id,locale,code,description) values(1752,'es','bbar_sync_error','Error al sincronizar la botonera');
insert into message(id,locale,code,description) values(1753,'es','client_client_required','Valor del Cliente requerido');
insert into message(id,locale,code,description) values(1754,'es','client_passwd_required','Valor de contraseña requerido');
insert into message(id,locale,code,description) values(1755,'es','client_passwdconf_required','Valor de confirmación de contraseña requerido');
insert into message(id,locale,code,description) values(1756,'es','client_passwd_different','La contraseña y su confirmación no coinciden');
insert into message(id,locale,code,description) values(1757,'es','client_client_exits','Ya existe el cliente introducido');
insert into message(id,locale,code,description) values(1758,'es','client_devices_exits','El cliente tiene dispositivos asociados');
insert into message(id,locale,code,description) values(1759,'es','client_service_exits','El cliente está asignado en uno o varios servicios');
insert into message(id,locale,code,description) values(1760,'es','client_add_ok','Cliente dado de alta');
insert into message(id,locale,code,description) values(1761,'es','client_add_ok_log','Cliente {0} dado de alta por {1}');
insert into message(id,locale,code,description) values(1762,'es','client_delete_ok','Cliente eliminado');
insert into message(id,locale,code,description) values(1763,'es','client_delete_ok_log','Cliente {0} eliminado por {1}');
insert into message(id,locale,code,description) values(1764,'es','client_update_ok','Cliente modificado');
insert into message(id,locale,code,description) values(1765,'es','client_update_ok_log','Cliente {0} modificado por {1}');
insert into message(id,locale,code,description) values(1766,'es','service_client_required','Valor de cliente requerido');
insert into message(id,locale,code,description) values(1767,'es','service_service_required','Valor de servicio requerido');
insert into message(id,locale,code,description) values(1768,'es','service_service_exits','Ya existe el servicio introducido');
insert into message(id,locale,code,description) values(1769,'es','service_type_required','Valor de tipo requerido');
insert into message(id,locale,code,description) values(1770,'es','service_description_required','Valor de descripción requerido');
insert into message(id,locale,code,description) values(1771,'es','service_add_ok','Servicio dado de alta');
insert into message(id,locale,code,description) values(1772,'es','service_add_ok_log','Servicio {0} dado de alta por {1}');
insert into message(id,locale,code,description) values(1773,'es','service_delete_ok','Servicio eliminado');
insert into message(id,locale,code,description) values(1774,'es','service_delete_ok_log','Servicio {0} eliminado por {1}');
insert into message(id,locale,code,description) values(1775,'es','service_update_ok','Servicio modificado');
insert into message(id,locale,code,description) values(1776,'es','service_update_ok_log','Servicio {0} modificado por {1}');
insert into message(id,locale,code,description) values(1777,'es','device_device_required','Valor de dispositivo requerido');
insert into message(id,locale,code,description) values(1778,'es','device_imsi_required','Valor de IMSI requerido');
insert into message(id,locale,code,description) values(1779,'es','device_manufacturer_required','Valor de fabricante requerido');
insert into message(id,locale,code,description) values(1780,'es','device_model_required','Valor de modelo requerido');
insert into message(id,locale,code,description) values(1781,'es','device_system_required','Valor de sistema requerido');
insert into message(id,locale,code,description) values(1782,'es','device_so_required','Valor de SO requerido');
insert into message(id,locale,code,description) values(1783,'es','device_sover_required','Valor de SO ver. requerido');
insert into message(id,locale,code,description) values(1784,'es','device_client_required','Valor de cliente requerido');
insert into message(id,locale,code,description) values(1785,'es','device_service_required','Valor de servicio requerido');
insert into message(id,locale,code,description) values(1786,'es','device_pushtoken_required','Valor de PT requerido');
insert into message(id,locale,code,description) values(1787,'es','device_device_exits','Ya existe el dispositivo introducido');
insert into message(id,locale,code,description) values(1788,'es','device_add_ok','Dispositivo dado de alta');
insert into message(id,locale,code,description) values(1789,'es','device_add_ok_log','Dispositivo {0} dado de alta por {1}');
insert into message(id,locale,code,description) values(1790,'es','device_delete_ok','Dispositivo eliminado');
insert into message(id,locale,code,description) values(1791,'es','device_delete_ok_log','Dispositivo {0} eliminado por {1}');
insert into message(id,locale,code,description) values(1792,'es','device_update_ok','Dispositivo modificado');
insert into message(id,locale,code,description) values(1793,'es','device_update_ok_log','Dispositivo {0} modificado por {1}');
insert into message(id,locale,code,description) values(1794,'es','manufacturer_manufacturer_required','Valor de fabricante requerido');
insert into message(id,locale,code,description) values(1795,'es','manufacturer_numcpes_required','Valor de n° de cpes requerido');
insert into message(id,locale,code,description) values(1796,'es','manufacturer_add_ok','Fabricante dado de alta');
insert into message(id,locale,code,description) values(1797,'es','manufacturer_add_ok_log','Fabricante {0} dado de alta por {1}');
insert into message(id,locale,code,description) values(1798,'es','manufacturer_delete_ok','Fabricante eliminado');
insert into message(id,locale,code,description) values(1799,'es','manufacturer_delete_ok_log','Fabricante {0} eliminado por {1}');
insert into message(id,locale,code,description) values(1800,'es','manufacturer_update_ok','Fabricante modificado');
insert into message(id,locale,code,description) values(1801,'es','manufacturer_update_ok_log','Fabricante {0} modificado por {1}');
insert into message(id,locale,code,description) values(1802,'es','model_manufacturer_required','Valor de fabricante requerido');
insert into message(id,locale,code,description) values(1803,'es','model_model_required','Valor de modelo requerido');
insert into message(id,locale,code,description) values(1804,'es','model_numcpes_required','Valor de n° de cpes requerido');
insert into message(id,locale,code,description) values(1805,'es','model_add_ok','Modelo dado de alta');
insert into message(id,locale,code,description) values(1806,'es','model_add_ok_log','Modelo {0} dado de alta por {1}');
insert into message(id,locale,code,description) values(1807,'es','model_delete_ok','Modelo eliminado');
insert into message(id,locale,code,description) values(1808,'es','model_delete_ok_log','Modelo {0} eliminado por {1}');
insert into message(id,locale,code,description) values(1809,'es','model_update_ok','Modelo modificado');
insert into message(id,locale,code,description) values(1810,'es','model_update_ok_log','Modelo {0} modificado por {1}');
insert into message(id,locale,code,description) values(1811,'es','cpe_reinvent_exist','No se hay elementos para reinventariar');
insert into message(id,locale,code,description) values(1812,'es','cpe_cwmp_par_required','Parámetro requerido');
insert into message(id,locale,code,description) values(1813,'es','cpe_cwmp_val_required','Valor requerido');
insert into message(id,locale,code,description) values(1814,'es','cpe_reinvent_ok','Reinventariado ejectuado correctamente');
insert into message(id,locale,code,description) values(1815,'es','cpe_add_ok','Modelo dado de alta');
insert into message(id,locale,code,description) values(1816,'es','cpe_reinvent_ok_log','Modelo {0} dado de alta por {1}');
insert into message(id,locale,code,description) values(1817,'es','cpe_add_ok_log','Reinventariado ejectuado correctamente por {0}');
insert into message(id,locale,code,description) values(1818,'es','cpe_delete_ok','Modelo eliminado');
insert into message(id,locale,code,description) values(1819,'es','cpe_delete_ok_log','Modelo {0} eliminado por {1}');
insert into message(id,locale,code,description) values(1820,'es','cpe_update_ok','Modelo modificado');
insert into message(id,locale,code,description) values(1821,'es','cpe_update_ok_log','Modelo {0} modificado por {1}');
insert into message(id,locale,code,description) values(1822,'es','access_hostname_required','Valor de equipo requerido');
insert into message(id,locale,code,description) values(1823,'es','access_provisiongroup_required','Valor de grupo de provisión requerido');
insert into message(id,locale,code,description) values(1824,'es','access_zone_required','Valor de zona requerido');
insert into message(id,locale,code,description) values(1825,'es','access_subzone_required','Valor de subzona requerido');
insert into message(id,locale,code,description) values(1826,'es','access_node_required','Valor de nodo requerido');
insert into message(id,locale,code,description) values(1827,'es','access_ipaddress_required','Valor de dirección IP requerido:');
insert into message(id,locale,code,description) values(1828,'es','access_giaddress_required','Valor de giaddress requerido:');
insert into message(id,locale,code,description) values(1829,'es','access_accesstype_required','Valor de tipo requerido');
insert into message(id,locale,code,description) values(1830,'es','access_numcpes_required','Valor de n° de cpes requerido');
insert into message(id,locale,code,description) values(1831,'es','access_hostname_exits','Ya existe el equipo introducido');
insert into message(id,locale,code,description) values(1832,'es','access_ipaddress_exits','Ya existe la dirección IP introducido');
insert into message(id,locale,code,description) values(1833,'es','access_giaddress_exits','Ya existe la giaddress introducida: {0}');
insert into message(id,locale,code,description) values(1834,'es','access_add_ok','Equipo de acceso dado de alta');
insert into message(id,locale,code,description) values(1835,'es','access_add_ok_log','Equipo de acceso {0} dado de alta por {1}');
insert into message(id,locale,code,description) values(1836,'es','access_delete_ok','Equipo de acceso eliminado');
insert into message(id,locale,code,description) values(1837,'es','access_delete_ok_log','Equipo de acceso {0} eliminado por {1}');
insert into message(id,locale,code,description) values(1838,'es','access_update_ok','Equipo de acceso modificado');
insert into message(id,locale,code,description) values(1839,'es','access_update_ok_log','Equipo de acceso {0} modificado por {1}');
insert into message(id,locale,code,description) values(1840,'es','access_upload_add','{0} equipos de acceso dado de alta');
insert into message(id,locale,code,description) values(1841,'es','access_upload_update','{0} equipos de acceso modificados');
insert into message(id,locale,code,description) values(1842,'es','access_upload_error','{0} equipos de acceso erroneos');
insert into message(id,locale,code,description) values(1843,'es','access_upload_line','La linea {0} no presenta el formato esperado');
insert into message(id,locale,code,description) values(1844,'es','firmware_manufacturer_required','Valor de fabricante requerido');
insert into message(id,locale,code,description) values(1845,'es','firmware_provisiongroup_required','Valor de grupo de provisión requerido');
insert into message(id,locale,code,description) values(1846,'es','firmware_model_required','Valor de modelo requerido');
insert into message(id,locale,code,description) values(1847,'es','firmware_firmware_required','Valor de firmware requerido');
insert into message(id,locale,code,description) values(1848,'es','firmware_node_required','Valor de nodo requerido');
insert into message(id,locale,code,description) values(1849,'es','firmware_numcpes_required','Valor de n° de cpes requerido');
insert into message(id,locale,code,description) values(1850,'es','firmware_manufacturer_exits','Ya existe el fabricante introducido');
insert into message(id,locale,code,description) values(1851,'es','firmware_model_exits','Ya existe el modelo introducido');
insert into message(id,locale,code,description) values(1852,'es','firmware_firmware_exits','Ya existe el firmware introducido');
insert into message(id,locale,code,description) values(1853,'es','firmware_giaddress_exits','Ya existe el giaddress {0} en un equipo distinto al especificado');
insert into message(id,locale,code,description) values(1854,'es','firmware_add_ok','Equipo de acceso dado de alta');
insert into message(id,locale,code,description) values(1855,'es','firmware_add_ok_log','Equipo de acceso {0} dado de alta por {1}');
insert into message(id,locale,code,description) values(1856,'es','firmware_delete_ok','Equipo de acceso eliminado');
insert into message(id,locale,code,description) values(1857,'es','firmware_delete_ok_log','Equipo de acceso {0} eliminado por {1}');
insert into message(id,locale,code,description) values(1858,'es','firmware_update_ok','Equipo de acceso modificado');
insert into message(id,locale,code,description) values(1859,'es','firmware_update_ok_log','Equipo de acceso {0} modificado por {1}');
insert into message(id,locale,code,description) values(1860,'es','firmware_upload_add','{0} equipos de acceso dado de alta');
insert into message(id,locale,code,description) values(1861,'es','firmware_upload_update','{0} equipos de acceso modificados');
insert into message(id,locale,code,description) values(1862,'es','firmware_upload_error','{0} equipos de acceso erroneos');
insert into message(id,locale,code,description) values(1863,'es','firmwareFile_manufacturer_required','Valor de fabricante requerido');
insert into message(id,locale,code,description) values(1864,'es','firmwareFile_name_required','Valor de grupo de nombre requerido');
insert into message(id,locale,code,description) values(1865,'es','firmwareFile_description_required','Valor de fichero requerido');
insert into message(id,locale,code,description) values(1866,'es','firmwareFile_firmware_required','Valor de firmware requerido');
insert into message(id,locale,code,description) values(1867,'es','firmwareFile_model_required','Valor de modelo requerido');
insert into message(id,locale,code,description) values(1868,'es','firmwareFile_numcpes_required','Valor de n° de cpes requerido');
insert into message(id,locale,code,description) values(1869,'es','firmwareFile_manufacturer_exits','Ya existe el fabricante introducido');
insert into message(id,locale,code,description) values(1870,'es','firmwareFile_model_exits','Ya existe el modelo introducido');
insert into message(id,locale,code,description) values(1871,'es','firmwareFile_firmware_exits','Ya existe el firmware introducido');
insert into message(id,locale,code,description) values(1872,'es','firmwareFile_name_exits','Ya existe el nombre introducido');
insert into message(id,locale,code,description) values(1873,'es','firmwareFile_description_exits','Ya existe el fichero introducido');
insert into message(id,locale,code,description) values(1874,'es','firmwareFile_add_ok','Equipo de acceso dado de alta');
insert into message(id,locale,code,description) values(1875,'es','firmwareFile_add_ok_log','Equipo de acceso {0} dado de alta por {1}');
insert into message(id,locale,code,description) values(1876,'es','firmwareFile_delete_ok','Equipo de acceso eliminado');
insert into message(id,locale,code,description) values(1877,'es','firmwareFile_delete_ok_log','Equipo de acceso {0} eliminado por {1}');
insert into message(id,locale,code,description) values(1878,'es','firmwareFile_update_ok','Equipo de acceso modificado');
insert into message(id,locale,code,description) values(1879,'es','firmwareFile_update_ok_log','Equipo de acceso {0} modificado por {1}');
insert into message(id,locale,code,description) values(1880,'es','firmwareFile_upload_add','{0} equipos de acceso dado de alta');
insert into message(id,locale,code,description) values(1881,'es','firmwareFile_upload_update','{0} equipos de acceso modificados');
insert into message(id,locale,code,description) values(1882,'es','firmwareFile_upload_error','{0} equipos de acceso erroneos');
insert into message(id,locale,code,description) values(1883,'es','firmwareFile_edit_manufacturererror','No se encuentra fabricante');
insert into message(id,locale,code,description) values(1884,'es','firmwareFile_upload_file_exist','El Fichero No Existe o su Tamaño es 0');
insert into message(id,locale,code,description) values(1885,'es','firmwareFile_upload_dir_exist','El Directorio Local No Existe');
insert into message(id,locale,code,description) values(1886,'es','firmwareFile_download_exist','El fichero no existe');
insert into message(id,locale,code,description) values(1887,'es','tutorial_name_required','Valor de fichero requerido');
insert into message(id,locale,code,description) values(1888,'es','tutorial_name_limit','El nombre excede el tamaño máximo permitido');
insert into message(id,locale,code,description) values(1889,'es','tutorial_add_ok','Tutorial dado de alta');
insert into message(id,locale,code,description) values(1890,'es','tutorial_add_ok_log','Tutorial {0} dado de alta por {1}');
insert into message(id,locale,code,description) values(1891,'es','tutorial_delete_ok','Tutorial eliminado');
insert into message(id,locale,code,description) values(1892,'es','tutorial_delete_ok_log','Tutorial {0} eliminado por {1}');
insert into message(id,locale,code,description) values(1893,'es','tutorial_update_ok','Tutorial modificado');
insert into message(id,locale,code,description) values(1894,'es','tutorial_update_ok_log','Tutorial {0} modificado por {1}');
insert into message(id,locale,code,description) values(1895,'es','tutorial_upload_file_exist','El Fichero No Existe o su Tamaño es 0');
insert into message(id,locale,code,description) values(1896,'es','tutorial_upload_error','Error al subir el tutorial {0}');
insert into message(id,locale,code,description) values(1897,'es','tutorial_download_exist','El fichero no existe');
insert into message(id,locale,code,description) values(1898,'es','tutorial_blank_buttonlabel','El botón {0} de la página con id {1} no tiene atributo label o está vació');
insert into message(id,locale,code,description) values(1899,'es','tutorial_blank_buttonaction','El botón {0} de la página con id {1} no tiene atributo action o está vació');
insert into message(id,locale,code,description) values(1900,'es','tutorial_blank_buttonvalue','El botón {0} de la página con id {1} no tiene atributo value o está vació');
insert into message(id,locale,code,description) values(1901,'es','tutorial_alert_email','Se va proceder a enviar un email a la dirección');
insert into message(id,locale,code,description) values(1902,'es','tutorial_alert_phone','Se va proceder a enviar un email a la dirección');
insert into message(id,locale,code,description) values(1903,'es','tutorial_alert_action','Se va proceder a ejecutar la acción con id {0} y role {1}');
insert into message(id,locale,code,description) values(1904,'es','tutorial_value_notfound','No se encuentra el atributo value en el botón {0} de la página con id {1}');
insert into message(id,locale,code,description) values(1905,'es','tutorial_value_notexist','El atributo value en el botón {0} de la página con id {1} no existe o no es un número positivo');
insert into message(id,locale,code,description) values(1906,'es','tutorial_attribute_notexist','El atributo value en el botón {0} de la página con id {1} no tiene atributo value o está vació');
insert into message(id,locale,code,description) values(1907,'es','tutorial_action_notexist','El atributo value en el botón {0} de la página con id {1} tiene definido un action no válido');
insert into message(id,locale,code,description) values(1908,'es','tutorial_notselected','No Hay Tutorial Seleccionado');
insert into message(id,locale,code,description) values(1909,'es','tutorial_leftbutton_repeated','Existe más de un botón \"left\" en la página con id {0}');
insert into message(id,locale,code,description) values(1910,'es','tutorial_rightbutton_repeated','Existe más de un botón \"right\" en la página con id {0}');
insert into message(id,locale,code,description) values(1911,'es','tutorial_centerbutton_repeated','Existe más de un botón \"center\" en la página con id {0}');
insert into message(id,locale,code,description) values(1912,'es','tutorial_body_notfound','No se encuentra la etiqueta BODY');
insert into message(id,locale,code,description) values(1913,'es','tutorial_page_notexist','No existe página con id {0} o hay mas de una con dicho id');
insert into message(id,locale,code,description) values(1914,'es','tutorial_notload','<h1>Error: No se ha podido cargar el tutorial</h1><br/><p>{0}</p>');
insert into message(id,locale,code,description) values(1915,'es','tutorial_disclaimer_deletepage','Esta acción no podrá deshacerse. ¿Estás seguro de que quieres borrar esta página?');
insert into message(id,locale,code,description) values(1916,'es','tutorial_create_namerequired','Debes indicar el nombre');
insert into message(id,locale,code,description) values(1917,'es','tutorial_edit_pageid_exists','El ID de la página ya está en uso');
insert into message(id,locale,code,description) values(1918,'es','tutorial_edit_pageid_equals1','La página 1 no puede ser eliminada');
insert into message(id,locale,code,description) values(1919,'es','tutorial_edit_pageid_noempty','Debes introducir un ID');
insert into message(id,locale,code,description) values(1920,'es','tutorial_edit_tutorialid_exists','El nombre del tutorial ya está en uso');
insert into message(id,locale,code,description) values(1921,'es','tutorial_edit_tutorialid_noempty','Debes introducir un nombre');
insert into message(id,locale,code,description) values(1922,'es','tutorial_edit_labelrequired','Debes introducir un valor para etiqueta');
insert into message(id,locale,code,description) values(1923,'es','tutorial_edit_actionrequired','Debes seleccionar una acción');
insert into message(id,locale,code,description) values(1924,'es','tutorial_edit_deletebuttonineditor','Eliminar botón');
insert into message(id,locale,code,description) values(1925,'es','tutorial_edit_disclaimdeletebutton','(El cambio no se guardará)');
insert into message(id,locale,code,description) values(1926,'es','tutorial_edit_predisclaim','Los cambios en la página (contenido y botones) no se guardaran ¿Estas seguro de que quieres');
insert into message(id,locale,code,description) values(1927,'es','tutorial_edit_disclaimchangepage','cambiar de página?');
insert into message(id,locale,code,description) values(1928,'es','tutorial_edit_disclaimnewpage','crear una nueva página?');
insert into message(id,locale,code,description) values(1929,'es','tutorial_edit_disclaimexiteditor','salir del editor?');
insert into message(id,locale,code,description) values(1930,'es','tutorial_edit_image_toobig','La imagen pesa demasiado (máximo 1 MB)');
insert into message(id,locale,code,description) values(1931,'es','tutorial_edit_image_invalidtype','Solo son válidos los formatos de imagen jpeg, png y gif');
insert into message(id,locale,code,description) values(1932,'es','tutorial_edit_pagesaved','Página guardada');
insert into message(id,locale,code,description) values(1933,'es','tutorial_edit_contenttoobig','El tutorial excede los 32MB permitidos');
insert into message(id,locale,code,description) values(1934,'es','multimedia_name_required','Valor de nombre requerido');
insert into message(id,locale,code,description) values(1935,'es','multimedia_contenttype_required','Valor de tipo de contenido requerido');
insert into message(id,locale,code,description) values(1936,'es','multimedia_name_exits','Ya existe el nombre introducido');
insert into message(id,locale,code,description) values(1937,'es','multimedia_name_limit','El nombre excede el tamaño máximo permitido');
insert into message(id,locale,code,description) values(1938,'es','multimedia_add_ok','Elemento dado de alta');
insert into message(id,locale,code,description) values(1939,'es','multimedia_add_ok_log','Elemento {0} dado de alta por {1}');
insert into message(id,locale,code,description) values(1940,'es','multimedia_delete_ok','Elemento eliminado');
insert into message(id,locale,code,description) values(1941,'es','multimedia_delete_ok_log','Elemento {0} eliminado por {1}');
insert into message(id,locale,code,description) values(1942,'es','multimedia_update_ok','Elemento modificado');
insert into message(id,locale,code,description) values(1943,'es','multimedia_update_ok_log','Elemento {0} modificado por {1}');
insert into message(id,locale,code,description) values(1944,'es','multimedia_upload_format','El fichero no presenta el formato esperado');
insert into message(id,locale,code,description) values(1945,'es','multimedia_upload_file_exist','El Fichero No Existe o su Tamaño es 0');
insert into message(id,locale,code,description) values(1946,'es','multimedia_upload_dir_exist','El Directorio Local No Existe');
insert into message(id,locale,code,description) values(1947,'es','multimedia_download_exist','El fichero no existe');
insert into message(id,locale,code,description) values(1948,'es','polinex_name_required','Valor de nombre requerido');
insert into message(id,locale,code,description) values(1949,'es','polinex_type_required','Valor de tipo requerido');
insert into message(id,locale,code,description) values(1950,'es','polinex_mac_required','Fichero de MACs requerido');
insert into message(id,locale,code,description) values(1951,'es','polinex_name_exits','Ya existe el nombre introducido');
insert into message(id,locale,code,description) values(1952,'es','polinex_type_exits','Ya existe el tipo introducido');
insert into message(id,locale,code,description) values(1953,'es','polinex_add_ok','Listado de MACs dado de alta');
insert into message(id,locale,code,description) values(1954,'es','polinex_add_ok_log','Listado de MACs {0} dado de alta por {1}');
insert into message(id,locale,code,description) values(1955,'es','polinex_delete_ok','Listado de MACs eliminado');
insert into message(id,locale,code,description) values(1956,'es','polinex_delete_ok_log','Listado de MACs {0} eliminado por {1}');
insert into message(id,locale,code,description) values(1957,'es','polinex_update_ok','Listado de MACs modificado');
insert into message(id,locale,code,description) values(1958,'es','polinex_update_ok_log','Listado de MACs {0} modificado por {1}');
insert into message(id,locale,code,description) values(1959,'es','polinex_upload_format','El fichero no presenta el formato esperado');
insert into message(id,locale,code,description) values(1960,'es','polinex_upload_exist','El Directorio Local No Existe, el Fichero No Existe o su Tamaño es 0');
insert into message(id,locale,code,description) values(1961,'es','polinex_download_exist','El fichero no existe');
insert into message(id,locale,code,description) values(1962,'es','template_name_required','Valor de fichero requerido');
insert into message(id,locale,code,description) values(1963,'es','template_name_exits','Ya existe el fichero introducido');
insert into message(id,locale,code,description) values(1964,'es','template_name_limit','El nombre excede el tamaño máximo permitido');
insert into message(id,locale,code,description) values(1965,'es','template_add_ok','Plantilla dada de alta');
insert into message(id,locale,code,description) values(1966,'es','template_add_ok_all','{0} plantillas dadas de alta');
insert into message(id,locale,code,description) values(1967,'es','template_add_ok_log','Plantilla {0} dada de alta por {1}');
insert into message(id,locale,code,description) values(1968,'es','template_delete_ok','Plantilla eliminada');
insert into message(id,locale,code,description) values(1969,'es','template_delete_ok_all','{0} plantillas eliminadas');
insert into message(id,locale,code,description) values(1970,'es','template_delete_ok_log','Plantilla {0} eliminada por {1}');
insert into message(id,locale,code,description) values(1971,'es','template_update_ok','Plantilla modificada');
insert into message(id,locale,code,description) values(1972,'es','template_update_ok_log','Plantilla {0} modificada por {1}');
insert into message(id,locale,code,description) values(1973,'es','template_upload_format','El fichero no presenta el formato esperado');
insert into message(id,locale,code,description) values(1974,'es','template_upload_error','Error en la carga de la plantilla. IntÉntelo de nuevo.');
insert into message(id,locale,code,description) values(1975,'es','template_upload_file_exist','El Fichero No Existe o su Tamaño es 0');
insert into message(id,locale,code,description) values(1976,'es','template_upload_dir_exist','El Directorio Local No Existe');
insert into message(id,locale,code,description) values(1977,'es','template_download_exist','El fichero no existe');
insert into message(id,locale,code,description) values(1978,'es','template_empty','No existen plantillas');
insert into message(id,locale,code,description) values(1979,'es','scene_file_required','Valor de fichero requerido');
insert into message(id,locale,code,description) values(1980,'es','scene_file_exits','Ya existe el fichero introducido');
insert into message(id,locale,code,description) values(1981,'es','scene_file_limit','El nombre excede el tamaño máximo permitido');
insert into message(id,locale,code,description) values(1982,'es','scene_file_format','{0} Formato invalido');
insert into message(id,locale,code,description) values(1983,'es','scene_service_required','Valor de servicio requerido');
insert into message(id,locale,code,description) values(1984,'es','scene_service_exits','Ya existe el servicio introducido');
insert into message(id,locale,code,description) values(1985,'es','scene_service_limit','El nombre servicio el tamaño máximo permitido');
insert into message(id,locale,code,description) values(1986,'es','scene_add_ok','Escenario dado de alta');
insert into message(id,locale,code,description) values(1987,'es','scene_add_ok_all','{0} escenarios añdidos');
insert into message(id,locale,code,description) values(1988,'es','scene_add_ok_log','Escenario {0} dado de alta por {1}');
insert into message(id,locale,code,description) values(1989,'es','scene_delete_ok','Escenario eliminado');
insert into message(id,locale,code,description) values(1990,'es','scene_delete_ok_all','{0} escenarios eliminadas');
insert into message(id,locale,code,description) values(1991,'es','scene_delete_ok_log','Escenario {0} eliminado por {1}');
insert into message(id,locale,code,description) values(1992,'es','scene_update_ok','Escenario modificado');
insert into message(id,locale,code,description) values(1993,'es','scene_update_ok_log','Escenario {0} modificado por {1}');
insert into message(id,locale,code,description) values(1994,'es','scene_upload_recoverscene','Problema recuperando el escenario');
insert into message(id,locale,code,description) values(1995,'es','scene_reset_sueccess','Escenario reseteado');
insert into message(id,locale,code,description) values(1996,'es','scene_reset_error','Error reseteando el escenario');
insert into message(id,locale,code,description) values(1997,'es','scene_resetall_error','Error reseteando el escenario {0}');
insert into message(id,locale,code,description) values(1998,'es','scene_resetall_successreport','{0} escenarios reseteados');
insert into message(id,locale,code,description) values(1999,'es','scene_upload_format','El fichero no presenta el formato esperado');
insert into message(id,locale,code,description) values(2000,'es','scene_upload_exist','El Directorio Local No Existe, el Fichero No Existe o su Tamaño es 0');
insert into message(id,locale,code,description) values(2001,'es','scene_download_exist','El fichero no existe');
insert into message(id,locale,code,description) values(2002,'es','polunitary_name_required','Valor de nombre requerido');
insert into message(id,locale,code,description) values(2003,'es','polunitary_frwobjetive_required','Valor de firmware objetivo requerido');
insert into message(id,locale,code,description) values(2004,'es','polunitary_mac_required','Valor de MAC requerido');
insert into message(id,locale,code,description) values(2005,'es','polunitary_mac_format','Formato de MAC incorrecto');
insert into message(id,locale,code,description) values(2006,'es','polunitary_name_exits','Ya existe el nombre introducido');
insert into message(id,locale,code,description) values(2007,'es','polunitary_type_exits','Ya existe el tipo introducido');
insert into message(id,locale,code,description) values(2008,'es','polunitary_add_ok','Política unitaria dada de alta');
insert into message(id,locale,code,description) values(2009,'es','polunitary_add_ok_log','Política unitaria {0} dada de alta por {1}');
insert into message(id,locale,code,description) values(2010,'es','polunitary_delete_ok','Política unitaria eliminada');
insert into message(id,locale,code,description) values(2011,'es','polunitary_delete_ok_log','Política unitaria {0} eliminada por {1}');
insert into message(id,locale,code,description) values(2012,'es','polunitary_update_ok','Política unitaria modificada');
insert into message(id,locale,code,description) values(2013,'es','polunitary_update_ok_log','Política unitaria {0} modificado por {1}');
insert into message(id,locale,code,description) values(2014,'es','polunitary_upgrade_ok','Proceso de actualización ejecutado correctamente');
insert into message(id,locale,code,description) values(2015,'es','polunitary_upgrade_ok_log','El proceso {0} de actualización ha sido ejecutado correctamente por {1}');
insert into message(id,locale,code,description) values(2016,'es','polunitary_identify_ok','Proceso de identificación ejecutado correctamente');
insert into message(id,locale,code,description) values(2017,'es','polunitary_identify_ok_log','El proceso {0} de identificación ha sido ejecutado correctamente por {1}');
insert into message(id,locale,code,description) values(2018,'es','polmassive_name_exits','Ya existe la política introducida');
insert into message(id,locale,code,description) values(2019,'es','polmassive_name_required','Valor de política requerido');
insert into message(id,locale,code,description) values(2020,'es','polmassive_state_required','Valor de estado requerido');
insert into message(id,locale,code,description) values(2021,'es','polmassive_priority_required','Valor de prioridad requerido');
insert into message(id,locale,code,description) values(2022,'es','polmassive_bydefault_required','Valor de valor por defecto requerido');
insert into message(id,locale,code,description) values(2023,'es','polmassive_exception_required','Valor de excepción requerido');
insert into message(id,locale,code,description) values(2024,'es','polmassive_mininit_required','Valor de inicio de ventana requerido');
insert into message(id,locale,code,description) values(2025,'es','polmassive_minend_required','Valor de fin de ventana requerido');
insert into message(id,locale,code,description) values(2026,'es','polmassive_manufacturer_required','Valor de fabricante requerido');
insert into message(id,locale,code,description) values(2027,'es','polmassive_model_required','Valor de modelo requerido');
insert into message(id,locale,code,description) values(2028,'es','polmassive_access_required','Valor de equipos de acceso requerido');
insert into message(id,locale,code,description) values(2029,'es','polmassive_frworigin_required','Valor de versiones firmware iniciales requerido');
insert into message(id,locale,code,description) values(2030,'es','polmassive_frwobjetive_required','Valor de fichero firmware objetivo requerido');
insert into message(id,locale,code,description) values(2031,'es','polmassive_frwobjetivever_required','Valor de versión firmware objetivo requerido');
insert into message(id,locale,code,description) values(2032,'es','polmassive_add_ok','Política masiva dada de alta');
insert into message(id,locale,code,description) values(2033,'es','polmassive_add_ok_log','Política masiva {0} dada de alta por {1}');
insert into message(id,locale,code,description) values(2034,'es','polmassive_delete_ok','Política masiva eliminada');
insert into message(id,locale,code,description) values(2035,'es','polmassive_delete_ok_log','Política masiva {0} eliminada por {1}');
insert into message(id,locale,code,description) values(2036,'es','polmassive_update_ok','Política masiva modificada');
insert into message(id,locale,code,description) values(2037,'es','polmassive_update_ok_log','Política masiva {0} modificado por {1}');
insert into message(id,locale,code,description) values(2038,'es','polmassive_changestate_active_ok','Política masiva activada');
insert into message(id,locale,code,description) values(2039,'es','polmassive_changestate_inactive_ok','Política masiva desactivada');
insert into message(id,locale,code,description) values(2040,'es','polmassive_changestate_active_ok_log','Política masiva {0} activada por {1}');
insert into message(id,locale,code,description) values(2041,'es','polmassive_changestate_inactive_ok_log','Política masiva {0} desactivada por {1}');
insert into message(id,locale,code,description) values(2042,'es','polbase_name_exits','Ya existe la política introducida');
insert into message(id,locale,code,description) values(2043,'es','polbase_name_required','Valor de política requerido');
insert into message(id,locale,code,description) values(2044,'es','polbase_state_required','Valor de estado requerido');
insert into message(id,locale,code,description) values(2045,'es','polbase_priority_required','Valor de prioridad requerido');
insert into message(id,locale,code,description) values(2046,'es','polbase_bydefault_required','Valor de valor por defecto requerido');
insert into message(id,locale,code,description) values(2047,'es','polbase_exception_required','Valor de excepción requerido');
insert into message(id,locale,code,description) values(2048,'es','polbase_mininit_required','Valor de inicio de ventana requerido');
insert into message(id,locale,code,description) values(2049,'es','polbase_minend_required','Valor de fin de ventana requerido');
insert into message(id,locale,code,description) values(2050,'es','polbase_manufacturer_required','Valor de fabricante requerido');
insert into message(id,locale,code,description) values(2051,'es','polbase_model_required','Valor de modelo requerido');
insert into message(id,locale,code,description) values(2052,'es','polbase_access_required','Valor de equipos de acceso requerido');
insert into message(id,locale,code,description) values(2053,'es','polbase_frworigin_required','Valor de versiones firmware iniciales requerido');
insert into message(id,locale,code,description) values(2054,'es','polbase_frwobjetive_required','Valor de fichero firmware objetivo requerido');
insert into message(id,locale,code,description) values(2055,'es','polbase_frwobjetivever_required','Valor de versión firmware objetivo requerido');
insert into message(id,locale,code,description) values(2056,'es','polbase_add_ok','Políticas base dada de alta');
insert into message(id,locale,code,description) values(2057,'es','polbase_add_ok_log','Políticas base {0} dada de alta por {1}');
insert into message(id,locale,code,description) values(2058,'es','polbase_delete_ok','Políticas base eliminada');
insert into message(id,locale,code,description) values(2059,'es','polbase_delete_ok_log','Políticas base {0} eliminada por {1}');
insert into message(id,locale,code,description) values(2060,'es','polbase_update_ok','Políticas base modificada');
insert into message(id,locale,code,description) values(2061,'es','polbase_update_ok_log','Políticas base {0} modificado por {1}');
insert into message(id,locale,code,description) values(2062,'es','polbase_changestate_active_ok','Políticas base activada');
insert into message(id,locale,code,description) values(2063,'es','polbase_changestate_inactive_ok','Políticas base desactivada');
insert into message(id,locale,code,description) values(2064,'es','polbase_changestate_active_ok_log','Políticas base {0} activada por {1}');
insert into message(id,locale,code,description) values(2065,'es','polbase_changestate_inactive_ok_log','Políticas base {0} desactivada por {1}');
insert into message(id,locale,code,description) values(2066,'es','server_license_error','Licencia Bloqueada. Schaman no puede Arrancar');
insert into message(id,locale,code,description) values(2067,'es','server_upload_dir_exist','El directorio {0} no existe');
insert into message(id,locale,code,description) values(2068,'es','server_upload_file_exist','El Fichero No Existe o su Tamaño es 0');
insert into message(id,locale,code,description) values(2069,'es','sever_upload_format','Se espera un fichero con extensión .CSV o .CSV.GZ');
insert into message(id,locale,code,description) values(2070,'es','sever_synchro_ok','El proceso de sincronización RDU a terminado.');
insert into message(id,locale,code,description) values(2071,'es','configuration_update_error','El valor no se ha podido actualizar');
insert into message(id,locale,code,description) values(2072,'es','configuration_update_license_error','La licencia introducida no es correcta');
insert into message(id,locale,code,description) values(2073,'es','configuration_delete_exist','No se ha encontrado el archivo de configuración');
insert into message(id,locale,code,description) values(2074,'es','configuration_missing','No se ha encontrado la configuración');
insert into message(id,locale,code,description) values(2075,'es','debug_location_request','La petición se ha enviado');
insert into message(id,locale,code,description) values(2076,'es','wmsg_start_required','Comienzo de la ventana requerido');
insert into message(id,locale,code,description) values(2077,'es','wmsg_finish_required','Finalización de la ventana requerido');
insert into message(id,locale,code,description) values(2078,'es','wmsg_message_required','Mensaje requerido');
insert into message(id,locale,code,description) values(2079,'es','wmsg_type_required','Tipo de mensaje de aviso requerido');
insert into message(id,locale,code,description) values(2080,'es','wmsg_start_before','El campo comienzo debe ser anterior al campo fin');
insert into message(id,locale,code,description) values(2081,'es','wmsg_overlap','No se pueden solapar ventanas temporales');
insert into message(id,locale,code,description) values(2082,'es','wmsg_msg_maxlen','El campo mensaje ha superado el tamaño máximo (1000)');
insert into message(id,locale,code,description) values(2083,'es','wmsg_add_ok_log','Mensaje de aviso {0} creado por {1}');
insert into message(id,locale,code,description) values(2084,'es','wmsg_add_ok','Mensaje de aviso creado');
insert into message(id,locale,code,description) values(2085,'es','wmsg_edit_ok','Mensaje de aviso modificado');
insert into message(id,locale,code,description) values(2086,'es','wmsg_edit_ok_log','Mensaje de aviso {0} modificado por {1}');
insert into message(id,locale,code,description) values(2087,'es','wmsg_delete_ok','Mensaje de aviso eliminado');
insert into message(id,locale,code,description) values(2088,'es','wmsg_delete_ok_log','Mensaje de aviso {0} eliminado por {1}');
insert into message(id,locale,code,description) values(2089,'es','lookupfile_key_required','Valor de nombre requerido');
insert into message(id,locale,code,description) values(2090,'es','lookupfile_content_required','Valor de contenido requerido');
insert into message(id,locale,code,description) values(2091,'es','lookupfile_key_exits','Ya existe el nombre introducido');
insert into message(id,locale,code,description) values(2092,'es','lookupfile_key_limit','El nombre excede el tamaño máximo permitido');
insert into message(id,locale,code,description) values(2093,'es','lookupfile_add_ok','Fichero dado de alta');
insert into message(id,locale,code,description) values(2094,'es','lookupfile_add_ok_log','Fichero {0} dado de alta por {1}');
insert into message(id,locale,code,description) values(2095,'es','lookupfile_delete_ok','Fichero eliminado');
insert into message(id,locale,code,description) values(2096,'es','lookupfile_delete_ok_log','Fichero {0} eliminado por {1}');
insert into message(id,locale,code,description) values(2097,'es','lookupfile_update_ok','Fichero modificado');
insert into message(id,locale,code,description) values(2098,'es','lookupfile_update_ok_log','Fichero {0} modificado por {1}');
insert into message(id,locale,code,description) values(2099,'es','lookupfile_upload_file_exist','El Fichero No Existe o su Tamaño es 0');
insert into message(id,locale,code,description) values(2100,'es','lookupfile_upload_dir_exist','El Directorio Local No Existe');
insert into message(id,locale,code,description) values(2101,'es','lookupfile_download_exist','El fichero no existe');
insert into message(id,locale,code,description) values(2102,'es','notif_error_invalidregistration','Token de registro no válido');
insert into message(id,locale,code,description) values(2103,'es','notif_error_notregistered','Dispositivo no registrado');
insert into message(id,locale,code,description) values(2104,'es','notif_error_missingregistration','Falta el token de registro');
insert into message(id,locale,code,description) values(2105,'es','notif_error_invalidpackageName','Nombre de paquete no válido');
insert into message(id,locale,code,description) values(2106,'es','notif_error_mismatchsenderid','El emisor no coincide');
insert into message(id,locale,code,description) values(2107,'es','notif_error_unavailable','Tiempo de espera agotado');
insert into message(id,locale,code,description) values(2108,'es','notif_error_internalservererror','Error de servidor interno');
insert into message(id,locale,code,description) values(2109,'es','notif_error_invalidapnscredential','Credenciales de APN no válidas');
insert into message(id,locale,code,description) values(2110,'es','notif_error_badrequest','Solicitud incorrecta');
insert into message(id,locale,code,description) values(2111,'es','dashboard_filter_dateIni_required','Fecha inicial requerida');
insert into message(id,locale,code,description) values(2112,'es','dashboard_filter_dateEnd_required','Fecha final requerida');
insert into message(id,locale,code,description) values(2113,'es','polproactive_add_ok','Política proactiva dada de alta');
insert into message(id,locale,code,description) values(2114,'es','polproactive_add_ok_log','Política proactiva {0} dada de alta por {1}');
insert into message(id,locale,code,description) values(2115,'es','polproactive_delete_ok','Política proactiva eliminada');
insert into message(id,locale,code,description) values(2116,'es','polproactive_delete_ok_log','Política proactiva {0} eliminada por {1}');
insert into message(id,locale,code,description) values(2117,'es','polproactive_update_ok','Política proactivae modificada');
insert into message(id,locale,code,description) values(2118,'es','polproactive_update_ok_log','Política proactiva {0} modificado por {1}');
insert into message(id,locale,code,description) values(2119,'es','polproactive_name_required','Valor de nombre requerido');
insert into message(id,locale,code,description) values(2120,'es','polproactive_name_exist','El nombre introducido ya existe');
insert into message(id,locale,code,description) values(2121,'es','polproactive_triggertype_required','Valor de Tipo de Disparador requerido');
insert into message(id,locale,code,description) values(2122,'es','polproactive_alarmId_required','Valor de Alarm Id requerido');
insert into message(id,locale,code,description) values(2123,'es','polproactive_inputType_required','Valor de Input Type requerido');
insert into message(id,locale,code,description) values(2124,'es','polproactive_inputValue_required','Valor de Input Value requerido');
insert into message(id,locale,code,description) values(2125,'es','polproactive_ticketorigin_required','Valor de Origen del Ticket requerido');
insert into message(id,locale,code,description) values(2126,'es','polproactive_currentstatus_required','Valor de Estado Actual requerido');
insert into message(id,locale,code,description) values(2127,'es','polproactive_previousstatus_required','Valor de Estado Previo requerido');
insert into message(id,locale,code,description) values(2128,'es','polproactive_delay_required','Valor de Retarde requerido');
insert into message(id,locale,code,description) values(2129,'es','polproactive_policyId_required','Valor de Policy Id requerido');
insert into message(id,locale,code,description) values(2130,'es','polproactive_executortype_required','Valor de Tipo de Ejecutor requerido');
insert into message(id,locale,code,description) values(2131,'es','polproactive_notificatortype_required','Valor de Notificador requerido');
insert into message(id,locale,code,description) values(2132,'es','polproactive_panelId_required','Valor de Panel ID requerido');
insert into message(id,locale,code,description) values(2133,'es','polproactive_text_required','Valor de Texto requerido');
insert into message(id,locale,code,description) values(2134,'es','polproactive_description_required','Valor de Description requerido');
insert into message(id,locale,code,description) values(2135,'es','polproactive_logic_required','Valor de Logic requerido');
insert into message(id,locale,code,description) values(2136,'es','polproactive_detail_required','Valor de Detalle requerido');
insert into message(id,locale,code,description) values(2137,'es','polproactive_issue_required','Valor de Asunto requerido');
insert into message(id,locale,code,description) values(2138,'es','polproactive_select_required','Valor de Selector requerido');
insert into message(id,locale,code,description) values(2139,'es','polproactive_title_required','Valor de Título requerido');
insert into message(id,locale,code,description) values(2140,'es','polproactive_message_required','Valor de Mensaje requerido');
insert into message(id,locale,code,description) values(2141,'es','polproactive_deeplink_required','Valor de DeepLink requerido');
insert into message(id,locale,code,description) values(2142,'es','polproactive_tag_required','Valor de TAG requerido');
insert into message(id,locale,code,description) values(2143,'es','polproactive_schedule_required','Valor de Horario requerido');
insert into message(id,locale,code,description) values(2144,'es','polproactive_dateini_required','Valor de Fecha de inicio requerido');
insert into message(id,locale,code,description) values(2145,'es','polproactive_dateend_required','Valor de Fecha de fin requerido');
insert into message(id,locale,code,description) values(2146,'es','polproactive_resources_required','Valor de Asignación de recursos (%) requerido');
insert into message(id,locale,code,description) values(2147,'es','polproactive_weekdays_required','Valor de Días de la semana requerido');
insert into message(id,locale,code,description) values(2148,'es','polproactive_starttime_required','Valor de Hora de inicio requerido');
insert into message(id,locale,code,description) values(2149,'es','polproactive_endtime_required','Valor de Hora de fin requerido');
insert into message(id,locale,code,description) values(2150,'es','polproactive_file_exist','Valor de fichero requerido');
insert into message(id,locale,code,description) values(2151,'es','polproactive_time_before','La hora de inicio debe ser anterior a la hora de fin');
insert into message(id,locale,code,description) values(2152,'es','polproactive_date_before','La fecha de inicio debe ser anterior a la fecha de fin');
insert into message(id,locale,code,description) values(2153,'es','polproactive_notificators_exist','Debe registrar al menos un notificador');
insert into message(id,locale,code,description) values(2154,'es','trb_template_login','No existe la plantilla LOGIN');
insert into message(id,locale,code,description) values(2155,'es','service_client_exits','Ya existe el cliente introducido');
insert into message(id,locale,code,description) values(2156,'es','scene_empty','No existen escenarios');
insert into message(id,locale,code,description) values(2157,'es','polproactive_endtim_requirede','Valor de Hora de finalización requerido');
insert into message(id,locale,code,description) values(2158,'es','users_table_firstname','Nombre');
insert into message(id,locale,code,description) values(2159,'es','users_table_lastname','Apellidos');
insert into message(id,locale,code,description) values(2160,'es','users_table_email','Correo');
insert into message(id,locale,code,description) values(2161,'es','users_table_high_date','Fecha Alta');
insert into message(id,locale,code,description) values(2162,'es','users_table_low_date','Fecha Baja');
insert into message(id,locale,code,description) values(2163,'es','users_table_active','Activo?');
insert into message(id,locale,code,description) values(2164,'es','users_firstname','Nombre');
insert into message(id,locale,code,description) values(2165,'es','users_lastname','Apellidos');
insert into message(id,locale,code,description) values(2166,'es','users_email','Correo');
insert into message(id,locale,code,description) values(2167,'es','users_firstname_empty','El Nombre no puede estar vacío.');
insert into message(id,locale,code,description) values(2168,'es','users_lastname_empty','Los Apellidos no pueden estar vacíos.');
insert into message(id,locale,code,description) values(2169,'es','users_email_empty','El correo no puede estar vacío.');
insert into message(id,locale,code,description) values(2170,'es','users_email_error_format','El correo no posee un formato correcto.');
insert into message(id,locale,code,description) values(2171,'es','users_rol_not_select','Debe seleccionar un Rol');
insert into message(id,locale,code,description) values(2172,'es','users_firstname_required','El Nombre es requerido');
insert into message(id,locale,code,description) values(2173,'es','users_lastname_required','Los Apellidos son requeridos.');
insert into message(id,locale,code,description) values(2174,'es','users_email_required','El Email es requerido.');
insert into message(id,locale,code,description) values(2175,'es','roles_table_title','Listado de Roles');
insert into message(id,locale,code,description) values(2176,'es','roles_table_name','Rol');
insert into message(id,locale,code,description) values(2177,'es','roles_name_required','El Nombre es requerido');
insert into message(id,locale,code,description) values(2178,'es','button_access','Accesos');
insert into message(id,locale,code,description) values(2179,'es','roles_title_access','Accesos por Rol');
insert into message(id,locale,code,description) values(2180,'es','roles_title_add','Alta de Rol');
insert into message(id,locale,code,description) values(2181,'es','access_code','Código');
insert into message(id,locale,code,description) values(2182,'es','access_description','Descripción');
insert into message(id,locale,code,description) values(2183,'es','access_icon','Ícono');
insert into message(id,locale,code,description) values(2184,'es','access_active','Activo?');
insert into message(id,locale,code,description) values(2185,'es','access_code_required','Código requerido.');
insert into message(id,locale,code,description) values(2186,'es','access_parent_required','Acceso Padre requerido.');
insert into message(id,locale,code,description) values(2187,'es','access_description_required','Descripción requerida');
insert into message(id,locale,code,description) values(2188,'es','access_table_title','Listado de Accesos');
insert into message(id,locale,code,description) values(2189,'es','access_table_code','Código');
insert into message(id,locale,code,description) values(2190,'es','access_table_description','Descripción');
insert into message(id,locale,code,description) values(2191,'es','access_table_icon','Ícono');
insert into message(id,locale,code,description) values(2192,'es','access_table_parent','Menú Padre');
insert into message(id,locale,code,description) values(2193,'es','access_title','Lista de Accesos');
insert into message(id,locale,code,description) values(2194,'es','access_title_detail','Detalle Acceso');
insert into message(id,locale,code,description) values(2195,'es','access_title_edit','Editar Acceso');
insert into message(id,locale,code,description) values(2196,'es','access_title_add','Agregar Acceso');
insert into message(id,locale,code,description) values(2197,'es','access_parent','Menú Padre');
insert into message(id,locale,code,description) values(2198,'es','access_code_empty','Código no puede estar vacío.');
insert into message(id,locale,code,description) values(2199,'es','access_description_empty','Descripción no puede estar vacío.');
insert into message(id,locale,code,description) values(2200,'es','access_creation_success','Acceso creado exitósamente.');
insert into message(id,locale,code,description) values(2201,'es','access_nameexits','Código de acceso ya existe');
insert into message(id,locale,code,description) values(2202,'es','access_edit_success','Acceso editado exitósamente.');
insert into message(id,locale,code,description) values(2203,'es','access_delete_success','Acceso eliminado exitósamente.');
insert into message(id,locale,code,description) values(2204,'es','configuration_revisar','Datos a Revisar');
insert into message(id,locale,code,description) values(2205,'es','users_client','Cliente');
insert into message(id,locale,code,description) values(2206,'es','users_table_client','Cliente');
insert into message(id,locale,code,description) values(2207,'es','client_file_select','Seleccionar');
insert into message(id,locale,code,description) values(2208,'es','validation_client_not_config','Cliente no posee configuración asociada.');
insert into message(id,locale,code,description) values(2209,'es','configuration_update_success','Se ha actualizado la configuración.');
insert into message(id,locale,code,description) values(2210,'es','category_title','Gestión de Categorías');
insert into message(id,locale,code,description) values(2211,'es','category_title_add','Agregar Categoria');
insert into message(id,locale,code,description) values(2212,'es','category_title_edit','Editar Categoría');
insert into message(id,locale,code,description) values(2213,'es','category_title_detail','Detalle Categoría');
insert into message(id,locale,code,description) values(2214,'es','category_title_config','Gestión de Configuración de Categoría {0}');
insert into message(id,locale,code,description) values(2215,'es','manufacturer_manufacturer_exits','Ya existe el fabricante introducido');


-- Insert Categories
INSERT INTO category(id,icon,name,show_server) VALUES(1,'settings','schaman',true);
INSERT INTO category(id,icon,name,show_server) VALUES(2,'security','licence',true);
INSERT INTO category(id,icon,name,show_server) VALUES(3,'bug_report','troubleshoot',false);
INSERT INTO category(id,icon,name,show_server) VALUES(4,'compare_arrows','acs',true);
INSERT INTO category(id,icon,name,show_server) VALUES(5,'phone_iphone','oneclick',false);
INSERT INTO category(id,icon,name,show_server) VALUES(6,'dehaze','cleaning_database',false);
INSERT INTO category(id,icon,name,show_server) VALUES(7,'report','log',false);
INSERT INTO category(id,icon,name,show_server) VALUES(8,'settings','blank',false);

-- Insert Configuration Default
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(1,'autoStart','true','boolean','',now(),now(),NULL,1);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(2,'icmpType','java','list','icmp',now(),now(),NULL,1);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(3,'language','es_ES','list','language',now(),now(),NULL,1);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(4,'license','50685968337-96B72313230-65657773563-77570386467-4A526459593-66958566746-6D4F63464D5-950354C733D','text','',now(),now(),NULL,2);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(5,'troubleshootActive','false','boolean','',now(),now(),NULL,3);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(6,'progressTMax','150','text','',now(),now(),NULL,3);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(7,'progressTExp','40','text','',now(),now(),NULL,3);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(8,'progressExp','80','text','',now(),now(),NULL,3);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(9,'progressTEnd','0','text','',now(),now(),NULL,3);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(15,'ockActive','false','boolean','',now(),now(),NULL,5);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(16,'ockSessionTimeout','900000','list','60000:1 minuto;120000:2 minutos;300000:5 minutos;600000:10 minutos;900000:15 minutos;1200000:20 minutos;1800000:30 minutos',now(),now(),NULL,5);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(18,'ockBotoneraIconsURL','http://localhost:8080/Schaman-nbi/rest/OCFileWs/buttonbar','text','',now(),now(),NULL,5);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(19,'ockAndroidApiKey','','text','',now(),now(),NULL,5);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(20,'ockIOSApiKey','','text','',now(),now(),NULL,5);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(21,'ockLinkedNotifTTL','120','text','',now(),now(),NULL,5);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(22,'ockLinkedTimeout','60','number','',now(),now(),NULL,5);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(23,'databaseCleanerActive','false','boolean','',now(),now(),NULL,6);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(24,'databaseCleanerTime','0','time','',now(),now(),NULL,6);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(25,'databaseCleanerThreshold','30','list','1:1 Día;2:2 Días;5:5 Días;7:7 Días;10:10 Días;15:15 Días;20:20 Días;30:30 Días;40:40 Días;61:2 Meses;91:3 Meses;183:6 Meses;365:1 Año;731:2 Años',now(),now(),NULL,6);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(26,'databaseCleanerCliTime','0','time','',now(),now(),NULL,6);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(27,'databaseCleanerCliThreshold','365','list','1:1 Día;2:2 Días;5:5 Días;7:7 Días;10:10 Días;15:15 Días;20:20 Días;30:30 Días;40:40 Días;61:2 Meses;91:3 Meses;183:6 Meses;365:1 Año;731:2 Años',now(),now(),NULL,6);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(28,'databaseCleanerWrnTime','0','time','',now(),now(),NULL,6);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(29,'logFile','/var/log/schaman/schaman.log','text','',now(),now(),NULL,7);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(30,'ockLoginTimeout','10','text','',now(),now(),NULL,8);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(31,'ockBotoneraURL','http://localhost:8080/Schaman-nbi/rest/OCButtonBarWs','text','',now(),now(),NULL,8);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(32,'ockBotoneraDown','always','list','wifi',now(),now(),NULL,8);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(33,'ockTutorialesVer','1','number','',now(),now(),NULL,8);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(34,'ockTutorialesURL','http://localhost:8080/Schaman-nbi/rest/OCFileWs/tutorials/1/tutorials.zip','text','',now(),now(),NULL,8);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(35,'ockTutorialesDown','always','list','wifi',now(),now(),NULL,8);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(36,'ockXmlApnsVer','1','number','',now(),now(),NULL,8);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(37,'ockXmlApnsURL','hhttp://localhost:8080/Schaman-nbi/rest/OCFileWs/apns/1/apns.xml','text','',now(),now(),NULL,8);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(38,'ockXmlApnsDown','always','list','wifi',now(),now(),NULL,8);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(39,'ockXmlMasterVer','1','number','',now(),now(),NULL,8);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(40,'ockXmlMasterURL','http://localhost:8080/Schaman-nbi/rest/OCFileWs/master/1/master.xml','text','',now(),now(),NULL,8);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(41,'ockXmlMasterDown','always','list','wifi',now(),now(),NULL,8);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(42,'ockXmlDeviceVer','1','number','',now(),now(),NULL,8);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(43,'ockXmlDeviceURL','http://localhost:8080/Schaman-nbi/rest/OCFileWs/device/1/device.xml','text','',now(),now(),NULL,8);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(44,'ockXmlDeviceDown','always','list','wifi',now(),now(),NULL,8);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(45,'ockOoklaServersVer','1','number','',now(),now(),NULL,8);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(46,'ockOoklaServersURL','http://localhost:8080/Schaman-nbi/rest/OCFileWs/ookla/1/servers.xml','text','',now(),now(),NULL,8);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(47,'ockOoklaServersDown','always','list','wifi',now(),now(),NULL,8);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(48,'ockSamsungKey','','text','',now(),now(),NULL,8);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(118,'acsProvActive','true','boolean','',now(),now(),NULL,4);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(119,'acsURL','http://192.168.30.17:8080/acs','text','',now(),now(),NULL,4);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(120,'acsPassword','L1WBXBdc5VrBu4ZhVOKIbQ==','secret','',now(),now(),NULL,4);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(121,'acsInformInterval','28800','text','',now(),now(),NULL,4);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(122,'acsProvCode','schaman','text','',now(),now(),NULL,4);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(123,'xmppServerIp','192.168.30.18','text','',now(),now(),NULL,4);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(124,'xmppServerPort','5222','number','',now(),now(),NULL,4);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(125,'xmppServerDomain','xmpp.cl.bluu.es','text','',now(),now(),NULL,4);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(126,'ockPassword','UBdR3wJZ9/0NcMO9GA4fhA==','secret','',now(),now(),NULL,5);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(137,'xmppUser','acs','text','',now(),now(),NULL,4);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(138,'xmppPassword','KCD/P7AGdrMA13AUHa3/lQ==','secret','',now(),now(),NULL,4);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(139,'xmppResource','acs','text','',now(),now(),NULL,4);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(140,'xmppAdminUser','admin','text','',now(),now(),NULL,4);
insert into configuration(id,data_key,data_value,fieldtype,fieldvalues,ttcreation,ttmodification,id_client,id_category) values(141,'xmppAdminPassword','L1WBXBdc5VrBu4ZhVOKIbQ==','secret','',now(),now(),NULL,4);

-- Insert Into Connection
INSERT INTO connections VALUES(1,NULL,'cpe','ent_cpe_1.0.xml','ent_cpe_1.0.xml','http://192.168.20.81:30005/','74dada-DIR-878-74dadaebfb83');

-- Insert into Manufacturer
insert into manufacturer VALUES(1,'D-Link Corporation');

-- Insert into Model
insert into model VALUES(1,'DIR-878',1);

-- Insert Into Status Devices
INSERT INTO status_devices VALUES(1,'Activo'),(2,'Inactivo');

-- Insert INTO Cpe
INSERT INTO cpe VALUES(1,now(),NULL,'74dada-DIR-878-74dadaebfb83','192.168.20.81',NULL,NULL,'74dadaebfb83',NULL,now(),NULL,'http://192.168.20.81:30005/',1,1,1);

-- Insert Into Templates
INSERT INTO templates VALUES(1,'<?xml version="1.0" encoding="UTF-8"?>           
 <template role="cpe" manufacturer="NOZHONE" model="NO6218">
     <md5>firma</md5>
     <connectivity protocol="cwmp"/>
     <parameters>
         <parameter id="par_group_ip_ping" type="group">
             <contents>
                 <operation type="wait_confirmation"/>
                 <operation type="parameter">par_ipping</operation>
             </contents>
         </parameter>
         <parameter id="par_group_troubleshoot" type="group">
             <contents>
                 <operation type="parameter">par_dev_model</operation>
                 <operation type="parameter">par_wifi_config</operation>              
             </contents>
         </parameter>
         <parameter id="par_group_wificonfig" type="group">
             <contents>
                 <operation type="parameter">par_dev_model</operation>
                 <operation type="parameter">par_wifi_config</operation>              
             </contents>
         </parameter>
         <parameter id="par_xmpp_supported" type="value">
             <contents>
                 <operation type="value">false</operation>
             </contents>
         </parameter>        
         <parameter id="par_dev_macaddress" type="value">
             <contents>
                 <operation type="device_value">InternetGatewayDevice.LANDevice.1.LANEthernetInterfaceConfig.1.MACAddress</operation>
             </contents>
         </parameter>
         <parameter id="par_dev_manufacturer" type="value">
             <contents>
                 <operation type="device_value">InternetGatewayDevice.DeviceInfo.Manufacturer</operation>
             </contents>
         </parameter>
         <parameter id="par_dev_model" type="value">
             <contents>
                 <operation type="device_value">InternetGatewayDevice.DeviceInfo.ProductClass</operation>
             </contents>
         </parameter>
         <parameter id="par_ipping" type="table">
             <contents>
                 <operation type="device_value">InternetGatewayDevice.IPPingDiagnostics.</operation>
             </contents>
         </parameter>
         <parameter id="par_ipping_diagnostics_state" type="value">
             <contents>
                 <operation type="device_value">InternetGatewayDevice.IPPingDiagnostics.DiagnosticsState</operation>
             </contents>
         </parameter>
         <parameter id="par_ipping_success_count" type="value">
             <contents>
                 <operation type="device_value">InternetGatewayDevice.IPPingDiagnostics.SuccessCount</operation>
             </contents>
         </parameter>
         <parameter id="par_ipping_failure_count" type="value">
             <contents>
                 <operation type="device_value">InternetGatewayDevice.IPPingDiagnostics.FailureCount</operation>
             </contents>
         </parameter>
         <parameter id="par_ipping_number_repetitions" type="value">
             <contents>
                 <operation type="device_value">InternetGatewayDevice.IPPingDiagnostics.NumberOfRepetitions</operation>
             </contents>
         </parameter>
         <parameter id="par_ipping_average_response_time" type="value">
             <contents>
                 <operation type="device_value">InternetGatewayDevice.IPPingDiagnostics.AverageResponseTime</operation>
             </contents>
         </parameter>
         <parameter id="par_ipping_minimum_response_time" type="value">
             <contents>
                 <operation type="device_value">InternetGatewayDevice.IPPingDiagnostics.MinimumResponseTime</operation>
             </contents>
         </parameter>
         <parameter id="par_ipping_maximum_response_time" type="value">
             <contents>
                 <operation type="device_value">InternetGatewayDevice.IPPingDiagnostics.MaximumResponseTime</operation>
             </contents>
         </parameter>
         <parameter id="par_wifi_ssid1" type="value">
             <contents>
                 <operation type="device_value">InternetGatewayDevice.LANDevice.1.WLANConfiguration.1.SSID</operation>
             </contents>
         </parameter>
         <parameter id="par_wifi_ssid1_channel" type="value">
             <contents>
                 <operation type="device_value">InternetGatewayDevice.LANDevice.1.WLANConfiguration.1.Channel</operation>
             </contents>
         </parameter>
         <parameter id="par_wifi_ssid1_encryption" type="value">
             <contents>
                 <operation type="device_value">InternetGatewayDevice.LANDevice.1.WLANConfiguration.1.BeaconType</operation>
             </contents>
         </parameter>
         <parameter id="par_wifi_config" type="table">
             <contents>
                 <operation type="device_table">InternetGatewayDevice.LANDevice.1.WLANConfiguration.</operation>
             </contents>
         </parameter>
     </parameters>
     <tests>
         <test id="tst_template">
             <boolean type="and" result="true"/>
         </test>
         <test id="tst_wifi">
             <boolean result="true"/>
             <messages>
                 <message id="msg_acs_wifi_ssid1" severity="info" visualize="ifrequired">
                     <text>SSID: #P#par_wifi_ssid1#</text>
                 </message>
                 <message id="msg_acs_wifi_ssid1_channel" severity="info" visualize="ifrequired">
                     <text>Channel: #P#par_wifi_ssid1_channel#</text>
                 </message>
                 <message id="msg_acs_wifi_ssid1_encryption" severity="info" visualize="ifrequired">
                     <text>Encryption: #P#par_wifi_ssid1_encryption#</text>
                 </message>
             </messages>
             <actions>
                 <action id="act_wifi_ssid1_setchannel" visualize="ifrequired"/>
                 <action id="act_wifi_ssid1_setencryption" visualize="ifrequired"/>
                 <action id="act_wifi_ssid1_setpassword" visualize="ifrequired"/>
                 <action id="act_wifi_ssid1_setsecurity" visualize="ifrequired"/>
                 <action id="act_reboot" visualize="ifrequired"/>
             </actions>           
         </test>
         <test id="tst_ip_ping">
             <preaction>act_ip_ping</preaction>
             <preparameter>par_group_ip_ping</preparameter>
             <boolean type="and">
                 <reference type="check" id="chk_ip_ping"/>                                        
                 <reference type="check" id="chk_local_ip_ping"/>
             </boolean>
             <messages>
                 <message id="msg_ip_ping_success" severity="info" visualize="ifrequired">
                     <text>Success: #P#par_ipping_success_count# pkgs received / #P#par_ipping_number_repetitions# pkgs sent</text>
                 </message>
                 <message id="msg_ip_ping_avg_time" severity="info" visualize="ifrequired">
                     <text>Average: #P#par_ipping_average_response_time# ms</text>
                 </message>
                 <message id="msg_ip_ping_ip_address" severity="info" visualize="ifrequired">
                     <text>IP: #P#act_ip_ping_arg_ip_address#</text>
                 </message>
             </messages>
             <actions>
                 <action id="act_ip_ping_ext" visualize="ifrequired"/>
             </actions>           
         </test>
     </tests>   
     <checks>
         <check id="chk_template">
             <parameter>par_dev_model</parameter>
             <boolean type="and">
                 <operation type="not_equal">6218</operation>                
             </boolean>
         </check>
         <check id="chk_ip_ping">
             <parameter>par_ipping_failure_count</parameter>            
             <boolean type="and">
                 <operation type="equal">0</operation>                
             </boolean>
         </check>
         <check id="chk_local_ip_ping">
             <parameter>act_ip_ping_arg_ip_address</parameter>            
             <boolean type="and">
                 <operation type="equal">192.168.10.50</operation>                
             </boolean>
             <messages>
                 <message id="msg_err_notmyip" severity="error" visualize="iffalse">
                     <text>IP #P#act_ip_ping_arg_ip_address# not my network ip</text>
                 </message>
             </messages>
         </check>
     </checks>
     <actions>
         <action id="act_wifi_ssid1_setchannel" type="config">
             <texts>
                 <text type="detail">Cambiar Canal Wifi</text>
                 <text type="help">Help Cambiar Canal Wifi</text>
                 <text type="warning">Warning Cambiar Canal Wifi</text>
                 <text type="ok">Ok Cambiar Canal Wifi</text>
                 <text type="error">Error Cambiar Canal Wifi</text>
             </texts>
             <inputs>
                 <input id="ipt_wifi_channel" type="list" default="1">
                     <text>Canal</text>
                     <valuelist>
                         <value>1</value>
                         <value>2</value>
                         <value>3</value>
                         <value>4</value>
                         <value>5</value>
                     </valuelist>
                 </input>
             </inputs>
             <sequence>
                 <operation type="cwmp_set_value">#I#ipt_wifi_channel#</operation>
                 <operation type="cwmp_set_parameter">InternetGatewayDevice.LANDevice.1.WLANConfiguration.1.Channel</operation>
             </sequence>
         </action>
         <action id="act_reboot" type="button">
             <texts>
                 <text type="detail">Resetear CPE</text>
                 <text type="help">Help Resetear CPE</text>
                 <text type="warning">Warning Resetear CPE</text>
                 <text type="ok">Ok Resetear CPE</text>
                 <text type="error">Error Resetear CPE</text>
             </texts>
         </action>
         <action id="act_wifi_ssid1_setencryption" type="config">
             <texts>
                 <text type="detail">Cambiar Encriptación</text>
                 <text type="help">Help Cambiar Encriptación</text>
                 <text type="warning">Warning Cambiar Encriptación</text>
                 <text type="ok">Ok Cambiar Encriptación</text>
                 <text type="error">Error Cambiar Encriptación</text>
             </texts>
             <inputs>
                 <input id="ipt_wifi_encryption" type="list" default="WPA">
                     <text>Encriptación</text>
                     <valuelist>
                         <value>None</value>
                         <value>Basic</value>
                         <value>WPA</value>
                     </valuelist>
                 </input>
             </inputs>
             <sequence>
                 <operation type="cwmp_set_value">#I#ipt_wifi_encryption#</operation>
                 <operation type="cwmp_set_parameter">InternetGatewayDevice.LANDevice.1.WLANConfiguration.1.BeaconType</operation>
             </sequence>
         </action>
         <action id="act_wifi_ssid1_setpassword" type="config">
             <texts>
                 <text type="detail">Cambiar Password KeyPassphrase</text>
                 <text type="help">Help Cambiar Password</text>
                 <text type="warning">Warning Cambiar Password</text>
                 <text type="ok">Ok Cambiar Password</text>
                 <text type="error">Error Cambiar Password</text>
             </texts>
             <inputs>
                 <input id="ipt_wifi_password" type="text" default="WPA">
                     <text>Password</text>
                 </input>
             </inputs>
             <sequence>
                 <operation type="cwmp_set_value">#I#ipt_wifi_password#</operation>
                 <operation type="cwmp_set_parameter">InternetGatewayDevice.LANDevice.1.WLANConfiguration.1.PreSharedKey.1.KeyPassphrase</operation>
             </sequence>
         </action>
         <action id="act_wifi_ssid1_setsecurity" type="config">
             <texts>
                 <text type="detail">Cambiar Seguridad PreSharedKey</text>
                 <text type="help">Help Cambiar Seguridad</text>
                 <text type="warning">Warning Cambiar Seguridad</text>
                 <text type="ok">Ok Cambiar Seguridad</text>
                 <text type="error">Error Cambiar Seguridad</text>
             </texts>
             <inputs>
                 <input id="ipt_wifi_encryption" type="list" default="WPA">
                     <text>Encriptación</text>
                     <valuelist>
                         <value>None</value>
                         <value>Basic</value>
                         <value>WPA</value>
                     </valuelist>
                 </input>
                 <input id="ipt_wifi_password" type="text" default="WPA">
                     <text>Password</text>
                 </input>
             </inputs>
             <sequence>
                 <operation type="cwmp_set_value">#I#ipt_wifi_encryption#</operation>
                 <operation type="cwmp_set_parameter">InternetGatewayDevice.LANDevice.1.WLANConfiguration.1.BeaconType</operation>
                 <operation type="cwmp_set_value">#I#ipt_wifi_password#</operation>
                 <operation type="cwmp_set_parameter">InternetGatewayDevice.LANDevice.1.WLANConfiguration.1.PreSharedKey.1.PreSharedKey</operation>
             </sequence>
         </action>
         <action id="act_ip_ping" type="button">
             <texts>
                 <text type="detail">IP Ping</text>
                 <text type="help">Help IP Ping</text>
                 <text type="warning">Warning IP Ping</text>
                 <text type="ok">Ok IP Ping</text>
                 <text type="error">Error IP Ping</text>
             </texts>
             <arguments>
                 <argument id="arg_ip_address">192.168.10.50</argument>
             </arguments>
             <sequence>
                 <operation type="cwmp_set_value">InternetGatewayDevice.WANDevice.1.WANConnectionDevice.3.WANIPConnection.4</operation>
                 <operation type="cwmp_set_parameter">InternetGatewayDevice.IPPingDiagnostics.Interface</operation>
                 <operation type="cwmp_set_value">#I#arg_ip_address#</operation>
                 <operation type="cwmp_set_parameter">InternetGatewayDevice.IPPingDiagnostics.Host</operation>
                 <operation type="cwmp_set_value">5</operation>
                 <operation type="cwmp_set_parameter">InternetGatewayDevice.IPPingDiagnostics.NumberOfRepetitions</operation>
                 <operation type="cwmp_set_value">200</operation>
                 <operation type="cwmp_set_parameter">InternetGatewayDevice.IPPingDiagnostics.Timeout</operation>
                 <operation type="cwmp_set_value">32</operation>
                 <operation type="cwmp_set_parameter">InternetGatewayDevice.IPPingDiagnostics.DataBlockSize</operation>
                 <operation type="cwmp_set_value">0</operation>
                 <operation type="cwmp_set_parameter">InternetGatewayDevice.IPPingDiagnostics.DSCP</operation>
                 <operation type="cwmp_set_value">Requested</operation>
                 <operation type="cwmp_set_parameter">InternetGatewayDevice.IPPingDiagnostics.DiagnosticsState</operation>
             </sequence>
         </action>        
         <action id="act_ip_ping_ext" type="config">
             <texts>
                 <text type="detail">IP Ping</text>
                 <text type="help">Help IP Ping</text>
                 <text type="warning">Warning IP Ping</text>
                 <text type="ok">Ok IP Ping</text>
                 <text type="error">Error IP Ping</text>
             </texts>
             <inputs>
                 <input id="arg_ip_address" type="list" default="192.168.10.50">
                     <text>IP Address</text>
                     <valuelist>
                         <value label="edu">192.168.10.50</value>
                         <value label="localhost">127.0.0.1</value>
                         <value label="google">www.google.es</value>
                     </valuelist>
                 </input>
             </inputs>
             <test>tst_ip_ping</test>
         </action>
     </actions>
 </template>','ent_cpe_1.0.xml',now(),now());