package hotelsearchengine.controllers;

import hotelsearchengine.models.*;
import hotelsearchengine.storage.DatabaseInterface;
import hotelsearchengine.storage.databaseHelper;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/******************************************************************************
 *  Nafn    : Friðrik Snær Björnsson
 *  T-póstur: fsb3@hi.is
 *
 *  Lýsing  : Lýsing á hvað forrit gerir, t.d. inntak, reikningar og úttak
 *
 *
 *****************************************************************************/
public class testing {
    private loginController lc;
    private editController ec;
    private searchController sc;
    private bookController bc;

    public testing(loginController lc, editController ec, searchController sc, bookController bc) {
        this.lc = lc;
        this.ec = ec;
        this.sc = sc;
        this.bc = bc;
    }

    public static void main(String[] args) {
        databaseHelper db = null;
        try {
            db = new databaseHelper();
        } catch (SQLException e) {
            System.out.println("Ekki hefur náð að tengjast");
            throw new RuntimeException(e);
        }

        loginController lc = new loginController(db);
        searchController sc = new searchController(db,lc);
        editController ec = new editController(db,lc);
        bookController bc = new bookController(db,lc);
        //Get gert eitthvað svona ef ég vil
        testing test = new testing(lc,ec,sc,bc);

        //Nú ætla ég að logga mig inn
        Person fridrik = lc.login("Fridrik","Lykilorðid-mitt");
        //Ég get sótt id'ið mitt
        int loggedId = lc.getLogged();
        //Er þetta ég sem er loggaður inn?
        System.out.println(lc.logged(loggedId));
        System.out.println(lc.logged(fridrik.getId()));

        //Ég ætla að leita að viðeigandi herbergi fyrir mig
        //Athugum að ÖLL viðföngin geta verið null og þá eru þau hundsuð
        //  --kall með öllum viðföngum sem null skilar öllu

        Integer lagmarksVerd = null; //Ég er mjög nískur og er alveg sama um lágmarksverð - forritið hundsar hluti sem eru ekki núll
        Integer hamarksVerd = 10000;
        Integer lagmarksStjornur = null;
        Integer hamarksStjornur = 9; //Ég þori nú ekki alveg á 10 stjörnu hótel - við höfðum 10 stjörnur en þið megið hafa 5 það ætti ekki að skipta máli
        String nafn = "Hótel saga"; //Ég hef heyrt góða hluti um þessa Hótel sögu
        String stadsetning = "Akureyri"; //Við erum að kíkja norður

        //Smá ves með þjónusturnar
        sc.getAllServices(); //Getum skoðað allar þjónustur hér - eða í töflunni Services
        ArrayList<Service> þjonustur = new ArrayList<>();
        þjonustur.add(new Service(1,"Casino"));
        þjonustur.add(new Service(2,"Gym"));
        //Þetta er kannski ekki alveg optimal hehe en ætti að virka

        //Síðan smá ves með Date hluti hehe
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH); //Mæli með þessum
        Date laustFra = null;
        Date laustTil = null;
        //Ugh try catch
        try {
            laustFra = sdf.parse("2022-04-18"); //18. apríl 2022
            laustTil = sdf.parse("2022-04-24"); //24. apríl 2022
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Double notendaEinkunn = null; //Mér er sama hvað fólk hefur að segja þetta er líka 1-10 en má vera 1-5

        Integer minnstaHerbStaerd = 3; //Þriggja svefnherbergja
        Integer mestaherbStaerd =3; //VIÐ ÞURFUM ÞRJÚ SVEFNHERBERGI

        //Þá erum við til í bullið

        ArrayList<Hotel> hotels = sc.searchHotels(lagmarksVerd,hamarksVerd,lagmarksStjornur,hamarksStjornur,nafn,stadsetning,þjonustur,laustFra,laustTil,notendaEinkunn,minnstaHerbStaerd,mestaherbStaerd);

        //Getum líka notað flotta flotta Restrictions hluti

        Restrictions r = new Restrictions(lagmarksVerd,hamarksVerd,lagmarksStjornur,hamarksStjornur,nafn,stadsetning,þjonustur,laustFra,laustTil,notendaEinkunn,minnstaHerbStaerd,mestaherbStaerd);
        hotels = sc.searchHotels(r);

        //Getum líka leitað að þeim herbergjum sem stemma við kröfur okkar
        ArrayList<Room> rooms = sc.searchHotelRooms(r);

        //Nú sá ég gott herbergi, ég ætla að bóka það
        int herbergisEinkennisNumer = 3;
        Date startDate = laustFra;
        Date endDate = laustTil;

        //book fallið bókar herbergið í mínu nafni
        //bc.unbook(5);
        //boolean tokst = bc.book(herbergisEinkennisNumer,startDate,endDate);
        //ef það skilar false þá var herbergið ekki laust
        //Getum líka skoðað hvort það sé laust bein með getAvailability
        boolean laust = bc.getAvailability(herbergisEinkennisNumer,startDate,endDate);

        //Ég ætla að skilja eftir Review á þessu hóteli
        int hotelId = 3;
        String umsogn = "Þetta hótel var GEÐVEIKT";
        int einkunn = 4;
        Review review = new Review(hotelId, lc.getLogged(),umsogn,einkunn);
        //ec.addReview(review);

        //Vá hvað þetta var gaman og æði
    }
}
