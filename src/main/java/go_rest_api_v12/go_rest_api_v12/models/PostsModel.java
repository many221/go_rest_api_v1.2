package go_rest_api_v12.go_rest_api_v12.models;

public class PostsModel {

    private int id;
    private int userId;
    private String title;
    private String body;

    public PostsModel() {
    }

    public PostsModel(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "PostsModel{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    /*
 {
id: 1749,
user_id: 3661,
title: "Ara thorax vox aptus claustrum sto viscus.",
body: "Pecto admoveo averto. Avaritia numquam cena. Verumtamen desipio defungo. Appono ambulo subiungo. Vesper cicuta tot. Mollitia amiculum depromo. Alii deleo tui. Aut soluta vis. Vero est sublime. Tabella creta adeo. Qui arto dolorum. Arma cedo spero. Temporibus non quia. Illum nesciunt sordeo. Alveus vetus aliqua. Abscido cerno thymum. Defungo eaque viscus. Vito vitae degenero. Facere ubi vulariter."
},
 */
}
