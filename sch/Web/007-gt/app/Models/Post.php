<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Post extends Model
{
    protected $fillable = [
        "title",
        "content",
        "is_published",
        "user_id",
        "slug"
    ];

    public function user()
    {
        return $this->belongsTo(User::class);
    }
}
