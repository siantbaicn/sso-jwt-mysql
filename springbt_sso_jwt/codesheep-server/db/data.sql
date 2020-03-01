INSERT INTO authority  VALUES(1,'ROLE_OAUTH_ADMIN');
INSERT INTO authority VALUES(2,'ROLE_RESOURCE_ADMIN');
INSERT INTO authority VALUES(3,'ROLE_PRODUCT_ADMIN');
INSERT INTO authority VALUES(4, 'ROLE_NORMAL');
INSERT INTO authority VALUES(5, 'ROLE_MEDIUM');
INSERT INTO credentials VALUES(1,b'1','oauth_admin','$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2','0');
INSERT INTO credentials VALUES(2,b'1','resource_admin','$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2','0');
INSERT INTO credentials  VALUES(3,b'1','product_admin','$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2','0');
INSERT INTO credentials  VALUES(4, 1, 'code_sheep', '$2a$10$JSRUmCd3aRzKfjX6K4EtVeEtpPRGAVWKopemD7ydlNX3LmrY6TcDe', NULL);

INSERT INTO credentials_authorities VALUE (1,1);
INSERT INTO credentials_authorities VALUE (2,2);
INSERT INTO credentials_authorities VALUE (3,3);
INSERT INTO credentials_authorities VALUE (4,4);
INSERT INTO credentials_authorities VALUE (4,5);


INSERT INTO oauth_client_details VALUES('curl_client','product_api', '$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2', 'read,write', 'client_credentials', 'http://127.0.0.1', 'ROLE_PRODUCT_ADMIN', 7200, 0, NULL, 'true');
INSERT INTO oauth_client_details VALUES('sheep1', 'sheep1', '$2a$10$JSRUmCd3aRzKfjX6K4EtVeEtpPRGAVWKopemD7ydlNX3LmrY6TcDe', 'read,write', 'authorization_code,', NULL, 'ROLE_NORMAL,ROLE_MEDIUM', 7200, 0, NULL, NULL);
INSERT INTO oauth_client_details VALUES('sheep2', 'sheep2', '$2a$10$JSRUmCd3aRzKfjX6K4EtVeEtpPRGAVWKopemD7ydlNX3LmrY6TcDe', 'read,write', 'authorization_code,', NULL, 'ROLE_NORMAL,ROLE_MEDIUM', 7200, 0, NULL, NULL);