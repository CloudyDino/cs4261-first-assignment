import * as functions from 'firebase-functions';
import * as admin from 'firebase-admin';

admin.initializeApp();

export const helloWorld = functions.https.onRequest((request, response) => {
    response.send('Hello from Firebase!\n\n');
});

export const getAllMessages = functions.https.onRequest((request, response) => {
    const db = admin.database();
    const ref = db.ref("messages");
    ref.on("value", function (data) {
        response.send(data);
    });
});
