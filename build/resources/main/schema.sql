CREATE TABLE SRCH_KEY_WORD_INFO (
  SEQ_NO BIGINT NOT NULL AUTO_INCREMENT,
  KEY_WORD VARCHAR(20) NOT NULL,
  SRCH_CNT BIGINT NOT NULL,
  MOD_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  IN_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (SEQ_NO)
);