Readme
---------------------
 * GitHub   
 * Fingerabdruecke
 * Installation
 * ZFM20
 * VM

GitHub
------------
Fingerabdrücke, Code des Prototyps und der Treiber für das Lesegerät ist auf GitHub zu finden
URL: https://github.com/tutzauel/BA

Fingerabdrücke
------------

*Der Ordner Fingerabdruecke enthällt drei Unterordner.
	1. Fingerprints auf der Datenbank
	2. Fingerprints die nicht auf der Datenbank sind
	3. Gültige Fingerabdrücke die authentifiziert werden sollen

*Diese wurden als Basis genommen um die Testergebnisse der Ausarbeitung zu erziehlen.


Installation
------------
Um die Authentifizierung mittels Fingercode mit der homomorphen Verschlüsselung anzuwenden, muss der Ordner EncriptedJafisGit heruntergeladen werden. 
Anschließend muss der Ordner MyCpp heruntergeladen werden, welche die C Komponente und damit den Kryptografischen Teil des Prototyps ist.

MyCpp:
Kryptografische Teil des Prototypen. Dieser nimmt Parameter über eine Schnittstelle entgegen 
welcher im Ornder EncriptedJafisGit implementiert ist.

JafisGit:
Um die Authentifizierung mittels Fingecode ohne die Verschlüsselung anzuwenden reicht es diesen Ordner in einer Java Umgebung auszuführen.

ZFM20
------------
Treiber des Fingerprint Reader ZFM20

VM
------------
Die VM die mit angefügt ist enthällt alle Treiber und einen voll funktionsfähigen Prototypen.
Das Passwort für die VM ist "leo"

Sobald das virtuelle Laufwerk mit VB oder VMPLayer gestartet wurde,

befindet sich eine Readme Datei in /Home/Desktop/

Alles weitere ist darin beschrieben.

