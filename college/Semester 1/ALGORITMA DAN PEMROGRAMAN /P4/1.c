#include <stdio.h>

int main()
{
    puts("Program tebak angka");
    puts("Anda hanya memiliki 3 kesempatan memasukkan angka");

    int the_number = 5;
    int input_number;

    for (int i = 1; i <= 3; i++)
    {
        printf("Kesempatan %d. Masukkan angka = ", i);
        scanf("%d", &input_number);

        if (the_number == input_number)
        {
            puts("BENAR");
            break;
        }

        puts("Salah");
    }

    return 0;
}
