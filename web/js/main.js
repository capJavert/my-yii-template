$( 'select[name=ispostava]' ).change(function() {
    var url      = window.location.href;
    var res = url.split("&");
    console.log(res);
    var gor= res[0]+"&ispostaveID="+$(this).val();
    window.location.href = gor;
});
$( 'select[name=tim]' ).change(function() {
    var url      = window.location.href;
    var res = url.split("&");
    var gor= res[0]+"&timID="+$(this).val();
    window.location.href = gor;
});
$( 'select[name=smjena]' ).change(function() {
    var url      = window.location.href;
    var res = url.split("&");
    var gor= res[0]+"&smjenaID="+$(this).val();
    window.location.href = gor;
});