<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ include file="/WEB-INF/rarefield/views/commons/header.jsp" %>

  <!-- Quill 편집기의 스타일 지정 -->
  <style>
    #editor {
        height: 35rem;
        overflow: auto;
    }
  
  </style>



<main class="row justify-content-between">

    <%@ include file="/WEB-INF/rarefield/views/commons/side_left_banner.jsp" %>

    <div class="col-8 row">
        <div class="container">
            <form action="" method="get" id="">
                
                <p style="font-size: small;">
                    {{communitys.community_type}}  >  {{communitys.community_subject}}
                </p>
                <br>
        
                <div>
                    <h3>{{communitys.community_title}}</h3>
                    <div>{{communitys.community_related_diseases}}</div>
                    <div style="font-size: small;" class="text-end">{{communitys.community_writer}} | {{communitys.community_date}}</div>
                </div>
        
                <hr>
        
                <div>
                    <div id="editor">{{communitys.community_content}}</div>
                </div>
        
                <br>
                <div style="font-size: small;">찜 | 댓글수</div>
        
                <hr>
        
                <!-- 댓글기능 -->
        
            </form>
        </div>
    </div>

    <%@ include file="/WEB-INF/rarefield/views/commons/side_right_banner.jsp" %>

      
    </main>
    <hr>
    <%@ include file="/WEB-INF/rarefield/views/commons/footer.jsp" %>

    <script src="https://cdn.jsdelivr.net/npm/quill@2.0.0-beta.0/dist/quill.js"></script>
    <script>
        // 서버나 다른 소스에서 로드한 콘텐츠를 대표하는 가상의 데이터
        let contentFromServer = '{{communitys.community_content | safe}}';
    
        // Quill 편집기 초기화
        let quill = new Quill('#editor', {
            theme: 'snow',
            readOnly: true, // 읽기 전용으로 설정
            modules: {
                toolbar: false // 툴바 비활성화
            }        
        });
    
        // 서버에서 가져온 콘텐츠로 편집기를 채움
        quill.root.innerHTML = contentFromServer;
    </script>
    

    </html>