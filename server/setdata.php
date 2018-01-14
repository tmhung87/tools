<?php

require('dbcon.php');

$ten = $_POST['TEN'];
$prn = $_POST['PRN'];
$sn = $_POST['SN'];
$anh1 = $_POST['ANH1'];
$anh2 = $_POST['ANH2'];
$anh3 = $_POST['ANH3'];
$t = time();

$path1 = "uploads/$ten.$t.1.png";
$path2 = "uploads/$ten.$t.2.png";
$path3 = "uploads/$ten.$t.3.png";

$url1 = "https://1luutru.000webhostapp.com/$path1";
$url2 = "https://1luutru.000webhostapp.com/$path2";
$url3 = "https://1luutru.000webhostapp.com/$path3";

$qr = "INSERT INTO tools VALUES(null,'$ten','$prn','$sn','$url1','$url2','$url3')";

if (mysqli_query($conn,$qr)){
	file_put_contents($path1, base64_decode($anh1));
	file_put_contents($path2, base64_decode($anh2));
	file_put_contents($path3, base64_decode($anh3));
	echo "thanh cong";
} else {
	echo "loi";
}
?>