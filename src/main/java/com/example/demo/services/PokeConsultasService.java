package com.example.demo.services;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import com.example.demo.model.PokeEntity;
import com.example.demo.model.Pokemon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import jakarta.annotation.PostConstruct;

import com.google.firebase.FirebaseApp;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;

import java.util.concurrent.ExecutionException;

@Service
public class PokeConsultasService {

    public PokeEntity getVidaPokemons() throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();

        // .get() devuelve un ApiFuture<DocumentSnapshot>
        // Es decir: una promesa de que el documento llegará en el futuro
        DocumentSnapshot docSnapshot = db.collection("pokebattles")
                                        .document("battle1")
                                        .get()
                                        .get(); // get() bloquea hasta obtener el documento

        // Verifica si el documento existe en la base de datos
        if (docSnapshot.exists()) {
            return docSnapshot.toObject(PokeEntity.class);
        } else {
            return new PokeEntity(); // devolver valores por defecto si no existe
        }
    }

    public Pokemon savePokemon(Pokemon pokemon) throws InterruptedException, ExecutionException{
        Firestore db = FirestoreClient.getFirestore();

        DocumentReference docRef = db.collection("pokemones").document();

        String id = docRef.getId();
        pokemon.setId(id);
        docRef.set(pokemon).get();
        return pokemon;

    }
}