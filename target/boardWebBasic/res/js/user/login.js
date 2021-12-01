var frm =document.querySelector('#frm');
if(frm){
    function proc(){
        alert('전송!');
    }
    var proc2 = function(){
        alert('전송!!!')
    }

    frm.addEventListener('submit',proc);
    frm.addEventListener('submit',proc2);
    frm.addEventListener('submit',function (e){
        alert('전송');
        e.preventDefault(); //원래 submit이 하는일은 마그낟,
    });
}