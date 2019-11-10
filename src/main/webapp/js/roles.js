
$("a.editRole").click(function(){

    idRol = $(this).attr('id');
    rol_name = $(`#cl-name-${idRol}`).text();
    rol_desc = $(`#cl-desc-${idRol}`).text();
    $("#idRol").val(`${idRol}`);
    $("#opc").val("2");
    $("#rolName").val(rol_name);
    $("#rolDesc").val(rol_desc);
});

$("a.deleteRole").click(function(){
    
    idRol = $(this).attr("id");
    confirm(function()
    { 	
        window.location.replace(`../SL_roles?delete=${idRol}`);
    }, 
    function(e,btn)
    {
    
    });
});

