<?php

require "./bootstrap.php";

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CRUD</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

<table border="10px">
    <thead>
        <tr>
            <td>NIS</td>
            <td>Name</td>
            <td>Class</td>
            <td><a href="form.php?action=add"><button>Add</button></a></td>
        </tr>
    </thead>
    <tbody>
        <?php foreach ([
            [
                'nis' => 14219,
                'name' => 'Muhammad Aspian',
                'class' => 'XII RPL 2',
            ]
        ] as $student): ?>
            <tr>
                <td><?= $student['nis'] ?></td>
                <td><?= $student['name'] ?></td>
                <td><?= $student['class'] ?></td>
                <td class="actions">
                    <a href="form.php?action=edit&nis=<?= $student['nis'] ?>">
                        <button>Edit</button>
                    </a>

                    <a href="?action=delete&nis=<?= $student['nis'] ?>">
                        <button>Delete</button>
                    </a>
                </td>
            </tr>
        <?php endforeach ?>
    </tbody>
</table>

</body>
</html>