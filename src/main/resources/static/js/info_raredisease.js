
document.addEventListener("DOMContentLoaded", function () {
    // URL에서 쿼리 파라미터 가져오기
    const urlParams = new URLSearchParams(window.location.search);
    const keyName = urlParams.get('key_name');
    const searchWord = urlParams.get('search_word');
    const currentPage = urlParams.get('currentPage');

    // 폼 입력 필드에 값 설정하기
    if (keyName !== null) {
        document.getElementById('keyName').value = keyName;
    }
    if (searchWord !== null) {
        document.getElementById('searchWord').value = searchWord;
    }
});


function fn_downExcel() {
    fetch('/download')
        .then(response => response.blob())
        .then(blob => {
            const url = window.URL.createObjectURL(blob);
            const a = document.createElement('a');
            a.style.display = 'none';
            a.href = url;
            a.download = 'data/csv/[헬프라인]희귀질환목록_2024_03_20_10_34_05.xlsx';
            document.body.appendChild(a);
            a.click();
            window.URL.revokeObjectURL(url);
            document.body.removeChild(a);
        })
        .catch(error => console.error('다운로드 중 에러가 발생했습니다.', error));
}

function goToPage(pageNumber) {
    var form = document.getElementById('searchForm');
    if (form) {
        var keyName = document.querySelector('select[name="key_name"]').value;
        var searchWord = document.querySelector('input[name="search_word"]').value;
        var baseUrl = form.action.split('?')[0];
        var queryString = `?currentPage=${pageNumber}`;

        if (searchWord) {
            queryString += `&key_name=${encodeURIComponent(keyName)}`;
            queryString += `&search_word=${encodeURIComponent(searchWord)}`;
        }

        console.log('keyName:', keyName);
        console.log('searchWord:', searchWord);
        console.log('baseUrl:', baseUrl);
        console.log('currentPage:', pageNumber);
        console.log('queryString:', queryString);

        window.location.href = baseUrl + queryString;
        // form.submit();
    } else {
        console.error("Form not found");
    }
}


// 페이지 로드 시 기존 값 설정
window.onload = function () {
    const urlParams = new URLSearchParams(window.location.search);

    if (urlParams.has('key_name')) {
        document.getElementById('keyName').value = urlParams.get('key_name');
    }

    if (urlParams.has('search_word')) {
        document.getElementById('searchWord').value = urlParams.get('search_word');
    }
};
