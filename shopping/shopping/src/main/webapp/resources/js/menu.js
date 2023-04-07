document.addEventListener('DOMContentLoaded', () => {
	
	let signInMenus = Array.from(document.getElementsByClassName('signInMenu'))
	signInMenus.forEach(menu => {
		menu.addEventListener('click', () => {
			let dialog = new bootstrap.Modal('#signInForm')
			dialog.show()
		})
	})
		
	let signUpMenus = Array.from(document.getElementsByClassName('signUpMenu'))
	signUpMenus.forEach(menu => {
		menu.addEventListener('click', () => {
			let dialog = new bootstrap.Modal('#signUpForm')
			dialog.show()
		})
	})
	
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