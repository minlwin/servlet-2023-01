document.addEventListener('DOMContentLoaded', () => {
	
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
	
})