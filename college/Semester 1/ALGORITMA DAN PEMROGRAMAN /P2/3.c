#include <stdio.h>

int main()
{
    int nilai_integer;
    printf("Masukkan nilai integer = ");
    scanf("%i", &nilai_integer);

    nilai_integer = nilai_integer / 2;

    printf("Hasil dengan %%i = %i\n", nilai_integer);
    printf("Hasil dengan %%f = %f\n", (float)nilai_integer);

    printf("\n\n");

    float nilai_float;
    printf("Masukkan nilai float = ");
    scanf("%f", &nilai_float);

    nilai_float = nilai_float / 2;

    printf("Hasil dengan %%i = %i\n", (int)nilai_float);
    printf("Hasil dengan %%f = %f\n", nilai_float);

    return 0;
}

/*
# Tugas Rumah

## 1. Input integer:
in case yang diinput adalah genap, maka hasilnya normal (10/2=5).
tapi jika ganjil maka akan dibulatkan. Misal input 7, maka %i akan 3 dan %f akan 3.000000
(output float sudah diparsing ke float, jika tidak akan menapilkan 0.000000).

## 2. Input float:
input = 9
%i = 4, %f = 4.5
Seperti sebelumnya, pada integer dilakukan pembulatan
dan kehilangan informasi detail dari hasil perhitungan.

3. Untuk menghitung volumenya (diasumsikan yang dimaksud adalah kubus),
variable yang dibutuhkan ada 2 yaitu sisi dan volume yang bertipe float atau double.

Rumus volume kubus adalah:

volume = s^3

4. Untuk menghitung volume dari sebuah tabung dapat menggunakan rumus berikut:

Rumus volume tabung adalah:

volume = pi * r^2 * t

semua variable menggunakan float atau double.
 */
