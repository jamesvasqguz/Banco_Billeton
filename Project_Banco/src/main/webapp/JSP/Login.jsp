<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/bootstrap.min.css" rel="stylesheet">
        <link href="../css/CSS.css" rel="stylesheet">
        <title>Inicio de Sesión</title>
    </head>
    <body>
        <div class="container">
            <div class="row justify-content-center pt-5 mt-5 mr-1">
                <div class="col-md-4 formulario">
                    <form method="POST" action="/Project_Banco/ControladorLogin">
                        <div class="form-group text-center">
                            <h1 class="text-light">INICIAR SESIÓN</h1>
                        </div>
                        <div class="form-group mx-sm-4 pt-3">
                            <input type="text" name="user" class="form-control" placeholder="Ingrese su Usuario"/>
                        </div>
                        <div class="form-group mx-sm-4 pb-3">
                            <input type="password" name="pass" id="pass1" class="form-control" placeholder="Ingrese su Contraseña"/>
                        </div>
                        <div class="form-group mx-sm-4 pb-3">
                            <img src="../Imagenes/eye.svg" id="boton1" class="boton"><a>Mostrar Contraseña</a>
                        </div>
                        <div class="dropdown-divider"></div>
                        <div class="form-group mx-sm-4 pb-2">
                            <input type="submit" class="btn btn-block ingresar" value="INGRESAR"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script>
            var btn = document.getElementById('boton1');
            var inpt = document.getElementById('pass1');
            btn.addEventListener('click', mostrarContraseña);
            function mostrarContraseña() {
                if (inpt.type == "password") {
                    inpt.type = "text";
                    btn.src = "../Imagenes/eye-off.svg";
                } else {
                    inpt.type = "password";
                    btn.src = "../Imagenes/eye.svg";
                }
            }
        </script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="../js/bootstrap.min.js" ></script>
    </body>
</html>
