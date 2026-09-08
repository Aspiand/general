#include <stdio.h>

int main()
{
    float nilai;
    char huruf;

    printf("Masukkan Nilai = ");
    scanf("%f", &nilai);

    if (nilai >= 80 && nilai <= 100)
    {
        huruf = 'A';
    }
    else if (nilai >= 60 && nilai <= 79.999)
    {
        huruf = 'B';
    }
    else if (nilai >= 50 && nilai <= 59.999)
    {
        huruf = 'C';
    }
    else if (nilai >= 40 && nilai <= 49.999)
    {
        huruf = 'D';
    }
    else if (nilai >= 0 && nilai <= 39.999)
    {
        huruf = 'E';
    }

    printf("Kamu mendapatkan nilai %c\n", huruf);
}