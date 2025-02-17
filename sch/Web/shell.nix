{ pkgs ? import <nixpkgs> {} }:

pkgs.mkShell {
  buildInputs = with pkgs; [
    php83
    php83Packages.composer
    php83Extensions.sqlite3
    php83Extensions.pdo
    php83Extensions.pdo_mysql
    php83Extensions.pdo_sqlite
  ];

  shellHook = ''
    echo "PHP version: $(php -v | head -n 1)"
    php -S localhost:8000
  '';
}