document.addEventListener('DOMContentLoaded', () => {
	
	let byId = (id) => document.getElementById(id)
	
	const addressSelect = byId('addressSelect')
	if(addressSelect) {
		addressSelect.addEventListener('change', () => {
			
			let selected = addressSelect.options[addressSelect.selectedIndex]
			
			if(selected.dataset.addressName) {
				byId('nameInput').value = selected.dataset.addressName
				byId('phoneInput').value = selected.dataset.addressPhone
				byId('buildingInput').value = selected.dataset.addressBuilding
				byId('streetInput').value = selected.dataset.addressStreet
				
				byId('nameInput').readOnly = true 
				byId('phoneInput').readOnly = true
				byId('buildingInput').readOnly = true
				byId('streetInput').readOnly = true
			} else {
				byId('nameInput').value = ''
				byId('phoneInput').value = ''
				byId('buildingInput').value = ''
				byId('streetInput').value = ''
				
				byId('nameInput').readOnly = false 
				byId('phoneInput').readOnly = false
				byId('buildingInput').readOnly = false
				byId('streetInput').readOnly = false
			}
		})
	}
})