<?php

include("ayar.php");
$uyeid = $_GET["kullanici_id"];


$sorgu = mysqli_query($baglan,"select kullaniciadi,kullanicisifre from kullanicilar where id='$uyeid'");

class Result
{
    public $kadi;
    public $sifre;
}
$result = new Result();

$deger=mysqli_fetch_assoc($sorgu);

$result->kadi = $deger["kullaniciadi"];
$result->sifre = $deger["kullanicisifre"];
echo(json_encode($result));



?>