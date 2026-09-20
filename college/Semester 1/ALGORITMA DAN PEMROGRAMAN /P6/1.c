#include "../lib/getch.h"

void send(int num)
{
    printf("You send %i\n", num);
    printf("Press enter to continue");
    getch();
}

int main()
{
    int value;

    send(value);

    printf("\nEnter some random number: ");
    scanf("%i", &value);
    send(value);

    return 0;
}