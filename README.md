HOW TO?? :D

- update composer requirements
- create database from db.sql
- copy config/db-local.php & config/web-local.php to config/db.php & config/web.php
- update database access info in db.php
- add cookieValidationKey in web.php
- chmod 0777 this folders: web/assets, web/assets/css, yii & runtime

API

signup: /api/signup?username={username}&password={password}&email={email}
login: /api/login?username={username}&password={password}
logout: /api/find?token={token}&modelName={modelName}
logout: /api/logout?token={token}

FAQ API

signup: signs user up, returns json with errors on fail, on success returns access token token is valid until /api/logout
login: login user up, returns json with errors on fail, on success returns access token token is valid until /api/logout
logout: logout user, returns json with errors on fail, on success returns json success message
find: returns json with errors on fail, on success returns json data example:
    /api/find?token={token}&modelName=User -> returns json array of existing usernames
