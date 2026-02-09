<?php

require __DIR__ . "/core/init.php";

$scan = [
    'id' => '',
    'url' => '',
    'trigger' => 'manual',
    'pages_visited' => ''
];

if (isset($_GET['id'])) {
    $stmt = $db->prepare("SELECT * FROM scans WHERE id = :id");
    $stmt->execute([
        'id' => $_GET['id'],
    ]);

    $fetch = $stmt->fetch(PDO::FETCH_ASSOC);
    if ($fetch) {
        $scan = $fetch;
    }
}

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $url = $_POST['url'];
    $trigger = $_POST['trigger'];
    $pages_visited = $_POST['pages_visited'];
    $id = $_POST['id'] ?? '';

    if ($id) {
        $stmt = $db->prepare("UPDATE scans SET url = :url, trigger = :trigger, pages_visited = :pages_visited WHERE id = :id");
        $stmt->execute([
            'url' => $url,
            'trigger' => $trigger,
            'pages_visited' => $pages_visited,
            'id' => $id
        ]);
    } else {
        $stmt = $db->prepare("INSERT INTO scans (url, trigger, pages_visited) VALUES (:url, :trigger, :pages_visited)");
        $stmt->execute([
            'url' => $url,
            'trigger' => $trigger,
            'pages_visited' => $pages_visited
        ]);
    }

    header("Location: index.php");
    exit;
}

?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Scan Form</title>

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
            align-items: center;
        }

        .form-container {
            background: #ffffff;
            padding: 30px;
            width: 100%;
            max-width: 420px;
            border-radius: 8px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
        }

        .form-container h1 {
            margin-bottom: 25px;
            text-align: center;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 6px;
            font-weight: 600;
        }

        input,
        select {
            width: 100%;
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
            font-size: 14px;
        }

        input:focus,
        select:focus {
            outline: none;
            border-color: #007bff;
        }

        .form-actions {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-top: 20px;
        }

        button {
            padding: 10px 18px;
            background: #007bff;
            border: none;
            color: white;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background: #0056b3;
        }

        .cancel {
            color: #666;
            text-decoration: none;
        }

        .cancel:hover {
            text-decoration: underline;
        }
    </style>
</head>

<body>

    <div class="page-wrapper">
        <div class="form-container">
            <h1><?= $scan['id'] ? 'Edit Scan' : 'Add New Scan' ?></h1>

            <form action="" method="POST">
                <?php if ($scan['id']): ?>
                    <input type="hidden" name="id" value="<?= htmlspecialchars($scan['id']) ?>">
                <?php endif; ?>

                <div class="form-group">
                    <label>URL</label>
                    <input type="text" name="url" value="<?= htmlspecialchars($scan['url']) ?>" required>
                </div>

                <div class="form-group">
                    <label>Trigger</label>
                    <select name="trigger">
                        <option value="manual" <?= $scan['trigger'] == 'manual' ? 'selected' : '' ?>>Manual</option>
                        <option value="schedule" <?= $scan['trigger'] == 'schedule' ? 'selected' : '' ?>>Schedule</option>
                    </select>
                </div>

                <div class="form-group">
                    <label>Pages Visited</label>
                    <input type="number" name="pages_visited" value="<?= htmlspecialchars($scan['pages_visited']) ?>">
                </div>

                <div class="form-actions">
                    <button type="submit">Save</button>
                    <a href="index.php" class="cancel">Cancel</a>
                </div>
            </form>
        </div>
    </div>

</body>

</html>