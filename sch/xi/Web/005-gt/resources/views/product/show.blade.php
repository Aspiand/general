<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>{{ $product->name }}</title>

    @vite(['resources/css/app.css', 'resources/js/app.js'])
</head>

<body>

    <main class="min-h-screen w-full bg-gray-200 p-8 grid place-items-center">
        <section class="bg-white p-8 rounded mx-auto" style="width: 35%">
            <div class="text-center mb-5">
                <h2>Update Produk</h2>
            </div>

            {{-- Update Form --}}
            <form action="/products/{{ $product->id }}" method="POST">
                @csrf
                @method('PUT')

                <div class="mb-2">
                    <label for="" class="text-sm text-gray-500">Nama Produk</label>
                    <input class="bg-gray-100 w-full" type="text" name="name" value="{{ $product->name }}">

                    @error('name')
                        <p class="text-sm text-red-500">{{ $message }}</p>
                    @enderror
                </div>

                <div class="mb-2">
                    <label for="" class="text-sm text-gray-500">Harga Produk</label>
                    <input class="bg-gray-100 w-full" type="text" name="price" value="{{ $product->price }}">

                    @error('price')
                        <p class="text-sm text-red-500">{{ $message }}</p>
                    @enderror
                </div>

                <div class="mb-2">
                    <label class="text-sm text-gray-500">Deskrpsi Produk</label>
                    <textarea class="bg-gray-100 w-full" type="text" name="description">
                        {{ $product->description }}
                    </textarea>

                    @error('description')
                        <p class="text-sm text-red-500">{{ $message }}</p>
                    @enderror
                </div>

                <div class="flex justify-between gap-3">

                    {{-- Save --}}
                    <button class="bg-blue-500 w-full p-3 text-white" type="submit">
                        Perbarui Produk
                    </button>

                    {{-- Delete --}}
                    <button id="button-delete" class="bg-red-500 w-full p-3 text-white" type="button">
                        Hapus Produk
                    </button>

                </div>

            </form>

            {{-- Delete Form --}}
            <form id="form-delete-product" action="/products/{{ $product->id }}" method="POST">
                @csrf
                @method('DELETE')

                <input type="hidden" name="id" value="{{ $product->id }}">
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

    <script>
        window.addEventListener("DOMContentLoaded", () => {
            const form_delete = document.getElementById("form-delete-product")
            const button_delete = document.getElementById("button-delete")

            button_delete.addEventListener("click", () => {
                const is_delete = confirm("are you sure to delete this product?")

                if (is_delete) {
                    form_delete.submit()
                }
            })
        });
    </script>

</body>

</html>
