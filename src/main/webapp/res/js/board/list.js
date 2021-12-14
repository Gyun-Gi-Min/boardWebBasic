function moveToDetail(iboard){
    console.log('iboard : '+iboard);
    location.href="/board/detail?iboard=" + iboard;
}

var btn = document.querySelector(".btn-toggle");
btn.addEventListener("click",function (){
    document.body.classList.toggle("dark-theme");
})


var searchFrmElem = document.querySelector('#searchFrm');
//querySelector는 1개의 주소값만 넘겨주거나 안넘겨줌.
if(searchFrmElem){
    var rowCntSelectElem = searchFrmElem.rowCnt;
    rowCntSelectElem.addEventListener('change',function (e){
            searchFrmElem.submit();

            //option 변경하면 submit 하도록하는 함수.
    })
}
