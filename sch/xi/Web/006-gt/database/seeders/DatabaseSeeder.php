<?php

namespace Database\Seeders;

use App\Models\User;
// use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     */
    public function run(): void
    {
        // User::factory(10)->create();

        // default password: 'password'

        User::factory()->create([
            'name' => 'admin',
            'email' => 'admin@aspian.my.id',
            'role' => 'admin'
        ]);

        User::factory()->create([
            'name' => 'elaina',
            'email' => 'elaina@aspian.my.id',
            'role' => 'user'
        ]);
    }
}
