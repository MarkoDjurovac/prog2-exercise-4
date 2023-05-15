package at.ac.fhcampuswien.fhmdb.controller;

import at.ac.fhcampuswien.fhmdb.exception.DatabaseException;
import at.ac.fhcampuswien.fhmdb.ui.ExceptionDialog;
import at.ac.fhcampuswien.fhmdb.model.WatchlistEntity;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;

import java.sql.SQLException;
import java.util.List;

public class WatchlistRepository {
    private final Dao<WatchlistEntity, Long> dao;

    public WatchlistRepository(Dao<WatchlistEntity, Long> dao) {
        this.dao = dao;
    }

    public void removeFromWatchlist(WatchlistEntity movie) throws DatabaseException {
        try {
            String apiId = movie.getApiId();
            DeleteBuilder<WatchlistEntity, Long> deleteBuilder = dao.deleteBuilder();
            deleteBuilder.where().eq("apiId", apiId);
            dao.delete(deleteBuilder.prepare());
        } catch (SQLException | NullPointerException | IllegalArgumentException e) {
            throw new DatabaseException("Failed to create a connection", e);
        }
    }

    public List<WatchlistEntity> getAll() throws DatabaseException {
        try {
            return dao.queryForAll();
        } catch (SQLException | NullPointerException | IllegalArgumentException e) {
            throw new DatabaseException("Failed to create a connection", e);
        }
    }

    public void addToWatchlist(WatchlistEntity movie) throws DatabaseException {
        try {
            if(dao.queryForEq("apiId", movie.getApiId()).size() < 1) {
                dao.create(movie);
            }
        } catch (SQLException | NullPointerException | IllegalArgumentException e) {
            throw new DatabaseException("Failed to create a connection", e);
        }
    }
}