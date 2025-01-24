<?php

namespace Database\Factories;

use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends \Illuminate\Database\Eloquent\Factories\Factory<\App\Models\Siswa>
 */
class SiswaFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array<string, mixed>
     */
    public function definition(): array
    {
        return [
            'nama' => $this->faker->name,
            'kelas' => implode(' ', [
                $this->faker->randomElement(['X', 'XI', 'XII']),
                $this->faker->randomElement(['RPL', 'Kecantikan', 'Boga', 'Musik']),
                $this->faker->randomElement(['1', '2', '3', '4', '5']),
            ])
        ];
    }
}
