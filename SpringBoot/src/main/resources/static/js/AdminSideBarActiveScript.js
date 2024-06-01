function tagActiveAndLink(event){
    const id = event.target.id;
    const className = event.target.className;


    if (event.target.tagName != 'A')
    return;


    if (className.includes("active")){
        event.target.classList.remove("active");
        return;
    }

    event.target.classList.add("active");
}

function tagActive(event){

    const parentElement = event.target.parentElement;

    // event.target.parentElement.className += " active";

    if (parentElement.className.includes("active")){
        parentElement.classList.remove("active");
        return;
    }


    parentElement.classList.add("active");
}