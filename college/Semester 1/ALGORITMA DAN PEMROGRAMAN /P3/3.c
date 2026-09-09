/*

Nama : Muhammad Aspian
NIM  : Anu
Kelas: SKIC 1A

*/

#include <stdio.h>

int main()
{
    float nilai;
    char huruf;

    printf("Masukkan Nilai = ");
    scanf("%f", &nilai);

    if (nilai >= 80)
    {
        huruf = 'A';
    }
    else if (nilai >= 60)
    {
        huruf = 'B';
    }
    else if (nilai >= 50)
    {
        huruf = 'C';
    }
    else if (nilai >= 40)
    {
        huruf = 'D';
    }
    else if (nilai <= 39.999)
    {
        huruf = 'E';
    }
    else
    {
        printf("Input tidak valid");
        return 0;
    }

    printf("Kamu mendapatkan nilai %c\n", huruf);

    return 0;
}
