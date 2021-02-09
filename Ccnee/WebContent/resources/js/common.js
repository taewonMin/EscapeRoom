function changePage(url){
	location.href = "/CCNEE" + url;
}

function getCookie(name) {
	var x, y;
	var val = document.cookie.split(';');
	
	for (var i = 0; i < val.length; i++) {
	x = val[i].substr(0, val[i].indexOf('='));
	y = val[i].substr(val[i].indexOf('=') + 1);
	x = x.replace(/^\s+|\s+$/g, ''); // 앞과 뒤의 공백 제거하기
		if (x == name) {
		  return unescape(y); // unescape로 디코딩 후 값 리턴
		}
	}
};

//page parameter & cookie initialize
function initPageParam(){
	document.cookie="pageNo="+1+";path=/CCNEE";
}

//handlebars printElement (args : data Array, append target, handlebar template, remove class)
function printData(dataArr, target, templateObject, removeClass){
	
	var template = Handlebars.compile(templateObject.html());
	
	var html = template(dataArr);
	
	$(removeClass).remove();
	target.append(html);
}

Handlebars.registerHelper({
	"prettifyDate" : prettifyDate
});

//java.util.Date().getTimes()를 변환.
function prettifyDate(timeValue){
	var dateObj = new Date(timeValue);
	var year = dateObj.getFullYear();
	var month = dateDecimalFormat(dateObj.getMonth()+1);
	var date = dateDecimalFormat(dateObj.getDate());
	return year+"."+month+"."+date;
}
function dateDecimalFormat(number){
	number+="";
	return number > 2 ? number : "0"+number;
}

//pagination 
function printPaging(searchVO,target){
	var str="";
	if(searchVO.totalCount > 0){
		if(searchVO.firstPageNo > searchVO.pageSize){ // [이전] 출력
			str += "<li onclick='goPage(&quot;list.do?pageNo="+(searchVO.firstPageNo - searchVO.pageSize);
			if(searchVO.search){
				str += "&search="+searchVO.search;
			}
			str += "&quot;);'>&lt;</li>";
		}
		for(var pNo = searchVO.firstPageNo; pNo <= searchVO.lastPageNo; pNo++){ // 이전 ~ 다음 사이 출력
			str += "<li id='listEl"+pNo+"' onclick='goPage(&quot;list.do?pageNo="+pNo;
			if(searchVO.search){
				str += "&search="+searchVO.search;
			}
			str += "&quot;);'>"+pNo+"</li>";
		}
		if(searchVO.lastPageNo < searchVO.totalPageCount){ // [다음] 출력
			str += "<li onclick='goPage(&quot;list.do?pageNo="+(searchVO.firstPageNo + searchVO.pageSize);
			if(searchVO.search){
				str += "&search="+searchVO.search;
			}
			str += "&quot;);'>&gt;</li>";
		}
	}
	
	target.html(str);
}





///////////////////////////////////////////////////////////////////////////test
function summernote_start(content){

	content.summernote({
		placeholder:'신고 사유를 적어주세요.',
		height:500,
		disableResizeEditor: true,
		toolbar: [
             // [groupName, [list of button]]
             ['Font Style', ['fontname']],
             ['style', ['bold', 'italic', 'underline']],
             ['font', ['strikethrough']],
             ['fontsize', ['fontsize']],
             ['color', ['color']],
             ['para', ['paragraph']],
             ['height', ['height']],
             ['Insert', ['picture']],
             ['Insert', ['link']],
             ['Misc', ['fullscreen']]
        ],
		callbacks:{
			onImageUpload : function(files, editor, welEditable) {
				for (var i = files.length - 1; i >= 0; i--) {
					if(files[i].size > 1024*1024*5){
	            		alert("이미지는 5MB 미만입니다.");
	            		return;
					}
				}
				
				//file sending
				for (var i = files.length - 1; i >= 0; i--) {
	            	sendImg(files[i], this,'uploadImg.do');
				}	
			},
			onMediaDelete : function(target) {
				var answer=confirm("정말 이미지를 삭제하시겠습니까?");
				if(answer){
					deleteImg(target[0].src,'deleteImg.do');
				}
			}
		}
	});
}

//summernote img upload
function sendImg(file, el,uploadURL) {
	
	var form_data = new FormData();
	form_data.append("file", file); 
	$.ajax({
	url: "/CCNEE/views/game/"+uploadURL,
  	data: form_data,
  	type: "post",
  	processData: false,
  	contentType: false,	    	
  	success: function(img_url) {
    		$(el).summernote('editor.insertImage', img_url);
  	}

	});
}

//summernote img delete
function deleteImg(src,deleteURL) {
	
	var splitSrc= src.split("=");
	var fileName = splitSrc[splitSrc.length-1];
  
	//alert(fileName);
	var fileData = {
			fileName:fileName
	}
	
	$.ajax({
		url:deleteURL,
		data : JSON.stringify(fileData),
		type:"post",
		success:function(res){
			console.log(res);
		}
	});
}
