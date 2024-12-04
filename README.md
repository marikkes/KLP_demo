Testet med curl

Legge til bruker:
curl -X POST http://localhost:8080/user \
  -H "Content-Type: application/json" \
  -d '{"email": "user1@example.com", "type": "USER"}'

curl -X POST http://localhost:8080/user \
  -H "Content-Type: application/json" \
  -d '{"email": "admin1@example.com", "type": "ADMIN"}'

Hente ut alle brukere:
curl -X GET http://localhost:8080/user

Hente bruker med spesifikk id:
curl -X GET http://localhost:8080/user/1

Hente ut bruker av bestemt type:
curl -X GET "http://localhost:8080/user?type=USER"

Hente navnet på et bestemt fylke:
curl -X GET "http://localhost:8080/county/11" -H "accept: text/plain"
