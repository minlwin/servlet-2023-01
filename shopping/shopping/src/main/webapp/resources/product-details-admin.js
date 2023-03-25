document.addEventListener('DOMContentLoaded', () => {
	
	const uploadForm = document.getElementById('uploadForm')
	const uploadInput = document.getElementById('uploadInput')
	const uploadBtn = document.getElementById('uploadBtn')
	
	if(uploadForm && uploadInput && uploadBtn) {
		uploadBtn.addEventListener('click', () => uploadInput.click())
		uploadInput.addEventListener('change', () => uploadForm.submit())		
	} 
	
	const controls = Array.from(document.getElementsByClassName('image-control'))
	const coverImage = document.getElementById('productCoverImage')
	
	if(controls && coverImage) {
		controls.forEach(control => {
			control.addEventListener('click', event => {
				coverImage.src = event.target.src
			});
		})		
	}
	
})