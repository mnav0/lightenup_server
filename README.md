# lightenup_server

query for animation queue
SELECT q.id, t.name FROM heroku_03f34d47c76bfbf.animation_queue q JOIN heroku_03f34d47c76bfbf.animation_types t ON q.type = t.id;

mysql://bf30736e7e60db:8b4321d4@us-cdbr-east-05.cleardb.net/heroku_03f34d47c76bfbf?reconnect=true
mysql://DBUSERNAME:DBPASSWORD@DBHOSTNAME/DBSCHEMA
