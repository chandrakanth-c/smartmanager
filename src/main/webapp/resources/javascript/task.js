const tabs=document.querySelectorAll('[data-tab-target]')
const tabContents=document.querySelectorAll('[data-tab-content]')

tabs.forEach(tab => {
	tab.addEventListener('click', () => {
		const target = document.querySelector(tab.dataset.tabTarget)
		tabContents.forEach(tabContent => {
			tabContent.classList.remove('active')
			})
		tab.classList.add('active')
		target.classList.add('active')
	})
})

function toggleDisable(){
	
	$(".tablebtn").attr('disabled',false)
	
	document.getElementById("desttaskname").value = document.getElementById("taskname");
	
}

function assignName(event){
	
	var value = event.srcElement.value
	document.getElementById("a").value = value;
	
}

function assignStartDate(event){
	
	var value = event.srcElement.value
	document.getElementById("b").value = value;
	
}

function assignEndDate(event){
	
	var value = event.srcElement.value
	document.getElementById("c").value = value;
	
}

function aTaskName(event){
	
	var value = event.srcElement.value
	document.getElementById("desttaskname").value = value;
	
}

function aProjectName(){
	
	var e = document.getElementById("projectsdrp")
	document.getElementById("desttaskproject").value = e.options[e.selectedIndex].value;
	
}

function aTaskStart(event){
	
	var value = event.srcElement.value
	document.getElementById("desttaskstart").value = value;
	
}

function aTaskEnd(event){
	
	var value = event.srcElement.value
	document.getElementById("desttaskend").value = value;
}


function aTaskAssigned(){
	
	var e = document.getElementById("memberdrp")
	document.getElementById("desttaskassigned").value = e.options[e.selectedIndex].value;
}

function aTaskStatus(event){
	
	var value = event.srcElement.value
	document.getElementById("desttaskstatus").value = value;
}

function aTaskRemaining(event){
	
	var value = event.srcElement.value
	document.getElementById("desttaskremaining").value = value;
}

function aTaskComments(event){
	
	var value = event.srcElement.value
	document.getElementById("desttaskcomments").value = value;
}