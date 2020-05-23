<?php

include("ayar.php");
class Result
{
    public $tf;

}
$result = new Result();

$uyeid = $_GET["kullanici_id"];
$user = $_GET["user"];
$sifre = $_GET["sifre"];

$sorgu = mysqli_query($baglan,"update kullanicilar set kullaniciadi = '$user' , kullanicisifre = '$sifre' where id = '$uyeid'");

$result->tf=true;

echo(json_encode($result));

?>