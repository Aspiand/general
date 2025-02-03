@extends('layout.app')
@section('content')
<div class="p-20 flex justify-center">
    <div class="p-5 bg-gray-600 rounded-xl">
        <h1 class="p-5 mb-4 text-4xl font-extrabold leading-none tracking-tight text-gray-900 dark:text-white text-center">
            {{ $title }}
        </h1>

        <form action="{{ $action }}" method="POST">
            @csrf

            @if (isset($siswa))
                @method("PUT")
            @endif

            <input type="text" placeholder="Nama" name="nama" value="{{ $siswa->nama ?? '' }}"
                class="input input-bordered input-primary w-full mb-2" />

            <select class="select select-primary w-full mb-2" name="kelas">
                <option disabled selected>Kelas</option>
                @foreach (["X", "XI", "XII"] as $kelas)
                    @foreach (['RPL', 'Kecantikan', 'Boga', 'Musik'] as $jurusan)
                        @for ($ruangan = 1; $ruangan <= 3; $ruangan++)
                            @php
                                $value = implode(" ", [$kelas, $jurusan, $ruangan]);
                            @endphp

                            <option value="{{$value}}" {{ isset($siswa) && $siswa->kelas == $value ? "selected" : ''}}>
                                {{$value}}
                            </option>
                        @endfor
                    @endforeach
                @endforeach
            </select>

            <button type="submit" class="btn btn-outline btn-primary w-full">Simpan</button>
        </form>
    </div>
</div>

@if ($errors->any())
    @foreach ($errors->all() as $error)
        @php
            flash()->flash("error", $error)
        @endphp
    @endforeach
@endif

@endsection
