const routineURL = "http://localhost:8080/Yoga/api/routine/"; //"http://35.204.89.171:8888/Yoga/api/routine/"




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

// function makeCard(routine) {
//     let myCard = document.createElement("div");
//     myCard.innerHTML = `<div class="card" style="width: 18rem;">
//         <div class="card-body">
//             <h5 class="card-title">${routine.routineName} Routine</h5>
//             <p class="card-text">Type: ${routine.routineType} </p>
//         </div>
//      </div>`

//     document.getElementById("readNotification").appendChild(myCard);

// }

function makeModal(routine) {
    let myModal = document.createElement("div");
    myModal.innerHTML = `<div class="modal fade" id="routineModal" tabindex="-1" role="dialog" aria-labelledby="routineModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">${routine.routineName}</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          Type: ${routine.routineType}
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>`

    document.getElementById("exampleModal").appendChild(myModal);

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
    deleteButton.innerHTML = `<button type="button" class="btn btn-secondary" onclick ='destroy(${newEntry.routineID})' > Delete</button >`;
    let readOneButton = document.createElement('td');
    readOneButton.innerHTML = `<button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#exampleModal" onclick ='readOne(${newEntry.routineID})' > More Details </button >`;


    aRow.appendChild(aRoutineID);
    aRow.appendChild(aRoutineName);
    aRow.appendChild(aRoutineType);
    aRow.appendChild(deleteButton);
    aRow.appendChild(readOneButton);
}

//read

const readAll = () => {
    // removes any existing tables
    const tableContainer = document.getElementById('table');
    if (tableContainer.rows.length > 1) {
        let tableSize = tableContainer.rows.length;
        for (let i = tableSize; i > 1; i--) {
            tableContainer.deleteRow(i - 1);
        }
    }
    makeRequest("GET", `${routineURL}getAllRoutines`)
        .then((req) => {
            let data = JSON.parse(req.responseText);
            console.table(data);
            console.table(data[0].routineName);

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
    makeRequest("GET", `${routineURL}getARoutine/${id}`).then((req) => {

        if (req.responseText && req.responseText !== "null") {
            removeAllChildren("readNotification");
            let aRoutine = JSON.parse(req.responseText);
            makeCard(aRoutine)
        } else {
            readNotification.innerText = "Routine doesn't exist"
        }
    }).catch(() => {
        readNotification.innerText = "Invalid ID";
    });
}

//delete
function destroy(id) {
    makeRequest("DELETE", `${routineURL}deleteRoutine/${id}`).then(() => {
        readAll();
    });
}

//create


function routineMaker(pName, pType) {
    const routine = {
        routineName: pName.value,
        routineType: pType.value,
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