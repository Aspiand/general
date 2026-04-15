<?php
$arr1 = [
    "Tomat",
    "Wortel",
    "Kacang Panjang",
    "Kentang",
    "Selada",
];

$arr2 = [
    5000, 1000, 2000, 3500, 7000
];

$arr = [
    ["Tomat", 5000]
];

for ($i = 0; $i < count($arr1); $i++) {
    echo $arr1[$i] . ": " . $arr2[$i] . "\n";
}