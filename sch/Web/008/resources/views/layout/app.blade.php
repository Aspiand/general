<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Latihan 7</title>
    @vite('resources/css/app.css')
</head>

<body>

    <div class="navbar bg-base-100">
        <div class="navbar-start">
            <div class="dropdown">
                <div tabindex="0" role="button" class="btn btn-ghost lg:hidden">
                    <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24"
                        stroke="currentColor">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                            d="M4 6h16M4 12h8m-8 6h16" />
                    </svg>
                </div>
                <ul tabindex="0"
                    class="menu menu-sm dropdown-content bg-base-100 rounded-box z-[1] mt-3 w-52 p-2 shadow">

                    <li><a href="{{route("siswa.index")}}">Home</a></li>
                    <li><a href="https://github.com/Aspiand">Github</a></li>
                </ul>
            </div>
            <a class="btn btn-ghost text-xl" href="https://github.com/Aspiand">Aspian</a>
        </div>
        <div class="navbar-center hidden lg:flex">
            <ul class="menu menu-horizontal px-1">
                <li><a href="{{route("siswa.index")}}">Home</a></li>
                <li><a href="https://github.com/Aspiand">Github</a></li>
            </ul>
        </div>

        <div class="navbar-end pe-5">
            <a class="btn btn-outline rounded-full" href="{{route("siswa.create")}}">Tambah</a>
        </div>
    </div>

    @yield('content')

</body>

</html>
