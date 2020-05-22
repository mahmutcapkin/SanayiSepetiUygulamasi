<?php

include("ayar.php");

$kullanici_id = $_GET["kullanici_id"];


$sorgu = mysqli_query($baglan," select u.*, ur.resimyolu, ur.urbaslik
from urunler as u 
join urunresimler as ur on
ur.id=(
	select ur2.id from urunresimler as ur2
    where u.id = ur2.urun_id
    limit 1
    ) where u.kullanici_id = '$kullanici_id' ");

class Result
{
    public $urunid;
    public $uyeid;
    public $fiyat;
    public $resimyolu;
    public $aciklama;
    public $baslik;
    public $truefalse;
    public $result;
    public $urunsayi;
    public $resimsayiadres;
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
        $result->uyeid = $kullanici_id;
        $result->fiyat =  $deger["fiyat"];
        $result->resimyolu =   $deger["resimyolu"];
        $result->aciklama = $deger["aciklama"];
        $result->baslik =  $deger["baslik"];
        $result->truefalse = true;
        $result->result = "Urun var";
        $result->urunsayi = $say;
        $result->resimsayiadres = $deger["urbaslik"];
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
    $result->result = "Urununuz yok";
    echo(json_encode($result));
echo("]");
}

?>