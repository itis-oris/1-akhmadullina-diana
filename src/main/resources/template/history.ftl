<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            font-family: cursive;
            margin: 0;
            padding: 0;
            /*background-image: url('https://i.pinimg.com/736x/e8/ff/5a/e8ff5ad05b623472b1f3c5335f0fe62d.jpg');*/
            background-image: url('https://i.pinimg.com/736x/6c/56/f5/6c56f5c7234f125b3b5e8e40784fb79f.jpg');
            background-size: cover;
            background-position: center;
            color: white;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }

        h2 {
            font-family: cursive;
            font-size: 80px;
            margin: 20px;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.6);
            color: white;
        }

        table {
            width: 80%;
            margin: 0 auto;
            border-collapse: collapse;
            background-color: rgb(255, 255, 255, 0.7);
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
        }

        td, th {
            padding: 12px;
            text-align: left;
            color: #333;
        }

        form {

            text-align: center;
            margin: 20px 0;
        }

        input[type="submit"] {
            font-family: cursive;
            background-color: rgba(213, 31, 116, 0.9);
            color: white;
            padding: 15px 30px;
            font-size: 1.2em;
            border: none;
            border-radius: 5px;
            cursor: pointer
        }

        .button-container {
            display: flex;
            justify-content: center;
            align-items: center;
            margin: 10px;
            gap: 20px
        }
    </style>
</head>
<body>

<h2>All Notes</h2>

<div>
<form action="add" method="get">
    <input type="submit" value="Add Note →">
</form>
</div>

<#--<div><form action="main" method="get">-->
<#--    <input type="hidden" name="order" value="order">-->
<#--    <div>-->
<#--        <input type="submit" value="change date order">-->
<#--    </div>-->
<#--</form><div>-->

<table>
    <thead>
    <tr>
        <th>date</th>
        <th>info</th>
        <th>text</th>
        <th>tags</th>
    </tr>
    </thead>
    <tbody>
    <#list notes as note>
        <tr>
            <td>${note.date}</td>
            <td>${note.displayed_name}</td>
            <td>${note.text}</td>
            <td>${tagNoteService.getTagNamesByIdNote(note.id_note)}</td>

            <td>
                <form action="delete" method="post">
                    <input type="hidden" name="id_note" value="${note.id_note}">
                    <input type="hidden" name="action" value="delete">
                    <div>
                        <input type="submit" value="delete">
                    </div>
                </form>
            </td>
            <td>
                <form action="delete" method="get">
                    <input type="hidden" name="id_note" value="${note.id_note}">
                    <input type="hidden" name="action" value="edit">
                    <div>
                        <input type="submit" value="edit">
                    </div>
                </form>
            </td>
        </tr>
    </#list>
    </tbody>
</table>

<div class="button-container">
    <form action="main" method="get">
        <input type="hidden" name="move" value="prev">
        <input type="submit" value="<-">
    </form>
    <form action="main" method="get">
        <input type="hidden" name="move" value="next">
        <input type="submit" value="->">
    </form>
</div>

</body>
</html>