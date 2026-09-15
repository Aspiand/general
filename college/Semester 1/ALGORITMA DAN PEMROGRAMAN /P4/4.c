#include <stdio.h>
#include "../lib/getch.h"

int main()
{
    int tahun_sekarang = 2026, tahun_lahir;

    while (1)
    {
        printf("Masukkan tahun lahir: ");
        scanf("%d", &tahun_lahir);
        getchar();

        printf("Umur anda = %d tahun\n", tahun_sekarang - tahun_lahir);

        printf("Mau mengulang? (Y/n) ");

        if (getch() == 'n')
        {
            break;
        }

        printf("\n");
    }

    return 0;
}