from typing import Self
from pprint import pprint

class Matrix:
    def __init__(self, matrix: list[list[int]]):
        self.__matrix = matrix

    def __getitem__(self, index: int):
        return self.__matrix[index]

    def __add__(self, add: Self) -> list[list[int]]:
        if self.ordo != add.ordo:
            raise TypeError

        return [[self.__matrix[i][j] + add[i][j] for j in range(self.column)] for i in range(self.row)]

    @property
    def row(self) -> int:
        return self.__matrix.__len__()

    @property
    def column(self) -> int:
        return self.__matrix[0].__len__()

    @property
    def ordo(self) -> tuple:
        return self.row, self.column

    @property
    def transpose(self) -> list[list[int]]:
        return [[self.__matrix[j][i] for j in range(self.row)] for i in range(self.column)]

    def print(self):
        pprint(self.__matrix)

if __name__ == "__main__":
    m = Matrix([[1, 2, 3], [4, 5, 6], [7, 8, 9]])
    # m.transpose.print()