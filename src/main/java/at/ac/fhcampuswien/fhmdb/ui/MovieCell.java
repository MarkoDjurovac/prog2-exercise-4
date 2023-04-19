package at.ac.fhcampuswien.fhmdb.ui;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.stream.Collectors;

public class MovieCell extends ListCell<Movie> {
    private final Label title = new Label();
    private final Label detail = new Label();
    private final Label genres = new Label();
    private final Label rating = new Label();
    private final Label releaseYear = new Label();
    private final VBox layout = new VBox(title, detail, genres, rating, releaseYear);

    @Override
    protected void updateItem(Movie movie, boolean empty) {
        super.updateItem(movie, empty);

        if (empty || movie == null) {
            setText(null);
            setGraphic(null);
        } else {
            this.getStyleClass().add("movie-cell");

            title.setText(movie.getTitle());

            detail.setText(
                    movie.getDescription() != null
                            ? movie.getDescription()
                            : "No description available"
            );

            genres.setText(
                    movie.getGenres() != null
                        ? ("Genres: " + movie.getGenres().stream().map(String::toString).collect(Collectors.joining(", ")))
                        : ("No genres available")
            );

            releaseYear.setText(
                       movie.getReleaseYear() != 0
                            ? ("Release Year: " + movie.getReleaseYear())
                            : ("No release year available")
            );

            rating.setText(
                    movie.getRating() != 0
                        ? ("Rating: " + movie.getRating())
                        : ("No rating available")
            );

            // color scheme
            title.getStyleClass().add("text-yellow");
            detail.getStyleClass().add("text-white");
            genres.getStyleClass().addAll("text-white", "text-italic");
            rating.getStyleClass().add("text-white");
            releaseYear.getStyleClass().add("text-white");
            layout.setBackground(new Background(new BackgroundFill(Color.web("#454545"), null, null)));

            // layout
            title.getFont();
            title.fontProperty().set(Font.font(20));
            detail.setMaxWidth(this.getScene().getWidth() - 30);
            detail.setWrapText(true);
            layout.setPadding(new Insets(10));
            layout.spacingProperty().set(10);
            layout.alignmentProperty().set(javafx.geometry.Pos.CENTER_LEFT);
            releaseYear.alignmentProperty().set(Pos.CENTER_RIGHT);
            setGraphic(layout);
        }
    }
}

