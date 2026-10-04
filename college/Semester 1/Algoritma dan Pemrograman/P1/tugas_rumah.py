# Dalam kasus ini diperlukan 2 input, yaitu panjang dan lebar.
# Setelah mendapatkan itu, luas dapat dihitung dengan
# menggunakan rumus panang * lebar dan keliling dapat
# dihitung dengan 2 * (panjang + lebar)

def luas_persegi_panjang(p: int, l: int) -> int:
    return p * l

def keliling_persegi_panjang(p: int, l: int) -> int:
    return 2 * (p + l)

def get_input(text: str) -> int:
    while True:
        try:
            return int(input(text))
        except KeyboardInterrupt:
            exit(0)
        except:
            print("Invalid input, try again.")

print(f"{"="*5} Program Hitung Luas dan Keliling Persegi Panjang {"="*5}")
for k,v in {
    "Nama": "Muhammad Aspian",
    "NIM": "mynim",
    "Kelas": "1A Sistem Informasi Kota Cerdas",
}.items():
    print(f"{k}\t: {v}")
print("="*60)

panjang = get_input("Masukkan panjang: ")
lebar = get_input("Masukkan lebar: ")

luas = luas_persegi_panjang(panjang, lebar)
keliling = keliling_persegi_panjang(panjang, lebar)

print(f"Luas Persegi Panjang: {luas}")
print(f"Keliling Persegi Panjang: {keliling}")
