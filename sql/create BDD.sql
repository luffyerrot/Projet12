CREATE DATABASE `projet12` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

CREATE TABLE projet12.users (
  `user_id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE projet12.user_informations (
  `user_id` bigint(20) NOT NULL,
  `adress` varchar(255) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `postcode` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE projet12.enterprises (
  `enterprise_id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`enterprise_id`),
  UNIQUE KEY `UK_85sikvf8i797lgp7dbldy48hj` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE projet12.enterprise_informations (
  `enterprise_id` bigint(20) NOT NULL,
  `adress` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `postcode` varchar(255) DEFAULT NULL,
  `siret` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`enterprise_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE projet12.products (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `enterprise_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfbvh43ybvuq7i57dnl45ct8wi` (`enterprise_id`),
  CONSTRAINT `FKfbvh43ybvuq7i57dnl45ct8wi` FOREIGN KEY (`enterprise_id`) REFERENCES `enterprises` (`enterprise_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE projet12.categories (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE projet12.restrictions (
  `user_id` bigint(20) NOT NULL,
  `categorie_id` bigint(20) NOT NULL,
  KEY `FKtg7tc7t22yb32g1pgp2je5nap` (`categorie_id`),
  KEY `FKm84ma1j2h7pxeh1ebfgrppyh2` (`user_id`),
  CONSTRAINT `FKm84ma1j2h7pxeh1ebfgrppyh2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FKtg7tc7t22yb32g1pgp2je5nap` FOREIGN KEY (`categorie_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE projet12.gift_baskets (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `recovery_date` datetime NOT NULL,
  `enterprise_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbidb74p4w79xeebrxhetb2mvx` (`enterprise_id`),
  CONSTRAINT `FKbidb74p4w79xeebrxhetb2mvx` FOREIGN KEY (`enterprise_id`) REFERENCES `enterprises` (`enterprise_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE projet12.gift_basket_product (
  `gift_basket_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  KEY `FKs5b6wuwrst72sbxd6699vk490` (`product_id`),
  KEY `FK7xrkafsg162ua69sfpxcs51u1` (`gift_basket_id`),
  CONSTRAINT `FK7xrkafsg162ua69sfpxcs51u1` FOREIGN KEY (`gift_basket_id`) REFERENCES `gift_baskets` (`id`),
  CONSTRAINT `FKs5b6wuwrst72sbxd6699vk490` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE projet12.product_categorie (
  `product_id` bigint(20) NOT NULL,
  `categorie_id` bigint(20) NOT NULL,
  KEY `FKr39o0chgf41p88iggywujyar7` (`categorie_id`),
  KEY `FKlq6tko9rr9yyw2bx256r9m7x4` (`product_id`),
  CONSTRAINT `FKlq6tko9rr9yyw2bx256r9m7x4` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FKr39o0chgf41p88iggywujyar7` FOREIGN KEY (`categorie_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE projet12.gift_basket_categorie (
  `gift_basket_id` bigint(20) NOT NULL,
  `categorie_id` bigint(20) NOT NULL,
  KEY `FKguil1cc8ptqjblu9dlpnk973g` (`categorie_id`),
  KEY `FKbm2meqpo2ns6oxndpr5sbl48g` (`gift_basket_id`),
  CONSTRAINT `FKbm2meqpo2ns6oxndpr5sbl48g` FOREIGN KEY (`gift_basket_id`) REFERENCES `gift_baskets` (`id`),
  CONSTRAINT `FKguil1cc8ptqjblu9dlpnk973g` FOREIGN KEY (`categorie_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE projet12.roles (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE projet12.user_role (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FKt7e7djp752sqn6w22i6ocqy6q` (`role_id`),
  KEY `FKj345gk1bovqvfame88rcx7yyx` (`user_id`),
  CONSTRAINT `FKj345gk1bovqvfame88rcx7yyx` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FKt7e7djp752sqn6w22i6ocqy6q` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE projet12.enterprise_role (
  `enterprise_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FKqyssuecl1jbm0mihs7w7502jg` (`role_id`),
  KEY `FKgi44675pdag5344fe2oxnbk4c` (`enterprise_id`),
  CONSTRAINT `FKgi44675pdag5344fe2oxnbk4c` FOREIGN KEY (`enterprise_id`) REFERENCES `enterprises` (`enterprise_id`),
  CONSTRAINT `FKqyssuecl1jbm0mihs7w7502jg` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE projet12.bookings (
  `id` bigint(20) NOT NULL,
  `booking_date` datetime NOT NULL,
  `gift_baskets_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqagqji4u6ecsacb9wsr9t1avc` (`gift_baskets_id`),
  KEY `FKeyog2oic85xg7hsu2je2lx3s6` (`user_id`),
  CONSTRAINT `FKeyog2oic85xg7hsu2je2lx3s6` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FKqagqji4u6ecsacb9wsr9t1avc` FOREIGN KEY (`gift_baskets_id`) REFERENCES `gift_baskets` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;