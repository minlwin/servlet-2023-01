document.addEventListener('DOMContentLoaded', () => {
	
	const dialog = new bootstrap.Modal('#paidInfoEditDialog')
	
	if(dialog) {
		document.getElementById('paidInfoAddNewBtn').addEventListener('click', () => {
			dialog.show()
		})
		
		Array.from(document.getElementsByClassName('paidEditLink')).forEach(btn => {
			btn.addEventListener('click', () => {
				const fetchLink = btn.getAttribute('data-link')
				fetch(fetchLink)
					.then(resp => resp.json())
					.then(data => {
						document.getElementById('idInput').value = data.id
						document.getElementById('paymentTypeSelect').value = data.paymentType
						document.getElementById('paymentNameInput').value = data.paymentName
						document.getElementById('accountNumberInput').value = data.accountNumber
						document.getElementById('accountNameInput').value = data.accountName
						
						dialog.show()
						
					})
					.catch(err => console.log(err))
			})
		})
	}
})