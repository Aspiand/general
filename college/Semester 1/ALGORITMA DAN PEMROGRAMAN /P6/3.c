#include <stdio.h>

void square(int lenght, int height)
{
    printf("|");

    for (int i = 0; i < lenght; i++)
    {
        printf("-");
    }
    printf("|\n");

    for (int j = 0; j < height; j++)
    {
        printf("|");
        for (int k = 0; k < lenght; k++)
        {
            printf(" ");
        }
        printf("|\n");
    }

    printf("|");
    for (int l = 0; l < lenght; l++)
    {
        printf("-");
    }
    printf("|\n");
}

int main()

{
    int lenght, height;
    printf("Drawing square\nLenght max 70, height max 10.\n");
    printf("Enter lenght and height, separated with space: ");
    scanf("%i %i", &lenght, &height);

    square(lenght, height);

    return 0;
}