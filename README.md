
# Transaction Routine

App to log Transactions



## Installation

To run this project on local

```bash
  git clone https://github.com/dhvl-nyk/txn-routine 
  sh run.sh
```

To run this project on docker

```bash
  gradle bootJar
  docker build --progress=plain -t txn-routine-app .
  docker container run -d -p 8080:8080 txn-routine-app:latest
```




## EndPoints
OpenAPI definition
```
http://localhost:8080/swagger-ui/index.html
```
Db
```
http://localhost:8080/h2-console
```  
