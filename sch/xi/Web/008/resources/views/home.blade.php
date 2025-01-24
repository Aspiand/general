@extends('app')
@section('content')
<div class="container">

    @foreach ($data as $row)
        <div class="card mb-1">
            <div class="card-body">
                Nama: {{$row->nama}}
                Kelas: {{$row->kelas}}
            </div>
        </div>
    @endforeach
</div>
@endsection
