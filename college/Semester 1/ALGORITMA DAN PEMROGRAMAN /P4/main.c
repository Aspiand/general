#include <stdio.h>
#include "./lib/getch.h"

int main()
{
    for (int i = 1; i <= 10; i++)
    {
        for (int j = 0; j <= 10; j++)
        {
            printf("%d x %d = %d\n", i, j, j * i);
        }

        printf("Continue? (Y/n) ");

        if (getche() == 'n')
        {
            break;
        }

        printf("\n");
    }

    return 0;
}
