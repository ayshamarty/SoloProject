function makeRequest(requestType, url, whatToSend) {
    return new Promise((resolve, reject) => {
        let req = new XMLHttpRequest();
        req.onload = () => {
            if (req.status >= 200 && req.status <= 299) {
                resolve(req);
            } else {
                reject(console.log("request failed"));
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

//read
function readAll() {
    makeRequest("GET", "http://localhost:8080/Yoga/api/poseController/getAllPoses/").then((req) => {


        readNotification.innerText = req.responseText;
    });
}

function readOne(id) {
    makeRequest("GET", `http://localhost:8080/Yoga/api/poseController/getAPose/${id}`).then((req) => {

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
    makeRequest("DELETE", `http://localhost:8080/Yoga/api/poseController/deletePose/${id}`).then((req) => {
        deleteNotification.innerText = "Pose deleted"
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
    let pose = poseMaker(poseName, poseDifficulty);

    makeRequest("POST", "http://localhost:8080/Yoga/api/poseController/createPose", JSON.stringify(pose)).then((req) => {
        createNotification.innerText = "Pose successfully created";
    });
}

function update() {

    let poseToUpdate = poseMaker(updatePoseName, updateDifficulty);
    console.table(poseToUpdate);

    makeRequest("PUT", `http://localhost:8080/Yoga/api/poseController/updatePose/${poseIDToChange.value}`, JSON.stringify(poseToUpdate)).then((req) => {
        updateNotification.innerText = "Pose successfully updated";
    });
}


function createTable() {

}

