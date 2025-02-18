<?php

namespace Database\Seeders;

use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use App\Models\Product;

class ProductSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        Product::truncate();

        foreach ([
            [
                "name" => "Flashdisk 16GiB",
                "price" => 80000,
                "stock" => 20,
                "description" => "Wajib (banget)"
            ],

            [
                "name" => "HG680P",
                "price" => 150000,
                "stock" => 99,
                "description" => "Sudah terinstall openwrt"
            ],

            [
                "name" => "PC Rakitan kere hore",
                "price" => 1000000,
                "stock" => 4,
                "description" => "Core 2 Duo E8400, H41, 4 + 2 DDR3, Varror 500W"
            ],

            [
                "name" => "SSD RX7 128GiB",
                "price" => 120000,
                "stock" => 82,
                "description" => "Bisa request install windows atau tidak"
            ],

            [
                "name" => "Keyboard & Mouse Acome",
                "price" => 150000,
                "stock" => 9,
                "description" => "Garansi 8 Tahun"
            ],
        ] as $product) Product::create($product);
    }
}
