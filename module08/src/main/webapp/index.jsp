<%@page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<head>
    <meta charset="UTF-8">
    <title>Расчёт доходности вклада</title>
</head>
<body>
<h1>Расчёт доходности вклада</h1>
<form action="/finance" method="post">
    <table>
        <tr>
            <td>
                Сумма на момент открытия
            </td>
            <td>
                <input type="number" name="sum" placeholder="Сумма на момент открытия" required>
            </td>
        </tr>
        <tr>
            <td>
                Годовая процентная ставка
            </td>
            <td>
                <input type="number" name="percentage" placeholder="Годовая процентная ставка" required>
            </td>
        </tr>
        <tr>
            <td>
                Количество лет
            </td>
            <td>
                <input type="number" name="years" placeholder="Количество лет" required>
            </td>
        </tr>
        <tr>
            <td>
            </td>
            <td>
                <input type="submit" value="Рассчитать">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
