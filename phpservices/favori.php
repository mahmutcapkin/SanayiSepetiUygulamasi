<?php

include("ayar.php");
$uyeid = $_GET["kullanici_id"];
$urunid = $_GET["urun_id"];

$sorgu = mysqli_query($baglan," select * from favoriurunler where kullanici_id = '$uyeid' and urun_id = '$urunid'");
$deger = mysqli_num_rows($sorgu);

class Result
{
    public $truefalse;
    public $text;
}
$result = new Result();
if($deger > 0)
{
    $result->truefalse =true;
    $result->text = "Favoriden Cikar";
    echo(json_encode($result));
}
else
{
    $result->truefalse =false;
    $result->text = "Favoriye Al";
    echo(json_encode($result));
}




?>