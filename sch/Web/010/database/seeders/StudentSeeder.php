<?php

namespace Database\Seeders;

use App\Models\Student;
use Illuminate\Database\Seeder;

class StudentSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        $grade = ["X", "XI", "XII"];
        $gender = ["man", "woman"];
        for ($i = 0; $i < 100; $i++) {
            Student::create([
                'name' => fake()->name(),
                'gender' => $gender[array_rand($gender)],
                'grade' => $grade[array_rand($grade)],
                'address' => fake()->address(),
            ]);
        }
    }
}
