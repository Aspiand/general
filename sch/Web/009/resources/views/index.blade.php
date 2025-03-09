@extends("layout.app")
@section("content")
    <div class="bg-gray-800 m-8 card">
        <div class="overflow-x-auto">
            <table class="table">
                <thead class="text-white font-bold">
                    <tr class="text-center">
                        <th>No</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Stock</th>
                        <th>Description</th>
                        <th>Created</th>
                        <th>Updated</th>
                        <th>-</th>
                    </tr>
                </thead>

                <tbody>
                    @foreach ($products as $product)
                        <tr class="text-left">
                            <th class="text-center">{{$loop->iteration}}</th>
                            <td>{{$product->name}}</td>
                            <td>{{$product->price}}</td>
                            <td class="text-center">{{$product->stock}}</td>
                            <td>{{$product->description}}</td>
                            <td class="text-center">{{$product->created_at}}</td>
                            <td class="text-center">{{$product->updated_at}}</td>
                            <td class="text-center">
                                <button value="{{$product->id}}"
                                    class="modify-button btn rounded-3xl outline outline-1 bg-gray-800 hover:outline-0">
                                    <svg class="w-5 h-5 text-white" fill="currentColor" viewBox="0 0 20 20"
                                        xmlns="http://www.w3.org/2000/svg">
                                        <path
                                            d="M17.414 2.586a2 2 0 00-2.828 0L7 10.172V13h2.828l7.586-7.586a2 2 0 000-2.828zM6 12v2H4v-2h2zm-1 3h2v2H5v-2z">
                                        </path>
                                    </svg>
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
                <form method="POST" id="main-form">
                    @csrf

                    <input id="id" type="hidden" name="id">
                    <input id="form-method" type="hidden" name="_method">

                    <div class="mb-5">
                        <label for="name" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Name</label>
                        <input type="text" name="name" id="name"
                            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-primary-500 dark:focus:border-primary-500"
                            placeholder="Type product name" autocomplete="off" required>
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

                    <button class="btn w-full mb-3 rounded-3xl outline outline-1 bg-gray-800 hover:outline-0">
                        <svg class="mr-1 -ml-1 w-6 h-6" fill="currentColor" viewBox="0 0 20 20"
                            xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd"
                                d="M10 5a1 1 0 011 1v3h3a1 1 0 110 2h-3v3a1 1 0 11-2 0v-3H6a1 1 0 110-2h3V6a1 1 0 011-1z"
                                clip-rule="evenodd"></path>
                        </svg>
                        Save
                    </button>

                </form>

                <form id="delete-form" method="POST">
                    @csrf
                    @method("DELETE")

                    <button type="button" id="delete-button"
                        class="btn w-full rounded-3xl inline-flex items-center text-white bg-red-600 hover:bg-red-700 focus:ring-4 focus:outline-none focus:ring-red-300 font-medium text-sm px-5 py-2.5 text-center dark:bg-red-500 dark:hover:bg-red-600 dark:focus:ring-red-900">
                        <svg aria-hidden="true" class="w-5 h-5 mr-1.5 -ml-1" fill="currentColor" viewBox="0 0 20 20"
                            xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd"
                                d="M9 2a1 1 0 00-.894.553L7.382 4H4a1 1 0 000 2v10a2 2 0 002 2h8a2 2 0 002-2V6a1 1 0 100-2h-3.382l-.724-1.447A1 1 0 0011 2H9zM7 8a1 1 0 012 0v6a1 1 0 11-2 0V8zm5-1a1 1 0 00-1 1v6a1 1 0 102 0V8a1 1 0 00-1-1z"
                                clip-rule="evenodd"></path>
                        </svg>
                        Delete
                    </button>

                </form>

            </div>
        </div>

        <form method="dialog" class="modal-backdrop">
            <button>close</button>
        </form>
    </dialog>

    <script>
        document.addEventListener("DOMContentLoaded", () => {
            const mainForm = document.getElementById("main-form");
            const formMethod = document.getElementById("form-method");
            const modalTitle = document.getElementById("modal-title");
            const deleteButton = document.getElementById("delete-button");
            const deleteForm = document.getElementById("delete-form");
            const inputName = document.getElementById("name");
            const inputPrice = document.getElementById("price");
            const inputStock = document.getElementById("stock");
            const inputDescription = document.getElementById("description");
            const createRoute = "{{route('product.store')}}";
            const updateRoute = "{{route('product.update', ':id')}}";
            const deleteRoute = "{{route('product.destroy', ':id')}}";

            const showModal = (title, method, action, product = {}) => {
                modal.showModal();
                modalTitle.innerHTML = title;
                formMethod.value = method;
                mainForm.action = action;

                inputName.value = product.name || "";
                inputPrice.value = product.price || "";
                inputStock.value = product.stock || "";
                inputDescription.value = product.description || "";

                if (method === "POST") {
                    deleteButton.classList.add("hidden");
                } else {
                    deleteButton.classList.remove("hidden");
                    deleteButton.value = product.id;
                }
            };

            document.getElementById("cart").addEventListener("click", () => {
                showModal("Add Product", "POST", createRoute);
            });

            deleteButton.addEventListener("click", function () {
                deleteForm.action = deleteRoute.replace(':id', this.value);
                deleteForm.submit();
            });

            document.querySelectorAll(".modify-button").forEach(button => {
                button.addEventListener("click", function () {
                    const row = this.closest("tr");
                    showModal("Modify Product", "PUT", updateRoute.replace(':id', this.value), {
                        id: this.value,
                        name: row.cells[1].textContent.trim(),
                        price: row.cells[2].textContent.trim(),
                        stock: row.cells[3].textContent.trim(),
                        description: row.cells[4].textContent.trim()
                    });
                });
            });
        });
    </script>
@endsection
