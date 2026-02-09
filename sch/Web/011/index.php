<?php

require __DIR__ . "/core/init.php";

if (isset($_GET['action']) && $_GET['action'] == 'delete' && isset($_GET['id'])) {
    $stmt = $db->prepare("DELETE FROM scans WHERE id = :id");
    $stmt->execute([
        'id' => $_GET['id']
    ]);

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
        * {
            box-sizing: border-box;
            font-family: Arial, Helvetica, sans-serif;
        }

        body {
            margin: 0;
            background: #f4f6f8;
        }

        .page-wrapper {
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: flex-start;
            padding-top: 40px;
        }

        .table-container {
            background: #ffffff;
            width: 100%;
            max-width: 900px;
            padding: 25px;
            border-radius: 8px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
        }

        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }

        .header h1 {
            margin: 0;
        }

        .btn-primary {
            padding: 10px 16px;
            background: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }

        .btn-primary:hover {
            background: #0056b3;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        thead {
            background: #f1f3f5;
        }

        th,
        td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        tbody tr:hover {
            background: #f9fafb;
        }

        td.url {
            max-width: 300px;
            word-break: break-all;
        }

        .actions a {
            margin-right: 10px;
            text-decoration: none;
            font-weight: 600;
        }

        .actions .edit {
            color: #007bff;
        }

        .actions .delete {
            color: #dc3545;
        }

        .actions a:hover {
            text-decoration: underline;
        }
    </style>
</head>

<body>

    <div class="page-wrapper">
        <div class="table-container">
            <div class="header">
                <h1>Scans</h1>
                <a href="form.php" class="btn-primary">Add New Scan</a>
            </div>

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
                            <td class="url"><?= htmlspecialchars($scan['url']) ?></td>
                            <td><?= htmlspecialchars($scan['trigger']) ?></td>
                            <td><?= htmlspecialchars($scan['pages_visited']) ?></td>
                            <td class="actions">
                                <a href="form.php?id=<?= $scan['id'] ?>" class="edit">Edit</a>
                                <a href="index.php?action=delete&id=<?= $scan['id'] ?>" class="delete"
                                    onclick="return confirm('Are you sure?')">
                                    Delete
                                </a>
                            </td>
                        </tr>
                    <?php endforeach; ?>
                </tbody>
            </table>
        </div>
    </div>

</body>

</html>