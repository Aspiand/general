#include <stdio.h>

int mysqrt(int num)
{
    for (int i = 1;; i++)
    {
        if (i * i == num)
        {
            return i;
        }
    }
}

int main()
{
    printf("Akar 225: %d\n", mysqrt(225));

    return 0;
}