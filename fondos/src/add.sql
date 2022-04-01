ALTER TABLE `presupuesto`.`financiamiento` 
ADD COLUMN `donador` VARCHAR(500) NULL AFTER `fechaRegistro`,
CHANGE COLUMN `Nombre` `Nombre` VARCHAR(50) NULL DEFAULT NULL ;