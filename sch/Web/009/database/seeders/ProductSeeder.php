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
        foreach ([
            "Flashdisk 16GB (80k)",
            "Hard Drive 500GB",
            "SSD Sata 256GB",
            "Ambalabu",
            "Core 2 Duo E8400",
            "H41",
            "HG680P"
        ] as $name) {
            Product::factory()->create([
                "name" => $name,
            ]);
        }

        // Product::truncate();
    }
}
