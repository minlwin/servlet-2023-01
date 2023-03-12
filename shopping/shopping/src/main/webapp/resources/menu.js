document.addEventListener('DOMContentLoaded', () => {
	let signInMenu = document.getElementById('signInMenu')
	
	if(signInMenu) {
		signInMenu.addEventListener('click', () => {
			let dialog = new bootstrap.Modal('#signInForm')
			dialog.show()
		})
	}

	let signUpMenu = document.getElementById('signUpMenu')
	
	if(signUpMenu) {
		signUpMenu.addEventListener('click', () => {
			let dialog = new bootstrap.Modal('#signUpForm')
			dialog.show()
		})
	}
	
	let signOutMenu = document.getElementById('signOutMenu')
	
	if(signOutMenu) {
		
		signOutMenu.addEventListener('click', () => {
			let signOutForm = document.getElementById('signOutForm')
			if(signOutForm) {
				signOutForm.submit()
			}			
		})
	}
})