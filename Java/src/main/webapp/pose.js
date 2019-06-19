const poseURL = "http://35.204.89.171:8888/Yoga/api/pose/"

//"http://localhost:8080/Yoga/api/pose/"




function makeRequest(requestType, url, whatToSend) {
    return new Promise((resolve, reject) => {
        let req = new XMLHttpRequest();
        req.onload = () => {
            if (req.status === 200) {
                resolve(req);
            } else {
                const reason = new Error("Rejected");
                reject(reason);
            }
        };
        req.open(requestType, url);
        req.send(whatToSend);
    });
}

function makeCard(pose) {
    let myCard = document.createElement("div");
    myCard.innerHTML = `<div class="card" style="width: 18rem;">
        <div class="card-body">
            <h5 class="card-title">${pose.poseName} Pose</h5>
            <p class="card-text">Difficulty: ${pose.poseDifficulty} </p>
        </div>
     </div>`

    document.getElementById("readNotification").appendChild(myCard);

}

function removeAllChildren(id) {
    let result = document.getElementById(id);
    while (result.hasChildNodes()) {
        result.removeChild(result.firstChild);
    }

}

function addToTable(newEntry, aRow) {
    let aPoseID = document.createElement('td');
    aPoseID.innerHTML = newEntry.poseID;
    let aPoseName = document.createElement('td');
    aPoseName.innerHTML = newEntry.poseName;
    let aPoseDifficulty = document.createElement('td');
    aPoseDifficulty.innerHTML = newEntry.poseDifficulty;
    let deleteButton = document.createElement('td');
    deleteButton.innerHTML = `<button type="button" class="btn btn-secondary" onclick ='destroy(${newEntry.poseID})' > Delete</button >`;


    aRow.appendChild(aPoseID);
    aRow.appendChild(aPoseName);
    aRow.appendChild(aPoseDifficulty);
    aRow.appendChild(deleteButton);
}

//read

const readAll = () => {
    // removes any existing tables
    const tableContainer = document.getElementById('table');
    if (tableContainer.rows.length > 1) {
        let tableSize = tableContainer.rows.length;
        for (i = tableSize; i > 1; i--) {
            tableContainer.deleteRow(i - 1);
        }
    }
    console.log(`${poseURL}getAllPoses`)
    makeRequest("GET", `${poseURL}getAllPoses`)
        .then((req) => {
            let data = JSON.parse(req.responseText);
            console.table(data);
            console.log(data[0].poseName);

            const tableContainer = document.getElementById('table');
            tableContainer.className = "table table-hover";

            // creating table rows and adding data into the rows
            for (let i = 0; i < data.length; i++) {
                let aRow = document.createElement('tr')
                tableContainer.appendChild(aRow);
                addToTable(data[i], aRow);
            }
            console.table(req.responseText)
        }).catch((error) => { console.log(error.message) });

}


function readOne(id) {
    makeRequest("GET", `${poseURL}${id}`).then((req) => {

        if (req.responseText && req.responseText !== "null") {
            removeAllChildren("readNotification");
            let aPose = JSON.parse(req.responseText);
            makeCard(aPose)
        } else {
            readNotification.innerText = "Pose doesn't exist"
        }
    }).catch(() => {
        readNotification.innerText = "Invalid ID";
    });
}

//delete
function destroy(id) {
    makeRequest("DELETE", `${poseUR}L${id}`).then(() => {
        readAll();
    });
}

//create


function poseMaker(pName, pDifficulty) {
    const pose = {
        poseName: pName.value,
        poseDifficulty: pDifficulty.value,
    };
    return pose;
}

function create() {
    let pose = poseMaker(createPoseName, createPoseDifficulty);
    makeRequest("POST", `${poseURL}createPose`, JSON.stringify(pose)).then(() => {
        readAll();
    }).catch((error) => { console.log(error.message) }).then(readAll());
}

function update() {

    let poseToUpdate = poseMaker(updatePoseName, updatePoseDifficulty);
    let id = poseIDToChange.value

    makeRequest("PUT", `${poseURL}updatePose/${id}`, JSON.stringify(poseToUpdate)).then(response => {
        console.log(response);
        readAll();
    }).catch((error) => { console.log(error.message) });
}



