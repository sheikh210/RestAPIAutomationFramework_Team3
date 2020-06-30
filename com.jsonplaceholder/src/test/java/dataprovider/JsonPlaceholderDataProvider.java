package dataprovider;

import org.testng.annotations.DataProvider;

public class JsonPlaceholderDataProvider {

    @DataProvider (name = "comments_data")
    public Object[][] getData() {
        return new Object[][] {
                {3, "[Nikita@garfield.biz]"},
                {488, "[Albert@johnny.biz]"},
                {347, "[Anthony.Koepp@savannah.tv]"},
                {223, "[Zella@jan.net]"},
                {29, "[Jennings_Pouros@erica.biz]"},
                {130, "[Trevion_Kuphal@bernice.name]"},
                {46, "[Jeremy.Harann@waino.me]"},
                {79, "[Esther_Ratke@shayna.biz]"},
                {157, "[Makenzie@libbie.io]"},
                {289, "[Paolo@cristopher.com]"}
        };
    }
}
