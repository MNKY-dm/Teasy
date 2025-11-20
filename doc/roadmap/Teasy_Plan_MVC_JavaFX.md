# 📋 PLAN DE RÉALISATION TEASY - Architecture MVC/JavaFX

**Niveau:** BTS SIO SLAM | **Projet Client:** Plateforme de réservation de billets événementiels | **Technos:** Java, JavaFX, MVC, MySQL

---

## 🎯 DÉCISIONS ARCHITECTURALES CLÉS

### 1. Gestion de Compte - CHOIX RECOMMANDÉ ✅
**Tu dois ABSOLUMENT implémenter la gestion de compte avec login/mot de passe.** Pourquoi ?
- C'est un critère standard pour tout projet "client lourd" en BTS
- Permet une traçabilité professionnelle
- Justifie une structure de BDD plus robuste
- Facilite le contrôle d'accès par rôle (Artiste, Admin, Client)
- Montre ta compréhension de l'architecture 3-tier

**Modification BDD:** Ajouter une table `user` avec:
- `id` (PK, auto-increment)
- `email` (unique)
- `password_hash` (jamais plaintext!)
- `role` (ENUM: 'CLIENT', 'ARTIST', 'ADMIN')
- `created_at`, `updated_at`

**Impact:** La `customer` table devient un profil/détail de client, lié à `user`

---

## 📐 ARCHITECTURE GÉNÉRALE - STRUCTURE DES DOSSIERS

```
Teasy/
├── src/
│   └── main/java/com/teasy/
│       ├── models/
│       │   ├── entities/          ← Classes métier (mirror de BDD)
│       │   │   ├── User.java
│       │   │   ├── Customer.java
│       │   │   ├── Event.java
│       │   │   ├── Seance.java
│       │   │   ├── Photo.java
│       │   │   └── Ticket.java
│       │   └── viewmodels/        ← Modèles pour les vues
│       │       ├── LoginViewModel.java
│       │       ├── EventListViewModel.java
│       │       └── ReservationViewModel.java
│       ├── controllers/
│       │   ├── MainController.java        ← Contrôleur principal (gestion scènes)
│       │   ├── LoginController.java
│       │   ├── EventController.java
│       │   ├── ReservationController.java
│       │   └── AdminController.java
│       ├── views/
│       │   ├── LoginView.java
│       │   ├── MainView.java
│       │   ├── EventListView.java
│       │   └── ReservationView.java
│       ├── dao/                   ← Data Access Objects
│       │   ├── UserDAO.java
│       │   ├── EventDAO.java
│       │   ├── TicketDAO.java
│       │   └── DatabaseConnection.java
│       ├── services/              ← Logique métier
│       │   ├── AuthenticationService.java
│       │   ├── EventService.java
│       │   └── ReservationService.java
│       ├── utils/
│       │   ├── PasswordUtils.java  ← Hachage sécurisé
│       │   └── Constants.java
│       └── Main.java              ← Point d'entrée
└── resources/
    └── styles/
        └── style.css              ← CSS JavaFX
```

---

## 🔄 ARCHITECTURE MVC - CONCEPT FONDAMENTAL

### Pourquoi MVC ?
- **Model:** Données + logique métier (indépendant de JavaFX)
- **View:** Interface graphique (affichage uniquement)
- **Controller:** Orchestration entre View et Model

### Le Pattern MVCI (amélioré pour JavaFX)

```
USER INPUT (View)
      ↓
Controller (reçoit l'action)
      ↓
Interactor/Service (applique logique métier)
      ↓
Model (met à jour les données - ObservableProperty)
      ↓
View (détecte le changement via binding et met à jour UI)
```

### Flux Complet - Exemple: Réserver un Billet

1. **View:** Utilisateur clique sur "Réserver"
2. **Controller:** `reserveTicketHandler()` récupère les données de la view
3. **Service:** `ReservationService.createReservation()` valide la logique métier
4. **DAO:** Appel à `TicketDAO.updateAvailability()` pour persister en BDD
5. **Model:** Mise à jour des `ObservableList` des tickets
6. **View:** Binding automatique met à jour l'affichage

---

## 📊 ÉTAPES DE RÉALISATION - ROADMAP DÉTAILLÉE

### PHASE 0: FONDATIONS (Semaine 1)
**Objectif:** Avoir une structure de projet propre qui compile

#### 0.1 Configuration du Projet
- [ ] Créer projet JavaFX dans ton IDE (IntelliJ ou Eclipse)
- [ ] Ajouter JavaFX SDK au module path (pas Maven!)
- [ ] Importer MySQL Connector JAR
- [ ] Vérifier que le projet compile

**Ressources:**
- Ajoute les JAR au `Project Settings > Libraries`
- Ajoute `--module-path` et `--add-modules` au run configuration

#### 0.2 Connexion à la Base de Données
- [ ] Créer classe `DatabaseConnection.java`
- [ ] Implémenter singleton pattern pour connexion unique
- [ ] Tester la connexion avec une requête simple

**Code Pattern - DO NOT CODE YET, just understand:**
```
DatabaseConnection (Singleton)
├── getConnection() → Connection
├── closeConnection()
└── executeQuery() [optionnel pour utiliser le pool]
```

#### 0.3 Créer les Classes Entity (Mirror de BDD)
- [ ] Créer `User.java` (id, email, passwordHash, role, createdAt)
- [ ] Créer `Customer.java` (id, userId, nom, email, phone)
- [ ] Créer `Event.java`, `Seance.java`, `Photo.java`, `Ticket.java`
- [ ] Ajouter getters/setters et toString()

**Important:** Ces classes sont juste des POJO (Plain Old Java Objects)
- Pas de logique métier
- Pas d'interaction avec BDD
- Pas d'import JavaFX

---

### PHASE 1: AUTHENTIFICATION (Semaine 2)
**Objectif:** Login/logout fonctionnel avec BDD

#### 1.1 UserDAO - Accès aux données utilisateurs
- [ ] Créer `UserDAO.java`
- [ ] Implémenter `findByEmail(String email)` → User
- [ ] Implémenter `createUser(User)` → boolean
- [ ] **IMPORTANT:** Utiliser `PreparedStatement` pour éviter SQL injection

**Bonnes pratiques:**
- Une requête = une méthode
- Gérer les exceptions (`SQLException`)
- Fermer ressources (try-with-resources)

#### 1.2 PasswordUtils - Hachage Sécurisé
- [ ] Créer `PasswordUtils.java`
- [ ] Implémenter `hashPassword(String)` avec BCrypt (si tu peux ajouter dépendance) OU
- [ ] Utiliser `MessageDigest.getInstance("SHA-256")` + salt
- [ ] Implémenter `verifyPassword(plaintext, hash)`

**Minimum acceptable:** SHA-256 avec salt
**Recommandé:** BCrypt (mais nécessite JAR supplémentaire)

#### 1.3 AuthenticationService - Logique d'authentification
- [ ] Créer `AuthenticationService.java`
- [ ] Implémenter `login(email, password)` → User (ou null si échec)
- [ ] Implémenter `register(email, password, role)` → boolean
- [ ] Stocker l'utilisateur courant en tant que static/singleton

```
CurrentUser (static)
├── currentUser: User
├── isLogged(): boolean
└── logout(): void
```

#### 1.4 Vue et Contrôleur de Login
- [ ] Créer `LoginView.java` (étend Region)
  - TextField email
  - PasswordField password
  - Button login
  - Label errorMessage
  
- [ ] Créer `LoginController.java`
  - Référence à Model (LoginViewModel)
  - Référence à Service (AuthenticationService)
  - Handler pour bouton Login
  - Validation simple (champs non vides)

**Binding Pattern:**
```
View (TextField) ← → ViewModel (StringProperty)
      ↓
Controller (onLoginClicked)
      ↓
Service (authentication)
```

#### 1.5 Navigation Scènes
- [ ] Créer `MainController.java` (contrôleur principal)
  - Gère le changement de scènes
  - Méthode `switchScene(Scene newScene)`
  - Maintient une référence au Stage primaire

**Pattern Recommandé:**
```
MainController.switchScene(LoginView ou MainView)
```

---

### PHASE 2: INTERFACE PRINCIPALE (Semaine 3)
**Objectif:** Affichage des événements avec TableView

#### 2.1 EventDAO - Accès aux événements
- [ ] Implémenter `getAllEvents()` → List\<Event\>
- [ ] Implémenter `getEventById(int id)` → Event
- [ ] Implémenter `getEventSeances(int eventId)` → List\<Seance\>

#### 2.2 Modèle Observable pour TableView
- [ ] Créer `EventListViewModel.java`
  ```
  events: ObservableList<Event>
  getEvents(): ObservableList<Event>
  loadEvents(): void
  ```

**CONCEPT CLEF:** ObservableList
- Permet la liaison automatique avec TableView
- Quand on ajoute/supprime un élément → UI met à jour automatiquement
- Plus efficace que mettre à jour UI manuellement

#### 2.3 EventView - Affichage TableView
- [ ] Créer `EventListView.java`
  - TableView avec colonnes: Nom, Date, Lieu, Places disponibles
  - Button "Voir détails"
  - Binder `tableView.setItems(viewModel.getEvents())`

#### 2.4 EventController
- [ ] Connecter ViewModel et View
- [ ] Charger les événements au démarrage
- [ ] Handler pour sélection événement

---

### PHASE 3: RÉSERVATION DE BILLETS (Semaine 4)
**Objectif:** Acheter des tickets (logique métier)

#### 3.1 TicketDAO
- [ ] Implémenter `getAvailableTickets(int seanceId)` → List\<Ticket\>
- [ ] Implémenter `reserveTicket(int ticketId, int customerId)` → boolean
- [ ] Implémenter `getUserTickets(int customerId)` → List\<Ticket\>

#### 3.2 ReservationService
- [ ] Implémenter validation:
  - L'utilisateur est connecté ?
  - Le billet est disponible ?
  - Paiement validé ? (simplifié: toujours ok)
  
- [ ] Implémenter `createReservation()` qui:
  1. Valide
  2. Appelle TicketDAO.reserveTicket()
  3. Enregistre historique
  4. Met à jour ViewModel

#### 3.3 ReservationView & Controller
- [ ] Afficher détails événement
- [ ] Afficher séances disponibles
- [ ] Formulaire: Nombre de places, sélectionner places
- [ ] Bouton "Réserver"

---

### PHASE 4: CONTRÔLE D'ACCÈS PAR RÔLE (Semaine 5)
**Objectif:** Interfaces différentes selon le rôle (Client, Artiste, Admin)

#### 4.1 RoleBasedViewController
```
if (currentUser.getRole() == ADMIN) → afficher panel Admin
if (currentUser.getRole() == ARTIST) → afficher panel Artiste
if (currentUser.getRole() == CLIENT) → afficher panel Client
```

#### 4.2 Fonctionnalités Admin (simplifié)
- [ ] Voir tous les utilisateurs
- [ ] Voir toutes les réservations
- [ ] Créer/modifier événements

#### 4.3 Fonctionnalités Artiste (simplifié)
- [ ] Créer événement
- [ ] Voir réservations de SES événements
- [ ] Statistiques simples

---

### PHASE 5: HISTORIQUE & AFFINAGE (Semaine 6)
**Objectif:** Finitions et robustesse

#### 5.1 Historique des Réservations
- [ ] Nouvelle table `reservation_history` ou colonne `used_at` dans ticket
- [ ] Afficher historique utilisateur: "Tickets réservés"
- [ ] Afficher statut: "Utilisé", "À utiliser", "Expiré"

#### 5.2 Gestion Erreurs
- [ ] Try-catch partout où c'est nécessaire
- [ ] Afficher messages d'erreur utilisateur (sans détails techniques!)
- [ ] Logs en fichier pour déboguer

#### 5.3 Tests & Documentation
- [ ] Créer document README expliquant:
  - Comment lancer l'app
  - Comptes de test (admin@test.com, artist@test.com, etc.)
  - Fonctionnalités principales
  
- [ ] Tester scénarios clés:
  - Login invalide
  - Réservation avec places insuffisantes
  - Changement de scène sans data loss

---

## 🏗️ PATTERNS JAVA À MAÎTRISER

### 1. Singleton Pattern (DatabaseConnection)
```
private static DatabaseConnection instance;

public static DatabaseConnection getInstance() {
    if (instance == null) {
        instance = new DatabaseConnection();
    }
    return instance;
}
```
**Pourquoi:** Une seule connexion à la BDD pour toute l'app

### 2. DAO Pattern (UserDAO, EventDAO, etc.)
Abstraction de l'accès aux données
**Bénéfice:** Facile de changer BDD plus tard

### 3. Observable Pattern (JavaFX)
```
ObservableList<Event> events = FXCollections.observableArrayList();
tableView.setItems(events);
// Quand on fait events.add(...) → TableView update auto!
```

### 4. Binding (JavaFX)
```
textField.textProperty().bindBidirectional(model.nameProperty());
// Les changements se synchronisent automatiquement
```

### 5. MVC/MVCI
**Séparation:** Métier (Service) ≠ Présentation (View) ≠ Données (DAO)

---

## 🔐 POINTS ESSENTIELS SÉCURITÉ

### JAMAIS faire ça:
```java
// ❌ SQL INJECTION - DANGEREUX!
String sql = "SELECT * FROM user WHERE email = '" + email + "'";
```

### Faire ça:
```java
// ✅ SAFE
String sql = "SELECT * FROM user WHERE email = ?";
PreparedStatement stmt = connection.prepareStatement(sql);
stmt.setString(1, email);
```

### Stockage mot de passe:
```java
// ❌ JAMAIS stocker plaintext
// ✅ Toujours hasher + salt
String hashedPassword = hashPassword(userPassword);
```

---

## 📚 RESSOURCES & TUTORIELS

### JavaFX & MVC
- **PragmaticCoding MVC:** https://www.pragmaticcoding.ca/javafx/MVC_In_JavaFX
- **Oracle JavaFX Best Practices:** https://docs.oracle.com/javafx/2/best_practices/jfxpub-best_practices.htm
- **YouTube - JavaFX Scene Switching:** https://www.youtube.com/watch?v=SB9AnciLmsw

### Login & Authentification
- **YouTube - Login with Database:** https://www.youtube.com/watch?v=J0IE5LRyzx8 (35 min, très pédagogique)
- **w3resource Login Form:** https://www.w3resource.com/java-exercises/javafx/javafx-events-and-event-handling-exercise-8.php

### JDBC & Sécurité
- **GeeksforGeeks - PreparedStatement:** https://www.geeksforgeeks.org/java/how-to-handle-sql-injection-in-jdbc-using-preparedstatement/
- **OWASP - Password Storage:** https://cheatsheetseries.owasp.org/cheatsheets/Password_Storage_Cheat_Sheet.html

### TableView & Observable
- **Jenkov - TableView:** https://jenkov.com/tutorials/javafx/tableview.html
- **YouTube - Observable Lists:** https://www.youtube.com/watch?v=Ia4Pf6xEMpI

---

## ✅ CHECKLIST DE VALIDATION - PAR PHASE

### Phase 0: ✅
- [ ] Projet compile sans erreur
- [ ] Connexion BDD fonctionnelle
- [ ] Classes Entity créées et testées

### Phase 1: ✅
- [ ] Login/logout fonctionne
- [ ] Mots de passe hashés en BDD
- [ ] Gestion erreurs (email inexistant, mdp faux)

### Phase 2: ✅
- [ ] TableView affiche événements
- [ ] Sélection événement fonctionne
- [ ] Affichage détails événement ok

### Phase 3: ✅
- [ ] Réservation possible
- [ ] Vérification places disponibles
- [ ] Historique des réservations visible

### Phase 4: ✅
- [ ] Interfaces différentes par rôle
- [ ] Admin peut créer événements
- [ ] Artiste voit ses stats

### Phase 5: ✅
- [ ] Pas d'erreurs non gérées
- [ ] Documentation complète
- [ ] Tous les scénarios testés

---

## 💡 CONSEILS PRATIQUES POUR DÉBUTER

### 1. Commence petit
- Fais marcher Login en premier
- PUIS add événements
- PUIS réservations
- Progressivement = moins d'erreurs

### 2. Sépare Model, View, Controller PHYSIQUEMENT
- Pas de logique métier dans les classes View!
- Pas de `@FXML` ou `Scene` dans les Services!
- Chaque classe a UNE responsabilité

### 3. Teste avec des print() (ou System.out.println)
- Avant de faire des UI complexes
- Rassure-toi que la logique marche
- "Fait d'abord fonctionner, embelli ensuite"

### 4. Les ObservableList c'est magique
- Dès que tu ajoutes un élément → UI update auto
- C'est pourquoi c'est mieux qu'une ArrayList normale
- Utilise-les partout dans tes ViewModels

### 5. Commit sur GitHub souvent
- Après chaque phase complète
- "Je connais les bases du MVC"
- "Authentification fonctionne"
- Utile pour revenir en arrière si bug

---

## 🚨 ERREURS À ÉVITER

| ❌ Erreur | ✅ Solution |
|-----------|-----------|
| Mélanger logique métier et UI | Service ≠ View |
| Créer nouvelle ObservableList à chaque chargement | `.clear()` puis `.addAll()` |
| Stocker mot de passe en plaintext | Toujours hasher |
| Créer nouvelle connexion BDD chaque requête | Utiliser Singleton |
| Ignorer les exceptions | Try-catch + afficher message user-friendly |
| Binder View directement à Entity | Passer par ViewModel/Service |
| N'utiliser que SELECT pour vérification | Utiliser COUNT() pour mieux |
| Pas de validation côté serveur | Valider TOUJOURS avant persister |

---

## 📖 STRUCTURE DE TON CODE - EXEMPLE CONCRET

Quand tu implémenteras la réservation, ton code ressemblera à:

```
1. UserClique ReserveButton → LoginView.handleReserveClick()
2. MainController.handleReserveClick() reçoit l'action
3. MainController appelle ReservationService.createReservation(eventId, userId, nbPlaces)
4. ReservationService vérifie:
   - User existe ?
   - Event existe ?
   - Tickets disponibles ?
5. Si tout OK: ReservationService appelle TicketDAO.reserveTicket()
6. TicketDAO UPDATE tickets SET reserved_at = NOW() WHERE id = ?
7. TicketDAO retourne true/false
8. ReservationService met à jour viewModel.reservations (ObservableList)
9. View (liée au ViewModel) se met à jour automatiquement!
```

---

## 🎓 POUR TON EXAMEN/PRÉSENTATION

**Sois prêt à expliquer:**
1. "Pourquoi MVC ?" → Séparation des responsabilités, testabilité, maintenance
2. "Comment gères-tu l'authentification ?" → Hash + salt, PreparedStatement
3. "Pourquoi ObservableList ?" → Binding automatique, plus efficace
4. "Comment évites-tu l'SQL injection ?" → PreparedStatement avec placeholders
5. "Architecture: comment les classes interagissent ?" → DAO → Service → Controller → View

**Prépare des diagrammes:**
- Schéma MVC
- Flux d'authentification
- Architecture générale

---

**COMMENCES PAR LA PHASE 0! ⭐**
Une fois que ton projet compile et que tu peux te connecter à la BDD, tout devient plus facile.