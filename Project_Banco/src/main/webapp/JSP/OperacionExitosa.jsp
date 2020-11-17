<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../css/bootstrap.min.css" rel="stylesheet">
        <link href="../css/CSS.css" rel="stylesheet">
        <title>Operacion Exitosa</title>
    </head>
    <body>
        <%
            HttpSession sesion = request.getSession();
            if (sesion.getAttribute("Loggeado") == null || sesion.getAttribute("Loggeado").equals("0")) {
                response.sendRedirect("Login.jsp");
            }
        %>
        <div class="container">
            <div class="row justify-content-center pt-5 mt-5 mr-1">
                <div class="col-md-4 formulario">
                    <div class="alert alert-success" role="alert">
                        <h2><%=sesion.getAttribute("msj")%></h2>

                        <%if (sesion.getAttribute("CCB") != null) {
                        %>
                        <h1><%=sesion.getAttribute("CCB")%></h1>
                        <div class="dropdown-divider"></div>
                        <a href="Gerente.jsp" class="btn btn-light btn-block"><img src="../Imagenes/checkmark-outline.svg" width="25px">Terminar</a>
                            <%
                                }
                            %>

                        <%if (sesion.getAttribute("codigoC") != null) {
                        %>
                        <h1><%=sesion.getAttribute("codigoC")%></h1>
                        <div class="dropdown-divider"></div>
                        <a href="Gerente.jsp" class="btn btn-light btn-block"><img src="../Imagenes/checkmark-outline.svg" width="25px">Terminar</a>
                            <%
                                }
                            %>

                        <%if (sesion.getAttribute("codigoU") != null) {
                        %>
                        <h1><%=sesion.getAttribute("codigoU")%></h1>
                        <div class="dropdown-divider"></div>
                        <a href="Gerente.jsp" class="btn btn-light btn-block"><img src="../Imagenes/checkmark-outline.svg" width="25px">Terminar</a>
                            <%
                                }
                            %>

                        <%if (sesion.getAttribute("codigoG") != null) {
                        %>
                        <h1><%=sesion.getAttribute("codigoG")%></h1>
                        <div class="dropdown-divider"></div>
                        <a href="Gerente.jsp" class="btn btn-light btn-block"><img src="../Imagenes/checkmark-outline.svg" width="25px">Terminar</a>
                            <%
                                }
                            %>

                        <%if (sesion.getAttribute("codigoCL") != null) {
                        %>
                        <h1><%=sesion.getAttribute("codigoCL")%></h1>
                        <div class="dropdown-divider"></div>
                        <a href="Gerente.jsp" class="btn btn-light btn-block"><img src="../Imagenes/checkmark-outline.svg" width="25px">Terminar</a>
                            <%
                                }
                            %>
                        
                        <%if (sesion.getAttribute("msm") != null) {
                        %>
                        <h1><%=sesion.getAttribute("msm")%></h1>
                        <div class="dropdown-divider"></div>
                        <a href="Gerente.jsp" class="btn btn-light btn-block"><img src="../Imagenes/checkmark-outline.svg" width="25px">Terminar</a>
                            <%
                                }
                            %>
                    </div>                  
                </div>
            </div>
        </div>
    </body>
</html>
