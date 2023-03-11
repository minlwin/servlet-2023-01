document.addEventListener('DOMContentLoaded', () => {
	
	let uploadBtn = document.getElementById('uploadBtn')
	let fileInput = document.getElementById('uploadInput')
	let uploadForm = document.getElementById('uploadForm')
	
	uploadBtn.addEventListener('click', () => {
		fileInput.click()
	})
	
	fileInput.addEventListener('change', () => {
		uploadForm.submit()
	})
})