package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.Firestore;

@RestController
public class PokeController {

    @GetMapping("/test")
    public String test() {
        Firestore db = FirestoreClient.getFirestore();
        return "Firebase conectado correctamente";
    }
}