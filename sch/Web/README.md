# -
## Start

PHP & Web Server
```bash
podman run --name php_dev -p 8000:80 -v $(pwd)/:/var/www/html/ -d php:8.2-apache
```

or

```bash
php -S localhost:8000
```

Database
```bash
podman run -dp 3306:3306 --name sql -e MYSQL_ROOT_PASSWORD=root docker.io/percona:latest
```