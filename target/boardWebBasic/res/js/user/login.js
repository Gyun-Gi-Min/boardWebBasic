var frm =document.querySelector('#frm');
console.log('frm : ' + frm);


if(frm){
    /*function proc(){
        alert('전송!');
    }
    var proc2 = function(){
        alert('전송!!!')
    }
    frm.addEventListener('submit',proc);
    frm.addEventListener('submit',proc2); */
    function frmSubmitEvent(e){
        //아이디가 5글자 미만 혹은 20글자 초과면 "아이디는 5~20글자입니다."
        //비밀번호는 5글자 미만 일떄 비밀번호를 확인해주세요.
        if(frm.uid.value.length <5 || frm.uid.value.length>20 ){
            alert('아이디는 5~20글자입니다.');
            e.preventDefault();
            return;
        }else if(frm.upw.value.length <5){
            alert('비밀번호를 확인해주세요');
            e.preventDefault();
            return;
        }
            alert('전송');

        }

    // e.preventDefault(); //원래 submit이 하는일은 막는다.
    frm.addEventListener('submit',frmSubmitEvent);

    var btnShowPw = document.querySelector('#btnShowPw');
    if(btnShowPw) {
        btnShowPw.addEventListener('click', function () {
            if(frm.upw.type === 'password'){
                frm.upw.type = 'text';
                btnShowPw.value = '비밀번호 숨기기';
            }else{
                frm.upw.type = 'password';
                btnShowPw.value = '비밀번호 보이기';
            }
        });
    }

}



