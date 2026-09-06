// gcc 2.c && ./a.out | iconv -f CP437 -t UTF-8

#include <stdio.h>

int main()
{
    printf("Belajar");
    printf("Bahasa C");

    printf("\n");

    puts("Belajar");
    puts("Bahasa C");

    printf("Belajar \tBahasa C");

    printf("\n");

    printf("Hello\rHi");

    printf("\n");

    printf("Kode ascii dari AB = \xAB");

    printf("\n");

    printf("Kode ascii dari 254 oktal = \253");

    printf("\n");

    printf("\xC9\xCD\xCD\xCD\xCD\xCD\xBB\r\n");
    printf("\xBA C++ \xBA\r\n");
    printf("\xC8\xCD\xCD\xCD\xCD\xCD\xBC\r\n");

    printf("Nilai = %i", 100);

    printf("\n");

    printf("Struk Belanja\n");
    printf("Teh   %6i\n", 300);
    printf("Gula  %6i\n", 5000);
    printf("Beras %6i\n", 25000);

    printf("Nilai pi = %6.2f", 3.1415);

    printf("\n");

    char x;
    x = 'A';
    // scanf("%c", &x);

    printf("Nilai x = %c Ascii\n", x);
    printf("Nilai x = %i desimal\n", x);
    printf("Nilai x = %x hexa\n", x);
    printf("Nilai x = %o oktal\n", x);

    int y;
    puts("Ngitung kuadrat");
    printf("Masukkan sebuah angka (maks 46340) = ");
    // scanf("%i", &y);
    y = 46341;
    printf("%i\xFD = %i", y, y * y);

    printf("\n");

    float z;
    puts("Bermain bilangan real");
    printf("Masukkan sebuah angka = ");
    // scanf("%f", &z);
    z = 9;
    printf("Nilai dibagi 2 = %f float\n", z / 2);

    printf("\n");

    float jumlah_pertemuan, kehadiran;
    printf("Masukkan jumlah pertemuan = ");
    // scanf("%f", &jumlah_pertemuan);
    jumlah_pertemuan = 8;
    kehadiran = (jumlah_pertemuan / 16) * 100;
    printf("Kehadiran anda = %3.2f%%\n", kehadiran);

    return 0;
}
