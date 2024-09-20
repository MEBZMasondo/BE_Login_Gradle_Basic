# README

- username : might be e-mail, a unique user attribute

- REGISTER A USER

```

POST:
http://localhost:8080/api/v1/user/save

{
  "name": "John Cage",
  "username": "johncage",
  "password": "password123"
}

```

- LOGIN

```

POST:       (should I use PUT: ? )
http://localhost:8080/api/v1/user/login

{
  "username": "johncage",
  "password": "password123"
}

```
- LIST ALL

```
http://localhost:8080/api/v1/user/list

```
