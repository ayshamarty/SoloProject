const routineURL = "http://localhost:8080/Yoga/api/routine/";
//"/Yoga/api/routine/";


const poseURL = "http://localhost:8080/Yoga/api/pose/";
//"/Yoga/api/pose/";

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