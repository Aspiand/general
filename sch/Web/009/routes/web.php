<?php

use App\Http\Controllers\CartController;
use App\Http\Controllers\ProductController;
use App\Models\Product;
use Illuminate\Support\Facades\Route;

Route::get('/', function () {
    return view("index", [
        "products" => Product::all()
    ]);
})->name("index");

// Route::resource('cart', CartController::class);
Route::resource('product', ProductController::class);
