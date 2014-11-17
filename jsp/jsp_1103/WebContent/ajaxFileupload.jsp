<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style>
progress {
	display: none;
}
</style>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script>
	var progress = null;
	// �ּ� ��) $(document).ready(function(){}); ���� ����
	window.onload = function() {
		progress = document.querySelector("progress");
		// upload ��ư�� Ŭ���ϸ� ������ ���ε��Ѵ�.
		// $(button) - document.querySelector
		document.querySelector("button").onclick = function() {
			fileUpload();
		};
	};
	function fileUpload() {
		var fileIn = $('#fileToUpload')[0];
		var file = fileIn.files[0];
		$.ajax({
			url : "http://localhost:8080/jsp_1103/uploadService.jsp",
			type : "POST",
			data : file,
			success : function() {
				alert("success");
			},
			error : function() {
				alert("fail");
			}
		});
	}
	/* 
	function fileUpload() {
		var uploadFile = document.querySelector("input");
		var xhr = new XMLHttpRequest();
		// ���ε� ���� -> xhr.upload.onloadstart�� �ϸ� download
		xhr.upload.onloadstart = function(e) {
			progress.value = 0;
			// progress �� ��Ÿ���� none���� ���̵��� ����!
			progress.style.display = "inline";
		};
		// ���ε� ���߿� ��� �߻� -> xhr.upload.onprogress �ϸ� download
		xhr.upload.onprogress = function(e) {
			//e.total : ��ü ���ε� ũ��, e.loaded : ���� ���ε� �� ũ��
			progress.value = e.loaded / e.total;
			// console.log(progress.value);
		};

		// ���ε� ���� �� �߻� -> xhr.download.onload �ϸ� download
		xhr.upload.onload = function(e) {
			progress.style.display = "none";
		};

		xhr.onreadystatechange = function() {
			if (xhr.reaState == 4 && xhr.status == 200) {
				document.querySelector("div").innerHTML = xhr.responseText;
			}
		};

		// ũ�ν� ������ ....
		//$ajx������ ���
		xhr.open("POST", "http://localhost:8080/jsp_1103/uploadService.jsp",
				true);
		xhr.setRequestHeader("X-File-Name",
				encodeURIComponent(uploadFile.files[0].name));
		xhr.send(uploadFile.files[0]);
	 */


</script>
</head>
<body>
	<h1>XMLHttpRequest Level2�� �̿��� ���� ���ε�</h1>
	<input type="file" id='fileToUpload'>
	<button>upload</button>
	<progress>0%</progress>
	<div></div>
</body>
</html>