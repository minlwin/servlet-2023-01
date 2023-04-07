document.addEventListener('DOMContentLoaded', ()=> {
	const byId = (id) => document.getElementById(id)
	
	const paymentScreenShootDialog = byId('paymentScreenShootDialog')
	const paidImageLink = document.getElementsByClassName('paidImageLink')
	const targetScreenShoot = byId('targetScreenShoot')
	
	if(paymentScreenShootDialog && paidImageLink && targetScreenShoot) {
		
		const dialog = new bootstrap.Modal(paymentScreenShootDialog)
		
		Array.from(paidImageLink).forEach(link => link.addEventListener('click', () => {
			targetScreenShoot.src = link.getAttribute('data-image-link')
			dialog.show()
		}))
	}
})