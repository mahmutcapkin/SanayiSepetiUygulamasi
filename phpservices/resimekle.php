<?php

include("ayar.php");

class Result
{
    public $sonuc;
    public $truefalse;
}

$result = new Result();

$uye_id = $_POST["Kullanici_id"];
$urun_id = $_POST["urun_id"];
$resim = $_POST["resim"];

$baslik = rand(0,100000).rand(0,100000).rand(0,100000).rand(0,100000);
$yol = "C:/xampp/htdocs/$baslik.jpg";

$ekle= mysqli_query($baglan,"insert into urunresimler(kullanici_id,urun_id,urbaslik,resimyolu) values ('$uye_id','$urun_id','$baslik','$yol')");

if($ekle)
{
    file_put_contents($yol,base64_decode($resim));
    $result->sonuc = "Resim Yuklendi";
    $result->truefalse = true;
    echo(json_encode($result));
}
else
{
    $result->sonuc = "Resim Yuklenemedi";
    $result->truefalse = false;
    echo(json_encode($result));
}

?>