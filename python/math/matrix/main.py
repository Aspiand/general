from random import randint


class Matrix:
    def __init__(self, matrix: list[list]):
        self.__matrix = matrix

    def __getitem__(self, index: int):
        return self.__matrix[index]

    def __mul__(self, multiply: "Matrix") -> list[list]:
        return [[multiply * item for item in row] for row in self.__matrix]

    @property
    def value(self) -> list[list]:
        return self.__matrix

    @property
    def row(self) -> int:
        return len(self.__matrix)

    @property
    def column(self) -> int:
        return len(self.__matrix[0])

    @property
    def ordo(self) -> tuple:
        return self.row, self.column

    @property
    def transpose(self) -> list[list]:
        tmp = []
        for k in range(len(self.__matrix[0])):
            tmp2 = []
            for i in range(len(self.__matrix)):
                for j in range(len(self.__matrix[i])):
                    tmp2.append(self.__matrix[i][j + k])
                    break

            tmp.append(tmp2)
            tmp2 = []

        return tmp

    @classmethod
    def generate(cls, rows: int, cols: int, start: int = 0, stop: int = 1) -> "Matrix":
        return Matrix(
            [[randint(start, stop) for _ in range(cols)] for _ in range(rows)]
        )

    @classmethod
    def add(cls, a: "Matrix", b: "Matrix"):
        if a.ordo != b.ordo:
            raise TypeError

        result = []

        for row in range(len(a.value)):
            for col in range(len(a.value[0])):
                result.append([a.value[row][col] + b.value[row][col]])

        return result

        # List Comprehension
        # return [[a.value[row][col] + b.value[row][col] for col in range(len(a.value[0]))] for row in range(len(a.value))]

    @classmethod
    def sub(cls, a: "Matrix", b: "Matrix"):
        if a.ordo != b.ordo:
            raise TypeError

        result = []

        for row in range(len(a.value)):
            for col in range(len(a.value[0])):
                result.append([a.value[row][col] - b.value[row][col]])

        return result

        # List Comprehension
        # return [[a.value[row][col] - b.value[row][col] for col in range(len(a.value[0]))] for row in range(len(a.value))]

    @classmethod
    def multiply(cls, a: "Matrix", b: "Matrix"):
        if a.ordo[1] != b.ordo[0]:
            raise TypeError

        # Membuat matrix yang berisi None dengan ukuran a * c
        # sesuai dengan aturan matrix -> a * b . b * c = a * c
        result: list[list] = [
            [None for _ in range(a.ordo[0])] for _ in range(b.ordo[1])
        ]
        b = Matrix(b.transpose)

        for row in range(len(a.value)):
            for loop in range(len(b.value)):
                # for col in range(len(a.value[0])):
                #     print(f"{a.value[row][col]} * {b.value[loop][col]} = {a.value[row][col] * b.value[loop][col]}")

                result[row][loop] = sum(
                    [
                        a.value[row][col] * b.value[loop][col]
                        for col in range(len(a.value[0]))
                    ]
                )

        return result


if __name__ == "__main__":
    A = Matrix([[1, 2, 3], [4, 5, 6]])

    B = Matrix([[7, 6, 5], [4, 3, 2]])

    C = Matrix(
        [
            [11],
            [12],
        ]
    )

    D = Matrix([[1, 2], [3, 4]])

    E = Matrix([[6, 5], [4, 3], [2, 1]])

    F = Matrix.generate(2, 2, 0, 10)
