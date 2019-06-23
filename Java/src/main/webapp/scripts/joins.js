function addToRoutine() {
    makeRequest("POST", `${routineURL}addToRoutine/${routineToJoin.value}/${poseToJoin.value}`).then((req) => {
        let notification = JSON.parse(req.responseText);
        joinNotify.innerText = notification.message
    }).catch((error) => { console.log(error.message) });
}

function removeFromRoutine() {
    makeRequest("DELETE", `${routineURL}removeFromRoutine/${routineToJoin.value}/${poseToJoin.value}`).then((req) => {
        let notification = JSON.parse(req.responseText);
        joinNotify.innerText = notification.message
    }).catch((error) => { console.log(error.message) }); 
}