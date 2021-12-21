
var cmtListContainerElem = document.querySelector('#cmtListContainer');
var cmtModContainerElem = document.querySelector('.cmtModContainer');

//(댓글 수정) 취소 버튼 클릭 이벤트 연결
var btnCancelElem = document.querySelector('#btnCancel');
btnCancelElem.addEventListener('click',function (){
    cmtModContainerElem.style.display='none';
    var selectedTrElem = document.querySelector('.cmt_selected');
    selectedTrElem.classList.remove('cmt_selected');
    
    tr.classList.add('cmt_selected');

});

var cmtModFrmElem = cmtModContainerElem.querySelector('#cmtModFrm');
var submitBtnElem = cmtModFrmElem.querySelector('input[type=submit]');
submitBtnElem.addEventListener('click',function (e){
    e.preventDefault();
    var url = '/board/cmt?proc=upd';
    //댓글 수정 : ctnt, icmt
    var param = {

        'icmt' : cmtModFrmElem.icmt.value,
        'ctnt' : cmtModFrmElem.ctnt.value
    };
    fetch(url, { //보낼때 headers 와 body를 보내줘야함.
        'method' : 'POST',
        'headers' : {'Content-Type' : 'application/json'}, //json형태로 날아가
        'body': JSON.stringify(param)
    }).then(function (res){
        return res.json();
    }).then(function (data){
        console.log(data.result);
        switch (data.result){
            case 0://수정 실패
                alert('댓글 수정을 할 수 없습니다.');
                break;
            case 1://수정 성공
                modCtnt(param.ctnt); //수정하고
                var e = new Event('click');
                btnCancelElem.dispatchEvent(e);  //이렇게하면 취소버튼 효과가 남.
                break;
        }
    }).catch(function (err){
        console.log(err);
    })

});

function modCtnt(ctnt){
    var selectedTrElem = document.querySelector('.cmt_selected');
    var tdCtntElem = selectedTrElem.children[0];
    tdCtntElem.innerText = ctnt;
}


if(cmtListContainerElem){

    function getList(){
        var url = '/board/cmt?iboard=' + cmtListContainerElem.dataset.iboard;
        fetch(url).then(function(res){
            return res.json();
        }).then(function (data){
            console.log(data);
            displayCmt2(data);
           /*
           location.reload();
           cmtListContainerElem.style.display = 'none';
           */
        }).catch(function (err){
            console.log(err);
        })

    }

    function displayCmt2(data){
        var tableElem = document.createElement('table');
        tableElem.innerHTML = `
            <tr>
                <th>내용</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>비고</th>
            </tr>
        `; //템플릿 리터럴
        cmtListContainerElem.appendChild(tableElem);

        var loginUserPk = cmtListContainerElem.dataset.loginuserpk === '' ? 0 : Number(cmtListContainerElem.dataset.loginuserpk);
        //HTML에서 가져오는건 전부다 문자열

        console.log('loginUserPk : ' + loginUserPk);




        data.forEach(function (item){
            var tr = document.createElement('tr');

            var ctnt = item.ctnt.replaceAll('<','&lt').replaceAll('>','&gt');

            tr.innerHTML = `
                <td>${item.ctnt}</td> <!--EL식 아님 주의!  | 자바스크립트 문법임.-->
                <td>${item.writerNm}</td>
                <td>${item.rdt}</td>
     
            `;
            tableElem.appendChild(tr);
            var lastTd = document.createElement('td');

            if(loginUserPk === item.writer){
                var btnMod = document.createElement('button');
                btnMod.innerText='수정';
                btnMod.addEventListener('click',function (){
                    tr.classList.add('cmt_selected'); //수정 누르면 tr class 바꿔줌
                    var ctnt = tr.children[0].innerText;
                    openModForm(item.icmt,ctnt);
                });
                var btnDel = document.createElement('button');
                btnDel.innerHTML='삭제';

                lastTd.appendChild(btnMod);
                lastTd.appendChild(btnDel);
                tr.appendChild(lastTd);
            }else{
                tr.appendChild(lastTd);
            }


        });
    }

    function openModForm(icmt, ctnt){ //자바스크립스의 구조분해할당?

        cmtModContainerElem.style.display='flex';

        cmtModFrmElem.icmt.value = icmt;
        cmtModFrmElem.ctnt.value = ctnt;
    }



    function displayCmt(data){
        var tableElem = document.createElement('table');
        
        var tr = document.createElement('tr');
        var th1 = document.createElement('th');
        th1.innerText='내용';
        var th2 = document.createElement('th');
        th2.innerText='작성자';
        var th3 = document.createElement('th');
        th3.innerText='작성일';
        var th4 = document.createElement('th');
        th4.innerText='비고';

        tr.appendChild(th1);
        tr.appendChild(th2);
        tr.appendChild(th3);
        tr.appendChild(th4);

        tableElem.appendChild(tr);
        cmtListContainerElem.appendChild(tableElem);
        
       
    }
    getList();

}

