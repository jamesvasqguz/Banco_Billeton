<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/bootstrap.min.css" rel="stylesheet">
        <link href="../css/CSS.css" rel="stylesheet">
        <title>Rechazo de Login</title>
    </head>
    <body>
        <div class="container">
            <div class="row justify-content-center pt-5 mt-5 mr-1">
                <div class="col-md-4 formulario">
                    <div class="alert alert-danger" role="alert">
                        Usuario y/o Contraseña invalidas
                    </div>                  
                    <div class="dropdown-divider"></div>
                    <div class="form-group mx-sm-4 pb-2">
                        <a href="Login.jsp" class="btn btn-light btn-block"><img src="../Imagenes/arrow-back-outline.svg" width="25px">Regresar</a>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
