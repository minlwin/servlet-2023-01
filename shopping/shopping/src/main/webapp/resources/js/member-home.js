document.addEventListener('DOMContentLoaded', () => {
	
	const profileEditBtn = document.getElementById('profileEditBtn')
	const profileEditDialog = document.getElementById('profileEditDialog')
	
	if(profileEditBtn && profileEditDialog) {
		profileEditBtn.addEventListener('click', () => {
			const dialog = new bootstrap.Modal(profileEditDialog)
			dialog.show()
		})
	} 
})