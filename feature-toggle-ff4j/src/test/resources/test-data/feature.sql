
CREATE TABLE if not exists `ff4j_features` (
  `feat_uid`     	VARCHAR(100)   PRIMARY KEY,
  `enable`  		INTEGER NOT NULL,
  `description` 	VARCHAR(1000),
  `strategy`		VARCHAR(1000),
  `expression`	    VARCHAR(255),
  `groupname`		VARCHAR(100)
);

INSERT INTO `ff4j_features`
    (`feat_uid`, enable, description, strategy, expression, groupname)
VALUES ('getAlbumList', true, '', 'com.example.demo.config.strategy.OfficeHoursFlippingStrategy', 'startDate=9&endDate=18', '');
