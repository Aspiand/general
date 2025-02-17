FROM debian:bookworm-slim

RUN apt update && apt install -y --no-install-recommends \
    php8.2 \
    php8.2-sqlite3 \
    php8.2-mysql
    apache2 \
    libapache2-mod-php8.2 \
    nano && \
    rm -rf /var/lib/apt/lists/* && \
    rm -rf /var/www/html/*

RUN sed -i.bak '/<Directory \/var\/www\/>/,/<\/Directory>/s/AllowOverride None/AllowOverride All/' "/etc/apache2/apache2.conf"

EXPOSE 80

CMD ["apachectl", "-DFOREGROUND"]