// div비활성화 상태 체크
function checkSessionStorage(){
	var c1it4 = sessionStorage.getItem("c1it4");
	var locker406 = sessionStorage.getItem("406호사물함");
	var c1it5_memo = sessionStorage.getItem("c1it5_memo");
	var to2Door = sessionStorage.getItem("복도");
	var toSpecialDoor = sessionStorage.getItem("숨겨진방");
	var c3it1 = sessionStorage.getItem("c3it1");
	var c3it2 = sessionStorage.getItem("c3it2");
	var c1it6 = sessionStorage.getItem("c1it6_div");
	var c3it5_memo = sessionStorage.getItem("c3it5_memo");
	
	var c2it5 = sessionStorage.getItem("c2it5");
	var c2it1_cover = sessionStorage.getItem("복도단자함");
	var memo2 = sessionStorage.getItem("메모2");
	var memo4 = sessionStorage.getItem("메모4");
	var c2it2_memo = sessionStorage.getItem("c2it2_memo");
	var c4it1_memo = sessionStorage.getItem("c4it1_memo");
	var meetingRoom1 = sessionStorage.getItem("세미나실");
	var meetingRoom2 = sessionStorage.getItem("분임토의3실");
	var meetingRoom3 = sessionStorage.getItem("분임토의4실");
	var locker = sessionStorage.getItem("잠긴사물함");
	
	if(c1it4){	
		$(c1it4).remove();
	}
	if(locker406){
		$('#c1it5_div').attr('data-target','#c1it5_memo_modal');
	}
	if(c1it5_memo){
		$('#c1it5_div').remove();
	}
	if(to2Door){
		$('#to2Door').removeAttr('data-toggle');
		$('#to2Door').removeAttr('data-target');
		$('#to2Door').attr('onclick','location.href="view.do?viewNo=2"');
	}
	if(toSpecialDoor){
		$('#toSpecialDoor').attr('onclick','moveLocation(3)');
	}
	if(c3it1){	
		$(c3it1).remove();
	}
	if(c3it2){	
		$(c3it2).remove();
	}
	if(c1it6){
		$('#c1it6_div').attr('data-target','#c1it6_answer_modal');
	}
	if(c3it5_memo){
		$('#c3it5_div').remove();
	}
	
	if(c2it5){
		$(c2it5).remove();
	}
	if(c2it1_cover){
		$(c2it1_cover).remove();
	}
	if(memo2){
		$(memo2).remove();
	}
	if(memo4){
		$(memo4).remove();
	}
	if(c2it2_memo){
		$(c2it2_memo).remove();
	}
	if(c4it1_memo){
		$(c4it1_memo).remove();
	}
	if(meetingRoom1){
		$(meetingRoom1).remove();
		$('#class6items #btn1').css('display','block');
	}
	if(meetingRoom2){
		$(meetingRoom2).remove();
		$('#class6items #btn2').css('display','block');
	}
	if(meetingRoom3){
		$(meetingRoom3).remove();
		$('#class8items #btn2').css('display','block');
	}
	if(locker){
		$(locker).remove();
		$('#c5it5_div').css("display", "block");
	}
}

// 방에 있는 아이템 클릭시
$('.item').click(function(){
	insertItem(this);		//DB에 아이템추가, 인벤토리에 아이템 보이기
	$('button[name="closeModal"]').click();
	closeInven();
});


///////////////////////////////////////////////// 인벤토리	
// DB에 아이템 추가
function insertItem(obj){
	var id = $(obj).attr('id');
	var text = $(obj).attr('data-text');
	var src = $(obj).attr('src');
	var splitSrc= src.split("/");  
	var fileName= splitSrc[splitSrc.length-1];  
	var sessionData = id + "=#" + id + "_div";
	
	var data = {
		"invenItemNo" : id
		,"invenCode" : sessionStorage.getItem("invenCode")
		,"gameNo" : 1
		,"invenItemInfo" : text
		,"invenItemSrc" : fileName
		,"sessionData": sessionData
	}
	$.ajax({
		url : "/CCNEE/game/view1.do"
		,type : "POST"
		,data : JSON.stringify(data)
		,success : function(data){
			
			alert("아이템을 획득하였습니다.");
			
			$('.modal-backdrop').remove();
			// div상태 세션에 저장
			var divId = "#" + id + "_div";
			sessionStorage.setItem(id, divId);
			// div상태 변경
			checkSessionStorage();
		}
		,error : function(error){
			alert('이미 획득한 아이템입니다.');
			$('.modal-backdrop').remove();
		}
	});
}

// 인벤토리 열기/닫기
function openInven() {
	//인벤여는 순간 db에서 아이템 불러오기
	$('.emptyDiv .item').remove();
	readItem();
    document.getElementById("myInven").style.height = "80px";
    document.getElementById("openInven").style.bottom = "80px";
    $("#openInven").removeAttr("onclick");
    $("#openInven").attr("onclick","closeInven()");
}
function closeInven() {
    document.getElementById("myInven").style.height = "0";
    document.getElementById("openInven").style.bottom = "0";
    $("#openInven").removeAttr("onclick");
    $("#openInven").attr("onclick","openInven()");
}

// 인벤토리 데이터 불러오기
function readItem(){
	var invenCode = sessionStorage.getItem("invenCode");
	var cnt = 1;
	$.getJSON("/CCNEE/game/view1/itemList.do?invenCode="+invenCode,function(invenItemList){
		$.each(invenItemList, function(invenItemList, item) {
   	 		var img = document.createElement("img");
   	 		img.id = item.invenItemNo;
   	 		img.src= "/CCNEE/resources/images/"+item.invenItemSrc;
   	 		img.dataset.text=item.invenItemInfo;
   	 		img.dataset.inven=true;
   	 		img.style.zIndex = 2;
   	 		img.style.top=0;
   	 		img.style.left=0;
   	 		img.className = 'item';
   	 		img.setAttribute('onclick','itemView(this)');
   	 		img.setAttribute('data-toggle','modal');
   	 		img.setAttribute('data-target','#item_modal');
   	 		$('#in'+cnt+'Div').prepend(img);
   	 		cnt+=1;
		});
	});
}


// 인벤토리 아이템 클릭시
function itemView(obj){
	if($(obj).attr("class").indexOf("selectItem") > -1){
    	var src = $(obj).attr('src');
    	var title = $(obj).attr('data-text').split('$$')[0];
    	var text = $(obj).attr('data-text').split('$$')[1];
    	
    	var params = {
    		src: src,
    		title: title,
    		text: text
    	}
    	printData(params, $('body'), $('#game-item-template'), $('#item_modal'));
	}else{
		$('#item_modal').remove();
    	$('.inven .item').removeClass("selectItem");
    	$(obj).addClass("selectItem");
	}
}

// 인벤토리 아이템 삭제
function deleteItem(itemNo){
	$.ajax({
		url:"/CCNEE/game/inven/delete.do",
		type:'post',
		data:{itemNo: itemNo},
		success:function(data){
			closeInven();
		},
		error:function(xhr){
			alert("인벤토리 아이템 삭제 실패");
		}
	})
}


////////////////////////////////////////////////////// 게임 진행 유틸 관련
//엔터키 입력
function enterCheck(value){
	if(event.keyCode == 13){
		if(typeof value == 'string'){
			memoCheck(value);
		}else{
			passwordCheck(value);
		}
	}
}

// 패스워드 입력 체크
function passwordCheck(doorNum){
	var inputPass = $('input[name="room'+doorNum+'"]');
	var password = inputPass.val();
	
	if(doorNum == 0){	// 최종 문
		if(password == "COVID"){
			alert("성공!");
			$.ajax({
				url:'/CCNEE/playtime/insert.do',
				type:'post',
				success:function(data){
				},
				error:function(xhr){
					alert("플레이타임 기록중 에러 발생");
				}
			});
		}
	}else if(doorNum == 1){	// 회의실1
		if(password == "1516"){	// 회의실 비밀번호
			alert("문이 열렸습니다.");
			
			$('#c2it10_modal .close').click();
			$('#c2it10_div').remove();
		
			// 버튼 보이기
			$('#class6items #btn1').css('display','block');
			
			sessionStorage.setItem("세미나실", "#c2it10_div");
			gameStatusChange("세미나실", "#c2it10_div");
			
			window.location.href="view.do?viewNo=3";
		}else{
			alert("비밀번호가 맞지 않습니다.");
			inputPass.val("");
			inputPass.focus();
		}
	}else if(doorNum == 2){	// 회의실2
		if(password == "MEAL"){	// 회의실 비밀번호
			alert("문이 열렸습니다.");
			
			$('.door_modal .close').click();
			$('#c2it11_div').remove();
		
			// 버튼 보이기
			$('#class6items #btn2').css('display','block');
			
			sessionStorage.setItem("분임토의3실", "#c2it11_div");
			gameStatusChange("분임토의3실", "#c2it11_div");
		}else{
			alert("비밀번호가 맞지 않습니다.");
			inputPass.val("");
			inputPass.focus();
		}
	}else if(doorNum == 3){	// 회의실3
		if(password == "1740"){	// 회의실 비밀번호
			alert("문이 열렸습니다.");
			
			$('.door_modal .close').click();
			$('#c4it6_div').remove();
		
			// 버튼 보이기
			$('#class8items #btn2').css('display','block');
			
			sessionStorage.setItem("분임토의4실", "#c4it6_div");
			gameStatusChange("분임토의4실", "#c4it6_div");
		}else{
			alert("비밀번호가 맞지 않습니다.");
			inputPass.val("");
			inputPass.focus();
		}
	}else if(doorNum == 4){	// 최종 비밀번호 힌트
		if(password == "20"){
			alert("사물함이 열렸습니다.");
			
			$('.door_modal .close').click();
			$('#c5it4_div').remove();
			$('#c5it5_div').css("display", "block");
		
			sessionStorage.setItem("잠긴사물함", "#c5it4_div");
			gameStatusChange("잠긴사물함", "#c5it4_div");
			
			$('#c5it5_div').click();
		}else{
			alert("비밀번호가 맞지 않습니다.");
			inputPass.val("");
			inputPass.focus();
		}
	}else if(doorNum == 5){	// 406호 사물함
		if(password == "7"){
			alert("사물함이 열렸습니다.");
			
			$('#c1it5_modal .close').click();
			$('#c1it5_memo_modal').modal();
			$('#c1it5_div').attr('data-target','#c1it5_memo_modal');
			
			sessionStorage.setItem("406호사물함", "#c1it5_div");
			gameStatusChange("406호사물함", "#c1it5_div");
			
			
		}else{
			alert("열리지 않는다.");
			inputPass.val("");
			inputPass.focus();
		}
	}else if(doorNum == 6){	// 406호
		if(password == "4911"){
			alert("문이 열렸습니다.");
			
			$('.door_modal .close').click();
			$('.modal-backdrop').remove();
			$('#to2Door').removeAttr('data-toggle');
			$('#to2Door').removeAttr('data-target');
			$('#to2Door').attr('onclick','location.href="view.do?viewNo=2"');
			window.location.href="view.do?viewNo=2";
			
			sessionStorage.setItem("복도", "#to2Door");
			gameStatusChange("복도", "#to2Door");

		}else{
			alert("비밀번호가 맞지 않습니다.");
			inputPass.val("");
			inputPass.focus();
		}
	}else if(doorNum == 7){	// CMD
		var password = "";
		for(var i = 0; i < 5; i++){
			password += $('input[name=num]').eq(i).val();
		}
		if(password == "44644"){
			$('#c1it6_modal .close').click();
			$('.modal-backdrop').remove();
			
			$('#c1it6_answer_modal').modal();
			$('#c1it6_div').removeAttr('data-target');
			$('#c1it6_div').attr('data-target','#c1it6_answer_modal');
			
			sessionStorage.setItem("c1it6_div", "#c1it6_div");
			gameStatusChange("CMD문제", "#c1it6_div");

		}else{
			alert("비밀번호가 맞지 않습니다.");
			for(var i = 0; i < 5; i++){
				$('input[name=num]').eq(i).val("");
			}
			 $('input[name=num]').eq(0).focus();
		}
	}
}

function memoCheck(memoNo){
	var input = $('input[name="'+memoNo+'"]');
	if(memoNo == 'memo1'){
		
	}else if(memoNo == 'memo2'){
		if(input.val() == '4F'){
			alert("정답");
			
			$('#c2it2_modal .close').click();
			$('#c2it2_div').remove();
		
			sessionStorage.setItem("메모2", "#c2it2_div");
			gameStatusChange("메모2", "#c2it2_div");
			
			$('#c2it2_memo_div').css('display','block');
			$('#c2it2_memo_div').click();
			
		}else{
			alert("정답이 아닌것같다..");
			input.val("");
		}
	}else if(memoNo == 'memo3'){
		if(input.val() == '56'){
			alert("정답");
			
			$('#c5it1_modal .close').click();
			$('#c5it1_div').remove();
		
			sessionStorage.setItem("메모3", "#c5it1_div");
			gameStatusChange("메모3", "#c5it1_div");
			
			$('#c5it1_memo_div').css('display','block');
			$('#c5it1_memo_div').click();
			
		}else{
			alert("정답이 아닌것같다..");
			input.val("");
		}
	}else if(memoNo == 'memo4'){
		if(input.val() == 'I'){
			alert("정답");
			
			$('#c4it1_modal .close').click();
			$('#c4it1_div').remove();
		
			sessionStorage.setItem("메모4", "#c4it1_div");
			gameStatusChange("메모4", "#c4it1_div");
			
			$('#c4it1_memo_div').css('display','block');
			$('#c4it1_memo_div').click();
			
		}else{
			alert("정답이 아닌것같다..");
			input.val("");
		}
	}
}

// 게임 상태 변화를 모든 유저에게 적용
function gameStatusChange(key, value){
	$.ajax({
		url:"/CCNEE/game/status.do",
		type:'post',
		data:{key:key,value:value},
		success:function(data){},
		error:function(data){}
	});
}

///////////////////////////////////////////////////////////// 장면 이동
function moveLocation(num,before){
	if(num=='4'&&before=='3'){//복도2>복도3
		$('#backImg').attr('src','/CCNEE/resources/images/class2view'+before+'.jpg');
		$('#class5items .btn').css('display','block');
    	imgMove(num,5); 
	}
	else if(num=='4'&&before=='1'){// 복도2>복도1
		$('#backImg').attr('src','/CCNEE/resources/images/class2view'+before+'.jpg');
		$('#class6items #btn1, #class6items #btn2').css('display','none');
		if(sessionStorage.getItem("세미나실")){	// 회의실1 버튼
			$('#class6items #btn1').css('display','block');
		}
		if(sessionStorage.getItem("분임토의3실")){	// 회의실2 버튼
			$('#class6items #btn2').css('display','block');
		}
    	imgMove(num,6); 
	}
	else if(num=='5'&&before=='2'){// 최종출구 > 복도중간
		$('#backImg').attr('src','/CCNEE/resources/images/class2view'+before+'.jpg');
    	imgMove(num,4); 
	}
	else if(num=='6'&&before=='7'){// 회의실입구> 회의실1
		$('#backImg').attr('src','/CCNEE/resources/images/class3view1.jpg');
    	imgMove(num,before); 
	}
	else if(num=='6'&&before=='8'){// 회의실입구 > 회의실2
		$('#backImg').attr('src','/CCNEE/resources/images/class4view1.jpg');
    	imgMove(num,before); 
	}
	else if(num=='6'&&before=='2'){// 회의실입구 > 뒤로
		$('#backImg').attr('src','/CCNEE/resources/images/class2view'+before+'.jpg');
    	imgMove(num,4); 
	}
}
function imgMove(before,after){    	
	$('#class'+before+'items').css('display','none');
	$('#class'+after+'items').css('display','block');
}


//새로고침 막기, 개발자 도구, 전체화면 막기
function removeReload() {
    if(event.keyCode == 116 || event.keyCode == 122 ||event.keyCode == 123 || event.ctrlKey == true){	// f5, f11, f12, ctrl 
    	event.keyCode = 0;
    	event.cancelBubble = true;
    	event.returnValue = false;
    }
}