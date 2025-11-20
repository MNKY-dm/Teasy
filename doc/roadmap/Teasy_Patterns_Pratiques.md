# 🔧 GUIDE PRATIQUE - Patterns & Implémentations Clés

## 1️⃣ PATTERN SINGLETON - DatabaseConnection

**Objectif:** Une seule connexion à la BDD pour toute l'app

**Structure:**
```
DatabaseConnection (classe)
├── - instance: DatabaseConnection (static, privé)
├── - connection: Connection (privé)
├── + getInstance(): DatabaseConnection (static)
├── + getConnection(): Connection
└── + closeConnection(): void
```

**Utilisation:**
```
Connection conn = DatabaseConnection.getInstance().getConnection();
```

**Bénéfices:**
- Économise ressources (pas de reconnexion à chaque opération)
- Point centralisé pour gérer connexion
- Facile à passer de localhost à serveur réel

---

## 2️⃣ PATTERN DAO - UserDAO Example

**Objectif:** Isoler logique d'accès aux données

**Qu'est-ce qu'on FAIT en DAO:**
- ✅ Requêtes SQL
- ✅ Mapping ResultSet → Objets Java
- ✅ Gestion exceptions SQLException
- ✅ Fermer ressources (try-with-resources)

**Qu'est-ce qu'on NE FAIT PAS:**
- ❌ Validation métier
- ❌ Hachage mot de passe
- ❌ Appels à d'autres DAO

**Structure UserDAO:**
```
UserDAO
├── + findByEmail(String email): User
├── + findById(int id): User
├── + createUser(User user): boolean
├── + updateUser(User user): boolean
├── + deleteUser(int id): boolean
└── + getAllUsers(): List<User>
```

**Pattern de chaque méthode:**
```
public User findByEmail(String email) {
    String sql = "SELECT * FROM user WHERE email = ?";
    
    try (Connection conn = DatabaseConnection.getInstance().getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setString(1, email);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return mapResultSetToUser(rs);  // Mapper row → Object
            }
        }
    } catch (SQLException e) {
        System.err.println("Error finding user: " + e.getMessage());
    }
    return null;
}

private User mapResultSetToUser(ResultSet rs) throws SQLException {
    return new User(
        rs.getInt("id"),
        rs.getString("email"),
        rs.getString("password_hash"),
        rs.getString("role"),
        rs.getTimestamp("created_at").toLocalDateTime()
    );
}
```

**Règle d'or DAO:**
- 1 méthode = 1 responsabilité SQL
- Toujours utiliser PreparedStatement
- Toujours fermer ressources
- Toujours retourner le même type

---

## 3️⃣ PATTERN SERVICE - AuthenticationService Example

**Objectif:** Logique métier INDÉPENDANTE de JavaFX et BDD

**Qu'est-ce qu'on FAIT en Service:**
- ✅ Validation métier (champs vides, email format, etc.)
- ✅ Hachage/vérification mot de passe
- ✅ Appeler DAOs pour persister
- ✅ Orchestrer plusieurs DAOs si nécessaire

**Qu'est-ce qu'on NE FAIT PAS:**
- ❌ Affichage (pas de System.out.println!)
- ❌ Détails SQL
- ❌ Gestion des Scènes JavaFX
- ❌ try-catch des SQLException (responsabilité DAO)

**Structure AuthenticationService:**
```
AuthenticationService
├── + login(String email, String password): User
├── + register(String email, String password, String role): boolean
├── + isValidEmail(String email): boolean
├── - validateCredentials(User user, String password): boolean
└── (static) getCurrentUser(): User
```

**Exemple login:**
```
public User login(String email, String password) {
    // Validation
    if (email == null || email.trim().isEmpty()) {
        return null;  // ou throw exception
    }
    if (password == null || password.trim().isEmpty()) {
        return null;
    }
    
    // Récupérer user en BDD
    UserDAO dao = new UserDAO();
    User user = dao.findByEmail(email);
    
    if (user == null) {
        return null;  // User inexistant
    }
    
    // Vérifier mot de passe
    if (!PasswordUtils.verifyPassword(password, user.getPasswordHash())) {
        return null;  // Mot de passe incorrect
    }
    
    // Succès
    CurrentSession.setCurrentUser(user);
    return user;
}
```

**Gestion session courante:**
```
public class CurrentSession {
    private static User currentUser = null;
    
    public static void setCurrentUser(User user) {
        currentUser = user;
    }
    
    public static User getCurrentUser() {
        return currentUser;
    }
    
    public static boolean isLoggedIn() {
        return currentUser != null;
    }
    
    public static void logout() {
        currentUser = null;
    }
}
```

---

## 4️⃣ PATTERN VIEWMODEL - ObservableList Example

**Objectif:** Données observables pour la View (binding automatique)

**Qu'est-ce qu'on met en ViewModel:**
- ✅ ObservableList (pour TableView)
- ✅ ObservableValue/Property (pour bind)
- ✅ Getters simples
- ✅ Setter qui update la liste (clear + addAll)

**Qu'est-ce qu'on NE met PAS:**
- ❌ Logique métier complexe
- ❌ Appels BDD (laisser au Service)
- ❌ Gestion Scènes

**Structure EventListViewModel:**
```
EventListViewModel
├── - events: ObservableList<Event>
├── - eventService: EventService
├── + getEvents(): ObservableList<Event>
├── + loadEvents(): void
├── + getEventCount(): int
└── + getEventById(int id): Event
```

**Exemple implementation:**
```
public class EventListViewModel {
    private ObservableList<Event> events;
    private EventService eventService;
    
    public EventListViewModel() {
        this.events = FXCollections.observableArrayList();
        this.eventService = new EventService();
    }
    
    public ObservableList<Event> getEvents() {
        return events;
    }
    
    public void loadEvents() {
        // ⚠️ PIÈCE CRITIQUE: on ne crée pas une nouvelle liste!
        // On réutilise la MÊME liste et on clear/addAll
        
        List<Event> allEvents = eventService.getAllEvents();
        events.clear();  // Vider la liste Observable
        events.addAll(allEvents);  // Ajouter les nouvelles données
        
        // Magic: TableView voit la modification et update automatiquement!
    }
    
    public int getEventCount() {
        return events.size();
    }
}
```

**Binding dans View:**
```
EventListViewModel viewModel = new EventListViewModel();
viewModel.loadEvents();  // Charger données

TableView<Event> tableView = new TableView<>();
tableView.setItems(viewModel.getEvents());  // ← Binding!

// Maintenant quand on fait viewModel.getEvents().add(newEvent)
// la TableView se met à jour AUTOMATIQUEMENT
```

---

## 5️⃣ PATTERN MVC - Flow Complet

**Flow d'une action utilisateur:**

```
┌─────────────────────────────────────────────────────────────┐
│  1. USER INTERACTION (View)                                 │
│     Ex: Utilisateur clique "Login"                          │
└────────────────────────┬────────────────────────────────────┘
                         │
┌────────────────────────▼────────────────────────────────────┐
│  2. CONTROLLER REÇOIT L'ÉVÉNEMENT                           │
│     Ex: LoginController.handleLoginClick()                  │
│     • Récupère données des TextField                        │
│     • Appelle Service                                        │
│     • NE FAIT PAS: appels BDD directs                       │
└────────────────────────┬────────────────────────────────────┘
                         │
┌────────────────────────▼────────────────────────────────────┐
│  3. SERVICE - LOGIQUE MÉTIER                                │
│     Ex: AuthenticationService.login(email, password)        │
│     • Valide données (email format, etc.)                   │
│     • Appelle DAO pour données                              │
│     • Exécute logique métier (hash pwd, etc.)               │
│     • NE FAIT PAS: affichage, gestion UI                    │
└────────────────────────┬────────────────────────────────────┘
                         │
┌────────────────────────▼────────────────────────────────────┐
│  4. DAO - ACCÈS DONNÉES                                     │
│     Ex: UserDAO.findByEmail(email)                          │
│     • Exécute requête SQL                                   │
│     • Mappe ResultSet → Objet Java                          │
│     • Ferme ressources                                      │
│     • NE FAIT PAS: logique métier                           │
└────────────────────────┬────────────────────────────────────┘
                         │
┌────────────────────────▼────────────────────────────────────┐
│  5. SERVICE met à jour MODEL/VIEWMODEL                      │
│     Ex: CurrentSession.setCurrentUser(user)                 │
│     Ex: viewModel.getUsers().add(newUser)                   │
│     • Mise à jour ObservableList                            │
└────────────────────────┬────────────────────────────────────┘
                         │
┌────────────────────────▼────────────────────────────────────┐
│  6. VIEW DÉTECTE CHANGEMENT (Binding JavaFX)                │
│     • ObservableList a changé                               │
│     • Binding automatique                                   │
│     • UI se met à jour SEULE!                               │
└─────────────────────────────────────────────────────────────┘
```

---

## 6️⃣ PATTERN OBSERVABLE - Binding Example

**Pourquoi Binding ?**
- Synchronisation automatique Model ↔ View
- Pas besoin de mettre à jour UI manuellement
- Moins de bugs

**Types de Binding:**

### One-way (View ← Model)
```
Label statusLabel = new Label();
StringProperty status = new SimpleStringProperty("En attente...");

statusLabel.textProperty().bind(status);

// Maintenant quand on fait:
status.set("Connecté");
// Le label s'update AUTOMATIQUEMENT
```

### Two-way (View ↔ Model)
```
TextField emailField = new TextField();
StringProperty email = new SimpleStringProperty("");

emailField.textProperty().bindBidirectional(email);

// L'utilisateur tape dans le champ → email.get() retourne la valeur
// Ou on fait email.set("test@test.com") → le champ affiche la valeur
```

### Dans ViewModel:
```
public class LoginViewModel {
    private StringProperty email = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();
    private BooleanProperty isLoading = new SimpleBooleanProperty(false);
    
    public StringProperty emailProperty() {
        return email;
    }
    
    public String getEmail() {
        return email.get();
    }
    
    public void setEmail(String value) {
        email.set(value);
    }
    
    // Même pour password, isLoading...
}
```

### Usage en View:
```
LoginViewModel viewModel = new LoginViewModel();
TextField emailField = new TextField();
PasswordField passwordField = new PasswordField();

emailField.textProperty().bindBidirectional(viewModel.emailProperty());
passwordField.textProperty().bindBidirectional(viewModel.passwordProperty());

Button loginBtn = new Button("Login");
loginBtn.disableProperty().bind(viewModel.isLoadingProperty());
// Le bouton est disabled tant qu'on charge!
```

---

## 7️⃣ SÉCURITÉ - PasswordUtils Pattern

**Qu'est-ce qu'on NE FAIT JAMAIS:**
```
// ❌ JAMAIS stocker plaintext
user.setPassword(password);
dao.save(user);  // BDD contient password en clair = catastrophe
```

**Qu'est-ce qu'on FAIT:**
```
// ✅ Toujours hasher + stocker le hash
String passwordHash = PasswordUtils.hashPassword(password);
user.setPasswordHash(passwordHash);
dao.save(user);
```

**Implémentation simple (SHA-256 + Salt):**
```
public class PasswordUtils {
    
    public static String hashPassword(String password) {
        try {
            // Créer un salt aléatoire
            byte[] salt = new byte[16];
            new java.security.SecureRandom().nextBytes(salt);
            
            // Hasher password + salt
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hash = md.digest(password.getBytes());
            
            // Combiner salt + hash
            byte[] saltAndHash = new byte[salt.length + hash.length];
            System.arraycopy(salt, 0, saltAndHash, 0, salt.length);
            System.arraycopy(hash, 0, saltAndHash, salt.length, hash.length);
            
            // Encoder en Base64 pour stocker en BDD
            return java.util.Base64.getEncoder().encodeToString(saltAndHash);
        } catch (Exception e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
    
    public static boolean verifyPassword(String password, String storedHash) {
        try {
            byte[] saltAndHash = java.util.Base64.getDecoder().decode(storedHash);
            
            // Extraire le salt
            byte[] salt = new byte[16];
            System.arraycopy(saltAndHash, 0, salt, 0, 16);
            
            // Hasher le password fourni avec le même salt
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hash = md.digest(password.getBytes());
            
            // Comparer les hashes
            byte[] expectedHash = new byte[salt AndHash.length - 16];
            System.arraycopy(saltAndHash, 16, expectedHash, 0, expectedHash.length);
            
            return java.util.Arrays.equals(hash, expectedHash);
        } catch (Exception e) {
            return false;
        }
    }
}
```

---

## 8️⃣ NAVIGATION SCÈNES - Scene Switching Pattern

**Problème:** Comment changer de scène proprement en MVC ?

**Solution 1: MainController gère les scènes (RECOMMANDÉ)**
```
public class MainController {
    private Stage primaryStage;
    
    public MainController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    
    public void switchToLogin() {
        LoginView loginView = new LoginView();
        LoginController controller = new LoginController(this, loginView);
        
        Scene scene = new Scene(loginView, 800, 600);
        primaryStage.setScene(scene);
    }
    
    public void switchToMainApp() {
        MainView mainView = new MainView();
        MainAppController controller = new MainAppController(this, mainView);
        
        Scene scene = new Scene(mainView, 1024, 768);
        primaryStage.setScene(scene);
    }
}
```

**Usage au login:**
```
public class LoginController {
    private MainController mainController;
    
    public LoginController(MainController mainController, LoginView view) {
        this.mainController = mainController;
        setupUI(view);
    }
    
    private void setupUI(LoginView view) {
        view.getLoginButton().setOnAction(event -> {
            // ... validation, login ...
            if (loginSuccess) {
                mainController.switchToMainApp();  // Change scène!
            }
        });
    }
}
```

---

## 9️⃣ GESTION DES ERREURS - Try-Catch Pattern

**Hiérarchie erreurs Java:**
```
Throwable
├── Error (grave, ne pas catch)
└── Exception
    ├── SQLException (BDD)
    ├── IOException (Fichiers)
    └── ...
```

**Pattern recommandé:**
```
try {
    // Code qui peut échouer
    User user = userDAO.findByEmail(email);
    
} catch (SQLException e) {
    // Erreur BDD - log et afficher message user
    System.err.println("Database error: " + e.getMessage());
    showUserError("Erreur de connexion à la BDD");
    
} catch (Exception e) {
    // Autres erreurs
    System.err.println("Unexpected error: " + e.getMessage());
    showUserError("Une erreur inattendue s'est produite");
}
```

**JAMAIS faire:**
```
// ❌ Ignorer erreur
try {
    someRiskyOperation();
} catch (Exception e) {
    // Silence total = bug caché!
}

// ❌ Re-throw sans info
try {
    someRiskyOperation();
} catch (Exception e) {
    throw new RuntimeException(e);  // Perd contexte
}
```

---

## 🔟 ORGANISATION DU CODE - Checklist

**Pour chaque classe, demande-toi:**
- [ ] A une seule responsabilité ?
- [ ] Nom clair et explicite ?
- [ ] Pas de logique d'une autre couche (DAO ≠ Service ≠ View) ?
- [ ] Getters/setters corrects ?
- [ ] Pas de `public static` sauf pour constantes et Singleton ?
- [ ] Docstring/commentaires pour parties complexes ?

**Pour chaque méthode:**
- [ ] Fait UNE seule chose ?
- [ ] Nom décrit son action (verbe + nom) ?
- [ ] Pas plus de 20 lignes ideally ?
- [ ] Pas de variables globales utilisées ?
- [ ] Gère ses exceptions ?

---

## 📝 RÉSUMÉ PATTERNS CLÉS

| Pattern | Où | Responsabilité |
|---------|-----|-----------------|
| **Singleton** | DatabaseConnection | Une seule instance |
| **DAO** | UserDAO, EventDAO | Accès données + SQL |
| **Service** | AuthService | Logique métier |
| **ViewModel** | EventListViewModel | Données observables |
| **MVC** | Architecture globale | Séparation responsabilités |
| **Observable** | ObservableList, Property | Binding auto |
| **Binding** | View ↔ ViewModel | Synchronisation auto |

---

**Pense toujours: "Où cette responsabilité appartient-elle ?"**
- UI → View
- Métier → Service
- SQL → DAO
- Données observables → ViewModel
- Orchestration → Controller