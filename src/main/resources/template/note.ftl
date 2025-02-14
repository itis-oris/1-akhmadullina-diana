<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/html">
<head>
    <title>note Form</title>
    <meta charset="utf-8"/>

    <style>
        body {
            background-image: url('https://i.pinimg.com/736x/6b/5a/4d/6b5a4daeea0a5043483ca2a8d6d8df20.jpg');
            background-size: cover;
            background-position: center;
            font-family: cursive;
            margin: 0;
            padding: 0;
            color: white;
        }

        .form-container {
            max-width: 800px;
            margin: 50px auto;
            padding: 20px;
            background-color: rgba(0, 0, 0, 0.6);
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.5);
        }

        .form-container input[type="text"] {
            font-family: cursive;
            width: 98%;
            padding: 8px;
            margin-bottom: 16px;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            background-color: #f7f7f7;
        }

        .form-container input[type="submit"] {
            font-family: cursive;
            width: 100%;
            padding: 12px;
            border: none;
            border-radius: 5px;
            background-color:rgba(213, 31, 116, 0.9);
            color: white;
            font-size: 18px;
        }

    </style>
</head>
<body>

<div class="form-container">
    <h2>${title}</h2>

    <form action="note" method="post">
        <div>
            <label for="date">Date</label>
            <input type="text" id="date" value="${date}" name="date" placeholder="dd/mm/yyyy" pattern="\d{2}/\d{2}/\d{4}" required>
        </div>
        <div style="display: ${display!"none"}">
            <label for="author">Author</label>
            <input type="text" id="author" value="${author!""}"  name="author" placeholder="Enter author's name">
        </div>
        <div>
            <label for="title">Title</label>
            <input type="text" id="title" value="${displayed_name}" name="title" placeholder="Enter note title" required>
        </div>
        <div>
            <label for="text">Text</label>
            <input type="text" id="text" value="${text}" name="text" placeholder="Your notes" required>
        </div>
        <div>
            Tags
            <td><#list tags as tag>
                    <div>
                        <label>
                            <input type="checkbox" name="tag" class="tag_checkbox" value=${tag.id_tag}>
                            ${tag.name}
                        </label>
                    </div>
                </#list>
            </td>
        </div>
        <div>
            <input type="submit" value="Save">
        </div>
    </form>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        const dateInput = document.getElementById('date');

        dateInput.addEventListener('input', function(e) {
            let value = dateInput.value.replace(/\D/g, ''); // Удаляем все нечисловые символы
            if (value.length > 2) value = value.slice(0, 2) + '/' + value.slice(2);
            if (value.length > 5) value = value.slice(0, 5) + '/' + value.slice(5);
            dateInput.value = value;
        });
    });
</script>

</body>
</html>
