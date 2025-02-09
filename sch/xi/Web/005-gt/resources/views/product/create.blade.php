<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Product - Create</title>

    @vite(['resources/css/app.css', 'resources/js/app.js'])
</head>

<body>

    <main class="min-h-screen w-full bg-gray-200 p-8 grid place-items-center">
        <section class="bg-white p-8 rounded mx-auto" style="width: 35%">
            <div class="text-center mb-5">
                <h2>Tambar Produk</h2>
            </div>

            <form action="/products" method="POST">
                @csrf

                <div class="mb-2">
                    <label for="" class="text-sm text-gray-500">Nama Produk</label>
                    <input class="bg-gray-100 w-full" type="text" name="name" value="{{ old('name') }}">

                    @error('name')
                        <p class="text-sm text-red-500">{{ $message }}</p>
                    @enderror
                </div>

                <div class="mb-2">
                    <label for="" class="text-sm text-gray-500">Harga Produk</label>
                    <input class="bg-gray-100 w-full" type="text" name="price" value="{{ old('price') }}">

                    @error('price')
                        <p class="text-sm text-red-500">{{ $message }}</p>
                    @enderror
                </div>

                <div class="mb-2">
                    <label class="text-sm text-gray-500">Deskrpsi Produk</label>
                    <textarea class="bg-gray-100 w-full" type="text" name="description">
                        {{ old('description') }}
                    </textarea>

                    @error('description')
                        <p class="text-sm text-red-500">{{ $message }}</p>
                    @enderror
                </div>

                <button class="bg-blue-500 w-full p-3 text-gray-200" type="submit">Simpan Produk</button>

            </form>
        </section>
    </main>

    @if ($errors->any())
        @foreach ($errors->all() as $error)
            <?php flash()->flash('error', $error, [
                'position' => 'bottom-right',
            ]); ?>
        @endforeach
    @endif

</body>

</html>
