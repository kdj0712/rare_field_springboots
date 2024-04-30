<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">


    {% block css %}


    {% endblock css %}

</head>

<body>

   
    {% block wordcloud %}
    {% endblock %}

    {% block mainpage_main %}
    <main class="row justify-content-between">
       

        <div class="col-8 row">
            {% block main_container %}


            {% endblock main_container%}
        </div>
       

    </main>
    {% endblock mainpage_main %}

<hr>

   

</body>
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