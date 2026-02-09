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
</head>

<body>

    <div class="form-container">
        <h1>
            <?= $scan['id'] ? 'Edit Scan' : 'Add New Scan' ?>
        </h1>
        <form action="" method="POST">
            <?php if ($scan['id']): ?>
                <input type="hidden" name="id" value="<?= htmlspecialchars($scan['id']) ?>">
            <?php endif; ?>

            <label for="url">URL</label>
            <input type="text" name="url" value="<?= htmlspecialchars($scan['url']) ?>" required>
            <br>

            <label for="trigger">Trigger</label>
            <select name="trigger">
                <option value="manual" <?= $scan['trigger'] == 'manual' ? 'selected' : '' ?>>Manual</option>
                <option value="schedule" <?= $scan['trigger'] == 'schedule' ? 'selected' : '' ?>>Schedule</option>
            </select>
            <br>

            <label for="pages_visited">Pages Visited</label>
            <input type="number" name="pages_visited" value="<?= htmlspecialchars($scan['pages_visited']) ?>">
            <br><br>

            <button type="submit">Save</button>
            <a href="index.php">Cancel</a>
        </form>
    </div>

</body>

</html>