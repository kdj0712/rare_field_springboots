<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>희귀질환정보 공유 플랫폼 Rare Field</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
    <link rel="icon" type="image/png" href="/data/img/favicon.ico">
    <style>
        #maps >*{
            border-style: dotted 1px black;
        }
        * {
            
            font-family: "Noto Sans KR", sans-serif;
            font-optical-sizing: auto;
            list-style-type: none;
            text-decoration: none;
            color: black;
            text-decoration-line: none;

        }

        #field {
            font-size: larger;
            font-weight: bold;
        }

        a {
            text-decoration: none;
            color: black;
            text-decoration-line: none;
        }

        /* #float_banner1 {
            position: fixed;

            overflow: hidden;
        }
        #float_banner2 {
            position: fixed;
            overflow: hidden;
        } */
    </style>
</head>

<body>

    <%@ include file="/WEB-INF/rarefield/views/commons/header.jsp" %>

    <%@ include file="/WEB-INF/rarefield/views/commons/mainpage_main.jsp" %> 
    
   

</body>
<%@ include file="/WEB-INF/rarefield/views/commons/footer.jsp" %> 
<script type="text/javascript">

    function search() {
        var search_select = document.getElementById("search_select").value;
        var search_word = document.getElementById("search_word").value;

        if (search_select && search_word) {
            var formAction = '';


            // 동적으로 폼 생성하고 제출
            var form = document.createElement('form');
            form.method = 'POST';
            if (search_select === 'dise_name_kr') {
                formAction = '/info/info_raredisease'
                form.action = formAction + '?key_name=' + encodeURIComponent(search_select) + '&search_word=' + encodeURIComponent(search_word);
            } else if (search_select === 'institution_name') {
                formAction = '/info/info_institution'
                form.action = formAction + '?keyword=' + encodeURIComponent(search_word);
            }


            document.body.appendChild(form);
            form.submit();
        } else {
            alert('선택과 검색어를 모두 입력해주세요.');
        }
    }
</script>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
{% block js %}
{% endblock js %}

</html>