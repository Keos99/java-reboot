<%@page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
    <head>
        <title>Результат</title>
    </head>
    <body>

    <h1>Результат</h1>
        <p>Итоговая сумма:
        <%=request.getAttribute("calc")%>
        рублей

    </body>
</html>