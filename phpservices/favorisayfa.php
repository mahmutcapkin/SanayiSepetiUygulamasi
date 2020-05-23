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
    $sil = mysqli_query($baglan,"delete from favoriurunler where kullanici_id = '$uyeid' and urun_id = '$urunid'");
    $result->truefalse =false;
    $result->text = "Favoriden Cikarildi";
    echo(json_encode($result));
}
else
{
  $ekle = mysqli_query($baglan,"insert into favoriurunler(kullanici_id,urun_id) values('$uyeid','$urunid')");
  $result->truefalse =true;
  $result->text = "Favoriye Alindi";
  echo(json_encode($result));
}

 


?>