<?php

include("ayar.php");

$sorgu = mysqli_query($baglan," select u.*, ur.resimyolu, ur.urbaslik
from urunler as u 
join urunresimler as ur on
ur.id=(
	select ur2.id from urunresimler as ur2
    where u.id = ur2.urun_id
    limit 1
    )");

class Result
{
    public $urunid;
    public $uyeid;
    public $fiyat;
    public $resimyolu;
    public $aciklama;
    public $baslik;
    public $truefalse;
    public $urunsayi;
    public $resimsayiadres;
    public $ilce;
    public $mahalle;
    public $sokak;
    public $binano;
}
$result = new Result();

$say = mysqli_num_rows($sorgu);

if($say > 0)
{
    $sayac = 0;

echo("[");
    while($deger=mysqli_fetch_assoc($sorgu))
    {
        $sayac = $sayac + 1;
        $result->urunid =  $deger["id"];
        $result->uyeid = $deger["kullanici_id"];
        $result->fiyat =  $deger["fiyat"];
        $result->resimyolu =   $deger["resimyolu"];
        $result->aciklama = $deger["aciklama"];
        $result->baslik =  $deger["baslik"];
        $result->truefalse = true;
        $result->urunsayi = $say;
        $result->resimsayiadres = $deger["urbaslik"];
        $result->ilce = $deger["ilce"];
        $result->mahalle =  $deger["mahalle"];
        $result->sokak =  $deger["sokak"];
        $result->binano =  $deger["binano"];
        echo(json_encode($result));
        if($say > $sayac)
        {
            echo(",");
        }
    }
echo("]");
   
}
else
{
echo("[");
    $result->truefalse = false;
    echo(json_encode($result));
echo("]");
}

?>