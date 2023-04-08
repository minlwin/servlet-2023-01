document.addEventListener('DOMContentLoaded', () => {
	const byId = (id) => document.getElementById(id)
	
	const select = byId('deliveryProviderSelect')
	
	select.addEventListener('change', () => {
		const option = select.options[select.selectedIndex]
		byId('deliveryPhone').innerText = option.dataset.phone
		byId('deliveryEmail').innerText = option.dataset.email
	})
})