DROP SCHEMA IF EXISTS `portal` ;
CREATE SCHEMA IF NOT EXISTS `portal` DEFAULT CHARACTER SET utf8 ;
USE `portal` ;

-- -----------------------------------------------------
-- Table `portal`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portal`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) NOT NULL,
  `email` VARCHAR(256) NOT NULL,
  `password` VARCHAR(256) NOT NULL,
  `permission` CHAR(5) NOT NULL,
  `phone_number` VARCHAR(13) NOT NULL,
  `is_receiver` TINYINT NULL,
  `date_time` DATETIME NOT NULL,
  `deleted_by` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_usuario_usuario_idx` (`deleted_by` ASC) VISIBLE,
  UNIQUE INDEX `phone_number_UNIQUE` (`phone_number` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_usuario`
    FOREIGN KEY (`deleted_by`)
    REFERENCES `portal`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portal`.`city`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portal`.`city` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) NULL,
  `is_active` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portal`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portal`.`category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) NULL,
  `is_active` TINYINT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portal`.`city_others`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portal`.`city_others` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portal`.`category_others`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portal`.`category_others` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portal`.`form`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portal`.`form` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `author_name` VARCHAR(64) NOT NULL,
  `author_role` VARCHAR(64) NULL,
  `business_name` VARCHAR(64) NOT NULL,
  `contact_email` VARCHAR(256) NOT NULL,
  `description` VARCHAR(1024) NOT NULL,
  `date_time` DATETIME NOT NULL,
  `city_id` INT NULL,
  `category_id` INT NULL,
  `city_others_id` INT NULL,
  `category_others_id` INT NULL,
  `deleted_by` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_lead_user1_idx` (`deleted_by` ASC) VISIBLE,
  INDEX `fk_form_city1_idx` (`city_id` ASC) VISIBLE,
  INDEX `fk_form_category1_idx` (`category_id` ASC) VISIBLE,
  INDEX `fk_form_city_others1_idx` (`city_others_id` ASC) VISIBLE,
  INDEX `fk_form_category_others1_idx` (`category_others_id` ASC) VISIBLE,
  CONSTRAINT `fk_lead_user1`
    FOREIGN KEY (`deleted_by`)
    REFERENCES `portal`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_form_city1`
    FOREIGN KEY (`city_id`)
    REFERENCES `portal`.`city` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_form_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `portal`.`category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_form_city_others1`
    FOREIGN KEY (`city_others_id`)
    REFERENCES `portal`.`city_others` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_form_category_others1`
    FOREIGN KEY (`category_others_id`)
    REFERENCES `portal`.`category_others` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portal`.`channel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portal`.`channel` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(32) NULL,
  `is_active` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portal`.`notification`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portal`.`notification` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date_time` DATETIME NULL,
  `channel_id` INT NOT NULL,
  `form_id` INT NOT NULL,
  `user_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_notification_channel1_idx` (`channel_id` ASC) VISIBLE,
  INDEX `fk_notification_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_notification_lead1_idx` (`form_id` ASC) VISIBLE,
  CONSTRAINT `fk_notification_channel1`
    FOREIGN KEY (`channel_id`)
    REFERENCES `portal`.`channel` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_notification_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `portal`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_notification_lead1`
    FOREIGN KEY (`form_id`)
    REFERENCES `portal`.`form` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portal`.`log`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portal`.`log` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date_time` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portal`.`image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portal`.`image` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_imgbb` VARCHAR(16) NOT NULL,
  `name` VARCHAR(128) NOT NULL,
  `display_url` VARCHAR(256) NULL,
  `width` INT NOT NULL,
  `height` INT NOT NULL,
  `size` INT NOT NULL,
  `time` INT NOT NULL,
  `expiration` INT NOT NULL,
  `mime` VARCHAR(16) NOT NULL,
  `extension` VARCHAR(5) NOT NULL,
  `delete_url` VARCHAR(256) NOT NULL,
  `date_time` DATETIME NOT NULL,
  `deleted_by` INT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_image_user1_idx` (`deleted_by` ASC) VISIBLE,
  INDEX `fk_image_user2_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_image_user1`
    FOREIGN KEY (`deleted_by`)
    REFERENCES `portal`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_image_user2`
    FOREIGN KEY (`user_id`)
    REFERENCES `portal`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `portal`.`image_configuration`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `portal`.`image_configuration` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `quantity_consumed` INT NOT NULL,
  `total_quota` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;