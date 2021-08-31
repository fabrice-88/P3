package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void getFavoritesWithSuccess() {
        Neighbour favoriteNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(0);
        service.addFavorite(favoriteNeighbours);
        assertEquals(1,service.getFavorites().size());
    }

    @Test
    public void addFavoritesWithSuccess() {
        Neighbour neighbourToAdd = DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(0);
        service.addFavorite(neighbourToAdd);
        assertTrue(service.getFavorites().contains(neighbourToAdd));
    }

    @Test
    public void deleteFavoritesWithSuccess() {
        Neighbour favoriteToDelete = DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(0);
        service.addFavorite(favoriteToDelete);
        assertTrue(service.getFavorites().contains(favoriteToDelete));
        service.deleteFavorite(favoriteToDelete);
        assertFalse(service.getFavorites().contains(favoriteToDelete));
    }
}