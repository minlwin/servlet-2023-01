document.addEventListener('DOMContentLoaded', () => {
	
	const byId = id => document.getElementById(id)
	
	const attachModal = (btnId, dialogId) => {
		let btn = byId(btnId)
		let dialog = byId(dialogId)
		
		if(btn, dialog) {
			let modal = new bootstrap.Modal(dialog)
			
			btn.addEventListener('click', () => modal.show())
		}
	}
	
	attachModal('cancelBtn', 'cancelDialog')
	attachModal('finishBtn', 'finishDialog')
})