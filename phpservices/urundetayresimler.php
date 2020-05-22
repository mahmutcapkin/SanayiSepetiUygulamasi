<?php

include("ayar.php");
$urunid = $_GET["urun_id"];

$sorgu = mysqli_query($baglan," select * from urunresimler where urun_id = '$urunid'");

class Result
{   
    public $resimyolu;
    public $resimsayiadres;
}

$result = new Result();
$say = mysqli_num_rows($sorgu);
$sayac = 0;
echo("[");
    while($deger=mysqli_fetch_assoc($sorgu))
    {
        $sayac = $sayac + 1;
        $result->resimyolu =  $deger["resimyolu"];
        $result->resimsayiadres =  $deger["urbaslik"];

    
        echo(json_encode($result));
        if($say > $sayac)
        {
            echo(",");
        }
    }
echo("]");
   















?>