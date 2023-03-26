document.addEventListener('DOMContentLoaded', () => {
	
	Array.from(document.getElementsByClassName('delete-feature')).forEach(btn => {
		btn.addEventListener('click', () => {
			const row = btn.parentElement.parentElement
			const features = row.parentElement
			
			features.removeChild(row)
		})
	})
	
	const addFeatrueBtn = document.getElementById('addFeatrueBtn')
	
	if(addFeatrueBtn) {
		addFeatrueBtn.addEventListener('click', () => {
			const featureInputs = document.getElementById('featureInputs')
			
			const row = document.createElement('div')
			row.setAttribute('class', 'row mt-2')
			
			const nameCol = document.createElement('div')
			nameCol.setAttribute('class', 'col-lg-4 col-md-2')
			let nameInput = document.createElement('input')
			nameInput.setAttribute('class', 'form-control')
			nameInput.setAttribute('placeholder', 'Enter Feature Name')
			nameInput.setAttribute('name', 'featureName')
			nameCol.appendChild(nameInput)			
			
			const valueCol = document.createElement('div')
			valueCol.setAttribute('class', 'col')
			
			let valueInput = document.createElement('input')
			valueInput.setAttribute('class', 'form-control')
			valueInput.setAttribute('placeholder', 'Enter Feature Name')
			valueInput.setAttribute('name', 'featureValue')
			valueCol.appendChild(valueInput)	
			
			const deleteCol = document.createElement('div')
			deleteCol.setAttribute('class', 'col-auto')
			
			let deleteBtn = document.createElement('button')
			deleteBtn.setAttribute('class', 'btn btn-outline-danger delete-feature')
			deleteBtn.setAttribute('type', 'button')
			
			let deleteIcon = document.createElement('i')
			deleteIcon.setAttribute('class', 'bi bi-trash')
			deleteBtn.appendChild(deleteIcon)
			deleteCol.appendChild(deleteBtn)	
			
			deleteBtn.addEventListener('click', () => {
				featureInputs.removeChild(row)
			})
			
			row.append(nameCol, valueCol, deleteCol)
			
			featureInputs.appendChild(row)
		})
	}
})