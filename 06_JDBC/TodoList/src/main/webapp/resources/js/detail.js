const compelteBtn = document.querySelector(".complete-btn");

compelteBtn.addEventListener("click", e => {

    const todoNo = e.target.dataset.todoNo;
    console.log(todoNo)

    let complete = e.target.innerText;

    complete = complete === 'Y' ? 'N' : 'Y';

    location.href = "/department/changeComplete?"
});