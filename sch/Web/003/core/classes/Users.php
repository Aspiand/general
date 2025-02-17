<?php

final class Users {
    private $db, $table;

    public $message = [];
    // Property message akan berisi Array Associative dengan dua buah kunci yaitu:
    //    type -> Tipe pesan, berupa sukses atau gagal.
    //    message -> Isi pesan.
    // Berfungsi untuk menampilkan pesan kepada user dan akan ditampilkan dengan alert
    // dan terletak di atas tag penutup body

    public function __construct(PDO $database, string $table = 'users') {
        $this->db = $database;
        $this->table = $table;

        $this->create_table($table);
    }

    public function fetch() {
        return $this->db->query(
            "SELECT * FROM $this->table"
        )->fetchAll(PDO::FETCH_ASSOC);
    }

    public function add(string $username, string $password) : bool {

        // Enkripsi Password
        $password = password_hash($password, PASSWORD_DEFAULT);

        $stmt = $this->db->prepare("
            INSERT INTO $this->table
            (username, password)
            VALUES (:username, :password)"
        );

        $stmt->bindParam(":username", $username, PDO::PARAM_STR);
        $stmt->bindParam(":password", $password, PDO::PARAM_STR);

        try {

            $stmt->execute();
            $this->message[] = [
                "type" => "success",
                "message" => "Akun Berhasil Ditambahkan!"
            ];

            return true;
        } catch (PDOException $e) {

            // Menangani input duplikat
            if ($e->getCode() == "23000") {

                $this->message[] = [
                    "type" => "failed",
                    "message" => "Username Sudah Ada!",
                ];

                return false;
            } else {

                $this->message[] = [
                    "type" => "failed",
                    "error_code" => $e->getCode(),
                    "message" => $e
                ];

                return false;
            }
        }
    }

    public function login(string $username, string $password) : bool {
        $hassword = $this->get_password($username);
        
        // Memeriksa hasil query apakah ada
        if (is_null($hassword)) {
            $this->message[] = [
                "type" => "failed",
                "message" => "Username Tidak Ada!"
            ];

            return false;
        }

        // Varifikasi Password apakah sama dengan yang ada di database
        else if (!password_verify($password, $hassword)) {
            $this->message[] = [
                "type" => "failed",
                "message" => "Password Salah!"
            ];

            return false;
        }

        $this->message[] = [
            "type" => "success",
            "message" => "Login Berhasil!"
        ];

        return true;
    }

    public function get_password(string $username) : string | null {
        $stmt = $this->db->prepare("
            SELECT password FROM $this->table
            WHERE username = :username"
        );

        $stmt->bindParam(":username", $username, PDO::PARAM_STR);
        $stmt->execute();

        return $stmt->fetch(PDO::FETCH_ASSOC)["password"] ?? null;
    }

    private function create_table(string $table) : void {
        $this->db->exec("
            -- DROP TABLE IF EXISTS $table;
            CREATE TABLE IF NOT EXISTS $table (
                username VARCHAR(255) PRIMARY KEY,
                password TEXT NOT NULL
            );
        ");
    }
}