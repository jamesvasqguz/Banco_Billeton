<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/bootstrap.min.css" rel="stylesheet">
        <link href="../css/CSS.css" rel="stylesheet"> 
        <title>Verificar PDF</title>
    </head>
    <body>
        <div class="container">
            <div class="row justify-content-center pt-5 mt-5 mr-1">
                <div class="col-md-4 registros">
                    <form method="POST" action="/Project_Banco/ControladorVerificar">
                        <div class="form-group text-center">
                            <h1 class="text-light">DPI del Cliente</h1>
                            <img src="../Imagenes/periodico.svg" width="100px"/>
                        </div>
                        <div class="form-group mx-sm-4 pt-3">
                            <label>Ingrese la cuenta del Cliente</label>
                            <input type="text" name="cuenta" class="form-control" placeholder="Cuenta a verificar" required="Campo Obligatorio"/>
                        </div>
                        <div class="dropdown-divider"></div>
                        <div class="form-group mx-sm-4 pt-2">
                            <input type="submit" class="btn btn-block btn-light " value="Verificar DPI"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
