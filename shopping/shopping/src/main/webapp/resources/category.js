document.addEventListener('DOMContentLoaded', () => {
	
	let addNewBtn = document.getElementById('addNewCategoryBtn')
	let dialog = new bootstrap.Modal('#categoryEditDialog')
	
	if(dialog) {
		if(addNewBtn) {
			addNewBtn.addEventListener('click', () => {
				dialog.show()
			})
		}
		
		let editButtons = Array.from(document.getElementsByClassName('categoryEditBtn'))
		
		editButtons.forEach(btn => btn.addEventListener('click', () => {
			document.getElementById('editCategoryId').value = btn.getAttribute('data-id')
			document.getElementById('editCategoryName').value = btn.getAttribute('data-name')
			dialog.show()
		}))
		
	}	
	
})