@extends('layout.app')
@section('content')
<div class="container mx-auto p-5">
    {{-- by gpt --}}
    <div class="grid md:grid-cols-2 lg:grid-cols-3 gap-5">
        @foreach ($data as $row)
            <div class="card bg-gray-800 text-white shadow-lg p-5">
                <div class="card-body">
                    <h2 class="card-title text-lg font-bold">Nama: {{$row->nama}}</h2>
                    <p class="text-gray-300">Kelas: {{$row->kelas}}</p>

                    <div class="flex gap-3 mt-4">
                        <a href="{{ route('siswa.show', $row->id) }}" class="btn btn-success text-white px-4 py-2">
                            Ubah
                        </a>

                        <form action="{{ route('siswa.destroy', $row->id) }}" method="POST">
                            @csrf
                            @method('delete')

                            <button type="submit" class="btn btn-error text-white px-4 py-2">
                                Hapus
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        @endforeach
    </div>
</div>
@endsection
