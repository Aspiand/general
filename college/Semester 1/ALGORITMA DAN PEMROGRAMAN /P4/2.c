#include <stdio.h>
#include "../lib/getch.h"

int main()
{
    int num = 1;
    for (int i = 1; i <= 8; i++)
    {
        num *= 2;
        printf("2 pangkat %d = %d\n", i, num);
    }

    puts("");

    do
    {
        puts("Muhammad Aspiand - NIM");
    } while (getch() == 'a');

    {
        char jk;
        do
        {
            printf("Tekan 1 atau 0. \nTekan 2 untuk keluar. ? = ");
            jk = getch();
            jk == '1' ? puts("Laki-Laki") : puts("Perempuan");
        } while (jk != '2');
    }

    return 0;
}