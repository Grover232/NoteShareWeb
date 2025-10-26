<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>NoteShareWeb - Login</title>

    <!-- Vincula tus estilos -->
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/index.css">

    <!-- Fuentes externas (mantén esto del HTML exportado) -->
    <link
      rel="stylesheet"
      href="https://unpkg.com/animate.css@4.1.1/animate.css"
    />
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&display=swap"
    />
</head>

<body>
<%
    String msg = request.getParameter("msg");
    if("error".equals(msg)){
%>
    <p style="color:red; text-align:center;">Correo o contraseña incorrectos</p>
<%
    } else if("registrado".equals(msg)){
%>
    <p style="color:green; text-align:center;">Registro exitoso, ya puedes iniciar sesión</p>
<%
    } else if("login".equals(msg)){
%>
    <p style="color:blue; text-align:center;">Debes iniciar sesión primero</p>
<%
    }
%>

<div class="right-container1">
  <div class="right-right">
    <div class="right-frame1st">
      <div class="right-text10">
        <span class="right-text11">Login</span>
      </div>

      <form class="right-form" action="login" method="post">
        <!-- Campo de correo -->
        <div class="right-inputfield1">
          <div class="right-frame-inputfieldbase1">
            <div class="right-inputwithlabel1">
              <span class="right-text12 TextsmMedium">Correo:</span>
              <div class="right-input1">
                <div class="right-content1">
                  <img src="public/mail125-quvf.svg" alt="mail" class="right-mail"/>
                  <input type="email" name="correo" class="right-text13 TextmdNormal"
                         placeholder="abc@gmail.com" required>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- Campo de contraseña -->
        <div class="right-inputfield2">
          <div class="right-frame-inputfieldbase2">
            <div class="right-inputwithlabel2">
              <span class="right-text14 TextsmMedium">Contraseña:</span>
              <div class="right-input2">
                <div class="right-content2">
                  <img src="public/user01138-xke.svg" alt="user" class="right-user01"/>
                  <input type="password" name="contrasena" class="right-text15 TextmdNormal"
                         placeholder="••••••" required>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- Botón de login -->
        <button type="submit" class="right-frame-buttonbase">
          <span class="right-text16 TextlgMedium">Ingresar</span>
          <div class="right-chevronright">
            <div class="right-group1">
              <div class="right-group2">
                <img src="public/path151-fwi.svg" alt="flecha" class="right-path"/>
              </div>
            </div>
          </div>
        </button>
      </form>
    </div>

    <!-- Enlace a registro -->
    <div class="right-frame2nd">
      <div class="right-text17">
        <div class="right-text18">
          <span class="right-text19">¿No tienes cuenta?</span>
        </div>
        <div class="right-login-btn">
          <a href="registro.jsp" class="right-text20">Regístrate aquí</a>
        </div>
      </div>
    </div>
  </div>
</div>

</body>
</html>



