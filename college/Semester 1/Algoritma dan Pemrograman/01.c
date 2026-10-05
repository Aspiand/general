#include <stdio.h>
#include <stdlib.h>
#include "./lib/getch.h"

int main()
{
    while (1)
    {
        for (int i = 1; i <= 20; i++)
        {

            if (i < 6)
            {
                printf("%2i. Saya bisa bahasa C\n", i);
            }
            else if (i < 11)
            {
                printf("%2i. Nim Saya C123456\n", i);
            }
            else if (i < 16)
            {
                printf("%2i. Nama Saya Muhammad Aspian\n", i);
            }
            else
            {
                printf("%2i. Akhirnya Bisa\n", i);
            }
        }

        printf("Exit? (y/N): ");
        if (getche() == 'y')
        {
            break;
        }
        printf("\n");
    }

    return 0;
}