#include <stdio.h>

#define pi 3.14

int main()
{
    int x, y;
    puts("Belajar Input dengna scanf()");
    printf("Masukkan dua angka dipisah spasi = ");
    // scanf("%i %i", &x, &y);
    // scanf("%i,%i", &x, &y);
    x = 30;
    y = 40;
    printf("Anda memasukkan %i dan %i", x, y);

    printf("\n");

    float z;
    puts("Belajar Input dengan scanf() variable float");
    printf("Masukkan satu angka real = ");
    // scanf("%f", &z);
    z = 3.146;
    printf("Tanpa Format, nilai z = %f\n", z);
    printf("Dengan Format, nilai z = %6.2f\n", z);
    printf("Dipaksa format integer = %i\n", z);

    // printf("Perintah pertama");
    // // getch(); // tak de
    // // getche();
    // printf("Perintah kedua\n");

    // printf("\n");

    float r;
    puts("Hitung keliling lingkaran");
    printf("Masukkan jari-jari = ");
    // scanf("%f", &r);
    r = 8;
    printf("Keliling = %f\n", 2 * pi * r);

    int p;
    puts("Program IF tunggal");
    printf("Masukkan angka = ");
    // scanf("%i", &p);
    p = 0;
    if (p > 0)
        printf("Angka Positif\n");
    else if (p == 0)
        printf("NOL\n");
    else
        printf("Angka Nigatif\n");

    char u;
    puts("Konversi Tulisan ke angka");
    // printf("Tekan sebuah angka 0 sampai 3 = ");
    // u = getche();
    u = '1';
    if (u == '0')
        printf("Anda Menekan NOL\n");
    else if (u == '1')
        printf("Anda Menekan Satu\n");
    else if (u == '2')
        printf("Anda Menekan Dua\n");
    else if (u == '3')
        printf("Anda Menekan Tiga\n");
    else
        printf("IDK\n");

    switch (u)
    {
    case '0':
        printf("Anda Menekan NOL\n");
        break;
    case '1':
        printf("Anda Menekan Satu\n");
        break;
    case '2':
        printf("Anda Menekan Dua\n");
        break;
    case '3':
        printf("Anda Menekan Tiga\n");
        break;
    default:
        printf("IDK\n");
    }

    int a, b;
    puts("Memastikan kedua input positif");
    printf("Masukkan dua bilangan dipisah spasi = ");
    scanf("%i %i", &a, &b);
    if (a > 0 && b > 0)
    {
        puts("Kedua input positif");
    }
    else
    {
        puts("Ada yang salah");
    }

    return 0;
}