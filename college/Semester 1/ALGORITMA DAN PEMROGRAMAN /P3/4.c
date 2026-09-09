#include <stdio.h>
#include "../lib/getch.h"

int main()
{
    // printf("Perintah pertama.\nTekan sembarang\n");
    // getche();
    // printf("Perintah kedua\n");

    char x;
    puts("Input dengan scanf(), getch(), getche() dengan char");

    printf("Tekan sembarang karakter sekali, akhiri dengan enter = ");
    scanf("%c", &x);
    printf("Anda menekan %c\n", x);

    // scanf("%c") hanya mengambil 1 karakter tapi menyisakan \n di buffer.
    // getche() langsung membaca sisa itu. getchar() di atas
    // bergunakan "membersihkan" buffer sebelum getche() dipanggil.
    getchar();
    printf("Tekan sembarang karakter sekali. ");
    x = getche();
    printf("Anda menekan %c\n", x);

    printf("Tekan sembarang karakter sekali. ");
    x = getch();
    printf("Anda menekan %c\n", x);

    return 0;
}
