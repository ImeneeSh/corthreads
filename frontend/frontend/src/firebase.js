import { initializeApp } from "firebase/app";
import { getAuth, GoogleAuthProvider, signInWithPopup } from "firebase/auth";

const firebaseConfig = {
    apiKey: "AIzaSyDIgmp1UqlrokVXwkumEAlv9CIVHKccbgc",
    authDomain: "corthreads-9ff32.firebaseapp.com",
    projectId: "corthreads-9ff32",
    storageBucket: "corthreads-9ff32.firebasestorage.app",
    messagingSenderId: "5041538188",
    appId: "1:5041538188:web:31c3b662cda9df4fa467a9"
};

const app = initializeApp(firebaseConfig);
const auth = getAuth(app);
const provider = new GoogleAuthProvider();

export { auth, provider, signInWithPopup };
