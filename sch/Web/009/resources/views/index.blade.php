@extends("layout.app")
@section("content")
    <div class="m-8">
        <button id="add-button" class="btn w-full bg-gray-800" onclick="modal.showModal()">Add</button>
    </div>

    <div class="bg-gray-800 m-8 card">
        <div class="overflow-x-auto">
            <table class="table">
                <thead>
                    <tr class="text-center">
                        <th>No</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Stock</th>
                        <th>Description</th>
                        <th>-</th>
                    </tr>
                </thead>

                <tbody>
                    @foreach ($products as $product)
                        <tr class="text-center">
                            <th>{{$loop->iteration}}</th>
                            <td class="text-left">{{$product->name}}</td>
                            <td class="text-left">{{$product->price}}</td>
                            <td>{{$product->stock}}</td>
                            <td>{{$product->description}}</td>
                            <td>
                                <button class="modify-button btn rounded-3xl outline outline-1 bg-gray-800 hover:outline-0">
                                    Modify
                                </button>
                            </td>
                        </tr>
                    @endforeach
                </tbody>
            </table>
        </div>
    </div>

    <dialog id="modal" class="modal">
        <div class="modal-box bg-gray-800">

            <div class="relative p-4 w-full max-w-2xl h-full md:h-auto">
                <!-- Modal content -->

                <!-- Modal header -->
                <div class="flex justify-between items-center pb-4 mb-4 rounded-t border-b sm:mb-5 dark:border-gray-600">
                    <h3 id="modal-title" class="text-lg font-semibold text-gray-900 dark:text-white">
                        <<>> Product
                    </h3>
                </div>

                <!-- Modal body -->
                <form action="{{route("product.store")}}" method="POST">
                    @csrf

                    <div class="mb-5">
                        <label for="name" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Name</label>
                        <input type="text" name="name" id="name"
                            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-primary-500 dark:focus:border-primary-500"
                            placeholder="Type product name" required>
                    </div>

                    <div class="grid gap-4 mb-4 sm:grid-cols-2">
                        <div>
                            <label for="price"
                                class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Price</label>
                            <input type="number" name="price" id="price"
                                class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-primary-500 dark:focus:border-primary-500"
                                placeholder="17.000" required>
                        </div>

                        <div>
                            <label for="stock"
                                class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Stock</label>
                            <input type="number" name="stock" id="stock"
                                class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-primary-500 dark:focus:border-primary-500"
                                placeholder="3" required>
                        </div>

                        <div class="sm:col-span-2">
                            <label for="description"
                                class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Description</label>
                            <textarea id="description" rows="4"
                                class="block p-2.5 w-full text-sm text-gray-900 bg-gray-50 rounded-lg border border-gray-300 focus:ring-primary-500 focus:border-primary-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-primary-500 dark:focus:border-primary-500"
                                name="description" placeholder="Write product description here"></textarea>
                        </div>
                    </div>

                    <div class="flex justify-between">
                        <button class="btn rounded-3xl outline outline-1 bg-gray-800 hover:outline-0">
                            <svg class="mr-1 -ml-1 w-6 h-6" fill="currentColor" viewBox="0 0 20 20"
                                xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd"
                                    d="M10 5a1 1 0 011 1v3h3a1 1 0 110 2h-3v3a1 1 0 11-2 0v-3H6a1 1 0 110-2h3V6a1 1 0 011-1z"
                                    clip-rule="evenodd"></path>
                            </svg>
                            Save
                        </button>

                        <button type="button" id="delete-button"
                            class="btn rounded-3xl inline-flex items-center text-white bg-red-600 hover:bg-red-700 focus:ring-4 focus:outline-none focus:ring-red-300 font-medium text-sm px-5 py-2.5 text-center dark:bg-red-500 dark:hover:bg-red-600 dark:focus:ring-red-900">
                            <svg aria-hidden="true" class="w-5 h-5 mr-1.5 -ml-1" fill="currentColor" viewBox="0 0 20 20"
                                xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd"
                                    d="M9 2a1 1 0 00-.894.553L7.382 4H4a1 1 0 000 2v10a2 2 0 002 2h8a2 2 0 002-2V6a1 1 0 100-2h-3.382l-.724-1.447A1 1 0 0011 2H9zM7 8a1 1 0 012 0v6a1 1 0 11-2 0V8zm5-1a1 1 0 00-1 1v6a1 1 0 102 0V8a1 1 0 00-1-1z"
                                    clip-rule="evenodd"></path>
                            </svg>
                            Delete
                        </button>
                    </div>
                </form>
            </div>
        </div>
        <form method="dialog" class="modal-backdrop">
            <button>close</button>
        </form>
    </dialog>

    <script>
        modal_title = document.getElementById("modal-title")
        delete_button = document.getElementById("delete-button")
        document.getElementById("add-button").addEventListener("click", function () {
            delete_button.classList.add("hidden")
            modal_title.innerHTML = "Add Product"
        })

        Array.from(document.getElementsByClassName("modify-button")).forEach((button) => {
            button.addEventListener("click", function () {
                modal.showModal()
                delete_button.classList.remove("hidden")
                modal_title.innerHTML = "Modify Product"
            })
        })
    </script>
@endsection
