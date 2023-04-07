document.addEventListener('DOMContentLoaded', ()=> {
	const byId = (id) => document.getElementById(id)
	const paymentInfoSelect = byId('paymentInfoSelect')
	const paymentInput = byId('paymentInput')
	const accountNumberInput = byId('accountNumberInput')
	const accountNameInput = byId('accountNameInput')

	
	if(paymentInfoSelect && paymentInput && accountNumberInput && accountNameInput) {
		paymentInfoSelect.addEventListener('change', () => {
			let selectedOption = paymentInfoSelect.options[paymentInfoSelect.selectedIndex]
			paymentInput.value = selectedOption.getAttribute('data-payment')
			accountNumberInput.value = selectedOption.getAttribute('data-acc-num')
			accountNameInput.value = selectedOption.getAttribute('data-acc-name')
		})
	}
	
	const amountInput = byId('amountInput')
	const uploadBtn = byId('uploadBtn')
	
	if(amountInput && uploadBtn) {
		amountInput.addEventListener('change', () => {
			if(amountInput.value) {
				uploadBtn.removeAttribute('disabled')
			} else {
				uploadBtn.setAttribute('disabled', 'disabled')
			}
		})
	}
	
	const paidForm = byId('paidForm')
	const screenShootInput = byId('screenShootInput')
	
	if(uploadBtn && paidForm && screenShootInput) {
		uploadBtn.addEventListener('click', () => {
			screenShootInput.click()
		})
		
		screenShootInput.addEventListener('change', () => {
			paidForm.submit()
		})
	}
})