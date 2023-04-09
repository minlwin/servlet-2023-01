document.addEventListener('DOMContentLoaded', () => {
	
	const profileEditBtn = document.getElementById('profileEditBtn')
	const profileEditDialog = document.getElementById('profileEditDialog')
	
	if(profileEditBtn && profileEditDialog) {
		profileEditBtn.addEventListener('click', () => {
			const dialog = new bootstrap.Modal(profileEditDialog)
			dialog.show()
		})
	} 
	
	const addressEditLink = Array.from(document.getElementsByClassName('addressEditLink'))
	const addressEditDialog = document.getElementById('addressEditDialog')
	
	if(addressEditLink && addressEditDialog) {
		addressEditLink.forEach(link => link.addEventListener('click', () => {
			document.getElementById('idInput').value = link.dataset.id
			document.getElementById('nameInput').value = link.dataset.name
			document.getElementById('phoneInput').value = link.dataset.phone
			document.getElementById('buildingInput').value = link.dataset.building
			document.getElementById('streetInput').value = link.dataset.street
			const dialog = new bootstrap.Modal(addressEditDialog)
			dialog.show()
		}))
	}
})