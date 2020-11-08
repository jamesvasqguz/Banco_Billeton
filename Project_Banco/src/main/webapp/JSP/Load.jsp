<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/bootstrap.min.css" rel="stylesheet">
        <link href="../css/CSS.css" rel="stylesheet">
        <title>Carga de Archivos</title>
    </head>
    <body>
        <div class="container">
            <div class="row justify-content-center pt-5 mt-5 mr-1">
                <div class="col-md-4 formulario">
                    <div class="form-group text-center">
                        <h1 class="text-light">CARGA DE ARCHIVOS</h1>
                        <form method="POST" enctype="multipart/form-data" action="/Project_Banco/ControladorCarga">
                            <label class="text-light" for="dataFile">Seleccione archivo que contiene los registros del Sistema:</label>
                            <br>
                            <input type="file" name="dataFile" class="btn btn-info"/>
                            <br>
                            <br>
                            <br>
                            <div class="dropdown-divider"></div>
                            <button type="submit" class="btn btn-success">CARGAR ARCHIVO</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="../js/bootstrap.min.js" ></script>
    </body>
</html>
