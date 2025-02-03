@extends('app')
@section('content')
<div class="p-20 flex justify-center">
    <div class="p-5 bg-gray-600 rounded-md">
        <h1 class="mb-4 text-4xl font-extrabold leading-none tracking-tight text-gray-900 dark:text-white text-center">
            Tambah Data
        </h1>

        <form action="/siswa/" method="POST">
            @csrf

            <input type="text" placeholder="Nama" name="nama" class="input input-bordered input-primary w-full mb-2" />

            <select class="select select-primary w-full mb-2" name="kelas">
                <option disabled selected>Kelas</option>
                @foreach (["X", "XI", "XII"] as $kelas)
                    @foreach (["RPL", "Kecantikan", "Boga"] as $jurusan)

                        <option value="{{implode(" ", [$kelas, $jurusan])}}">
                            {{implode(" ", [$kelas, $jurusan])}}
                        </option>

                    @endforeach
                @endforeach
            </select>

            <button type="submit" class="btn btn-outline btn-primary w-full">Simpan</button>
        </form>
    </div>
</div>

@endsection
