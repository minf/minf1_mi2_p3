CREATE  TABLE IF NOT EXISTS `category` (
  `id` INT NOT NULL ,
  `parent_id` INT NULL ,
  `name` VARCHAR(200) NULL ,
  `description` VARCHAR(200) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8;


CREATE  TABLE IF NOT EXISTS `option` (
  `id` INT NOT NULL ,
  `article_id` VARCHAR(5) NULL ,
  `category_id` INT NULL ,
  `name` VARCHAR(200) NULL ,
  `description` VARCHAR(200) NULL ,
  `img` VARCHAR(200) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `category_id` (`category_id` ASC) ,
  CONSTRAINT `category_id`
    FOREIGN KEY (`category_id` )
    REFERENCES `category` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8