<?php

include("ayar.php");



if(!empty($_POST))
{
        $ad =   $_POST['kullaniciadi'];   //"ahmet@gmail.com";
        $sifre =   $_POST['kullanicisifre'];    //  "12345";
}
Class Uye
{
        public $id;
        public $kadi;
}


$kontrol = mysqli_fetch_assoc(mysqli_query($baglan," select * from kullanicilar where kullaniciadi='".$ad."' and kullanicisifre='".$sifre."'"));
$uye = new Uye();
$uye->id = $kontrol["id"];
$uye->kadi = $kontrol["kullaniciadi"];

echo(json_encode($uye));


?> 