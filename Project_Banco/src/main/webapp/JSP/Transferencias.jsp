<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/bootstrap.min.css" rel="stylesheet">
        <link href="../css/CSS.css" rel="stylesheet">
        <title>Transferencias</title>
    </head>
    <body>
        <%
            HttpSession sesion = request.getSession();
            if (sesion.getAttribute("Loggeado") == null || sesion.getAttribute("Loggeado").equals("0")) {
                response.sendRedirect("Login.jsp");
            }
        %>
        <nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="Cliente.jsp"><%=sesion.getAttribute("username")%></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
                <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" href="InformacionCliente.jsp">Mis Datos</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Mis Cuentas
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="EstadoCuenta.jsp">Estado de Cuenta</a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Asociaciones
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="SolicitarAsociacion.jsp">Solicitar Asociacion</a>
                            <a class="dropdown-item" href="Solicitudes.jsp">Aceptar Asociacion</a>
                            <div class="dropdown-divider"></div>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Transferencias.jsp">Transferencias</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="ReportesCliente.jsp">Reportes</a>
                    </li>
                </ul>
                <form class="form-inline my-2 my-lg-0" action="CerrarSesion.jsp">
                    <button class="btn btn-outline-light my-2 my-sm-0" type="submit">Salir<img src="../Imagenes/out.svg" width="30px"></button>
                </form>
            </div>
        </nav>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="../js/bootstrap.min.js" ></script>
        <div class="container">
            <div class="row justify-content-center pt-5 mt-5 mr-1">
                <div class="col-md-4 registros">
                    <form method="POST" action="/Project_Banco/ControladorTransferencia">
                        <div class="form-group text-center">
                            <h1 class="text-light">Realizar Transferencia</h1>
                            <img src="../Imagenes/enviar.svg" width="100px"/>
                        </div>
                        <div class="form-group mx-sm-4 pt-3">
                            <label>Cuenta que recibe el dinero.</label>
                            <input type="text" name="cuentaD" class="form-control" placeholder="Cuenta destino" required="Campo Obligatorio"/>
                        </div>
                        <div class="form-group mx-sm-4 pt-3">
                            <label>Cuenta que envia el dinero.</label>
                            <input type="text" name="cuentaO" class="form-control" placeholder="Cuenta origen" required="Campo Obligatorio"/>
                        </div>
                        <div class="form-group mx-sm-4 pt-3">
                            <label>Monto</label>
                            <input type="text" name="dinero" class="form-control" placeholder="Monto inicial" required="Campo Obligatorio"/>
                        </div>
                        <div class="form-group mx-sm-4 pt-3">
                            <label>Cajero</label>
                            <input type="text" name="cajero" class="form-control" value="101" readonly>
                        </div>
                        <div class="dropdown-divider"></div>
                        <div class="form-group mx-sm-4 pt-2">
                            <input type="submit" class="btn btn-block btn-light " value="Enviar Dinero"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
