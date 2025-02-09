<x-app-layout>
    <x-slot name="header">
        <h2 class="font-semibold text-xl text-gray-800 leading-tight">
            {{ __('Article') }}
        </h2>
    </x-slot>

    <main class="max-w-7xl mx-auto sm:px-6 lg:px-8 py-12">
        <form action="/posts" method="POST"
            class="relative border-s border-gray-200 dark:border-gray-700 bg-white overflow-hidden shadow-sm sm:rounded-lg p-6">

            @csrf

            {{-- Title --}}
            <div class="mb-5">
                <label for="title-input"
                    class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Title</label>
                <input id="title-input" type="text" name="title" placeholder="Your new title here" required
                    class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
            </div>

            {{-- Editor TinyMCE --}}
            <div class="mb-5">
                <textarea name="content"></textarea>
            </div>

            <button type="submit"
                class="text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-4 focus:ring-blue-300 font-medium rounded-full text-sm px-5 py-2.5 text-center me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                Save
            </button>

        </form>
    </main>

    @push('after-scripts')
        <script>
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
