<?php

include("ayar.php");
    $kadi = $_POST['kullaniciadi'];
    $sifre = $_POST['kullanicisifre'];
    $dogrulamakodu = rand(0,10000);
    $durum = 0;

class Result{
    public $result;
    public $truefalse;
    public $dogrulamaKodu;
}

$result = new Result();

$kontrol = mysqli_query($baglan,"select * from kullanicilar where kullaniciadi='$kadi'");

if(mysqli_num_rows($kontrol) < 1)
{
    $ekle = mysqli_query($baglan,"insert into kullanicilar(kullaniciadi,kullanicisifre,dogrulamakodu,durum) values ('$kadi','$sifre','$dogrulamakodu','$durum')");

    if($ekle)
    {
        $result->dogrulamaKodu = $dogrulamakodu;
        $result->truefalse = true;
        $result->result = "Kayit Basarili";
        echo(json_encode($result));
    }

}
else
{
    $result->result = "Boyle bir kayit vardir";
    $result->truefalse = false;
    echo(json_encode($result));
}

?> 





