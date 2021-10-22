  
insert into projet12.roles value(800, 'ROLE_USER'), (801, 'ROLE_ADMIN');

insert into projet12.users value(800, 'user@gmail.com', '/assets/images/users/unknow.png', '$2a$10$rBH/auEBl4jC2PfdFaodJunzYlyBVsMWc56Q5VtJ9Id7v9/BJXsjq', 'user'),
					   (801, 'admin@gmail.com', '/assets/images/users/unknow.png', '$2a$10$arBPccdH6MQYseCLDuxJ7u2BPFdxZSd.kKpjZQO7UA.y34PEoSzjC', 'admin');
insert into projet12.user_informations value(800, 'rue des pommes', '1990-02-25 00:00:00', 'saint germain', 'louis', 'herier', 4, '0628293031', 65066),
					   (801, 'rue des oranges', '1995-10-12 00:00:00', 'saint dupont', 'george', 'taratu', 22, '0631302928', 44254);
                       
insert into projet12.enterprises value(800, 'user@enterprise.com', '/assets/images/users/unknow.png', 'userenterprise', '$2a$10$rBH/auEBl4jC2PfdFaodJunzYlyBVsMWc56Q5VtJ9Id7v9/BJXsjq'),
					   (801, 'admin@enterprise.com', '/assets/images/users/unknow.png', 'adminenterprise', '$2a$10$arBPccdH6MQYseCLDuxJ7u2BPFdxZSd.kKpjZQO7UA.y34PEoSzjC');
insert into projet12.enterprise_informations value(800, 'rue des cerises', 'saint ilien', 'Spécialité orientale concentré sur le fast food.', 16, '0629283130', 12457, '1458653785146'),
					   (801, 'rue des peches', 'saint hivonne', 'Boulangerie artisanal.', 7, '0630312829', 89405, '2941830670943');

insert into projet12.user_role value(800, 800), (801, 800), (801, 801);
insert into projet12.enterprise_role value(800, 800), (801, 801), (801, 801);

insert into projet12.categories value(800,'aucune catégorie', 'aucune');
insert into projet12.categories value(801,'ne mange aucune provenance animal', 'végétalien');
insert into projet12.categories value(802,'ne mange aucune viande animal', 'végétarien');
insert into projet12.categories value(803,'ne mange aucune provenance de porc et aucun produit non certifié halal', 'halal');
insert into projet12.categories value(804,'ne mange aucun produit contenant des lactoses', 'intolérant lactose');

insert into projet12.products value(800,'une pièce de boeuf de 230 grammes', '/assets/images/products/unknow.png', 'steack', 800);
insert into projet12.products value(801,'une boite de pâte de 500 grammes', '/assets/images/products/unknow.png', 'pâte', 800);
insert into projet12.products value(802,'deux portions de tofu de 130 grammes chacun', '/assets/images/products/unknow.png', 'tofu', 800);

insert into projet12.gift_baskets value(800,'colis d alimentation', 'colis1', '2021-08-23 18:30:00', 800);
insert into projet12.gift_baskets value(801,'colis d alimentation numero 2', 'colis2', '2021-08-26 18:30:00', 800);

insert into projet12.gift_basket_categorie value(800, 804);
insert into projet12.gift_basket_categorie value(801, 801);

insert into projet12.gift_basket_product value(800, 800);
insert into projet12.gift_basket_product value(800, 801);
insert into projet12.gift_basket_product value(800, 802);
insert into projet12.gift_basket_product value(801, 800);
insert into projet12.gift_basket_product value(801, 802);

insert into projet12.restrictions value(800, 804);

insert into projet12.bookings value(800, '2021-08-23 16:22:34', 800, 800);
