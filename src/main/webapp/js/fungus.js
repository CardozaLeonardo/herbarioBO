$("a.deleteFungus").click(function(){
    
    idFun = $(this).attr("id");
    confirm(function()
    { 	
        window.location.replace(`../deleteFungus?id=${idFun}`);
    }, 
    function(e,btn)
    {
    
    });
});

$("a.deleteSpecie").click(function(){
    
    idFun = $(this).attr("id");
    confirm(function()
    { 	
        window.location.replace(`../deleteSpecie?id=${idFun}`);
    }, 
    function(e,btn)
    {
    
    });
});