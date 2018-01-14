<?php
require('dbcon.php');

/**
* 
*/
class Tools 
{
	
	function Tools($id,$ten,$pn,$sn,$anh1,$anh2,$anh3)
	{
		$this->ID = $id;
		$this->TEN = $ten;
		$this->PN = $pn;
		$this->SN = $sn;
		$this->ANH1 = $anh1;
		$this->ANH2 = $anh2;
		$this->ANH3 = $anh3;
	}
}

$query = "SELECT * FROM tools";
$data = mysqli_query($conn,$query);

$mangtools = array();

while ($row = mysqli_fetch_assoc($data)) {
	array_push($mangtools, new Tools(
		$row['id'],
		$row['ten'],
		$row['pn'],
		$row['sn'],
		$row['anh1'],
		$row['anh2'],
		$row['anh3']
	));
}

echo json_encode($mangtools);

?>