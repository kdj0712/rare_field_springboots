<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ include file="/WEB-INF/rarefield/views/commons/header.jsp" %>

<!-- Quill의 CSS 파일 링크 -->
<link href="https://cdn.jsdelivr.net/npm/quill@2.0.0-beta.0/dist/quill.snow.css" rel="stylesheet" />
<!-- Quill 편집기의 스타일 지정 -->
<style>
    #editor {
        height: 10rem;
        font-size: 1.1rem;
    }
</style>



<main class="row justify-content-between">

    <%@ include file="/WEB-INF/rarefield/views/commons/side_left_banner.jsp" %>

    <div class="col-8 row">
        <div class="col-md-9 col-lg-10 px-md-4 p-5">
            <form action="" method="post" id="myForm">
                <div class="mb-3">
                    <div class="row p-1">
                        <div class="col-2">
                            <label class="" for="community_title">제목</label>
                        </div>
                        <div class="col-10">
                            <input type="" class="form-control" name="community_title" id="community_title" value="" required>
                        </div>
                    </div>
        
                    <div class="row p-1">
                        <div class="col-2">
                            <label for="notice_type">커뮤니티 유형</label>
                        </div>
                        <div class="col-2">
                            <select class="form-control" name="community_choice" id="community_choice" required>
                                <option value="none" style="text-align: center;" selected hidden>=====선택=====</option>
                                <option value="궁금해요">궁금해요</option>
                                <option value="추천해요">추천해요</option>
                                <option value="자랑해요">자랑해요</option>
                                <option value="찾아요">찾아요</option>
                            </select>
                        </div>
                    </div>
    
        
                    <div class="row p-1">
                        <div class="col-2">
                            <label>프로그램 내용</label>
                        </div>
                        <div class="col-10">
                            <!-- Quill 편집기를 적용할 div 요소 -->
                            <input type="hidden" name="community_content" id="editorContent" value="" required>
                            <div id="editor"></div>
                            <input type="hidden" name="community_comment" id="community_comment" value=null required>
                        </div>
                    </div>
                    <div style="text-align: end;" class="p-2">
                        <button class="btn btn-primary" type="submit" formaction="/empo_community/insert" name="btn_type"
                            formmethod="post">글쓰기</button>
                        <button class="btn btn-primary" type="submit" formaction="/empo_community"
                            formmethod="get">목록</button>
                    </div>
                </div>
            </form>
           
        </div>
    </div>

    <%@ include file="/WEB-INF/rarefield/views/commons/side_right_banner.jsp" %>

      
    </main>
    <hr>
    <%@ include file="/WEB-INF/rarefield/views/commons/footer.jsp" %>

<!-- Quill의 JavaScript 파일 링크 -->
<script src="https://cdn.jsdelivr.net/npm/quill@2.0.0-beta.0/dist/quill.js"></script>

<!-- Quill 편집기 초기화 스크립트 -->
<script>

    document.addEventListener('DOMContentLoaded', function () {
        var community_type = document.getElementById('community_type');
        var community_subject = document.getElementById('community_subject');
        var options = {
            '궁금해요': ['질병정보', '의료보험정보'],
            '추천해요': ['의료기관 추천', '의료보조기구 추천', '신약 추천'],
            '자랑해요': ['취업성공', '소소한 행복'],
            '찾아요': ['간병인 찾기', '의료보조기구 찾기']
        };
        community_type.addEventListener('change', function () {
            // 두 번째 셀렉트 메뉴를 초기화
            community_subject.innerHTML = '';
            // 선택된 첫 번째 셀렉트 메뉴의 값에 따라 옵션 설정
            var selectedOption = options[community_type.value];
            for (var i = 0; i < selectedOption.length; i++) {
                var option = document.createElement('option');
                option.value = selectedOption[i];
                option.text = selectedOption[i];
                community_subject.appendChild(option);
            }
        });
    });

    var quill = new Quill('#editor', {
        theme: 'snow'
    });
    let form = document.querySelector('#myForm');
    form.onsubmit = function (event) {
        let editorContentInput = document.querySelector('#editorContent');
        editorContentInput.value = quill.root.innerHTML;

    };

   

</script>
    

    </html>