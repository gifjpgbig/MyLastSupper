
import firebase from "firebase/app";
import "firebase/messaging";
function getToken() {
    const messaging = firebase.messaging();
    messaging.getToken({ vapidKey: "BFQJjpHmsHR3WRRwG9ea5Xawvt8p5JwAF-JgAPDGU0Elj7UX_kjSg8rHHQhfL9OTpiIgR_YSdNl9R425RkjV8JY", })
        .then((currentToken) => {
            if (currentToken) {
                console.log(currentToken);
            }
            else {
                console.log("No registration token available. Request permission to generate one.");
            }
        })
        .catch((err) => {
            console.log("An error occurred while retrieving token. ", err);
        });
}


export { getToken };