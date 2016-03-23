HOW TO?? :D

- update composer requirements
- create database from db.sql
- copy config/db-local.php & config/web-local.php to config/db.php & config/web.php
- update database access info in db.php
- add cookieValidationKey in web.php
- chmod 0777 this folders: web/assets, web/assets/css, yii & runtime

API

<b>signup</b>: /api/signup?username={username}&password={password}&email={email}<br />
<b>login</b>: /api/login?username={username}&password={password}<br />
<b>logout</b>: /api/find?token={token}&modelName={modelName}<br />
<b>logout</b>: /api/logout?token={token}<br />
<br />
FAQ API

<b>signup</b>: signs user up, returns json with errors on fail, on success returns access token token is valid until /api/logout<br />
<b>login</b>: login user up, returns json with errors on fail, on success returns access token token is valid until /api/logout<br />
<b>logout</b>: logout user, returns json with errors on fail, on success returns json success message<br />
<b>find</b>: returns json with errors on fail, on success returns json data example:<br />
    /api/find?token={token}&modelName=User -> returns json array of existing usernames<br />
