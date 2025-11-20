# ⚡ QUICK START - Ton Premier Code Ce Week-end

**Objectif:** Avoir un projet qui compile et peut se connecter à la BDD

---

## 📋 CHECKLIST JOUR 1 - Samedi

### [ ] 1. Créer le Projet
- [ ] Ouvrir IntelliJ → New Project → Java
- [ ] Nommer: `Teasy`
- [ ] Sélectionner JDK 16+
- [ ] Créer

### [ ] 2. Ajouter JavaFX
- [ ] Télécharger JavaFX SDK depuis https://gluonhq.com/products/javafx/
- [ ] File → Project Structure → Modules → Dependencies
- [ ] + → Add Library → Select Folder → Choisir dossier JavaFX
- [ ] Appliquer

### [ ] 3. Ajouter MySQL Connector
- [ ] Télécharger mysql-connector-java-8.0.23.jar (ou + récent)
- [ ] Droite-clic projet → Open Module Settings → Libraries
- [ ] + → Add → Sélectionner le JAR
- [ ] Appliquer

### [ ] 4. Tester le Lancement
- [ ] File → New → Java Class → nommer `Main`
```java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(new Label("Teasy App"));
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
```
- [ ] Run → Vérifier que la fenêtre s'ouvre

### [ ] 5. Créer Structure de Dossiers
```
src/
├── com/teasy/
│   ├── Main.java
│   ├── models/
│   ├── views/
│   ├── controllers/
│   ├── dao/
│   ├── services/
│   └── utils/
```

---

## 📋 CHECKLIST JOUR 2 - Dimanche

### [ ] 6. Créer Classe DatabaseConnection
```
src/com/teasy/dao/DatabaseConnection.java
```

**Minimum à faire:**
- Singleton pattern
- Méthode `getInstance()`
- Méthode `getConnection()`
- Vérifier connexion avec test simple

**Tester:**
```java
public static void main(String[] args) {
    Connection conn = DatabaseConnection.getInstance().getConnection();
    if (conn != null) {
        System.out.println("✅ Connexion OK!");
    }
}
```

### [ ] 7. Créer Classes Entity
Créer dans `src/com/teasy/models/`:
- [ ] `User.java` (id, email, passwordHash, role)
- [ ] `Customer.java` (id, userId, nom, phone)
- [ ] `Event.java` (id, nom, description, date)

**Pour chaque classe:**
- [ ] Champs privés
- [ ] Constructeurs (vide + complet)
- [ ] Getters/setters
- [ ] toString()

### [ ] 8. Créer UserDAO de Base
Créer `src/com/teasy/dao/UserDAO.java`

**Méthodes à implémenter:**
```java
public User findByEmail(String email) { ... }
public boolean createUser(User user, String passwordHash) { ... }
```

**Tester:**
```java
UserDAO dao = new UserDAO();
User test = dao.findByEmail("admin@test.com");
if (test != null) {
    System.out.println("✅ User trouvé: " + test.getEmail());
}
```

### [ ] 9. Créer PasswordUtils
Créer `src/com/teasy/utils/PasswordUtils.java`

**Méthodes:**
```java
public static String hashPassword(String password) { ... }
public static boolean verifyPassword(String password, String hash) { ... }
```

**Tester:**
```java
String hash = PasswordUtils.hashPassword("test123");
boolean verified = PasswordUtils.verifyPassword("test123", hash);
System.out.println("Hash correct: " + verified);
```

### [ ] 10. Créer AuthenticationService
Créer `src/com/teasy/services/AuthenticationService.java`

**Méthode clé:**
```java
public User login(String email, String password) {
    UserDAO dao = new UserDAO();
    User user = dao.findByEmail(email);
    if (user != null && PasswordUtils.verifyPassword(password, user.getPasswordHash())) {
        return user;
    }
    return null;
}
```

**Tester:**
```java
AuthenticationService auth = new AuthenticationService();
User logged = auth.login("admin@test.com", "password123");
if (logged != null) {
    System.out.println("✅ Login OK: " + logged.getEmail());
}
```

---

## 🚀 BONUS - Si tu as du temps

### [ ] 11. Créer Vue Login Basique
```java
// src/com/teasy/views/LoginView.java
public class LoginView extends VBox {
    private TextField emailField;
    private PasswordField passwordField;
    private Button loginButton;
    private Label errorLabel;

    public LoginView() {
        emailField = new TextField();
        emailField.setPromptText("Email");
        
        passwordField = new PasswordField();
        passwordField.setPromptText("Mot de passe");
        
        loginButton = new Button("Login");
        errorLabel = new Label();
        
        this.getChildren().addAll(
            new Label("Teasy Login"),
            emailField,
            passwordField,
            loginButton,
            errorLabel
        );
    }

    // Getters pour le Controller
    public String getEmail() { return emailField.getText(); }
    public String getPassword() { return passwordField.getText(); }
    public Button getLoginButton() { return loginButton; }
    public void setError(String msg) { errorLabel.setText(msg); }
}
```

### [ ] 12. Créer LoginController
```java
// src/com/teasy/controllers/LoginController.java
public class LoginController {
    private LoginView view;
    private AuthenticationService authService;

    public LoginController(LoginView view) {
        this.view = view;
        this.authService = new AuthenticationService();
        setupHandlers();
    }

    private void setupHandlers() {
        view.getLoginButton().setOnAction(event -> handleLogin());
    }

    private void handleLogin() {
        String email = view.getEmail();
        String password = view.getPassword();

        User user = authService.login(email, password);
        if (user != null) {
            System.out.println("✅ Login réussi!");
            // TODO: Changer de scène
        } else {
            view.setError("Email ou mot de passe incorrect");
        }
    }
}
```

### [ ] 13. Intégrer dans Main.java
```java
@Override
public void start(Stage primaryStage) {
    LoginView loginView = new LoginView();
    LoginController controller = new LoginController(loginView);
    
    Scene scene = new Scene(loginView, 400, 300);
    primaryStage.setTitle("Teasy");
    primaryStage.setScene(scene);
    primaryStage.show();
}
```

---

## ✅ VALIDATION - À la fin du week-end

- [ ] Projet compile sans erreur
- [ ] Classe DatabaseConnection fonctionne
- [ ] PasswordUtils fonctionne
- [ ] AuthenticationService login fonctionne
- [ ] LoginView s'affiche
- [ ] Click Login affiche message (erreur ou succès)

**Si tout marche:** 🎉 Tu as la base! Passe à la PHASE 1 du Plan Complet

---

## 🐛 Troubleshooting Rapide

**Erreur:** "Cannot find module javafx"
→ Vérifier File → Project Settings → Module Path + JavaFX

**Erreur:** "Connection refused"
→ Vérifier que MySQL est lancé sur localhost:3306

**Erreur:** "SQLException: Column 'password_hash' not found"
→ Vérifier structure table `user` en BDD

**Erreur:** "NullPointerException" au login
→ Vérifier que l'utilisateur existe en BDD

---

## 📚 Fichiers à Créer (Ordre Priorité)

1. `Main.java` ← Lance app
2. `DatabaseConnection.java` ← Connexion BDD
3. `User.java` ← Modèle
4. `UserDAO.java` ← Accès BDD
5. `PasswordUtils.java` ← Hachage
6. `AuthenticationService.java` ← Logique login
7. `LoginView.java` ← UI
8. `LoginController.java` ← Lien View ↔ Service

---

## 💡 Rappels Clés

1. **Un fichier = Une classe**
2. **Pas de `main()` sauf dans Main.java**
3. **Toujours utiliser PreparedStatement** (pas de String concatenation!)
4. **Toujours fermer ressources** (try-with-resources)
5. **Test avec println** avant de faire UI complexe
6. **Commit sur GitHub** à la fin du jour!

---

## 🎯 Objectif à la fin du week-end

Pouvoir faire:
1. Lancer l'app
2. Cliquer Login
3. Voir: "✅ Login réussi" ou "❌ Erreur identifiants"

Si c'est le cas, tu es PRÊT pour continuer sur les événements! 🚀