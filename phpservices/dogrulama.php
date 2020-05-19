<?php

include("ayar.php");

 $kadi = $_POST['kullaniciadi'];   
 $dogrulamaKodu = $_POST['dogrulamakodu'];    

class Result{
    public $result;
    public $truefalse;
    public $id;
    public $kad;
}

$result= new Result();
$kontrol = mysqli_query($baglan,"select * from kullanicilar where kullaniciadi='$kadi' and dogrulamakodu='$dogrulamaKodu' and durum='0'");

if(mysqli_num_rows($kontrol) > 0)
{
    $guncelle = mysqli_query($baglan,"update kullanicilar set durum='1' where kullaniciadi='$kadi' and dogrulamakodu='$dogrulamaKodu'");
    $kontrol2 = mysqli_fetch_assoc($kontrol);
    if($guncelle)
    {
        
        $result->truefalse = true;
        $result->result = "Email adresiniz dogrulandi";
        $result->id = $kontrol2["id"];
        $result->kad = $kontrol2["kullaniciadi"];
        echo(json_encode($result));
    }
}
else
{
        $result->truefalse = false;
        $result->result = "Email adresiniz dogrulanamadi";
        echo(json_encode($result));
}

?> 