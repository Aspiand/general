/*
Nama: Muhammad Aspian
NIM : anu
*/

#include <stdio.h>

int main()
{
    float total_belanja, potongan, total_bayar;

    puts("Pembayaran Belanja");
    printf("Masukkan total belanja = ");
    scanf("%f", &total_belanja);

    if (total_belanja > 100000)
    {
        potongan = (20.0 / 100.0) * total_belanja;
        total_bayar = total_belanja - potongan;
        puts("Selamat Anda mendapat diskon 20%");
        printf("Total Belanja\t= %9.2f\n", total_belanja);
        printf("Potongan 20%\t= %9.2f\n", potongan);
        printf("Total Bayar\t= %9.2f\n", total_bayar);
    }
    else
    {
        printf("Total Belanja = %9.2f\n", total_belanja);
    }

    return 0;
}