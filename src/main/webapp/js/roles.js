
$("a.editRole").click(function(){

    idRol = $(this).attr('id');
    rol_name = $(`#cl-name-${idRol}`).text();
    rol_desc = $(`#cl-desc-${idRol}`).text();
    $("#idRol").val(`${idRol}`);
    $("#opc").val("2");
    $("#rolName").val(rol_name);
    $("#rolDesc").val(rol_desc);
    $("#opcIndicador").text("Operaci\u00F3n actual: actualizar");
    $("#roleForm").attr("action", "../actualizarRol");
});

$("a.deleteRole").click(function(){
    
    idRol = $(this).attr("id");
    confirm(function()
    { 	
        window.location.replace(`../deleteRol?id=${idRol}`);
    }, 
    function(e,btn)
    {
    
    });
});

$("#cancelRoleBTN").click(function(){
	$("#roleForm").attr("action", "../nuevoRol");
	$("#opcIndicador").text("Operaci\u00F3n actual: guardar");
});

