## Projekt: Java Text-Adventure "CUBE"

Ein textbasiertes Logik- und Explorationsspiel, inspiriert durch den Film "Cube". 
Der Spieler muss durch ein komplexes System aus 125 Räumen navigieren und dabei tödliche Fallen identifizieren.

### Technische Kern-Features
- **3D-Koordinatensystem:** Implementierung einer Spielwelt als $5 \times 5 \times 5$ Matrix unter Verwendung eines dreidimensionalen Arrays (Room[x][y][z]).
- **Objektorientierte Architektur (OOP):** Saubere Trennung der Logik in die Klassen Main, Cube, Room und Player.
- **Bewegungslogik & Validierung:** Dynamische Steuerung des Spielers in sechs Richtungen (Norden, Süden, Osten, Westen, Oben, Unten) inklusive Absicherung gegen Index-Fehler (Boundary Checks).
- **Prozedurale Daten:** Automatische Generierung von Raumnummern bei der Initialisierung des Cubes.
- **Kapselung:** Konsequente Nutzung von Zugriffsmodifizierern (private) und Getter-Methoden zur Sicherstellung der Datenintegrität.

### Verwendete Technologien
- **Sprache:** Java
- **Build-Tool:** Maven
- **IDE:** IntelliJ IDEA
- **Versionskontrolle:** Git

### Geplante Erweiterungen (Roadmap)
- **Trap-Logik:** Implementierung eines Algorithmus zur Primzahlprüfung, um gefährliche Räume anhand ihrer Raumnummern zu identifizieren.
- **Inventory System:** Sammeln und Nutzen von Gegenständen (z. B. Schuhe zum Testen von Fallen).
- **Win/Loss Conditions:** Definition eines Zielraums und Implementierung der Lebenspunkte des Spielers.

### Installation & Ausführung
1. Repository klonen: git clone (https://github.com/Pixelhaft/java-cube-project)
2. In IntelliJ öffnen.
3. Die Main.java ausführen.
4. Befehle wie north, south, up, down im Terminal eingeben.
