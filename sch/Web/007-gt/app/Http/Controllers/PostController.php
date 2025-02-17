<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Str;
use Illuminate\Support\Facades\Auth;

use App\Models\Post;
use App\Models\User;

class PostController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        return view('posts.index', [
            'posts' => Post::all()
        ]);
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create()
    {
        return view('posts.article');
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        $validated = $request->validate([
            'title' => 'required|string',
            'content' => 'required|string'
        ]);

        // add slug
        $validated['slug'] = Str::slug($validated['title'] . time(), '_');

        // get creator
        $creator = User::find(Auth::user()->id);

        $post = new Post($validated);

        $creator->posts()->save($post);

        return redirect()->route('posts.index')->with('success', 'Artikel berhasil ditambahkan');
    }

    /**
     * Display the specified resource.
     */
    public function show(string $id)
    {
        return view("posts.show", [
            "post" => Post::find($id)
        ]);
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(string $id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, string $id)
    {
        $post = Post::findOrFail($id);

        $validated = $request->validate([
            "title" => "required|string",
            "content" => "required|string"
        ]);

        $validated["slug"] = Str::slug($validated["title"] . "_" . time(), "_");

        $post->update($validated);

        return redirect()->route("posts.index")->with("success", "Data berhasil ditambahkan");
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(string $id)
    {
        $post = Post::findOrFail($id);
        $post->delete();

        return redirect()->route('posts.index')->with('success', 'Data berhasil dihapus');
    }
}
