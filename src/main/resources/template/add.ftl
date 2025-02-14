<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Notes</title>
    <style>
        body {
            font-family: cursive;
            margin: 0;
            padding: 0;
            background-image: url('https://i.pinimg.com/736x/6c/56/f5/6c56f5c7234f125b3b5e8e40784fb79f.jpg');
            background-size: cover;
            background-position: center;
            display: flex;
            align-items: center;
            flex-direction: column;
        }

        h1 {
            font-size: 80px;
            color: white;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.6);
            border-radius: 40px;
        }

        form {
            text-align: center;
            margin-top: 30px;
        }


        input[type="submit"] {
            font-family: cursive;
            width: 160px;
            background-color: rgba(71, 33, 33, 0.7);
            color: white;
            padding: 15px 30px;
            font-size: 1.2em;
        }

    </style>
</head>
<body>

<div class="header">
    <h1>Add Notes</h1>
</div>

<#--<td>-->
<#--<form action="book" method="get">-->
<#--    <input type="submit" value="book">-->
<#--</form>-->
<#--<td>-->

<#--<td>-->
<#--<form action="movie" method="get">-->
<#--    <input type="submit" value="movie">-->
<#--</form>-->
<#--<td>-->

<#--<td>-->
<#--    <form action="music" method="get">-->
<#--        <input type="submit" value="music">-->
<#--    </form>-->
<#--<td>-->

<td>
    <form action="note" method="get">
        <input type="submit" name="type" value="book">
    </form>
<td>

<td>
    <form action="note" method="get">
        <input type="submit" name="type" value="movie">
    </form>
<td>

<td>
    <form action="note" method="get">
        <input type="submit" name="type" value="music">
    </form>
<td>

</body>
</html>