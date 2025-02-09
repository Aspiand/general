<x-app-layout>
    <x-slot name="header">
        <h2 class="font-semibold text-xl text-gray-800 leading-tight">
            {{ __('Timeline') }}
        </h2>
    </x-slot>

    {{-- Container --}}
    <main class="max-w-7xl mx-auto sm:px-6 lg:px-8 py-12">

        {{-- Timeline Container --}}
        <ol class="relative border-s border-gray-200 dark:border-gray-700">
            <div class="bg-white overflow-hidden shadow-sm sm:rounded-lg pt-6">

                @foreach ($posts as $post)
                    <li class="mb-10 ms-4">
                        <div
                            class="absolute w-3 h-3 bg-gray-200 rounded-full mt-1.5 -start-1.5 border border-white dark:border-gray-900 dark:bg-gray-700">
                        </div>
                        <time class="mb-1 text-sm font-normal leading-none text-gray-400 dark:text-gray-500">February
                            2022</time>
                        <h3 class="text-lg font-semibold text-gray-900 dark:text-white">{{ $post->title }}</h3>
                        <p class="mb-4 text-base font-normal text-gray-500 dark:text-gray-400">
                            {!! $post->content !!}
                        </p>

                        <a href="/posts/{{ $post->id }}"
                            class="inline-flex items-center mt-5 px-4 py-2 text-sm font-medium text-gray-900 bg-white border border-gray-200 rounded-lg hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:outline-none focus:ring-gray-100 focus:text-blue-700 dark:bg-gray-800 dark:text-gray-400 dark:border-gray-600 dark:hover:text-white dark:hover:bg-gray-700 dark:focus:ring-gray-700">
                            Edit

                            <svg class="w-3 h-3 ms-2 rtl:rotate-180" aria-hidden="true"
                                xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 10">
                                <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round"
                                    stroke-width="2" d="M1 5h12m0 0L9 1m4 4L9 9" />
                            </svg>
                        </a>
                    </li>
                @endforeach

            </div>
        </ol>

    </main>

</x-app-layout>
