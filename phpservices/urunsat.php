<?php

include("ayar.php");

$kullanici_id = $_POST["kullanici_id"];
$ilce = $_POST["ilce"];
$mahalle = $_POST["mahalle"];
$sokak = $_POST["sokak"];
$binano = $_POST["binano"];

$baslik = $_POST["baslik"];
$aciklama = $_POST["aciklama"];
$marka = $_POST["marka"];
$fiyat = $_POST["fiyat"];
$uretimyeri = $_POST["uretimyeri"];


class Result
{
    public $uye_id;
    public $urun_id;
    public $truefalse;
}

$result = new Result();

$ekle= mysqli_query($baglan,"insert into urunler(kullanici_id,ilce,mahalle,sokak,binano,baslik,aciklama,marka,fiyat,uretimyeri) values('$kullanici_id','$ilce','$mahalle','$sokak','$binano','$baslik','$aciklama','$marka','$fiyat','$uretimyeri')");

if($ekle)
{
    $sor = mysqli_query($baglan,"select * from urunler where kullanici_id = '$kullanici_id' order by id desc limit 1");
    $sor2 = mysqli_fetch_assoc($sor);
    
    $result->uye_id = $kullanici_id;
    $result->truefalse= true;
    $result->urun_id = $sor2["id"];
    echo(json_encode($result));
}
else
{
    $result->truefalse = false;
    echo(json_encode($result));
}

?>