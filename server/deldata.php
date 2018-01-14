<?php
require('dbcon.php');

$id = $_POST['ID'];
$ten = $_POST['TEN'];
$anh1 = $_POST['ANH1'];

echo "$anh1";

$s1 = strstr($anh1, 'uploads');

echo "$s1";

$s2 = str_replace( '.1.png', '', $s1 );

echo $s2;

$qr ="DELETE FROM tools WHERE id = $id";

foreach (glob("$s2.*.*") as $filename) {
    unlink($filename);
}

mysqli_query($conn,$qr);

?>