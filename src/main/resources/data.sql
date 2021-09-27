
-- insert client details
insert into oauth_client_details (client_id, client_secret, scope, authorized_grant_types,
   authorities, access_token_validity, refresh_token_validity)
   select * from (select 'client1', 'clientsecretkey1', 'read,write', 'password,refresh_token,client_credentials,authorization_code', 'ROLE_CLIENT,ROLE_TRUSTED_CLIENT', 900, 2592000) as tmp
   where not exists(select client_id from oauth_client_details where client_id="client1") limit 1;
