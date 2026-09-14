#include <stdio.h>
#include "../lib/getch.h"

int main()
{
    puts("Program Menampilkan Nama dan Nim");

    int jumlah_baris;
    printf("Masukkan jumlah baris: ");
    scanf("%d", &jumlah_baris);
    getchar();

    for (int i = 1; i <= jumlah_baris; i++)
    {
        printf("%3d. Muhammad Aspiand - NIM\n", i);

        if (i % 5 == 0 && i != jumlah_baris)
        {
            printf("tekan enter untuk melanjutkan");
            getch();
            putchar('\n');
        }
    }

    return 0;
}