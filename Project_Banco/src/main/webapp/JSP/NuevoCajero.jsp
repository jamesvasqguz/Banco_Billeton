<%@page import="DataBase.DBRestrincciones"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/bootstrap.min.css" rel="stylesheet">
        <link href="../css/CSS.css" rel="stylesheet">  
        <title>Nuevo Cajero</title>
    </head>
    <body>
        <%
            HttpSession sesion = request.getSession();
            if (sesion.getAttribute("Loggeado") == null || sesion.getAttribute("Loggeado").equals("0")) {
                response.sendRedirect("Login.jsp");
            }
        %>
        <nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="Gerente.jsp"><%=sesion.getAttribute("username")%></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
                <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                    <%DBRestrincciones cons = new DBRestrincciones();
                        String cd = String.valueOf(sesion.getAttribute("id"));
                        String rol = String.valueOf(sesion.getAttribute("rol"));
                        int codigo = Integer.parseInt(cd);
                        if ((cons.enHorario(codigo, rol))) {
                    %>
                    <li class="nav-item">
                        <a class="nav-link" href="NuevaCuenta.jsp">Crear Cuenta Bancaria</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Registrar
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="NuevoGerente.jsp">Gerentes</a>
                            <a class="dropdown-item" href="NuevoCajero.jsp">Cajeros</a>
                            <a class="dropdown-item" href="NuevoCliente.jsp">Clientes</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="NuevoUsuario.jsp">Usuarios</a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Actualizar Datos
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="InformacionGerente.jsp">Mis Datos</a>
                            <a class="dropdown-item" href="ActualizarDatos.jsp">Datos de Usuarios</a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Limites
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <%if ((cons.existenLimites(codigo))) {
                            %>
                            <a class="dropdown-item" href="FijarLimites.jsp">Modificar Limites</a>
                            <%} else {%>
                            <a class="dropdown-item" href="FijarLimites.jsp">Fijar Limites</a>
                            <a class="dropdown-item" href="ModificarLimites.jsp">Modificar Limites</a>
                            <%}%>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Reportes
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="GReport1.jsp">Reporte 1</a>
                            <a class="dropdown-item" href="GReport2.jsp">Reporte 2</a>
                            <a class="dropdown-item" href="GReport3.jsp">Reporte 3</a>
                            <a class="dropdown-item" href="GReport4.jsp">Reporte 4</a>
                            <a class="dropdown-item" href="GReport5.jsp">Reporte 5</a>
                            <a class="dropdown-item" href="GReport6.jsp">Reporte 6</a>
                            <a class="dropdown-item" href="GReport7.jsp">Reporte 7</a>  
                        </div>
                    </li>
                    <%                        } else {
                    %>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Reportes
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="GReport1.jsp">Reporte 1</a>
                            <a class="dropdown-item" href="GReport2.jsp">Reporte 2</a>
                            <a class="dropdown-item" href="GReport3.jsp">Reporte 3</a>
                            <a class="dropdown-item" href="GReport4.jsp">Reporte 4</a>
                            <a class="dropdown-item" href="GReport5.jsp">Reporte 5</a>
                            <a class="dropdown-item" href="GReport6.jsp">Reporte 6</a>
                            <a class="dropdown-item" href="GReport7.jsp">Reporte 7</a>  
                        </div>
                    </li>
                    <%                        }%>
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
                    <form method="POST" action="/Project_Banco/ControladorNCAJERO">
                        <div class="form-group text-center">
                            <h1 class="text-light">Nuevo Cajero</h1>
                            <img src="../Imagenes/edit-user.png" width="100px"/>
                        </div>
                        <div class="form-group mx-sm-4 pt-3">
                            <label>Nombre</label>
                            <input type="text" name="nombre" class="form-control" placeholder="Nombre Completo" required="Campo Obligatorio"/>
                        </div>
                        <div class="form-group mx-sm-4 pt-3">
                            <label>Turno del Cajero</label>
                            <br>
                            <input type="text" name="turno" placeholder="Turno" list="Turnos" required="Campo Obligatorio">
                            <datalist id="Turnos">
                                <option value="MATUTINO">
                                <option value="VESPERTINO">
                            </datalist>
                        </div>
                        <div class="form-group mx-sm-4 pt-3">
                            <label>DPI</label>
                            <input type="text" name="dpi" class="form-control" placeholder="DPI del Cajero" required="Campo Obligatorio"/>
                        </div>
                        <div class="form-group mx-sm-4 pt-3">
                            <label>Direccion</label>
                            <input type="text" name="direccion" class="form-control" placeholder="Direccion del Cajero" required="Campo Obligatorio"/>
                        </div>
                        <div class="form-group mx-sm-4 pt-3">
                            <label>Sexo</label>
                            <br>
                            <input type="text" name="sexo" placeholder="Sexo" list="Sexo" required="Campo Obligatorio">
                            <datalist id="Sexo">
                                <option value="M">
                                <option value="F">
                            </datalist>
                        </div>
                        <div class="dropdown-divider"></div>
                        <div class="form-group mx-sm-4 pt-3">
                            <label>Codigo Unico se le brindara a continuacion.</label>
                        </div>
                        <div class="form-group mx-sm-4 pt-2">
                            <input type="submit" class="btn btn-block btn-light " value="Crear"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
