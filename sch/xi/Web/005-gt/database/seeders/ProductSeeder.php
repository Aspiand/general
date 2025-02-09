<?php

namespace Database\Seeders;

use App\Models\Product;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class ProductSeeder extends Seeder
{
    public function run(): void
    {
        $products = [
            [
                "name" => "Laptop",
                "price" => "100K",
                "description" => "ThinkPad"
            ],

            [
                "name" => "Flash Drive 16GB",
                "price" => "80K",
                "description" => "Ga ngotak"
            ]

        ];

        foreach ($products as $product) {
            Product::create($product);
        }
    }
}
