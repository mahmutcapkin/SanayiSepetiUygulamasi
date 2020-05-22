<?php

include("ayar.php");
$urunid = $_GET["urun_id"];

$sorgu = mysqli_query($baglan," select * from urunler where id = '$urunid'");

class Result
{   
    public $uyeid;
    public $ilce;
    public $mahalle;
    public $sokak;
    public $binano;
    public $baslik;
    public $aciklama;
    public $marka;
    public $fiyat;
    public $uretimyeri;  
}
$result = new Result();

$deger=mysqli_fetch_assoc($sorgu);

    $result->uyeid =  $deger["kullanici_id"];
    $result->ilce = $deger["ilce"];
    $result->mahalle =  $deger["mahalle"];
    $result->sokak =   $deger["sokak"];
    $result->binano = $deger["binano"];
    $result->baslik =  $deger["baslik"];
    $result->aciklama = $deger["aciklama"];
    $result->marka = $deger["marka"];
    $result->fiyat = $deger["fiyat"];
    $result->uretimyeri = $deger["uretimyeri"];
    echo(json_encode($result));

?>