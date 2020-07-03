package dataProviders;

import org.testng.annotations.DataProvider;

public class SpotifyDataProviders {

    @DataProvider (name = "artist_data")
    public Object[][] getArtistIDs() {
        return new Object[][] {
                {"3bVYqr2NfmwmL4YJisWhJI","Dubfire"},
                {"3TXQ1ddouwQAI78hV4hXDj","Maceo Plex"},
                {"1btv9qmIpbp7q1ixCYNdHu","Adam Beyer"},
                {"3KOHpygRuo1ruQAbEneR3t","George FitzGerald"},
                {"73A3bLnfnz5BoQjb4gNCga","Bicep"},
                {"6LHsnRBUYhFyt01PdKXAF5","Bob Moses"},
                {"7eMjH8sGNTqZycmrO1P9WD","Animal Picnic"},
                {"27gtK7m9vYwCyJ04zz0kIb","Lane 8"},
                {"5Dyfxq0ZrFjjeFBdSNxDbo","Yotto"},
                {"1VCo9PBRImHKuQyiubNSqF","AFFKT"}
        };
    }
}
