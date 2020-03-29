

const country = document.getElementById("country");
const state = document.getElementById("state");



function populateSelect(element,data) {
    data.forEach((dat) => {
        let child = document.createElement("option");
        child.appendChild(document.createTextNode(dat.name));
        child.value = dat.id;
        element.appendChild(child);
    });
}

function removeAllSelectChildren(element) {
    let elem = element;
    var child = elem.lastElementChild(elem);
    while(child) {
        elem.removeChild(child);
        child = elem.lastElementChild;
    }
}

async function fetchStates(countryName) {

    let response = await axios.get('https://django-acacia.herokuapp.com/api/state/search/?country__name=' + countryName,{
        headers : {
            Cookie : 'token-access=' + Cookies.get('token-access') + '; token-refresh=' + Cookies.get('token-refresh')
        }
    });

    let data = response.data;
    removeAllSelectChildren(state);
    populateSelect(state, data);

}


function getCountryNameSelected() {

    if(country.value){
        let countryName = country.options[country.selectedIndex].text;
        fetchStates(countryName);
    }

}

country.addEventListener("change", () => {
   //getCountryNameSelected();
    //console.log(Cookies.get("token-access"));
    (async () => {
        const rawResponse = await fetch('https://django-acacia.herokuapp.com/api/state/search/?country__name=Nicaragua', {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            credentials: 'include'
        });
        const content = await rawResponse.json();

        console.log(content);
    })();
});

