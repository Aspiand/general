<?php

namespace App\Http\Controllers;

use App\Models\Product;
use Illuminate\Http\Request;

class ProductController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        //
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        $validated = $request->validate([
            "name" => "required|string|max:255",
            "price" => "required|integer",
            "stock" => "required|integer",
            "description" => "required|string",
        ]);

        Product::create($validated);
        return redirect()->route("index", ["products" => Product::all()]);
    }

    /**
     * Display the specified resource.
     */
    public function show(string $id)
    {
        //
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
        Product::all()[$id]->update($request->validate([
            "name" => "required|string|max:255",
            "price" => "required|integer",
            "stock" => "required|integer",
            "description" => "required|string",
        ]));

        return redirect()->route("index");
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(string $id)
    {
        Product::all()[$id]->delete();
        return redirect()->route("index");
    }
}
