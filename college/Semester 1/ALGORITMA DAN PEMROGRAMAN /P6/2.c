#include <stdio.h>

int kali_dua(int x)
{
    return x * 2;
}

int main()
{
    int a = kali_dua(500), b;

    printf("Hasil kali dua dari fungsi: %i\n", a);
    printf("Hasil langsung dari fungsi: \n", kali_dua(100));

    printf("\nMasukkan angka: ");
    scanf("%i", &b);
    printf("Nilai %i dikali 2 = %i", b, kali_dua(b));

    return 0;
}