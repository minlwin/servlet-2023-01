document.addEventListener('DOMContentLoaded', () => {
	
	const signInMenus = Array.from(document.getElementsByClassName('signInMenu'))
	signInMenus.forEach(menu => {
		menu.addEventListener('click', () => {
			const dialog = new bootstrap.Modal('#signInForm')
			dialog.show()
		})
	})
		
	const signUpMenus = Array.from(document.getElementsByClassName('signUpMenu'))
	signUpMenus.forEach(menu => {
		menu.addEventListener('click', () => {
			const dialog = new bootstrap.Modal('#signUpForm')
			dialog.show()
		})
	})
	
	const signOutMenu = document.getElementById('signOutMenu')
	
	if(signOutMenu) {
		
		signOutMenu.addEventListener('click', () => {
			const signOutForm = document.getElementById('signOutForm')
			if(signOutForm) {
				signOutForm.submit()
			}			
		})
	}
	
	const changePassMenu = document.getElementById('changePassMenu')
	const changePassDialog = document.getElementById('changePassDialog')
	
	if(changePassMenu && changePassDialog) {
		changePassMenu.addEventListener('click', () => {
			const dialog = new bootstrap.Modal(changePassDialog)
			dialog.show()
		})
	}
	
	const errorDialog = document.getElementById('errorDialog')
	
	if(errorDialog) {
		const dialog = new bootstrap.Modal(errorDialog)
		dialog.show()
	}
})