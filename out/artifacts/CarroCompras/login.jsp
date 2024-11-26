<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

</head>


<body>

<div class="container mt-5" >
    <h1 class="text-center">Login</h1>

    <div class="row justify-content-center">
        <div class="col-md-6">
            <form action="/CarroCompras/logins" method="post">
                <div class="mb-3">
                    <label for="usuario" class="form-label">Usuario</label>
                    <input type="text" class="form-control bs-primary-border-subtle "  id="usuario"
                           aria-describedby="usuarioHelp" name="username">
                    <div id="usuarioHelp" class="form-text">Por favor, ingresa tu nombre de usuario</div>
                </div>
                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label">Password</label>
                    <input type="password" class="form-control" id="exampleInputPassword1"
                    name="password">
                </div>
                <button type="submit" class="btn btn-primary w-100">Entrar</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
