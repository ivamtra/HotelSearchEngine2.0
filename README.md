# ColdSearchEngine
------------------------------------
This is an API for a hot hotel search engine<br>

## Uppsetning

### databaseHelper:

**Lýsing**: \
Sér um allar fyrirspurnir í gagnagrunninn. \
Er einungis notaður sem tenging. \
Þarf að nota sem viðfang í smiði á öllum controllerum. \
Aðferðir ættu ekki að vera notaðar beint.

**Smiður**:
```java
databaseHelper dbh = new databaseHelper
```

**Aðferðir**:
Engar

### loginController:

**Lýsing**: \
Sér um að halda utan um hver er skráður inn sem núverandi notandi og hvaða réttindi hann hefur
Notaður sem viðfang í smið hjá editController og bookController (mælt með að nota sama hlut fyrir báða)

**Smiður**:
```java
loginController lc = new loginController(databaseHelper dbh);
```

**Aðferðir**:
```java
person login(String name, String password)
```
* Athuga að ef engin manneskja er skráð með þetta nafn og lykilorð er skilað null
* Skráir annars manneskjuna inn og geymir það til að átta sig á hvaða réttindi hún hefur
* Athuga að ekki þarf að skrá út til að skrá annan inn

```java
void logout()
```
* Breytir í það að enginn sé skráður inn

```java
boolean logged(int personId)
```
* Athugum hvort einhver ákveðin manneskja sé skráð inn skilar true ef svo er

```java
int getLogged()
```
* Skilar personId hjá þeim sem er skráður inn, -1 ef enginn er skráður

### editController:

**Lýsing**: \
Sér um hvers lags breytingar á gagnagrunninum sem væri hægt að gera \
Tekur tillit til hver er skráður inn og hvaða réttindi hann hefur

**Smiður**:
```java
editController ec = new editController(databaseHelper dbh, loginController lc);
```

**Aðferðir**:
```java
int addHotel(Hotel hotel)
```
* Bætir hóteli við gagnagruninn
* Athugar fyrst hvort sá sem er skráður inn sé eigandi og hvort hann sé eigandi á hotel hlutnum sem er gefinn

```java
void addReview(Review review, int hotelId)
```
* Bætir review við gagnagrunninn á hótelið með hotelId
* Athugar fyrst hvort þetta sé viðskiptavinur

```java
void addRoom(Room room)
```
* Bætir við herbergi í gagnagrunninn á hótelið með hotelId
* Athugar fyrst hvort sá sem er skráður inn sé eigandi og hvort hann sé eigandi á hotelinu sem er gefið

### searchController:

**Lýsing**: \
Sér um leitir \
Notast mikið við restrictions hluti sem innihalda allskonar kröfur á leitina \ 
Notast ekkert við login (hver sem er getur leitað)

**Smiður**:
```java
searchController sc = new searchController(databaseHelper dbh)
```

**Aðferðir**:
```java
ArrayList<Hotel> getAllHotels()
```
* Skilar öllum hótelum sem eru skráð í gagnagrunninn

```java
ArrayList<Hotel> searchHotels(Restrictions r)
```
* Skilar þeim hótelum sem eiga herbergi sem stemma við Restrictions hlutinn

```java
ArrayList<Room> searchRooms(Restrictions r)
```
* Skilar þeim herbergjum sem stemma við Restrictions hlutinn

```java
ArrayList<Service> getAllServices()
```
* Skilar öllum þeim þjónustum sem mögulegt er að leita að


### bookController:

**Lýsing**: \
Sér um bókanir \
Bókanir taka mark af availability þeas ef það er laust þá má bóka það


**Smiður**:
```java
bookController bc = new bookController(databaseHelper dbh, loginController lc)
```

**Aðferðir**:
```java
boolean book(int roomId, Date start, Date end)
```
* Bókar herbergið með roomId frá start til end
* Bókar í nafni þess sem er skráður inn með loginController
* Bókar herbergið ef það er laust, skilar þá true, false annars

```java
boolean unbook(int bookingId)
```
* Afbókar bókunina með bookingId

```java
boolean getAvailability(int roomId, Date start, Date end)
```
* Skilar true ef herbergið með roomId er laust frá start-end
* false annars

# Sýnidæmi
Best væri að skilgreina breyturnar á eftirfarandi máta




## Test

## Upplýsingar um höfunda
| Nöfn                        | Netföng       | GitHub Notendanöfn |
| :----------------------------|:-------------| :------------------|
| Friðrik Snær Björnsson     | fsb3@hi.is   | 	fridrik-snaer  |
| Ívan Már Þrastarson           | imt1@hi.is   | ivamtra    |
| Kristján Leó Guðmundsson    | klg12@hi.is   | KristjanLeo   |
| Valdimar Örn Sverrisson     | vos9@hi.is    | Valdi9hi      |
