

//Table tabs start
let tabs = document.querySelectorAll('.tabs .tab');
let tabContents = document.querySelectorAll('.tab-content');

tabs.forEach((tab, index, arr) => {
	tab.addEventListener('click', () => {
		arr.forEach(item => {item.classList.remove('active')});
		tab.classList.add('active');
		Array.from(tabContents).forEach(item => item.classList.remove('active'));
		tabContents[index].classList.add('active');
	})
});
//table tabs end