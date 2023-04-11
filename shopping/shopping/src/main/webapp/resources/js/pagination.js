document.addEventListener('DOMContentLoaded', () => {
	const pagination = document.getElementById('pagination')
	const formId = pagination.dataset.searchForm
	
	const searchForm = document.getElementById(formId)
	const pageInput = document.getElementById(`${formId}PageInput`)
	const sizeInput = document.getElementById(`${formId}SizeInput`)
	
	const pageSizeSelect = document.getElementById('pageSizeSelect')
	const pageLinks = Array.from(document.getElementsByClassName('page-link'))
	
	if(searchForm && pageInput && sizeInput && pageSizeSelect && pageLinks) {
		
		pageSizeSelect.addEventListener('change', () => {
			sizeInput.value = pageSizeSelect.value 
			pageInput.value = '1'
			
			searchForm.submit()
		})
		
		pageLinks.forEach(link => link.addEventListener('click', () => {
			pageInput.value = link.dataset.pageNum
			searchForm.submit()
		}))
	}

})