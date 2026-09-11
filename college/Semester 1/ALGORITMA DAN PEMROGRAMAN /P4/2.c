#include <stdio.h>

int main()
{
    int num = 1;
    for (int i = 1; i <= 8; i++)
    {
        num *= 2;
        printf("2 pangkat %d = %d\n", i, num);
    }

    return 0;
}