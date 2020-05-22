<?php

include("ayar.php");
$urun_id = $_GET["urun_id"];

$sil = mysqli_query($baglan,"delete from urunler where id= '$urun_id'");
$sil2 = mysqli_query($baglan,"delete from urunresimler where urun_id= '$urun_id'");

class Result
{
    public $result;
    public $truefalse;
}

$result = new Result();

if($sil && $sil2)
{
    
    $result->result = "Urun silinmistir";
    $result->truefalse= true;
    echo(json_encode($result));
}
else
{
    $result->result = "Urun silinemedi";
    $result->truefalse= false;
    echo(json_encode($result));
}



?>