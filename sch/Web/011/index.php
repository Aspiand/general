<?php

require __DIR__ . "/core/init.php";

if (isset($_GET['action']) && $_GET['action'] == 'delete' && isset($_GET['id'])) {
    $stmt = $db->prepare("DELETE FROM scans WHERE id = :id");
    $stmt->execute(['id' => $_GET['id']]);
    header("Location: index.php");
    exit;
}

$stmt = $db->query("SELECT * FROM scans");
$scans = $stmt->fetchAll(PDO::FETCH_ASSOC);

?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Keyword Scanner</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }

        th,
        td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }
    </style>
</head>

<body>

    <h1>Scans</h1>
    <a href="form.php">Add New Scan</a>
    <br><br>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>URL</th>
                <th>Trigger</th>
                <th>Pages Visited</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <?php foreach ($scans as $scan): ?>
                <tr>
                    <td><?= htmlspecialchars($scan['id']) ?></td>
                    <td><?= htmlspecialchars($scan['url']) ?></td>
                    <td><?= htmlspecialchars($scan['trigger']) ?></td>
                    <td><?= htmlspecialchars($scan['pages_visited']) ?></td>
                    <td>
                        <a href="form.php?id=<?= $scan['id'] ?>">Edit</a>
                        <a href="index.php?action=delete&id=<?= $scan['id'] ?>"
                            onclick="return confirm('Are you sure?')">Delete</a>
                    </td>
                </tr>
            <?php endforeach; ?>
        </tbody>
    </table>

</body>

</html>