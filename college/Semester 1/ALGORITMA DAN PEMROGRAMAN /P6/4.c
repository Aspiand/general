#include <stdio.h>
#include <stdlib.h>
#include "../lib/getch.h"

void about_me()
{
    system("clear");
    puts("Name: Muhammad Aspian");
    puts("NIM : ");
    puts("Press enter to continue");
    getch();
}

void calculate_rectangle_area()
{
    system("clear");

    int lenght, width;

    while (1)
    {
        puts("\nProgram to calculate rectangle area");
        printf("Enter lenght and width (separated by space): ");
        scanf("%i %i", &lenght, &width);
        printf("Area of recrangle: %d\n", lenght * width);

        int c;
        while ((c = getchar()) != '\n' && c != EOF)
            ;

        printf("Exit? (y/N): ");
        if (getche() == 'y')
        {
            break;
        }
    }
}

int main()
{
    while (1)
    {
        system("clear");
        puts("========================================");
        puts("Simple app written in \033[9mrust\033[0m c");
        puts("========================================");
        puts("<< Main Menu >>");
        puts("1. About Me");
        puts("2. Calculate the area of the rectangle.");
        puts("3. Exit");

        printf("Choose: ");
        switch (getche())
        {
        case '1':
            about_me();
            break;
        case '2':
            calculate_rectangle_area();
            break;
        case '3':
            printf("\nYou sure? (y/N) ");
            if (getch() == 'y')
            {
                return 0;
            }
            break;
        default:
            puts("Invalid input, try again: ");
            break;
        }
    }
}