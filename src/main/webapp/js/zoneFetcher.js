

const country = document.getElementById("country");
const state = document.getElementById("state");
const city = document.getElementById("city");



function populateSelect(element,data) {
    let e = document.createElement("option");
    e.appendChild(document.createTextNode('Seleccione...'));
    e.value = '';
    element.appendChild(e);

    data.forEach((dat) => {
        let child = document.createElement("option");
        child.appendChild(document.createTextNode(dat.name));
        child.value = dat.id;
        element.appendChild(child);
    });
}

function removeAllSelectChildren(element) {
    let elem = element;
    var child = elem.lastElementChild;
    while(child) {
        elem.removeChild(child);
        child = elem.lastElementChild;
    }
}

async function fetchStates(countryName) {


    (async () => {
        const rawResponse = await fetch(`https://django-acacia.herokuapp.com/api/state/search/?country__name=${countryName}`, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            credentials: 'include'
        });
        const content = await rawResponse.json();

        removeAllSelectChildren(state);
        populateSelect(state, content);
    })();



}

function fetchCities(stateName) {
    (async () => {
        const rawResponse = await fetch(`https://django-acacia.herokuapp.com/api/city/search/?state__name=${stateName}`, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            credentials: 'include'
        });
        const content = await rawResponse.json();

        removeAllSelectChildren(city);
        populateSelect(city, content);
    })();
}


function getCountryNameSelected() {

    if(country.value){
        let countryName = country.options[country.selectedIndex].text;
        fetchStates(countryName);
        state.disabled = false;
    }else {
        let cont = [];
        removeAllSelectChildren(state);
        populateSelect(state, cont);
        removeAllSelectChildren(city);
        populateSelect(city, cont);
        state.disabled = true;
        city.disabled = true;
    }

}

function getStateNameSelected() {
    if(state.value) {
        let stateName = state.options[state.selectedIndex].text;
        console.log(stateName);
        fetchCities(stateName);
        city.disabled = false;
    }else {

        let cont = [];
        removeAllSelectChildren(city);
        populateSelect(city, cont);
        city.disabled = true;
    }
}

country.addEventListener("change", () => {
   getCountryNameSelected();

});

state.addEventListener("change", () => {
    getStateNameSelected();

});




