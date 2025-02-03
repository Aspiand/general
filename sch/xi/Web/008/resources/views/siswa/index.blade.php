@extends('app')
@section('content')
<div>

    @foreach ($data as $row)
        <div class="card m-5 bg-slate-600">
            <div class="card-body">
                Nama: {{$row->nama}} <br>
                Kelas: {{$row->kelas}} <br>

                <div class="flex gap-2">
                    <a href="{{route("siswa.show", $row->id)}}">
                        <button class="btn btn-success text-white">Ubah</button>
                    </a>

                    <form action=" {{route("siswa.destroy", $row->id)}}" method="POST">
                        @csrf
                        @method('delete')

                        <button class="btn btn-error w-50 h-10 text-white">Hapus</button>
                    </form>
                </div>
            </div>
        </div>
    @endforeach

</div>
@endsection
