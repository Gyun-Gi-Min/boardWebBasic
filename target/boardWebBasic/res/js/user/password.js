var frmElem = document.querySelector('#frm');
var submitBtnElem = document.querySelector('#submitBtn');

submitBtnElem.addEventListener('click',function (){


    // 현재 비밀번호도 5자 이하면 alert
    // 변경 비밀번호 5자이하 alert
    // 변경 , 확인 값이 달라도 alert
    // 다 통과하면 submit 통과하면?????????
    if(frm.upw.value.length < 5){
        alert('현재 비밀번호를 확인해 주세요');
    }  else if (frm.changedUpw.value.length<5){
        alert('변경 비밀번호를 확인해주세요');
    } else if(frm.changedUpw.value !== frm.changedUpwConfirm.value){
        alert('변경 비밀번호가 일치하는지 확인해주세요');
    } else{
        frmElem.submit();
    }


});