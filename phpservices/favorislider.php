<?php


include("ayar.php");
$uyeid = $_GET["kullanici_id"];

$sorgu = mysqli_query($baglan,"select fu.*, ur.urbaslik
from favoriurunler as fu 
join urunresimler as ur on 
ur.id= (
	select ur2.id from urunresimler as ur2
    where fu.urun_id=ur2.urun_id
    limit 1
)
where fu.kullanici_id='$uyeid'");

class Slider
{
    public $truefalse;
    public $resimsayiadres;
    public $urunid;
} 
$slider = new Slider();

$say = mysqli_num_rows($sorgu);

if($say > 0)
{
    $sayac=0;
echo("[");
    while($deger=mysqli_fetch_assoc($sorgu))
    {
        $sayac = $sayac + 1;

        $slider->urunid = $deger["urun_id"];
        $slider->truefalse = true;
        $slider->resimsayiadres = $deger["urbaslik"];

        echo(json_encode($slider));
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
    
$slider->urunid = null;
$slider->truefalse = false;
$slider->resimsayiadres = "bos";
echo(json_encode($slider));

echo("]");
}


?>