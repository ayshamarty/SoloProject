const routineURL = "http://localhost:8080/Yoga/api/routine/"
//"http://35.195.95.55:8888/Yoga/api/routine/";



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

function makeCard(routine) {
    let myCard = document.createElement("div");
    myCard.innerHTML = `<div class="card" style="width: 18rem;">
        <div class="card-body">
            <h5 class="card-title">${routine.routineName} Routine</h5>
            <p class="card-text">Type: ${routine.routineType} </p>
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
    let aRoutineID = document.createElement('td');
    aRoutineID.innerHTML = newEntry.routineID;
    let aRoutineName = document.createElement('td');
    aRoutineName.innerHTML = newEntry.routineName;
    let aRoutineType = document.createElement('td');
    aRoutineType.innerHTML = newEntry.routineType;
    let deleteButton = document.createElement('td');
    deleteButton.innerHTML = `<button type="button" class="btn btn-secondary" onclick='destroy(${newEntry.routineID})' > Delete</button >`;
    let readOneButton = document.createElement('td');
    readOneButton.innerHTML = `<button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo" onclick='showRoutine(${newEntry.routineID})' > More Details </button >`;

    aRow.appendChild(aRoutineID);
    aRow.appendChild(aRoutineName);
    aRow.appendChild(aRoutineType);
    aRow.appendChild(deleteButton);
    aRow.appendChild(readOneButton);
}

function addModalTable(poseToAdd, poseRow) {
    let aNewID = document.createElement('td');
    aNewID.innerHTML = poseToAdd.poseSet.poseID;
    let aNewName = document.createElement('td');
    aNewName.innerHTML = poseToAdd.poseSet.poseName
    let aNewDifficulty = document.createElement('td');
    aNewDifficulty.innerHTML = poseToAdd.poseSet.poseDifficulty
    console.log(poseToAdd)

    poseRow.appendChild(aNewID);
    poseRow.appendChild(aNewName);
    poseRow.appendChild(aNewDifficulty);
}

//read

const readAll = () => {
    // removes any existing tables
    const tableContainer = document.getElementById('mainTable');
    if (tableContainer.rows.length > 1) {
        let tableSize = tableContainer.rows.length;
        for (i = tableSize; i > 1; i--) {
            tableContainer.deleteRow(i - 1);
        }
    }
    makeRequest("GET", `${routineURL}getAllRoutines`)
        .then((req) => {
            let data = JSON.parse(req.responseText);
            console.table(data);
            console.table(data[0].routineName);

            const tableContainer = document.getElementById('mainTable');

            // creating table rows and adding data into the rows
            for (let i = 0; i < data.length; i++) {
                let aRow = document.createElement('tr')
                tableContainer.appendChild(aRow);
                addToTable(data[i], aRow);
            }
            console.table(req.responseText)
        }).catch((error) => { console.log(error.message) });

}


function showRoutine(id) {
    makeRequest("GET", `${routineURL}getARoutine/${id}`).then((req) => {
        let routine = JSON.parse(req.responseText);
        console.table(routine);
        let poses = routine.poseSet;
        console.table(poses);
        let ourTitle = document.getElementById('modalTitle');
        ourTitle.innerText = `${routine.routineName} Routine`;
        const ourTable = document.getElementById('routineTable');

        for (let i = 0; i < poses.length; i++) {
            let poseRow = document.createElement('tr')
            ourTable.appendChild(poseRow);
            addModalTable(poses[i], poseRow);
        }

    }).catch(() => {
        readNotification.innerText = "Invalid ID";
    });
}

// makeRequest("GET", `${poseURL}getAPose/${id}`).then((req) => {
//     let pose = JSON.parse(req.responseText);
//     console.log(req.responseText);
//     let logo = document.getElementById('poseIMG')
//     logo.src = `images/${pose.poseIMG}`;
//     let changeTitle = document.getElementById('modalTitle');
//     changeTitle.innerText = `${pose.poseName} Pose`;
//     let changeBody = document.getElementById('cardTitle');
//     changeBody.innerText = `Difficulty: ${pose.poseDifficulty}`;
//     let changeInfo = document.getElementById('cardText');
//     changeInfo.innerText = `${pose.poseInfo}`;
// }).catch(() => {
//     readNotification.innerText = "Invalid ID";
// });
// }

//delete
function destroy(id) {
    makeRequest("DELETE", `${routineURL}deleteRoutine/${id}`).then(() => {
        readAll();
    });
}

//create


function routineMaker(rName, rType) {
    const routine = {
        routineName: rName.value,
        routineType: rType.value,
    };
    return routine;
}

function create() {
    let routine = routineMaker(createRoutineName, createRoutineType);
    makeRequest("POST", `${routineURL}createRoutine`, JSON.stringify(routine)).then(() => {
        readAll();
    }).catch((error) => { console.log(error.message) }).then(readAll());
}

function update() {

    let routineToUpdate = routineMaker(updateRoutineName, updateRoutineType);
    let id = routineIDToChange.value

    makeRequest("PUT", `${routineURL}updateRoutine/${id}`, JSON.stringify(routineToUpdate)).then(response => {
        console.log(response);
        readAll();
    }).catch((error) => { console.log(error.message) });
}

readAll();