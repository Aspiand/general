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
        <div class="flex-1">
            <a class="btn btn-ghost text-xl">Aspiand</a>
        </div>
        <div class="flex-none">
            <ul class="menu menu-horizontal px-1">
                <li><a href="https://github.com/Aspiand/">Github</a></li>
                <li>
                    <details>
                        <summary>Parent</summary>
                        <ul class="bg-base-100 rounded-t-none p-2">
                            <li><a href="https://immich.aspian.my.id/">Immich</a></li>
                            <li><a>Link 2</a></li>
                        </ul>
                    </details>
                </li>
            </ul>
        </div>
    </div>

    @yield('content')

</body>

</html>
