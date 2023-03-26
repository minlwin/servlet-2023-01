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
	const setCoverInput = document.getElementById('setCoverInput')
	
	if(controls && coverImage) {
		controls.forEach(control => {
			control.addEventListener('click', () => {
				coverImage.src = control.getAttribute('data-product-url')
				setCoverInput.value = control.getAttribute('data-product-photo')
			});
		})		
	}
	
	const soldOutForm = document.getElementById('soldOutForm')
	const soldOutBtn = document.getElementById('soldOutBtn')
	
	if(soldOutBtn && soldOutForm) {
		soldOutBtn.addEventListener('click', () => soldOutForm.submit())
	}
	
	const setCoverBtn = document.getElementById('setCoverBtn')
	const setCoverForm = document.getElementById('setCoverForm')
	
	if(setCoverBtn && setCoverForm) {
		setCoverBtn.addEventListener('click', () => setCoverForm.submit())
	}
	
	const deleteImageBtn = document.getElementById('deleteImageBtn')
	const deleteImageInput = document.getElementById('deleteImageInput')
	
	if(deleteImageBtn && deleteImageInput && setCoverForm) {
		deleteImageBtn.addEventListener('click', () => {
			deleteImageInput.value = 'true'
			setCoverForm.submit()
		})
	}
	
})