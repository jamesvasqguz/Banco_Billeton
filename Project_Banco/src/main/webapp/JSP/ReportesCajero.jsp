<%@page import="DataBase.DBRestrincciones"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/bootstrap.min.css" rel="stylesheet">
        <link href="../css/CSS.css" rel="stylesheet">  
        <title>Reportes Cajero</title>
    </head>
    <body>
        <%
            HttpSession sesion = request.getSession();
            if (sesion.getAttribute("Loggeado") == null || sesion.getAttribute("Loggeado").equals("0")) {
                response.sendRedirect("Login.jsp");
            }
            DBRestrincciones cons = new DBRestrincciones();
            String cd = String.valueOf(sesion.getAttribute("id"));
            String rol = String.valueOf(sesion.getAttribute("rol"));
            int codigo = Integer.parseInt(cd);
        %>
        <nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="Cajero.jsp"><%=sesion.getAttribute("username")%></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
                <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                    <%
                        if ((cons.enHorario(codigo, rol))) {
                    %>
                    <li class="nav-item">
                        <a class="nav-link" href="InformacionCajero.jsp">Mis Datos</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Transacciones
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="Deposito.jsp">Deposito</a>
                            <a class="dropdown-item" href="Retiro.jsp">Retiro</a>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="ReportesCajero.jsp">Reportes</a>
                    </li>
                    <%                        } else {
                    %>
                    <li class="nav-item">
                        <a class="nav-link" href="ReportesCajero.jsp">Reportes</a>
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
    </body>
</html>
