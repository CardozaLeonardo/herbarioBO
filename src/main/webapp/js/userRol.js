
$("#removeRoleBTN").click(function(){
    
    if($("#currentRoles").val())
    {
        
        confirm(function(){
            idUser = $("#idUser").val();
            rolUser = $("#currentRoles").val();
            window.location.replace(`../removerRol?delete=${rolUser}&idUser=${idUser}`);

        },function(){

        });

    }else{
        //textMSG = utf8_encode('Selección vacia')
        
        warningAlert("Selecci\u00F3n vacia", "Seleccione un rol a eliminar");
    }
});


$("#removeOpcBTN").click(function(){
    
    if($("#currentOpc").val())
    {
        
        confirm(function()
        { 	
            idRol = $("#idRol").val();
            opcion = $("#currentOpc").val();
            window.location.replace(`../removerOpcion?delete=${opcion}&idRol=${idRol}`);
        }, 
        function(e,btn)
        {
        
        });

    }else{
        //textMSG = utf8_encode('Selección vacia')
        
        warningAlert("Selecci\u00F3n vacia", "Seleccione una opci\u00F3n a eliminar");
    }
});

$("#listaRoles").change(()=>{
    if($("#listaRoles").val())
    {
        if($(`.cr-${$("#listaRoles").val()}`).length){
            $("#submitRole").prop('disabled', true);
        }else{
            $("#submitRole").prop('disabled', false);
        }
    }
})



$("#listaOpciones").change(()=>{
    if($("#listaOpciones").val())
    {
        if($(`.cr-${$("#listaOpciones").val()}`).length){
            $("#submitOPC").prop('disabled', true);
        }else{
            $("#submitOPC").prop('disabled', false);
        }
    }
})

