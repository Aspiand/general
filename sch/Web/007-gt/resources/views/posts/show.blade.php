<x-app-layout>
    <x-slot name="header">
        <h2 class="font-semibold text-xl text-gray-800 leading-tight">
            {{ __('Article') }}
        </h2>
    </x-slot>

    <main class="max-w-7xl mx-auto sm:px-6 lg:px-8 py-12">
        <form action="/posts/{{ $post->id }}" method="POST"
            class="relative border-s border-gray-200 dark:border-gray-700 bg-white overflow-hidden shadow-sm sm:rounded-lg p-6">

            @csrf
            @method('PUT')

            {{-- Title --}}
            <div class="mb-5">
                <label for="title-input"
                    class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Title</label>
                <input id="title-input" type="text" name="title" placeholder="Your new title here"
                    value="{{ $post->title }}" required
                    class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
            </div>

            {{-- Editor TinyMCE --}}
            <div class="mb-5">
                <textarea name="content">{{ $post->content }}</textarea>
            </div>

            <a href="{{ route('posts.index') }}">
                <button type="button"
                    class="text-gray-900 bg-white border border-gray-300 focus:outline-none hover:bg-gray-100 focus:ring-4 focus:ring-gray-100 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-gray-800 dark:text-white dark:border-gray-600 dark:hover:bg-gray-700 dark:hover:border-gray-600 dark:focus:ring-gray-700">Back</button>
            </a>

            <button id="delete-button" type="button"
                class="focus:outline-none text-white bg-red-700 hover:bg-red-800 focus:ring-4 focus:ring-red-300 font-medium rounded-lg text-sm px-4 py-2 me-2 mb-2 dark:bg-red-600 dark:hover:bg-red-700 dark:focus:ring-red-900">
                Delete
            </button>

            <button type="submit"
                class="text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-4 focus:ring-blue-300 font-medium rounded-full text-sm px-5 py-2.5 text-center me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                Save
            </button>

        </form>
    </main>

    {{-- Delete Form --}}
    <form id="delete-form" action="/posts/{{ $post->id }}" method="POST">
        @csrf
        @method('DELETE')

        <input type="hidden" name="id" value="{{ $post->id }}">
    </form>

    @if ($errors->any())
        @foreach ($errors->all() as $error)
            <?php flash()->flash('error', $error, [
                'position' => 'bottom-right',
            ]); ?>
        @endforeach
    @endif

    @push('after-scripts')
        <script>
            const delete_form = document.getElementById("delete-form")
            document.getElementById("delete-button").addEventListener("click", () => {
                delete_form.submit()
            })

            tinymce.init({
                selector: 'textarea',
                plugins: [
                    // Core editing features
                    'anchor', 'autolink', 'charmap', 'codesample', 'emoticons', 'image', 'link', 'lists', 'media',
                    'searchreplace', 'table', 'visualblocks', 'wordcount',
                    // Your account includes a free trial of TinyMCE premium features
                    // Try the most popular premium features until Dec 2, 2024:
                    'checklist', 'mediaembed', 'casechange', 'export', 'formatpainter', 'pageembed', 'a11ychecker',
                    'tinymcespellchecker', 'permanentpen', 'powerpaste', 'advtable', 'advcode', 'editimage',
                    'advtemplate', 'ai', 'mentions', 'tinycomments', 'tableofcontents', 'footnotes', 'mergetags',
                    'autocorrect', 'typography', 'inlinecss', 'markdown',
                    // Early access to document converters
                    'importword', 'exportword', 'exportpdf'
                ],
                toolbar: 'undo redo | blocks fontfamily fontsize | bold italic underline strikethrough | link image media table mergetags | addcomment showcomments | spellcheckdialog a11ycheck typography | align lineheight | checklist numlist bullist indent outdent | emoticons charmap | removeformat',
                tinycomments_mode: 'embedded',
                tinycomments_author: 'Author name',
                mergetags_list: [{
                        value: 'First.Name',
                        title: 'First Name'
                    },
                    {
                        value: 'Email',
                        title: 'Email'
                    },
                ],
                ai_request: (request, respondWith) => respondWith.string(() => Promise.reject(
                    'See docs to implement AI Assistant')),
                exportpdf_converter_options: {
                    'format': 'Letter',
                    'margin_top': '1in',
                    'margin_right': '1in',
                    'margin_bottom': '1in',
                    'margin_left': '1in'
                },
                exportword_converter_options: {
                    'document': {
                        'size': 'Letter'
                    }
                },
                importword_converter_options: {
                    'formatting': {
                        'styles': 'inline',
                        'resets': 'inline',
                        'defaults': 'inline',
                    }
                },
            });
        </script>
    @endpush

</x-app-layout>
