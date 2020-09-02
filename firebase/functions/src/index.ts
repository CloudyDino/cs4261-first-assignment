import * as functions from 'firebase-functions';
import * as admin from 'firebase-admin';

admin.initializeApp();

export const helloWorld = functions.https.onRequest((request, response) => {
    response.send('Hello from Firebase!\n\n');
});

export const getAllMessages = functions.https.onRequest((request, response) => {
    const db = admin.database();
    const ref = db.ref("messages");
    ref.on("value", function (snapshot) {
        const data = snapshot.val();
        for (const messageId in data) {
            if (!request.body['getAvatar']) {
                delete data[messageId]['photoUrl']
            }
            if (!request.body['getImage']) {
                delete data[messageId]['imageUrl']
            }
            if (!request.body['getName']) {
                delete data[messageId]['name']
            }
            if (!request.body['getText']) {
                delete data[messageId]['text']
            }
        }
        response.send(data);
    });
});
