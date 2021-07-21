package enums;

public class Main {
    public static void main(String[] args) {
        System.out.println(Seasons.WINTER.name());
        System.out.println(Seasons.SPRING);
        System.out.println(Seasons.SUMMER);
        System.out.println(Seasons.FALL);

        printSeasons(Seasons.values());

        printSeason(Seasons.FALL);

        printExtended(ExtendedSeason.values());

    }

    static void printExtended(ExtendedSeason[] seasons) {
        for (Object season : seasons) {
            //System.out.println(season.getOrder());
        }
        //ExtendedSeason.S
    }

    static void printSeasons(Seasons[] seasons) {
        for (Seasons season : seasons) {
            printSeason(season);
        }
    }

    static void printSeason(Seasons season) {
        System.out.println(season.ordinal());
        System.out.println(season.name());
    }

}
