# Analisa perhitungan
# Untuk menghitung pangkat dari suatu bilangan, bilangan tersebut
# perlu dikalikan dengan dirinya sendiri sebanyak pangkat.
# Dalam kasus ini terdapat satu input yang bertipe integer.

def pangkat(bilangan: int, pangkat: int) -> int:
    hasil = 1

    for i in range(pangkat):
        hasil *= bilangan

    return hasil

bilangan = int(input("Masukkan sebuah bilangan: "))
print(f"Pangkat 2: {pangkat(bilangan, 2)}")
print(f"Pangkat 3: {pangkat(bilangan, 3)}")
